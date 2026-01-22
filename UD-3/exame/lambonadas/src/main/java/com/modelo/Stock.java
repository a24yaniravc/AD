package com.modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Clase que representa las entidades presentes dentro de la tabla 'stock' en la BD
 * Además, representa el stock de un Producto
 */

@Entity
@Table(name = "stock")
public class Stock {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int cantidad;

    // Relaciones BD
    @OneToOne(cascade = CascadeType.ALL)
    Producto producto;

    /**
     * Constructor Stock vacío para Hibernate
     */
    public Stock() {}

    /**
     * Constructor principal Stock
     * @param cantidad
     * @param producto
     */
    public Stock(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    // GETTERS

    /**
     * Método getId, devuelve la ID
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Método getCantidad, devuelve la Cantidad
     * @return
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Método getProducto, devuelve el Producto
     * @return
     */
    public Producto getProducto() {
        return producto;
    }

    // SETTERS

    /**
     * Método setId, cambia la ID
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método setCantidad, cambia la cantidad
     * @param cantidad
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Método setProducto, cambia el producto al que pertenece el stock
     * @param producto
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Método toString
     */
    @Override
    public String toString() {
        return "Stock [id=" + id + ", cantidad=" + cantidad + ", producto=" + producto + "]";
    }
}
