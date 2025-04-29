import numpy as np
import cv2
from scipy import fft

# Definimos el filtro pasa bajas (o pasa altas) como una función gaussiana
def create_gaussian_filter(shape, sigma):
    F1 = np.arange(-shape[0]//2, shape[0]//2, 1)
    F2 = np.arange(-shape[1]//2, shape[1]//2, 1)
    X, Y = np.meshgrid(F1, F2)
    R = np.sqrt(X**2 + Y**2)
    R = R / np.max(R)
    Filt_Im = np.exp(-(R**2) / (2 * sigma**2))
    return Filt_Im

# Creamos el filtro gaussiano de tamaño 512x512
Filt_Im = create_gaussian_filter((512, 512), sigma=0.2)

# Tomamos la referencia de la cámara
camera = cv2.VideoCapture(0)

while(True):
    # Leemos un frame de la cámara
    _, frame = camera.read()
    
    # Redimensionamos la imagen a 512x512
    image = cv2.resize(frame, (512, 512))
    # Convertimos la imagen a escala de grises (Un plano de color)
    gray_image = cv2.cvtColor(image, cv2.COLOR_RGB2GRAY)
    # Establecemos el tipo de dato de intensidad como float
    gray_f = np.float64(gray_image)

    # Calculamos la transformada discreta de fourier 2D
    Fimg = fft.fft2(gray_f)

    # Movemos el origen al centro de la imagen (bajas frecuencias)
    Fsh_Image = fft.fftshift(Fimg)

    # Aplicamos el filtro gaussiano al espectro de la imagen (Imagen x Máscara)
    FFt_filtered = Fsh_Image * Filt_Im

    # Recuperamos la imagen calculando la transformada inversa de fourier del espectro ya filtrado
    ImageFiltered = fft.ifft2(fft.ifftshift(FFt_filtered))

    # Normalizamos las imagenes para mostrarlas en pantalla
    Fsh_ImageN = cv2.normalize(abs(Fsh_Image), None, alpha=0, beta=255, norm_type=cv2.NORM_MINMAX, dtype=cv2.CV_32F)
    FFt_filteredN = cv2.normalize(abs(FFt_filtered), None, alpha=0, beta=255, norm_type=cv2.NORM_MINMAX, dtype=cv2.CV_32F)
    ImageFilteredN = cv2.normalize(abs(ImageFiltered), None, alpha=0, beta=255, norm_type=cv2.NORM_MINMAX, dtype=cv2.CV_8U)
    
    # Mostramos las imagenes en pantalla
    cv2.imshow("Filtrada", ImageFilteredN)
    cv2.imshow("Camara", image)
    cv2.imshow("Espectro", Fsh_ImageN)
    cv2.imshow("Espectro Filtrado", FFt_filteredN)

    key = cv2.waitKey(1)

    # Salir de la captura de video si presionamos 'q'
    if key & 0xFF == ord("q"):
        break

camera.release()
cv2.destroyAllWindows()

camera.release()
