import java.util.Comparator;

public class Comparador implements Comparator<Personaje> {
    @Override
    public int compare(Personaje p1, Personaje p2) {
        if (p1.getVida()<p2.getVida()) return 1;
        if (p1.getVida()>p2.getVida()) return -1;
        return 0;
    }
    
}
