/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import entity.*;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.controller.exceptions.IllegalOrphanException;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Oscar
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
        if (directorprograma.getMuestradirectorList() == null) {
            directorprograma.setMuestradirectorList(new ArrayList<Muestradirector>());
        }
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
            List<Muestradirector> attachedMuestradirectorList = new ArrayList<Muestradirector>();
            for (Muestradirector muestradirectorListMuestradirectorToAttach : directorprograma.getMuestradirectorList()) {
                muestradirectorListMuestradirectorToAttach = em.getReference(muestradirectorListMuestradirectorToAttach.getClass(), muestradirectorListMuestradirectorToAttach.getId());
                attachedMuestradirectorList.add(muestradirectorListMuestradirectorToAttach);
            }
            directorprograma.setMuestradirectorList(attachedMuestradirectorList);
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
            for (Muestradirector muestradirectorListMuestradirector : directorprograma.getMuestradirectorList()) {
                Directorprograma oldDirectorprogramaIdOfMuestradirectorListMuestradirector = muestradirectorListMuestradirector.getDirectorprogramaId();
                muestradirectorListMuestradirector.setDirectorprogramaId(directorprograma);
                muestradirectorListMuestradirector = em.merge(muestradirectorListMuestradirector);
                if (oldDirectorprogramaIdOfMuestradirectorListMuestradirector != null) {
                    oldDirectorprogramaIdOfMuestradirectorListMuestradirector.getMuestradirectorList().remove(muestradirectorListMuestradirector);
                    oldDirectorprogramaIdOfMuestradirectorListMuestradirector = em.merge(oldDirectorprogramaIdOfMuestradirectorListMuestradirector);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Directorprograma directorprograma) throws IllegalOrphanException, NonexistentEntityException, Exception {
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
            List<Muestradirector> muestradirectorListOld = persistentDirectorprograma.getMuestradirectorList();
            List<Muestradirector> muestradirectorListNew = directorprograma.getMuestradirectorList();
            List<String> illegalOrphanMessages = null;
            for (Muestradirector muestradirectorListOldMuestradirector : muestradirectorListOld) {
                if (!muestradirectorListNew.contains(muestradirectorListOldMuestradirector)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Muestradirector " + muestradirectorListOldMuestradirector + " since its directorprogramaId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
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
            List<Muestradirector> attachedMuestradirectorListNew = new ArrayList<Muestradirector>();
            for (Muestradirector muestradirectorListNewMuestradirectorToAttach : muestradirectorListNew) {
                muestradirectorListNewMuestradirectorToAttach = em.getReference(muestradirectorListNewMuestradirectorToAttach.getClass(), muestradirectorListNewMuestradirectorToAttach.getId());
                attachedMuestradirectorListNew.add(muestradirectorListNewMuestradirectorToAttach);
            }
            muestradirectorListNew = attachedMuestradirectorListNew;
            directorprograma.setMuestradirectorList(muestradirectorListNew);
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
            for (Muestradirector muestradirectorListNewMuestradirector : muestradirectorListNew) {
                if (!muestradirectorListOld.contains(muestradirectorListNewMuestradirector)) {
                    Directorprograma oldDirectorprogramaIdOfMuestradirectorListNewMuestradirector = muestradirectorListNewMuestradirector.getDirectorprogramaId();
                    muestradirectorListNewMuestradirector.setDirectorprogramaId(directorprograma);
                    muestradirectorListNewMuestradirector = em.merge(muestradirectorListNewMuestradirector);
                    if (oldDirectorprogramaIdOfMuestradirectorListNewMuestradirector != null && !oldDirectorprogramaIdOfMuestradirectorListNewMuestradirector.equals(directorprograma)) {
                        oldDirectorprogramaIdOfMuestradirectorListNewMuestradirector.getMuestradirectorList().remove(muestradirectorListNewMuestradirector);
                        oldDirectorprogramaIdOfMuestradirectorListNewMuestradirector = em.merge(oldDirectorprogramaIdOfMuestradirectorListNewMuestradirector);
                    }
                }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<Muestradirector> muestradirectorListOrphanCheck = directorprograma.getMuestradirectorList();
            for (Muestradirector muestradirectorListOrphanCheckMuestradirector : muestradirectorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Directorprograma (" + directorprograma + ") cannot be destroyed since the Muestradirector " + muestradirectorListOrphanCheckMuestradirector + " in its muestradirectorList field has a non-nullable directorprogramaId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
