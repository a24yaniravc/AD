import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FicheroBinario {
    private File fichero;
    private String ruta;

    // Constructor
    public FicheroBinario(String ruta) {
        this.fichero = new File(ruta);
        this.ruta = ruta;
        try { 
            boolean created = fichero.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Getter
    public File getFichero() {
        return fichero;
    }

    public File getRuta(){
        return ruta;
    }

    // Insertar texto
    public void escribir(String texto) {
        try {
            FileOutputStream file = new FileOutputStream(fichero);
            BufferedOutputStream buffer = new BufferedOutputStream(file);

            buffer.write(texto.getBytes());

            System.out.println("Texto escrito correctamente en " + fichero.getName());
            buffer.close();
            file.close();
        } catch (IOException error) {
            System.out.println("No se ha podido escribir en el archivo.");
        }
    }

    // Leer el contenido
    public void leer() {
        try (BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(fichero))) {
            System.out.println();
            System.out.print("Contenido de " + fichero.getName() + ": ");

            int b;
            System.out.println();

            while ((b = buffer.read()) != -1) {
                System.out.printf("%c (%d)%n", (char) b, b);
            }

            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe.");
        } catch (IOException e) {
            System.out.println("No se ha podido leer el archivo.");
        }
    }

    // Copiar
    public void copiar(FicheroBinario ficheroDestino) {
        try (FileInputStream oldFile = new FileInputStream(fichero);
            FileOutputStream copyFile = new FileOutputStream(ficheroDestino.getFichero())) {

            byte[] buffer = new byte[1024];

            int bytesLeidos;
            while ((bytesLeidos = oldFile.read(buffer)) != -1) {
                copyFile.write(buffer, 0, bytesLeidos);
            }

            System.out.println("Contenido copiado de " 
                                + fichero.getName() +
                                " a " + 
                                ficheroDestino.getFichero().getName() 
                                + " correctamente.");
        } catch (IOException e) {
            System.out.println("Error al copiar el fichero.");
        }
    }
}
