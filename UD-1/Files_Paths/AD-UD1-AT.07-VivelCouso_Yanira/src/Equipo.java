import java.lang.Comparable;
import java.io.Serializable;

public class Equipo implements Serializable, Comparable<Equipo> {
    private String nombre;
    private int victorias;
    private int derrotas;
    private int puntos_a_favor;
    private int puntos_en_contra;

    // Constructor (solo nombre)
    Equipo(String nombre) {
        this.nombre = nombre;
    }

    // Constructor (todo)
    Equipo(String nombre, int victorias, int derrotas, int puntos_a_favor, int puntos_en_contra) {
        this.nombre = nombre;
        this.victorias = victorias;
        this.derrotas = derrotas;
        this.puntos_a_favor = puntos_a_favor;
        this.puntos_en_contra = puntos_en_contra;
    }

    // Getters
    public int getPuntos() {
        return victorias*2 + derrotas;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPartidosJugados() {
        return victorias + derrotas;
    }

    public int getDiferenciaDePuntos() {
        return puntos_a_favor - puntos_en_contra;
    }

    // Setters
    public void setVictorias(int victorias) {
        this.victorias = victorias;
        this.puntos_a_favor = (victorias * 2);
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
        this.puntos_en_contra = derrotas;
    }

    public void setPuntos_a_favor(int puntos_a_favor) {
        this.puntos_a_favor = puntos_a_favor;
    }

    public void setPuntos_en_contra(int puntos_en_contra) {
        this.puntos_en_contra = puntos_en_contra;

    }

    // Comparator (puntos > diferencia_puntos > nombre)
    @Override
    public int compareTo(Equipo eq2) {
        // Ordenar principalmente por puntos (descendente), luego por diferencia de puntos (descendente), luego por nombre
        int cmp = Integer.compare(eq2.getPuntos(), this.getPuntos());
        if (cmp != 0) return cmp;
        cmp = Integer.compare(eq2.getDiferenciaDePuntos(), this.getDiferenciaDePuntos());
        if (cmp != 0) return cmp;
        return this.nombre.compareToIgnoreCase(eq2.nombre);
    }

    // Método String
    @Override
    public String toString() {
        return "Equipo [nombre=" + nombre + ", puntos=" + getPuntos() + ", victorias=" + victorias + ", derrotas=" + derrotas + ", puntos_a_favor="
                + puntos_a_favor + ", puntos_en_contra=" + puntos_en_contra + ", partidos_jugados="
                + getPartidosJugados() + "]";
    }

    // Override de Equals (comparar por nombre)
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Equipo)) return false;
        Equipo other = (Equipo) obj;
        return this.nombre.equalsIgnoreCase(other.nombre);
    }
}
