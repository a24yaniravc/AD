package com.principales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorLibros {
    private Connection conn;

    // Constructor
    public GestorLibros(Connection conn) {
        this.conn = conn;
        try (Statement stmt = conn.createStatement()) {
            // Crear tabla libros si no existe
            String sqlCreate = "CREATE TABLE IF NOT EXISTS libros (" +
                    "ISBN VARCHAR(20) NOT NULL," +
                    "titulo VARCHAR(100) NOT NULL," +
                    "autor VARCHAR(100) NOT NULL," +
                    "anio_publicacion INT NOT NULL," +
                    "PRIMARY KEY (ISBN)" +
                    ")";
            stmt.executeUpdate(sqlCreate);
        } catch (SQLException e) {
            System.err.println("ERROR creando la tabla libros: " + e.getMessage());
        }
    }

    // Métodos para gestionar libros

    // Crear un nuevo libro
    public void addLibro(String isbn, String titulo, String autor, int anioPublicacion) {
        String sqlInsert = "INSERT INTO libros (ISBN, titulo, autor, anio_publicacion) VALUES (?, ?, ?, ?)";

        try (var pstmt = conn.prepareStatement(sqlInsert)) {
            pstmt.setString(1, isbn);
            pstmt.setString(2, titulo);
            pstmt.setString(3, autor);
            pstmt.setInt(4, anioPublicacion);
            pstmt.executeUpdate();
            System.out.println("Libro creado exitosamente.");
        } catch (SQLException e) {
            System.err.println("ERROR agregando libro: " + e.getMessage());
        }
    }

    // Consultas
    // Obtener todos los libros
    public void getLibros() {
        String sqlSelect = "SELECT * FROM libros";

        try (Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(sqlSelect)) {
            while (rs.next()) {
                System.out.println("ISBN: " + rs.getString("ISBN") +
                        ", Titulo: " + rs.getString("titulo") +
                        ", Autor: " + rs.getString("autor") +
                        ", Año de Publicación: " + rs.getInt("anio_publicacion"));
            }
        } catch (SQLException e) {
            System.err.println("ERROR obteniendo libros: " + e.getMessage());
        }
    }

    // Obtener libro de un autor/a específico
    public void getLibrosByAutor(String autor) {
        String sqlSelect = "SELECT * FROM libros WHERE autor = ?";
        try (PreparedStatement statement = conn.prepareStatement(sqlSelect)) {
            statement.setString(1, autor);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    System.out.println("ISBN: " + rs.getString("ISBN") +
                            ", Titulo: " + rs.getString("titulo") +
                            ", Autor: " + rs.getString("autor") +
                            ", Año de Publicación: " + rs.getInt("anio_publicacion"));
                }
            }
        } catch (SQLException e) {
            System.err.println("ERROR obteniendo libros por autor: " + e.getMessage());
        }
    }

    // Obtener libros posteriores a un año específico
    public void getLibrosByAnio(int anioPublicacion) {
        String sqlSelect = "SELECT * FROM libros WHERE anio_publicacion > ?";
        try (PreparedStatement statement = conn.prepareStatement(sqlSelect)) {
            statement.setInt(1, anioPublicacion);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    System.out.println("ISBN: " + rs.getString("ISBN") +
                            ", Titulo: " + rs.getString("titulo") +
                            ", Autor: " + rs.getString("autor") +
                            ", Año de Publicación: " + rs.getInt("anio_publicacion"));
                }
            }
        } catch (SQLException e) {
            System.err.println("ERROR obteniendo libros por año: " + e.getMessage());
        }
    }

    // Modificar un libro
    // Modificar titulo
    public void updateTitulo(String isbn, String nuevoTitulo) {
        String sqlUpdate = "UPDATE libros SET titulo = ? WHERE ISBN = ?";

        try (PreparedStatement statement = conn.prepareStatement(sqlUpdate)) {
            statement.setString(1, nuevoTitulo);
            statement.setString(2, isbn);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Título actualizado exitosamente.");
            } else {
                System.out.println("No se encontró el libro con el ISBN proporcionado.");
            }
        } catch (SQLException e) {
            System.err.println("ERROR actualizando título: " + e.getMessage());
        }
    }

    // Modificar autor/a
    public void updateAutor(String isbn, String nuevoAutor) {
        String sqlUpdate = "UPDATE libros SET autor = ? WHERE ISBN = ?";

        try (PreparedStatement statement = conn.prepareStatement(sqlUpdate)) {
            statement.setString(1, nuevoAutor);
            statement.setString(2, isbn);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Autor actualizado exitosamente.");
            } else {
                System.out.println("No se encontró el libro con el ISBN proporcionado.");
            }
        } catch (SQLException e) {
            System.err.println("ERROR actualizando autor: " + e.getMessage());
        }
    }

    // Modificar año de publicación
    public void updateAnioPublicacion(String isbn, int nuevoAnio) {
        String sqlUpdate = "UPDATE libros SET anio_publicacion = ? WHERE ISBN = ?";
        try (PreparedStatement statement = conn.prepareStatement(sqlUpdate)) {
            statement.setInt(1, nuevoAnio);
            statement.setString(2, isbn);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Año de publicación actualizado exitosamente.");
            } else {
                System.out.println("No se encontró el libro con el ISBN proporcionado.");
            }
        } catch (SQLException e) {
            System.err.println("ERROR actualizando año de publicación: " + e.getMessage());
        }
    }

    // Eliminar un libro
    public void deleteLibro(String isbn) {
        String sqlDelete = "DELETE FROM libros WHERE ISBN = ?";
        try (PreparedStatement statement = conn.prepareStatement(sqlDelete)) {
            statement.setString(1, isbn);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Libro eliminado exitosamente.");
            } else {
                System.out.println("No se encontró el libro con el ISBN proporcionado.");
            }
        } catch (SQLException e) {
            System.err.println("ERROR eliminando libro: " + e.getMessage());
        }
    }

    // Eliminar libros anteriores a un año específico
    public void deleteLibrosByAnio(int anioPublicacion) {
        String sqlDelete = "DELETE FROM libros WHERE anio_publicacion < ?";
        try (PreparedStatement statement = conn.prepareStatement(sqlDelete)) {
            statement.setInt(1, anioPublicacion);
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " libros eliminados que eran anteriores al año " + anioPublicacion + ".");
        } catch (SQLException e) {
            System.err.println("ERROR eliminando libros por año: " + e.getMessage());
        }
    }

    // CleanLibros
    public void cleanLibros() {
        String sqlDeleteAll = "DELETE FROM libros";
        try (Statement statement = conn.createStatement()) {
            int rowsAffected = statement.executeUpdate(sqlDeleteAll);
            System.out.println("Se han eliminado " + rowsAffected + " libros de la base de datos.");
        } catch (SQLException e) {
            System.err.println("ERROR limpiando libros: " + e.getMessage());
        }
    }

    // Alterar la estructura de libros
    /*public void alterLibros(String nombreColumna, String tipo) {
        String sqlAlterLibros = "ALTER TABLE libros ADD COLUMN " + nombreColumna + " " + tipo;

        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate(sqlAlterLibros);
            System.out.println("Añadida la columna '" + nombreColumna + "' de tipo " + tipo + " a libros.");
        } catch (SQLException e) {
            System.out.println("ERROR alterando libors: " + e.getMessage());
        }

    }*/

    //
}