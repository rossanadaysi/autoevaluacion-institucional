/*
 * Created on 26-feb-2005
 *
 */
package connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Marco Gonzalez
 *
 */
public final class jpaConnection {

    private static EntityManagerFactory emf;

    public static void createEntityManagerFactory() {

        try {
            emf = Persistence.createEntityManagerFactory("AutoevaluacionInstitucionalPU");
           // System.out.println("N O T A: EMF CREADO");
        } catch (Exception e) {
            System.err.println("E R R O R: ERROR AL CREAR EMF");
        }

    }

    public static EntityManager getEntityManager() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
          // System.out.println("N O T A: EM CREADO");
        } catch (Exception e) {
            System.err.println("E R R O R:  ERROR AL CREAR EM");
        }
        return em;
    }
}
