package conexiones;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
*   Clase MySQLConnection
*   Establece la conexión con la BD
*/
public class MySQLConnection implements DBConnection {
    private String URLserver = "jdbc:mysql://localhost:3306/";
    private String URL = "jdbc:mysql://localhost:3306/dam2";
    private String USER = "root";
    private String PASSWORD = "root";

    /*  
    *   Método getConnection
    *   Establece la conexión con la BD
    */
    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectando a Mysql coa Base de datos: dam2 " + e.getMessage());
            return null;
        }
    }

    /*
    *   Método getConnectionServer
    *   Establece la conexión con el servidor MySQL
    */
    @Override
    public Connection getConnectionServer() {

        try {
            return DriverManager.getConnection(URLserver, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectando a MySql: " + e.getMessage());
            return null;
        }
    }
}
