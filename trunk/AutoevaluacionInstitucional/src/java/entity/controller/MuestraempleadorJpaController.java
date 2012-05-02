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
import entity.Empleador;
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
            Muestra muestraId = muestraempleador.getMuestraId();
            if (muestraId != null) {
                muestraId = em.getReference(muestraId.getClass(), muestraId.getId());
                muestraempleador.setMuestraId(muestraId);
            }
            Empleador empleadorId = muestraempleador.getEmpleadorId();
            if (empleadorId != null) {
                empleadorId = em.getReference(empleadorId.getClass(), empleadorId.getId());
                muestraempleador.setEmpleadorId(empleadorId);
            }
            em.persist(muestraempleador);
            if (muestraId != null) {
                muestraId.getMuestraempleadorList().add(muestraempleador);
                muestraId = em.merge(muestraId);
            }
            if (empleadorId != null) {
                empleadorId.getMuestraempleadorList().add(muestraempleador);
                empleadorId = em.merge(empleadorId);
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
            Muestra muestraIdOld = persistentMuestraempleador.getMuestraId();
            Muestra muestraIdNew = muestraempleador.getMuestraId();
            Empleador empleadorIdOld = persistentMuestraempleador.getEmpleadorId();
            Empleador empleadorIdNew = muestraempleador.getEmpleadorId();
            if (muestraIdNew != null) {
                muestraIdNew = em.getReference(muestraIdNew.getClass(), muestraIdNew.getId());
                muestraempleador.setMuestraId(muestraIdNew);
            }
            if (empleadorIdNew != null) {
                empleadorIdNew = em.getReference(empleadorIdNew.getClass(), empleadorIdNew.getId());
                muestraempleador.setEmpleadorId(empleadorIdNew);
            }
            muestraempleador = em.merge(muestraempleador);
            if (muestraIdOld != null && !muestraIdOld.equals(muestraIdNew)) {
                muestraIdOld.getMuestraempleadorList().remove(muestraempleador);
                muestraIdOld = em.merge(muestraIdOld);
            }
            if (muestraIdNew != null && !muestraIdNew.equals(muestraIdOld)) {
                muestraIdNew.getMuestraempleadorList().add(muestraempleador);
                muestraIdNew = em.merge(muestraIdNew);
            }
            if (empleadorIdOld != null && !empleadorIdOld.equals(empleadorIdNew)) {
                empleadorIdOld.getMuestraempleadorList().remove(muestraempleador);
                empleadorIdOld = em.merge(empleadorIdOld);
            }
            if (empleadorIdNew != null && !empleadorIdNew.equals(empleadorIdOld)) {
                empleadorIdNew.getMuestraempleadorList().add(muestraempleador);
                empleadorIdNew = em.merge(empleadorIdNew);
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
            Muestra muestraId = muestraempleador.getMuestraId();
            if (muestraId != null) {
                muestraId.getMuestraempleadorList().remove(muestraempleador);
                muestraId = em.merge(muestraId);
            }
            Empleador empleadorId = muestraempleador.getEmpleadorId();
            if (empleadorId != null) {
                empleadorId.getMuestraempleadorList().remove(muestraempleador);
                empleadorId = em.merge(empleadorId);
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
