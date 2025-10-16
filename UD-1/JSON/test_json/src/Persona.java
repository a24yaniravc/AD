import java.util.ArrayList;

public class Persona {
    private String nombre;
    private String apellidos;
    private int edad;
    private String ciudad;
    private ArrayList<String> telefonos;

    // Constructor
    public Persona(String nombre, String apellidos, int edad, String ciudad, ArrayList<String> telefonos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.ciudad = ciudad;
        this.telefonos = telefonos;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public ArrayList<String> getTelefonos() {
        return telefonos;
    }

    @Override
    public String toString() {
        return "Persona [nombre=" + nombre + 
                ", apellidos=" + apellidos +
                ", edad=" + edad + ", ciudad=" + ciudad + 
                ", telefonos=" + telefonos + "]";
    }
}
