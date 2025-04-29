/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.empresa.DaoImpl;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.com.empresa.config.bd.ConectaBD;
import pe.com.empresa.dao.LibroDao;
import pe.com.empresa.modelo.Libro;

/**
 *
 * @author pepon
 */
public class LibroDaoImpl implements LibroDao{
    ConectaBD cn = new ConectaBD();
    Connection c = null;
    Statement stmt = null;
    ResultSet rs = null;
    @Override
    public List listar() {
        List<Libro> libros = new ArrayList<>();
        String sql = """
                        SELECT *
                        FROM Libro 
                        ORDER BY CODIGO
                     """;
        try {
            c = cn.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Libro libro = new Libro();
                libro.setIdlibro(rs.getInt("idlibro"));
                libro.setCodigo(rs.getString("codigo"));
                libro.setMombre(rs.getString("nombre"));
                libro.setAutor(rs.getString("autor"));
                libro.setPrecio(rs.getDouble("precio"));
                libros.add(libro);
            }
        } catch (SQLException e) {
            System.out.println("No se pudo listar");
        }
        return libros;
    }

    @Override
    public Libro buscar(int idlibro) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean agregar(Libro libro) {
        String sql = "  INSERT INTO LIBRO(CODIGO, NOMBRE, AUTOR, PRECIO) "
                + "     VALUES( "
                + "         '" + libro.getCodigo()+ "', "
                + "         '" + libro.getMombre()+ "', "
                + "         '" + libro.getAutor()+ "', "
                + libro.getPrecio()
                + "     )";
        System.out.println("SQL: " + sql);
        try {
            c = cn.getConnection();

            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("<Se agregó un nuevo libro a la BD>");
        } catch (SQLException e) {
            System.out.println("No se pudo agregar un nuevo libro");
            return false;
        }
        return true;
    }

    @Override
    public boolean editar(Libro libro) {
        String sql = "  UPDATE LIBRO "
                + "     SET "
                + "         CODIGO = '" + libro.getCodigo()+ "', "
                + "         NOMBRE = '" + libro.getMombre()+ "', "
                + "         AUTOR = '" + libro.getAutor()+ "', "
                + "         PRECIO = " + libro.getPrecio()
                + "     WHERE "
                + "         IDLIBRO = " + libro.getIdlibro();
        System.out.println("SQL: " + sql);
        try {
            c = cn.getConnection();
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("<Se editó un nuevo libro a la BD>");
        } catch (SQLException e) {
            System.out.println("No se editar los datos del libro");
            return false;
        }
        return true;
    }

    @Override
    public boolean eliminar(int idlibro) {
         String sql = "  DELETE FROM LIBRO "
                + "     WHERE"
                + "         IDLIBRO = " + idlibro;
        System.out.println("SQL: " + sql);
        try {
            c = cn.getConnection();
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("<Se eliminó un libro >");
        } catch (SQLException e) {
            System.out.println("No se pudo eliminar el registro del libro");
            return false;
        }
        return true;
    }
    
}
