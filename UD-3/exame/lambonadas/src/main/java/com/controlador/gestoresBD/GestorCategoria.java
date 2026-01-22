package com.controlador.gestoresBD;

import java.util.List;

import com.controlador.ControladorSesion;
import com.modelo.Categoria;
import com.modelo.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

/**
 * Clase GestorCategoria que efectua los cambios
 * sobre la tabla Categoria en la BD
 */
public class GestorCategoria {
    /**
     * Método insertarCategoria, inserta una nueva Categoria a la BD
     * 
     * @param nombre
     * @param productos
     */
    public static void insertarCategoria(String nombre, List<Producto> productos) {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            Categoria categoria = new Categoria(nombre, productos);
            em.persist(categoria);

            eTx.commit();

            System.out.println("La categoría " + nombre + " ha sido insertada con éxito.");
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de insertar la Categoria: " + e);
        } finally {
            em.close();
        }
    }

    /**
     * Método eliminarCategoria, elimina una categoria de la BD
     * a través de su ID
     * @param id
     */
    public static void eliminarCategoria(int id) {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            Categoria categoria = em.find(Categoria.class, id);

            // Comprobamos que exista
            if (categoria != null) {
                String nombre = categoria.getNombre();

                em.remove(categoria);
                System.out.println("La categoría " + nombre + " ha sido borrada con éxito.");
            } else {
                System.out.println("No existe ninguna categoría con esta ID en la base de datos.");
            }

            eTx.commit();

        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de eliminar la Categoria: " + e);
        } finally {
            em.close();
        }
    }

    /**
     * Método modificarCategoria, modificar todos los parámetros de una categoria
     * accedida a través de su ID
     * @param id
     * @param nombre
     * @param productos
     */
    public static void modificarCategoria(int id, String nombre, List<Producto> productos) {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();
        String nombreAnterior;

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            Categoria categoria = em.find(Categoria.class, id);

            if (categoria != null) {
                nombreAnterior = categoria.getNombre();

                categoria.setNombre(nombre);
                categoria.setProductos(productos);

                System.out.println("La categoría " + nombreAnterior + " ha sido modificada con éxito. Ahora se ve así: "
                        + categoria);
            } else {
                System.out.println("No existe ninguna categoría con esta ID en la base de datos.");
            }
            
            eTx.commit();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de modificar la Categoria: " + e);
        } finally {
            em.close();
        }
    }

    /**
     * Método consultarCategoria, consulta una Categoría
     * a través de su ID
     * @param id
     */
    public static void consultarCategoria(int id) {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            Categoria categoria = em.find(Categoria.class, id);

            if (categoria != null) {
                System.out.println("Categoria encontrada:");
                System.out.println(categoria);
            } else {
                System.out.println("No existe ninguna categoría con esta ID en la base de datos.");
            }


            eTx.commit();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de consultar la Categoria: " + e);
        } finally {
            em.close();
        }
    }

    /**
     * Método consultarTodasCategorias, consulta todas las categorías
     * presentes en la BD
     */
    public static void consultarTodasCategorias() {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            List<Categoria> categorias = em.createQuery("FROM Categoria", Categoria.class).getResultList();

            if (!categorias.isEmpty()) {
                System.out.println("===== Categorias disponibles =====");

                for (Categoria c : categorias) {
                    System.out.println(c);
                }
            } else {
                System.out.println("No existe ninguna categoría en la base de datos.");
            }

            eTx.commit();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de consultar la Categorias: " + e);
        } finally {
            em.close();
        }
    }
}
