/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import connection.jpaConnection;
import entity.Egresado;
import entity.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Programa;
import entity.Fuente;
import entity.Persona;

/**
 *
 * @author vanesa
 */
public class EgresadoJpaController implements Serializable {

    public EgresadoJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Egresado egresado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa programaId = egresado.getProgramaId();
            if (programaId != null) {
                programaId = em.getReference(programaId.getClass(), programaId.getId());
                egresado.setProgramaId(programaId);
            }
            Fuente fuenteId = egresado.getFuenteId();
            if (fuenteId != null) {
                fuenteId = em.getReference(fuenteId.getClass(), fuenteId.getId());
                egresado.setFuenteId(fuenteId);
            }
            Persona personaId = egresado.getPersonaId();
            if (personaId != null) {
                personaId = em.getReference(personaId.getClass(), personaId.getId());
                egresado.setPersonaId(personaId);
            }
            em.persist(egresado);
            if (programaId != null) {
                programaId.getEgresadoList().add(egresado);
                programaId = em.merge(programaId);
            }
            if (fuenteId != null) {
                fuenteId.getEgresadoList().add(egresado);
                fuenteId = em.merge(fuenteId);
            }
            if (personaId != null) {
                personaId.getEgresadoList().add(egresado);
                personaId = em.merge(personaId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Egresado egresado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Egresado persistentEgresado = em.find(Egresado.class, egresado.getId());
            Programa programaIdOld = persistentEgresado.getProgramaId();
            Programa programaIdNew = egresado.getProgramaId();
            Fuente fuenteIdOld = persistentEgresado.getFuenteId();
            Fuente fuenteIdNew = egresado.getFuenteId();
            Persona personaIdOld = persistentEgresado.getPersonaId();
            Persona personaIdNew = egresado.getPersonaId();
            if (programaIdNew != null) {
                programaIdNew = em.getReference(programaIdNew.getClass(), programaIdNew.getId());
                egresado.setProgramaId(programaIdNew);
            }
            if (fuenteIdNew != null) {
                fuenteIdNew = em.getReference(fuenteIdNew.getClass(), fuenteIdNew.getId());
                egresado.setFuenteId(fuenteIdNew);
            }
            if (personaIdNew != null) {
                personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
                egresado.setPersonaId(personaIdNew);
            }
            egresado = em.merge(egresado);
            if (programaIdOld != null && !programaIdOld.equals(programaIdNew)) {
                programaIdOld.getEgresadoList().remove(egresado);
                programaIdOld = em.merge(programaIdOld);
            }
            if (programaIdNew != null && !programaIdNew.equals(programaIdOld)) {
                programaIdNew.getEgresadoList().add(egresado);
                programaIdNew = em.merge(programaIdNew);
            }
            if (fuenteIdOld != null && !fuenteIdOld.equals(fuenteIdNew)) {
                fuenteIdOld.getEgresadoList().remove(egresado);
                fuenteIdOld = em.merge(fuenteIdOld);
            }
            if (fuenteIdNew != null && !fuenteIdNew.equals(fuenteIdOld)) {
                fuenteIdNew.getEgresadoList().add(egresado);
                fuenteIdNew = em.merge(fuenteIdNew);
            }
            if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
                personaIdOld.getEgresadoList().remove(egresado);
                personaIdOld = em.merge(personaIdOld);
            }
            if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
                personaIdNew.getEgresadoList().add(egresado);
                personaIdNew = em.merge(personaIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = egresado.getId();
                if (findEgresado(id) == null) {
                    throw new NonexistentEntityException("The egresado with id " + id + " no longer exists.");
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
            Egresado egresado;
            try {
                egresado = em.getReference(Egresado.class, id);
                egresado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The egresado with id " + id + " no longer exists.", enfe);
            }
            Programa programaId = egresado.getProgramaId();
            if (programaId != null) {
                programaId.getEgresadoList().remove(egresado);
                programaId = em.merge(programaId);
            }
            Fuente fuenteId = egresado.getFuenteId();
            if (fuenteId != null) {
                fuenteId.getEgresadoList().remove(egresado);
                fuenteId = em.merge(fuenteId);
            }
            Persona personaId = egresado.getPersonaId();
            if (personaId != null) {
                personaId.getEgresadoList().remove(egresado);
                personaId = em.merge(personaId);
            }
            em.remove(egresado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Egresado> findEgresadoEntities() {
        return findEgresadoEntities(true, -1, -1);
    }

    public List<Egresado> findEgresadoEntities(int maxResults, int firstResult) {
        return findEgresadoEntities(false, maxResults, firstResult);
    }

    private List<Egresado> findEgresadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Egresado.class));
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

    public Egresado findEgresado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Egresado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEgresadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Egresado> rt = cq.from(Egresado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
