import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CrearZip {
    private String nombreZIP = "";

    CrearZip(String nombreZIP) {
        if (!nombreZIP.contains(".zip")) {
            this.nombreZIP = "ficheros\\" + nombreZIP + ".zip";
        } else {
            this.nombreZIP = "ficheros\\" + nombreZIP;
        }
    }

    public void comprimirFicheros(ArrayList<String> nombres, int numFicheros, String carpeta) {
        Boolean generacionCorrecta = true;
        ArrayList<String> errores = new ArrayList<String>();

        try {
            FileOutputStream fos = new FileOutputStream(nombreZIP);
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            // Inserta ficheros en el ZIP
            for (int i = 0; i < numFicheros; i++) {
                File fileToZip = new File(carpeta + "\\" + nombres.get(i));

                // Añade el fichero si existe
                if (fileToZip.exists()) {
                    FileInputStream fis = new FileInputStream(fileToZip);
                    ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                    zipOut.putNextEntry(zipEntry);

                    byte[] buffer = new byte[1024];
                    int bytesLeidos;
                    while ((bytesLeidos = fis.read(buffer)) >= 0) {
                        zipOut.write(buffer, 0, bytesLeidos);
                    }
                    fis.close();
                } else {
                    // Si uno falla, lo guarda
                    generacionCorrecta = false;
                    errores.add(nombres.get(i));
                }
            }

            zipOut.close();
            fos.close();

            System.out.println("Compresión finalizada.");

            // Mostrar errores si los hay
            if (generacionCorrecta == false) {
                System.out.println("Ha habido un fallo encontrando los siguientes archivos: ");
                for (int i = 0; i < errores.size(); i++) {
                    System.out.print(errores.get(i));
                    if (i != (errores.size() - 1)) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void comprimirDirectorio(String carpeta) {
        File directorio = new File(carpeta);

        // Validar que es un directorio
        if (!directorio.exists() || !directorio.isDirectory()) {
            System.out.println("El parámetro debe ser un directorio válido.");
        } else {
            // Comprimir el directorio
            try (FileOutputStream fos = new FileOutputStream(nombreZIP);
                    ZipOutputStream zipOut = new ZipOutputStream(fos)) {
                comprimirRecursivo(directorio, directorio.getName(), zipOut);
                System.out.println("Compresión finalizada.");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void comprimirRecursivo(File fileToZip, String nombreEnZip, ZipOutputStream zipOut) throws IOException {
        // Si es un directorio, recorrer su contenido
        if (fileToZip.isDirectory()) {
            if (!nombreEnZip.endsWith("/")) {
                nombreEnZip += "/";
            }
            zipOut.putNextEntry(new ZipEntry(nombreEnZip));
            zipOut.closeEntry();
            // Recorrer los archivos hijos
            File[] children = fileToZip.listFiles();
            if (children != null) {
                for (File childFile : children) {
                    comprimirRecursivo(childFile, nombreEnZip + childFile.getName(), zipOut);
                }
            }
            return;
        }

        // Si es un archivo, añadirlo al ZIP
        try (FileInputStream fis = new FileInputStream(fileToZip)) {
            ZipEntry zipEntry = new ZipEntry(nombreEnZip);
            zipOut.putNextEntry(zipEntry);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) >= 0) {
                zipOut.write(buffer, 0, length);
            }
        }
    }

    // Añadir contenido a un ZIP existente
    public void anhadirContenido(String archivoNuevo) {
        try {
            Map<String, String> env = new HashMap<>();
            env.put("create", "true");
            Path path = Paths.get(nombreZIP);
            if (!Files.exists(path)) {
                System.out.println("El archivo ZIP no existe.");
            } else {
                URI uri = URI.create("jar:" + path.toUri());
                String filename = archivoNuevo;
                if (!Files.exists(Paths.get(filename))) {
                    System.out.println("El archivo a añadir no existe.");
                } else {
                    try (FileSystem fs = FileSystems.newFileSystem(uri, env)) {
                        Path file = Paths.get(filename); // Fichero a incluir
                        Path nf = fs.getPath("/" + file.getFileName());
                        Files.write(nf, Files.readAllBytes(file), StandardOpenOption.CREATE);
                        System.out.println("Archivo añadido correctamente.");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
