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
 * @author Ususario
 */
public class DocenteJpaController implements Serializable {

    public DocenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Docente docente) {
        if (docente.getMuestradocenteList() == null) {
            docente.setMuestradocenteList(new ArrayList<Muestradocente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa programaId = docente.getProgramaId();
            if (programaId != null) {
                programaId = em.getReference(programaId.getClass(), programaId.getId());
                docente.setProgramaId(programaId);
            }
            Persona personaId = docente.getPersonaId();
            if (personaId != null) {
                personaId = em.getReference(personaId.getClass(), personaId.getId());
                docente.setPersonaId(personaId);
            }
            Fuente fuenteId = docente.getFuenteId();
            if (fuenteId != null) {
                fuenteId = em.getReference(fuenteId.getClass(), fuenteId.getId());
                docente.setFuenteId(fuenteId);
            }
            List<Muestradocente> attachedMuestradocenteList = new ArrayList<Muestradocente>();
            for (Muestradocente muestradocenteListMuestradocenteToAttach : docente.getMuestradocenteList()) {
                muestradocenteListMuestradocenteToAttach = em.getReference(muestradocenteListMuestradocenteToAttach.getClass(), muestradocenteListMuestradocenteToAttach.getId());
                attachedMuestradocenteList.add(muestradocenteListMuestradocenteToAttach);
            }
            docente.setMuestradocenteList(attachedMuestradocenteList);
            em.persist(docente);
            if (programaId != null) {
                programaId.getDocenteList().add(docente);
                programaId = em.merge(programaId);
            }
            if (personaId != null) {
                personaId.getDocenteList().add(docente);
                personaId = em.merge(personaId);
            }
            if (fuenteId != null) {
                fuenteId.getDocenteList().add(docente);
                fuenteId = em.merge(fuenteId);
            }
            for (Muestradocente muestradocenteListMuestradocente : docente.getMuestradocenteList()) {
                Docente oldDocenteIdOfMuestradocenteListMuestradocente = muestradocenteListMuestradocente.getDocenteId();
                muestradocenteListMuestradocente.setDocenteId(docente);
                muestradocenteListMuestradocente = em.merge(muestradocenteListMuestradocente);
                if (oldDocenteIdOfMuestradocenteListMuestradocente != null) {
                    oldDocenteIdOfMuestradocenteListMuestradocente.getMuestradocenteList().remove(muestradocenteListMuestradocente);
                    oldDocenteIdOfMuestradocenteListMuestradocente = em.merge(oldDocenteIdOfMuestradocenteListMuestradocente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Docente docente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docente persistentDocente = em.find(Docente.class, docente.getId());
            Programa programaIdOld = persistentDocente.getProgramaId();
            Programa programaIdNew = docente.getProgramaId();
            Persona personaIdOld = persistentDocente.getPersonaId();
            Persona personaIdNew = docente.getPersonaId();
            Fuente fuenteIdOld = persistentDocente.getFuenteId();
            Fuente fuenteIdNew = docente.getFuenteId();
            List<Muestradocente> muestradocenteListOld = persistentDocente.getMuestradocenteList();
            List<Muestradocente> muestradocenteListNew = docente.getMuestradocenteList();
            List<String> illegalOrphanMessages = null;
            for (Muestradocente muestradocenteListOldMuestradocente : muestradocenteListOld) {
                if (!muestradocenteListNew.contains(muestradocenteListOldMuestradocente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Muestradocente " + muestradocenteListOldMuestradocente + " since its docenteId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (programaIdNew != null) {
                programaIdNew = em.getReference(programaIdNew.getClass(), programaIdNew.getId());
                docente.setProgramaId(programaIdNew);
            }
            if (personaIdNew != null) {
                personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
                docente.setPersonaId(personaIdNew);
            }
            if (fuenteIdNew != null) {
                fuenteIdNew = em.getReference(fuenteIdNew.getClass(), fuenteIdNew.getId());
                docente.setFuenteId(fuenteIdNew);
            }
            List<Muestradocente> attachedMuestradocenteListNew = new ArrayList<Muestradocente>();
            for (Muestradocente muestradocenteListNewMuestradocenteToAttach : muestradocenteListNew) {
                muestradocenteListNewMuestradocenteToAttach = em.getReference(muestradocenteListNewMuestradocenteToAttach.getClass(), muestradocenteListNewMuestradocenteToAttach.getId());
                attachedMuestradocenteListNew.add(muestradocenteListNewMuestradocenteToAttach);
            }
            muestradocenteListNew = attachedMuestradocenteListNew;
            docente.setMuestradocenteList(muestradocenteListNew);
            docente = em.merge(docente);
            if (programaIdOld != null && !programaIdOld.equals(programaIdNew)) {
                programaIdOld.getDocenteList().remove(docente);
                programaIdOld = em.merge(programaIdOld);
            }
            if (programaIdNew != null && !programaIdNew.equals(programaIdOld)) {
                programaIdNew.getDocenteList().add(docente);
                programaIdNew = em.merge(programaIdNew);
            }
            if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
                personaIdOld.getDocenteList().remove(docente);
                personaIdOld = em.merge(personaIdOld);
            }
            if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
                personaIdNew.getDocenteList().add(docente);
                personaIdNew = em.merge(personaIdNew);
            }
            if (fuenteIdOld != null && !fuenteIdOld.equals(fuenteIdNew)) {
                fuenteIdOld.getDocenteList().remove(docente);
                fuenteIdOld = em.merge(fuenteIdOld);
            }
            if (fuenteIdNew != null && !fuenteIdNew.equals(fuenteIdOld)) {
                fuenteIdNew.getDocenteList().add(docente);
                fuenteIdNew = em.merge(fuenteIdNew);
            }
            for (Muestradocente muestradocenteListNewMuestradocente : muestradocenteListNew) {
                if (!muestradocenteListOld.contains(muestradocenteListNewMuestradocente)) {
                    Docente oldDocenteIdOfMuestradocenteListNewMuestradocente = muestradocenteListNewMuestradocente.getDocenteId();
                    muestradocenteListNewMuestradocente.setDocenteId(docente);
                    muestradocenteListNewMuestradocente = em.merge(muestradocenteListNewMuestradocente);
                    if (oldDocenteIdOfMuestradocenteListNewMuestradocente != null && !oldDocenteIdOfMuestradocenteListNewMuestradocente.equals(docente)) {
                        oldDocenteIdOfMuestradocenteListNewMuestradocente.getMuestradocenteList().remove(muestradocenteListNewMuestradocente);
                        oldDocenteIdOfMuestradocenteListNewMuestradocente = em.merge(oldDocenteIdOfMuestradocenteListNewMuestradocente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = docente.getId();
                if (findDocente(id) == null) {
                    throw new NonexistentEntityException("The docente with id " + id + " no longer exists.");
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
            Docente docente;
            try {
                docente = em.getReference(Docente.class, id);
                docente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The docente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Muestradocente> muestradocenteListOrphanCheck = docente.getMuestradocenteList();
            for (Muestradocente muestradocenteListOrphanCheckMuestradocente : muestradocenteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Docente (" + docente + ") cannot be destroyed since the Muestradocente " + muestradocenteListOrphanCheckMuestradocente + " in its muestradocenteList field has a non-nullable docenteId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Programa programaId = docente.getProgramaId();
            if (programaId != null) {
                programaId.getDocenteList().remove(docente);
                programaId = em.merge(programaId);
            }
            Persona personaId = docente.getPersonaId();
            if (personaId != null) {
                personaId.getDocenteList().remove(docente);
                personaId = em.merge(personaId);
            }
            Fuente fuenteId = docente.getFuenteId();
            if (fuenteId != null) {
                fuenteId.getDocenteList().remove(docente);
                fuenteId = em.merge(fuenteId);
            }
            em.remove(docente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Docente> findDocenteEntities() {
        return findDocenteEntities(true, -1, -1);
    }

    public List<Docente> findDocenteEntities(int maxResults, int firstResult) {
        return findDocenteEntities(false, maxResults, firstResult);
    }

    private List<Docente> findDocenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Docente.class));
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

    public Docente findDocente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Docente.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Docente> rt = cq.from(Docente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
