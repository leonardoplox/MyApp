package pe.com.empresa.config.bd;
import java.sql.*;
/**
 *
 * @author Dell
 */
public class CrearTablas {
    public static void inicio(){
        ConectaBD cn = new ConectaBD();
        Connection c = null;
        Statement stmt = null;
        try {
            c = cn.getConnection();
            stmt = c.createStatement();
            String sql = """
                         CREATE TABLE ALUMNO(
                            IDALUMNO   INTEGER PRIMARY KEY AUTOINCREMENT, 
                            NOMBRE     TEXT    NOT NULL, 
                            APELLIDO   TEXT    NOT NULL,
                            DNI        CHAR(8) NOT NULL,
                            CURSO      TEXT    NOT NULL 
                         );
                         
                         CREATE TABLE lIBRO(
                            IDLIBRO   INTEGER PRIMARY KEY AUTOINCREMENT, 
                            CODIGO    TEXT    NOT NULL,
                            NOMBRE    TEXT    NOT NULL,
                            AUTOR     TEXT    NOT NULL,
                            PRECIO    REAL
                         );
                         
                         CREATE TABLE DOCENTE(
                            IDDOCENTE   INTEGER PRIMARY KEY AUTOINCREMENT,
                            NOMBRE      TEXT   NOT NULL,
                            DNI         TEXT   NOT NULL,
                            AREA        TEXT   NOT NULL,
                            SALARIO     REAL
                         )
                         """;           
            System.out.println("SQL: " + sql);
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("<Tabla Creada correctamente>");        
    }
    public static void main(String[] args) {
        inicio();
    }       
}

