public class Mago extends Personaje {
    private int nivelMagia;
    private Hechizo hechizoFavorito;

    public Mago (String nombre, int vida, int nivelMagia, Hechizo hechizoFavorito) {
        super(nombre, vida);
        this.nivelMagia = Math.max(nivelMagia, 0);
        this.hechizoFavorito = hechizoFavorito;
    }

    public boolean estaVivo() { return getVida() > 0; }

    public void atacar(Monstruo monstruo) {
        monstruo.recibirDanho(nivelMagia);
    }

    public void aprenderHechizo(Hechizo hechizo) {
        this.hechizoFavorito = hechizo;
    }
}
