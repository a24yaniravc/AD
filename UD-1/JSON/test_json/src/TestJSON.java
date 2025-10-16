import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Tests de JSON con Gson
// Crear un archivo JSON
// Leer un archivo JSON

public class TestJSON {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // Menu
        menu();
        int opcion = sc.nextInt();

        System.out.println("");

        while (opcion != 6) {
            switch (opcion) {
            case 1:
                crearJSON_ObjectConString();
                break;
            case 2:
                crearJSON_ObjectConJSON();
                break;
            case 3:
                crearJSONDesdeObjeto();
                break;
            case 4:
                deserializarJSONaObjeto();
                break;
            case 5:
                modificarJSON_Object();
                break;
            case 6:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida");
                break;  
            }
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            System.out.println("");
        }
        sc.close();
    }

    public static void menu() {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Crear JSON-Object desde String");
        System.out.println("2. Crear JSON-Object desde Objeto");
        System.out.println("3. Crear JSON desde Objeto");
        System.out.println("4. Deserializar/Leer un JSON a un Objeto.");
        System.err.println("5. Modificar + añadir datos a JSON-Object");
        System.out.println("6. Salir");
        
        System.out.print("Seleccione una opción: ");
    }

    // Funciones
    public static void crearJSON_ObjectConString() {
        // Parseo de un String
        String jsonString = "{\"nombre\":\"John\", \"apellidos\":\"Doe\", \"edad\":30, \"ciudad\":\"New York\", \"telefonos\":[\"123-456-7890\", \"987-654-3210\"]}";

        // Convertir JSON String a Objeto
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        System.out.println("JSON Object desde String: " + jsonObject);
    }

    public static void crearJSON_ObjectConJSON() {
           // Parseo de un fichero JSON
        try {
            FileReader reader = new FileReader("archivos/persona.json");
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

            System.out.println("Objeto JSON desde archivo: " + jsonObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void crearJSONDesdeObjeto() {
        ArrayList<String> telefonos = new ArrayList<String>(){{ add("123-456-7890"); add("987-654-3210"); }};
        Persona persona = new Persona("John", "Doe", 30, "New York", telefonos);

        // Crear Gson con formato bonito
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("archivos/persona-pretty.json")){
            gson.toJson(persona, writer);
            System.out.println("Archivo creado con exito.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deserializarJSONaObjeto() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("archivos/persona.json")) {
            Persona persona = gson.fromJson(reader, Persona.class);
            System.out.println("Objeto deserializado: " + persona);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void modificarJSON_Object() {
        try {
            FileReader reader = new FileReader("archivos/persona.json");
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();

            // Modificar el objeto JSON
            // Añadir
            jsonObject.addProperty("direccion", "123 Main St");
            jsonObject.addProperty("profesión", "Ingeniero");
            System.out.println("Añadido dirección y profesión.");

            // Modificar
            String ciudad = jsonObject.get("ciudad").getAsString();
            int edad = jsonObject.get("edad").getAsInt();

            jsonObject.addProperty("ciudad", "Los Angeles");
            jsonObject.addProperty("edad", 50);
            System.out.println("Modificado ciudad '" + ciudad + "' a '" +
                     jsonObject.get("ciudad").getAsString() + 
                     "' y edad '" + edad + "' a '" + 
                     jsonObject.get("edad").getAsString() + "'.");

            // Borrar
            jsonObject.remove("telefonos");
            System.out.println("Borrado telefonos.");
            System.out.println("");
            System.out.println("Objeto JSON modificado: " + jsonObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
