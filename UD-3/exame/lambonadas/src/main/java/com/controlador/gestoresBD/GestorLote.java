package com.controlador.gestoresBD;

import java.util.List;

import com.controlador.ControladorSesion;
import com.modelo.Lote;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

/**
 * Clase GestorLote que efectua los cambios
 * sobre la tabla Lotes en la BD
 */
public class GestorLote {
    /**
     * Método insertarLote, inserta una nueva Lote a la BD
     * @param cantidad
     * @param fechaEntrada
     * @param fechaCaducidad
     */
    public static void insertarLote(int cantidad, String fechaEntrada, String fechaCaducidad) {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            Lote lote = new Lote(cantidad, fechaEntrada, fechaCaducidad);
            em.persist(lote);

            eTx.commit();

            System.out.println("Nuevo lote añadido con éxito.");
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de insertar el lote: " + e);
        } finally {
            em.close();
        }
    }

    /**
     * Método eliminarLote, elimina un Lote de la BD
     * a través de su ID
     * @param id
     */
    public static void eliminarLote(int id) {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            Lote lote = em.find(Lote.class, id);

            // Comprobamos que exista
            if (lote != null) {
                em.remove(lote);
                System.out.println("El lote ha sido borrado con éxito.");
            } else {
                System.out.println("No existe ningun lote con esta ID en la base de datos.");
            }

            eTx.commit();

        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de eliminar el lote: " + e);
        } finally {
            em.close();
        }
    }

    /**
     * Método modificarLote, modificar un Lote de la BD
     * a través de su ID
     * @param id
     * @param cantidad
     * @param fechaEntrada
     * @param fechaCaducidad
     */
    public static void modificarLote(int id, int cantidad, String fechaEntrada, String fechaCaducidad) {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            Lote lote = em.find(Lote.class, id);

            if (lote != null) {
                lote.setCantidad(cantidad);
                lote.setFechaEntrada(fechaEntrada);
                lote.setFechaCaducidad(fechaCaducidad);

                System.out.println("Se ha modificado el lote con id " + id + " en la BD.");
            } else {
                System.out.println("No existe ningun lote con esta ID en la base de datos.");
            }
            
            eTx.commit();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de modificar el lote: " + e);
        } finally {
            em.close();
        }
    }

    /**
     * Método consultarLote, consulta un Lote de la BD
     * a través de su ID
     * @param id
     */
    public static void consultarLote(int id) {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            Lote lote = em.find(Lote.class, id);

            if (lote != null) {
                System.out.println("Lote encontrado:");
                System.out.println(lote);
            } else {
                System.out.println("No existe ningun lote con esta ID en la base de datos.");
            }


            eTx.commit();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de consultar el lote: " + e);
        } finally {
            em.close();
        }
    }

    /**
     * Método consultarTodosLotes, consulta todos los Lotes
     * disponibles en la BD
     */
    public static void consultarTodosLotes() {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            List<Lote> lotes = em.createQuery("FROM Lote", Lote.class).getResultList();

            if (!lotes.isEmpty()) {
                System.out.println("===== Lotes disponibles =====");

                for (Lote c : lotes) {
                    System.out.println(c);
                }
            } else {
                System.out.println("No existe ningun lote en la base de datos.");
            }

            eTx.commit();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de consultar los lotes: " + e);
        } finally {
            em.close();
        }
    }
}
