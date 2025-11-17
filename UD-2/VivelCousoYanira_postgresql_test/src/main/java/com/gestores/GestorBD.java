package com.gestores;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.conexiones.PostgreSQL_Connection;
import com.principales.ConfigLoader;

public class GestorBD {
    private static PostgreSQL_Connection dbConnection;

    public GestorBD() {
        dbConnection = new PostgreSQL_Connection();
    }

    // Crear base de datos a través del Connection Inicial a PostgreSQL
    public void crearBD() {
        try (Connection conn = dbConnection.getConnectionServer();
                Statement stmt = conn.createStatement()) {

            String dbName = ConfigLoader.get("postgresql.bd");
            String User = ConfigLoader.get("postgresql.user");

            // Creamos base de datos (se non existe)
            String comprobar = "SELECT 1 FROM pg_database WHERE datname = '" + dbName + "'";
            ResultSet rs = stmt.executeQuery(comprobar);

            if (!rs.next()) {
                stmt.executeUpdate("CREATE DATABASE " + dbName + " WITH OWNER = " + User + " ENCODING 'UTF8' TEMPLATE template0");
                System.out.println("Base de datos creada con éxito.");
            }
        } catch (SQLException e) {
            System.err.println("Error al crear la base de datos: " + e.getMessage());
        }
    }
}
