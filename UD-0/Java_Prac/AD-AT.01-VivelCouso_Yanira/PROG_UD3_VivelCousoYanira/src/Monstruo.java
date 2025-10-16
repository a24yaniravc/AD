public class Monstruo {
    private String nombre;
    private int vida;
    private TipoMonstruo tipo;
    private int fuerza;

    public Monstruo(String nombre, int vida, TipoMonstruo tipo, int fuerza) {
        this.nombre = nombre;
        this.vida = Math.max(0, vida);
        this.tipo = tipo;
        this.fuerza = Math.max(0, fuerza);
    }

    public int getVida() { return vida; }
    public String getNombre() { return nombre; }
    public TipoMonstruo getTipo() { return tipo; }
    public int getFuerza() { return fuerza; }

    public boolean estaVivo() { return vida > 0; }

    public void atacar(Mago mago) {
        mago.recibirDanho(fuerza);
    }

    public void recibirDanho(int daño) {
        vida = Math.max(0, vida - daño);
    }
}
