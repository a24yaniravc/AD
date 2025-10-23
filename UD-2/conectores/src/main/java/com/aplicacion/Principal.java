package com.aplicacion;

import java.sql.*;

import com.conexiones.DBConnection;
import com.conexiones.MySQLConnection;

public class Principal {
    private static void testConnection(DBConnection dbConnection, String dbName) {
        System.out.println("Probando conexión con " + dbName + "...");

        try (Connection conn = dbConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Conexión establecida con " + dbName + "\n");
            } else {
                System.out.println("No se pudo establecer la conexión con " + dbName);
            }
        } catch(Exception e) {
            System.err.println("ERROR inesperado: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {
        testConnection(new MySQLConnection(), "MySQL:testdb");

        Connection connection = DriverManager.getConnection(ConfigLoader.get("mysql.url"), 
            ConfigLoader.get("mysql.user"), 
            ConfigLoader.get("mysql.password"));
        
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE test1");

        statement.execute("CREATE TABLE test1 " + "(id INT not NULL, " + 
                            "nombre VARCHAR(20), " + "PRIMARY KEY ( id ))");

        statement.executeUpdate("INSERT INTO test1 VALUES (100, 'Zara')");

        ResultSet rs = statement.executeQuery("SELECT * FROM test1");
        while(rs.next()){
            //Display values
            System.out.print("ID: " + rs.getInt("id"));
            System.out.println(", Name: " + rs.getString("nombre"));
         }
    }
}
