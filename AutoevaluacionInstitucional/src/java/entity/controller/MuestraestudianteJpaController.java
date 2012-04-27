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
import entity.Estudiante;
import entity.Muestra;
import entity.Muestraestudiante;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ususario
 */
public class MuestraestudianteJpaController implements Serializable {

    public MuestraestudianteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Muestraestudiante muestraestudiante) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante estudianteId = muestraestudiante.getEstudianteId();
            if (estudianteId != null) {
                estudianteId = em.getReference(estudianteId.getClass(), estudianteId.getId());
                muestraestudiante.setEstudianteId(estudianteId);
            }
            Muestra muestraId = muestraestudiante.getMuestraId();
            if (muestraId != null) {
                muestraId = em.getReference(muestraId.getClass(), muestraId.getId());
                muestraestudiante.setMuestraId(muestraId);
            }
            em.persist(muestraestudiante);
            if (estudianteId != null) {
                estudianteId.getMuestraestudianteList().add(muestraestudiante);
                estudianteId = em.merge(estudianteId);
            }
            if (muestraId != null) {
                muestraId.getMuestraestudianteList().add(muestraestudiante);
                muestraId = em.merge(muestraId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Muestraestudiante muestraestudiante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Muestraestudiante persistentMuestraestudiante = em.find(Muestraestudiante.class, muestraestudiante.getId());
            Estudiante estudianteIdOld = persistentMuestraestudiante.getEstudianteId();
            Estudiante estudianteIdNew = muestraestudiante.getEstudianteId();
            Muestra muestraIdOld = persistentMuestraestudiante.getMuestraId();
            Muestra muestraIdNew = muestraestudiante.getMuestraId();
            if (estudianteIdNew != null) {
                estudianteIdNew = em.getReference(estudianteIdNew.getClass(), estudianteIdNew.getId());
                muestraestudiante.setEstudianteId(estudianteIdNew);
            }
            if (muestraIdNew != null) {
                muestraIdNew = em.getReference(muestraIdNew.getClass(), muestraIdNew.getId());
                muestraestudiante.setMuestraId(muestraIdNew);
            }
            muestraestudiante = em.merge(muestraestudiante);
            if (estudianteIdOld != null && !estudianteIdOld.equals(estudianteIdNew)) {
                estudianteIdOld.getMuestraestudianteList().remove(muestraestudiante);
                estudianteIdOld = em.merge(estudianteIdOld);
            }
            if (estudianteIdNew != null && !estudianteIdNew.equals(estudianteIdOld)) {
                estudianteIdNew.getMuestraestudianteList().add(muestraestudiante);
                estudianteIdNew = em.merge(estudianteIdNew);
            }
            if (muestraIdOld != null && !muestraIdOld.equals(muestraIdNew)) {
                muestraIdOld.getMuestraestudianteList().remove(muestraestudiante);
                muestraIdOld = em.merge(muestraIdOld);
            }
            if (muestraIdNew != null && !muestraIdNew.equals(muestraIdOld)) {
                muestraIdNew.getMuestraestudianteList().add(muestraestudiante);
                muestraIdNew = em.merge(muestraIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = muestraestudiante.getId();
                if (findMuestraestudiante(id) == null) {
                    throw new NonexistentEntityException("The muestraestudiante with id " + id + " no longer exists.");
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
            Muestraestudiante muestraestudiante;
            try {
                muestraestudiante = em.getReference(Muestraestudiante.class, id);
                muestraestudiante.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The muestraestudiante with id " + id + " no longer exists.", enfe);
            }
            Estudiante estudianteId = muestraestudiante.getEstudianteId();
            if (estudianteId != null) {
                estudianteId.getMuestraestudianteList().remove(muestraestudiante);
                estudianteId = em.merge(estudianteId);
            }
            Muestra muestraId = muestraestudiante.getMuestraId();
            if (muestraId != null) {
                muestraId.getMuestraestudianteList().remove(muestraestudiante);
                muestraId = em.merge(muestraId);
            }
            em.remove(muestraestudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Muestraestudiante> findMuestraestudianteEntities() {
        return findMuestraestudianteEntities(true, -1, -1);
    }

    public List<Muestraestudiante> findMuestraestudianteEntities(int maxResults, int firstResult) {
        return findMuestraestudianteEntities(false, maxResults, firstResult);
    }

    private List<Muestraestudiante> findMuestraestudianteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Muestraestudiante.class));
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

    public Muestraestudiante findMuestraestudiante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Muestraestudiante.class, id);
        } finally {
            em.close();
        }
    }

    public int getMuestraestudianteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Muestraestudiante> rt = cq.from(Muestraestudiante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
