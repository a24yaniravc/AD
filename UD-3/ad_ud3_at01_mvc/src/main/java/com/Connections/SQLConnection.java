package com.Connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.Controller.ConfigLoader;

/*
 * Clase utilizada para la Connection a la BD
 */
public class SQLConnection implements DBConnection {
    // Variables necesarias para la conexión
    String URL = ConfigLoader.get("mysql.url");
    String BD = ConfigLoader.get("mysql.bd");
    String USER = ConfigLoader.get("mysql.user");
    String PASS = ConfigLoader.get("mysql.password");

    /*
     * Método utilizado para la conexión a la BD
     */
    @Override
    public Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL+BD, USER, PASS);
            System.out.println("Conexión a la BD: " + BD + " realizada con éxito.");
            return conn;
        } catch (SQLException e) {
            System.err.println("Error al establecer conexión con la el servidor SQL: " + e.getMessage());
            return null;
        }
    }

    /*
     * Método utilizado para la conexión al servidor
     */
    @Override
    public Connection getConnectionServer() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión al servidor realizada con éxito.");
            return conn;
        } catch (SQLException e) {
            System.err.println("Error al establecer conexión con la el servidor SQL: " + e.getMessage());
            return null;
        }
    }

}
