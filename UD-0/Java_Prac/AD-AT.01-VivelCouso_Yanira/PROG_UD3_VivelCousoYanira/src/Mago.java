public class Mago {
    private String nombre;
    private int vida;
    private int nivelMagia;
    private Hechizo hechizoFavorito;

    public Mago (String nombre, int vida, int nivelMagia, Hechizo hechizoFavorito) {
        this.nombre = nombre;
        this.vida = Math.max(vida, 0);
        this.nivelMagia = Math.max(nivelMagia, 0);
        this.hechizoFavorito = hechizoFavorito;
    }

    public int getVida() { return vida; }
    public String getNombre() { return nombre; }
    public boolean estaVivo() { return vida > 0; }

    public void recibirDanho(int daño) {
        vida = Math.max(0, vida - daño);
    }

    public void lanzarHechizo(Monstruo monstruo) {
        monstruo.recibirDanho(nivelMagia);
    }

    public void aprenderHechizo(Hechizo hechizo) {
        this.hechizoFavorito = hechizo;
    }
}
