/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import connection.jpaConnection;
import entity.Sectorempresarial;
import entity.controller.exceptions.IllegalOrphanException;
import entity.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Empleador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vanesa
 */
public class SectorempresarialJpaController implements Serializable {

    public SectorempresarialJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Sectorempresarial sectorempresarial) {
        if (sectorempresarial.getEmpleadorList() == null) {
            sectorempresarial.setEmpleadorList(new ArrayList<Empleador>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Empleador> attachedEmpleadorList = new ArrayList<Empleador>();
            for (Empleador empleadorListEmpleadorToAttach : sectorempresarial.getEmpleadorList()) {
                empleadorListEmpleadorToAttach = em.getReference(empleadorListEmpleadorToAttach.getClass(), empleadorListEmpleadorToAttach.getId());
                attachedEmpleadorList.add(empleadorListEmpleadorToAttach);
            }
            sectorempresarial.setEmpleadorList(attachedEmpleadorList);
            em.persist(sectorempresarial);
            for (Empleador empleadorListEmpleador : sectorempresarial.getEmpleadorList()) {
                Sectorempresarial oldSectorempresarialIdOfEmpleadorListEmpleador = empleadorListEmpleador.getSectorempresarialId();
                empleadorListEmpleador.setSectorempresarialId(sectorempresarial);
                empleadorListEmpleador = em.merge(empleadorListEmpleador);
                if (oldSectorempresarialIdOfEmpleadorListEmpleador != null) {
                    oldSectorempresarialIdOfEmpleadorListEmpleador.getEmpleadorList().remove(empleadorListEmpleador);
                    oldSectorempresarialIdOfEmpleadorListEmpleador = em.merge(oldSectorempresarialIdOfEmpleadorListEmpleador);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sectorempresarial sectorempresarial) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sectorempresarial persistentSectorempresarial = em.find(Sectorempresarial.class, sectorempresarial.getId());
            List<Empleador> empleadorListOld = persistentSectorempresarial.getEmpleadorList();
            List<Empleador> empleadorListNew = sectorempresarial.getEmpleadorList();
            List<String> illegalOrphanMessages = null;
            for (Empleador empleadorListOldEmpleador : empleadorListOld) {
                if (!empleadorListNew.contains(empleadorListOldEmpleador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empleador " + empleadorListOldEmpleador + " since its sectorempresarialId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Empleador> attachedEmpleadorListNew = new ArrayList<Empleador>();
            for (Empleador empleadorListNewEmpleadorToAttach : empleadorListNew) {
                empleadorListNewEmpleadorToAttach = em.getReference(empleadorListNewEmpleadorToAttach.getClass(), empleadorListNewEmpleadorToAttach.getId());
                attachedEmpleadorListNew.add(empleadorListNewEmpleadorToAttach);
            }
            empleadorListNew = attachedEmpleadorListNew;
            sectorempresarial.setEmpleadorList(empleadorListNew);
            sectorempresarial = em.merge(sectorempresarial);
            for (Empleador empleadorListNewEmpleador : empleadorListNew) {
                if (!empleadorListOld.contains(empleadorListNewEmpleador)) {
                    Sectorempresarial oldSectorempresarialIdOfEmpleadorListNewEmpleador = empleadorListNewEmpleador.getSectorempresarialId();
                    empleadorListNewEmpleador.setSectorempresarialId(sectorempresarial);
                    empleadorListNewEmpleador = em.merge(empleadorListNewEmpleador);
                    if (oldSectorempresarialIdOfEmpleadorListNewEmpleador != null && !oldSectorempresarialIdOfEmpleadorListNewEmpleador.equals(sectorempresarial)) {
                        oldSectorempresarialIdOfEmpleadorListNewEmpleador.getEmpleadorList().remove(empleadorListNewEmpleador);
                        oldSectorempresarialIdOfEmpleadorListNewEmpleador = em.merge(oldSectorempresarialIdOfEmpleadorListNewEmpleador);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sectorempresarial.getId();
                if (findSectorempresarial(id) == null) {
                    throw new NonexistentEntityException("The sectorempresarial with id " + id + " no longer exists.");
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
            Sectorempresarial sectorempresarial;
            try {
                sectorempresarial = em.getReference(Sectorempresarial.class, id);
                sectorempresarial.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sectorempresarial with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Empleador> empleadorListOrphanCheck = sectorempresarial.getEmpleadorList();
            for (Empleador empleadorListOrphanCheckEmpleador : empleadorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sectorempresarial (" + sectorempresarial + ") cannot be destroyed since the Empleador " + empleadorListOrphanCheckEmpleador + " in its empleadorList field has a non-nullable sectorempresarialId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(sectorempresarial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sectorempresarial> findSectorempresarialEntities() {
        return findSectorempresarialEntities(true, -1, -1);
    }

    public List<Sectorempresarial> findSectorempresarialEntities(int maxResults, int firstResult) {
        return findSectorempresarialEntities(false, maxResults, firstResult);
    }

    private List<Sectorempresarial> findSectorempresarialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sectorempresarial.class));
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

    public Sectorempresarial findSectorempresarial(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sectorempresarial.class, id);
        } finally {
            em.close();
        }
    }

    public int getSectorempresarialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sectorempresarial> rt = cq.from(Sectorempresarial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
