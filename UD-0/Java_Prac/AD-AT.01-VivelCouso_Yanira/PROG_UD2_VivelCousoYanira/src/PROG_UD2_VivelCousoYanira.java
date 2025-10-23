import java.util.Scanner;

public class PROG_UD2_VivelCousoYanira {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // Menú
        imprimirMenu();
        int respuesta = sc.nextInt();

        // Respuestas
        while (respuesta != 4) {
            if (respuesta != 1 && respuesta != 2 && respuesta != 3 && respuesta != 4) {
                System.out.println("Opción inválida. Inténtelo de nuevo.");
                System.out.println("");
                imprimirMenu();
                respuesta = sc.nextInt();
            } else {
                switch (respuesta) {
                    case 1:
                        subirSueldo(sc);
                        break;
                    case 2:
                        mayoresEdad(sc);
                        break;
                    case 3:
                        alumnoAlto(sc);
                        break;
                }
                System.out.println("");
                imprimirMenu();
                respuesta = sc.nextInt();
                sc.nextLine();
            }
        }

        System.out.println("Cerrando el programa...");
    }

    // Menú
    private static void imprimirMenu() {
        System.out.println("--- MENÚ ---");
        System.out.println("1.Incremento sueldo");
        System.out.println("2.Alumnado mayor de edad");
        System.out.println("3.Alumnado alto.");
        System.out.println("4.Salir");
        System.out.println("------------");

        System.out.print("Seleccione una de las opciones proporcionadas: ");
    }

    // Opción 1: Incremento sueldo
    private static void subirSueldo(Scanner sc) {
        System.out.println("");
        System.out.println("Opción 1)");

        System.out.print("Por favor, introduzca el sueldo del trabajador: ");
        double sueldo = sc.nextDouble();

        if (sueldo < 1000) {
            sueldo = sueldo * 1.15;
        } else {
            sueldo = sueldo * 1.12;
        }

        System.out.println("El nuevo sueldo asignado es: " + sueldo);
    }

    // Opcion 2: Alumnado mayor de edad
    private static void mayoresEdad(Scanner sc) {
        System.out.println("");
        System.out.println("Opción 2)");
        
        int mayores = 0;

        for (int i = 1; i <= 5; i++) {
            System.out.print("Introduce la edad del alumno " + i + ": ");
            int edad = sc.nextInt();

            if (edad >= 18) {
                mayores++;
            }
        }

        System.out.println("Número de alumnos mayores de edad: " + mayores);
    }

    // Opción 3: Alumno alto
    private static void alumnoAlto(Scanner sc) {
        System.out.println("");
        System.out.println("Opción 3)");        

        int contador = 1;
        int altos = 0;

        while (contador != 5) {
            System.out.print("Introduce la altura del alumno " + contador + ": ");
            double altura = sc.nextDouble();

            if (altura > 1.75) {
                altos++;
            }

            contador++;
        }

        System.out.println("Número de alumnos con altura superior a 1.75m: " + altos);
    }
}
