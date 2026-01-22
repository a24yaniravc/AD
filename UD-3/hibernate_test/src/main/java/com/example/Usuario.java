package com.example;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios") // Define el nombre de la tabla en la base de datos
public class Usuario {
    @Id // Define la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autogeneración del ID
    private int id;

    private String nombre;
    private String email;

    public Usuario() {
    }

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email + "]";
    }
}
