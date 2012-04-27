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
import entity.Empleador;
import entity.Muestra;
import entity.Muestraempleador;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ususario
 */
public class MuestraempleadorJpaController implements Serializable {

    public MuestraempleadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Muestraempleador muestraempleador) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleador empleadorId = muestraempleador.getEmpleadorId();
            if (empleadorId != null) {
                empleadorId = em.getReference(empleadorId.getClass(), empleadorId.getId());
                muestraempleador.setEmpleadorId(empleadorId);
            }
            Muestra muestraId = muestraempleador.getMuestraId();
            if (muestraId != null) {
                muestraId = em.getReference(muestraId.getClass(), muestraId.getId());
                muestraempleador.setMuestraId(muestraId);
            }
            em.persist(muestraempleador);
            if (empleadorId != null) {
                empleadorId.getMuestraempleadorList().add(muestraempleador);
                empleadorId = em.merge(empleadorId);
            }
            if (muestraId != null) {
                muestraId.getMuestraempleadorList().add(muestraempleador);
                muestraId = em.merge(muestraId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Muestraempleador muestraempleador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Muestraempleador persistentMuestraempleador = em.find(Muestraempleador.class, muestraempleador.getId());
            Empleador empleadorIdOld = persistentMuestraempleador.getEmpleadorId();
            Empleador empleadorIdNew = muestraempleador.getEmpleadorId();
            Muestra muestraIdOld = persistentMuestraempleador.getMuestraId();
            Muestra muestraIdNew = muestraempleador.getMuestraId();
            if (empleadorIdNew != null) {
                empleadorIdNew = em.getReference(empleadorIdNew.getClass(), empleadorIdNew.getId());
                muestraempleador.setEmpleadorId(empleadorIdNew);
            }
            if (muestraIdNew != null) {
                muestraIdNew = em.getReference(muestraIdNew.getClass(), muestraIdNew.getId());
                muestraempleador.setMuestraId(muestraIdNew);
            }
            muestraempleador = em.merge(muestraempleador);
            if (empleadorIdOld != null && !empleadorIdOld.equals(empleadorIdNew)) {
                empleadorIdOld.getMuestraempleadorList().remove(muestraempleador);
                empleadorIdOld = em.merge(empleadorIdOld);
            }
            if (empleadorIdNew != null && !empleadorIdNew.equals(empleadorIdOld)) {
                empleadorIdNew.getMuestraempleadorList().add(muestraempleador);
                empleadorIdNew = em.merge(empleadorIdNew);
            }
            if (muestraIdOld != null && !muestraIdOld.equals(muestraIdNew)) {
                muestraIdOld.getMuestraempleadorList().remove(muestraempleador);
                muestraIdOld = em.merge(muestraIdOld);
            }
            if (muestraIdNew != null && !muestraIdNew.equals(muestraIdOld)) {
                muestraIdNew.getMuestraempleadorList().add(muestraempleador);
                muestraIdNew = em.merge(muestraIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = muestraempleador.getId();
                if (findMuestraempleador(id) == null) {
                    throw new NonexistentEntityException("The muestraempleador with id " + id + " no longer exists.");
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
            Muestraempleador muestraempleador;
            try {
                muestraempleador = em.getReference(Muestraempleador.class, id);
                muestraempleador.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The muestraempleador with id " + id + " no longer exists.", enfe);
            }
            Empleador empleadorId = muestraempleador.getEmpleadorId();
            if (empleadorId != null) {
                empleadorId.getMuestraempleadorList().remove(muestraempleador);
                empleadorId = em.merge(empleadorId);
            }
            Muestra muestraId = muestraempleador.getMuestraId();
            if (muestraId != null) {
                muestraId.getMuestraempleadorList().remove(muestraempleador);
                muestraId = em.merge(muestraId);
            }
            em.remove(muestraempleador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Muestraempleador> findMuestraempleadorEntities() {
        return findMuestraempleadorEntities(true, -1, -1);
    }

    public List<Muestraempleador> findMuestraempleadorEntities(int maxResults, int firstResult) {
        return findMuestraempleadorEntities(false, maxResults, firstResult);
    }

    private List<Muestraempleador> findMuestraempleadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Muestraempleador.class));
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

    public Muestraempleador findMuestraempleador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Muestraempleador.class, id);
        } finally {
            em.close();
        }
    }

    public int getMuestraempleadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Muestraempleador> rt = cq.from(Muestraempleador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
