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
import pe.com.empresa.config.bd.ConectaBD;
import java.util.List;
import pe.com.empresa.dao.AlumnoDao;
import pe.com.empresa.modelo.Alumno;

/**
 *
 * @author pepon
 */
public class AlumnoDaoImpl implements AlumnoDao{
    ConectaBD cn = new ConectaBD();
    Connection c = null;
    Statement stmt = null;
    ResultSet rs = null;
    @Override
    public List listar() {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = """
                        SELECT *
                        FROM ALUMNO 
                        ORDER BY NOMBRE
                     """;
        try {
            c = cn.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdalumno(rs.getInt("idalumno"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setDni(rs.getString("dni"));
                alumno.setCurso(rs.getString("curso"));
                alumnos.add(alumno);
            }
        } catch (SQLException e) {
            System.out.println("No se pudo listar");
        }
        return alumnos;
    }

   @Override
    public Alumno buscar(int idalumno) {
        Alumno alumno = new Alumno();
        String sql = "  SELECT * "
                + "     FROM ALUMNO "
                + "     WHERE IDALUMNO = " + idalumno;
        System.out.println("SQL: " + sql);

        try {
            c = cn.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                alumno.setIdalumno(rs.getInt("idalumno"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setDni(rs.getString("dni"));
                alumno.setCurso(rs.getString("curso"));
            }

        } catch (SQLException e) {
            System.out.println("<No se pudo agregar una nueva alumno>");
        }
        return alumno;
    }

    @Override
    public boolean agregar(Alumno alumno) {
       String sql = "  INSERT INTO ALUMNO(NOMBRE, APELLIDO, DNI, CURSO) "
                + "     VALUES( "
                + "         '" + alumno.getNombre() + "', "
                + "         '" + alumno.getApellido() + "', "
                + "         '" + alumno.getDni() + "', "
                + "         '" +alumno.getCurso() +"'"
                + "     )";
        System.out.println("SQL: " + sql);
        try {
            c = cn.getConnection();
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("<Se agregó un nuevo alumno a la BD>");
        } catch (SQLException e) {
            System.out.println("No se pudo agregar un nuevo alumno");
            return false;
        }
        return true;
    }

    @Override
    public boolean editar(Alumno alumno) {
        String sql = "  UPDATE ALUMNO "
                + "     SET "
                + "         NOMBRE = '" + alumno.getNombre() + "', "
                + "         APELLIDO = '" + alumno.getApellido() + "', "
                + "         DNI = '" + alumno.getDni() + "', "
                + "         CURSO = '" + alumno.getCurso()+"' "
                + "     WHERE "
                + "         IDALUMNO = " + alumno.getIdalumno();
        System.out.println("SQL: " + sql);
        try {
            c = cn.getConnection();
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("<Se editó un nuevo alumno a la BD>");
        } catch (SQLException e) {
            System.out.println("No se editar los datos del alumno");
            return false;
        }
        return true;
    }

    @Override
    public boolean eliminar(int idalumno) {
        String sql = "  DELETE FROM ALUMNO "
                + "     WHERE"
                + "         IDALUMNO = " + idalumno;
        System.out.println("SQL: " + sql);
        try {
            c = cn.getConnection();
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("<Se eliminó un alumno de la BD >");
        } catch (SQLException e) {
            System.out.println("No se pudo eliminar el registro del alumno");
            return false;
        }
        return true;
    }
  

}
    

