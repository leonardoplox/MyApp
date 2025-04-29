package pe.com.empresa.vista;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import pe.com.empresa.DaoImpl.AlumnoDaoImpl;
import pe.com.empresa.dao.AlumnoDao;
import pe.com.empresa.modelo.Alumno;
import pe.com.empresa.utils.Lectura;
import pe.com.empresa.utils.Utilitarios;

/**
 *
 * @author Daniel Levano
 */
public class IndexAlumno {
    private static Lectura leer = new Lectura();
    private static AlumnoDao daoimpl = new AlumnoDaoImpl();
    
    
     public static boolean hayDatos() {
        List<Alumno> alumnos = daoimpl.listar();
        return alumnos != null && !alumnos.isEmpty();
    }
     
    public static boolean nombreExiste(String nombre) {
    List<Alumno> alumnos = daoimpl.listar();
    for (Alumno alumno : alumnos) {
        if (alumno.getNombre().equalsIgnoreCase(nombre)) {
            return true;
        }
    }
    return false;
}
    
    
     public static boolean dniExiste(String dni) {
        List<Alumno> alumnos = daoimpl.listar();
        for (Alumno alumno : alumnos) {
            if (alumno.getDni().equals(dni)) {
                return true;
            }
        }
        return false;
    }

    public static boolean esNumerico(String str) {
        return str.matches("\\d+");
    }
    public static void agregar() {
    String nombre;
    String apellido;
    String dni;
    String curso;
    char continuar;

    do {
        System.out.println("Agregar Alumno: ");
        do {
            System.out.print("> Nombre: ");
            nombre = leer.cadena();
            if (nombreExiste(nombre)) {
                System.out.println("Nombre ya existe. Ingrese un nombre único.");
            }
        } while (nombreExiste(nombre));

        System.out.print("> Apellido: ");
        apellido = leer.cadena();

        do {
            System.out.print("> DNI: ");
            dni = leer.cadena();
            if (dni.length() != 8 || !esNumerico(dni)) {
                System.out.println("DNI debe tener 8 dígitos numéricos.");
            } else if (dniExiste(dni)) {
                System.out.println("DNI ya existe. Ingrese un DNI único.");
            }
        } while (dni.length() != 8 || !esNumerico(dni) || dniExiste(dni));

        System.out.print("> Curso: ");
        curso = leer.cadena();

        Alumno alumno = new Alumno(0, nombre, apellido, dni, curso);
        daoimpl.agregar(alumno);

        System.out.print("¿Desea agregar otro alumno? (s/n): ");
        continuar = leer.letra();
    } while (continuar == 's' || continuar == 'S');
}
    public static void eliminar(){
         if (!hayDatos()) {
            System.out.println("No hay datos. Por favor, ingrese datos primero.");
            return;
        }
        char continuar;

    do {
        listar();
        System.out.print("Ingrese el id del alumno a eliminar: ");
        int id = leer.entero();
        daoimpl.eliminar(id);

        System.out.print("¿Desea eliminar otro alumno? (s/n): ");
        continuar = leer.letra();
       } while (continuar == 's' || continuar == 'S');
    }
    public static void editar() {
        if (!hayDatos()) {
            System.out.println("No hay datos. Por favor, ingrese datos primero.");
            return;
        }
    char continuar;

    do {
        int id;
        String nombre;
        String apellido;
        String dni;
        String curso;

        listar();
        System.out.print("Ingrese el ID del alumno a editar: ");
        id = leer.entero();

        do {
            System.out.print("> Nuevo Nombre: ");
            nombre = leer.cadena();
            if (nombreExiste(nombre)) {
                System.out.println("Nombre ya existe. Ingrese un nombre único.");
            }
        } while (nombreExiste(nombre));

        System.out.print("> Nuevo Apellido: ");
        apellido = leer.cadena();

        do {
            System.out.print("> Nuevo DNI: ");
            dni = leer.cadena();
            if (dni.length() != 8 || !esNumerico(dni)) {
                System.out.println("DNI debe tener 8 dígitos numéricos.");
            } else if (dniExiste(dni)) {
                System.out.println("DNI ya existe. Ingrese un DNI único.");
            }
        } while (dni.length() != 8 || !esNumerico(dni) || dniExiste(dni));

        System.out.print("> Nuevo Curso: ");
        curso = leer.cadena();

        Alumno alumno = new Alumno(id, nombre, apellido, dni, curso);
        daoimpl.editar(alumno);

        System.out.print("¿Desea editar otro alumno? (s/n): ");
        continuar = leer.letra();
    } while (continuar == 's' || continuar == 'S');
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
        if (!hayDatos()) {
            System.out.println("No hay datos. Por favor, ingrese datos primero.");
            return;
        }
    List<Alumno> alumnos = daoimpl.listar();
        int tamañoMaxId = 4;
        int tamañoMaxNombre = 10;
        int tamañoMaxApellido = 10;
        int tamañoMaxDni = 8;
        int tamañoMaxCurso = 10;

        System.out.println("=======================================================");
        System.out.println("\t       -- LISTADO DE ALUMNOS --"); 
        System.out.println("=======================================================");
        System.out.print("ID ");
        imprimirEspacios(tamañoMaxId - 2);
        System.out.print("| Nombre ");
        imprimirEspacios(tamañoMaxNombre - 6);
        System.out.print("| Apellido ");
        imprimirEspacios(tamañoMaxApellido - 8);
        System.out.print("| DNI ");
        imprimirEspacios(tamañoMaxDni - 3);
        System.out.print("| Curso ");
        imprimirEspacios(tamañoMaxCurso - 6);
        System.out.println(" |");
        System.out.println("--------------------------------------------------------");

        for (Alumno alumno : alumnos) {
            System.out.print(alumno.getIdalumno());
            imprimirEspacios(tamañoMaxId - String.valueOf(alumno.getIdalumno()).length());
            System.out.print(" | " + truncarConPuntos(alumno.getNombre(), tamañoMaxNombre));
            imprimirEspacios(tamañoMaxNombre - truncarConPuntos(alumno.getNombre(), tamañoMaxNombre).length());
            System.out.print(" | " + truncarConPuntos(alumno.getApellido(), tamañoMaxApellido));
            imprimirEspacios(tamañoMaxApellido - truncarConPuntos(alumno.getApellido(), tamañoMaxApellido).length());
            System.out.print(" | " + truncarConPuntos(alumno.getDni(), tamañoMaxDni));
            imprimirEspacios(tamañoMaxDni - truncarConPuntos(alumno.getDni(), tamañoMaxDni).length());
            System.out.print(" | " + truncarConPuntos(alumno.getCurso(), tamañoMaxCurso));
            imprimirEspacios(tamañoMaxCurso - truncarConPuntos(alumno.getCurso(), tamañoMaxCurso).length());
            System.out.println(" |");
        }
            System.out.println("--------------------------------------------------------");
    }
    
