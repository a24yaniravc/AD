package com.principales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Principal {
    public static String DB_NAME = "biblioYaniraV";
    public static String DB_USER = "usuario";
    public static String DB_PASSWORD = "usuario123";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("==== Gestor de BD ====");
        System.out.println("Creando base de datos...");
        System.out.println("Si existe, desea borrar la BD anterior? (true/false)");
        System.out.print("Respuesta: ");
        String respuesta = sc.nextLine();

        // Opcion de sobreescribir o no
        while (!respuesta.equals("true") && !respuesta.equals("false")) {
            System.out.println("Entrada no valida. Introduzca 'true' o 'false'");
            System.out.print("Respuesta: ");
            respuesta = sc.nextLine();
        }

        if (respuesta.equals("true")) {
            GestorBiblioteca gestorBD = new GestorBiblioteca(true);
            gestorBD.create(DB_NAME);
        } else if (respuesta.equals("false")) {
            GestorBiblioteca gestorBD = new GestorBiblioteca(false);
            gestorBD.create(DB_NAME);
        }

        System.out.println("Proceso finalizado.");

        // Usamos GestorLibros
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DB_NAME, DB_USER,
                DB_PASSWORD);

        System.out.println("");
        System.out.println("=== Gestor de Libros ===");
        GestorLibros gestorLibros = new GestorLibros(conn);
        System.out.println("Agregando libros de ejemplo...");
        // Agregar libros
        gestorLibros.addLibro("978-3-16-148410-0", "El Quijote", "Miguel de Cervantes", 1605);
        gestorLibros.addLibro("978-0-14-044913-6", "Guerra y Paz", "León Tolstói", 1869);
        gestorLibros.addLibro("978-0-452-28423-4", "1984", "George Orwell", 1949);
        gestorLibros.addLibro("978-0-7432-7356-5", "El Código Da Vinci", "Dan Brown", 2003);
        System.out.println("");

        // Consultas
        // Consultar todos los libros
        System.out.println("Lista de todos los libros:");
        gestorLibros.getLibros();
        System.out.println("");

        // Consultar libros por autor
        String autorConsulta = "George Orwell";
        System.out.println("Libros del autor " + autorConsulta + ":");
        gestorLibros.getLibrosByAutor(autorConsulta);
        System.out.println("");

        // Consultar libros posteriores a año específico de publicación
        int anioConsulta = 1940;
        System.out.println("Libros publicados después del año " + anioConsulta + ":");
        gestorLibros.getLibrosByAnio(anioConsulta);
        System.out.println("");

        // Modificaciones
        // Modificar titulo libro
        String isbnModificar = "978-0-452-28423-4";
        String nuevoTitulo = "Mil novecientos ochenta y cuatro";
        System.out.println("Modificando titulo del libro con ISBN " + isbnModificar + ":");
        gestorLibros.updateTitulo(isbnModificar, nuevoTitulo);
        System.out.println("");

        // Verificar modificación
        System.out.println("Verificando modificación:");
        gestorLibros.getLibrosByAutor("George Orwell");
        System.out.println("");

        // Modificar autor/a libro
        String isbnModificarAutor = "978-0-14-044913-6";
        String nuevoAutor = "Lev Tolstói";
        System.out.println("Modificando autor del libro con ISBN " + isbnModificarAutor + ":");
        gestorLibros.updateAutor(isbnModificarAutor, nuevoAutor);
        System.out.println("");

        // Verificar modificación
        System.out.println("Verificando modificación:");
        gestorLibros.getLibrosByAutor(nuevoAutor);
        System.out.println("");

        // Modificar año de publicación
        String isbnModificarAnio = "978-3-16-148410-0";
        int nuevoAnio = 1615;
        System.out.println("Modificando año de publicación del libro con ISBN " + isbnModificarAnio + ":");
        gestorLibros.updateAnioPublicacion(isbnModificarAnio, nuevoAnio);
        System.out.println("");

        // Verificar modificación
        System.out.println("Verificando modificación:");
        gestorLibros.getLibrosByAnio(1600);
        System.out.println("");

        // Eliminar un libro
        String isbnEliminar = "978-3-16-148410-0";
        System.out.println("Eliminando libro con ISBN " + isbnEliminar + ":");
        gestorLibros.deleteLibro(isbnEliminar);
        System.out.println("");

        // Verificar eliminación
        System.out.println("Verificando eliminación:");
        gestorLibros.getLibros();
        System.out.println("");

        // Eliminar libros anteriores a un año específico
        int anioEliminar = 1950;
        System.out.println("Eliminando libros anteriores al año " + anioEliminar + ":");
        gestorLibros.deleteLibrosByAnio(anioEliminar);
        System.out.println("");

        // Verificar eliminación
        System.out.println("Verificando eliminación:");
        gestorLibros.getLibros();
        System.out.println("");

        // Limpiar todos los libros
        System.out.println("Limpiando todos los libros:");
        gestorLibros.cleanLibros();
        System.out.println("");

        // Select para verificar
        System.out.println("Verificando que no queden libros:");
        gestorLibros.getLibros();
        System.out.println("");
        
        sc.close();
    }
}
