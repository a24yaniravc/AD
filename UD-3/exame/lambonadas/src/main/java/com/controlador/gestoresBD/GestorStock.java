package com.controlador.gestoresBD;

import java.util.List;

import com.controlador.ControladorSesion;
import com.modelo.Producto;
import com.modelo.Stock;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class GestorStock {
    /**
     * Método insertarStock, inserta un nuevo Stock a la BD
     * @param cantidad
     * @param producto
     */
    public static void insertarStock(int cantidad, Producto producto) {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            Stock stock = new Stock(cantidad, producto);
            em.persist(stock);

            System.out.println("Nuevo stock añadido con éxito.");

            eTx.commit();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de insertar el stock: " + e);
        } finally {
            em.close();
        }
    }

    /**
     * Método eliminarStock, eliminar Stock de la BD
     * a través de su ID
     * @param id
     */
    public static void eliminarStock(int id) {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            Stock stock = em.find(Stock.class, id);

            // Comprobamos que exista
            if (stock != null) {
                em.remove(stock);
                System.out.println("El stock ha sido borrado con éxito.");
            } else {
                System.out.println("No existe ningun stock con esta ID en la base de datos.");
            }

            eTx.commit();

        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de eliminar el stock: " + e);
        } finally {
            em.close();
        }
    }

    /**
     * Método modificarStock, modifica Stock de la BD
     * a través de su ID
     * @param id
     * @param cantidad
     * @param producto
     */
    public static void modificarStock(int id, int cantidad, Producto producto) {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            Stock stock = em.find(Stock.class, id);

            if (stock != null) {
                stock.setCantidad(cantidad);
                stock.setProducto(producto);

                System.out.println("Se ha modificado el Stock con id " + id + " en la BD.");
            } else {
                System.out.println("No existe ningun Stock con esta ID en la base de datos.");
            }

            eTx.commit();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de modificar el stock: " + e);
        } finally {
            em.close();
        }
    }

    /**
     * Método modificarCantidadStock, modifica Stock de la BD
     * a través de su ID
     * @param id
     * @param cantidad
     * @param producto
     */
    public static void modificarCantidadStock(int id, int cantidad) {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            Stock stock = em.find(Stock.class, id);

            if (stock != null) {
                stock.setCantidad(cantidad);

                System.out.println("Se ha modificado la cantidad del Stock con id " + id + " en la BD.");
            } else {
                System.out.println("No existe ningun Stock con esta ID en la base de datos.");
            }

            eTx.commit();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de modificar el stock: " + e);
        } finally {
            em.close();
        }
    }

    /**
     * Método consultarStock, consulta Stock de la DB
     * a través de su ID
     * @param id
     */
    public static void consultarStock(int id) {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            Stock stock = em.find(Stock.class, id);

            if (stock != null) {
                System.out.println("Stock encontrado:");
                System.out.println(stock);
            } else {
                System.out.println("No existe ningun stock con esta ID en la base de datos.");
            }

            eTx.commit();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de consultar el stock: " + e);
        } finally {
            em.close();
        }
    }

    /**
     * Método consultarTodosStock, consulta todo el
     * Stock presente en la DB
     */
    public static void consultarTodosStock() {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            List<Stock> stocks = em.createQuery("FROM Stock", Stock.class).getResultList();

            if (!stocks.isEmpty()) {
                System.out.println("===== Stocks disponibles =====");

                for (Stock c : stocks) {
                    System.out.println(c);
                }
            } else {
                System.out.println("No existe ningun stock en la base de datos.");
            }

            eTx.commit();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de consultar los stocks: " + e);
        } finally {
            em.close();
        }
    }
}
