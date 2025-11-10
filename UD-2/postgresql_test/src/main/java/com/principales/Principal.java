package com.principales;

import java.sql.Connection;

import com.conexiones.PostgreSQLConnection;
import com.conexiones.PostgreSQL_DB;
import com.gestores.GestorAlumnado;
import com.gestores.GestorBD;

public class Principal {
    private static PostgreSQLConnection postgres;
    private static PostgreSQL_DB DB;
    private static Connection conn;
    private static GestorBD gestorBD;
    private static GestorAlumnado gestorAlumnado;

    public static void main(String[] args) {
        // Probamos la conexión a PostgreSQL
        postgres = new PostgreSQLConnection();

        if (postgres.getConnection() != null) {
            System.out.println("Conexión a PostgreSQL realizada con éxito.");
        } else {
            System.out.println("Fallo en la conexión a PostgreSQL.");
        }

        // Creamos la DB dam2
        gestorBD = new GestorBD();
        gestorBD.crearBD();

        // Usamos la DB dam2
        DB = new PostgreSQL_DB();
        conn = DB.getConnection();

        // Creamos la tabla de alumnos
        gestorAlumnado = new GestorAlumnado(conn);
        gestorAlumnado.crearTablaAlumnos();

        // Eliminar tabla de alumnos
        // gestorAlumnado.eliminarTablaAlumnos();
    }
}
