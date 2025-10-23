import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

public class CopiaFichero {
    // Paths
    private Path pathOriginal = Paths.get("dir", "origen.txt");
    private Path pathNew = Paths.get("dir", "copia.txt");
    
    private Path dir_Backup = Paths.get("backup");
    private Path pathNewBackup = Paths.get("backup", pathNew.getFileName().toString());

    // Getters
    Path getPathOriginal() {
        return pathOriginal;
    }

    Path getPathNew() {
        return pathNew;
    }

    Path getDirBackup() {
        return dir_Backup;
    }

    // Método
    public void copy() {
        try {
            // Almacenamos el contenido de origen.txt
            String prueba = Files.readString(pathOriginal);
            
            // Sobreescribimos el contenido en copia.txt
            Files.writeString(pathNew, prueba);

            // Ahora movemos copia.txt a backup
            if (!Files.exists(dir_Backup)) {
                Files.createDirectory(dir_Backup);
            } 
                        
            Files.move(pathNew, pathNewBackup);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) throws Exception {
        CopiaFichero cf = new CopiaFichero();
        cf.copy();  
    }
}
