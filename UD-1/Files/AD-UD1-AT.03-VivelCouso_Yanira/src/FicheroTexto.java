import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FicheroTexto {
    private File fichero;
    private String ruta;

    // Setter
    public FicheroTexto(String ruta) {
        this.fichero = new File(ruta);
        this.ruta = ruta;
        try {
            boolean created = fichero.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Getters
    public File getFichero() {
        return fichero;
    }

    public String getRuta() {
        return ruta;
    }

    public void escribir(String texto) {
        try {
            FileWriter file = new FileWriter(getRuta(), true);
            BufferedWriter buffer = new BufferedWriter(file);

            buffer.write(texto);
            
            // Cierre
            buffer.close();
            file.close();

        } catch (IOException e) {
            System.out.println("No se puede crear el archivo.");
        }
    }

    public void leer() {
        try {
            FileReader file = new FileReader(getRuta());

            int i = file.read();

            while (i != -1) {
                char c = (char) i;
                System.out.print(c);
                i = file.read();
            }
            
            // 3. Cierre del archivo.
            file.close();
            System.out.println("");
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe.");
        } catch (IOException e) {
            System.out.println("No se puede abrir el archivo.");
        }
    }
}
