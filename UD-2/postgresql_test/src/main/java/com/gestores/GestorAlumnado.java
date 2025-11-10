package com.gestores;

import java.sql.Statement;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * id
 * nombre
 * edad
 * email
 * añadir alumno
 * borrar alumno
 * modificar
 * consultar todos los alumnos
 */



public class GestorAlumnado {
    private Scanner sc = new Scanner(System.in);
    private Connection connection;

    public GestorAlumnado(Connection conn) {
        this.connection = conn;
    }

    public void crearTablaAlumnos() {
        // Lógica para crear la tabla de alumnos
        String sql = "CREATE TABLE IF NOT EXISTS alumnos (id SERIAL PRIMARY KEY, nombre VARCHAR(100), edad INT, email VARCHAR(100))";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabla de alumnos creada con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla de alumnos: " + e.getMessage());
        }
    }

    public void anhadirAlumno() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Edad: ");
        String edad = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        
        String sql = "INSERT INTO alumnos(nombre, edad, email) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, Integer.parseInt(edad));
            pstmt.setString(3, email);
            pstmt.executeUpdate();
            System.out.println("Alumno añadido con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al añadir alumno: " + e.getMessage());
        }
    }

    public void borrarAlumno(String id) {
        String sql = "DELETE FROM alumnos WHERE id = '"+id+"'";

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e){
            System.err.println("Error al eliminar alumno: " + e.getMessage());
        }
    }

    public void modificarAlumno(String id) {
        String sql = "";
    }

    public void listarAlumnado() {
        String sql = "SELECT * FROM alumnos";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println("Error al mostrar a los alumnos: " + e.getMessage());
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
