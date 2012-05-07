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
import entity.Indicador;
import entity.Encuesta;
import entity.Pregunta;
import java.util.ArrayList;
import java.util.List;
import entity.Resultadoevaluacion;
import entity.controller.exceptions.IllegalOrphanException;
import entity.controller.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class PreguntaJpaController implements Serializable {

    public PreguntaJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Pregunta pregunta) {
        if (pregunta.getEncuestaList() == null) {
            pregunta.setEncuestaList(new ArrayList<Encuesta>());
        }
        if (pregunta.getResultadoevaluacionList() == null) {
            pregunta.setResultadoevaluacionList(new ArrayList<Resultadoevaluacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Indicador indicadorId = pregunta.getIndicadorId();
            if (indicadorId != null) {
                indicadorId = em.getReference(indicadorId.getClass(), indicadorId.getId());
                pregunta.setIndicadorId(indicadorId);
            }
            List<Encuesta> attachedEncuestaList = new ArrayList<Encuesta>();
            for (Encuesta encuestaListEncuestaToAttach : pregunta.getEncuestaList()) {
                encuestaListEncuestaToAttach = em.getReference(encuestaListEncuestaToAttach.getClass(), encuestaListEncuestaToAttach.getId());
                attachedEncuestaList.add(encuestaListEncuestaToAttach);
            }
            pregunta.setEncuestaList(attachedEncuestaList);
            List<Resultadoevaluacion> attachedResultadoevaluacionList = new ArrayList<Resultadoevaluacion>();
            for (Resultadoevaluacion resultadoevaluacionListResultadoevaluacionToAttach : pregunta.getResultadoevaluacionList()) {
                resultadoevaluacionListResultadoevaluacionToAttach = em.getReference(resultadoevaluacionListResultadoevaluacionToAttach.getClass(), resultadoevaluacionListResultadoevaluacionToAttach.getIdResultadoEvaluacion());
                attachedResultadoevaluacionList.add(resultadoevaluacionListResultadoevaluacionToAttach);
            }
            pregunta.setResultadoevaluacionList(attachedResultadoevaluacionList);
            em.persist(pregunta);
            if (indicadorId != null) {
                indicadorId.getPreguntaList().add(pregunta);
                indicadorId = em.merge(indicadorId);
            }
            for (Encuesta encuestaListEncuesta : pregunta.getEncuestaList()) {
                encuestaListEncuesta.getPreguntaList().add(pregunta);
                encuestaListEncuesta = em.merge(encuestaListEncuesta);
            }
            for (Resultadoevaluacion resultadoevaluacionListResultadoevaluacion : pregunta.getResultadoevaluacionList()) {
                Pregunta oldPreguntaIdOfResultadoevaluacionListResultadoevaluacion = resultadoevaluacionListResultadoevaluacion.getPreguntaId();
                resultadoevaluacionListResultadoevaluacion.setPreguntaId(pregunta);
                resultadoevaluacionListResultadoevaluacion = em.merge(resultadoevaluacionListResultadoevaluacion);
                if (oldPreguntaIdOfResultadoevaluacionListResultadoevaluacion != null) {
                    oldPreguntaIdOfResultadoevaluacionListResultadoevaluacion.getResultadoevaluacionList().remove(resultadoevaluacionListResultadoevaluacion);
                    oldPreguntaIdOfResultadoevaluacionListResultadoevaluacion = em.merge(oldPreguntaIdOfResultadoevaluacionListResultadoevaluacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pregunta pregunta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pregunta persistentPregunta = em.find(Pregunta.class, pregunta.getId());
            Indicador indicadorIdOld = persistentPregunta.getIndicadorId();
            Indicador indicadorIdNew = pregunta.getIndicadorId();
            List<Encuesta> encuestaListOld = persistentPregunta.getEncuestaList();
            List<Encuesta> encuestaListNew = pregunta.getEncuestaList();
            List<Resultadoevaluacion> resultadoevaluacionListOld = persistentPregunta.getResultadoevaluacionList();
            List<Resultadoevaluacion> resultadoevaluacionListNew = pregunta.getResultadoevaluacionList();
            List<String> illegalOrphanMessages = null;
            for (Resultadoevaluacion resultadoevaluacionListOldResultadoevaluacion : resultadoevaluacionListOld) {
                if (!resultadoevaluacionListNew.contains(resultadoevaluacionListOldResultadoevaluacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Resultadoevaluacion " + resultadoevaluacionListOldResultadoevaluacion + " since its preguntaId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (indicadorIdNew != null) {
                indicadorIdNew = em.getReference(indicadorIdNew.getClass(), indicadorIdNew.getId());
                pregunta.setIndicadorId(indicadorIdNew);
            }
            List<Encuesta> attachedEncuestaListNew = new ArrayList<Encuesta>();
            for (Encuesta encuestaListNewEncuestaToAttach : encuestaListNew) {
                encuestaListNewEncuestaToAttach = em.getReference(encuestaListNewEncuestaToAttach.getClass(), encuestaListNewEncuestaToAttach.getId());
                attachedEncuestaListNew.add(encuestaListNewEncuestaToAttach);
            }
            encuestaListNew = attachedEncuestaListNew;
            pregunta.setEncuestaList(encuestaListNew);
            List<Resultadoevaluacion> attachedResultadoevaluacionListNew = new ArrayList<Resultadoevaluacion>();
            for (Resultadoevaluacion resultadoevaluacionListNewResultadoevaluacionToAttach : resultadoevaluacionListNew) {
                resultadoevaluacionListNewResultadoevaluacionToAttach = em.getReference(resultadoevaluacionListNewResultadoevaluacionToAttach.getClass(), resultadoevaluacionListNewResultadoevaluacionToAttach.getIdResultadoEvaluacion());
                attachedResultadoevaluacionListNew.add(resultadoevaluacionListNewResultadoevaluacionToAttach);
            }
            resultadoevaluacionListNew = attachedResultadoevaluacionListNew;
            pregunta.setResultadoevaluacionList(resultadoevaluacionListNew);
            pregunta = em.merge(pregunta);
            if (indicadorIdOld != null && !indicadorIdOld.equals(indicadorIdNew)) {
                indicadorIdOld.getPreguntaList().remove(pregunta);
                indicadorIdOld = em.merge(indicadorIdOld);
            }
            if (indicadorIdNew != null && !indicadorIdNew.equals(indicadorIdOld)) {
                indicadorIdNew.getPreguntaList().add(pregunta);
                indicadorIdNew = em.merge(indicadorIdNew);
            }
            for (Encuesta encuestaListOldEncuesta : encuestaListOld) {
                if (!encuestaListNew.contains(encuestaListOldEncuesta)) {
                    encuestaListOldEncuesta.getPreguntaList().remove(pregunta);
                    encuestaListOldEncuesta = em.merge(encuestaListOldEncuesta);
                }
            }
            for (Encuesta encuestaListNewEncuesta : encuestaListNew) {
                if (!encuestaListOld.contains(encuestaListNewEncuesta)) {
                    encuestaListNewEncuesta.getPreguntaList().add(pregunta);
                    encuestaListNewEncuesta = em.merge(encuestaListNewEncuesta);
                }
            }
            for (Resultadoevaluacion resultadoevaluacionListNewResultadoevaluacion : resultadoevaluacionListNew) {
                if (!resultadoevaluacionListOld.contains(resultadoevaluacionListNewResultadoevaluacion)) {
                    Pregunta oldPreguntaIdOfResultadoevaluacionListNewResultadoevaluacion = resultadoevaluacionListNewResultadoevaluacion.getPreguntaId();
                    resultadoevaluacionListNewResultadoevaluacion.setPreguntaId(pregunta);
                    resultadoevaluacionListNewResultadoevaluacion = em.merge(resultadoevaluacionListNewResultadoevaluacion);
                    if (oldPreguntaIdOfResultadoevaluacionListNewResultadoevaluacion != null && !oldPreguntaIdOfResultadoevaluacionListNewResultadoevaluacion.equals(pregunta)) {
                        oldPreguntaIdOfResultadoevaluacionListNewResultadoevaluacion.getResultadoevaluacionList().remove(resultadoevaluacionListNewResultadoevaluacion);
                        oldPreguntaIdOfResultadoevaluacionListNewResultadoevaluacion = em.merge(oldPreguntaIdOfResultadoevaluacionListNewResultadoevaluacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pregunta.getId();
                if (findPregunta(id) == null) {
                    throw new NonexistentEntityException("The pregunta with id " + id + " no longer exists.");
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
            Pregunta pregunta;
            try {
                pregunta = em.getReference(Pregunta.class, id);
                pregunta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pregunta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Resultadoevaluacion> resultadoevaluacionListOrphanCheck = pregunta.getResultadoevaluacionList();
            for (Resultadoevaluacion resultadoevaluacionListOrphanCheckResultadoevaluacion : resultadoevaluacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pregunta (" + pregunta + ") cannot be destroyed since the Resultadoevaluacion " + resultadoevaluacionListOrphanCheckResultadoevaluacion + " in its resultadoevaluacionList field has a non-nullable preguntaId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Indicador indicadorId = pregunta.getIndicadorId();
            if (indicadorId != null) {
                indicadorId.getPreguntaList().remove(pregunta);
                indicadorId = em.merge(indicadorId);
            }
            List<Encuesta> encuestaList = pregunta.getEncuestaList();
            for (Encuesta encuestaListEncuesta : encuestaList) {
                encuestaListEncuesta.getPreguntaList().remove(pregunta);
                encuestaListEncuesta = em.merge(encuestaListEncuesta);
            }
            em.remove(pregunta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pregunta> findPreguntaEntities() {
        return findPreguntaEntities(true, -1, -1);
    }

    public List<Pregunta> findPreguntaEntities(int maxResults, int firstResult) {
        return findPreguntaEntities(false, maxResults, firstResult);
    }

    private List<Pregunta> findPreguntaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pregunta.class));
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

    public Pregunta findPregunta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pregunta.class, id);
        } finally {
            em.close();
        }
    }

    public int getPreguntaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pregunta> rt = cq.from(Pregunta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
