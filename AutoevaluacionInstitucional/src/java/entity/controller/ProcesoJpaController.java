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
import entity.Asignacionencuesta;
import entity.Proceso;
import entity.controller.exceptions.IllegalOrphanException;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class ProcesoJpaController implements Serializable {

    public ProcesoJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Proceso proceso) {
        if (proceso.getAsignacionencuestaList() == null) {
            proceso.setAsignacionencuestaList(new ArrayList<Asignacionencuesta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa programaId = proceso.getProgramaId();
            if (programaId != null) {
                programaId = em.getReference(programaId.getClass(), programaId.getId());
                proceso.setProgramaId(programaId);
            }
            List<Asignacionencuesta> attachedAsignacionencuestaList = new ArrayList<Asignacionencuesta>();
            for (Asignacionencuesta asignacionencuestaListAsignacionencuestaToAttach : proceso.getAsignacionencuestaList()) {
                asignacionencuestaListAsignacionencuestaToAttach = em.getReference(asignacionencuestaListAsignacionencuestaToAttach.getClass(), asignacionencuestaListAsignacionencuestaToAttach.getId());
                attachedAsignacionencuestaList.add(asignacionencuestaListAsignacionencuestaToAttach);
            }
            proceso.setAsignacionencuestaList(attachedAsignacionencuestaList);
            em.persist(proceso);
            if (programaId != null) {
                programaId.getProcesoList().add(proceso);
                programaId = em.merge(programaId);
            }
            for (Asignacionencuesta asignacionencuestaListAsignacionencuesta : proceso.getAsignacionencuestaList()) {
                Proceso oldProcesoIdOfAsignacionencuestaListAsignacionencuesta = asignacionencuestaListAsignacionencuesta.getProcesoId();
                asignacionencuestaListAsignacionencuesta.setProcesoId(proceso);
                asignacionencuestaListAsignacionencuesta = em.merge(asignacionencuestaListAsignacionencuesta);
                if (oldProcesoIdOfAsignacionencuestaListAsignacionencuesta != null) {
                    oldProcesoIdOfAsignacionencuestaListAsignacionencuesta.getAsignacionencuestaList().remove(asignacionencuestaListAsignacionencuesta);
                    oldProcesoIdOfAsignacionencuestaListAsignacionencuesta = em.merge(oldProcesoIdOfAsignacionencuestaListAsignacionencuesta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proceso proceso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proceso persistentProceso = em.find(Proceso.class, proceso.getId());
            Programa programaIdOld = persistentProceso.getProgramaId();
            Programa programaIdNew = proceso.getProgramaId();
            List<Asignacionencuesta> asignacionencuestaListOld = persistentProceso.getAsignacionencuestaList();
            List<Asignacionencuesta> asignacionencuestaListNew = proceso.getAsignacionencuestaList();
            List<String> illegalOrphanMessages = null;
            for (Asignacionencuesta asignacionencuestaListOldAsignacionencuesta : asignacionencuestaListOld) {
                if (!asignacionencuestaListNew.contains(asignacionencuestaListOldAsignacionencuesta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asignacionencuesta " + asignacionencuestaListOldAsignacionencuesta + " since its procesoId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (programaIdNew != null) {
                programaIdNew = em.getReference(programaIdNew.getClass(), programaIdNew.getId());
                proceso.setProgramaId(programaIdNew);
            }
            List<Asignacionencuesta> attachedAsignacionencuestaListNew = new ArrayList<Asignacionencuesta>();
            for (Asignacionencuesta asignacionencuestaListNewAsignacionencuestaToAttach : asignacionencuestaListNew) {
                asignacionencuestaListNewAsignacionencuestaToAttach = em.getReference(asignacionencuestaListNewAsignacionencuestaToAttach.getClass(), asignacionencuestaListNewAsignacionencuestaToAttach.getId());
                attachedAsignacionencuestaListNew.add(asignacionencuestaListNewAsignacionencuestaToAttach);
            }
            asignacionencuestaListNew = attachedAsignacionencuestaListNew;
            proceso.setAsignacionencuestaList(asignacionencuestaListNew);
            proceso = em.merge(proceso);
            if (programaIdOld != null && !programaIdOld.equals(programaIdNew)) {
                programaIdOld.getProcesoList().remove(proceso);
                programaIdOld = em.merge(programaIdOld);
            }
            if (programaIdNew != null && !programaIdNew.equals(programaIdOld)) {
                programaIdNew.getProcesoList().add(proceso);
                programaIdNew = em.merge(programaIdNew);
            }
            for (Asignacionencuesta asignacionencuestaListNewAsignacionencuesta : asignacionencuestaListNew) {
                if (!asignacionencuestaListOld.contains(asignacionencuestaListNewAsignacionencuesta)) {
                    Proceso oldProcesoIdOfAsignacionencuestaListNewAsignacionencuesta = asignacionencuestaListNewAsignacionencuesta.getProcesoId();
                    asignacionencuestaListNewAsignacionencuesta.setProcesoId(proceso);
                    asignacionencuestaListNewAsignacionencuesta = em.merge(asignacionencuestaListNewAsignacionencuesta);
                    if (oldProcesoIdOfAsignacionencuestaListNewAsignacionencuesta != null && !oldProcesoIdOfAsignacionencuestaListNewAsignacionencuesta.equals(proceso)) {
                        oldProcesoIdOfAsignacionencuestaListNewAsignacionencuesta.getAsignacionencuestaList().remove(asignacionencuestaListNewAsignacionencuesta);
                        oldProcesoIdOfAsignacionencuestaListNewAsignacionencuesta = em.merge(oldProcesoIdOfAsignacionencuestaListNewAsignacionencuesta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proceso.getId();
                if (findProceso(id) == null) {
                    throw new NonexistentEntityException("The proceso with id " + id + " no longer exists.");
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
            Proceso proceso;
            try {
                proceso = em.getReference(Proceso.class, id);
                proceso.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proceso with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Asignacionencuesta> asignacionencuestaListOrphanCheck = proceso.getAsignacionencuestaList();
            for (Asignacionencuesta asignacionencuestaListOrphanCheckAsignacionencuesta : asignacionencuestaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proceso (" + proceso + ") cannot be destroyed since the Asignacionencuesta " + asignacionencuestaListOrphanCheckAsignacionencuesta + " in its asignacionencuestaList field has a non-nullable procesoId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Programa programaId = proceso.getProgramaId();
            if (programaId != null) {
                programaId.getProcesoList().remove(proceso);
                programaId = em.merge(programaId);
            }
            em.remove(proceso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proceso> findProcesoEntities() {
        return findProcesoEntities(true, -1, -1);
    }

    public List<Proceso> findProcesoEntities(int maxResults, int firstResult) {
        return findProcesoEntities(false, maxResults, firstResult);
    }

    private List<Proceso> findProcesoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proceso.class));
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

    public Proceso findProceso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proceso.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proceso> rt = cq.from(Proceso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
