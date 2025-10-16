import java.util.*;

public class Principal {
    public static void main(String[] args) {
        // Datos
        String nombre_archivo = "clasificacion.dat";
        Scanner sc = new Scanner(System.in);
        int num_eq = 0;
        String respuesta = "";
        Boolean iniciado = false;

        // Inicio
        System.out.println("Iniciando...");
        while (iniciado != true) {
            System.out.print("Número de equipos personalizado? (S/N) ");
            respuesta = sc.nextLine();
            if (respuesta.toUpperCase().equals("S")) {
                System.out.print("Número de equipos: ");
                num_eq = sc.nextInt();
                Clasificacion clasf = new Clasificacion(num_eq);
                sc.nextLine();
                getMenu(sc, clasf, nombre_archivo, num_eq);
                iniciado = true;
            } else if (respuesta.toUpperCase().equals("N")) {
                Clasificacion clasf = new Clasificacion();
                getMenu(sc, clasf, nombre_archivo, 18);
                iniciado = true;
            } else {
                System.out.println("Por favor, inserte una respuesta correcta.");
            }
        }
        sc.close();
    }

    public static void getMenu(Scanner sc, Clasificacion clasf, String nombre_archivo, int num_eq) {
        // Menú
        boolean salir = false;
        while (!salir) {
            System.out.println("==== Menú ====");
            System.out.println("a. Añadir equipo");
            System.out.println("b. Mostrar clasificación");
            System.out.println("c. Guardar clasificación");
            System.out.println("d. Cargar clasificación");
            System.out.println("e. Salir");
            System.out.print("Elige una opción: ");
            String opcion = sc.nextLine().toLowerCase();

            switch (opcion) {
                case "a":
                    if (clasf.getEquipos().length == num_eq-1) {
                        System.out.println("Número de equipos máximo alcanzado.");
                    } else {
                        clasf.anadirEquipo();
                    }
                    break;
                case "b":
                    clasf.mostrarClasificacion();
                    break;
                case "c":
                    clasf.guardarClasificacion(nombre_archivo);
                    break;
                case "d":
                    clasf.cargarClasificacion(nombre_archivo);
                    break;
                case "e":
                    System.out.println("Saliendo...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
