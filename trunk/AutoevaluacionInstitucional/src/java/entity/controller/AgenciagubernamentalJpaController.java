/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import entity.Agenciagubernamental;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Persona;
import entity.Fuente;
import entity.Muestraagencia;
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
public class AgenciagubernamentalJpaController implements Serializable {

    public AgenciagubernamentalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Agenciagubernamental agenciagubernamental) {
        if (agenciagubernamental.getMuestraagenciaList() == null) {
            agenciagubernamental.setMuestraagenciaList(new ArrayList<Muestraagencia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona personaId = agenciagubernamental.getPersonaId();
            if (personaId != null) {
                personaId = em.getReference(personaId.getClass(), personaId.getId());
                agenciagubernamental.setPersonaId(personaId);
            }
            Fuente fuenteId = agenciagubernamental.getFuenteId();
            if (fuenteId != null) {
                fuenteId = em.getReference(fuenteId.getClass(), fuenteId.getId());
                agenciagubernamental.setFuenteId(fuenteId);
            }
            List<Muestraagencia> attachedMuestraagenciaList = new ArrayList<Muestraagencia>();
            for (Muestraagencia muestraagenciaListMuestraagenciaToAttach : agenciagubernamental.getMuestraagenciaList()) {
                muestraagenciaListMuestraagenciaToAttach = em.getReference(muestraagenciaListMuestraagenciaToAttach.getClass(), muestraagenciaListMuestraagenciaToAttach.getId());
                attachedMuestraagenciaList.add(muestraagenciaListMuestraagenciaToAttach);
            }
            agenciagubernamental.setMuestraagenciaList(attachedMuestraagenciaList);
            em.persist(agenciagubernamental);
            if (personaId != null) {
                personaId.getAgenciagubernamentalList().add(agenciagubernamental);
                personaId = em.merge(personaId);
            }
            if (fuenteId != null) {
                fuenteId.getAgenciagubernamentalList().add(agenciagubernamental);
                fuenteId = em.merge(fuenteId);
            }
            for (Muestraagencia muestraagenciaListMuestraagencia : agenciagubernamental.getMuestraagenciaList()) {
                Agenciagubernamental oldAgenciagubernamentalIdOfMuestraagenciaListMuestraagencia = muestraagenciaListMuestraagencia.getAgenciagubernamentalId();
                muestraagenciaListMuestraagencia.setAgenciagubernamentalId(agenciagubernamental);
                muestraagenciaListMuestraagencia = em.merge(muestraagenciaListMuestraagencia);
                if (oldAgenciagubernamentalIdOfMuestraagenciaListMuestraagencia != null) {
                    oldAgenciagubernamentalIdOfMuestraagenciaListMuestraagencia.getMuestraagenciaList().remove(muestraagenciaListMuestraagencia);
                    oldAgenciagubernamentalIdOfMuestraagenciaListMuestraagencia = em.merge(oldAgenciagubernamentalIdOfMuestraagenciaListMuestraagencia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Agenciagubernamental agenciagubernamental) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Agenciagubernamental persistentAgenciagubernamental = em.find(Agenciagubernamental.class, agenciagubernamental.getId());
            Persona personaIdOld = persistentAgenciagubernamental.getPersonaId();
            Persona personaIdNew = agenciagubernamental.getPersonaId();
            Fuente fuenteIdOld = persistentAgenciagubernamental.getFuenteId();
            Fuente fuenteIdNew = agenciagubernamental.getFuenteId();
            List<Muestraagencia> muestraagenciaListOld = persistentAgenciagubernamental.getMuestraagenciaList();
            List<Muestraagencia> muestraagenciaListNew = agenciagubernamental.getMuestraagenciaList();
            List<String> illegalOrphanMessages = null;
            for (Muestraagencia muestraagenciaListOldMuestraagencia : muestraagenciaListOld) {
                if (!muestraagenciaListNew.contains(muestraagenciaListOldMuestraagencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Muestraagencia " + muestraagenciaListOldMuestraagencia + " since its agenciagubernamentalId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (personaIdNew != null) {
                personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
                agenciagubernamental.setPersonaId(personaIdNew);
            }
            if (fuenteIdNew != null) {
                fuenteIdNew = em.getReference(fuenteIdNew.getClass(), fuenteIdNew.getId());
                agenciagubernamental.setFuenteId(fuenteIdNew);
            }
            List<Muestraagencia> attachedMuestraagenciaListNew = new ArrayList<Muestraagencia>();
            for (Muestraagencia muestraagenciaListNewMuestraagenciaToAttach : muestraagenciaListNew) {
                muestraagenciaListNewMuestraagenciaToAttach = em.getReference(muestraagenciaListNewMuestraagenciaToAttach.getClass(), muestraagenciaListNewMuestraagenciaToAttach.getId());
                attachedMuestraagenciaListNew.add(muestraagenciaListNewMuestraagenciaToAttach);
            }
            muestraagenciaListNew = attachedMuestraagenciaListNew;
            agenciagubernamental.setMuestraagenciaList(muestraagenciaListNew);
            agenciagubernamental = em.merge(agenciagubernamental);
            if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
                personaIdOld.getAgenciagubernamentalList().remove(agenciagubernamental);
                personaIdOld = em.merge(personaIdOld);
            }
            if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
                personaIdNew.getAgenciagubernamentalList().add(agenciagubernamental);
                personaIdNew = em.merge(personaIdNew);
            }
            if (fuenteIdOld != null && !fuenteIdOld.equals(fuenteIdNew)) {
                fuenteIdOld.getAgenciagubernamentalList().remove(agenciagubernamental);
                fuenteIdOld = em.merge(fuenteIdOld);
            }
            if (fuenteIdNew != null && !fuenteIdNew.equals(fuenteIdOld)) {
                fuenteIdNew.getAgenciagubernamentalList().add(agenciagubernamental);
                fuenteIdNew = em.merge(fuenteIdNew);
            }
            for (Muestraagencia muestraagenciaListNewMuestraagencia : muestraagenciaListNew) {
                if (!muestraagenciaListOld.contains(muestraagenciaListNewMuestraagencia)) {
                    Agenciagubernamental oldAgenciagubernamentalIdOfMuestraagenciaListNewMuestraagencia = muestraagenciaListNewMuestraagencia.getAgenciagubernamentalId();
                    muestraagenciaListNewMuestraagencia.setAgenciagubernamentalId(agenciagubernamental);
                    muestraagenciaListNewMuestraagencia = em.merge(muestraagenciaListNewMuestraagencia);
                    if (oldAgenciagubernamentalIdOfMuestraagenciaListNewMuestraagencia != null && !oldAgenciagubernamentalIdOfMuestraagenciaListNewMuestraagencia.equals(agenciagubernamental)) {
                        oldAgenciagubernamentalIdOfMuestraagenciaListNewMuestraagencia.getMuestraagenciaList().remove(muestraagenciaListNewMuestraagencia);
                        oldAgenciagubernamentalIdOfMuestraagenciaListNewMuestraagencia = em.merge(oldAgenciagubernamentalIdOfMuestraagenciaListNewMuestraagencia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = agenciagubernamental.getId();
                if (findAgenciagubernamental(id) == null) {
                    throw new NonexistentEntityException("The agenciagubernamental with id " + id + " no longer exists.");
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
            Agenciagubernamental agenciagubernamental;
            try {
                agenciagubernamental = em.getReference(Agenciagubernamental.class, id);
                agenciagubernamental.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The agenciagubernamental with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Muestraagencia> muestraagenciaListOrphanCheck = agenciagubernamental.getMuestraagenciaList();
            for (Muestraagencia muestraagenciaListOrphanCheckMuestraagencia : muestraagenciaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Agenciagubernamental (" + agenciagubernamental + ") cannot be destroyed since the Muestraagencia " + muestraagenciaListOrphanCheckMuestraagencia + " in its muestraagenciaList field has a non-nullable agenciagubernamentalId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Persona personaId = agenciagubernamental.getPersonaId();
            if (personaId != null) {
                personaId.getAgenciagubernamentalList().remove(agenciagubernamental);
                personaId = em.merge(personaId);
            }
            Fuente fuenteId = agenciagubernamental.getFuenteId();
            if (fuenteId != null) {
                fuenteId.getAgenciagubernamentalList().remove(agenciagubernamental);
                fuenteId = em.merge(fuenteId);
            }
            em.remove(agenciagubernamental);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Agenciagubernamental> findAgenciagubernamentalEntities() {
        return findAgenciagubernamentalEntities(true, -1, -1);
    }

    public List<Agenciagubernamental> findAgenciagubernamentalEntities(int maxResults, int firstResult) {
        return findAgenciagubernamentalEntities(false, maxResults, firstResult);
    }

    private List<Agenciagubernamental> findAgenciagubernamentalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Agenciagubernamental.class));
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

    public Agenciagubernamental findAgenciagubernamental(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Agenciagubernamental.class, id);
        } finally {
            em.close();
        }
    }

    public int getAgenciagubernamentalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Agenciagubernamental> rt = cq.from(Agenciagubernamental.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
