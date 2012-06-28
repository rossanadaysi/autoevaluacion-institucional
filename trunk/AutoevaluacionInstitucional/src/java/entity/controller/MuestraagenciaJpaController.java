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
import entity.Agenciagubernamental;
import entity.Muestraagencia;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Oscar
 */
public class MuestraagenciaJpaController implements Serializable {

    public MuestraagenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Muestraagencia muestraagencia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Muestra muestraId = muestraagencia.getMuestraId();
            if (muestraId != null) {
                muestraId = em.getReference(muestraId.getClass(), muestraId.getId());
                muestraagencia.setMuestraId(muestraId);
            }
            Agenciagubernamental agenciagubernamentalId = muestraagencia.getAgenciagubernamentalId();
            if (agenciagubernamentalId != null) {
                agenciagubernamentalId = em.getReference(agenciagubernamentalId.getClass(), agenciagubernamentalId.getId());
                muestraagencia.setAgenciagubernamentalId(agenciagubernamentalId);
            }
            em.persist(muestraagencia);
            if (muestraId != null) {
                muestraId.getMuestraagenciaList().add(muestraagencia);
                muestraId = em.merge(muestraId);
            }
            if (agenciagubernamentalId != null) {
                agenciagubernamentalId.getMuestraagenciaList().add(muestraagencia);
                agenciagubernamentalId = em.merge(agenciagubernamentalId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Muestraagencia muestraagencia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Muestraagencia persistentMuestraagencia = em.find(Muestraagencia.class, muestraagencia.getId());
            Muestra muestraIdOld = persistentMuestraagencia.getMuestraId();
            Muestra muestraIdNew = muestraagencia.getMuestraId();
            Agenciagubernamental agenciagubernamentalIdOld = persistentMuestraagencia.getAgenciagubernamentalId();
            Agenciagubernamental agenciagubernamentalIdNew = muestraagencia.getAgenciagubernamentalId();
            if (muestraIdNew != null) {
                muestraIdNew = em.getReference(muestraIdNew.getClass(), muestraIdNew.getId());
                muestraagencia.setMuestraId(muestraIdNew);
            }
            if (agenciagubernamentalIdNew != null) {
                agenciagubernamentalIdNew = em.getReference(agenciagubernamentalIdNew.getClass(), agenciagubernamentalIdNew.getId());
                muestraagencia.setAgenciagubernamentalId(agenciagubernamentalIdNew);
            }
            muestraagencia = em.merge(muestraagencia);
            if (muestraIdOld != null && !muestraIdOld.equals(muestraIdNew)) {
                muestraIdOld.getMuestraagenciaList().remove(muestraagencia);
                muestraIdOld = em.merge(muestraIdOld);
            }
            if (muestraIdNew != null && !muestraIdNew.equals(muestraIdOld)) {
                muestraIdNew.getMuestraagenciaList().add(muestraagencia);
                muestraIdNew = em.merge(muestraIdNew);
            }
            if (agenciagubernamentalIdOld != null && !agenciagubernamentalIdOld.equals(agenciagubernamentalIdNew)) {
                agenciagubernamentalIdOld.getMuestraagenciaList().remove(muestraagencia);
                agenciagubernamentalIdOld = em.merge(agenciagubernamentalIdOld);
            }
            if (agenciagubernamentalIdNew != null && !agenciagubernamentalIdNew.equals(agenciagubernamentalIdOld)) {
                agenciagubernamentalIdNew.getMuestraagenciaList().add(muestraagencia);
                agenciagubernamentalIdNew = em.merge(agenciagubernamentalIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = muestraagencia.getId();
                if (findMuestraagencia(id) == null) {
                    throw new NonexistentEntityException("The muestraagencia with id " + id + " no longer exists.");
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
            Muestraagencia muestraagencia;
            try {
                muestraagencia = em.getReference(Muestraagencia.class, id);
                muestraagencia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The muestraagencia with id " + id + " no longer exists.", enfe);
            }
            Muestra muestraId = muestraagencia.getMuestraId();
            if (muestraId != null) {
                muestraId.getMuestraagenciaList().remove(muestraagencia);
                muestraId = em.merge(muestraId);
            }
            Agenciagubernamental agenciagubernamentalId = muestraagencia.getAgenciagubernamentalId();
            if (agenciagubernamentalId != null) {
                agenciagubernamentalId.getMuestraagenciaList().remove(muestraagencia);
                agenciagubernamentalId = em.merge(agenciagubernamentalId);
            }
            em.remove(muestraagencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Muestraagencia> findMuestraagenciaEntities() {
        return findMuestraagenciaEntities(true, -1, -1);
    }

    public List<Muestraagencia> findMuestraagenciaEntities(int maxResults, int firstResult) {
        return findMuestraagenciaEntities(false, maxResults, firstResult);
    }

    private List<Muestraagencia> findMuestraagenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Muestraagencia.class));
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

    public Muestraagencia findMuestraagencia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Muestraagencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getMuestraagenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Muestraagencia> rt = cq.from(Muestraagencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
