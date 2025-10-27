package com.principales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.gestoresTablas.GestorPedidos;
import com.gestoresTablas.GestorProductos;
import com.gestoresTablas.GestorClientes;

public class Principal {
    // Cambiar datos según BD y usuario
    public static String DB_NAME = "bolechasYaniraVC";
    public static String DB_USER = "root";
    public static String DB_PASSWORD = "root";

    // Variables globales
    public static Scanner scanner = new Scanner(System.in);
    public static GestorBolechas gestor = new GestorBolechas();;

    // Gestores de tablas
    public static GestorPedidos gestorPedidos;
    public static GestorClientes gestorClientes;
    public static GestorProductos gestorProductos;

    // Conexión a la BD
    private static Connection connDB;

    public static void main(String[] args) {
        String opcion = "";

        while (!opcion.equals("6")) {
            System.out.println("==== Menú ====");
            System.out.println("1. Mantenimiento de Bolechas");
            System.out.println("2. Consultar información de un Cliente");
            System.out.println("3. Consultar pedidos realizados por un cliente");
            System.out.println("4. Realizar un pedido");
            System.out.println("5. Exportar pedido a un JSON");
            System.out.println("6. Salir");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    // Mantenimiento de Bolechas
                    menuMantenimiento();
                    break;
                case "2":
                    // Consultar información de un Cliente
                    break;
                case "3":
                    // Consultar pedidos realizados por un cliente
                    break;
                case "4":
                    // Realizar un pedido
                    break;
                case "5":
                    // Exportar pedido a un JSON
                    break;
                case "6":
                    // Salir
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    // Opción incorrecta
                    System.out.println("La opción introducida es incorrecta. Vuelva a intentarlo.");
                    System.out.print("Seleccione una opción: ");
                    opcion = scanner.nextLine();
                    break;
            }
        }

        System.out.println("Programa finalizado.");
    }

    public static void menuMantenimiento() {
        // Crear BD
        String subOpcion = "";

        while (!subOpcion.equals("4")) {
            System.out.println("");
            System.out.println("==== Mantenimiento ====");
            System.out.println("1. Crear Base de Datos");
            System.out.println("2. Gestionar clientes");
            System.out.println("3. Gestionar productos");
            System.out.println("4. Volver al menú principal");

            System.out.print("Seleccione una opción: ");
            subOpcion = scanner.nextLine();

            switch (subOpcion) {
                case "1":
                    System.out.println("");
                    // Crear BD (si no existía)
                    gestor.create(DB_NAME);

                    System.out.println("> Conectandose a la base de datos...");

                    try {
                        connDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                                + DB_NAME,
                                DB_USER,
                                DB_PASSWORD);
                        System.out.println("Conexión exitosa a la base de datos '" + DB_NAME + "'.");
                    } catch (SQLException e) {
                        System.err.println("ERROR conectando a la BD: " + e.getMessage());
                    }

                    // Crear tablas
                    System.out.print("Desea crear las tablas? (SI/NO): ");
                    String respuesta = scanner.nextLine();
                    if (respuesta.equalsIgnoreCase("SI")) {
                        gestor.createTables(connDB);

                        // Permitimos gestionar pedidos, clientes y productos
                        gestorPedidos = new GestorPedidos();
                        gestorClientes = new GestorClientes();
                        gestorProductos = new GestorProductos();

                    } else {
                        System.out.println("No se han creado las tablas.");
                    }
                    break;
                case "2":
                    // Gestionar clientes
                    
                    break;
                case "3":
                    // Gestionar productos
                    break;
                case "4":
                    // Volver al menú principal
                    System.out.println("Volviendo al menú principal...");
                    System.out.println("");
                    break;
                default:
                    System.out.println("La opción introducida es incorrecta. Vuelva a intentarlo.");
                    System.out.print("Seleccione una opción: ");
                    subOpcion = scanner.nextLine();
                    break;
            }
        }
    }
}