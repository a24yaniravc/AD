import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Principal {
    public static void main(String[] args) {
        Almacen ap = new Almacen();
        Persona p1 = new Persona("Juan", "Couso", "Fernandez", 12);
        Persona p2 = new Persona("Mara", "Couso", "Martinez", 27);
        Persona p3 = new Persona("Rayne", "Fernandez", "Castro", 32);
        
        // Insertamos los objetos Persona en el archivo bin
        try {
            FileOutputStream stream = new FileOutputStream("personas.bin");
            ObjectOutputStream out = new ObjectOutputStream(stream);

            out.writeObject(p1);
            out.writeObject(p2);
            out.writeObject(p3);
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Leemos el almacen
        ap.leerPersonas();
    }
}
