package com.gestores;

import java.sql.Statement;
import java.util.Scanner;

import com.conexiones.MySQL_Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    // Scanner y conexión
    private Scanner sc = new Scanner(System.in);
    private MySQL_Connection mysql_conn;

    // Constructor
    public GestorAlumnado(MySQL_Connection mysql_conn) {
        this.mysql_conn = mysql_conn;
    }

    // Crear tabla alumnos
    public void crearTablaAlumnos() {
        // Lógica para crear la tabla de alumnos
        String sql = "CREATE TABLE IF NOT EXISTS alumnos (id SERIAL PRIMARY KEY, nombre VARCHAR(100), edad INT, email VARCHAR(100))";
        try (Connection connection = mysql_conn.getConnection();
            Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabla de alumnos creada con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla de alumnos: " + e.getMessage());
        }
    }

    // Añadir alumno
    public void anhadirAlumno() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Edad: ");
        String edad = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        
        String sql = "INSERT INTO alumnos(nombre, edad, email) VALUES (?, ?, ?)";
        try (Connection connection = mysql_conn.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, Integer.parseInt(edad));
            pstmt.setString(3, email);
            pstmt.executeUpdate();
            System.out.println("Alumno añadido con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al añadir alumno: " + e.getMessage());
        }
    }

    // Borrar alumno
    public void borrarAlumno(String id) {
        String sql = "DELETE FROM alumnos WHERE id = '"+id+"'";

        try (Connection connection = mysql_conn.getConnection();
            Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e){
            System.err.println("Error al eliminar alumno: " + e.getMessage());
        }
    }

    // Modificar alumno
    public void modificarAlumno(String id, String nuevo_dato, String opcion) {
        String sql = "";

        switch (opcion) {
            case "1":
                sql = "UPDATE alumnos SET edad = ? WHERE id = ?";
                break;
            case "2":
                sql = "UPDATE alumnos SET nombre = ? WHERE id = ?";
                break;
            case "3":
                sql = "UPDATE alumnos SET email = ? WHERE id = ?";
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        try (Connection connection = mysql_conn.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nuevo_dato);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al modificar alumno: " + e.getMessage());
        }
    }

    // Listar todos los alumnos
    public void listarAlumnado() {
        String sql = "SELECT * FROM alumnos";
        try (Connection connection = mysql_conn.getConnection();
            Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Edad: " + rs.getInt("edad"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("-----------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar a los alumnos: " + e.getMessage());
        }
    }

    public void eliminarTablaAlumnos() {
        // Lógica para eliminar la tabla de alumnos
        String sql = "DROP TABLE IF EXISTS alumnos";
        try (Connection connection = mysql_conn.getConnection();
            Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabla de alumnos eliminada con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar la tabla de alumnos: " + e.getMessage());
        }
    }
}