    public static void busquedaNombre() {
        if (!hayDatos()) {
            System.out.println("No hay datos. Por favor, ingrese datos primero.");
            return;
        }
        buscar_nombre();
    }
     public static void buscarid() {
         if (!hayDatos()) {
            System.out.println("No hay datos. Por favor, ingrese datos primero.");
            return;
        }
        char continuar;
        do {
            System.out.print("Ingrese el ID del alumno a buscar: ");
            int id = leer.entero();
            Alumno alumno = daoimpl.buscar(id);

            if (alumno != null) {
                int tamañoMaxId = 4;
                int tamañoMaxNombre = 10;
                int tamañoMaxApellido = 10;
                int tamañoMaxDni = 8;
                int tamañoMaxCurso = 10;

                System.out.println("=======================================================");
                System.out.println("\t       -- DETALLE DEL ALUMNO --"); 
                System.out.println("=======================================================");
                System.out.print("ID ");
                imprimirEspacios(tamañoMaxId - 2);
                System.out.print("| Nombre ");
                imprimirEspacios(tamañoMaxNombre - 6);
                System.out.print("| Apellido ");
                imprimirEspacios(tamañoMaxApellido - 8);
                System.out.print("| DNI ");
                imprimirEspacios(tamañoMaxDni - 3);
                System.out.print("| Curso ");
                imprimirEspacios(tamañoMaxCurso - 6);
                System.out.println(" |");
                System.out.println("--------------------------------------------------------");

                System.out.print(alumno.getIdalumno());
                imprimirEspacios(tamañoMaxId - String.valueOf(alumno.getIdalumno()).length());
                System.out.print(" | " + truncarConPuntos(alumno.getNombre(), tamañoMaxNombre));
                imprimirEspacios(tamañoMaxNombre - truncarConPuntos(alumno.getNombre(), tamañoMaxNombre).length());
                System.out.print(" | " + truncarConPuntos(alumno.getApellido(), tamañoMaxApellido));
                imprimirEspacios(tamañoMaxApellido - truncarConPuntos(alumno.getApellido(), tamañoMaxApellido).length());
                System.out.print(" | " + truncarConPuntos(alumno.getDni(), tamañoMaxDni));
                imprimirEspacios(tamañoMaxDni - truncarConPuntos(alumno.getDni(), tamañoMaxDni).length());
                System.out.print(" | " + truncarConPuntos(alumno.getCurso(), tamañoMaxCurso));
                imprimirEspacios(tamañoMaxCurso - truncarConPuntos(alumno.getCurso(), tamañoMaxCurso).length());
                System.out.println(" |");
                System.out.println("--------------------------------------------------------");
            } else {
                System.out.println("Alumno con ID " + id + " no encontrado.");
            }
            
            System.out.print("¿Desea buscar otro alumno por ID? (s/n): ");
            continuar = leer.letra();
        } while (continuar == 's' || continuar == 'S');
    }
      public static void reporte_individual(int pos) {

        List<Alumno> alumnos = daoimpl.listar();

        int idalumno = alumnos.get(pos).getIdalumno();
        String nombre = alumnos.get(pos).getNombre();
        String apellido = alumnos.get(pos).getApellido();
        String dni = alumnos.get(pos).getDni();
        String curso = alumnos.get(pos).getCurso();

        System.out.println(
                Utilitarios.recortar_cadena(String.valueOf(pos + 1), 8) + "\t"
                + Utilitarios.recortar_cadena(String.valueOf(idalumno), 8) + "\t\t"
                + Utilitarios.recortar_cadena(nombre, 16) + "\t"
                + Utilitarios.recortar_cadena(apellido, 16) + "\t\t"
                + Utilitarios.recortar_cadena(dni, 16) + "\t"
                + Utilitarios.recortar_cadena(String.valueOf(curso), 16)
        );
    }

