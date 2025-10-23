package com.conexiones;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;

public class MySQLConnection implements DBConnection {
    /* Sin archivo aplicacion.properties */
    private String URL = "";
    private static final String USER = "usuario";
    private static final String PASSWORD = "usuario123";

    public MySQLConnection () {
        this.URL = "jdbc:mysql://localhost:3306/";
    }
    
@Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectando a MYSQL: " + e.getMessage());
            return null;
        }
    }
}
