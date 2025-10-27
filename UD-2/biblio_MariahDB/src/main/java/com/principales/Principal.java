package com.principales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Principal {
    // Cambiar datos según BD y usuario
    public static String DB_NAME = "biblioYaniraV";
    public static String DB_USER = "usuario";
    public static String DB_PASSWORD = "usuario123";
    public static String DB_URL = "jdbc:mariadb://localhost:3307/";

    // Variables globales
    public static Boolean salir = false;
    public static Scanner sc = new Scanner(System.in);

    public static void gestorVisualBD() {
        System.out.println("Opciones:");
        System.out.println("1. Reemplazar BD con la original");
        System.out.println("2. Eliminar BD actual");
        System.out.print("Respuesta: ");
        String respuesta = sc.nextLine();

        while (!respuesta.equals("1") && !respuesta.equals("2")) {
            System.out.println("La opción introducida es incorrecta. Vuelva a intentarlo.");
            System.out.print("Respuesta: ");
            respuesta = sc.nextLine();
        }

        switch (respuesta) {
            case "1":
                new GestorBiblioteca(true).create(DB_NAME);
                break;
            case "2":
                System.out.println(
                        "Está seguro? Se eliminará toda la BD " + DB_NAME + ". Además, se cerrará la aplicación.");
                System.out.print("Elección (Si/No): ");
                respuesta = sc.nextLine().toUpperCase();

                while (!respuesta.equals("SI") && !respuesta.equals("NO")) {
                    System.out.println("La opción es inválida. Vuelva a intentarlo.");
                    System.out.print("Elección (Si/No): ");
                    respuesta = sc.nextLine().toUpperCase();
                }

                if (respuesta.equals("SI")) {
                    System.out.println("Eliminando base de datos...");
                    new GestorBiblioteca(true).destroy(DB_NAME);
                    salir = true;
                } else {
                    System.out.println("Se ha cancelado el proceso de borrado.");
                    break;
                }
            default:
                break;
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("==== Gestor de BD ====");
        System.out.println("Creando base de datos...");
        System.out.println("Si existe, desea reemplazar la BD anterior? (true/false)");
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
        Connection conn = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER,
                DB_PASSWORD);

        System.out.println("");
        System.out.println("=== Gestor de Libros ===");
        GestorLibros gestorLibros = new GestorLibros(conn);
        System.out.println("Desea agregar libros de ejemplo? (Si/No)");
        System.out.print("Respuesta: ");
        respuesta = sc.nextLine().toUpperCase();

        while (!respuesta.equals("SI") && !respuesta.equals("NO")) {
            System.out.println("La respuesta es incorrecta. Vuelva a intentarlo.");
            System.out.print("Respuesta: ");
            respuesta = sc.nextLine().toUpperCase();
        }

        if (respuesta.equals("NO")) {
            System.out.println("No se agregarán libros de ejemplo.");
        } else {
            System.out.println("Agregando libros de ejemplo...");
            // Agregar libros
            gestorLibros.addLibro("978-3-16-148410-0", "El Quijote", "Miguel de Cervantes", 1605);
            gestorLibros.addLibro("978-0-14-044913-6", "Guerra y Paz", "León Tolstói", 1869);
            gestorLibros.addLibro("978-0-452-28423-4", "1984", "George Orwell", 1949);
            gestorLibros.addLibro("978-0-7432-7356-5", "El Código Da Vinci", "Dan Brown", 2003);
        }

        // Menú de operaciones
        while (salir != true) {
            System.out.println("");
            System.out.println("=== Menú de Operaciones ===");
            System.out.println("1. Agregar libro");
            System.out.println("2. Consultar todos los libros");
            System.out.println("3. Consultar libros por autor");
            System.out.println("4. Consultar libros por año");
            System.out.println("5. Modificar libro");
            System.out.println("6. Eliminar libro");
            System.out.println("7. Eliminar todos los libros");
            System.out.println("8. Gestor de Base de Datos");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            System.out.println("");

            switch (opcion) {
                case 1:
                    // Agregar libro
                    System.out.print("Ingrese ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Ingrese título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Ingrese autor: ");
                    String autor = sc.nextLine();
                    System.out.print("Ingrese año de publicación: ");
                    int anio = sc.nextInt();
                    gestorLibros.addLibro(isbn, titulo, autor, anio);
                    break;
                case 2:
                    // Consultar todos los libros
                    System.out.println("Lista de todos los libros:");
                    gestorLibros.getLibros();
                    break;
                case 3:
                    // Consultar libros por autor
                    System.out.print("Ingrese autor: ");
                    String autorConsulta = sc.nextLine();
                    gestorLibros.getLibrosByAutor(autorConsulta);
                    break;
                case 4:
                    // Consultar libros por año
                    System.out.print("Ingrese año de publicación: ");
                    int anioConsulta = sc.nextInt();
                    gestorLibros.getLibrosByAnio(anioConsulta);
                    break;
                case 5:
                    // Modificar libro
                    System.out.print("Ingrese ISBN del libro a modificar: ");
                    String isbnModificar = sc.nextLine();
                    System.out.print("Ingrese nuevo título (dejar vacío para no modificar): ");
                    String nuevoTitulo = sc.nextLine();
                    System.out.print("Ingrese nuevo autor (dejar vacío para no modificar): ");
                    String nuevoAutor = sc.nextLine();
                    System.out.print("Ingrese nuevo año de publicación (0 para no modificar): ");
                    int nuevoAnio = sc.nextInt();
                    System.out.println("Inserte cual de las siguientes opciones desea realizar:");
                    System.out.println("1. Modificar título");
                    System.out.println("2. Modificar autor");
                    System.out.println("3. Modificar año de publicación");

                    System.out.print("Opción: ");
                    int subOpcion = sc.nextInt();

                    while (subOpcion < 1 || subOpcion > 3) {
                        System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 3:");
                        System.out.print("Opción: ");
                        subOpcion = sc.nextInt();
                    }

                    // Realizar la modificación según la subopción
                    switch (subOpcion) {
                        case 1:
                            gestorLibros.updateTitulo(isbnModificar, nuevoTitulo);
                            break;
                        case 2:
                            gestorLibros.updateAutor(isbnModificar, nuevoAutor);
                            break;
                        case 3:
                            gestorLibros.updateAnioPublicacion(isbnModificar, nuevoAnio);
                            break;
                    }
                    break;
                case 6:
                    // Eliminar libro
                    System.out.print("Ingrese ISBN del libro a eliminar: ");
                    String isbnEliminar = sc.nextLine();
                    gestorLibros.deleteLibro(isbnEliminar);
                    break;
                case 7:
                    // Eliminar todos los libros
                    System.out.println("Estás seguro? Se eliminarán todos los libros de la base de datos.");
                    System.out.print("(Si/No): ");

                    String subrespuesta = sc.nextLine().toUpperCase();

                    while (!subrespuesta.equals("SI") && !subrespuesta.equals("NO")) {
                        System.out.println("La respuesta es incorrecta. Vuelva a intentarlo.");
                        System.out.print("(Si/No): ");
                        subrespuesta = sc.nextLine().toUpperCase();
                    }

                    if (subrespuesta.equals("SI")) {
                        gestorLibros.cleanLibros();
                        System.out.println("Borrando todos los libros..");
                    } else {
                        System.out.println("Se ha cancelado el proceso de borrado.");
                    }
                    break;
                case 8:
                    // Gestor de Base de Datos
                    System.out.println("Accediendo al Gestor de Base de Datos");
                    System.out.println("");
                    gestorVisualBD();
                    break;
                case 9:
                    // Salir
                    System.out.println("Saliendo de la aplicación..");
                    System.out.println("Adios :)");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        sc.close();
    }
}
