/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import entity.Criterio;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Descripcioncriterio;
import entity.controller.exceptions.IllegalOrphanException;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Oscar
 */
public class CriterioJpaController implements Serializable {

    public CriterioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Criterio criterio) {
        if (criterio.getDescripcioncriterioList() == null) {
            criterio.setDescripcioncriterioList(new ArrayList<Descripcioncriterio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Descripcioncriterio> attachedDescripcioncriterioList = new ArrayList<Descripcioncriterio>();
            for (Descripcioncriterio descripcioncriterioListDescripcioncriterioToAttach : criterio.getDescripcioncriterioList()) {
                descripcioncriterioListDescripcioncriterioToAttach = em.getReference(descripcioncriterioListDescripcioncriterioToAttach.getClass(), descripcioncriterioListDescripcioncriterioToAttach.getId());
                attachedDescripcioncriterioList.add(descripcioncriterioListDescripcioncriterioToAttach);
            }
            criterio.setDescripcioncriterioList(attachedDescripcioncriterioList);
            em.persist(criterio);
            for (Descripcioncriterio descripcioncriterioListDescripcioncriterio : criterio.getDescripcioncriterioList()) {
                Criterio oldCriterioIdOfDescripcioncriterioListDescripcioncriterio = descripcioncriterioListDescripcioncriterio.getCriterioId();
                descripcioncriterioListDescripcioncriterio.setCriterioId(criterio);
                descripcioncriterioListDescripcioncriterio = em.merge(descripcioncriterioListDescripcioncriterio);
                if (oldCriterioIdOfDescripcioncriterioListDescripcioncriterio != null) {
                    oldCriterioIdOfDescripcioncriterioListDescripcioncriterio.getDescripcioncriterioList().remove(descripcioncriterioListDescripcioncriterio);
                    oldCriterioIdOfDescripcioncriterioListDescripcioncriterio = em.merge(oldCriterioIdOfDescripcioncriterioListDescripcioncriterio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Criterio criterio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Criterio persistentCriterio = em.find(Criterio.class, criterio.getId());
            List<Descripcioncriterio> descripcioncriterioListOld = persistentCriterio.getDescripcioncriterioList();
            List<Descripcioncriterio> descripcioncriterioListNew = criterio.getDescripcioncriterioList();
            List<String> illegalOrphanMessages = null;
            for (Descripcioncriterio descripcioncriterioListOldDescripcioncriterio : descripcioncriterioListOld) {
                if (!descripcioncriterioListNew.contains(descripcioncriterioListOldDescripcioncriterio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Descripcioncriterio " + descripcioncriterioListOldDescripcioncriterio + " since its criterioId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Descripcioncriterio> attachedDescripcioncriterioListNew = new ArrayList<Descripcioncriterio>();
            for (Descripcioncriterio descripcioncriterioListNewDescripcioncriterioToAttach : descripcioncriterioListNew) {
                descripcioncriterioListNewDescripcioncriterioToAttach = em.getReference(descripcioncriterioListNewDescripcioncriterioToAttach.getClass(), descripcioncriterioListNewDescripcioncriterioToAttach.getId());
                attachedDescripcioncriterioListNew.add(descripcioncriterioListNewDescripcioncriterioToAttach);
            }
            descripcioncriterioListNew = attachedDescripcioncriterioListNew;
            criterio.setDescripcioncriterioList(descripcioncriterioListNew);
            criterio = em.merge(criterio);
            for (Descripcioncriterio descripcioncriterioListNewDescripcioncriterio : descripcioncriterioListNew) {
                if (!descripcioncriterioListOld.contains(descripcioncriterioListNewDescripcioncriterio)) {
                    Criterio oldCriterioIdOfDescripcioncriterioListNewDescripcioncriterio = descripcioncriterioListNewDescripcioncriterio.getCriterioId();
                    descripcioncriterioListNewDescripcioncriterio.setCriterioId(criterio);
                    descripcioncriterioListNewDescripcioncriterio = em.merge(descripcioncriterioListNewDescripcioncriterio);
                    if (oldCriterioIdOfDescripcioncriterioListNewDescripcioncriterio != null && !oldCriterioIdOfDescripcioncriterioListNewDescripcioncriterio.equals(criterio)) {
                        oldCriterioIdOfDescripcioncriterioListNewDescripcioncriterio.getDescripcioncriterioList().remove(descripcioncriterioListNewDescripcioncriterio);
                        oldCriterioIdOfDescripcioncriterioListNewDescripcioncriterio = em.merge(oldCriterioIdOfDescripcioncriterioListNewDescripcioncriterio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = criterio.getId();
                if (findCriterio(id) == null) {
                    throw new NonexistentEntityException("The criterio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Criterio criterio;
            try {
                criterio = em.getReference(Criterio.class, id);
                criterio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The criterio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Descripcioncriterio> descripcioncriterioListOrphanCheck = criterio.getDescripcioncriterioList();
            for (Descripcioncriterio descripcioncriterioListOrphanCheckDescripcioncriterio : descripcioncriterioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Criterio (" + criterio + ") cannot be destroyed since the Descripcioncriterio " + descripcioncriterioListOrphanCheckDescripcioncriterio + " in its descripcioncriterioList field has a non-nullable criterioId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(criterio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Criterio> findCriterioEntities() {
        return findCriterioEntities(true, -1, -1);
    }

    public List<Criterio> findCriterioEntities(int maxResults, int firstResult) {
        return findCriterioEntities(false, maxResults, firstResult);
    }

    private List<Criterio> findCriterioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Criterio.class));
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

    public Criterio findCriterio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Criterio.class, id);
        } finally {
            em.close();
        }
    }

    public int getCriterioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Criterio> rt = cq.from(Criterio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
