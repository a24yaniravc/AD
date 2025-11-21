package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import principales.ConfigLoader;

// Implementación de DBConnection para Oracle
public class MyOracle_Connection implements DBConnection {
    // Cargar configuración
    String URL = ConfigLoader.get("oracle.url");
    String USER = ConfigLoader.get("oracle.userNuevo");
    String PASSWORD = ConfigLoader.get("oracle.passwordBase");

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectando a ORACLE-DAM2: " + e.getMessage());
            return null;
        }
    }
}