    public static void reporte_titulo() {
        System.out.println("Nro \t\tIDALUMNO \t\tNOMBRE \t\t\tAPELLIDO \t\t\tDNI \t\t\tEDAD");
    }
     public static void menu_buscar_nombre() {
        System.out.println("");
        System.out.println("> Formas de buscar: ");
        System.out.println(">  letra*");
        System.out.println(">  *letra");
        System.out.println(">  *letra*\n");
        System.out.println("Ingrese el nombre a buscar: ");
    }

//========= BUSQUEDA ===========================
    public static void buscar_nombre() {
        menu_buscar_nombre();
        String bus_nombre = leer.cadena().toLowerCase();
        String bus_nombre_puro;

        int pos_final = bus_nombre.length() - 1;

        boolean b_inicio = (bus_nombre.charAt(0) == '*' && bus_nombre.charAt(pos_final) != '*');
        boolean b_final = (bus_nombre.charAt(0) != '*' && bus_nombre.charAt(pos_final) == '*');
        boolean b_inicio_final = (bus_nombre.charAt(0) == '*' && bus_nombre.charAt(pos_final) == '*');

        if (b_inicio) {
            bus_nombre_puro = bus_nombre.substring(1, pos_final + 1);
            buscar_nombre_inicio(bus_nombre_puro);
        } else if (b_final) {
            bus_nombre_puro = bus_nombre.substring(0, pos_final);
            buscar_nombre_final(bus_nombre_puro);
        } else if (b_inicio_final) {
            bus_nombre_puro = bus_nombre.substring(1, pos_final);
            buscar_nombre_inicio_final(bus_nombre_puro);
        } else {
            Utilitarios.error_busqueda();
            buscar_nombre();
        }
    }

    public static void buscar_nombre_inicio(String bus_nombre_puro) {
        System.out.println("\n");
        List<Alumno> alumnos = daoimpl.listar();
        int largo_bus_nombre_puro = bus_nombre_puro.length();
        int largo_array = alumnos.size();
        int coincidencia = 0;
        String nombre_ropa;
        String sub_nombre_ropa;

        for (int i = 0; i < largo_array; i++) {
            nombre_ropa = alumnos.get(i).getNombre().toLowerCase();

            int inicio = nombre_ropa.length() - largo_bus_nombre_puro;
            int fin = nombre_ropa.length();

            if (nombre_ropa.length() >= largo_bus_nombre_puro) {
                sub_nombre_ropa = nombre_ropa.substring(inicio, fin);

                if (sub_nombre_ropa.equals(bus_nombre_puro)) {
                    if (coincidencia == 0) {
                        System.out.println("> COINCIDENCIAS PARA: " + bus_nombre_puro.toUpperCase());
                        reporte_titulo();
                        System.out.println(Utilitarios.repite_cadena("=", 150));
                        coincidencia++;
                    }
                    reporte_individual(i);
                    System.out.println(Utilitarios.repite_cadena("- ", 75));
                }
            }
        }
        if (largo_array == 0) {
            System.out.println("> EL ARRAY ESTA VACIO... ");
        } else if (coincidencia == 0) {
            System.out.println("> NO HAY COINCIDENCIA... ");
        }
        System.out.println("\n");
    }

