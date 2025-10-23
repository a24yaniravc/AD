package com.conexiones;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.aplicacion.ConfigLoader;

import java.sql.Connection;

public class MySQLConnection implements DBConnection {
    /* Sin archivo aplicacion.properties */

    /*  'testdb' es el nombre de la base de datos a la que nos vamos a conectar
     *  private static final String URL ="jdbc:mysql://localhost:3306/testdb";
     *  private static final String USER = "usuario";
     *  private static final String PASSWORD = "usuario123";
    */

@Override
    public Connection getConnection() {
        /* Con archivo aplicacion.properties */
        String URL = ConfigLoader.get("mysql.url");
        String USER = ConfigLoader.get("mysql.user");
        String PASSWORD = ConfigLoader.get("mysql.password");
    
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectado a MYSQL: " + e.getMessage());
            return null;
        }
    }
}
