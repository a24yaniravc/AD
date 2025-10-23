import java.io.Serializable;

public class Persona implements Serializable {
    private String nombre;
    private String ap1;
    private String ap2;
    private int edad;

    Persona(String nombre, String ap1, String ap2, int edad) {
        this.nombre = nombre;
        this.ap1 = ap1;
        this.ap2 = ap2;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona[nombre=" + nombre + ", ap1=" + ap1 + ", ap2=" + ap2 + ", edad=" + edad
                + "]";
    }
}
