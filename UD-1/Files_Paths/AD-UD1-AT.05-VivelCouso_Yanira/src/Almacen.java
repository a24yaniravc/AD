import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Almacen {
    void leerPersonas() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("personas.bin"))) {
            Persona p;
            System.out.println("Iniciando lectura...");
            
            // Leer objetos Persona hasta alcanzar el final del archivo
            while ((p = leer(in)) != null) {
                System.out.println(p);
            }

            System.out.println("Final del archivo alcanzado.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Persona leer(ObjectInputStream in) {
        try {
            return (Persona) in.readObject();
        } catch (EOFException e) {
            return null; // Se alcanzó el final del fichero
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
