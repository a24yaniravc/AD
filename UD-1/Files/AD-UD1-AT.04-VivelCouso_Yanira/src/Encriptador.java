import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Encriptador {
    private Map<Character, Character> mapa = new HashMap<>();

    private String path = "codec.txt";
    private File file = new File(path);

    // Setter
    public Encriptador() {
        try {
            FileReader fileRead = new FileReader(this.path);
            BufferedReader buffer = new BufferedReader(fileRead);

            String caracteresLineaNoEncriptada = buffer.readLine();
            String caracteresLineaEncriptada = buffer.readLine();

            // Insertamos valores
            for (int i = 0; i < caracteresLineaNoEncriptada.length(); i++) {
                mapa.put(caracteresLineaNoEncriptada.charAt(i), caracteresLineaEncriptada.charAt(i));
            }

            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void test() {
        System.out.println(mapa);
    }

    // Getters
    public String getFilePath() {
        return path;
    }

    public File getFile() {
        return file;
    }

    public Map<Character, Character> getMapa() {
        return mapa;
    }

    public char valorEncriptado(char caracter, Map<Character, Character> lista) {
        return mapa.get(caracter);
    }

    public String getExtension(String nombreArchivo) {
        int i = nombreArchivo.lastIndexOf('.');
        if (i > 0 && i < nombreArchivo.length() - 1) {
            return nombreArchivo.substring(i + 1);
        }
        return "";
    }

    public String quitarTipo(String archivo) {
        String extension = getExtension(archivo);

        return path.replace(extension, "");
    }

    public void encriptar(String ruta) {
        try {
            // Leemos el archivo
            FileReader fileRead = new FileReader(ruta);
            BufferedReader bufferRead = new BufferedReader(fileRead);

            String linea_encriptar = bufferRead.readLine();
            String devolver = "";

            for (int i = 0; i < linea_encriptar.length(); i++) {
                devolver += valorEncriptado(linea_encriptar.charAt(i), getMapa());
            }

            bufferRead.close();

            // Escribimos el archivo
            File archivoNuevo = new File(quitarTipo(getFile().getName()) +
                                     "__codificado" + getExtension(ruta));
            boolean creado = archivoNuevo.createNewFile();
            FileWriter fileWrite = new FileWriter(quitarTipo(getFile().getName()) +
                                     "__codificado" + getExtension(ruta));
            BufferedWriter bufferWrite = new BufferedWriter(fileWrite);

            bufferWrite.write(devolver);

            bufferWrite.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        Encriptador enc = new Encriptador();
        enc.test();
        // enc.valorEncriptado("e");
    }
}
