import java.util.ArrayList;
import java.util.Scanner;

public class Pricipal {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // Probar con los archivos arrr.txt y texto.md
        String respuesta = "";

        while (!respuesta.equals("5")) {
            System.out.println("==== Menú ====");
            System.out.println("1) Comprimir, seleccionando los archivos.");
            System.out.println("2) Comprimir, seleccionando una carpeta.");
            System.out.println("3) Descomprimir un archivo.");
            System.out.println("4) Modificar un ZIP existente.");
            System.out.println("5) Salir.");
            System.out.print("Respuesta: ");
            respuesta = sc.nextLine();
            System.out.println("");

            switch (respuesta) {
                case "1":
                    System.out.println("Comprimir archivos:");
                    System.out.print("Insertar nombre del archivo ZIP a crear: ");
                    String nombreZIP = sc.nextLine();
                    // Creamos la clase necesaria para los métodos
                    CrearZip cz = new CrearZip(nombreZIP);
                    System.out.print("Cuántos ficheros quiere añadir al ZIP? ");
                    int numFicheros = sc.nextInt();

                    sc.nextLine(); // Limpiamos el scanner

                    System.out.println("Ahora inserte los nombres de los ficheros que desea añadir:");
                    ArrayList<String> nombres = new ArrayList<String>();
                    for (int i = 0; i < numFicheros; i++) {
                        System.out.print("Fichero número " + (i + 1) + ": ");
                        String nombreFichero = sc.nextLine();
                        nombres.add(nombreFichero);
                    }

                    System.out.println("");
                    cz.comprimirFicheros(nombres, numFicheros, "ficheros");
                    break;
                case "2":
                    System.out.println("Comprimir directorio:");
                    System.out.print("Insertar nombre del archivo ZIP a crear: ");
                    nombreZIP = sc.nextLine();
                    // Creamos la clase necesaria para los métodos
                    CrearZip cz2 = new CrearZip(nombreZIP);

                    System.out.print("Ahora inserte la dirección de la carpeta: ");
                    String carpeta = sc.nextLine();
                    cz2.comprimirDirectorio(carpeta);
                    break;
                case "3":
                    System.out.println("Descomprimir ZIP:");
                    System.out.print("Insertar nombre del archivo ZIP a descomprimir: ");
                    nombreZIP = sc.nextLine();
                    System.out.print("Insertar nombre del directorio donde extraer: ");
                    String nombreDirec = sc.nextLine();

                    // Creamos la clase necesaria para los métodos
                    ExtraerZip ez = new ExtraerZip(nombreZIP, nombreDirec);
                    ez.descomprimir();
                    break;
                case "4":
                    System.out.println("Añadir archivos al ZIP:");
                    System.out.print("Insertar nombre del archivo ZIP: ");
                    nombreZIP = sc.nextLine();
                    // Creamos la clase necesaria para los métodos
                    cz = new CrearZip(nombreZIP);
                    System.out.print("Insertar ruta del archivo a añadir: ");
                    String archivoNuevo = sc.nextLine();
                    cz.anhadirContenido(archivoNuevo);
                    break;
                case "5":
                    System.out.println("Cerrando...");
                    break;
                default:
                    System.out.println("Elija una opción correcta.");
                    break;
            }
        }
        sc.close();
    }
}
