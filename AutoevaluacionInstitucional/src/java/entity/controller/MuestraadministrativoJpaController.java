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
import entity.Administrativo;
import entity.Muestra;
import entity.Muestraadministrativo;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ususario
 */
public class MuestraadministrativoJpaController implements Serializable {

    public MuestraadministrativoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Muestraadministrativo muestraadministrativo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrativo administrativoId = muestraadministrativo.getAdministrativoId();
            if (administrativoId != null) {
                administrativoId = em.getReference(administrativoId.getClass(), administrativoId.getId());
                muestraadministrativo.setAdministrativoId(administrativoId);
            }
            Muestra muestraId = muestraadministrativo.getMuestraId();
            if (muestraId != null) {
                muestraId = em.getReference(muestraId.getClass(), muestraId.getId());
                muestraadministrativo.setMuestraId(muestraId);
            }
            em.persist(muestraadministrativo);
            if (administrativoId != null) {
                administrativoId.getMuestraadministrativoList().add(muestraadministrativo);
                administrativoId = em.merge(administrativoId);
            }
            if (muestraId != null) {
                muestraId.getMuestraadministrativoList().add(muestraadministrativo);
                muestraId = em.merge(muestraId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Muestraadministrativo muestraadministrativo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Muestraadministrativo persistentMuestraadministrativo = em.find(Muestraadministrativo.class, muestraadministrativo.getId());
            Administrativo administrativoIdOld = persistentMuestraadministrativo.getAdministrativoId();
            Administrativo administrativoIdNew = muestraadministrativo.getAdministrativoId();
            Muestra muestraIdOld = persistentMuestraadministrativo.getMuestraId();
            Muestra muestraIdNew = muestraadministrativo.getMuestraId();
            if (administrativoIdNew != null) {
                administrativoIdNew = em.getReference(administrativoIdNew.getClass(), administrativoIdNew.getId());
                muestraadministrativo.setAdministrativoId(administrativoIdNew);
            }
            if (muestraIdNew != null) {
                muestraIdNew = em.getReference(muestraIdNew.getClass(), muestraIdNew.getId());
                muestraadministrativo.setMuestraId(muestraIdNew);
            }
            muestraadministrativo = em.merge(muestraadministrativo);
            if (administrativoIdOld != null && !administrativoIdOld.equals(administrativoIdNew)) {
                administrativoIdOld.getMuestraadministrativoList().remove(muestraadministrativo);
                administrativoIdOld = em.merge(administrativoIdOld);
            }
            if (administrativoIdNew != null && !administrativoIdNew.equals(administrativoIdOld)) {
                administrativoIdNew.getMuestraadministrativoList().add(muestraadministrativo);
                administrativoIdNew = em.merge(administrativoIdNew);
            }
            if (muestraIdOld != null && !muestraIdOld.equals(muestraIdNew)) {
                muestraIdOld.getMuestraadministrativoList().remove(muestraadministrativo);
                muestraIdOld = em.merge(muestraIdOld);
            }
            if (muestraIdNew != null && !muestraIdNew.equals(muestraIdOld)) {
                muestraIdNew.getMuestraadministrativoList().add(muestraadministrativo);
                muestraIdNew = em.merge(muestraIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = muestraadministrativo.getId();
                if (findMuestraadministrativo(id) == null) {
                    throw new NonexistentEntityException("The muestraadministrativo with id " + id + " no longer exists.");
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
            Muestraadministrativo muestraadministrativo;
            try {
                muestraadministrativo = em.getReference(Muestraadministrativo.class, id);
                muestraadministrativo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The muestraadministrativo with id " + id + " no longer exists.", enfe);
            }
            Administrativo administrativoId = muestraadministrativo.getAdministrativoId();
            if (administrativoId != null) {
                administrativoId.getMuestraadministrativoList().remove(muestraadministrativo);
                administrativoId = em.merge(administrativoId);
            }
            Muestra muestraId = muestraadministrativo.getMuestraId();
            if (muestraId != null) {
                muestraId.getMuestraadministrativoList().remove(muestraadministrativo);
                muestraId = em.merge(muestraId);
            }
            em.remove(muestraadministrativo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Muestraadministrativo> findMuestraadministrativoEntities() {
        return findMuestraadministrativoEntities(true, -1, -1);
    }

    public List<Muestraadministrativo> findMuestraadministrativoEntities(int maxResults, int firstResult) {
        return findMuestraadministrativoEntities(false, maxResults, firstResult);
    }

    private List<Muestraadministrativo> findMuestraadministrativoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Muestraadministrativo.class));
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

    public Muestraadministrativo findMuestraadministrativo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Muestraadministrativo.class, id);
        } finally {
            em.close();
        }
    }

    public int getMuestraadministrativoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Muestraadministrativo> rt = cq.from(Muestraadministrativo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
