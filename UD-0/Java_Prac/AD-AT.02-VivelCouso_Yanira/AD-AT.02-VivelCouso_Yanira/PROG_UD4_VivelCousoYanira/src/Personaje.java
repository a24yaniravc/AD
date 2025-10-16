public class Personaje {
    private String nombre;
    private int vida;

    public Personaje (String nombre, int vida) {
        this.nombre = nombre;
        this.vida = Math.max(vida, 0);
    }

    public String getNombre() { return nombre; }
    public int getVida() { return vida; }

    public void recibirDanho(int daño) {
        vida = Math.max(0, vida - daño);
    }

    @Override
    public String toString() {
        return "Personaje [nombre =" + nombre + ", vida=" + vida + "]";
    }    
}
