/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import entity.*;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Oscar
 */
public class MuestracriterioJpaController implements Serializable {

    public MuestracriterioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Muestracriterio muestracriterio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona personaId = muestracriterio.getPersonaId();
            if (personaId != null) {
                personaId = em.getReference(personaId.getClass(), personaId.getId());
                muestracriterio.setPersonaId(personaId);
            }
            Descripcioncriterio descripcioncriterioId = muestracriterio.getDescripcioncriterioId();
            if (descripcioncriterioId != null) {
                descripcioncriterioId = em.getReference(descripcioncriterioId.getClass(), descripcioncriterioId.getId());
                muestracriterio.setDescripcioncriterioId(descripcioncriterioId);
            }
            Fuente fuenteId = muestracriterio.getFuenteId();
            if (fuenteId != null) {
                fuenteId = em.getReference(fuenteId.getClass(), fuenteId.getId());
                muestracriterio.setFuenteId(fuenteId);
            }
            Muestra muestraId = muestracriterio.getMuestraId();
            if (muestraId != null) {
                muestraId = em.getReference(muestraId.getClass(), muestraId.getId());
                muestracriterio.setMuestraId(muestraId);
            }
            em.persist(muestracriterio);
            if (personaId != null) {
                personaId.getMuestracriterioList().add(muestracriterio);
                personaId = em.merge(personaId);
            }
            if (descripcioncriterioId != null) {
                descripcioncriterioId.getMuestracriterioList().add(muestracriterio);
                descripcioncriterioId = em.merge(descripcioncriterioId);
            }
            if (fuenteId != null) {
                fuenteId.getMuestracriterioList().add(muestracriterio);
                fuenteId = em.merge(fuenteId);
            }
            if (muestraId != null) {
                muestraId.getMuestracriterioList().add(muestracriterio);
                muestraId = em.merge(muestraId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Muestracriterio muestracriterio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Muestracriterio persistentMuestracriterio = em.find(Muestracriterio.class, muestracriterio.getId());
            Persona personaIdOld = persistentMuestracriterio.getPersonaId();
            Persona personaIdNew = muestracriterio.getPersonaId();
            Descripcioncriterio descripcioncriterioIdOld = persistentMuestracriterio.getDescripcioncriterioId();
            Descripcioncriterio descripcioncriterioIdNew = muestracriterio.getDescripcioncriterioId();
            Fuente fuenteIdOld = persistentMuestracriterio.getFuenteId();
            Fuente fuenteIdNew = muestracriterio.getFuenteId();
            Muestra muestraIdOld = persistentMuestracriterio.getMuestraId();
            Muestra muestraIdNew = muestracriterio.getMuestraId();
            if (personaIdNew != null) {
                personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
                muestracriterio.setPersonaId(personaIdNew);
            }
            if (descripcioncriterioIdNew != null) {
                descripcioncriterioIdNew = em.getReference(descripcioncriterioIdNew.getClass(), descripcioncriterioIdNew.getId());
                muestracriterio.setDescripcioncriterioId(descripcioncriterioIdNew);
            }
            if (fuenteIdNew != null) {
                fuenteIdNew = em.getReference(fuenteIdNew.getClass(), fuenteIdNew.getId());
                muestracriterio.setFuenteId(fuenteIdNew);
            }
            if (muestraIdNew != null) {
                muestraIdNew = em.getReference(muestraIdNew.getClass(), muestraIdNew.getId());
                muestracriterio.setMuestraId(muestraIdNew);
            }
            muestracriterio = em.merge(muestracriterio);
            if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
                personaIdOld.getMuestracriterioList().remove(muestracriterio);
                personaIdOld = em.merge(personaIdOld);
            }
            if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
                personaIdNew.getMuestracriterioList().add(muestracriterio);
                personaIdNew = em.merge(personaIdNew);
            }
            if (descripcioncriterioIdOld != null && !descripcioncriterioIdOld.equals(descripcioncriterioIdNew)) {
                descripcioncriterioIdOld.getMuestracriterioList().remove(muestracriterio);
                descripcioncriterioIdOld = em.merge(descripcioncriterioIdOld);
            }
            if (descripcioncriterioIdNew != null && !descripcioncriterioIdNew.equals(descripcioncriterioIdOld)) {
                descripcioncriterioIdNew.getMuestracriterioList().add(muestracriterio);
                descripcioncriterioIdNew = em.merge(descripcioncriterioIdNew);
            }
            if (fuenteIdOld != null && !fuenteIdOld.equals(fuenteIdNew)) {
                fuenteIdOld.getMuestracriterioList().remove(muestracriterio);
                fuenteIdOld = em.merge(fuenteIdOld);
            }
            if (fuenteIdNew != null && !fuenteIdNew.equals(fuenteIdOld)) {
                fuenteIdNew.getMuestracriterioList().add(muestracriterio);
                fuenteIdNew = em.merge(fuenteIdNew);
            }
            if (muestraIdOld != null && !muestraIdOld.equals(muestraIdNew)) {
                muestraIdOld.getMuestracriterioList().remove(muestracriterio);
                muestraIdOld = em.merge(muestraIdOld);
            }
            if (muestraIdNew != null && !muestraIdNew.equals(muestraIdOld)) {
                muestraIdNew.getMuestracriterioList().add(muestracriterio);
                muestraIdNew = em.merge(muestraIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = muestracriterio.getId();
                if (findMuestracriterio(id) == null) {
                    throw new NonexistentEntityException("The muestracriterio with id " + id + " no longer exists.");
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
            Muestracriterio muestracriterio;
            try {
                muestracriterio = em.getReference(Muestracriterio.class, id);
                muestracriterio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The muestracriterio with id " + id + " no longer exists.", enfe);
            }
            Persona personaId = muestracriterio.getPersonaId();
            if (personaId != null) {
                personaId.getMuestracriterioList().remove(muestracriterio);
                personaId = em.merge(personaId);
            }
            Descripcioncriterio descripcioncriterioId = muestracriterio.getDescripcioncriterioId();
            if (descripcioncriterioId != null) {
                descripcioncriterioId.getMuestracriterioList().remove(muestracriterio);
                descripcioncriterioId = em.merge(descripcioncriterioId);
            }
            Fuente fuenteId = muestracriterio.getFuenteId();
            if (fuenteId != null) {
                fuenteId.getMuestracriterioList().remove(muestracriterio);
                fuenteId = em.merge(fuenteId);
            }
            Muestra muestraId = muestracriterio.getMuestraId();
            if (muestraId != null) {
                muestraId.getMuestracriterioList().remove(muestracriterio);
                muestraId = em.merge(muestraId);
            }
            em.remove(muestracriterio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Muestracriterio> findMuestracriterioEntities() {
        return findMuestracriterioEntities(true, -1, -1);
    }

    public List<Muestracriterio> findMuestracriterioEntities(int maxResults, int firstResult) {
        return findMuestracriterioEntities(false, maxResults, firstResult);
    }

    private List<Muestracriterio> findMuestracriterioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Muestracriterio.class));
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

    public Muestracriterio findMuestracriterio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Muestracriterio.class, id);
        } finally {
            em.close();
        }
    }

    public int getMuestracriterioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Muestracriterio> rt = cq.from(Muestracriterio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
