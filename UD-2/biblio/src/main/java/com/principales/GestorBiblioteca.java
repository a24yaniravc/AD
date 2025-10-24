package com.principales;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.conexiones.MySQLConnection;

public class GestorBiblioteca {
    private final boolean createAnyways;

    // Constructor
    public GestorBiblioteca(boolean createAnyways) {
        this.createAnyways = createAnyways;
    }

    // Crear o Reemplazar BD
    public void create(String DB) {
        MySQLConnection mysqlConnection = new MySQLConnection();
        try (Connection connection = mysqlConnection.getConnection();
                Statement statement = connection.createStatement()) {

            if (createAnyways) {
                statement.executeUpdate("DROP DATABASE IF EXISTS " + DB);
                System.out.println("> Base de datos borrada");

                statement.executeUpdate("CREATE DATABASE " + DB);
                System.out.println("> Base de datos creada");
            } else {
                statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB);
            }
        } catch (SQLException e) {
            System.err.println("ERROR creando la BD: " + e.getMessage());
        }
    }

    // Destruir BD
    public void destroy(String DB) {
        MySQLConnection mysqlConnection = new MySQLConnection();
        try (Connection connection = mysqlConnection.getConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP DATABASE IF EXISTS " + DB);

        } catch (SQLException e) {
            System.err.println("ERROR creando la BD: " + e.getMessage());
        }
    }
}