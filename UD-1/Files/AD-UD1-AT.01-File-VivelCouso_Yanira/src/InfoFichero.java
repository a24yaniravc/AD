import java.io.File;

public class InfoFichero {
    public static void mostrarInfo(File file) {
        try {
            if (file.exists()) {
                System.out.println("Nombre: " + file.getName());
                System.out.println("Ruta relativa: " + file.getPath());
                System.out.println("Ruta absoluta: " + file.getAbsolutePath());
                System.out.println("Permiso de lectura: " + file.canRead());
                System.out.println("Permiso de escritura: " + file.canWrite());
                System.out.println("Tamaño: " + file.length());
                System.out.println("Directorio? " + file.isDirectory());
                System.out.println("Fichero? " + file.isFile());
            } else {
                System.out.println("La ruta indicada no existe.");
            }
        } catch (Exception error) {
            System.out.println("Se ha detectado un error.");
        }
    }
}
