package com.controlador.gestoresBD;

import java.util.List;

import com.controlador.ControladorSesion;
import com.modelo.Categoria;
import com.modelo.Lote;
import com.modelo.Producto;
import com.modelo.Stock;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class GestorProducto {
    /**
     * Método insertarProducto, inserta un nuevo producto a la BD
     * 
     * @param nombre
     * @param peso
     * @param precio
     * @param lotes
     */
    public static void insertarProducto(String nombre, Double peso, Double precio, List<Lote> lotes) {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            Producto producto = new Producto(nombre, peso, precio, lotes);
            em.persist(producto);

            System.out.println("Nuevo producto " + nombre + " añadido con éxito.");

            eTx.commit();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de insertar el producto: " + e);
        } finally {
            em.close();
        }
    }

    /**
     * Método eliminarProducto, elimina un Producto de la BD
     * a través de su ID
     * 
     * @param id
     */
    public static void eliminarProducto(int id) {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            Producto producto = em.find(Producto.class, id);

            // Comprobamos que exista
            if (producto != null) {
                em.remove(producto);
                System.out.println("El producto ha sido borrado con éxito.");
            } else {
                System.out.println("No existe ningun producto con esta ID en la base de datos.");
            }

            eTx.commit();

        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de eliminar el producto: " + e);
        } finally {
            em.close();
        }
    }

    /**
     * Método modificarProducto, modifica todo un Producto de una BD
     * a través de su ID
     * 
     * @param id
     * @param nombre
     * @param peso
     * @param precio
     * @param lotes
     */
    public static void modificarProducto(int id, String nombre, Double peso, Double precio, List<Lote> lotes) {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            Producto producto = em.find(Producto.class, id);

            if (producto != null) {
                producto.setNombre(nombre);
                producto.setPeso(peso);
                producto.setPrecio(precio);
                producto.setLotes(lotes);

                System.out.println("Se ha modificado el producto con id " + id + " en la BD.");
            } else {
                System.out.println("No existe ningun producto con esta ID en la base de datos.");
            }

            eTx.commit();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de modificar el producto: " + e);
        } finally {
            em.close();
        }
    }

    /**
     * Método modificarLotesProducto, modifica los lotes de un Producto de una BD
     * a través de su ID
     * 
     * @param lote
     */
    public static void modificarLotesProducto(int id, Lote lote) {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            Producto producto = em.find(Producto.class, id);

            if (producto != null) {
                List<Lote> lotes = producto.getLotes();
                lotes.add(lote);

                producto.setLotes(lotes);

                System.out.println("Se ha modificado el producto con id " + id + " en la BD.");
            } else {
                System.out.println("No existe ningun producto con esta ID en la base de datos.");
            }

            eTx.commit();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de modificar el producto: " + e);
        } finally {
            em.close();
        }
    }

    /**
     * Método consultarProducto, consulta un Producto de la BD
     * a través de su ID
     * 
     * @param id
     */
    public static void consultarProducto(int id) {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            Producto producto = em.find(Producto.class, id);

            if (producto != null) {
                System.out.println("Producto encontrado:");
                System.out.println(producto);

                System.out.print("Cantidad total de stock: ");
                Stock stock = em.find(null, producto.getId());
                System.out.println(stock.getCantidad());

                System.out.print("Número de lotes: ");
                System.out.println(producto.getLotes().size());

                System.out.println("Nombre de la categoría: ");
                Categoria categoria = em.find(Categoria.class, producto);
                if (categoria != null) {
                    System.out.println(categoria.getNombre());
                } else {
                    System.out.println("null");
                }
            } else {
                System.out.println("No existe ningun producto con esta ID en la base de datos.");
            }

            eTx.commit();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de consultar el producto: " + e);
        } finally {
            em.close();
        }
    }

    /**
     * Método consultarTodosProductos, consulta todos los
     * Productos de la DB
     */
    public static void consultarTodosProductos() {
        EntityManager em = ControladorSesion.getControladorSesion().getHibernateUtil().getEntityManager();

        try {
            EntityTransaction eTx = em.getTransaction();
            eTx.begin();

            List<Producto> productos = em.createQuery("FROM Producto", Producto.class).getResultList();

            if (!productos.isEmpty()) {
                System.out.println("===== Productos disponibles =====");

                for (Producto c : productos) {
                    System.out.println(c);
                }
            } else {
                System.out.println("No existe ningun producto en la base de datos.");
            }

            eTx.commit();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error a la hora de consultar los productos: " + e);
        } finally {
            em.close();
        }
    }
}
