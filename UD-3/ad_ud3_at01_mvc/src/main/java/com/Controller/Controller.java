package com.Controller;

import java.util.List;

import com.Model.RepositorioTareas;
import com.Model.Tarea;

public class Controller {
    /**
     * Lista todas las tareas del repositorio.
     */
    public List<Tarea> listar() {
        return RepositorioTareas.obtenerTodas();
    }

    /**
     * Agrega una nueva tarea al repositorio.
     * @param titulo
     * @param descripcion
     */
    public void agregar(String titulo, String descripcion) {
        Tarea t = new Tarea(titulo, descripcion);
        RepositorioTareas.agregar(t);
    }

    /**
     * Marca una tarea como completada por su ID.
     * @param id
     */
    public void marcarCompletada(int id) {
        RepositorioTareas.marcarCompletada(id);
    }

    /**
     * Elimina una tarea del repositorio por su ID.
     * @param id
     */
    public void eliminar(int id) {
        RepositorioTareas.eliminar(id);
    }

    /**
     * Busca una tarea por su ID.
     * @param id
     * @return
     */
    public Tarea buscarID(int id) {
        return RepositorioTareas.obtenerPorId(id);
    }
}
