package com.principales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.gestoresTablas.GestorPedidos;
import com.gestoresTablas.GestorProductos;
import com.gestoresTablas.GestorClientes;

public class Principal {
    // Cambiar datos según BD y usuario(CORRECCION: no poner los datos aquí)
    private static String DB_NAME = "bolechasYaniraVC";
    private static String DB_USER = "root";
    private static String DB_PASSWORD = "root";

    // Variables globales
    private static Scanner scanner = new Scanner(System.in);
    private static GestorBolechas gestor = new GestorBolechas();;

    // Gestores de tablas
    private static GestorPedidos gestorPedidos;
    private static GestorClientes gestorClientes;
    private static GestorProductos gestorProductos;

    // Prueba2

    // Conexión a la BD
    private static Connection connDB;

    public static void main(String[] args) {
        // Inicio del programa

        try {
            connDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                    + DB_NAME,
                    DB_USER,
                    DB_PASSWORD);

            gestorPedidos = new GestorPedidos();
            gestorClientes = new GestorClientes();
            gestorProductos = new GestorProductos();

            System.out.println("Conexión exitosa a la base de datos '" + DB_NAME + "'.");
        } catch (SQLException e) {
            System.err.println(
                    "ERROR conectando a la BD. La base de datos no existe o las credenciales son incorrectas.");
            System.err.println("Mensaje de error: " + e.getMessage());
        }

        String opcion = "";

        // Menú principal
        while (!opcion.equals("6")) {
            System.out.println("");
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
                    gestorClientes.consultarInfoCliente(connDB);
                    break;
                case "3":
                    // Consultar pedidos realizados por un cliente
                    gestorPedidos.consultarPedidosCliente(connDB);
                    break;
                case "4":
                    // Realizar un pedido
                    gestorPedidos.realizarPedido(connDB);
                    break;
                case "5":
                    // Exportar pedido a un JSON
                    gestorPedidos.exportarPedidoJSON(connDB);
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

        // Menú de mantenimiento
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
                    gestorClientes.gestionarClientes(connDB);
                    break;
                case "3":
                    // Gestionar productos
                    gestorProductos.gestionarProductos(connDB);
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