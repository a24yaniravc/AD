public class Proveedor {
    // Atributos
    String nombre;
    String telefono;

    // Constructores
    public Proveedor(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Proveedor(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Nombre='" + nombre + '\'' +
                ", Telefono='" + telefono + '\'';
    }
    
}
