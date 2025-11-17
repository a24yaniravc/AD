package com.conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.principales.ConfigLoader;

public class MySQL_Connection implements DBConnection {
    // Cargar configuración
    String URL = ConfigLoader.get("mysql.url");
    String BD = ConfigLoader.get("mysql.bd");
    String USER = ConfigLoader.get("mysql.user");
    String PASSWORD = ConfigLoader.get("mysql.password");

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL + BD, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectando a MYSQL-DAM2: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Connection getConnectionServer() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectando a MYSQL-DAM2: " + e.getMessage());
            return null;
        }
    }
}
