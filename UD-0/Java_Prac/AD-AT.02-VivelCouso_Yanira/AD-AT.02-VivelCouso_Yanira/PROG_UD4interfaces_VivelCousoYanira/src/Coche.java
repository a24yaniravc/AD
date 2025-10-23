import java.util.Comparator;

public class Coche extends Vehiculo {
    int num_puertas;

    Coche (String matricula, int num_ruedas, int anho_fabricacion, int num_puertas) {
        super(matricula, num_ruedas, anho_fabricacion);
        this.num_puertas = num_puertas;
    }

    public int compareTo(Coche v2) {
        if (this.num_puertas>v2.num_puertas) return 1;
        if (this.num_puertas<v2.num_puertas) return -1;
        return 0;
    }
}

/*class ComparaMatricula implements Comparator<Coche>{
    @Override
    public int compare(Coche c1, Coche c2) {
        if(c1.matricula.compareTo(c2.matricula)) return 1;
        return 1;
    }
}*/

