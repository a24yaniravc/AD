package com.example;

import jakarta.persistence.*;

@Entity
@Table(name = "libros") // Define el nombre de la tabla en la base de datos
public class Libro {
    @Id // Define la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autogeneración del ID
    private int id;

    private String titulo;
    private String autor;
    private int paginas;

    public Libro() {
    }

    public Libro(String titulo, String autor, int paginas) {
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    @Override
    public String toString() {
        return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", paginas=" + paginas + "]";
    }
}
