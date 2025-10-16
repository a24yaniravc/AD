import java.io.File;
import java.io.IOException;

public class ManejoFichero {
    void crearFichero(String ruta) {
        try {
            File archivo = new File(ruta);
            boolean creado = archivo.createNewFile();

            if (creado) {
                System.out.println("El archivo ha sido creado.");
                System.out.println();
            } else {
                System.out.println("No se ha podido crear el archivo.");
                System.out.println();
            }
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }

    void borrarFichero(String ruta) {
        File archivo = new File(ruta);
        boolean deleted = archivo.delete();

        if (deleted) {
            System.out.println("El archivo se ha eliminado.");
        } else {
            System.out.println("No se ha podido eliminar el archivo.");
        }
    }

    void crearDirectorio(String ruta) {
        File dir = new File(ruta);
        boolean dirCreado = dir.mkdir();

        if (dirCreado) {
            System.out.println("El directorio ha sido creado.");
        } else {
            System.out.println("No se ha podido crear el directorio.");
        }
    }

    void borrarDirectorio(String ruta) {
        File dir = new File(ruta);
        boolean deleted = dir.delete();

        if (deleted) {
            System.out.println("El directorio se ha eliminado.");
        } else {
            System.out.println("No se ha podido eliminar el directorio.");
        }
    }

    void listarDirectorio(String ruta) {
        File archivo = new File(ruta);

        File[] fileList = archivo.listFiles();
        for (File entrada : fileList) {
            if(entrada.isFile()) {
                System.out.println("Archivo: " + entrada);   
            } else if (entrada.isDirectory()) {
                System.out.println("Directorio: " + entrada);
            }
        }
        
        System.out.println();
    }
}
