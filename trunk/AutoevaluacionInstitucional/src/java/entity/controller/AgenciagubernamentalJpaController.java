/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import connection.jpaConnection;
import entity.Agenciagubernamental;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Persona;
import entity.Fuente;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class AgenciagubernamentalJpaController implements Serializable {

    public AgenciagubernamentalJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Agenciagubernamental agenciagubernamental) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona personaId = agenciagubernamental.getPersonaId();
            if (personaId != null) {
                personaId = em.getReference(personaId.getClass(), personaId.getId());
                agenciagubernamental.setPersonaId(personaId);
            }
            Fuente fuenteId = agenciagubernamental.getFuenteId();
            if (fuenteId != null) {
                fuenteId = em.getReference(fuenteId.getClass(), fuenteId.getId());
                agenciagubernamental.setFuenteId(fuenteId);
            }
            em.persist(agenciagubernamental);
            if (personaId != null) {
                personaId.getAgenciagubernamentalList().add(agenciagubernamental);
                personaId = em.merge(personaId);
            }
            if (fuenteId != null) {
                fuenteId.getAgenciagubernamentalList().add(agenciagubernamental);
                fuenteId = em.merge(fuenteId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Agenciagubernamental agenciagubernamental) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Agenciagubernamental persistentAgenciagubernamental = em.find(Agenciagubernamental.class, agenciagubernamental.getId());
            Persona personaIdOld = persistentAgenciagubernamental.getPersonaId();
            Persona personaIdNew = agenciagubernamental.getPersonaId();
            Fuente fuenteIdOld = persistentAgenciagubernamental.getFuenteId();
            Fuente fuenteIdNew = agenciagubernamental.getFuenteId();
            if (personaIdNew != null) {
                personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
                agenciagubernamental.setPersonaId(personaIdNew);
            }
            if (fuenteIdNew != null) {
                fuenteIdNew = em.getReference(fuenteIdNew.getClass(), fuenteIdNew.getId());
                agenciagubernamental.setFuenteId(fuenteIdNew);
            }
            agenciagubernamental = em.merge(agenciagubernamental);
            if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
                personaIdOld.getAgenciagubernamentalList().remove(agenciagubernamental);
                personaIdOld = em.merge(personaIdOld);
            }
            if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
                personaIdNew.getAgenciagubernamentalList().add(agenciagubernamental);
                personaIdNew = em.merge(personaIdNew);
            }
            if (fuenteIdOld != null && !fuenteIdOld.equals(fuenteIdNew)) {
                fuenteIdOld.getAgenciagubernamentalList().remove(agenciagubernamental);
                fuenteIdOld = em.merge(fuenteIdOld);
            }
            if (fuenteIdNew != null && !fuenteIdNew.equals(fuenteIdOld)) {
                fuenteIdNew.getAgenciagubernamentalList().add(agenciagubernamental);
                fuenteIdNew = em.merge(fuenteIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = agenciagubernamental.getId();
                if (findAgenciagubernamental(id) == null) {
                    throw new NonexistentEntityException("The agenciagubernamental with id " + id + " no longer exists.");
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
            Agenciagubernamental agenciagubernamental;
            try {
                agenciagubernamental = em.getReference(Agenciagubernamental.class, id);
                agenciagubernamental.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The agenciagubernamental with id " + id + " no longer exists.", enfe);
            }
            Persona personaId = agenciagubernamental.getPersonaId();
            if (personaId != null) {
                personaId.getAgenciagubernamentalList().remove(agenciagubernamental);
                personaId = em.merge(personaId);
            }
            Fuente fuenteId = agenciagubernamental.getFuenteId();
            if (fuenteId != null) {
                fuenteId.getAgenciagubernamentalList().remove(agenciagubernamental);
                fuenteId = em.merge(fuenteId);
            }
            em.remove(agenciagubernamental);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Agenciagubernamental> findAgenciagubernamentalEntities() {
        return findAgenciagubernamentalEntities(true, -1, -1);
    }

    public List<Agenciagubernamental> findAgenciagubernamentalEntities(int maxResults, int firstResult) {
        return findAgenciagubernamentalEntities(false, maxResults, firstResult);
    }

    private List<Agenciagubernamental> findAgenciagubernamentalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Agenciagubernamental.class));
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

    public Agenciagubernamental findAgenciagubernamental(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Agenciagubernamental.class, id);
        } finally {
            em.close();
        }
    }

    public int getAgenciagubernamentalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Agenciagubernamental> rt = cq.from(Agenciagubernamental.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
