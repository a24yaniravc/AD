package com.conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDBConnection implements DBConnection {
    /* Sin archivo aplicacion.properties */
    private String URL = "";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public MariaDBConnection () {
        this.URL = "jdbc:mariadb://localhost:3307/";
    }
    
@Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectando a MARIADB: " + e.getMessage());
            return null;
        }
    }
}
