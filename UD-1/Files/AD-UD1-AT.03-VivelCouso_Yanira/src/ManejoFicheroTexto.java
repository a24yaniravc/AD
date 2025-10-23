import java.util.Scanner;

public class ManejoFicheroTexto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ruta = "AD-UD1-AT.03-VivelCouso_Yanira/destino.bin";
        FicheroTexto archivo_texto = new FicheroTexto(ruta);

        boolean terminar = false;

        // Menú
        while (terminar != true) {
            System.out.println("==== Menú ====");
            System.out.println("1. Escribir en el fichero.");
            System.out.println("2. Leer fichero.");
            System.out.println("3. Salir.");

            // Leemos respuesta
            System.out.println("==============");
            System.out.print("Elija: ");
            String respuesta = sc.nextLine();
            System.out.println("");

            switch (respuesta) {
                case "1":
                    System.out.println("Ha decidido insertar texto.");
                    System.out.print("Introduzca el texto: ");
                    String texto = sc.nextLine();
                    archivo_texto.escribir(texto);
                    break;
                case "2":
                    System.out.println("Ha decidido leer el texto:");
                    archivo_texto.leer();
                    break;
                case "3":
                    System.out.println("Gracias por usar el programa :)");
                    System.out.println("[Saliendo...]");
                    terminar = true;
                    boolean deleted = archivo_texto.getFichero().delete();
                    break;
                default:
                    break;
            }
            System.out.println("");
        }
        sc.close();
    }
}
