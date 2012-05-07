/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Indicador;
import entity.Instrumento;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class InstrumentoJpaController implements Serializable {

    public InstrumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Instrumento instrumento) {
        if (instrumento.getIndicadorList() == null) {
            instrumento.setIndicadorList(new ArrayList<Indicador>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Indicador> attachedIndicadorList = new ArrayList<Indicador>();
            for (Indicador indicadorListIndicadorToAttach : instrumento.getIndicadorList()) {
                indicadorListIndicadorToAttach = em.getReference(indicadorListIndicadorToAttach.getClass(), indicadorListIndicadorToAttach.getId());
                attachedIndicadorList.add(indicadorListIndicadorToAttach);
            }
            instrumento.setIndicadorList(attachedIndicadorList);
            em.persist(instrumento);
            for (Indicador indicadorListIndicador : instrumento.getIndicadorList()) {
                indicadorListIndicador.getInstrumentoList().add(instrumento);
                indicadorListIndicador = em.merge(indicadorListIndicador);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Instrumento instrumento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Instrumento persistentInstrumento = em.find(Instrumento.class, instrumento.getId());
            List<Indicador> indicadorListOld = persistentInstrumento.getIndicadorList();
            List<Indicador> indicadorListNew = instrumento.getIndicadorList();
            List<Indicador> attachedIndicadorListNew = new ArrayList<Indicador>();
            for (Indicador indicadorListNewIndicadorToAttach : indicadorListNew) {
                indicadorListNewIndicadorToAttach = em.getReference(indicadorListNewIndicadorToAttach.getClass(), indicadorListNewIndicadorToAttach.getId());
                attachedIndicadorListNew.add(indicadorListNewIndicadorToAttach);
            }
            indicadorListNew = attachedIndicadorListNew;
            instrumento.setIndicadorList(indicadorListNew);
            instrumento = em.merge(instrumento);
            for (Indicador indicadorListOldIndicador : indicadorListOld) {
                if (!indicadorListNew.contains(indicadorListOldIndicador)) {
                    indicadorListOldIndicador.getInstrumentoList().remove(instrumento);
                    indicadorListOldIndicador = em.merge(indicadorListOldIndicador);
                }
            }
            for (Indicador indicadorListNewIndicador : indicadorListNew) {
                if (!indicadorListOld.contains(indicadorListNewIndicador)) {
                    indicadorListNewIndicador.getInstrumentoList().add(instrumento);
                    indicadorListNewIndicador = em.merge(indicadorListNewIndicador);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = instrumento.getId();
                if (findInstrumento(id) == null) {
                    throw new NonexistentEntityException("The instrumento with id " + id + " no longer exists.");
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
            Instrumento instrumento;
            try {
                instrumento = em.getReference(Instrumento.class, id);
                instrumento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The instrumento with id " + id + " no longer exists.", enfe);
            }
            List<Indicador> indicadorList = instrumento.getIndicadorList();
            for (Indicador indicadorListIndicador : indicadorList) {
                indicadorListIndicador.getInstrumentoList().remove(instrumento);
                indicadorListIndicador = em.merge(indicadorListIndicador);
            }
            em.remove(instrumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Instrumento> findInstrumentoEntities() {
        return findInstrumentoEntities(true, -1, -1);
    }

    public List<Instrumento> findInstrumentoEntities(int maxResults, int firstResult) {
        return findInstrumentoEntities(false, maxResults, firstResult);
    }

    private List<Instrumento> findInstrumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Instrumento.class));
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

    public Instrumento findInstrumento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Instrumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getInstrumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Instrumento> rt = cq.from(Instrumento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
