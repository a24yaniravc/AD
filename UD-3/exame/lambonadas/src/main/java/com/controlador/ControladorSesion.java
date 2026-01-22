package com.controlador;

/**
 * Clase ControladorSesion (Singleton) 
 * que permite la creación de Sesiones en la BD
 */
public class ControladorSesion {
    // Atributos
    private static ControladorSesion instancia;
    private HibernateUtil hibernateUtil;

    /**
     * Constructor privado ya que se trata de un Singleton
     */
    private ControladorSesion(){}

    /**
     * Método getControladorSesion que permite el acceso a la clase 
     * y la creación de su única instancia si aún no existe
     * 
     * Devuelve una instancia de la clase
     * @return 
     */
    public static ControladorSesion getControladorSesion() {
        if (instancia == null) {
            instancia = new ControladorSesion();
        }
        
        return instancia;
    }

    /**
     * Método que devuelve el HibernateUtil
     * @return
     */
    public HibernateUtil getHibernateUtil() {
        if (hibernateUtil == null){
            this.hibernateUtil = new HibernateUtil();
        }
        return hibernateUtil;
    }
}
