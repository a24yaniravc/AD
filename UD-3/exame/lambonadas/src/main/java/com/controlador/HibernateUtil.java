package com.controlador;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Clase HybernateUtil que linkea la BD con la lógica Java a través de Hibernate
 */
public class HibernateUtil {
    // Atributos
    private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("lambonadasServizo");

    /**
     * Getter que devulve el EntityManager
     * @return
     */
    public EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    /**
     * Método que permite cerrar el EntityManagerFactory
     */
    public void closeEntityManagerFactory() {
        if (emFactory.isOpen()) {
            emFactory.close();
        }
    }
}
