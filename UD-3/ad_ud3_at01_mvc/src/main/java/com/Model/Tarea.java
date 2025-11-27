package com.Model;

import java.util.List;

public class Tarea {
    private int id; // Autoincrimentable
    private String titulo;
    private String descripcion;
    private boolean completada;

    // Constructor
    public Tarea(String titulo, String descripcion) {
        List<Tarea> tareas = RepositorioTareas.obtenerTodas(); 
        
        // Asignar ID autoincrementable
        if (tareas.isEmpty()) {
            this.id = 1;
        } else {
            for (Tarea t : tareas) {
                if (t.getId() >= this.id) {
                    this.id = t.getId() + 1;
                }
            }
        }
        
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.completada = false;
    }

    // Getters y Setters

    // Getter ID
    public int getId() {
        return id;
    }

    // ID es autoincrementable
    // por lo que no se proporciona un setter para él


    // Getter Titulo
    public String getTitulo() {
        return titulo;
    }

    // Setter Titulo
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Getter Descripcion
    public String getDescripcion() {
        return descripcion;
    }

    // Setter Descripcion
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getter Completada
    public boolean isCompletada() {
        return completada;
    }

    // Setter Completada
    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    // Método toString para representar la tarea como una cadena
    @Override
    public String toString() {
        return "Tarea [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", completada=" + completada
                + "]";
    }
}
