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
import entity.Representante;
import entity.Privilegio;
import entity.Representantehasprivilegio;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ususario
 */
public class RepresentantehasprivilegioJpaController implements Serializable {

    public RepresentantehasprivilegioJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Representantehasprivilegio representantehasprivilegio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Representante representanteId = representantehasprivilegio.getRepresentanteId();
            if (representanteId != null) {
                representanteId = em.getReference(representanteId.getClass(), representanteId.getId());
                representantehasprivilegio.setRepresentanteId(representanteId);
            }
            Privilegio privilegioId = representantehasprivilegio.getPrivilegioId();
            if (privilegioId != null) {
                privilegioId = em.getReference(privilegioId.getClass(), privilegioId.getId());
                representantehasprivilegio.setPrivilegioId(privilegioId);
            }
            em.persist(representantehasprivilegio);
            if (representanteId != null) {
                representanteId.getRepresentantehasprivilegioList().add(representantehasprivilegio);
                representanteId = em.merge(representanteId);
            }
            if (privilegioId != null) {
                privilegioId.getRepresentantehasprivilegioList().add(representantehasprivilegio);
                privilegioId = em.merge(privilegioId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Representantehasprivilegio representantehasprivilegio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Representantehasprivilegio persistentRepresentantehasprivilegio = em.find(Representantehasprivilegio.class, representantehasprivilegio.getId());
            Representante representanteIdOld = persistentRepresentantehasprivilegio.getRepresentanteId();
            Representante representanteIdNew = representantehasprivilegio.getRepresentanteId();
            Privilegio privilegioIdOld = persistentRepresentantehasprivilegio.getPrivilegioId();
            Privilegio privilegioIdNew = representantehasprivilegio.getPrivilegioId();
            if (representanteIdNew != null) {
                representanteIdNew = em.getReference(representanteIdNew.getClass(), representanteIdNew.getId());
                representantehasprivilegio.setRepresentanteId(representanteIdNew);
            }
            if (privilegioIdNew != null) {
                privilegioIdNew = em.getReference(privilegioIdNew.getClass(), privilegioIdNew.getId());
                representantehasprivilegio.setPrivilegioId(privilegioIdNew);
            }
            representantehasprivilegio = em.merge(representantehasprivilegio);
            if (representanteIdOld != null && !representanteIdOld.equals(representanteIdNew)) {
                representanteIdOld.getRepresentantehasprivilegioList().remove(representantehasprivilegio);
                representanteIdOld = em.merge(representanteIdOld);
            }
            if (representanteIdNew != null && !representanteIdNew.equals(representanteIdOld)) {
                representanteIdNew.getRepresentantehasprivilegioList().add(representantehasprivilegio);
                representanteIdNew = em.merge(representanteIdNew);
            }
            if (privilegioIdOld != null && !privilegioIdOld.equals(privilegioIdNew)) {
                privilegioIdOld.getRepresentantehasprivilegioList().remove(representantehasprivilegio);
                privilegioIdOld = em.merge(privilegioIdOld);
            }
            if (privilegioIdNew != null && !privilegioIdNew.equals(privilegioIdOld)) {
                privilegioIdNew.getRepresentantehasprivilegioList().add(representantehasprivilegio);
                privilegioIdNew = em.merge(privilegioIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = representantehasprivilegio.getId();
                if (findRepresentantehasprivilegio(id) == null) {
                    throw new NonexistentEntityException("The representantehasprivilegio with id " + id + " no longer exists.");
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
            Representantehasprivilegio representantehasprivilegio;
            try {
                representantehasprivilegio = em.getReference(Representantehasprivilegio.class, id);
                representantehasprivilegio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The representantehasprivilegio with id " + id + " no longer exists.", enfe);
            }
            Representante representanteId = representantehasprivilegio.getRepresentanteId();
            if (representanteId != null) {
                representanteId.getRepresentantehasprivilegioList().remove(representantehasprivilegio);
                representanteId = em.merge(representanteId);
            }
            Privilegio privilegioId = representantehasprivilegio.getPrivilegioId();
            if (privilegioId != null) {
                privilegioId.getRepresentantehasprivilegioList().remove(representantehasprivilegio);
                privilegioId = em.merge(privilegioId);
            }
            em.remove(representantehasprivilegio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Representantehasprivilegio> findRepresentantehasprivilegioEntities() {
        return findRepresentantehasprivilegioEntities(true, -1, -1);
    }

    public List<Representantehasprivilegio> findRepresentantehasprivilegioEntities(int maxResults, int firstResult) {
        return findRepresentantehasprivilegioEntities(false, maxResults, firstResult);
    }

    private List<Representantehasprivilegio> findRepresentantehasprivilegioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Representantehasprivilegio.class));
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

    public Representantehasprivilegio findRepresentantehasprivilegio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Representantehasprivilegio.class, id);
        } finally {
            em.close();
        }
    }

    public int getRepresentantehasprivilegioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Representantehasprivilegio> rt = cq.from(Representantehasprivilegio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
