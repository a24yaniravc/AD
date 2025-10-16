import java.io.File;

public class Filtrar {
    static void filtrar(String ruta, String extension) {
        File ref = new File(ruta);

        File[] fileList = ref.listFiles();

        for (File entry : fileList) {
            if (entry.getName().endsWith(extension)) {
                System.out.println(entry.getName());
            }
        }
    }
}
