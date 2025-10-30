package com.gestoresTablas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class GestorClientes {
    private Scanner scanner = new Scanner(System.in);

    // Gestionar clientes
    public void gestionarClientes(Connection connDB) {
        String opcion = "";

        while (!opcion.equals("5")) {
            System.out.println("");
            System.out.println("==== Gestor de Clientes ====");
            System.out.println("1. Añadir cliente");
            System.out.println("2. Eliminar cliente");
            System.out.println("3. Modificar cliente");
            System.out.println("4. Mostrar todos los clientes");
            System.out.println("5. Volver al menú de mantenimiento");

            System.out.print("Opción: ");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    // Añadir cliente
                    anadirCliente(connDB);
                    break;
                case "2":
                    // Eliminar cliente
                    eliminarCliente(connDB);
                    break;
                case "3":
                    // Lógica para modificar cliente
                    modificarCliente(connDB);
                    break;
                case "4":
                    mostrarClientes(connDB);
                    break;
                case "5":
                    // Volver al menú principal
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
        System.out.println("Saliendo del Gestor de Clientes...");
    }

    // Añadir cliente
    private void anadirCliente(Connection connDB) {
        System.out.println("Añadir cliente seleccionado.");
        System.out.println("");
        System.out.println("Introduzca los datos del cliente:");
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("Creando cliente en la base de datos...");

        try (Statement statement = connDB.createStatement()) {
            String sql = "INSERT INTO cliente (dni, nombre) VALUES ('" + dni + "', '" + nombre + "')";
            statement.executeUpdate(sql);
            System.out.println("Cliente añadido correctamente.");
        } catch (Exception e) {
            System.err.println("ERROR añadiendo el cliente: " + e.getMessage());
        }
    }

    // Eliminar cliente
    private void eliminarCliente(Connection connDB) {
        System.out.println("Eliminar cliente seleccionado.");
        System.out.println("");
        System.out.print("Introduzca el DNI del cliente a eliminar: ");
        String dni = scanner.nextLine();

        System.out.println("Eliminando cliente de la base de datos...");

        try (Statement statement = connDB.createStatement()) {
            String sql = "DELETE FROM cliente WHERE dni = '" + dni + "'";
            statement.executeUpdate(sql);
            System.out.println("Cliente eliminado correctamente.");
        } catch (Exception e) {
            System.err.println("ERROR eliminando el cliente: " + e.getMessage());
        }
    }

    // Modificar cliente
    private void modificarCliente(Connection connDB) {
        System.out.println("Modificar cliente seleccionado.");
        System.out.println("");
        System.out.print("Introduzca el DNI del cliente a modificar: ");
        String dni = scanner.nextLine();

        String opcion = "";

        // Menú de modificación
        while (!opcion.equals("3")) {
            System.out.println("");
            System.out.println("¿Que desea modificar?");
            System.out.println("1. Nombre");
            System.out.println("2. idPedido");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    // Modificar nombre
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();

                    try (Statement statement = connDB.createStatement()) {
                        String sql = "UPDATE cliente SET nombre = '" + nuevoNombre + "' WHERE dni = '" + dni + "'";
                        statement.executeUpdate(sql);
                        System.out.println("Nombre modificado correctamente.");
                    } catch (Exception e) {
                        System.err.println("ERROR modificando el nombre: " + e.getMessage());
                    }
                    break;
                case "2":
                    // Modificar idPedido
                    System.out.print("Nuevo idPedido: ");
                    String nuevoIdPedido = scanner.nextLine();

                    try (Statement statement = connDB.createStatement()) {
                        String sql = "UPDATE cliente SET idPedido = '" + nuevoIdPedido + "' WHERE dni = '" + dni + "'";
                        statement.executeUpdate(sql);
                        System.out.println("idPedido modificado correctamente.");
                    } catch (Exception e) {
                        System.err.println("ERROR modificando el idPedido: " + e.getMessage());
                    }
                    break;
                case "3":
                    // Salir
                    System.out.println("Saliendo del menú de modificación...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    // Consultar información de un Cliente
    public void consultarInfoCliente(Connection connDB) {
        System.out.println("Consultar información de un Cliente seleccionado.");
        System.out.println("");
        System.out.print("Introduzca el DNI del cliente a consultar: ");
        String dni = scanner.nextLine();

        System.out.println("Consultando información del cliente en la base de datos...");

        try (Statement statement = connDB.createStatement()) {
            String sql = "SELECT * FROM cliente WHERE dni = '" + dni + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");

                System.out.println("Información del Cliente:");
                System.out.println("DNI: " + dni);
                System.out.println("Nombre: " + nombre);
            } else {
                System.out.println("No se encontró ningún cliente con el DNI proporcionado.");
            }
        } catch (Exception e) {
            System.err.println("ERROR consultando el cliente: " + e.getMessage());
        }
    }

    private void mostrarClientes(Connection connDB) {
        System.out.println("Consultar información de todos los Clientes");
        System.out.println("");
        System.out.println("Clientes presentes en la BD:");
        
        try (Statement stmt = connDB.createStatement()){
            String sql = "SELECT * FROM cliente";

            ResultSet resultSet = stmt.executeQuery(sql);

            while(resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nombre = resultSet.getString("nombre");
                System.out.println("DNI: " + dni + ", Nombre: " + nombre);
            }

        } catch (SQLException e) {
            System.out.println("Error en la lectura de los Clientes: " + e.getMessage());
        }
    }
}