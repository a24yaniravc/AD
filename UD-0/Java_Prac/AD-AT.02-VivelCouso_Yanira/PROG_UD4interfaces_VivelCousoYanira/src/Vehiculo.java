public class Vehiculo {
    private String matricula;
    private int num_ruedas;
    private int anho_fabricacion;

    public Vehiculo(String matricula, int num_ruedas, int anho_fabricacion) {
        this.matricula = matricula;
        this.num_ruedas = num_ruedas;
        this.anho_fabricacion = anho_fabricacion;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getNumRuedas() {
        return num_ruedas;
    }

    public int getAnhoFab() {
        return anho_fabricacion;
    }
}