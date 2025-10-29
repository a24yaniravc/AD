package com.gestoresTablas;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestorPedidos {
    private Scanner sc = new Scanner(System.in);

    // Realizar un pedido
    public void realizarPedido(Connection connDB) {
        System.out.println("Realizar un nuevo pedido.");
        System.out.println("");

        System.out.println("Introduzca el DNI del cliente: ");
        String idCliente = sc.nextLine();

        System.out.println("Introduzca el ID del producto: ");
        String idProducto = sc.nextLine();

        System.out.println("Introduzca la cantidad del producto: ");
        String cantidad = sc.nextLine();

        try {
            Statement statement = connDB.createStatement();
            String insertPEDIDO = "INSERT INTO Pedidos (fecha, idProducto, idCliente) VALUES (NOW(), '"
                    + idProducto + "', '" + idCliente + "')";

            // Ejecutar la inserción y obtener el ID generado
            statement.executeUpdate(insertPEDIDO, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            int idPedido = -1;
            if (generatedKeys.next()) {
                idPedido = generatedKeys.getInt(1);
            }

            String insertProducto = "UPDATE Producto SET cantidad = cantidad - " + cantidad + ", idPedido = "
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
            Statement statement = connDB.createStatement();
            String queryPEDIDO = "SELECT * FROM Pedidos WHERE idCliente = '" + idCliente + "'";
            ResultSet resultSetPEDIDO = statement.executeQuery(queryPEDIDO);

            String queryProducto = "SELECT precio FROM Producto WHERE idPedido IN (SELECT id FROM Pedidos WHERE idCliente = '"
                    + idCliente + "')";
            ResultSet resultSetProducto = statement.executeQuery(queryProducto);
            System.out.println("Pedidos realizados por el cliente con ID " + idCliente + ":");
            while (resultSetPEDIDO.next()) {
                String idPedido = resultSetPEDIDO.getString("idPedido");
                String fecha = resultSetPEDIDO.getString("fecha");
                String total = resultSetPEDIDO.getString("cantidad");
                total = Integer.parseInt(total) * resultSetProducto.getFloat("precio") + "€";
                System.out.println("ID Pedido: " + idPedido + ", Fecha: " + fecha + ", Total: " + total);
            }
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

        try {
            Statement statement = connDB.createStatement();
            String query = "SELECT p.id, p.fecha, p.idProducto, p.idCliente, pr.nombre, pr.descripción, pr.cantidad, pr.precio "
                    + "FROM Pedidos p JOIN Producto pr ON p.idProducto = pr.id "
                    + "WHERE p.id = '" + idPedido + "'";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                String json = "{\n" +
                        "  \"idPedido\": \"" + resultSet.getString("id") + "\",\n" +
                        "  \"fecha\": \"" + resultSet.getString("fecha") + "\",\n" +
                        "  \"clienteID\": \"" + resultSet.getString("idCliente") + "\",\n" +
                        "  \"producto\": {\n" +
                        "    \"idProducto\": \"" + resultSet.getString("idProducto") + "\",\n" +
                        "    \"nombre\": \"" + resultSet.getString("nombre") + "\",\n" +
                        "    \"descripción\": \"" + resultSet.getString("descripción") + "\",\n" +
                        "    \"cantidad\": \"" + resultSet.getString("cantidad") + "\",\n" +
                        "    \"precio\": \"" + resultSet.getString("precio") + "\"\n" +
                        "  }\n" +
                        "}";

                System.out.println("Pedido exportado a JSON:");
                System.out.println(json);
            } else {
                System.out.println("No se encontró ningún pedido con el ID proporcionado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al exportar el pedido a JSON: " + e.getMessage());
        }
    }
}
