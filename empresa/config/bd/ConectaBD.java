package pe.com.empresa.config.bd;
import java.sql.*;
/*
* @author Daniel Lévano  * 
 */
public class ConectaBD {
    private static Connection c = null;
    private static final String BD = "BDColegio2.db";
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:";
    public Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            c = DriverManager.getConnection(URL + BD);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("<Base de Datos " + BD + " OK>");
        return c;
    }
}
