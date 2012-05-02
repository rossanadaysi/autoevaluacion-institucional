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
import entity.Docente;
import entity.Muestradocente;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ususario
 */
public class MuestradocenteJpaController implements Serializable {

    public MuestradocenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Muestradocente muestradocente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Muestra muestraId = muestradocente.getMuestraId();
            if (muestraId != null) {
                muestraId = em.getReference(muestraId.getClass(), muestraId.getId());
                muestradocente.setMuestraId(muestraId);
            }
            Docente docenteId = muestradocente.getDocenteId();
            if (docenteId != null) {
                docenteId = em.getReference(docenteId.getClass(), docenteId.getId());
                muestradocente.setDocenteId(docenteId);
            }
            em.persist(muestradocente);
            if (muestraId != null) {
                muestraId.getMuestradocenteList().add(muestradocente);
                muestraId = em.merge(muestraId);
            }
            if (docenteId != null) {
                docenteId.getMuestradocenteList().add(muestradocente);
                docenteId = em.merge(docenteId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Muestradocente muestradocente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Muestradocente persistentMuestradocente = em.find(Muestradocente.class, muestradocente.getId());
            Muestra muestraIdOld = persistentMuestradocente.getMuestraId();
            Muestra muestraIdNew = muestradocente.getMuestraId();
            Docente docenteIdOld = persistentMuestradocente.getDocenteId();
            Docente docenteIdNew = muestradocente.getDocenteId();
            if (muestraIdNew != null) {
                muestraIdNew = em.getReference(muestraIdNew.getClass(), muestraIdNew.getId());
                muestradocente.setMuestraId(muestraIdNew);
            }
            if (docenteIdNew != null) {
                docenteIdNew = em.getReference(docenteIdNew.getClass(), docenteIdNew.getId());
                muestradocente.setDocenteId(docenteIdNew);
            }
            muestradocente = em.merge(muestradocente);
            if (muestraIdOld != null && !muestraIdOld.equals(muestraIdNew)) {
                muestraIdOld.getMuestradocenteList().remove(muestradocente);
                muestraIdOld = em.merge(muestraIdOld);
            }
            if (muestraIdNew != null && !muestraIdNew.equals(muestraIdOld)) {
                muestraIdNew.getMuestradocenteList().add(muestradocente);
                muestraIdNew = em.merge(muestraIdNew);
            }
            if (docenteIdOld != null && !docenteIdOld.equals(docenteIdNew)) {
                docenteIdOld.getMuestradocenteList().remove(muestradocente);
                docenteIdOld = em.merge(docenteIdOld);
            }
            if (docenteIdNew != null && !docenteIdNew.equals(docenteIdOld)) {
                docenteIdNew.getMuestradocenteList().add(muestradocente);
                docenteIdNew = em.merge(docenteIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = muestradocente.getId();
                if (findMuestradocente(id) == null) {
                    throw new NonexistentEntityException("The muestradocente with id " + id + " no longer exists.");
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
            Muestradocente muestradocente;
            try {
                muestradocente = em.getReference(Muestradocente.class, id);
                muestradocente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The muestradocente with id " + id + " no longer exists.", enfe);
            }
            Muestra muestraId = muestradocente.getMuestraId();
            if (muestraId != null) {
                muestraId.getMuestradocenteList().remove(muestradocente);
                muestraId = em.merge(muestraId);
            }
            Docente docenteId = muestradocente.getDocenteId();
            if (docenteId != null) {
                docenteId.getMuestradocenteList().remove(muestradocente);
                docenteId = em.merge(docenteId);
            }
            em.remove(muestradocente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Muestradocente> findMuestradocenteEntities() {
        return findMuestradocenteEntities(true, -1, -1);
    }

    public List<Muestradocente> findMuestradocenteEntities(int maxResults, int firstResult) {
        return findMuestradocenteEntities(false, maxResults, firstResult);
    }

    private List<Muestradocente> findMuestradocenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Muestradocente.class));
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

    public Muestradocente findMuestradocente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Muestradocente.class, id);
        } finally {
            em.close();
        }
    }

    public int getMuestradocenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Muestradocente> rt = cq.from(Muestradocente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
