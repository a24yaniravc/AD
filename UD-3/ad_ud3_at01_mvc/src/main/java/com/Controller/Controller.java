package com.Controller;

import com.Model.RepositorioTareas;
import com.Model.Tarea;

public class Controller {
    public void listar() {
        RepositorioTareas.obtenerTodas();
    }

    public void agregar(String titulo, String descripcion) {
        Tarea t = new Tarea(titulo, descripcion);
        RepositorioTareas.agregar(t);
    }

    public void marcarCompletada(int id) {
        RepositorioTareas.marcarCompletada(id);
    }

    public void eliminar(int id) {
        RepositorioTareas.eliminar(id);
    }

    public void buscarID(int id) {
        RepositorioTareas.obtenerPorId(id);
    }
}
