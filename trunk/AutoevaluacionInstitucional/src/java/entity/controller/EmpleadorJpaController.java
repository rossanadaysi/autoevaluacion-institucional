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
import entity.Fuente;
import entity.Muestraempleador;
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
public class EmpleadorJpaController implements Serializable {

    public EmpleadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleador empleador) {
        if (empleador.getMuestraempleadorList() == null) {
            empleador.setMuestraempleadorList(new ArrayList<Muestraempleador>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona personaId = empleador.getPersonaId();
            if (personaId != null) {
                personaId = em.getReference(personaId.getClass(), personaId.getId());
                empleador.setPersonaId(personaId);
            }
            Fuente fuenteId = empleador.getFuenteId();
            if (fuenteId != null) {
                fuenteId = em.getReference(fuenteId.getClass(), fuenteId.getId());
                empleador.setFuenteId(fuenteId);
            }
            List<Muestraempleador> attachedMuestraempleadorList = new ArrayList<Muestraempleador>();
            for (Muestraempleador muestraempleadorListMuestraempleadorToAttach : empleador.getMuestraempleadorList()) {
                muestraempleadorListMuestraempleadorToAttach = em.getReference(muestraempleadorListMuestraempleadorToAttach.getClass(), muestraempleadorListMuestraempleadorToAttach.getId());
                attachedMuestraempleadorList.add(muestraempleadorListMuestraempleadorToAttach);
            }
            empleador.setMuestraempleadorList(attachedMuestraempleadorList);
            em.persist(empleador);
            if (personaId != null) {
                personaId.getEmpleadorList().add(empleador);
                personaId = em.merge(personaId);
            }
            if (fuenteId != null) {
                fuenteId.getEmpleadorList().add(empleador);
                fuenteId = em.merge(fuenteId);
            }
            for (Muestraempleador muestraempleadorListMuestraempleador : empleador.getMuestraempleadorList()) {
                Empleador oldEmpleadorIdOfMuestraempleadorListMuestraempleador = muestraempleadorListMuestraempleador.getEmpleadorId();
                muestraempleadorListMuestraempleador.setEmpleadorId(empleador);
                muestraempleadorListMuestraempleador = em.merge(muestraempleadorListMuestraempleador);
                if (oldEmpleadorIdOfMuestraempleadorListMuestraempleador != null) {
                    oldEmpleadorIdOfMuestraempleadorListMuestraempleador.getMuestraempleadorList().remove(muestraempleadorListMuestraempleador);
                    oldEmpleadorIdOfMuestraempleadorListMuestraempleador = em.merge(oldEmpleadorIdOfMuestraempleadorListMuestraempleador);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleador empleador) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleador persistentEmpleador = em.find(Empleador.class, empleador.getId());
            Persona personaIdOld = persistentEmpleador.getPersonaId();
            Persona personaIdNew = empleador.getPersonaId();
            Fuente fuenteIdOld = persistentEmpleador.getFuenteId();
            Fuente fuenteIdNew = empleador.getFuenteId();
            List<Muestraempleador> muestraempleadorListOld = persistentEmpleador.getMuestraempleadorList();
            List<Muestraempleador> muestraempleadorListNew = empleador.getMuestraempleadorList();
            List<String> illegalOrphanMessages = null;
            for (Muestraempleador muestraempleadorListOldMuestraempleador : muestraempleadorListOld) {
                if (!muestraempleadorListNew.contains(muestraempleadorListOldMuestraempleador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Muestraempleador " + muestraempleadorListOldMuestraempleador + " since its empleadorId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (personaIdNew != null) {
                personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
                empleador.setPersonaId(personaIdNew);
            }
            if (fuenteIdNew != null) {
                fuenteIdNew = em.getReference(fuenteIdNew.getClass(), fuenteIdNew.getId());
                empleador.setFuenteId(fuenteIdNew);
            }
            List<Muestraempleador> attachedMuestraempleadorListNew = new ArrayList<Muestraempleador>();
            for (Muestraempleador muestraempleadorListNewMuestraempleadorToAttach : muestraempleadorListNew) {
                muestraempleadorListNewMuestraempleadorToAttach = em.getReference(muestraempleadorListNewMuestraempleadorToAttach.getClass(), muestraempleadorListNewMuestraempleadorToAttach.getId());
                attachedMuestraempleadorListNew.add(muestraempleadorListNewMuestraempleadorToAttach);
            }
            muestraempleadorListNew = attachedMuestraempleadorListNew;
            empleador.setMuestraempleadorList(muestraempleadorListNew);
            empleador = em.merge(empleador);
            if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
                personaIdOld.getEmpleadorList().remove(empleador);
                personaIdOld = em.merge(personaIdOld);
            }
            if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
                personaIdNew.getEmpleadorList().add(empleador);
                personaIdNew = em.merge(personaIdNew);
            }
            if (fuenteIdOld != null && !fuenteIdOld.equals(fuenteIdNew)) {
                fuenteIdOld.getEmpleadorList().remove(empleador);
                fuenteIdOld = em.merge(fuenteIdOld);
            }
            if (fuenteIdNew != null && !fuenteIdNew.equals(fuenteIdOld)) {
                fuenteIdNew.getEmpleadorList().add(empleador);
                fuenteIdNew = em.merge(fuenteIdNew);
            }
            for (Muestraempleador muestraempleadorListNewMuestraempleador : muestraempleadorListNew) {
                if (!muestraempleadorListOld.contains(muestraempleadorListNewMuestraempleador)) {
                    Empleador oldEmpleadorIdOfMuestraempleadorListNewMuestraempleador = muestraempleadorListNewMuestraempleador.getEmpleadorId();
                    muestraempleadorListNewMuestraempleador.setEmpleadorId(empleador);
                    muestraempleadorListNewMuestraempleador = em.merge(muestraempleadorListNewMuestraempleador);
                    if (oldEmpleadorIdOfMuestraempleadorListNewMuestraempleador != null && !oldEmpleadorIdOfMuestraempleadorListNewMuestraempleador.equals(empleador)) {
                        oldEmpleadorIdOfMuestraempleadorListNewMuestraempleador.getMuestraempleadorList().remove(muestraempleadorListNewMuestraempleador);
                        oldEmpleadorIdOfMuestraempleadorListNewMuestraempleador = em.merge(oldEmpleadorIdOfMuestraempleadorListNewMuestraempleador);
                    }
                }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<Muestraempleador> muestraempleadorListOrphanCheck = empleador.getMuestraempleadorList();
            for (Muestraempleador muestraempleadorListOrphanCheckMuestraempleador : muestraempleadorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleador (" + empleador + ") cannot be destroyed since the Muestraempleador " + muestraempleadorListOrphanCheckMuestraempleador + " in its muestraempleadorList field has a non-nullable empleadorId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Persona personaId = empleador.getPersonaId();
            if (personaId != null) {
                personaId.getEmpleadorList().remove(empleador);
                personaId = em.merge(personaId);
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
