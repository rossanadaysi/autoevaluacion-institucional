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
import entity.Pregunta;
import entity.Encabezado;
import entity.Resultadoevaluacion;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Oscar
 */
public class ResultadoevaluacionJpaController implements Serializable {

    public ResultadoevaluacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Resultadoevaluacion resultadoevaluacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pregunta preguntaId = resultadoevaluacion.getPreguntaId();
            if (preguntaId != null) {
                preguntaId = em.getReference(preguntaId.getClass(), preguntaId.getId());
                resultadoevaluacion.setPreguntaId(preguntaId);
            }
            Encabezado encabezadoId = resultadoevaluacion.getEncabezadoId();
            if (encabezadoId != null) {
                encabezadoId = em.getReference(encabezadoId.getClass(), encabezadoId.getId());
                resultadoevaluacion.setEncabezadoId(encabezadoId);
            }
            em.persist(resultadoevaluacion);
            if (preguntaId != null) {
                preguntaId.getResultadoevaluacionList().add(resultadoevaluacion);
                preguntaId = em.merge(preguntaId);
            }
            if (encabezadoId != null) {
                encabezadoId.getResultadoevaluacionList().add(resultadoevaluacion);
                encabezadoId = em.merge(encabezadoId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Resultadoevaluacion resultadoevaluacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resultadoevaluacion persistentResultadoevaluacion = em.find(Resultadoevaluacion.class, resultadoevaluacion.getIdResultadoEvaluacion());
            Pregunta preguntaIdOld = persistentResultadoevaluacion.getPreguntaId();
            Pregunta preguntaIdNew = resultadoevaluacion.getPreguntaId();
            Encabezado encabezadoIdOld = persistentResultadoevaluacion.getEncabezadoId();
            Encabezado encabezadoIdNew = resultadoevaluacion.getEncabezadoId();
            if (preguntaIdNew != null) {
                preguntaIdNew = em.getReference(preguntaIdNew.getClass(), preguntaIdNew.getId());
                resultadoevaluacion.setPreguntaId(preguntaIdNew);
            }
            if (encabezadoIdNew != null) {
                encabezadoIdNew = em.getReference(encabezadoIdNew.getClass(), encabezadoIdNew.getId());
                resultadoevaluacion.setEncabezadoId(encabezadoIdNew);
            }
            resultadoevaluacion = em.merge(resultadoevaluacion);
            if (preguntaIdOld != null && !preguntaIdOld.equals(preguntaIdNew)) {
                preguntaIdOld.getResultadoevaluacionList().remove(resultadoevaluacion);
                preguntaIdOld = em.merge(preguntaIdOld);
            }
            if (preguntaIdNew != null && !preguntaIdNew.equals(preguntaIdOld)) {
                preguntaIdNew.getResultadoevaluacionList().add(resultadoevaluacion);
                preguntaIdNew = em.merge(preguntaIdNew);
            }
            if (encabezadoIdOld != null && !encabezadoIdOld.equals(encabezadoIdNew)) {
                encabezadoIdOld.getResultadoevaluacionList().remove(resultadoevaluacion);
                encabezadoIdOld = em.merge(encabezadoIdOld);
            }
            if (encabezadoIdNew != null && !encabezadoIdNew.equals(encabezadoIdOld)) {
                encabezadoIdNew.getResultadoevaluacionList().add(resultadoevaluacion);
                encabezadoIdNew = em.merge(encabezadoIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = resultadoevaluacion.getIdResultadoEvaluacion();
                if (findResultadoevaluacion(id) == null) {
                    throw new NonexistentEntityException("The resultadoevaluacion with id " + id + " no longer exists.");
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
            Resultadoevaluacion resultadoevaluacion;
            try {
                resultadoevaluacion = em.getReference(Resultadoevaluacion.class, id);
                resultadoevaluacion.getIdResultadoEvaluacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resultadoevaluacion with id " + id + " no longer exists.", enfe);
            }
            Pregunta preguntaId = resultadoevaluacion.getPreguntaId();
            if (preguntaId != null) {
                preguntaId.getResultadoevaluacionList().remove(resultadoevaluacion);
                preguntaId = em.merge(preguntaId);
            }
            Encabezado encabezadoId = resultadoevaluacion.getEncabezadoId();
            if (encabezadoId != null) {
                encabezadoId.getResultadoevaluacionList().remove(resultadoevaluacion);
                encabezadoId = em.merge(encabezadoId);
            }
            em.remove(resultadoevaluacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Resultadoevaluacion> findResultadoevaluacionEntities() {
        return findResultadoevaluacionEntities(true, -1, -1);
    }

    public List<Resultadoevaluacion> findResultadoevaluacionEntities(int maxResults, int firstResult) {
        return findResultadoevaluacionEntities(false, maxResults, firstResult);
    }

    private List<Resultadoevaluacion> findResultadoevaluacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Resultadoevaluacion.class));
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

    public Resultadoevaluacion findResultadoevaluacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Resultadoevaluacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getResultadoevaluacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Resultadoevaluacion> rt = cq.from(Resultadoevaluacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
