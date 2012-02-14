/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import connection.jpaConnection;
import entity.Administrativo;
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
public class AdministrativoJpaController implements Serializable {

    public AdministrativoJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Administrativo administrativo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa programaId = administrativo.getProgramaId();
            if (programaId != null) {
                programaId = em.getReference(programaId.getClass(), programaId.getId());
                administrativo.setProgramaId(programaId);
            }
            Fuente fuenteId = administrativo.getFuenteId();
            if (fuenteId != null) {
                fuenteId = em.getReference(fuenteId.getClass(), fuenteId.getId());
                administrativo.setFuenteId(fuenteId);
            }
            Persona personaId = administrativo.getPersonaId();
            if (personaId != null) {
                personaId = em.getReference(personaId.getClass(), personaId.getId());
                administrativo.setPersonaId(personaId);
            }
            em.persist(administrativo);
            if (programaId != null) {
                programaId.getAdministrativoList().add(administrativo);
                programaId = em.merge(programaId);
            }
            if (fuenteId != null) {
                fuenteId.getAdministrativoList().add(administrativo);
                fuenteId = em.merge(fuenteId);
            }
            if (personaId != null) {
                personaId.getAdministrativoList().add(administrativo);
                personaId = em.merge(personaId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Administrativo administrativo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrativo persistentAdministrativo = em.find(Administrativo.class, administrativo.getId());
            Programa programaIdOld = persistentAdministrativo.getProgramaId();
            Programa programaIdNew = administrativo.getProgramaId();
            Fuente fuenteIdOld = persistentAdministrativo.getFuenteId();
            Fuente fuenteIdNew = administrativo.getFuenteId();
            Persona personaIdOld = persistentAdministrativo.getPersonaId();
            Persona personaIdNew = administrativo.getPersonaId();
            if (programaIdNew != null) {
                programaIdNew = em.getReference(programaIdNew.getClass(), programaIdNew.getId());
                administrativo.setProgramaId(programaIdNew);
            }
            if (fuenteIdNew != null) {
                fuenteIdNew = em.getReference(fuenteIdNew.getClass(), fuenteIdNew.getId());
                administrativo.setFuenteId(fuenteIdNew);
            }
            if (personaIdNew != null) {
                personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
                administrativo.setPersonaId(personaIdNew);
            }
            administrativo = em.merge(administrativo);
            if (programaIdOld != null && !programaIdOld.equals(programaIdNew)) {
                programaIdOld.getAdministrativoList().remove(administrativo);
                programaIdOld = em.merge(programaIdOld);
            }
            if (programaIdNew != null && !programaIdNew.equals(programaIdOld)) {
                programaIdNew.getAdministrativoList().add(administrativo);
                programaIdNew = em.merge(programaIdNew);
            }
            if (fuenteIdOld != null && !fuenteIdOld.equals(fuenteIdNew)) {
                fuenteIdOld.getAdministrativoList().remove(administrativo);
                fuenteIdOld = em.merge(fuenteIdOld);
            }
            if (fuenteIdNew != null && !fuenteIdNew.equals(fuenteIdOld)) {
                fuenteIdNew.getAdministrativoList().add(administrativo);
                fuenteIdNew = em.merge(fuenteIdNew);
            }
            if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
                personaIdOld.getAdministrativoList().remove(administrativo);
                personaIdOld = em.merge(personaIdOld);
            }
            if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
                personaIdNew.getAdministrativoList().add(administrativo);
                personaIdNew = em.merge(personaIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = administrativo.getId();
                if (findAdministrativo(id) == null) {
                    throw new NonexistentEntityException("The administrativo with id " + id + " no longer exists.");
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
            Administrativo administrativo;
            try {
                administrativo = em.getReference(Administrativo.class, id);
                administrativo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The administrativo with id " + id + " no longer exists.", enfe);
            }
            Programa programaId = administrativo.getProgramaId();
            if (programaId != null) {
                programaId.getAdministrativoList().remove(administrativo);
                programaId = em.merge(programaId);
            }
            Fuente fuenteId = administrativo.getFuenteId();
            if (fuenteId != null) {
                fuenteId.getAdministrativoList().remove(administrativo);
                fuenteId = em.merge(fuenteId);
            }
            Persona personaId = administrativo.getPersonaId();
            if (personaId != null) {
                personaId.getAdministrativoList().remove(administrativo);
                personaId = em.merge(personaId);
            }
            em.remove(administrativo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Administrativo> findAdministrativoEntities() {
        return findAdministrativoEntities(true, -1, -1);
    }

    public List<Administrativo> findAdministrativoEntities(int maxResults, int firstResult) {
        return findAdministrativoEntities(false, maxResults, firstResult);
    }

    private List<Administrativo> findAdministrativoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Administrativo.class));
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

    public Administrativo findAdministrativo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Administrativo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdministrativoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Administrativo> rt = cq.from(Administrativo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
