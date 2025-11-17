package com.principales;

import java.util.Scanner;

import com.conexiones.MySQL_Connection;
import com.gestores.GestorAlumnado;
import com.gestores.GestorBD;

public class Principal {
    private static Scanner sc = new Scanner(System.in);

    // Variables
    private static MySQL_Connection MySQL_connection;
    private static GestorBD gestorBD;
    private static GestorAlumnado gestorAlumnado;

    public static void main(String[] args) {
        // Probamos la conexión a MySQL
        MySQL_connection = new MySQL_Connection();

        // Creamos la DB dam2
        gestorBD = new GestorBD(MySQL_connection);
        gestorBD.crearBD();

        // Creamos la tabla de alumnos
        gestorAlumnado = new GestorAlumnado(MySQL_connection);
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
                
                    System.out.println("=== Menú de cambios ===");
                    System.out.println("1. Modificar edad");
                    System.out.println("2. Modificar nombre");
                    System.out.println("3. Modificar email");
                    System.out.print("Selección: ");
                    String seleccion = sc.nextLine();

                    switch (seleccion) {
                        case "1":
                            System.out.print("Nueva edad: ");
                            String nuevaEdad = sc.nextLine();
                            gestorAlumnado.modificarAlumno(r, nuevaEdad, seleccion);
                            break;
                        case "2":
                            System.out.print("Nuevo nombre: ");
                            String nuevoNombre = sc.nextLine();
                            gestorAlumnado.modificarAlumno(r, nuevoNombre, seleccion);
                            break;
                        case "3":
                            System.out.print("Nuevo email: ");
                            String nuevoEmail = sc.nextLine();
                            gestorAlumnado.modificarAlumno(r, nuevoEmail, seleccion);
                            break;
                        default:
                            System.out.println("Selección no válida.");
                    }
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
