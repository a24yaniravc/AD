import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        // Crear algunos proveedores y productos de ejemplo
        Proveedor proveedor1 = new Proveedor("Zara", null);
        Proveedor proveedor2 = new Proveedor("Polinesia", "121231212");

        Producto producto1 = new Producto("Camiseta", 19.99, true, new String[] { "Ropa", "Verano", "Casual" }, 50,
                "Camiseta de algodón", proveedor1);
        Producto producto2 = new Producto("Pantalones", 39.99, true, new String[] { "Ropa", "Invierno", "Formal" }, 30,
                "Pantalones de mezclilla", proveedor2);

        // Añadir productos a una lista
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(producto1);
        productos.add(producto2);

        // Guardar y leer productos usando GestorProductos
        System.out.println("Test del gestor de productos:");
        GestorProductos.guardarProductos(productos);
        System.out.println("");
        GestorProductos.leerProductos();
        System.out.println("");
        GestorProductos.leerProductosArray();
    }
}
