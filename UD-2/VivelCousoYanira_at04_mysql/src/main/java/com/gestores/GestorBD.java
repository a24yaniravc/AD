package com.gestores;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

import com.conexiones.MySQL_Connection;
import com.principales.ConfigLoader;

public class GestorBD {
    private static MySQL_Connection dbConnection;

    public GestorBD(MySQL_Connection dbConnection) {
        GestorBD.dbConnection = dbConnection;
    }

    // Crear base de datos a través del Connection Inicial a MySQL
    public void crearBD() {
        try (Connection conn = dbConnection.getConnectionServer();
                Statement stmt = conn.createStatement()) {

            String dbName = ConfigLoader.get("mysql.bd");
            
            String sql = "CREATE DATABASE IF NOT EXISTS " + dbName;
            stmt.executeUpdate(sql);
            System.out.println("Base de datos '" + dbName + "' creada o ya existente.");
            
        } catch (SQLException e) {
            System.err.println("Error al crear la base de datos: " + e.getMessage());
        }
    }
}
