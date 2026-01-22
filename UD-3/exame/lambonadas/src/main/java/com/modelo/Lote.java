package com.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase que representa las entidades presentes dentro de la tabla 'lotes' en la BD
 * Además, representa un lote de un Producto
 */

@Entity
@Table(name="lotes")
public class Lote {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int cantidad;
    String fechaEntrada;
    String fechaCaducidad;

    /**
     * Constructor Lote vacío para Hibernate
     */
    public Lote() {}

    /**
     * Constructor principal Lote
     * @param cantidad
     * @param fechaEntrada
     * @param fechaCaducidad
     */
    public Lote(int cantidad, String fechaEntrada, String fechaCaducidad) {
        this.cantidad = cantidad;
        this.fechaEntrada = fechaEntrada;
        this.fechaCaducidad = fechaCaducidad;
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
     * Método getCantidad, devuelve la cantidad
     * @return
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Méotodo getFechaEntrada, devuelve la fecha de entrada
     * @return
     */
    public String getFechaEntrada() {
        return fechaEntrada;
    }

    /**
     * Método getFechaCaducidad, devuelve la fecha de caducidad
     * @return
     */
    public String getFechaCaducidad() {
        return fechaCaducidad;
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
     * Método setFechaEntrada, cambia la fecha de entrada
     * @param fechaEntrada
     */
    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    /**
     * Método setFechaCaducidad, cambia la fecha de caducidad
     * @param fechaCaducidad
     */
    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    /**
     * Método toString
     */
    @Override
    public String toString() {
        return "Lote [id=" + id + ", cantidad=" + cantidad + ", fechaEntrada=" + fechaEntrada + ", fechaCaducidad="
                + fechaCaducidad + "]";
    }
}
