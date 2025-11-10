package com.principales;

import java.sql.Connection;
import java.util.Scanner;

import com.conexiones.PostgreSQLConnection;
import com.conexiones.PostgreSQL_DB;
import com.gestores.GestorAlumnado;
import com.gestores.GestorBD;

public class Principal {
    private static Scanner sc = new Scanner(System.in);

    // Variables
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

        // Menú de opciones
        String resp = "";
        String r = "";

        while (!resp.equals("5")) {
            System.out.println("");
            System.out.println("===== Menú =====");
            System.out.println("1. Añadir alumno");
            System.out.println("2. Eliminar alumno");
            System.out.println("3. Modificar alumno");
            System.out.println("4. Listar todos los alumnos");
            System.out.println("5. Salir");
            System.out.print("Selección: ");
            resp = sc.nextLine();
            System.out.println("");

            switch (resp) {
                case "1":
                    System.out.println("> Añadir alumno seleccionado");
                    gestorAlumnado.anhadirAlumno();
                    break;
                case "2":
                    System.out.println("> Eliminar alumno seleccionado");
                    System.out.print("Escriba la id del alumno a eliminar: ");
                    r = sc.nextLine();
                    gestorAlumnado.borrarAlumno(r);
                    break;
                case "3":
                    System.out.println("> Modificar alumno seleccionado");
                    System.out.print("Escriba la id del alumno a modificar: ");
                    r = sc.nextLine();
                    gestorAlumnado.modificarAlumno(r);
                    break;
                case "4":
                    System.out.println("> Listar todos los alumnos seleccionado");
                    gestorAlumnado.listarAlumnado();
                default:
                    System.out.println("Saliendo...");
                    break;
            }
        }

        // Eliminar tabla de alumnos
        // gestorAlumnado.eliminarTablaAlumnos();
    }
}
