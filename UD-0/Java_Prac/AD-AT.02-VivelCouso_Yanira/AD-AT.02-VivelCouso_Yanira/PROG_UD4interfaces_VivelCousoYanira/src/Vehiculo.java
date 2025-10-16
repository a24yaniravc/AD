public class Vehiculo {
    private String matricula;
    private int num_ruedas;
    private int anho_fabricacion;

    public Vehiculo(String matricula, int num_ruedas, int anho_fabricacion) {
        this.matricula = matricula;
        this.num_ruedas = num_ruedas;
        this.anho_fabricacion = anho_fabricacion;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        return true;
    }
}