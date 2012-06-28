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
import entity.Criterio;
import entity.Descripcioncriterio;
import entity.Muestracriterio;
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
public class DescripcioncriterioJpaController implements Serializable {

    public DescripcioncriterioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Descripcioncriterio descripcioncriterio) {
        if (descripcioncriterio.getMuestracriterioList() == null) {
            descripcioncriterio.setMuestracriterioList(new ArrayList<Muestracriterio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Criterio criterioId = descripcioncriterio.getCriterioId();
            if (criterioId != null) {
                criterioId = em.getReference(criterioId.getClass(), criterioId.getId());
                descripcioncriterio.setCriterioId(criterioId);
            }
            List<Muestracriterio> attachedMuestracriterioList = new ArrayList<Muestracriterio>();
            for (Muestracriterio muestracriterioListMuestracriterioToAttach : descripcioncriterio.getMuestracriterioList()) {
                muestracriterioListMuestracriterioToAttach = em.getReference(muestracriterioListMuestracriterioToAttach.getClass(), muestracriterioListMuestracriterioToAttach.getId());
                attachedMuestracriterioList.add(muestracriterioListMuestracriterioToAttach);
            }
            descripcioncriterio.setMuestracriterioList(attachedMuestracriterioList);
            em.persist(descripcioncriterio);
            if (criterioId != null) {
                criterioId.getDescripcioncriterioList().add(descripcioncriterio);
                criterioId = em.merge(criterioId);
            }
            for (Muestracriterio muestracriterioListMuestracriterio : descripcioncriterio.getMuestracriterioList()) {
                Descripcioncriterio oldDescripcioncriterioIdOfMuestracriterioListMuestracriterio = muestracriterioListMuestracriterio.getDescripcioncriterioId();
                muestracriterioListMuestracriterio.setDescripcioncriterioId(descripcioncriterio);
                muestracriterioListMuestracriterio = em.merge(muestracriterioListMuestracriterio);
                if (oldDescripcioncriterioIdOfMuestracriterioListMuestracriterio != null) {
                    oldDescripcioncriterioIdOfMuestracriterioListMuestracriterio.getMuestracriterioList().remove(muestracriterioListMuestracriterio);
                    oldDescripcioncriterioIdOfMuestracriterioListMuestracriterio = em.merge(oldDescripcioncriterioIdOfMuestracriterioListMuestracriterio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Descripcioncriterio descripcioncriterio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Descripcioncriterio persistentDescripcioncriterio = em.find(Descripcioncriterio.class, descripcioncriterio.getId());
            Criterio criterioIdOld = persistentDescripcioncriterio.getCriterioId();
            Criterio criterioIdNew = descripcioncriterio.getCriterioId();
            List<Muestracriterio> muestracriterioListOld = persistentDescripcioncriterio.getMuestracriterioList();
            List<Muestracriterio> muestracriterioListNew = descripcioncriterio.getMuestracriterioList();
            List<String> illegalOrphanMessages = null;
            for (Muestracriterio muestracriterioListOldMuestracriterio : muestracriterioListOld) {
                if (!muestracriterioListNew.contains(muestracriterioListOldMuestracriterio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Muestracriterio " + muestracriterioListOldMuestracriterio + " since its descripcioncriterioId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (criterioIdNew != null) {
                criterioIdNew = em.getReference(criterioIdNew.getClass(), criterioIdNew.getId());
                descripcioncriterio.setCriterioId(criterioIdNew);
            }
            List<Muestracriterio> attachedMuestracriterioListNew = new ArrayList<Muestracriterio>();
            for (Muestracriterio muestracriterioListNewMuestracriterioToAttach : muestracriterioListNew) {
                muestracriterioListNewMuestracriterioToAttach = em.getReference(muestracriterioListNewMuestracriterioToAttach.getClass(), muestracriterioListNewMuestracriterioToAttach.getId());
                attachedMuestracriterioListNew.add(muestracriterioListNewMuestracriterioToAttach);
            }
            muestracriterioListNew = attachedMuestracriterioListNew;
            descripcioncriterio.setMuestracriterioList(muestracriterioListNew);
            descripcioncriterio = em.merge(descripcioncriterio);
            if (criterioIdOld != null && !criterioIdOld.equals(criterioIdNew)) {
                criterioIdOld.getDescripcioncriterioList().remove(descripcioncriterio);
                criterioIdOld = em.merge(criterioIdOld);
            }
            if (criterioIdNew != null && !criterioIdNew.equals(criterioIdOld)) {
                criterioIdNew.getDescripcioncriterioList().add(descripcioncriterio);
                criterioIdNew = em.merge(criterioIdNew);
            }
            for (Muestracriterio muestracriterioListNewMuestracriterio : muestracriterioListNew) {
                if (!muestracriterioListOld.contains(muestracriterioListNewMuestracriterio)) {
                    Descripcioncriterio oldDescripcioncriterioIdOfMuestracriterioListNewMuestracriterio = muestracriterioListNewMuestracriterio.getDescripcioncriterioId();
                    muestracriterioListNewMuestracriterio.setDescripcioncriterioId(descripcioncriterio);
                    muestracriterioListNewMuestracriterio = em.merge(muestracriterioListNewMuestracriterio);
                    if (oldDescripcioncriterioIdOfMuestracriterioListNewMuestracriterio != null && !oldDescripcioncriterioIdOfMuestracriterioListNewMuestracriterio.equals(descripcioncriterio)) {
                        oldDescripcioncriterioIdOfMuestracriterioListNewMuestracriterio.getMuestracriterioList().remove(muestracriterioListNewMuestracriterio);
                        oldDescripcioncriterioIdOfMuestracriterioListNewMuestracriterio = em.merge(oldDescripcioncriterioIdOfMuestracriterioListNewMuestracriterio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = descripcioncriterio.getId();
                if (findDescripcioncriterio(id) == null) {
                    throw new NonexistentEntityException("The descripcioncriterio with id " + id + " no longer exists.");
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
            Descripcioncriterio descripcioncriterio;
            try {
                descripcioncriterio = em.getReference(Descripcioncriterio.class, id);
                descripcioncriterio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The descripcioncriterio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Muestracriterio> muestracriterioListOrphanCheck = descripcioncriterio.getMuestracriterioList();
            for (Muestracriterio muestracriterioListOrphanCheckMuestracriterio : muestracriterioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Descripcioncriterio (" + descripcioncriterio + ") cannot be destroyed since the Muestracriterio " + muestracriterioListOrphanCheckMuestracriterio + " in its muestracriterioList field has a non-nullable descripcioncriterioId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Criterio criterioId = descripcioncriterio.getCriterioId();
            if (criterioId != null) {
                criterioId.getDescripcioncriterioList().remove(descripcioncriterio);
                criterioId = em.merge(criterioId);
            }
            em.remove(descripcioncriterio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Descripcioncriterio> findDescripcioncriterioEntities() {
        return findDescripcioncriterioEntities(true, -1, -1);
    }

    public List<Descripcioncriterio> findDescripcioncriterioEntities(int maxResults, int firstResult) {
        return findDescripcioncriterioEntities(false, maxResults, firstResult);
    }

    private List<Descripcioncriterio> findDescripcioncriterioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Descripcioncriterio.class));
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

    public Descripcioncriterio findDescripcioncriterio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Descripcioncriterio.class, id);
        } finally {
            em.close();
        }
    }

    public int getDescripcioncriterioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Descripcioncriterio> rt = cq.from(Descripcioncriterio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
