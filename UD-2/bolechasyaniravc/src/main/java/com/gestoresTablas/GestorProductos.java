package com.gestoresTablas;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestorProductos {
    private Scanner scanner = new Scanner(System.in);

    // Gestionar productos
    public void gestionarProductos(Connection connDB) {
        String opcion = "";

        while (!opcion.equals("5")) {
            System.out.println("");
            System.out.println("==== Gestor de Productos ====");
            System.out.println("1. Añadir producto");
            System.out.println("2. Eliminar producto");
            System.out.println("3. Modificar producto");
            System.out.println("4. Mostrar todos los productos");
            System.out.println("5. Volver al menú de mantenimiento");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    // Añadir producto
                    anadirProducto(connDB);
                    break;
                case "2":
                    // Eliminar producto
                    eliminarProducto(connDB);
                    break;
                case "3":
                    // Modificar producto
                    modificarProducto(connDB);
                    break;
                case "4":
                    mostrarProductos(connDB);
                    break;
                case "5":
                    System.out.println("Saliendo del gestor de productos...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    // Añadir producto
    private void anadirProducto(Connection connDB) {
        System.out.println("Añadir producto seleccionado.");
        System.out.println("");
        System.out.println("Introduzca los datos del producto:");
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("¿Desea añadir una descripción? (SI/NO): ");
        String respuestaDesc = scanner.nextLine();

        // Si la respuesta es sí, pedir la descripción
        String descripcion = "NULL";
        if (respuestaDesc.equalsIgnoreCase("SI")) {
            System.out.print("Descripción: ");
            descripcion = scanner.nextLine();
        }

        System.out.print("Cantidad: ");
        String cantidad = scanner.nextLine();
        System.out.print("Precio: ");
        String precio = scanner.nextLine();
        System.out.print("¿Desea añadir un id de pedido? (SI/NO): ");
        String respuesta = scanner.nextLine();

        // Si la respuesta es sí, pedir el id del pedido
        String idPedido = "null";
        if (respuesta.equalsIgnoreCase("SI")) {
            System.out.print("Introduzca el ID del pedido: ");
            idPedido = scanner.nextLine();
        }

        // Insertar el producto en la base de datos
        try (Statement statement = connDB.createStatement()) {
            String sql = "INSERT INTO producto (id, nombre, descripcion, cantidad, precio, idPedido) VALUES ("
                    + id + ", '"
                    + nombre + "', "
                    + (descripcion.equals("NULL") ? "NULL" : "'" + descripcion + "'") + ", "
                    + cantidad + ", "
                    + precio + ", "
                    + (idPedido.equals("null") ? "NULL" : idPedido) + ")";

            statement.executeUpdate(sql);
            System.out.println("Producto añadido correctamente.");
        } catch (Exception e) {
            System.err.println("ERROR añadiendo el producto: " + e.getMessage());
        }
    }

    // Eliminar producto
    private void eliminarProducto(Connection connDB) {
        System.out.println("Eliminar producto seleccionado.");
        System.out.println("");
        System.out.print("Introduzca el ID del producto a eliminar: ");
        String id = scanner.nextLine();

        // Eliminar el producto de la base de datos
        try (Statement statement = connDB.createStatement()) {
            String sql = "DELETE FROM producto WHERE id = " + id;
            int rowsAffected = statement.executeUpdate(sql);
            if (rowsAffected > 0) {
                System.out.println("Producto eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún producto con el ID proporcionado.");
            }
        } catch (Exception e) {
            System.err.println("ERROR eliminando el producto: " + e.getMessage());
        }
    }

    // Modificar producto
    private void modificarProducto(Connection connDB) {
        System.out.println("Modificar producto seleccionado.");
        System.out.println("");
        System.out.print("Introduzca el ID del producto a modificar: ");
        String id = scanner.nextLine();

        // Pedir qué campo modificar
        String opcion = "";

        while (!opcion.equals("6")) {
            System.out.println("¿Qué campo desea modificar?");
            System.out.println("1. Nombre");
            System.out.println("2. Descripción");
            System.out.println("3. Cantidad");
            System.out.println("4. Precio");
            System.out.println("5. idPedido");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    // Modificar nombre
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    
                    try (Statement statement = connDB.createStatement()) {
                        String sql = "UPDATE producto SET nombre = '" + nuevoNombre + "' WHERE id = " + id;
                        statement.executeUpdate(sql);
                        System.out.println("Producto modificado correctamente.");
                    } catch (Exception e) {
                        System.err.println("ERROR modificando el producto: " + e.getMessage());
                    }
                    break;
                case "2":
                    // Modificar descripción
                    System.out.print("Nueva descripción: ");
                    String nuevaDescripcion = scanner.nextLine();
                    try (Statement statement = connDB.createStatement()) {
                        String sql = "UPDATE producto SET descripcion = '" + nuevaDescripcion + "' WHERE id = " + id;
                        statement.executeUpdate(sql);
                        System.out.println("Producto modificado correctamente.");
                    } catch (Exception e) {
                        System.err.println("ERROR modificando el producto: " + e.getMessage());
                    }
                    break;
                case "3":
                    // Modificar cantidad
                    System.out.print("Nueva cantidad: ");
                    String nuevaCantidad = scanner.nextLine();
                    try (Statement statement = connDB.createStatement()) {
                        String sql = "UPDATE producto SET cantidad = " + nuevaCantidad + " WHERE id = " + id;
                        statement.executeUpdate(sql);
                        System.out.println("Producto modificado correctamente.");
                    } catch (Exception e) {
                        System.err.println("ERROR modificando el producto: " + e.getMessage());
                    }
                    break;
                case "4":
                    // Modificar precio
                    System.out.print("Nuevo precio: ");
                    String nuevoPrecio = scanner.nextLine();
                    try (Statement statement = connDB.createStatement()) {
                        String sql = "UPDATE producto SET precio = " + nuevoPrecio + " WHERE id = " + id;
                        statement.executeUpdate(sql);
                        System.out.println("Producto modificado correctamente.");
                    } catch (Exception e) {
                        System.err.println("ERROR modificando el producto: " + e.getMessage());
                    }
                    break;
                case "5":
                    // Modificar idPedido
                    System.out.print("Nuevo idPedido: ");
                    String nuevoIdPedido = scanner.nextLine();
                    try (Statement statement = connDB.createStatement()) {
                        String sql = "UPDATE producto SET idPedido = " + nuevoIdPedido + " WHERE id = " + id;
                        statement.executeUpdate(sql);
                        System.out.println("Producto modificado correctamente.");
                    } catch (Exception e) {
                        System.err.println("ERROR modificando el producto: " + e.getMessage());
                    }
                    break;
                case "6":
                    // Salir
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    private void mostrarProductos(Connection connDB) {
        System.out.println("Consultar información de todos los Productos");
        System.out.println("");
        System.out.println("Productos presentes en la BD:");
        
        try (Statement stmt = connDB.createStatement()){
            String sql = "SELECT * FROM producto";

            ResultSet resultSet = stmt.executeQuery(sql);

            while(resultSet.next()) {
                String id = resultSet.getString("id");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                String cantidad = resultSet.getString("cantidad");
                String precio = resultSet.getString("precio");
                String idPedido = resultSet.getString("idPedido");
                System.out.println("ID Producto: " + id + ", Nombre: " + nombre + 
                        ", Descripción: " + descripcion + ", Cantidad: " + cantidad +
                        ", Precio: " + precio +  "ID Pedido: " + idPedido);
            }

        } catch (SQLException e) {
            System.out.println("Error en la lectura de los Productos: " + e.getMessage());
        }
    }
}
