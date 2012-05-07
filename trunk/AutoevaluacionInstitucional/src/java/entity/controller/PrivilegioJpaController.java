/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.controller;

import connection.jpaConnection;
import entity.Privilegio;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Representantehasprivilegio;
import entity.controller.exceptions.IllegalOrphanException;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class PrivilegioJpaController implements Serializable {

    public PrivilegioJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Privilegio privilegio) {
        if (privilegio.getRepresentantehasprivilegioList() == null) {
            privilegio.setRepresentantehasprivilegioList(new ArrayList<Representantehasprivilegio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Representantehasprivilegio> attachedRepresentantehasprivilegioList = new ArrayList<Representantehasprivilegio>();
            for (Representantehasprivilegio representantehasprivilegioListRepresentantehasprivilegioToAttach : privilegio.getRepresentantehasprivilegioList()) {
                representantehasprivilegioListRepresentantehasprivilegioToAttach = em.getReference(representantehasprivilegioListRepresentantehasprivilegioToAttach.getClass(), representantehasprivilegioListRepresentantehasprivilegioToAttach.getId());
                attachedRepresentantehasprivilegioList.add(representantehasprivilegioListRepresentantehasprivilegioToAttach);
            }
            privilegio.setRepresentantehasprivilegioList(attachedRepresentantehasprivilegioList);
            em.persist(privilegio);
            for (Representantehasprivilegio representantehasprivilegioListRepresentantehasprivilegio : privilegio.getRepresentantehasprivilegioList()) {
                Privilegio oldPrivilegioIdOfRepresentantehasprivilegioListRepresentantehasprivilegio = representantehasprivilegioListRepresentantehasprivilegio.getPrivilegioId();
                representantehasprivilegioListRepresentantehasprivilegio.setPrivilegioId(privilegio);
                representantehasprivilegioListRepresentantehasprivilegio = em.merge(representantehasprivilegioListRepresentantehasprivilegio);
                if (oldPrivilegioIdOfRepresentantehasprivilegioListRepresentantehasprivilegio != null) {
                    oldPrivilegioIdOfRepresentantehasprivilegioListRepresentantehasprivilegio.getRepresentantehasprivilegioList().remove(representantehasprivilegioListRepresentantehasprivilegio);
                    oldPrivilegioIdOfRepresentantehasprivilegioListRepresentantehasprivilegio = em.merge(oldPrivilegioIdOfRepresentantehasprivilegioListRepresentantehasprivilegio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Privilegio privilegio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Privilegio persistentPrivilegio = em.find(Privilegio.class, privilegio.getId());
            List<Representantehasprivilegio> representantehasprivilegioListOld = persistentPrivilegio.getRepresentantehasprivilegioList();
            List<Representantehasprivilegio> representantehasprivilegioListNew = privilegio.getRepresentantehasprivilegioList();
            List<String> illegalOrphanMessages = null;
            for (Representantehasprivilegio representantehasprivilegioListOldRepresentantehasprivilegio : representantehasprivilegioListOld) {
                if (!representantehasprivilegioListNew.contains(representantehasprivilegioListOldRepresentantehasprivilegio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Representantehasprivilegio " + representantehasprivilegioListOldRepresentantehasprivilegio + " since its privilegioId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Representantehasprivilegio> attachedRepresentantehasprivilegioListNew = new ArrayList<Representantehasprivilegio>();
            for (Representantehasprivilegio representantehasprivilegioListNewRepresentantehasprivilegioToAttach : representantehasprivilegioListNew) {
                representantehasprivilegioListNewRepresentantehasprivilegioToAttach = em.getReference(representantehasprivilegioListNewRepresentantehasprivilegioToAttach.getClass(), representantehasprivilegioListNewRepresentantehasprivilegioToAttach.getId());
                attachedRepresentantehasprivilegioListNew.add(representantehasprivilegioListNewRepresentantehasprivilegioToAttach);
            }
            representantehasprivilegioListNew = attachedRepresentantehasprivilegioListNew;
            privilegio.setRepresentantehasprivilegioList(representantehasprivilegioListNew);
            privilegio = em.merge(privilegio);
            for (Representantehasprivilegio representantehasprivilegioListNewRepresentantehasprivilegio : representantehasprivilegioListNew) {
                if (!representantehasprivilegioListOld.contains(representantehasprivilegioListNewRepresentantehasprivilegio)) {
                    Privilegio oldPrivilegioIdOfRepresentantehasprivilegioListNewRepresentantehasprivilegio = representantehasprivilegioListNewRepresentantehasprivilegio.getPrivilegioId();
                    representantehasprivilegioListNewRepresentantehasprivilegio.setPrivilegioId(privilegio);
                    representantehasprivilegioListNewRepresentantehasprivilegio = em.merge(representantehasprivilegioListNewRepresentantehasprivilegio);
                    if (oldPrivilegioIdOfRepresentantehasprivilegioListNewRepresentantehasprivilegio != null && !oldPrivilegioIdOfRepresentantehasprivilegioListNewRepresentantehasprivilegio.equals(privilegio)) {
                        oldPrivilegioIdOfRepresentantehasprivilegioListNewRepresentantehasprivilegio.getRepresentantehasprivilegioList().remove(representantehasprivilegioListNewRepresentantehasprivilegio);
                        oldPrivilegioIdOfRepresentantehasprivilegioListNewRepresentantehasprivilegio = em.merge(oldPrivilegioIdOfRepresentantehasprivilegioListNewRepresentantehasprivilegio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = privilegio.getId();
                if (findPrivilegio(id) == null) {
                    throw new NonexistentEntityException("The privilegio with id " + id + " no longer exists.");
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
            Privilegio privilegio;
            try {
                privilegio = em.getReference(Privilegio.class, id);
                privilegio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The privilegio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Representantehasprivilegio> representantehasprivilegioListOrphanCheck = privilegio.getRepresentantehasprivilegioList();
            for (Representantehasprivilegio representantehasprivilegioListOrphanCheckRepresentantehasprivilegio : representantehasprivilegioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Privilegio (" + privilegio + ") cannot be destroyed since the Representantehasprivilegio " + representantehasprivilegioListOrphanCheckRepresentantehasprivilegio + " in its representantehasprivilegioList field has a non-nullable privilegioId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(privilegio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Privilegio> findPrivilegioEntities() {
        return findPrivilegioEntities(true, -1, -1);
    }

    public List<Privilegio> findPrivilegioEntities(int maxResults, int firstResult) {
        return findPrivilegioEntities(false, maxResults, firstResult);
    }

    private List<Privilegio> findPrivilegioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Privilegio.class));
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

    public Privilegio findPrivilegio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Privilegio.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrivilegioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Privilegio> rt = cq.from(Privilegio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
