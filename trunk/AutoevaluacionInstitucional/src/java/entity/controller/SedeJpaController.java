/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import connection.jpaConnection;
import entity.Sede;
import entity.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Programa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vanesa
 */
public class SedeJpaController implements Serializable {

    public SedeJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Sede sede) {
        if (sede.getProgramaList() == null) {
            sede.setProgramaList(new ArrayList<Programa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Programa> attachedProgramaList = new ArrayList<Programa>();
            for (Programa programaListProgramaToAttach : sede.getProgramaList()) {
                programaListProgramaToAttach = em.getReference(programaListProgramaToAttach.getClass(), programaListProgramaToAttach.getId());
                attachedProgramaList.add(programaListProgramaToAttach);
            }
            sede.setProgramaList(attachedProgramaList);
            em.persist(sede);
            for (Programa programaListPrograma : sede.getProgramaList()) {
                Sede oldSedeIdOfProgramaListPrograma = programaListPrograma.getSedeId();
                programaListPrograma.setSedeId(sede);
                programaListPrograma = em.merge(programaListPrograma);
                if (oldSedeIdOfProgramaListPrograma != null) {
                    oldSedeIdOfProgramaListPrograma.getProgramaList().remove(programaListPrograma);
                    oldSedeIdOfProgramaListPrograma = em.merge(oldSedeIdOfProgramaListPrograma);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sede sede) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sede persistentSede = em.find(Sede.class, sede.getId());
            List<Programa> programaListOld = persistentSede.getProgramaList();
            List<Programa> programaListNew = sede.getProgramaList();
            List<Programa> attachedProgramaListNew = new ArrayList<Programa>();
            for (Programa programaListNewProgramaToAttach : programaListNew) {
                programaListNewProgramaToAttach = em.getReference(programaListNewProgramaToAttach.getClass(), programaListNewProgramaToAttach.getId());
                attachedProgramaListNew.add(programaListNewProgramaToAttach);
            }
            programaListNew = attachedProgramaListNew;
            sede.setProgramaList(programaListNew);
            sede = em.merge(sede);
            for (Programa programaListOldPrograma : programaListOld) {
                if (!programaListNew.contains(programaListOldPrograma)) {
                    programaListOldPrograma.setSedeId(null);
                    programaListOldPrograma = em.merge(programaListOldPrograma);
                }
            }
            for (Programa programaListNewPrograma : programaListNew) {
                if (!programaListOld.contains(programaListNewPrograma)) {
                    Sede oldSedeIdOfProgramaListNewPrograma = programaListNewPrograma.getSedeId();
                    programaListNewPrograma.setSedeId(sede);
                    programaListNewPrograma = em.merge(programaListNewPrograma);
                    if (oldSedeIdOfProgramaListNewPrograma != null && !oldSedeIdOfProgramaListNewPrograma.equals(sede)) {
                        oldSedeIdOfProgramaListNewPrograma.getProgramaList().remove(programaListNewPrograma);
                        oldSedeIdOfProgramaListNewPrograma = em.merge(oldSedeIdOfProgramaListNewPrograma);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sede.getId();
                if (findSede(id) == null) {
                    throw new NonexistentEntityException("The sede with id " + id + " no longer exists.");
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
            Sede sede;
            try {
                sede = em.getReference(Sede.class, id);
                sede.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sede with id " + id + " no longer exists.", enfe);
            }
            List<Programa> programaList = sede.getProgramaList();
            for (Programa programaListPrograma : programaList) {
                programaListPrograma.setSedeId(null);
                programaListPrograma = em.merge(programaListPrograma);
            }
            em.remove(sede);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sede> findSedeEntities() {
        return findSedeEntities(true, -1, -1);
    }

    public List<Sede> findSedeEntities(int maxResults, int firstResult) {
        return findSedeEntities(false, maxResults, firstResult);
    }

    private List<Sede> findSedeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sede.class));
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

    public Sede findSede(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sede.class, id);
        } finally {
            em.close();
        }
    }

    public int getSedeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sede> rt = cq.from(Sede.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
