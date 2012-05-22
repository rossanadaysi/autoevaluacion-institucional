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
import entity.Egresado;
import entity.Muestraegresado;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class MuestraegresadoJpaController implements Serializable {

    public MuestraegresadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Muestraegresado muestraegresado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Muestra muestraId = muestraegresado.getMuestraId();
            if (muestraId != null) {
                muestraId = em.getReference(muestraId.getClass(), muestraId.getId());
                muestraegresado.setMuestraId(muestraId);
            }
            Egresado egresadoId = muestraegresado.getEgresadoId();
            if (egresadoId != null) {
                egresadoId = em.getReference(egresadoId.getClass(), egresadoId.getId());
                muestraegresado.setEgresadoId(egresadoId);
            }
            em.persist(muestraegresado);
            if (muestraId != null) {
                muestraId.getMuestraegresadoList().add(muestraegresado);
                muestraId = em.merge(muestraId);
            }
            if (egresadoId != null) {
                egresadoId.getMuestraegresadoList().add(muestraegresado);
                egresadoId = em.merge(egresadoId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Muestraegresado muestraegresado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Muestraegresado persistentMuestraegresado = em.find(Muestraegresado.class, muestraegresado.getId());
            Muestra muestraIdOld = persistentMuestraegresado.getMuestraId();
            Muestra muestraIdNew = muestraegresado.getMuestraId();
            Egresado egresadoIdOld = persistentMuestraegresado.getEgresadoId();
            Egresado egresadoIdNew = muestraegresado.getEgresadoId();
            if (muestraIdNew != null) {
                muestraIdNew = em.getReference(muestraIdNew.getClass(), muestraIdNew.getId());
                muestraegresado.setMuestraId(muestraIdNew);
            }
            if (egresadoIdNew != null) {
                egresadoIdNew = em.getReference(egresadoIdNew.getClass(), egresadoIdNew.getId());
                muestraegresado.setEgresadoId(egresadoIdNew);
            }
            muestraegresado = em.merge(muestraegresado);
            if (muestraIdOld != null && !muestraIdOld.equals(muestraIdNew)) {
                muestraIdOld.getMuestraegresadoList().remove(muestraegresado);
                muestraIdOld = em.merge(muestraIdOld);
            }
            if (muestraIdNew != null && !muestraIdNew.equals(muestraIdOld)) {
                muestraIdNew.getMuestraegresadoList().add(muestraegresado);
                muestraIdNew = em.merge(muestraIdNew);
            }
            if (egresadoIdOld != null && !egresadoIdOld.equals(egresadoIdNew)) {
                egresadoIdOld.getMuestraegresadoList().remove(muestraegresado);
                egresadoIdOld = em.merge(egresadoIdOld);
            }
            if (egresadoIdNew != null && !egresadoIdNew.equals(egresadoIdOld)) {
                egresadoIdNew.getMuestraegresadoList().add(muestraegresado);
                egresadoIdNew = em.merge(egresadoIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = muestraegresado.getId();
                if (findMuestraegresado(id) == null) {
                    throw new NonexistentEntityException("The muestraegresado with id " + id + " no longer exists.");
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
            Muestraegresado muestraegresado;
            try {
                muestraegresado = em.getReference(Muestraegresado.class, id);
                muestraegresado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The muestraegresado with id " + id + " no longer exists.", enfe);
            }
            Muestra muestraId = muestraegresado.getMuestraId();
            if (muestraId != null) {
                muestraId.getMuestraegresadoList().remove(muestraegresado);
                muestraId = em.merge(muestraId);
            }
            Egresado egresadoId = muestraegresado.getEgresadoId();
            if (egresadoId != null) {
                egresadoId.getMuestraegresadoList().remove(muestraegresado);
                egresadoId = em.merge(egresadoId);
            }
            em.remove(muestraegresado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Muestraegresado> findMuestraegresadoEntities() {
        return findMuestraegresadoEntities(true, -1, -1);
    }

    public List<Muestraegresado> findMuestraegresadoEntities(int maxResults, int firstResult) {
        return findMuestraegresadoEntities(false, maxResults, firstResult);
    }

    private List<Muestraegresado> findMuestraegresadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Muestraegresado.class));
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

    public Muestraegresado findMuestraegresado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Muestraegresado.class, id);
        } finally {
            em.close();
        }
    }

    public int getMuestraegresadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Muestraegresado> rt = cq.from(Muestraegresado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
