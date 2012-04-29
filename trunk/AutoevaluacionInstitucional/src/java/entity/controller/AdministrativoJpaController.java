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
public class AdministrativoJpaController implements Serializable {

    public AdministrativoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Administrativo administrativo) {
        if (administrativo.getMuestraadministrativoList() == null) {
            administrativo.setMuestraadministrativoList(new ArrayList<Muestraadministrativo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa programaId = administrativo.getProgramaId();
            if (programaId != null) {
                programaId = em.getReference(programaId.getClass(), programaId.getId());
                administrativo.setProgramaId(programaId);
            }
            Persona personaId = administrativo.getPersonaId();
            if (personaId != null) {
                personaId = em.getReference(personaId.getClass(), personaId.getId());
                administrativo.setPersonaId(personaId);
            }
            Fuente fuenteId = administrativo.getFuenteId();
            if (fuenteId != null) {
                fuenteId = em.getReference(fuenteId.getClass(), fuenteId.getId());
                administrativo.setFuenteId(fuenteId);
            }
            List<Muestraadministrativo> attachedMuestraadministrativoList = new ArrayList<Muestraadministrativo>();
            for (Muestraadministrativo muestraadministrativoListMuestraadministrativoToAttach : administrativo.getMuestraadministrativoList()) {
                muestraadministrativoListMuestraadministrativoToAttach = em.getReference(muestraadministrativoListMuestraadministrativoToAttach.getClass(), muestraadministrativoListMuestraadministrativoToAttach.getId());
                attachedMuestraadministrativoList.add(muestraadministrativoListMuestraadministrativoToAttach);
            }
            administrativo.setMuestraadministrativoList(attachedMuestraadministrativoList);
            em.persist(administrativo);
            if (programaId != null) {
                programaId.getAdministrativoList().add(administrativo);
                programaId = em.merge(programaId);
            }
            if (personaId != null) {
                personaId.getAdministrativoList().add(administrativo);
                personaId = em.merge(personaId);
            }
            if (fuenteId != null) {
                fuenteId.getAdministrativoList().add(administrativo);
                fuenteId = em.merge(fuenteId);
            }
            for (Muestraadministrativo muestraadministrativoListMuestraadministrativo : administrativo.getMuestraadministrativoList()) {
                Administrativo oldAdministrativoIdOfMuestraadministrativoListMuestraadministrativo = muestraadministrativoListMuestraadministrativo.getAdministrativoId();
                muestraadministrativoListMuestraadministrativo.setAdministrativoId(administrativo);
                muestraadministrativoListMuestraadministrativo = em.merge(muestraadministrativoListMuestraadministrativo);
                if (oldAdministrativoIdOfMuestraadministrativoListMuestraadministrativo != null) {
                    oldAdministrativoIdOfMuestraadministrativoListMuestraadministrativo.getMuestraadministrativoList().remove(muestraadministrativoListMuestraadministrativo);
                    oldAdministrativoIdOfMuestraadministrativoListMuestraadministrativo = em.merge(oldAdministrativoIdOfMuestraadministrativoListMuestraadministrativo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Administrativo administrativo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrativo persistentAdministrativo = em.find(Administrativo.class, administrativo.getId());
            Programa programaIdOld = persistentAdministrativo.getProgramaId();
            Programa programaIdNew = administrativo.getProgramaId();
            Persona personaIdOld = persistentAdministrativo.getPersonaId();
            Persona personaIdNew = administrativo.getPersonaId();
            Fuente fuenteIdOld = persistentAdministrativo.getFuenteId();
            Fuente fuenteIdNew = administrativo.getFuenteId();
            List<Muestraadministrativo> muestraadministrativoListOld = persistentAdministrativo.getMuestraadministrativoList();
            List<Muestraadministrativo> muestraadministrativoListNew = administrativo.getMuestraadministrativoList();
            List<String> illegalOrphanMessages = null;
            for (Muestraadministrativo muestraadministrativoListOldMuestraadministrativo : muestraadministrativoListOld) {
                if (!muestraadministrativoListNew.contains(muestraadministrativoListOldMuestraadministrativo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Muestraadministrativo " + muestraadministrativoListOldMuestraadministrativo + " since its administrativoId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (programaIdNew != null) {
                programaIdNew = em.getReference(programaIdNew.getClass(), programaIdNew.getId());
                administrativo.setProgramaId(programaIdNew);
            }
            if (personaIdNew != null) {
                personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
                administrativo.setPersonaId(personaIdNew);
            }
            if (fuenteIdNew != null) {
                fuenteIdNew = em.getReference(fuenteIdNew.getClass(), fuenteIdNew.getId());
                administrativo.setFuenteId(fuenteIdNew);
            }
            List<Muestraadministrativo> attachedMuestraadministrativoListNew = new ArrayList<Muestraadministrativo>();
            for (Muestraadministrativo muestraadministrativoListNewMuestraadministrativoToAttach : muestraadministrativoListNew) {
                muestraadministrativoListNewMuestraadministrativoToAttach = em.getReference(muestraadministrativoListNewMuestraadministrativoToAttach.getClass(), muestraadministrativoListNewMuestraadministrativoToAttach.getId());
                attachedMuestraadministrativoListNew.add(muestraadministrativoListNewMuestraadministrativoToAttach);
            }
            muestraadministrativoListNew = attachedMuestraadministrativoListNew;
            administrativo.setMuestraadministrativoList(muestraadministrativoListNew);
            administrativo = em.merge(administrativo);
            if (programaIdOld != null && !programaIdOld.equals(programaIdNew)) {
                programaIdOld.getAdministrativoList().remove(administrativo);
                programaIdOld = em.merge(programaIdOld);
            }
            if (programaIdNew != null && !programaIdNew.equals(programaIdOld)) {
                programaIdNew.getAdministrativoList().add(administrativo);
                programaIdNew = em.merge(programaIdNew);
            }
            if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
                personaIdOld.getAdministrativoList().remove(administrativo);
                personaIdOld = em.merge(personaIdOld);
            }
            if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
                personaIdNew.getAdministrativoList().add(administrativo);
                personaIdNew = em.merge(personaIdNew);
            }
            if (fuenteIdOld != null && !fuenteIdOld.equals(fuenteIdNew)) {
                fuenteIdOld.getAdministrativoList().remove(administrativo);
                fuenteIdOld = em.merge(fuenteIdOld);
            }
            if (fuenteIdNew != null && !fuenteIdNew.equals(fuenteIdOld)) {
                fuenteIdNew.getAdministrativoList().add(administrativo);
                fuenteIdNew = em.merge(fuenteIdNew);
            }
            for (Muestraadministrativo muestraadministrativoListNewMuestraadministrativo : muestraadministrativoListNew) {
                if (!muestraadministrativoListOld.contains(muestraadministrativoListNewMuestraadministrativo)) {
                    Administrativo oldAdministrativoIdOfMuestraadministrativoListNewMuestraadministrativo = muestraadministrativoListNewMuestraadministrativo.getAdministrativoId();
                    muestraadministrativoListNewMuestraadministrativo.setAdministrativoId(administrativo);
                    muestraadministrativoListNewMuestraadministrativo = em.merge(muestraadministrativoListNewMuestraadministrativo);
                    if (oldAdministrativoIdOfMuestraadministrativoListNewMuestraadministrativo != null && !oldAdministrativoIdOfMuestraadministrativoListNewMuestraadministrativo.equals(administrativo)) {
                        oldAdministrativoIdOfMuestraadministrativoListNewMuestraadministrativo.getMuestraadministrativoList().remove(muestraadministrativoListNewMuestraadministrativo);
                        oldAdministrativoIdOfMuestraadministrativoListNewMuestraadministrativo = em.merge(oldAdministrativoIdOfMuestraadministrativoListNewMuestraadministrativo);
                    }
                }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<Muestraadministrativo> muestraadministrativoListOrphanCheck = administrativo.getMuestraadministrativoList();
            for (Muestraadministrativo muestraadministrativoListOrphanCheckMuestraadministrativo : muestraadministrativoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Administrativo (" + administrativo + ") cannot be destroyed since the Muestraadministrativo " + muestraadministrativoListOrphanCheckMuestraadministrativo + " in its muestraadministrativoList field has a non-nullable administrativoId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Programa programaId = administrativo.getProgramaId();
            if (programaId != null) {
                programaId.getAdministrativoList().remove(administrativo);
                programaId = em.merge(programaId);
            }
            Persona personaId = administrativo.getPersonaId();
            if (personaId != null) {
                personaId.getAdministrativoList().remove(administrativo);
                personaId = em.merge(personaId);
            }
            Fuente fuenteId = administrativo.getFuenteId();
            if (fuenteId != null) {
                fuenteId.getAdministrativoList().remove(administrativo);
                fuenteId = em.merge(fuenteId);
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
