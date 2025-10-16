import java.io.*;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class GestorProductos {
    // Métodos estáticos para guardar y leer productos en formato JSON
    public static void guardarProductos(ArrayList<Producto> p) {
        File directorio = new File("archivos");
        Boolean error = false;

        // Si no existe, lo creamos
        if (!directorio.isDirectory() || !directorio.exists()) {
            boolean created = directorio.mkdir();
            if (created) {
                System.out.println("Directorio 'archivos' creado.");
            } else {
                System.out.println("No se ha podido crear el directorio 'archivos'.");
                error = true;
            }
        }

        // Si da error, no se puede guardar
        if (error == false) {
            System.out.println("Guardando " + p.size() + " productos en un archivo JSON...");

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder = gsonBuilder.setPrettyPrinting(); // Formatea el JSON para que sea más bonito
            Gson gson = gsonBuilder.create();

            // Escribimos el archivo
            try (FileWriter writer = new FileWriter("archivos" + "\\" + "productos.json")) {
                gson.toJson(p, writer);
                System.out.println("Productos guardados correctamente en productos.json");
            } catch (Exception e) {
                System.out.println("Error al guardar los productos: " + e.getMessage());
            }
        } else {
            System.out.println("No se puede guardar el archivo JSON.");
        }

    }

    // Leer el archivo JSON y mostrar su contenido en consola
    public static void leerProductos() {
        System.out.println("Productos leídos desde JSON:");

        if (!new File("archivos" + "\\" + "productos.json").exists() // Comprueba si existe
                || !new File("archivos" + "\\" + "productos.json").isFile()) { // Comprueba si es un archivo
            System.out.println("El archivo productos.json no existe.");
        } else if (new File("archivos" + "\\" + "productos.json").length() == 0) { // Comprueba si está vacío
            System.out.println("El archivo productos.json está vacío.");
        } else { // Si existe y no está vacío, lo leemos
            try {
                FileReader reader = new FileReader("archivos" + "\\" + "productos.json");
                BufferedReader buffer = new BufferedReader(reader);

                String line = buffer.readLine();

                // Leemos línea a línea
                while (line != null) {
                    System.out.println(line);
                    line = buffer.readLine();
                }

                buffer.close();
                reader.close();

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                System.out.println("No se puede abrir el archivo.");
            }
        }
    }

    // Leer el archivo JSON y transferir los productos a un ArrayList
    public static void leerProductosArray() {
        ArrayList<Producto> productos = new ArrayList<>();
        Gson gson = new Gson();

        System.out.println("Productos leídos desde JSON y transferidos a ArrayList:");

        if (!new File("archivos" + "\\" + "productos.json").exists() // Comprueba si existe
                || !new File("archivos" + "\\" + "productos.json").isFile()) { // Comprueba si es un archivo
            System.out.println("El archivo productos.json no existe.");
        } else if (new File("archivos" + "\\" + "productos.json").length() == 0) { // Comprueba si está vacío
            System.out.println("El archivo productos.json está vacío.");
        } else { // Si existe y no está vacío, lo leemos
            try {
                FileReader reader = new FileReader("archivos" + "\\" + "productos.json");
                Type tipoLista = new TypeToken<ArrayList<Producto>>() {}.getType(); // Definimos el tipo de la lista
                ArrayList<Producto> productosLista = gson.fromJson(reader, tipoLista);

                // Añadimos los productos leídos a la lista (si no es null)
                if (productosLista != null) {
                    for (Producto p : productosLista) {
                        productos.add(p);
                    }
                } else {
                    productos = new ArrayList<>(); // Si es null, inicializamos la lista vacía
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            if (productos == null || productos.size() == 0) { // Si no se ha leído nada
                System.out.println("El archivo JSON está vacío o no contiene productos.");
            } else { // Si se ha leído algo, lo mostramos
                System.out.println("Se han leído " + productos.size() + " productos del archivo JSON:");
                for (Producto p : productos) {
                    System.out.println(p);
                }
            }
        }
    }
}
