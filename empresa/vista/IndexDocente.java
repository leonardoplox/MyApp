/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.empresa.vista;

import java.util.List;
import pe.com.empresa.DaoImpl.DocenteDaoImpl;
import pe.com.empresa.dao.DocenteDao;
import pe.com.empresa.modelo.Docente;
import pe.com.empresa.utils.Lectura;
import pe.com.empresa.utils.Utilitarios;
    
public class IndexDocente {
    private static Lectura leer = new Lectura();
    private static DocenteDao daoimpl = new DocenteDaoImpl();
    
    public static boolean dniExiste(String dni) {
        List<Docente> docentes = daoimpl.listar();
        for (Docente docente : docentes) {
            if (docente.getDni().equals(dni)) {
                return true;
            }
        }
        return false;
    }

    public static boolean esNumerico(String str) {
        return str.matches("\\d+");
    }
    public static void agregar(){
        String nombre;
        String dni;
        String area;
        double salario;
        char continuar;
        
        do{
        System.out.println("Agregar docente}: ");
        System.out.print("> Nombre: ");
        nombre = leer.cadena();
        do{
            System.out.print("> DNI: ");
            dni = leer.cadena();
            if(dni.length()!=8 || !esNumerico(dni)){
            System.out.println("DNI debe tener 8 dígitos numéricos.");
            }else if(dniExiste(dni)){
                    System.out.println("DNI ya existe. Ingrese un DNI único.");
            }
          }while(dni.length()!=8 || !esNumerico(dni) || dniExiste(dni));
        System.out.print("> Area: ");
        area = leer.cadena();
        System.out.print("> Salario: ");    
        salario = leer.decimal();
        Docente docente = new Docente(0, nombre, dni, area, salario);
        daoimpl.agregar(docente);
        
        System.out.println("¿Desea agregar otro docente? (s/n): ");
        continuar = leer.letra();
        }while(continuar=='S' || continuar=='s');
    }
    public static void eliminar(){
        char continuar;
        do{
        listar();
        System.out.print("Ingrese el id del docente eliminar: ");
        int id = leer.entero();
        daoimpl.eliminar(id);
        
        System.out.println("¿Desea eliminar otro docente? (s/n): ");
        continuar = leer.letra();
        }while(continuar=='S' || continuar=='s');
    }
    public static void editar(){
        int id;
        String nombre;
        String dni;
        String area;
        double salario;
        char continuar;
        do{
        listar();
        System.out.print("Ingrese el ID del docente a editar: ");
        id = leer.entero();
        System.out.print("> Nuevo Nombre: ");
        nombre = leer.cadena();
        do {
                System.out.print("> Nuevo DNI: ");
                dni = leer.cadena();
                if (dni.length() != 8 || !esNumerico(dni)) {
                    System.out.println("DNI debe tener 8 dígitos numéricos.");
                } else if (dniExiste(dni)) {
                    System.out.println("DNI ya existe. Ingrese un DNI único.");
                }
            } while (dni.length() != 8 || !esNumerico(dni) || dniExiste(dni));
        System.out.print("> Nuevo Area: ");
        area = leer.cadena();
        System.out.print("> Nuevo Salario: ");
        salario = leer.decimal();
        Docente docente = new Docente(id, nombre, dni, area, salario);
        daoimpl.editar(docente);
        
        System.out.println("¿Desea seguir editando otro docente? (s/n): ");
        continuar = leer.letra();
        }while(continuar=='S' || continuar=='s');
    }
    
    public static void imprimirEspacios(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            System.out.print(" ");
        }
    }
    
    public static String truncarConPuntos(String texto, int tamañoMax) {
        if (texto.length() > tamañoMax) {
            return texto.substring(0, tamañoMax - 3) + "...";
        } else {
            return texto;
        }
    }
    
    public static void listar(){
        List<Docente> docentes = daoimpl.listar();
        int tamañoMaxId = 4;
        int tamañoMaxNombre = 10;
        int tamañoMaxDni = 8;
        int tamañoMaxArea = 10;
        int tamañoMaxSalario = 10;

        System.out.println("=======================================================");
        System.out.println("\t        -- LISTADO DE DOCENTES --");
        System.out.println("=======================================================");
        System.out.print("ID ");
        imprimirEspacios(tamañoMaxId - 2);
        System.out.print("| Nombre ");
        imprimirEspacios(tamañoMaxNombre - 6);
        System.out.print("| DNI ");
        imprimirEspacios(tamañoMaxDni - 3);
        System.out.print("| Área ");
        imprimirEspacios(tamañoMaxArea - 5);
        System.out.print("| Salario ");
        imprimirEspacios(tamañoMaxSalario - 8);
        System.out.println(" |");
        System.out.println("--------------------------------------------------------");

        for (Docente docente : docentes) {
            System.out.print(docente.getIddocente());
            imprimirEspacios(tamañoMaxId - String.valueOf(docente.getIddocente()).length());
            System.out.print(" | " + truncarConPuntos(docente.getNombre(), tamañoMaxNombre));
            imprimirEspacios(tamañoMaxNombre - truncarConPuntos(docente.getNombre(), tamañoMaxNombre).length());
            System.out.print(" | " + truncarConPuntos(docente.getDni(), tamañoMaxDni));
            imprimirEspacios(tamañoMaxDni - truncarConPuntos(docente.getDni(), tamañoMaxDni).length());
            System.out.print(" | " + truncarConPuntos(docente.getArea(), tamañoMaxArea));
            imprimirEspacios(tamañoMaxArea - truncarConPuntos(docente.getArea(), tamañoMaxArea).length());
            System.out.print(" | " + docente.getSalario());
            imprimirEspacios(tamañoMaxSalario - String.valueOf(docente.getSalario()).length());
            System.out.println(" |");
        }
       System.out.println("--------------------------------------------------------");
        

    }    
    public static void menu(){
        System.out.println("""
                           ***DOCENTE***
                           1. AGREGAR
                           2. ELIMINAR
                           3. EDITAR
                           4. LISTAR
                           5. VOLVER
                           """);
        System.out.print("Escoja opción[1-5]: ");
    }
    public static void inicio(){
        int opcion;
        do{
            menu();
            opcion=leer.entero();
            switch (opcion) {
                case 1-> agregar();
                case 2-> eliminar();
                case 3-> editar();
                case 4-> listar();
                case 5-> Utilitarios.volver();
                default-> Utilitarios.error();
            }
        }while(opcion!=5);
    }    
    
}
