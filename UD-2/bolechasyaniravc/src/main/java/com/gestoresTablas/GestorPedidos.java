package com.gestoresTablas;

import java.sql.Statement;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GestorPedidos {
    private Scanner sc = new Scanner(System.in);

    // Realizar un pedido
    public void realizarPedido(Connection connDB) {
        System.out.println("Realizar un nuevo pedido.");
        System.out.println("");

        System.out.print("Introduzca el DNI del cliente: ");
        String idCliente = sc.nextLine();

        System.out.print("Introduzca el ID del producto: ");
        String idProducto = sc.nextLine();

        System.out.print("Introduzca la cantidad del producto: ");
        String cantidad = sc.nextLine();

        try {
            Statement statement = connDB.createStatement();
            String insertPEDIDO = "INSERT INTO pedido (fecha, idProducto, idCliente) VALUES (NOW(), '"
                    + idProducto + "', '" + idCliente + "')";

            // Ejecutar la inserción y obtener el ID generado
            statement.executeUpdate(insertPEDIDO, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            int idPedido = -1;
            if (generatedKeys.next()) {
                idPedido = generatedKeys.getInt(1);
            }

            String insertProducto = "UPDATE producto SET cantidad = cantidad - " + cantidad + ", idPedido = "
                    + idPedido + " WHERE id = '" + idProducto + "'";
            statement.executeUpdate(insertProducto);

            System.out.println("Pedido realizado correctamente con ID: " + idPedido);
        } catch (SQLException e) {
            System.out.println("Error al realizar el pedido: " + e.getMessage());
        }
    }

    // Consultar los pedidos realizados por un cliente
    public void consultarPedidosCliente(Connection connDB) {
        System.out.println("Consultar pedidos de un Cliente seleccionado.");
        System.out.println("");

        System.out.print("Introduzca el ID del cliente: ");
        String idCliente = sc.nextLine();

        try {
            Statement ps = connDB.createStatement();
            String query = "SELECT p.id AS pedidoId, p.fecha, pr.cantidad AS cantidad, pr.precio " +
                    "FROM pedido p JOIN producto pr ON p.idProducto = pr.id " +
                    "WHERE p.idCliente = '" + idCliente + "'";
            ResultSet resultSet = ps.executeQuery(query);

            System.out.println("Pedidos realizados por el cliente con ID " + idCliente + ":");

            while (resultSet.next()) {
                String idPedido = resultSet.getString("pedidoId");
                String fecha = resultSet.getString("fecha");
                int cantidadProducto = resultSet.getInt("cantidad");
                float precio = resultSet.getFloat("precio");
                String total = (cantidadProducto * precio) + "€";
                System.out.println("ID Pedido: " + idPedido + ", Fecha: " + fecha + ", Total: " + total);
            }

            resultSet.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar los pedidos del cliente: " + e.getMessage());
        }
    }

    // Exportar pedido a un JSON
    public void exportarPedidoJSON(Connection connDB) {
        System.out.println("Exportar pedido a un archivo JSON.");
        System.out.println("");

        System.out.print("Introduzca el ID del pedido a exportar: ");
        String idPedido = sc.nextLine();

        // Si no existe, crear carpeta "pedidos_json"
        File carpeta = new File("pedidos_json");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        try {
            // Consulta para obtener los datos del pedido
            Statement statement = connDB.createStatement();
            String query = "SELECT p.id, p.fecha, p.idProducto, p.idCliente, pr.nombre, pr.descripcion, pr.cantidad, pr.precio "
                    + "FROM pedido p JOIN producto pr ON p.idProducto = pr.id "
                    + "WHERE p.id = '" + idPedido + "'";
            ResultSet resultSet = statement.executeQuery(query);

            // Recoger datos del pedido
            if (resultSet.next()) {
                // Construir un objeto Map para que Gson lo serialice correctamente
                Map<String, Object> pedido = new HashMap<>();
                pedido.put("idPedido", resultSet.getInt("id"));
                pedido.put("fecha", resultSet.getString("fecha"));
                pedido.put("clienteID", resultSet.getString("idCliente"));

                Map<String, Object> producto = new HashMap<>();
                producto.put("idProducto", resultSet.getString("idProducto"));
                producto.put("nombre", resultSet.getString("nombre"));
                producto.put("descripcion", resultSet.getString("descripcion"));
                producto.put("cantidad", resultSet.getInt("cantidad"));
                producto.put("precio", resultSet.getFloat("precio"));

                pedido.put("producto", producto);

                // Creamos el JSON
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setPrettyPrinting();
                Gson gson = gsonBuilder.create();

                // Escribir el JSON en un archivo
                try (FileWriter writer = new FileWriter("pedidos_json/pedido_" 
                        + idPedido + ".json")) {
                    gson.toJson(pedido, writer);
                    System.out.println("Archivo JSON creado: pedido_" + idPedido + ".json");
                } catch (IOException e) {
                    System.out.println("Error al crear el archivo JSON: " + e.getMessage());
                }

                statement.close();
            } else {
                System.out.println("No se encontró ningún pedido con el ID proporcionado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al exportar el pedido a JSON: " + e.getMessage());
        }
    }
}
