package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Session session = null;

        // Configurar Hibernate y abrir una sesión
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            
            session = factory.getCurrentSession(); // se cierra automáticamente al salir del try-with-resources
            Transaction tx = session.beginTransaction();

            /********** GUARDAR **********/
            // Crear libro
            Libro libro1 = new Libro("Hibernate para DAM2", "Alumnado Dam2", 26);
            session.persist(libro1);

            // Crear usuario
            Usuario usuario1 = new Usuario("Cristina", "crispf@sanclemente.net");
            session.persist(usuario1);

            tx.commit(); // confirmamos la creación y cerramos la sesión

            System.out.println("Libro guardado: " + libro1 + " con éxito.");
            System.out.println("Usuario guardado: " + usuario1 + " con éxito.");

            /********** MODIFICAR **********/
            session = factory.getCurrentSession();
            tx = session.beginTransaction();

            libro1.setAutor("Antonio López");
            Libro mergedLibro = (Libro) session.merge(libro1); // actualizamos el libro en la base de datos
            
            tx.commit(); // confirmamos la modificación y cerramos la sesión
            
            System.out.println("Libro modificado: " + mergedLibro + " con éxito.");

            /********** ELIMINAR **********/
            session = factory.getCurrentSession();
            tx = session.beginTransaction();

            session.remove(mergedLibro); // eliminamos el libro de la base de datos

            tx.commit(); // confirmamos la eliminación y cerramos la sesión

            System.out.println("Libro eliminado: " + mergedLibro + " con éxito.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}