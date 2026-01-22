package com;

import com.controlador.gestoresBD.GestorLote;
import com.controlador.gestoresBD.GestorProducto;
import com.controlador.gestoresBD.GestorStock;
import com.modelo.Lote;
import com.modelo.Producto;

/**
 * Clase principal que ejecuta el código
 */
public class Principal {
    public static void main(String[] args) {
        Producto producto = new Producto("Ositos Haribo", 100.0, 1.5, null);
        Lote lote1 = new Lote(20, null, null);
        Lote lote2 = new Lote(30, null, null);

        // Insertar producto
        GestorProducto.insertarProducto(producto.getNombre(), producto.getPeso(), producto.getPrecio(), producto.getLotes());
        // Insertar Stock de dicho producto
        GestorStock.insertarStock(lote1.getCantidad(), producto);

        // Insertar Lote número 1
        GestorLote.insertarLote(lote1.getCantidad(), lote1.getFechaEntrada(), lote1.getFechaCaducidad());
        GestorStock.modificarCantidadStock(producto.getId(), lote1.getCantidad()); // Modificar Stock
        
        // Insertar Lote número 2
        GestorLote.insertarLote(lote2.getCantidad(), lote2.getFechaEntrada(), lote2.getFechaCaducidad());
        GestorStock.modificarCantidadStock(producto.getId(), lote1.getCantidad() + lote2.getCantidad()); // Modificar Stock

        // Agregar lotes a Producto
        GestorProducto.modificarLotesProducto(producto.getId(), lote1);
        GestorProducto.modificarLotesProducto(producto.getId(), lote2);

        //GestorProducto.consultarProducto(producto.getId()); // Consultar producto

        GestorProducto.eliminarProducto(producto.getId()); // Eliminar producto
    }
}