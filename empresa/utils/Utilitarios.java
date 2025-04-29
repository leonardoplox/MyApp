package pe.com.empresa.utils;

/**
 *
 * @author Daniel Levano
 */
public class Utilitarios {
    public static void volver(){
        System.out.println("> Regresando al MENU PRINCIPAL...");
    }
    public static void salir(){
        System.out.println("Gracias por su visita");
    }
    public static void error(){
        System.out.println("Error");
    }
      public static void error_repetir_operacion() {
        System.out.println("ERROR 333, por favor escribe bien [1-2]");
    }

    public static void error_busqueda() {
        System.out.println("ERROR 333, por favor escriba bien escribe bien  <");
    }

    public static void menu_repetir_operacion() {
        System.out.println("");
        System.out.println("---> Deseas repetir esta operacion ");
        System.out.println("\t 1. Si");
        System.out.println("\t 2. No");
        System.out.println("\t Seleccione una opcion [1-2]: ");
    }

    public static String recortar_cadena(String cadena, int limite) {
        String cadena_recortada;
        if (cadena.length() < limite) {
            String vacio = "";
            for (int i = cadena.length(); i < limite; i++) {
                vacio += " ";
            }
            cadena_recortada = cadena + vacio;
        } else {
            cadena_recortada = cadena.substring(0, limite - 3) + "...";
        }
        return cadena_recortada;
    }

    public static String repite_cadena(String cad, int times) {
        String cadRepetida = "";
        for (int i = 0; i < times; i++) {
            cadRepetida += cad;
        }
        return cadRepetida;
    }

}