    public static void buscar_nombre_final(String bus_nombre_puro) {
        System.out.println("\n");
        List<Alumno> alumnos = daoimpl.listar();
        int largo_bus_nombre_puro = bus_nombre_puro.length();
        int largo_array = alumnos.size();
        int coincidencia = 0;
        String nombre_ropa;
        String sub_nombre_ropa;

        for (int i = 0; i < largo_array; i++) {
            nombre_ropa = alumnos.get(i).getNombre().toLowerCase();

            if (nombre_ropa.length() >= largo_bus_nombre_puro) {
                sub_nombre_ropa = nombre_ropa.substring(0, largo_bus_nombre_puro);

                if (sub_nombre_ropa.equals(bus_nombre_puro)) {
                    if (coincidencia == 0) {
                        System.out.println("> COINCIDENCIAS PARA: " + bus_nombre_puro.toUpperCase());
                        reporte_titulo();
                        System.out.println(Utilitarios.repite_cadena("=", 150));
                        coincidencia++;
                    }
                    reporte_individual(i);
                    System.out.println(Utilitarios.repite_cadena("- ", 75));
                }
            }
        }
        if (largo_array == 0) {
            System.out.println("> EL ARRAY ESTA VACIO... ");
        } else if (coincidencia == 0) {
            System.out.println("> NO HAY COINCIDENCIA... ");
        }
        System.out.println("\n");
    }

    public static void buscar_nombre_inicio_final(String bus_nombre_puro) {
        System.out.println("\n");
        List<Alumno> alumnos = daoimpl.listar();
        int largo_bus_nombre_puro = bus_nombre_puro.length();
        int largo_array = alumnos.size();
        int coincidencia = 0;

        String nombre_ropa;
        String sub_nombre_ropa;

        for (int i = 0; i < largo_array; i++) {
            nombre_ropa = alumnos.get(i).getNombre().toLowerCase();
            int rep = 0;
            if (nombre_ropa.length() >= largo_bus_nombre_puro) {
                for (int j = 0; j < nombre_ropa.length() - largo_bus_nombre_puro + 1; j++) {
                    sub_nombre_ropa = nombre_ropa.substring(0 + j, largo_bus_nombre_puro + j);

                    if (sub_nombre_ropa.equals(bus_nombre_puro)) {
                        if (coincidencia == 0) {
                            System.out.println("> COINCIDENCIAS PARA: " + bus_nombre_puro.toUpperCase());
                            reporte_titulo();
                            System.out.println(Utilitarios.repite_cadena("=", 150));
                            coincidencia++;
                        }
                        if (rep == 0) {
                            reporte_individual(i);
                            System.out.println(Utilitarios.repite_cadena("- ", 75));
                            rep++;
                        }
                    }
                }
            }
        }
        if (largo_array == 0) {
            System.out.println("> EL ARRAY ESTA VACIO... ");
        } else if (coincidencia == 0) {
            System.out.println("> NO HAY COINCIDENCIA... ");
        }
        System.out.println("\n");
    }
     
     public static void exportarPDF(){
        Document documento = new Document();
       
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Alumnos.pdf"));
            documento.open();
            
            PdfPTable tabla = new PdfPTable(5);
            tabla.addCell("Id Persona");
            tabla.addCell("Nombre(s)");
            tabla.addCell("Apellido(s)");
            tabla.addCell("DNI");
            tabla.addCell("Curso");
            
            try {
                Connection cn = DriverManager.getConnection("jdbc:sqlite:BDColegio2.db");
                PreparedStatement pst = cn.prepareStatement("select * from alumno");
                
                ResultSet rs = pst.executeQuery();
                
                if(rs.next()){
                                       
                    do {                        
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                    } while (rs.next());
                    documento.add(tabla);                    
                }
                
            } catch (DocumentException | SQLException e) {
            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Tabla Alumno exportada exitosamente.");
        } catch (DocumentException | HeadlessException | FileNotFoundException e) {
        }
    }
    public static void menu(){
        System.out.println("""
                           ***ALUMNOS***
                           1. AGREGAR
                           2. ELIMINAR
                           3. EDITAR
                           4. LISTAR
                           5. BUSCARID
                           6. BUSCARNOMBRE
                           7. Exportar Reporte en PDF
                           8. VOLVER
                           """);
        System.out.print("Escoja opción[1-7]: ");
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
                case 5->  buscarid();
                case 6-> busquedaNombre();
                case 7-> exportarPDF();
                case 8-> Utilitarios.volver();
                default-> Utilitarios.error();
            }
        }while(opcion!=8);
    }    

}
