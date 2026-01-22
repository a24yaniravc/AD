package com.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Clase que representa las entidades presentes dentro de la tabla 'productos' en la BD
 * Además, representa un producto
 */

@Entity
@Table(name="productos")
public class Producto {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String nombre;
    Double peso;
    Double precio;

    // Relaciones BD
    @OneToMany(cascade = CascadeType.ALL)
    List<Lote> lotes;

    /**
     * Constructor Producto vacío para Hibernate
     */
    public Producto() {}

    /**
     * Constructor principal Producto
     * @param nombre
     * @param peso
     * @param precio
     * @param lotes
     */
    public Producto(String nombre, Double peso, Double precio, List<Lote> lotes) {
        this.nombre = nombre;
        this.peso = peso;
        this.precio = precio;
        this.lotes = lotes;
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
     * Método getNombre, devuelve el nombre
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método getPeso, devuelve el peso
     * @return
     */
    public Double getPeso() {
        return peso;
    }

    /**
     * Método getPrecio, devuelve el precio
     * @return
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Método getLotes, devuelve la lista de lotes
     * @return
     */
    public List<Lote> getLotes() {
        return lotes;
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
     * Método setNombre, cambia el nombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método setPeso, cambia el peso
     * @param peso
     */
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    /**
     * Método setPrecio, cambia el precio
     * @param precio
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * Método setLotes, cambia la lista de lotes
     * @param lotes
     */
    public void setLotes(List<Lote> lotes) {
        this.lotes = lotes;
    }

    /**
     * Método toString
     */
    @Override
    public String toString() {
        return "Producto [id=" + id + ", nombre=" + nombre + ", peso=" + peso + ", precio=" + precio + ", lotes="
                + lotes + "]";
    }
}
