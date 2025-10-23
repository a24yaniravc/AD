import java.io.File;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introducir ruta: "); // Usar carpeta
        String ruta = sc.nextLine();
        System.out.print("Introducir filtro: "); // .txt
        String ext = sc.nextLine();
        System.out.println();

        // Clase Filtrar
        System.out.println("Resultado de la búsqueda:");
        Filtrar.filtrar(ruta, ext);
        System.out.println();

        // Clase FiltrarNombre con interfaz
        FiltrarNombre filtrarNombre = new FiltrarNombre();

        System.out.println("Filtrado por '.md':");
        File file = new File(ruta);
        String[] list = file.list();
        for (String entry : list) {
            if (filtrarNombre.accept(file, entry)) {
                System.out.println(entry);
            }
        }

        sc.close();
    }
}
