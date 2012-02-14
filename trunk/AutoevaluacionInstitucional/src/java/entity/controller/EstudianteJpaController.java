/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import connection.jpaConnection;
import entity.Estudiante;
import entity.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Programa;
import entity.Persona;
import entity.Fuente;

/**
 *
 * @author vanesa
 */
public class EstudianteJpaController implements Serializable {

    public EstudianteJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Estudiante estudiante) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa programaId = estudiante.getProgramaId();
            if (programaId != null) {
                programaId = em.getReference(programaId.getClass(), programaId.getId());
                estudiante.setProgramaId(programaId);
            }
            Persona personaId = estudiante.getPersonaId();
            if (personaId != null) {
                personaId = em.getReference(personaId.getClass(), personaId.getId());
                estudiante.setPersonaId(personaId);
            }
            Fuente fuenteId = estudiante.getFuenteId();
            if (fuenteId != null) {
                fuenteId = em.getReference(fuenteId.getClass(), fuenteId.getId());
                estudiante.setFuenteId(fuenteId);
            }
            em.persist(estudiante);
            if (programaId != null) {
                programaId.getEstudianteList().add(estudiante);
                programaId = em.merge(programaId);
            }
            if (personaId != null) {
                personaId.getEstudianteList().add(estudiante);
                personaId = em.merge(personaId);
            }
            if (fuenteId != null) {
                fuenteId.getEstudianteList().add(estudiante);
                fuenteId = em.merge(fuenteId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estudiante estudiante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante persistentEstudiante = em.find(Estudiante.class, estudiante.getId());
            Programa programaIdOld = persistentEstudiante.getProgramaId();
            Programa programaIdNew = estudiante.getProgramaId();
            Persona personaIdOld = persistentEstudiante.getPersonaId();
            Persona personaIdNew = estudiante.getPersonaId();
            Fuente fuenteIdOld = persistentEstudiante.getFuenteId();
            Fuente fuenteIdNew = estudiante.getFuenteId();
            if (programaIdNew != null) {
                programaIdNew = em.getReference(programaIdNew.getClass(), programaIdNew.getId());
                estudiante.setProgramaId(programaIdNew);
            }
            if (personaIdNew != null) {
                personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
                estudiante.setPersonaId(personaIdNew);
            }
            if (fuenteIdNew != null) {
                fuenteIdNew = em.getReference(fuenteIdNew.getClass(), fuenteIdNew.getId());
                estudiante.setFuenteId(fuenteIdNew);
            }
            estudiante = em.merge(estudiante);
            if (programaIdOld != null && !programaIdOld.equals(programaIdNew)) {
                programaIdOld.getEstudianteList().remove(estudiante);
                programaIdOld = em.merge(programaIdOld);
            }
            if (programaIdNew != null && !programaIdNew.equals(programaIdOld)) {
                programaIdNew.getEstudianteList().add(estudiante);
                programaIdNew = em.merge(programaIdNew);
            }
            if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
                personaIdOld.getEstudianteList().remove(estudiante);
                personaIdOld = em.merge(personaIdOld);
            }
            if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
                personaIdNew.getEstudianteList().add(estudiante);
                personaIdNew = em.merge(personaIdNew);
            }
            if (fuenteIdOld != null && !fuenteIdOld.equals(fuenteIdNew)) {
                fuenteIdOld.getEstudianteList().remove(estudiante);
                fuenteIdOld = em.merge(fuenteIdOld);
            }
            if (fuenteIdNew != null && !fuenteIdNew.equals(fuenteIdOld)) {
                fuenteIdNew.getEstudianteList().add(estudiante);
                fuenteIdNew = em.merge(fuenteIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estudiante.getId();
                if (findEstudiante(id) == null) {
                    throw new NonexistentEntityException("The estudiante with id " + id + " no longer exists.");
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
            Estudiante estudiante;
            try {
                estudiante = em.getReference(Estudiante.class, id);
                estudiante.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estudiante with id " + id + " no longer exists.", enfe);
            }
            Programa programaId = estudiante.getProgramaId();
            if (programaId != null) {
                programaId.getEstudianteList().remove(estudiante);
                programaId = em.merge(programaId);
            }
            Persona personaId = estudiante.getPersonaId();
            if (personaId != null) {
                personaId.getEstudianteList().remove(estudiante);
                personaId = em.merge(personaId);
            }
            Fuente fuenteId = estudiante.getFuenteId();
            if (fuenteId != null) {
                fuenteId.getEstudianteList().remove(estudiante);
                fuenteId = em.merge(fuenteId);
            }
            em.remove(estudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estudiante> findEstudianteEntities() {
        return findEstudianteEntities(true, -1, -1);
    }

    public List<Estudiante> findEstudianteEntities(int maxResults, int firstResult) {
        return findEstudianteEntities(false, maxResults, firstResult);
    }

    private List<Estudiante> findEstudianteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estudiante.class));
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

    public Estudiante findEstudiante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estudiante.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstudianteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estudiante> rt = cq.from(Estudiante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
