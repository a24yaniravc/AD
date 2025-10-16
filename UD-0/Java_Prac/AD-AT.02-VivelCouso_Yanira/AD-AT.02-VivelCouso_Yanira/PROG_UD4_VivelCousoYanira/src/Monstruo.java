public class Monstruo extends Personaje {
    private TipoMonstruo tipo;
    private int fuerza;

    public Monstruo(String nombre, int vida, TipoMonstruo tipo, int fuerza) {
        super(nombre, vida);
        this.tipo = tipo;
        this.fuerza = Math.max(0, fuerza);
    }

    public TipoMonstruo getTipo() { return tipo; }
    public int getFuerza() { return fuerza; }

    public boolean estaVivo() { return getVida() > 0; }

    public void atacar(Mago mago) {
        mago.recibirDanho(fuerza);
    }
}
