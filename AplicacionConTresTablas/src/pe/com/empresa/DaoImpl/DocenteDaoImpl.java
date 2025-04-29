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
import pe.com.empresa.dao.DocenteDao;
import pe.com.empresa.modelo.Docente;

/**
 *
 * @author pepon
 */
public class DocenteDaoImpl implements DocenteDao{
    ConectaBD cn = new ConectaBD();
    Connection c = null;
    Statement stmt = null;
    ResultSet rs = null;
    @Override
    public List listar() {
        List<Docente> docentes = new ArrayList<>();
        String sql = """
                        SELECT *
                        FROM DOCENTE 
                        ORDER BY NOMBRE
                     """;
        try {
            c = cn.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Docente docente = new Docente();
                docente.setIddocente(rs.getInt("iddocente"));
                docente.setNombre(rs.getString("nombre"));
                docente.setDni(rs.getString("dni"));
                docente.setArea(rs.getString("area"));
                docente.setSalario(rs.getDouble("salario"));
                docentes.add(docente);
            }
        } catch (SQLException e) {
            System.out.println("No se pudo listar");
        }
        return docentes;
    }

    @Override
    public Docente buscar(int iddocente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean agregar(Docente docente) {
        String sql = "  INSERT INTO DOCENTE(NOMBRE, DNI, AREA, SALARIO) "
                + "     VALUES( "
                + "         '" + docente.getNombre()+ "', "
                + "         '" + docente.getDni()+ "', "
                + "         '" + docente.getArea()+ "', "
                + docente.getSalario()
                + "     )";
        System.out.println("SQL: " + sql);
        try {
            c = cn.getConnection();

            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("<Se agregó un docente a la BD>");
        } catch (SQLException e) {
            System.out.println("No se pudo agregar un nuevo docente");
            return false;
        }
        return true;
    }

    @Override
    public boolean editar(Docente docente) {
        String sql = "  UPDATE DOCENTE "
                + "     SET "
                + "         NOMBRE = '" + docente.getNombre() + "', "
                + "         DNI = '" + docente.getDni()+ "', "
                + "         AREA = '" + docente.getArea()+ "', "
                + "         SALARIO = " + docente.getSalario()
                + "     WHERE "
                + "         IDDOCENTE = " + docente.getIddocente();
        System.out.println("SQL: " + sql);
        try {
            c = cn.getConnection();
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("<Se editó uno nuevo docente a la BD>");
        } catch (SQLException e) {
            System.out.println("No se editar los datos del docente");
            return false;
        }
        return true;
    }

    @Override
    public boolean eliminar(int iddocente) {
         String sql = "  DELETE FROM DOCENTE "
                + "     WHERE"
                + "         IDDOCENTE = " + iddocente;
        System.out.println("SQL: " + sql);
        try {
            c = cn.getConnection();
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("<Se eliminó un docente >");
        } catch (SQLException e) {
            System.out.println("No se pudo eliminar el registro del docente");
            return false;
        }
        return true;
    }

 }
    

