public class Bosque {
    private String nombre;
    private int nivelPeligro;
    private Monstruo monstruoJefe;

    public Bosque(String nombre, int nivelPeligro, Monstruo monstruoJefe) {
        this.nombre = nombre;
        this.nivelPeligro = nivelPeligro;
        this.monstruoJefe = monstruoJefe;
    }

    public void mostrarJefe() {
        System.out.println(
            "Monstruo jefe: " + monstruoJefe.getNombre() 
            + ", vida: " + monstruoJefe.getVida() + 
            ", tipo de monstruo: " + monstruoJefe.getTipo() + 
            ", fuerza del monstruo: " + monstruoJefe.getFuerza()
            );
    }

    public void cambiarJefe(Monstruo nuevoJefe) {
        this.monstruoJefe = nuevoJefe;
    }

    public Monstruo getMonstruoJefe() {
        return monstruoJefe;
    }
}
