/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import connection.jpaConnection;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Programa;
import entity.Persona;
import entity.Representante;
import entity.Representantehasprivilegio;
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
public class RepresentanteJpaController implements Serializable {

   public RepresentanteJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Representante representante) {
        if (representante.getRepresentantehasprivilegioList() == null) {
            representante.setRepresentantehasprivilegioList(new ArrayList<Representantehasprivilegio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa programaId = representante.getProgramaId();
            if (programaId != null) {
                programaId = em.getReference(programaId.getClass(), programaId.getId());
                representante.setProgramaId(programaId);
            }
            Persona personaId = representante.getPersonaId();
            if (personaId != null) {
                personaId = em.getReference(personaId.getClass(), personaId.getId());
                representante.setPersonaId(personaId);
            }
            List<Representantehasprivilegio> attachedRepresentantehasprivilegioList = new ArrayList<Representantehasprivilegio>();
            for (Representantehasprivilegio representantehasprivilegioListRepresentantehasprivilegioToAttach : representante.getRepresentantehasprivilegioList()) {
                representantehasprivilegioListRepresentantehasprivilegioToAttach = em.getReference(representantehasprivilegioListRepresentantehasprivilegioToAttach.getClass(), representantehasprivilegioListRepresentantehasprivilegioToAttach.getId());
                attachedRepresentantehasprivilegioList.add(representantehasprivilegioListRepresentantehasprivilegioToAttach);
            }
            representante.setRepresentantehasprivilegioList(attachedRepresentantehasprivilegioList);
            em.persist(representante);
            if (programaId != null) {
                programaId.getRepresentanteList().add(representante);
                programaId = em.merge(programaId);
            }
            if (personaId != null) {
                personaId.getRepresentanteList().add(representante);
                personaId = em.merge(personaId);
            }
            for (Representantehasprivilegio representantehasprivilegioListRepresentantehasprivilegio : representante.getRepresentantehasprivilegioList()) {
                Representante oldRepresentanteIdOfRepresentantehasprivilegioListRepresentantehasprivilegio = representantehasprivilegioListRepresentantehasprivilegio.getRepresentanteId();
                representantehasprivilegioListRepresentantehasprivilegio.setRepresentanteId(representante);
                representantehasprivilegioListRepresentantehasprivilegio = em.merge(representantehasprivilegioListRepresentantehasprivilegio);
                if (oldRepresentanteIdOfRepresentantehasprivilegioListRepresentantehasprivilegio != null) {
                    oldRepresentanteIdOfRepresentantehasprivilegioListRepresentantehasprivilegio.getRepresentantehasprivilegioList().remove(representantehasprivilegioListRepresentantehasprivilegio);
                    oldRepresentanteIdOfRepresentantehasprivilegioListRepresentantehasprivilegio = em.merge(oldRepresentanteIdOfRepresentantehasprivilegioListRepresentantehasprivilegio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Representante representante) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Representante persistentRepresentante = em.find(Representante.class, representante.getId());
            Programa programaIdOld = persistentRepresentante.getProgramaId();
            Programa programaIdNew = representante.getProgramaId();
            Persona personaIdOld = persistentRepresentante.getPersonaId();
            Persona personaIdNew = representante.getPersonaId();
            List<Representantehasprivilegio> representantehasprivilegioListOld = persistentRepresentante.getRepresentantehasprivilegioList();
            List<Representantehasprivilegio> representantehasprivilegioListNew = representante.getRepresentantehasprivilegioList();
            List<String> illegalOrphanMessages = null;
            for (Representantehasprivilegio representantehasprivilegioListOldRepresentantehasprivilegio : representantehasprivilegioListOld) {
                if (!representantehasprivilegioListNew.contains(representantehasprivilegioListOldRepresentantehasprivilegio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Representantehasprivilegio " + representantehasprivilegioListOldRepresentantehasprivilegio + " since its representanteId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (programaIdNew != null) {
                programaIdNew = em.getReference(programaIdNew.getClass(), programaIdNew.getId());
                representante.setProgramaId(programaIdNew);
            }
            if (personaIdNew != null) {
                personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
                representante.setPersonaId(personaIdNew);
            }
            List<Representantehasprivilegio> attachedRepresentantehasprivilegioListNew = new ArrayList<Representantehasprivilegio>();
            for (Representantehasprivilegio representantehasprivilegioListNewRepresentantehasprivilegioToAttach : representantehasprivilegioListNew) {
                representantehasprivilegioListNewRepresentantehasprivilegioToAttach = em.getReference(representantehasprivilegioListNewRepresentantehasprivilegioToAttach.getClass(), representantehasprivilegioListNewRepresentantehasprivilegioToAttach.getId());
                attachedRepresentantehasprivilegioListNew.add(representantehasprivilegioListNewRepresentantehasprivilegioToAttach);
            }
            representantehasprivilegioListNew = attachedRepresentantehasprivilegioListNew;
            representante.setRepresentantehasprivilegioList(representantehasprivilegioListNew);
            representante = em.merge(representante);
            if (programaIdOld != null && !programaIdOld.equals(programaIdNew)) {
                programaIdOld.getRepresentanteList().remove(representante);
                programaIdOld = em.merge(programaIdOld);
            }
            if (programaIdNew != null && !programaIdNew.equals(programaIdOld)) {
                programaIdNew.getRepresentanteList().add(representante);
                programaIdNew = em.merge(programaIdNew);
            }
            if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
                personaIdOld.getRepresentanteList().remove(representante);
                personaIdOld = em.merge(personaIdOld);
            }
            if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
                personaIdNew.getRepresentanteList().add(representante);
                personaIdNew = em.merge(personaIdNew);
            }
            for (Representantehasprivilegio representantehasprivilegioListNewRepresentantehasprivilegio : representantehasprivilegioListNew) {
                if (!representantehasprivilegioListOld.contains(representantehasprivilegioListNewRepresentantehasprivilegio)) {
                    Representante oldRepresentanteIdOfRepresentantehasprivilegioListNewRepresentantehasprivilegio = representantehasprivilegioListNewRepresentantehasprivilegio.getRepresentanteId();
                    representantehasprivilegioListNewRepresentantehasprivilegio.setRepresentanteId(representante);
                    representantehasprivilegioListNewRepresentantehasprivilegio = em.merge(representantehasprivilegioListNewRepresentantehasprivilegio);
                    if (oldRepresentanteIdOfRepresentantehasprivilegioListNewRepresentantehasprivilegio != null && !oldRepresentanteIdOfRepresentantehasprivilegioListNewRepresentantehasprivilegio.equals(representante)) {
                        oldRepresentanteIdOfRepresentantehasprivilegioListNewRepresentantehasprivilegio.getRepresentantehasprivilegioList().remove(representantehasprivilegioListNewRepresentantehasprivilegio);
                        oldRepresentanteIdOfRepresentantehasprivilegioListNewRepresentantehasprivilegio = em.merge(oldRepresentanteIdOfRepresentantehasprivilegioListNewRepresentantehasprivilegio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = representante.getId();
                if (findRepresentante(id) == null) {
                    throw new NonexistentEntityException("The representante with id " + id + " no longer exists.");
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
            Representante representante;
            try {
                representante = em.getReference(Representante.class, id);
                representante.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The representante with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Representantehasprivilegio> representantehasprivilegioListOrphanCheck = representante.getRepresentantehasprivilegioList();
            for (Representantehasprivilegio representantehasprivilegioListOrphanCheckRepresentantehasprivilegio : representantehasprivilegioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Representante (" + representante + ") cannot be destroyed since the Representantehasprivilegio " + representantehasprivilegioListOrphanCheckRepresentantehasprivilegio + " in its representantehasprivilegioList field has a non-nullable representanteId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Programa programaId = representante.getProgramaId();
            if (programaId != null) {
                programaId.getRepresentanteList().remove(representante);
                programaId = em.merge(programaId);
            }
            Persona personaId = representante.getPersonaId();
            if (personaId != null) {
                personaId.getRepresentanteList().remove(representante);
                personaId = em.merge(personaId);
            }
            em.remove(representante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Representante> findRepresentanteEntities() {
        return findRepresentanteEntities(true, -1, -1);
    }

    public List<Representante> findRepresentanteEntities(int maxResults, int firstResult) {
        return findRepresentanteEntities(false, maxResults, firstResult);
    }

    private List<Representante> findRepresentanteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Representante.class));
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

    public Representante findRepresentante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Representante.class, id);
        } finally {
            em.close();
        }
    }

    public int getRepresentanteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Representante> rt = cq.from(Representante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
