/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import entity.Directorprograma;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Persona;
import entity.Programa;
import entity.Fuente;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Usuario
 */
public class DirectorprogramaJpaController implements Serializable {

    public DirectorprogramaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Directorprograma directorprograma) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona personaId = directorprograma.getPersonaId();
            if (personaId != null) {
                personaId = em.getReference(personaId.getClass(), personaId.getId());
                directorprograma.setPersonaId(personaId);
            }
            Programa programaId = directorprograma.getProgramaId();
            if (programaId != null) {
                programaId = em.getReference(programaId.getClass(), programaId.getId());
                directorprograma.setProgramaId(programaId);
            }
            Fuente fuenteId = directorprograma.getFuenteId();
            if (fuenteId != null) {
                fuenteId = em.getReference(fuenteId.getClass(), fuenteId.getId());
                directorprograma.setFuenteId(fuenteId);
            }
            em.persist(directorprograma);
            if (personaId != null) {
                personaId.getDirectorprogramaList().add(directorprograma);
                personaId = em.merge(personaId);
            }
            if (programaId != null) {
                programaId.getDirectorprogramaList().add(directorprograma);
                programaId = em.merge(programaId);
            }
            if (fuenteId != null) {
                fuenteId.getDirectorprogramaList().add(directorprograma);
                fuenteId = em.merge(fuenteId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Directorprograma directorprograma) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Directorprograma persistentDirectorprograma = em.find(Directorprograma.class, directorprograma.getId());
            Persona personaIdOld = persistentDirectorprograma.getPersonaId();
            Persona personaIdNew = directorprograma.getPersonaId();
            Programa programaIdOld = persistentDirectorprograma.getProgramaId();
            Programa programaIdNew = directorprograma.getProgramaId();
            Fuente fuenteIdOld = persistentDirectorprograma.getFuenteId();
            Fuente fuenteIdNew = directorprograma.getFuenteId();
            if (personaIdNew != null) {
                personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
                directorprograma.setPersonaId(personaIdNew);
            }
            if (programaIdNew != null) {
                programaIdNew = em.getReference(programaIdNew.getClass(), programaIdNew.getId());
                directorprograma.setProgramaId(programaIdNew);
            }
            if (fuenteIdNew != null) {
                fuenteIdNew = em.getReference(fuenteIdNew.getClass(), fuenteIdNew.getId());
                directorprograma.setFuenteId(fuenteIdNew);
            }
            directorprograma = em.merge(directorprograma);
            if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
                personaIdOld.getDirectorprogramaList().remove(directorprograma);
                personaIdOld = em.merge(personaIdOld);
            }
            if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
                personaIdNew.getDirectorprogramaList().add(directorprograma);
                personaIdNew = em.merge(personaIdNew);
            }
            if (programaIdOld != null && !programaIdOld.equals(programaIdNew)) {
                programaIdOld.getDirectorprogramaList().remove(directorprograma);
                programaIdOld = em.merge(programaIdOld);
            }
            if (programaIdNew != null && !programaIdNew.equals(programaIdOld)) {
                programaIdNew.getDirectorprogramaList().add(directorprograma);
                programaIdNew = em.merge(programaIdNew);
            }
            if (fuenteIdOld != null && !fuenteIdOld.equals(fuenteIdNew)) {
                fuenteIdOld.getDirectorprogramaList().remove(directorprograma);
                fuenteIdOld = em.merge(fuenteIdOld);
            }
            if (fuenteIdNew != null && !fuenteIdNew.equals(fuenteIdOld)) {
                fuenteIdNew.getDirectorprogramaList().add(directorprograma);
                fuenteIdNew = em.merge(fuenteIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = directorprograma.getId();
                if (findDirectorprograma(id) == null) {
                    throw new NonexistentEntityException("The directorprograma with id " + id + " no longer exists.");
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
            Directorprograma directorprograma;
            try {
                directorprograma = em.getReference(Directorprograma.class, id);
                directorprograma.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The directorprograma with id " + id + " no longer exists.", enfe);
            }
            Persona personaId = directorprograma.getPersonaId();
            if (personaId != null) {
                personaId.getDirectorprogramaList().remove(directorprograma);
                personaId = em.merge(personaId);
            }
            Programa programaId = directorprograma.getProgramaId();
            if (programaId != null) {
                programaId.getDirectorprogramaList().remove(directorprograma);
                programaId = em.merge(programaId);
            }
            Fuente fuenteId = directorprograma.getFuenteId();
            if (fuenteId != null) {
                fuenteId.getDirectorprogramaList().remove(directorprograma);
                fuenteId = em.merge(fuenteId);
            }
            em.remove(directorprograma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Directorprograma> findDirectorprogramaEntities() {
        return findDirectorprogramaEntities(true, -1, -1);
    }

    public List<Directorprograma> findDirectorprogramaEntities(int maxResults, int firstResult) {
        return findDirectorprogramaEntities(false, maxResults, firstResult);
    }

    private List<Directorprograma> findDirectorprogramaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Directorprograma.class));
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

    public Directorprograma findDirectorprograma(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Directorprograma.class, id);
        } finally {
            em.close();
        }
    }

    public int getDirectorprogramaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Directorprograma> rt = cq.from(Directorprograma.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
