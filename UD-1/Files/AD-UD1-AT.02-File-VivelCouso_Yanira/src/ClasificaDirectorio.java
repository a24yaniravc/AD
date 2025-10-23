import java.io.File;

public class ClasificaDirectorio {
    public void segunExtension(String dir) {
        File directorio = new File(dir);

        // Directorio válido?
        if (!directorio.exists() || !directorio.isDirectory()) {
            System.out.println("El parámetro debe ser un directorio válido.");
            return; // Salir si no es válido
        }

        // Listar ficheros dentro del directorio
        File[] archivos = directorio.listFiles();

        if (archivos == null) {
            System.out.println("No se pudieron listar los archivos del directorio.");
        } else {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    // extensión del archivo
                    String nombre = archivo.getName();
                    String extension = getExtension(nombre);

                    if (!extension.isEmpty()) {
                        // Subdirectorio con nombre en mayúsculas
                        String nombreSubdir = extension.toUpperCase();
                        File subdir = new File(directorio, nombreSubdir);

                        if (!subdir.exists()) {
                            subdir.mkdir();
                        }

                        // Crear destino nuevo como File
                        File destino = new File(subdir, archivo.getName());

                        // Mover archivo con renameTo
                        boolean result = archivo.renameTo(destino);
                        
                        if (!result) {
                            System.out.println("No se pudo mover: " + archivo.getName());
                        }
                    }
                }
            }
        }
    }

    // Método para obtener extensión archivo
    private String getExtension(String nombreArchivo) {
        int i = nombreArchivo.lastIndexOf('.');
        if (i > 0 && i < nombreArchivo.length() - 1) {
            return nombreArchivo.substring(i + 1);
        }
        return "";
    }

    // test
    public static void main(String[] args) {
        ClasificaDirectorio cd = new ClasificaDirectorio();
        cd.segunExtension("carpeta");
    }
}
