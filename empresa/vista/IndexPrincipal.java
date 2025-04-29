package pe.com.empresa.vista;

import pe.com.empresa.utils.Lectura;
import pe.com.empresa.utils.Utilitarios;

/**
 * @author Daniel Levano
 */
public class IndexPrincipal {  
    private static Lectura leer = new Lectura();

    public static void menu(){
        System.out.println("""
                           ***MENU PRINCIPAL***
                           1. ALUMNO
                           2. LIBRO
                           3. DOCENTE
                           4. SALIR
                           """);
        System.out.print("Escoja opción[1-4]: ");
    }
    public static void inicio(){
        int opcion;
        do{
            menu();
            opcion=leer.entero();
            switch (opcion) {
                case 1-> IndexAlumno.inicio();
                case 2-> IndexLibro.inicio();
                case 3-> IndexDocente.inicio();
                case 4-> Utilitarios.salir();
                default-> Utilitarios.error();
            }
        }while(opcion!=4);
    }       
}
