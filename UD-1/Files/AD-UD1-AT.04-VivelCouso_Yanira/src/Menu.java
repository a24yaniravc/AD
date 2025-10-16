import java.io.File;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Encriptador enc = new Encriptador();
        String eleccion = "";

        while (!eleccion.equals("4")) {
            System.out.println("");
            System.out.println("==== Menú ====");
            System.out.println("1) Encriptar archivo.");
            System.out.println("2) Desencriptar archivo.");

            System.out.println("==============");
            System.out.print("Elección: ");
            eleccion = sc.nextLine();

            switch (eleccion) {
                case "1":
                    System.out.print("Introduzca ruta: ");
                    String ruta = sc.nextLine();                  
                    File archivoNuevo = new File(ruta);

                    while (!archivoNuevo.exists()) {
                        System.out.println("Por favor, inserte una ruta válida.");
                        System.out.print("Introduzca ruta: ");
                        ruta = sc.nextLine();
                        enc.encriptar(ruta);
                    }
                    //enc.valorEncriptado(ruta);
                    break;
            
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }
        }
        sc.close();
    }
}
