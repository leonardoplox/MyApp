/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.empresa.vista;

import java.util.List;
import pe.com.empresa.DaoImpl.LibroDaoImpl;
import pe.com.empresa.dao.LibroDao;
import pe.com.empresa.modelo.Libro;
import pe.com.empresa.utils.Lectura;
import pe.com.empresa.utils.Utilitarios;

public class IndexLibro {
    private static Lectura leer = new Lectura();
    private static LibroDao daoimpl = new LibroDaoImpl();
    public static void agregar(){
        String codigo;
        String nombre;
        String autor;
        double precio;
        char continuar;
        do{
        System.out.println("Agregar libro}: ");
        System.out.print("> Codigo: ");
        codigo = leer.cadena();
        System.out.print("> Nombre: ");
        nombre = leer.cadena();
        System.out.print("> Autor: ");
        autor = leer.cadena();
        System.out.print("> Precio: ");
        precio = leer.decimal();
        Libro libro = new Libro(0, codigo, nombre, autor, precio);
        daoimpl.agregar(libro);
        
        System.out.println("¿Desea seguir editando otro libro? (s/n): ");
        continuar = leer.letra();
        }while(continuar=='S' || continuar=='s');
    }
    public static void eliminar(){
        char continuar;
        do{
        listar();
        System.out.print("Ingrese el id del libro eliminar: ");
        int id = leer.entero();
        daoimpl.eliminar(id);
        
        System.out.println("¿Desea seguir editando otro libro? (s/n): ");
        continuar = leer.letra();
        }while(continuar=='S' || continuar=='s');
    }
    public static void editar(){
        int id;
        String codigo;
        String nombre;
        String autor;
        double precio;
        char continuar;
        do{
        listar();
        System.out.print("Ingrese el ID del libro a editar: ");
        id = leer.entero();
        System.out.print("> Nuevo Codigo: ");
        codigo = leer.cadena();
        System.out.print("> Nuevo Nombre: ");
        nombre = leer.cadena();
        System.out.print("> Nuevo Autor: ");
        autor = leer.cadena();
        System.out.print("> Nuevo Precio: ");
        precio = leer.decimal();
        Libro libro = new Libro(id, codigo, nombre, autor, precio);
        daoimpl.editar(libro);
        
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
        List<Libro> libros = daoimpl.listar();
        int tamañoMaxId = 4;
        int tamañoMaxCodigo = 7;
        int tamañoMaxNombre = 10;
        int tamañoMaxAutor = 10;
        int tamañoMaxPrecio = 10;

        System.out.println("=======================================================");
        System.out.println("\t         -- LISTADO DE LIBROS --");
        System.out.println("=======================================================");
        System.out.print("ID ");
        imprimirEspacios(tamañoMaxId - 2);
        System.out.print("| Código ");
        imprimirEspacios(tamañoMaxCodigo - 7);
        System.out.print("| Nombre ");
        imprimirEspacios(tamañoMaxNombre - 6);
        System.out.print("| Autor ");
        imprimirEspacios(tamañoMaxAutor - 5);
        System.out.print("| Precio ");
        imprimirEspacios(tamañoMaxPrecio - 7);
        System.out.println(" |");
        System.out.println("--------------------------------------------------------");

        for (Libro libro : libros) {
            System.out.print(libro.getIdlibro());
            imprimirEspacios(tamañoMaxId - String.valueOf(libro.getIdlibro()).length());
            System.out.print(" | " + truncarConPuntos(libro.getCodigo(), tamañoMaxCodigo));
            imprimirEspacios(tamañoMaxCodigo - truncarConPuntos(libro.getCodigo(), tamañoMaxCodigo).length());
            System.out.print(" | " + truncarConPuntos(libro.getMombre(), tamañoMaxNombre));
            imprimirEspacios(tamañoMaxNombre - truncarConPuntos(libro.getMombre(), tamañoMaxNombre).length());
            System.out.print(" | " + truncarConPuntos(libro.getAutor(), tamañoMaxAutor));
            imprimirEspacios(tamañoMaxAutor - truncarConPuntos(libro.getAutor(), tamañoMaxAutor).length());
            System.out.print(" | " + libro.getPrecio());
            imprimirEspacios(tamañoMaxPrecio - String.valueOf(libro.getPrecio()).length());
            System.out.println(" |");
        }
        System.out.println("--------------------------------------------------------");
    }    
    public static void menu(){
        System.out.println("""
                           ***LIBRO***
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


