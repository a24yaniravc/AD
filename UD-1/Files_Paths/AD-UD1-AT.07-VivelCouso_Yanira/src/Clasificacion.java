import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.StringJoiner;
import java.util.Scanner;

public class Clasificacion implements Serializable {
    private Scanner sc = new Scanner(System.in);
    private Equipo[] equipos;
    private int size = 0;

    // Método para añadir un equipo
    public void anadirEquipo() {
        boolean hecho = false;

        // Datos a recoger
        String seguir = "";
        int ganados = 0;
        int perdidos = 0;
        int favor = 0;
        int contra = 0;

        System.out.print("Nombre del equipo: ");
        String nombre = sc.nextLine();
        while (hecho != true) {
            System.out.print("(S/N) Insertar datos? ");
            seguir = sc.nextLine().toUpperCase();
            if (seguir.equals("S")) {
                System.out.print("Partidos ganados: ");
                ganados = sc.nextInt();
                System.out.print("Partidos perdidos: ");
                perdidos = sc.nextInt();
                System.out.print("Puntos a favor: ");
                favor = sc.nextInt();
                System.out.print("Puntos en contra: ");
                contra = sc.nextInt();
                addEquipo(new Equipo(nombre, ganados, perdidos, favor, contra));
                System.out.println("Equipo añadido.");
                hecho = true;
            } else if (seguir.equals("N")) {
                addEquipo(new Equipo(nombre));
                System.out.println("Equipo añadido.");
                hecho = true;
            } else {
                System.out.println("Por favor, inserte una respuesta correcta.");
            }
        }
    }

    // Constructor por defecto
    public Clasificacion() {
        equipos = new Equipo[18];
        size = 0;
    }

    // Constructor
    public Clasificacion(int tamanho) {
        equipos = new Equipo[tamanho];
        size = 0;
    }

    // Getter
    public Equipo[] getEquipos() {
        return equipos;
    }

    // Método para añadir un equipo
    public boolean addEquipo(Equipo e) {
        if (e == null)
            return false;
        // evitar duplicados por nombre
        for (int i = 0; i < size; i++) {
            if (equipos[i].equals(e))
                return false;
        }
        equipos[size++] = e;
        return true;
    }

    // Método para eliminar un equipo
    public boolean removeEquipo(String nombre) {
        for (int i = 0; i < size; i++) {
            if (equipos[i].getNombre().equalsIgnoreCase(nombre)) {
                System.arraycopy(equipos, i + 1, equipos, i, size - i - 1);
                equipos[--size] = null;
                System.out.println("Equipo eliminado: " + nombre);
                return true;
            }
        }
        System.out.println("Equipo no encontrado: " + nombre);
        return false;
    }

    // Método para mostrar la clasificación
    public void mostrarClasificacion() {
        if (size == 0) {
            System.out.println("No hay equipos en la clasificación.");
            return;
        }
        // Usar TreeSet para ordenar los equipos según Comparable
        TreeSet<Equipo> orden = new TreeSet<>(Equipo::compareTo);
        for (int i = 0; i < size; i++)
            orden.add(equipos[i]);
        int posicion = 1;
        for (Equipo e : orden) {
            System.out.println(posicion + ". " + e);
            posicion++;
        }
    }

    // Método para guardar la clasificación
    public void guardarClasificacion(String nombre_archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombre_archivo))) {
            // Serialize como ArrayList
            ArrayList<Equipo> lista = new ArrayList<>();
            for (int i = 0; i < size; i++)
                lista.add(equipos[i]);
            oos.writeObject(lista);
            System.out.println("Clasificación guardada en " + nombre_archivo);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    // Método para cargar la clasificación
    public void cargarClasificacion(String nombre_archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombre_archivo))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) {
                ArrayList<?> tempList = (ArrayList<?>) obj;
                ArrayList<Equipo> equipoList = new ArrayList<>();
                for (Object o : tempList) {
                    if (o instanceof Equipo) {
                        equipoList.add((Equipo) o);
                    }
                }
                // Meter en el array
                TreeSet<Equipo> orden = new TreeSet<>();
                orden.addAll(equipoList);
                equipos = new Equipo[orden.size()];
                size = 0;
                for (Equipo e : orden)
                    equipos[size++] = e;
            } else {
                System.out.println("El archivo no contiene una lista válida de equipos.");
            }
            System.out.println("Clasificación cargada de " + nombre_archivo);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }
    }

    // Método String
    @Override
    public String toString() {
        if (size == 0)
            return "No hay equipos en la clasificación.";
        TreeSet<Equipo> orden = new TreeSet<>();
        for (int i = 0; i < size; i++)
            orden.add(equipos[i]);
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        int posicion = 1;
        for (Equipo e : orden) {
            sj.add(posicion + ". " + e.toString());
            posicion++;
        }
        return sj.toString();
    }
}
