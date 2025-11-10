package com.conexiones;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.principales.ConfigLoader;

import java.sql.Connection;

public class PostgreSQLConnection implements DBConnection {
    @Override
    public Connection getConnection() {
        String URL = ConfigLoader.get("postgresql.urlInicial");
        String USER = ConfigLoader.get("postgresql.user");
        String PASSWORD = ConfigLoader.get("postgresql.password");
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectando a POSTGRESQL: " + e.getMessage());
            return null;
        }
    }
}
