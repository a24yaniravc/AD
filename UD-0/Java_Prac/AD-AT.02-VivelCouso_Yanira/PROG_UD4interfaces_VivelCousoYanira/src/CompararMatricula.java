import java.util.Comparator;

public class CompararMatricula implements Comparator<Vehiculo>{
    @Override
    public int compare(Vehiculo coche1, Vehiculo coche2) {
        if (coche1 instanceof Coche && coche2 instanceof Coche) {
            return ((Coche) coche1).getMatricula().compareTo(((Coche) coche2).getMatricula()); 
        } else {
            return 0;
        } 
    }
}
