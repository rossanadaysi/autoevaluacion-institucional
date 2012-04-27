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
import entity.Factor;
import entity.Ponderacionfactor;
import entity.Proceso;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ususario
 */
public class PonderacionfactorJpaController implements Serializable {

    public PonderacionfactorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ponderacionfactor ponderacionfactor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factor factorId = ponderacionfactor.getFactorId();
            if (factorId != null) {
                factorId = em.getReference(factorId.getClass(), factorId.getId());
                ponderacionfactor.setFactorId(factorId);
            }
            Proceso procesoId = ponderacionfactor.getProcesoId();
            if (procesoId != null) {
                procesoId = em.getReference(procesoId.getClass(), procesoId.getId());
                ponderacionfactor.setProcesoId(procesoId);
            }
            em.persist(ponderacionfactor);
            if (factorId != null) {
                factorId.getPonderacionfactorList().add(ponderacionfactor);
                factorId = em.merge(factorId);
            }
            if (procesoId != null) {
                procesoId.getPonderacionfactorList().add(ponderacionfactor);
                procesoId = em.merge(procesoId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ponderacionfactor ponderacionfactor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ponderacionfactor persistentPonderacionfactor = em.find(Ponderacionfactor.class, ponderacionfactor.getId());
            Factor factorIdOld = persistentPonderacionfactor.getFactorId();
            Factor factorIdNew = ponderacionfactor.getFactorId();
            Proceso procesoIdOld = persistentPonderacionfactor.getProcesoId();
            Proceso procesoIdNew = ponderacionfactor.getProcesoId();
            if (factorIdNew != null) {
                factorIdNew = em.getReference(factorIdNew.getClass(), factorIdNew.getId());
                ponderacionfactor.setFactorId(factorIdNew);
            }
            if (procesoIdNew != null) {
                procesoIdNew = em.getReference(procesoIdNew.getClass(), procesoIdNew.getId());
                ponderacionfactor.setProcesoId(procesoIdNew);
            }
            ponderacionfactor = em.merge(ponderacionfactor);
            if (factorIdOld != null && !factorIdOld.equals(factorIdNew)) {
                factorIdOld.getPonderacionfactorList().remove(ponderacionfactor);
                factorIdOld = em.merge(factorIdOld);
            }
            if (factorIdNew != null && !factorIdNew.equals(factorIdOld)) {
                factorIdNew.getPonderacionfactorList().add(ponderacionfactor);
                factorIdNew = em.merge(factorIdNew);
            }
            if (procesoIdOld != null && !procesoIdOld.equals(procesoIdNew)) {
                procesoIdOld.getPonderacionfactorList().remove(ponderacionfactor);
                procesoIdOld = em.merge(procesoIdOld);
            }
            if (procesoIdNew != null && !procesoIdNew.equals(procesoIdOld)) {
                procesoIdNew.getPonderacionfactorList().add(ponderacionfactor);
                procesoIdNew = em.merge(procesoIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ponderacionfactor.getId();
                if (findPonderacionfactor(id) == null) {
                    throw new NonexistentEntityException("The ponderacionfactor with id " + id + " no longer exists.");
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
            Ponderacionfactor ponderacionfactor;
            try {
                ponderacionfactor = em.getReference(Ponderacionfactor.class, id);
                ponderacionfactor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ponderacionfactor with id " + id + " no longer exists.", enfe);
            }
            Factor factorId = ponderacionfactor.getFactorId();
            if (factorId != null) {
                factorId.getPonderacionfactorList().remove(ponderacionfactor);
                factorId = em.merge(factorId);
            }
            Proceso procesoId = ponderacionfactor.getProcesoId();
            if (procesoId != null) {
                procesoId.getPonderacionfactorList().remove(ponderacionfactor);
                procesoId = em.merge(procesoId);
            }
            em.remove(ponderacionfactor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ponderacionfactor> findPonderacionfactorEntities() {
        return findPonderacionfactorEntities(true, -1, -1);
    }

    public List<Ponderacionfactor> findPonderacionfactorEntities(int maxResults, int firstResult) {
        return findPonderacionfactorEntities(false, maxResults, firstResult);
    }

    private List<Ponderacionfactor> findPonderacionfactorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ponderacionfactor.class));
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

    public Ponderacionfactor findPonderacionfactor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ponderacionfactor.class, id);
        } finally {
            em.close();
        }
    }

    public int getPonderacionfactorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ponderacionfactor> rt = cq.from(Ponderacionfactor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
