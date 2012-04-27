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
import entity.Caracteristica;
import entity.Ponderacioncaracteristica;
import entity.Proceso;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ususario
 */
public class PonderacioncaracteristicaJpaController implements Serializable {

    public PonderacioncaracteristicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ponderacioncaracteristica ponderacioncaracteristica) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Caracteristica caracteristicaId = ponderacioncaracteristica.getCaracteristicaId();
            if (caracteristicaId != null) {
                caracteristicaId = em.getReference(caracteristicaId.getClass(), caracteristicaId.getId());
                ponderacioncaracteristica.setCaracteristicaId(caracteristicaId);
            }
            Proceso procesoId = ponderacioncaracteristica.getProcesoId();
            if (procesoId != null) {
                procesoId = em.getReference(procesoId.getClass(), procesoId.getId());
                ponderacioncaracteristica.setProcesoId(procesoId);
            }
            em.persist(ponderacioncaracteristica);
            if (caracteristicaId != null) {
                caracteristicaId.getPonderacioncaracteristicaList().add(ponderacioncaracteristica);
                caracteristicaId = em.merge(caracteristicaId);
            }
            if (procesoId != null) {
                procesoId.getPonderacioncaracteristicaList().add(ponderacioncaracteristica);
                procesoId = em.merge(procesoId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ponderacioncaracteristica ponderacioncaracteristica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ponderacioncaracteristica persistentPonderacioncaracteristica = em.find(Ponderacioncaracteristica.class, ponderacioncaracteristica.getId());
            Caracteristica caracteristicaIdOld = persistentPonderacioncaracteristica.getCaracteristicaId();
            Caracteristica caracteristicaIdNew = ponderacioncaracteristica.getCaracteristicaId();
            Proceso procesoIdOld = persistentPonderacioncaracteristica.getProcesoId();
            Proceso procesoIdNew = ponderacioncaracteristica.getProcesoId();
            if (caracteristicaIdNew != null) {
                caracteristicaIdNew = em.getReference(caracteristicaIdNew.getClass(), caracteristicaIdNew.getId());
                ponderacioncaracteristica.setCaracteristicaId(caracteristicaIdNew);
            }
            if (procesoIdNew != null) {
                procesoIdNew = em.getReference(procesoIdNew.getClass(), procesoIdNew.getId());
                ponderacioncaracteristica.setProcesoId(procesoIdNew);
            }
            ponderacioncaracteristica = em.merge(ponderacioncaracteristica);
            if (caracteristicaIdOld != null && !caracteristicaIdOld.equals(caracteristicaIdNew)) {
                caracteristicaIdOld.getPonderacioncaracteristicaList().remove(ponderacioncaracteristica);
                caracteristicaIdOld = em.merge(caracteristicaIdOld);
            }
            if (caracteristicaIdNew != null && !caracteristicaIdNew.equals(caracteristicaIdOld)) {
                caracteristicaIdNew.getPonderacioncaracteristicaList().add(ponderacioncaracteristica);
                caracteristicaIdNew = em.merge(caracteristicaIdNew);
            }
            if (procesoIdOld != null && !procesoIdOld.equals(procesoIdNew)) {
                procesoIdOld.getPonderacioncaracteristicaList().remove(ponderacioncaracteristica);
                procesoIdOld = em.merge(procesoIdOld);
            }
            if (procesoIdNew != null && !procesoIdNew.equals(procesoIdOld)) {
                procesoIdNew.getPonderacioncaracteristicaList().add(ponderacioncaracteristica);
                procesoIdNew = em.merge(procesoIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ponderacioncaracteristica.getId();
                if (findPonderacioncaracteristica(id) == null) {
                    throw new NonexistentEntityException("The ponderacioncaracteristica with id " + id + " no longer exists.");
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
            Ponderacioncaracteristica ponderacioncaracteristica;
            try {
                ponderacioncaracteristica = em.getReference(Ponderacioncaracteristica.class, id);
                ponderacioncaracteristica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ponderacioncaracteristica with id " + id + " no longer exists.", enfe);
            }
            Caracteristica caracteristicaId = ponderacioncaracteristica.getCaracteristicaId();
            if (caracteristicaId != null) {
                caracteristicaId.getPonderacioncaracteristicaList().remove(ponderacioncaracteristica);
                caracteristicaId = em.merge(caracteristicaId);
            }
            Proceso procesoId = ponderacioncaracteristica.getProcesoId();
            if (procesoId != null) {
                procesoId.getPonderacioncaracteristicaList().remove(ponderacioncaracteristica);
                procesoId = em.merge(procesoId);
            }
            em.remove(ponderacioncaracteristica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ponderacioncaracteristica> findPonderacioncaracteristicaEntities() {
        return findPonderacioncaracteristicaEntities(true, -1, -1);
    }

    public List<Ponderacioncaracteristica> findPonderacioncaracteristicaEntities(int maxResults, int firstResult) {
        return findPonderacioncaracteristicaEntities(false, maxResults, firstResult);
    }

    private List<Ponderacioncaracteristica> findPonderacioncaracteristicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ponderacioncaracteristica.class));
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

    public Ponderacioncaracteristica findPonderacioncaracteristica(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ponderacioncaracteristica.class, id);
        } finally {
            em.close();
        }
    }

    public int getPonderacioncaracteristicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ponderacioncaracteristica> rt = cq.from(Ponderacioncaracteristica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
