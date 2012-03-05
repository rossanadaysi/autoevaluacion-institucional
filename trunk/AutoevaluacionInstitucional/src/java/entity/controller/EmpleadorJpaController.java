/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import entity.Empleador;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Persona;
import entity.Sectorempresarial;
import entity.Fuente;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Usuario
 */
public class EmpleadorJpaController implements Serializable {

    public EmpleadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleador empleador) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona personaId = empleador.getPersonaId();
            if (personaId != null) {
                personaId = em.getReference(personaId.getClass(), personaId.getId());
                empleador.setPersonaId(personaId);
            }
            Sectorempresarial sectorempresarialId = empleador.getSectorempresarialId();
            if (sectorempresarialId != null) {
                sectorempresarialId = em.getReference(sectorempresarialId.getClass(), sectorempresarialId.getId());
                empleador.setSectorempresarialId(sectorempresarialId);
            }
            Fuente fuenteId = empleador.getFuenteId();
            if (fuenteId != null) {
                fuenteId = em.getReference(fuenteId.getClass(), fuenteId.getId());
                empleador.setFuenteId(fuenteId);
            }
            em.persist(empleador);
            if (personaId != null) {
                personaId.getEmpleadorList().add(empleador);
                personaId = em.merge(personaId);
            }
            if (sectorempresarialId != null) {
                sectorempresarialId.getEmpleadorList().add(empleador);
                sectorempresarialId = em.merge(sectorempresarialId);
            }
            if (fuenteId != null) {
                fuenteId.getEmpleadorList().add(empleador);
                fuenteId = em.merge(fuenteId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleador empleador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleador persistentEmpleador = em.find(Empleador.class, empleador.getId());
            Persona personaIdOld = persistentEmpleador.getPersonaId();
            Persona personaIdNew = empleador.getPersonaId();
            Sectorempresarial sectorempresarialIdOld = persistentEmpleador.getSectorempresarialId();
            Sectorempresarial sectorempresarialIdNew = empleador.getSectorempresarialId();
            Fuente fuenteIdOld = persistentEmpleador.getFuenteId();
            Fuente fuenteIdNew = empleador.getFuenteId();
            if (personaIdNew != null) {
                personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
                empleador.setPersonaId(personaIdNew);
            }
            if (sectorempresarialIdNew != null) {
                sectorempresarialIdNew = em.getReference(sectorempresarialIdNew.getClass(), sectorempresarialIdNew.getId());
                empleador.setSectorempresarialId(sectorempresarialIdNew);
            }
            if (fuenteIdNew != null) {
                fuenteIdNew = em.getReference(fuenteIdNew.getClass(), fuenteIdNew.getId());
                empleador.setFuenteId(fuenteIdNew);
            }
            empleador = em.merge(empleador);
            if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
                personaIdOld.getEmpleadorList().remove(empleador);
                personaIdOld = em.merge(personaIdOld);
            }
            if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
                personaIdNew.getEmpleadorList().add(empleador);
                personaIdNew = em.merge(personaIdNew);
            }
            if (sectorempresarialIdOld != null && !sectorempresarialIdOld.equals(sectorempresarialIdNew)) {
                sectorempresarialIdOld.getEmpleadorList().remove(empleador);
                sectorempresarialIdOld = em.merge(sectorempresarialIdOld);
            }
            if (sectorempresarialIdNew != null && !sectorempresarialIdNew.equals(sectorempresarialIdOld)) {
                sectorempresarialIdNew.getEmpleadorList().add(empleador);
                sectorempresarialIdNew = em.merge(sectorempresarialIdNew);
            }
            if (fuenteIdOld != null && !fuenteIdOld.equals(fuenteIdNew)) {
                fuenteIdOld.getEmpleadorList().remove(empleador);
                fuenteIdOld = em.merge(fuenteIdOld);
            }
            if (fuenteIdNew != null && !fuenteIdNew.equals(fuenteIdOld)) {
                fuenteIdNew.getEmpleadorList().add(empleador);
                fuenteIdNew = em.merge(fuenteIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empleador.getId();
                if (findEmpleador(id) == null) {
                    throw new NonexistentEntityException("The empleador with id " + id + " no longer exists.");
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
            Empleador empleador;
            try {
                empleador = em.getReference(Empleador.class, id);
                empleador.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleador with id " + id + " no longer exists.", enfe);
            }
            Persona personaId = empleador.getPersonaId();
            if (personaId != null) {
                personaId.getEmpleadorList().remove(empleador);
                personaId = em.merge(personaId);
            }
            Sectorempresarial sectorempresarialId = empleador.getSectorempresarialId();
            if (sectorempresarialId != null) {
                sectorempresarialId.getEmpleadorList().remove(empleador);
                sectorempresarialId = em.merge(sectorempresarialId);
            }
            Fuente fuenteId = empleador.getFuenteId();
            if (fuenteId != null) {
                fuenteId.getEmpleadorList().remove(empleador);
                fuenteId = em.merge(fuenteId);
            }
            em.remove(empleador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleador> findEmpleadorEntities() {
        return findEmpleadorEntities(true, -1, -1);
    }

    public List<Empleador> findEmpleadorEntities(int maxResults, int firstResult) {
        return findEmpleadorEntities(false, maxResults, firstResult);
    }

    private List<Empleador> findEmpleadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleador.class));
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

    public Empleador findEmpleador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleador.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleador> rt = cq.from(Empleador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
