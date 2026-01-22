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
 * Clase que representa las entidades presentes dentro de la tabla 'categorias' en la BD
 * Además, representa una categoría
 */

@Entity
@Table(name="categorias")
public class Categoria {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String nombre;

    // Relaciones BD
    @OneToMany(cascade = CascadeType.ALL)
    List<Producto> productos;

    /**
     * Constructor Categoria vacío para Hibernate
     */
    public Categoria() {}

    /**
     * Constructor principal Categoria
     * @param nombre
     * @param productos
     */
    public Categoria(String nombre, List<Producto> productos) {
        this.nombre = nombre;
        this.productos = productos;
    }

    // Getters

    /**
     * Método getId, devuelve la ID
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Método getNombre, devuelve el Nombre
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método getProductos, devuelve la lista de productos
     * @return
     */
    public List<Producto> getProductos() {
        return productos;
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
     * Método setNombre, cambia el Nombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método setProductos, cambia la lista de Productos
     * @param productos
     */
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    /**
     * Método toString
     */
    @Override
    public String toString() {
        return "Categoria [id=" + id + ", nombre=" + nombre + ", productos=" + productos + "]";
    }
}
