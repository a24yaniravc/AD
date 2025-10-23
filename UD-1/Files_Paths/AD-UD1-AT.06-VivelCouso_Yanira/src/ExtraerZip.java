import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ExtraerZip {
    private String nombreZIP = "";
    private String zonaDescomprimir = "";

    ExtraerZip(String nombreZIP, String nombreDirec) {
        if (!nombreZIP.contains(".zip")) {
            this.nombreZIP = "ficheros\\" + nombreZIP + ".zip";
        } else {
            this.nombreZIP = "ficheros\\" + nombreZIP;
        }
        this.zonaDescomprimir = nombreDirec;
    }

    public void descomprimir() {
        File carpetaSalida = new File(zonaDescomprimir);
        // Creamos la carpeta si no existe
        if (!carpetaSalida.exists()) {
            carpetaSalida.mkdir();
        }

        try (FileInputStream fis = new FileInputStream(nombreZIP);
                ZipInputStream zis = new ZipInputStream(fis)) {
            ZipEntry entrada;

            while ((entrada = zis.getNextEntry()) != null) {
                System.out.println("Extrayendo: " + entrada.getName());
                File nuevoFichero = new File(zonaDescomprimir, entrada.getName());
                new File(nuevoFichero.getParent()).mkdirs();

                try (FileOutputStream fos = new FileOutputStream(nuevoFichero)) {
                    byte[] buffer = new byte[1024];
                    int bytesLeidos;
                    while ((bytesLeidos = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, bytesLeidos);
                    }
                }
                zis.closeEntry();
            }
            System.out.println("Descompresión completada en la carpeta: " +
                    zonaDescomprimir);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
