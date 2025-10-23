import java.io.File;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ManejoFichero manejoFichero = new ManejoFichero();

        System.out.println("==== Pruebas ====");
        System.out.print("Inserte ruta para crear el archivo: ");
        String rutaArchivo = sc.nextLine();
        File archivo = new File(rutaArchivo);
        manejoFichero.crearFichero(rutaArchivo); // Crea el fichero indicado.

        System.out.print("Inserte ruta para crear un directorio: ");
        String rutaDirectorio = sc.nextLine();
        manejoFichero.crearDirectorio(rutaDirectorio); // Crear directorio
        System.out.println();

        System.out.println("Listado directorio test1: ");
        manejoFichero.listarDirectorio("test1"); // Listar

        System.out.println("Mostrando información sobre el archivo creado: ");
        InfoFichero.mostrarInfo(archivo); // Mostrando info

        // Borrar directorio y archivo creados
        System.out.println(); 
        System.out.println("Borrando archivos..."); 
        manejoFichero.borrarFichero(rutaDirectorio);
        manejoFichero.borrarDirectorio(rutaArchivo);
    }
}
