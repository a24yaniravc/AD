package com.gestores;

import java.sql.Statement;

import java.sql.Connection;
import java.sql.SQLException;

public class GestorAlumnado {
    private Connection connection;

    public GestorAlumnado(Connection conn) {
        this.connection = conn;
    }

    public void crearTablaAlumnos() {
        // Lógica para crear la tabla de alumnos
        String sql = "CREATE TABLE IF NOT EXISTS alumnos (id SERIAL PRIMARY KEY, nombre VARCHAR(100), edad INT)";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabla de alumnos creada con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla de alumnos: " + e.getMessage());
        }
    }

    public void eliminarTablaAlumnos() {
        // Lógica para eliminar la tabla de alumnos
        String sql = "DROP TABLE IF EXISTS alumnos";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabla de alumnos eliminada con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar la tabla de alumnos: " + e.getMessage());
        }
    }
}
