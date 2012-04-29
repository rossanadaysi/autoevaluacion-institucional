/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Muestra;
import entity.Directorprograma;
import entity.Muestradirector;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Oscar
 */
public class MuestradirectorJpaController implements Serializable {

    public MuestradirectorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Muestradirector muestradirector) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Muestra muestraId = muestradirector.getMuestraId();
            if (muestraId != null) {
                muestraId = em.getReference(muestraId.getClass(), muestraId.getId());
                muestradirector.setMuestraId(muestraId);
            }
            Directorprograma directorprogramaId = muestradirector.getDirectorprogramaId();
            if (directorprogramaId != null) {
                directorprogramaId = em.getReference(directorprogramaId.getClass(), directorprogramaId.getId());
                muestradirector.setDirectorprogramaId(directorprogramaId);
            }
            em.persist(muestradirector);
            if (muestraId != null) {
                muestraId.getMuestradirectorList().add(muestradirector);
                muestraId = em.merge(muestraId);
            }
            if (directorprogramaId != null) {
                directorprogramaId.getMuestradirectorList().add(muestradirector);
                directorprogramaId = em.merge(directorprogramaId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Muestradirector muestradirector) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Muestradirector persistentMuestradirector = em.find(Muestradirector.class, muestradirector.getId());
            Muestra muestraIdOld = persistentMuestradirector.getMuestraId();
            Muestra muestraIdNew = muestradirector.getMuestraId();
            Directorprograma directorprogramaIdOld = persistentMuestradirector.getDirectorprogramaId();
            Directorprograma directorprogramaIdNew = muestradirector.getDirectorprogramaId();
            if (muestraIdNew != null) {
                muestraIdNew = em.getReference(muestraIdNew.getClass(), muestraIdNew.getId());
                muestradirector.setMuestraId(muestraIdNew);
            }
            if (directorprogramaIdNew != null) {
                directorprogramaIdNew = em.getReference(directorprogramaIdNew.getClass(), directorprogramaIdNew.getId());
                muestradirector.setDirectorprogramaId(directorprogramaIdNew);
            }
            muestradirector = em.merge(muestradirector);
            if (muestraIdOld != null && !muestraIdOld.equals(muestraIdNew)) {
                muestraIdOld.getMuestradirectorList().remove(muestradirector);
                muestraIdOld = em.merge(muestraIdOld);
            }
            if (muestraIdNew != null && !muestraIdNew.equals(muestraIdOld)) {
                muestraIdNew.getMuestradirectorList().add(muestradirector);
                muestraIdNew = em.merge(muestraIdNew);
            }
            if (directorprogramaIdOld != null && !directorprogramaIdOld.equals(directorprogramaIdNew)) {
                directorprogramaIdOld.getMuestradirectorList().remove(muestradirector);
                directorprogramaIdOld = em.merge(directorprogramaIdOld);
            }
            if (directorprogramaIdNew != null && !directorprogramaIdNew.equals(directorprogramaIdOld)) {
                directorprogramaIdNew.getMuestradirectorList().add(muestradirector);
                directorprogramaIdNew = em.merge(directorprogramaIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = muestradirector.getId();
                if (findMuestradirector(id) == null) {
                    throw new NonexistentEntityException("The muestradirector with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Muestradirector muestradirector;
            try {
                muestradirector = em.getReference(Muestradirector.class, id);
                muestradirector.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The muestradirector with id " + id + " no longer exists.", enfe);
            }
            Muestra muestraId = muestradirector.getMuestraId();
            if (muestraId != null) {
                muestraId.getMuestradirectorList().remove(muestradirector);
                muestraId = em.merge(muestraId);
            }
            Directorprograma directorprogramaId = muestradirector.getDirectorprogramaId();
            if (directorprogramaId != null) {
                directorprogramaId.getMuestradirectorList().remove(muestradirector);
                directorprogramaId = em.merge(directorprogramaId);
            }
            em.remove(muestradirector);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Muestradirector> findMuestradirectorEntities() {
        return findMuestradirectorEntities(true, -1, -1);
    }

    public List<Muestradirector> findMuestradirectorEntities(int maxResults, int firstResult) {
        return findMuestradirectorEntities(false, maxResults, firstResult);
    }

    private List<Muestradirector> findMuestradirectorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Muestradirector.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Muestradirector findMuestradirector(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Muestradirector.class, id);
        } finally {
            em.close();
        }
    }

    public int getMuestradirectorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Muestradirector> rt = cq.from(Muestradirector.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
