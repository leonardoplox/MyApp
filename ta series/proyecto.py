import os
import matplotlib.pyplot as plt
import numpy as np
import scipy.fftpack as fft

from mpl_toolkits.mplot3d import Axes3D
from matplotlib import cm



beta = 255
# Creamos un filtro pasa bajas gaussiano de dimensiones 512x512
F1=np.arange(-256,256,1)
F2=np.arange(-256,256,1)
[X,Y]=np.meshgrid(F1,F2)
R=np.sqrt(X**2+Y**2)
R=R/np.max(R)
sigma = 0.5


# Filtro pasa bajas con función gaussiana
Filt_Im = np.exp(-(R**2)/(2*sigma**2))


# Graficamos el filtro en 2D
fig = plt.figure(figsize=(4, 4))
plt.imshow(Filt_Im)
plt.show()

# Graficamos el filtro en 3D
fig = plt.figure(figsize=(4, 4))
ax = fig.add_subplot(111, projection='3d')  # Usamos add_subplot para 3D
ax.plot_surface(X, Y, Filt_Im, cmap=cm.viridis)
plt.show()
