package com.conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.principales.ConfigLoader;

public class PostgreSQL_DB implements DBConnection {
    @Override
    public Connection getConnection() {
        String DB = ConfigLoader.get("postgresql.bd");
        String URL = ConfigLoader.get("postgresql.url") + DB;
        String USER = ConfigLoader.get("postgresql.user");
        String PASSWORD = ConfigLoader.get("postgresql.password");

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectando a POSTGRESQL-DAM2: " + e.getMessage());
            return null;
        }
    }
}
