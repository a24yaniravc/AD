package com.principales;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.conexiones.MySQLConnection;

public class GestorBolechas {
    private Scanner scanner = new Scanner(System.in);

    // Crear BD
    public void create(String DB) {
        MySQLConnection mysqlConnection = new MySQLConnection();
        try (Connection connectionServer = mysqlConnection.getConnection();
                Statement statement = connectionServer.createStatement()) {

            System.out.println("Desea eliminar la base de datos si ya existe? (s/n)");
            String respuesta = scanner.nextLine();

            if (respuesta.equalsIgnoreCase("s")) {
                statement.executeUpdate("DROP DATABASE IF EXISTS " + DB);
            }
            statement.executeUpdate("CREATE DATABASE " + DB);

            System.out.println("La base de datos '" + DB + "' ha sido creada correctamente.");
            connectionServer.close(); // Cerramos la conexión con el servidor
        } catch (SQLException e) {
            System.err.println("ERROR creando la BD: " + e.getMessage());
        }
    }

    // Crear tablas
    public void createTables(Connection connDB) {
        try (Statement statement = connDB.createStatement()) {
            // Tabla cliente
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS cliente (dni char(9) NOT NULL," +
                    "nombre varchar(50) NOT NULL," + "PRIMARY KEY (dni))");
            System.out.println("> Tabla 'cliente' creada");

            // Tabla pedido con fk de cliente
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS pedido (id int AUTO_INCREMENT NOT NULL," +
                    "fecha datetime NOT NULL," + "idProducto int NOT NULL," +
                    "idCliente char(9)," + "PRIMARY KEY (id)," +
                    "CONSTRAINT fk_pedido_cliente FOREIGN KEY (idCliente) REFERENCES cliente(dni) " +
                    "on update cascade on delete set null)");
            System.out.println("> Tabla 'pedido' creada");

            // Tabla producto con fk de pedido
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS producto (id int NOT NULL," +
                    "nombre varchar(40) NOT NULL," + "descripcion varchar(100)," +
                    "cantidad int NOT NULL," + "precio float NOT NULL," +
                    "idPedido int," + "PRIMARY KEY (id)," +
                    "CONSTRAINT fk_pedido_producto FOREIGN KEY (idPedido) REFERENCES pedido(id) " +
                    "on update cascade on delete set null)");
            System.out.println("> Tabla 'producto' creada");

            System.out.println("");
            System.out.println("Todas las tablas han sido creadas correctamente.");
        } catch (SQLException e) {
            System.err.println("ERROR creando las tablas: " + e.getMessage());
        }
    }
}
