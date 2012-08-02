/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import connection.jpaConnection;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Ponderacionfactor;
import java.util.ArrayList;
import java.util.List;
import entity.Caracteristica;
import entity.Factor;
import entity.controller.exceptions.IllegalOrphanException;
import entity.controller.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;

/**
 *
 * @author Oscar
 */
public class FactorJpaController implements Serializable {

    public FactorJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Factor factor) {
        if (factor.getPonderacionfactorList() == null) {
            factor.setPonderacionfactorList(new ArrayList<Ponderacionfactor>());
        }
        if (factor.getCaracteristicaList() == null) {
            factor.setCaracteristicaList(new ArrayList<Caracteristica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ponderacionfactor> attachedPonderacionfactorList = new ArrayList<Ponderacionfactor>();
            for (Ponderacionfactor ponderacionfactorListPonderacionfactorToAttach : factor.getPonderacionfactorList()) {
                ponderacionfactorListPonderacionfactorToAttach = em.getReference(ponderacionfactorListPonderacionfactorToAttach.getClass(), ponderacionfactorListPonderacionfactorToAttach.getId());
                attachedPonderacionfactorList.add(ponderacionfactorListPonderacionfactorToAttach);
            }
            factor.setPonderacionfactorList(attachedPonderacionfactorList);
            List<Caracteristica> attachedCaracteristicaList = new ArrayList<Caracteristica>();
            for (Caracteristica caracteristicaListCaracteristicaToAttach : factor.getCaracteristicaList()) {
                caracteristicaListCaracteristicaToAttach = em.getReference(caracteristicaListCaracteristicaToAttach.getClass(), caracteristicaListCaracteristicaToAttach.getId());
                attachedCaracteristicaList.add(caracteristicaListCaracteristicaToAttach);
            }
            factor.setCaracteristicaList(attachedCaracteristicaList);
            em.persist(factor);
            for (Ponderacionfactor ponderacionfactorListPonderacionfactor : factor.getPonderacionfactorList()) {
                Factor oldFactorIdOfPonderacionfactorListPonderacionfactor = ponderacionfactorListPonderacionfactor.getFactorId();
                ponderacionfactorListPonderacionfactor.setFactorId(factor);
                ponderacionfactorListPonderacionfactor = em.merge(ponderacionfactorListPonderacionfactor);
                if (oldFactorIdOfPonderacionfactorListPonderacionfactor != null) {
                    oldFactorIdOfPonderacionfactorListPonderacionfactor.getPonderacionfactorList().remove(ponderacionfactorListPonderacionfactor);
                    oldFactorIdOfPonderacionfactorListPonderacionfactor = em.merge(oldFactorIdOfPonderacionfactorListPonderacionfactor);
                }
            }
            for (Caracteristica caracteristicaListCaracteristica : factor.getCaracteristicaList()) {
                Factor oldFactorIdOfCaracteristicaListCaracteristica = caracteristicaListCaracteristica.getFactorId();
                caracteristicaListCaracteristica.setFactorId(factor);
                caracteristicaListCaracteristica = em.merge(caracteristicaListCaracteristica);
                if (oldFactorIdOfCaracteristicaListCaracteristica != null) {
                    oldFactorIdOfCaracteristicaListCaracteristica.getCaracteristicaList().remove(caracteristicaListCaracteristica);
                    oldFactorIdOfCaracteristicaListCaracteristica = em.merge(oldFactorIdOfCaracteristicaListCaracteristica);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factor factor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factor persistentFactor = em.find(Factor.class, factor.getId());
            List<Ponderacionfactor> ponderacionfactorListOld = persistentFactor.getPonderacionfactorList();
            List<Ponderacionfactor> ponderacionfactorListNew = factor.getPonderacionfactorList();
            List<Caracteristica> caracteristicaListOld = persistentFactor.getCaracteristicaList();
            List<Caracteristica> caracteristicaListNew = factor.getCaracteristicaList();
            List<String> illegalOrphanMessages = null;
            for (Ponderacionfactor ponderacionfactorListOldPonderacionfactor : ponderacionfactorListOld) {
                if (!ponderacionfactorListNew.contains(ponderacionfactorListOldPonderacionfactor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ponderacionfactor " + ponderacionfactorListOldPonderacionfactor + " since its factorId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Ponderacionfactor> attachedPonderacionfactorListNew = new ArrayList<Ponderacionfactor>();
            for (Ponderacionfactor ponderacionfactorListNewPonderacionfactorToAttach : ponderacionfactorListNew) {
                ponderacionfactorListNewPonderacionfactorToAttach = em.getReference(ponderacionfactorListNewPonderacionfactorToAttach.getClass(), ponderacionfactorListNewPonderacionfactorToAttach.getId());
                attachedPonderacionfactorListNew.add(ponderacionfactorListNewPonderacionfactorToAttach);
            }
            ponderacionfactorListNew = attachedPonderacionfactorListNew;
            factor.setPonderacionfactorList(ponderacionfactorListNew);
            List<Caracteristica> attachedCaracteristicaListNew = new ArrayList<Caracteristica>();
            for (Caracteristica caracteristicaListNewCaracteristicaToAttach : caracteristicaListNew) {
                caracteristicaListNewCaracteristicaToAttach = em.getReference(caracteristicaListNewCaracteristicaToAttach.getClass(), caracteristicaListNewCaracteristicaToAttach.getId());
                attachedCaracteristicaListNew.add(caracteristicaListNewCaracteristicaToAttach);
            }
            caracteristicaListNew = attachedCaracteristicaListNew;
            factor.setCaracteristicaList(caracteristicaListNew);
            factor = em.merge(factor);
            for (Ponderacionfactor ponderacionfactorListNewPonderacionfactor : ponderacionfactorListNew) {
                if (!ponderacionfactorListOld.contains(ponderacionfactorListNewPonderacionfactor)) {
                    Factor oldFactorIdOfPonderacionfactorListNewPonderacionfactor = ponderacionfactorListNewPonderacionfactor.getFactorId();
                    ponderacionfactorListNewPonderacionfactor.setFactorId(factor);
                    ponderacionfactorListNewPonderacionfactor = em.merge(ponderacionfactorListNewPonderacionfactor);
                    if (oldFactorIdOfPonderacionfactorListNewPonderacionfactor != null && !oldFactorIdOfPonderacionfactorListNewPonderacionfactor.equals(factor)) {
                        oldFactorIdOfPonderacionfactorListNewPonderacionfactor.getPonderacionfactorList().remove(ponderacionfactorListNewPonderacionfactor);
                        oldFactorIdOfPonderacionfactorListNewPonderacionfactor = em.merge(oldFactorIdOfPonderacionfactorListNewPonderacionfactor);
                    }
                }
            }
            for (Caracteristica caracteristicaListOldCaracteristica : caracteristicaListOld) {
                if (!caracteristicaListNew.contains(caracteristicaListOldCaracteristica)) {
                    caracteristicaListOldCaracteristica.setFactorId(null);
                    caracteristicaListOldCaracteristica = em.merge(caracteristicaListOldCaracteristica);
                }
            }
            for (Caracteristica caracteristicaListNewCaracteristica : caracteristicaListNew) {
                if (!caracteristicaListOld.contains(caracteristicaListNewCaracteristica)) {
                    Factor oldFactorIdOfCaracteristicaListNewCaracteristica = caracteristicaListNewCaracteristica.getFactorId();
                    caracteristicaListNewCaracteristica.setFactorId(factor);
                    caracteristicaListNewCaracteristica = em.merge(caracteristicaListNewCaracteristica);
                    if (oldFactorIdOfCaracteristicaListNewCaracteristica != null && !oldFactorIdOfCaracteristicaListNewCaracteristica.equals(factor)) {
                        oldFactorIdOfCaracteristicaListNewCaracteristica.getCaracteristicaList().remove(caracteristicaListNewCaracteristica);
                        oldFactorIdOfCaracteristicaListNewCaracteristica = em.merge(oldFactorIdOfCaracteristicaListNewCaracteristica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = factor.getId();
                if (findFactor(id) == null) {
                    throw new NonexistentEntityException("The factor with id " + id + " no longer exists.");
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
            Factor factor;
            try {
                factor = em.getReference(Factor.class, id);
                factor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ponderacionfactor> ponderacionfactorListOrphanCheck = factor.getPonderacionfactorList();
            for (Ponderacionfactor ponderacionfactorListOrphanCheckPonderacionfactor : ponderacionfactorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Factor (" + factor + ") cannot be destroyed since the Ponderacionfactor " + ponderacionfactorListOrphanCheckPonderacionfactor + " in its ponderacionfactorList field has a non-nullable factorId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Caracteristica> caracteristicaList = factor.getCaracteristicaList();
            for (Caracteristica caracteristicaListCaracteristica : caracteristicaList) {
                caracteristicaListCaracteristica.setFactorId(null);
                caracteristicaListCaracteristica = em.merge(caracteristicaListCaracteristica);
            }
            em.remove(factor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Factor> findFactorEntities() {
        return findFactorEntities(true, -1, -1);
    }

    public List<Factor> findFactorEntities(int maxResults, int firstResult) {
        return findFactorEntities(false, maxResults, firstResult);
    }

    private List<Factor> findFactorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Factor.class));
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

    public Factor findFactor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Factor.class, id);
        } finally {
            em.close();
        }
    }

    public int getFactorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Factor> rt = cq.from(Factor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
