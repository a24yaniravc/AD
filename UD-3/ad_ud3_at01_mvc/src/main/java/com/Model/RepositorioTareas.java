package com.Model;

import java.util.List;

public class RepositorioTareas {
    private static RepositorioTareas instancia;
    private List<Tarea> tareas = new java.util.ArrayList<>();

    // Constructor privado para implementar el patrón Singleton
    private RepositorioTareas() {

    }

    // Método para obtener la instancia única de RepositorioTareas
    public static RepositorioTareas getInstancia() {
        if (instancia == null) {
            synchronized (RepositorioTareas.class) {
                if (instancia == null) {
                    instancia = new RepositorioTareas();
                }
            }
        }
        return instancia;
    }

    /**
     * Obtiene todas las tareas almacenadas en el repositorio.
     * @return
     */
    public static List<Tarea> obtenerTodas() {
        return getInstancia().tareas;
    }

    /**
     * Obtiene una tarea por su ID.
     * @param id
     * @return
     */
    public static Tarea obtenerPorId(int id) {
        Tarea tareaEncontrada = null;
        
        for (Tarea t : getInstancia().tareas) {
            if (t.getId() == id) {
                tareaEncontrada = t;
            }
        }
        return tareaEncontrada;
    }

    /**
     * Agrega una nueva tarea al repositorio.
     * @param t
     */
    public static void agregar(Tarea t) {
        getInstancia().tareas.add(t);

        System.out.println("Agregada tarea: " + t.toString());
    }


    /**
     * Elimina una tarea del repositorio por su ID.
     * @param id
     * @return
     */
    public static Boolean eliminar(int id) {
        Boolean respuesta = false;

        Tarea tareaAEliminar = obtenerPorId(id);
        if (tareaAEliminar != null) {
            getInstancia().tareas.remove(tareaAEliminar);
            respuesta = true;
        }
        return respuesta;
    }

    /**
     * Marca una tarea como completada por su ID.
     * @param id
     */
    public static void marcarCompletada(int id) {
        Tarea tareaAMarcar = obtenerPorId(id);
        if (tareaAMarcar != null) {
            tareaAMarcar.setCompletada(true);
        }
    }
}
