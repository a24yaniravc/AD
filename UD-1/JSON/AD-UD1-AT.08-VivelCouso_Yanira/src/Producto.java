public class Producto {
    // Atributos
    private String nombre;
    private double precio;
    private boolean disponible;
    private String[] categorias;
    private int stock;
    private String descripcion;
    private Proveedor proveedor;

    // Constructores
    public Producto(String nombre, double precio, boolean disponible, String[] categorias, int stock,
            String descripcion, Proveedor proveedor) {
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = disponible;
        if (categorias.length != 3) {
            throw new IllegalArgumentException("Un producto debe tener exactamente 3 categorías.");
        }
        this.categorias = categorias;
        this.stock = stock;
        this.descripcion = descripcion;
        this.proveedor = proveedor;
    }

    // Getters y Setters
    public Producto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public String[] getCategorias() {
        return categorias;
    }

    public int getStock() {
        return stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    @Override
    public String toString() {
        return "Producto[" +
                "Nombre='" + nombre + '\'' +
                ", Precio=" + precio +
                ", Disponible=" + disponible +
                ", Categorias={" + String.join(", ", categorias) +
                "}, Stock=" + stock +
                ", Descripcion='" + descripcion + '\'' +
                ", Proveedor{" + (proveedor != null ? proveedor.toString() : "null") + // Manejo de null
                "}]";
    }
}
