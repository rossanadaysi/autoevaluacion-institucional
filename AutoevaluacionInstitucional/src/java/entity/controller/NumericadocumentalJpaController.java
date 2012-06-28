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
import entity.Proceso;
import entity.Indicador;
import entity.Numericadocumental;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Oscar
 */
public class NumericadocumentalJpaController implements Serializable {

    public NumericadocumentalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Numericadocumental numericadocumental) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proceso procesoId = numericadocumental.getProcesoId();
            if (procesoId != null) {
                procesoId = em.getReference(procesoId.getClass(), procesoId.getId());
                numericadocumental.setProcesoId(procesoId);
            }
            Indicador indicadorId = numericadocumental.getIndicadorId();
            if (indicadorId != null) {
                indicadorId = em.getReference(indicadorId.getClass(), indicadorId.getId());
                numericadocumental.setIndicadorId(indicadorId);
            }
            em.persist(numericadocumental);
            if (procesoId != null) {
                procesoId.getNumericadocumentalList().add(numericadocumental);
                procesoId = em.merge(procesoId);
            }
            if (indicadorId != null) {
                indicadorId.getNumericadocumentalList().add(numericadocumental);
                indicadorId = em.merge(indicadorId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Numericadocumental numericadocumental) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Numericadocumental persistentNumericadocumental = em.find(Numericadocumental.class, numericadocumental.getId());
            Proceso procesoIdOld = persistentNumericadocumental.getProcesoId();
            Proceso procesoIdNew = numericadocumental.getProcesoId();
            Indicador indicadorIdOld = persistentNumericadocumental.getIndicadorId();
            Indicador indicadorIdNew = numericadocumental.getIndicadorId();
            if (procesoIdNew != null) {
                procesoIdNew = em.getReference(procesoIdNew.getClass(), procesoIdNew.getId());
                numericadocumental.setProcesoId(procesoIdNew);
            }
            if (indicadorIdNew != null) {
                indicadorIdNew = em.getReference(indicadorIdNew.getClass(), indicadorIdNew.getId());
                numericadocumental.setIndicadorId(indicadorIdNew);
            }
            numericadocumental = em.merge(numericadocumental);
            if (procesoIdOld != null && !procesoIdOld.equals(procesoIdNew)) {
                procesoIdOld.getNumericadocumentalList().remove(numericadocumental);
                procesoIdOld = em.merge(procesoIdOld);
            }
            if (procesoIdNew != null && !procesoIdNew.equals(procesoIdOld)) {
                procesoIdNew.getNumericadocumentalList().add(numericadocumental);
                procesoIdNew = em.merge(procesoIdNew);
            }
            if (indicadorIdOld != null && !indicadorIdOld.equals(indicadorIdNew)) {
                indicadorIdOld.getNumericadocumentalList().remove(numericadocumental);
                indicadorIdOld = em.merge(indicadorIdOld);
            }
            if (indicadorIdNew != null && !indicadorIdNew.equals(indicadorIdOld)) {
                indicadorIdNew.getNumericadocumentalList().add(numericadocumental);
                indicadorIdNew = em.merge(indicadorIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = numericadocumental.getId();
                if (findNumericadocumental(id) == null) {
                    throw new NonexistentEntityException("The numericadocumental with id " + id + " no longer exists.");
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
            Numericadocumental numericadocumental;
            try {
                numericadocumental = em.getReference(Numericadocumental.class, id);
                numericadocumental.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The numericadocumental with id " + id + " no longer exists.", enfe);
            }
            Proceso procesoId = numericadocumental.getProcesoId();
            if (procesoId != null) {
                procesoId.getNumericadocumentalList().remove(numericadocumental);
                procesoId = em.merge(procesoId);
            }
            Indicador indicadorId = numericadocumental.getIndicadorId();
            if (indicadorId != null) {
                indicadorId.getNumericadocumentalList().remove(numericadocumental);
                indicadorId = em.merge(indicadorId);
            }
            em.remove(numericadocumental);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Numericadocumental> findNumericadocumentalEntities() {
        return findNumericadocumentalEntities(true, -1, -1);
    }

    public List<Numericadocumental> findNumericadocumentalEntities(int maxResults, int firstResult) {
        return findNumericadocumentalEntities(false, maxResults, firstResult);
    }

    private List<Numericadocumental> findNumericadocumentalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Numericadocumental.class));
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

    public Numericadocumental findNumericadocumental(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Numericadocumental.class, id);
        } finally {
            em.close();
        }
    }

    public int getNumericadocumentalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Numericadocumental> rt = cq.from(Numericadocumental.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
