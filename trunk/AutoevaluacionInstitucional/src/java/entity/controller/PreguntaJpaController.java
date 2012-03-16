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
import entity.Encuestahaspregunta;
import entity.Pregunta;
import entity.controller.exceptions.IllegalOrphanException;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Usuario
 */
public class PreguntaJpaController implements Serializable {

    public PreguntaJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Pregunta pregunta) {
        if (pregunta.getEncuestahaspreguntaList() == null) {
            pregunta.setEncuestahaspreguntaList(new ArrayList<Encuestahaspregunta>());
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
            List<Encuestahaspregunta> attachedEncuestahaspreguntaList = new ArrayList<Encuestahaspregunta>();
            for (Encuestahaspregunta encuestahaspreguntaListEncuestahaspreguntaToAttach : pregunta.getEncuestahaspreguntaList()) {
                encuestahaspreguntaListEncuestahaspreguntaToAttach = em.getReference(encuestahaspreguntaListEncuestahaspreguntaToAttach.getClass(), encuestahaspreguntaListEncuestahaspreguntaToAttach.getId());
                attachedEncuestahaspreguntaList.add(encuestahaspreguntaListEncuestahaspreguntaToAttach);
            }
            pregunta.setEncuestahaspreguntaList(attachedEncuestahaspreguntaList);
            em.persist(pregunta);
            if (indicadorId != null) {
                indicadorId.getPreguntaList().add(pregunta);
                indicadorId = em.merge(indicadorId);
            }
            for (Encuestahaspregunta encuestahaspreguntaListEncuestahaspregunta : pregunta.getEncuestahaspreguntaList()) {
                Pregunta oldPreguntaIdOfEncuestahaspreguntaListEncuestahaspregunta = encuestahaspreguntaListEncuestahaspregunta.getPreguntaId();
                encuestahaspreguntaListEncuestahaspregunta.setPreguntaId(pregunta);
                encuestahaspreguntaListEncuestahaspregunta = em.merge(encuestahaspreguntaListEncuestahaspregunta);
                if (oldPreguntaIdOfEncuestahaspreguntaListEncuestahaspregunta != null) {
                    oldPreguntaIdOfEncuestahaspreguntaListEncuestahaspregunta.getEncuestahaspreguntaList().remove(encuestahaspreguntaListEncuestahaspregunta);
                    oldPreguntaIdOfEncuestahaspreguntaListEncuestahaspregunta = em.merge(oldPreguntaIdOfEncuestahaspreguntaListEncuestahaspregunta);
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
            List<Encuestahaspregunta> encuestahaspreguntaListOld = persistentPregunta.getEncuestahaspreguntaList();
            List<Encuestahaspregunta> encuestahaspreguntaListNew = pregunta.getEncuestahaspreguntaList();
            List<String> illegalOrphanMessages = null;
            for (Encuestahaspregunta encuestahaspreguntaListOldEncuestahaspregunta : encuestahaspreguntaListOld) {
                if (!encuestahaspreguntaListNew.contains(encuestahaspreguntaListOldEncuestahaspregunta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Encuestahaspregunta " + encuestahaspreguntaListOldEncuestahaspregunta + " since its preguntaId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (indicadorIdNew != null) {
                indicadorIdNew = em.getReference(indicadorIdNew.getClass(), indicadorIdNew.getId());
                pregunta.setIndicadorId(indicadorIdNew);
            }
            List<Encuestahaspregunta> attachedEncuestahaspreguntaListNew = new ArrayList<Encuestahaspregunta>();
            for (Encuestahaspregunta encuestahaspreguntaListNewEncuestahaspreguntaToAttach : encuestahaspreguntaListNew) {
                encuestahaspreguntaListNewEncuestahaspreguntaToAttach = em.getReference(encuestahaspreguntaListNewEncuestahaspreguntaToAttach.getClass(), encuestahaspreguntaListNewEncuestahaspreguntaToAttach.getId());
                attachedEncuestahaspreguntaListNew.add(encuestahaspreguntaListNewEncuestahaspreguntaToAttach);
            }
            encuestahaspreguntaListNew = attachedEncuestahaspreguntaListNew;
            pregunta.setEncuestahaspreguntaList(encuestahaspreguntaListNew);
            pregunta = em.merge(pregunta);
            if (indicadorIdOld != null && !indicadorIdOld.equals(indicadorIdNew)) {
                indicadorIdOld.getPreguntaList().remove(pregunta);
                indicadorIdOld = em.merge(indicadorIdOld);
            }
            if (indicadorIdNew != null && !indicadorIdNew.equals(indicadorIdOld)) {
                indicadorIdNew.getPreguntaList().add(pregunta);
                indicadorIdNew = em.merge(indicadorIdNew);
            }
            for (Encuestahaspregunta encuestahaspreguntaListNewEncuestahaspregunta : encuestahaspreguntaListNew) {
                if (!encuestahaspreguntaListOld.contains(encuestahaspreguntaListNewEncuestahaspregunta)) {
                    Pregunta oldPreguntaIdOfEncuestahaspreguntaListNewEncuestahaspregunta = encuestahaspreguntaListNewEncuestahaspregunta.getPreguntaId();
                    encuestahaspreguntaListNewEncuestahaspregunta.setPreguntaId(pregunta);
                    encuestahaspreguntaListNewEncuestahaspregunta = em.merge(encuestahaspreguntaListNewEncuestahaspregunta);
                    if (oldPreguntaIdOfEncuestahaspreguntaListNewEncuestahaspregunta != null && !oldPreguntaIdOfEncuestahaspreguntaListNewEncuestahaspregunta.equals(pregunta)) {
                        oldPreguntaIdOfEncuestahaspreguntaListNewEncuestahaspregunta.getEncuestahaspreguntaList().remove(encuestahaspreguntaListNewEncuestahaspregunta);
                        oldPreguntaIdOfEncuestahaspreguntaListNewEncuestahaspregunta = em.merge(oldPreguntaIdOfEncuestahaspreguntaListNewEncuestahaspregunta);
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
            List<Encuestahaspregunta> encuestahaspreguntaListOrphanCheck = pregunta.getEncuestahaspreguntaList();
            for (Encuestahaspregunta encuestahaspreguntaListOrphanCheckEncuestahaspregunta : encuestahaspreguntaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pregunta (" + pregunta + ") cannot be destroyed since the Encuestahaspregunta " + encuestahaspreguntaListOrphanCheckEncuestahaspregunta + " in its encuestahaspreguntaList field has a non-nullable preguntaId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Indicador indicadorId = pregunta.getIndicadorId();
            if (indicadorId != null) {
                indicadorId.getPreguntaList().remove(pregunta);
                indicadorId = em.merge(indicadorId);
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
