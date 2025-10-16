public class Coche extends Vehiculo implements Comparable<Coche> {
    private int num_puertas;

    Coche(String matricula, int num_ruedas, int anho_fabricacion, int num_puertas) {
        super(matricula, num_ruedas, anho_fabricacion);
        this.num_puertas = num_puertas;
    }

    public int getNumPuertas() {
        return num_puertas;
    }

    public int compareTo(Coche v2) {
        if (this.num_puertas > v2.num_puertas)
            return 1;
        if (this.num_puertas < v2.num_puertas)
            return -1;
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        Coche coche2 = (Coche) obj;
        if(this.num_puertas == coche2.num_puertas
            && this.getNumRuedas() == coche2.getNumRuedas()
            && this.getMatricula() == coche2.getMatricula()
            && this.getAnhoFab() == coche2.getAnhoFab()) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Coche[matricula=" + getMatricula() + ",num_ruedas="
                + getNumRuedas() + ",num_puertas=" + getNumPuertas() +
                ",anho_fabricacion=" + getAnhoFab() + "]";
    }
}