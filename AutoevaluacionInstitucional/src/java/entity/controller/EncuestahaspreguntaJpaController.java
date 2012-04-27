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
import entity.Pregunta;
import entity.Encuesta;
import entity.Encuestahaspregunta;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ususario
 */
public class EncuestahaspreguntaJpaController implements Serializable {

    public EncuestahaspreguntaJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Encuestahaspregunta encuestahaspregunta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pregunta preguntaId = encuestahaspregunta.getPreguntaId();
            if (preguntaId != null) {
                preguntaId = em.getReference(preguntaId.getClass(), preguntaId.getId());
                encuestahaspregunta.setPreguntaId(preguntaId);
            }
            Encuesta encuestaId = encuestahaspregunta.getEncuestaId();
            if (encuestaId != null) {
                encuestaId = em.getReference(encuestaId.getClass(), encuestaId.getId());
                encuestahaspregunta.setEncuestaId(encuestaId);
            }
            em.persist(encuestahaspregunta);
            if (preguntaId != null) {
                preguntaId.getEncuestahaspreguntaList().add(encuestahaspregunta);
                preguntaId = em.merge(preguntaId);
            }
            if (encuestaId != null) {
                encuestaId.getEncuestahaspreguntaList().add(encuestahaspregunta);
                encuestaId = em.merge(encuestaId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Encuestahaspregunta encuestahaspregunta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Encuestahaspregunta persistentEncuestahaspregunta = em.find(Encuestahaspregunta.class, encuestahaspregunta.getId());
            Pregunta preguntaIdOld = persistentEncuestahaspregunta.getPreguntaId();
            Pregunta preguntaIdNew = encuestahaspregunta.getPreguntaId();
            Encuesta encuestaIdOld = persistentEncuestahaspregunta.getEncuestaId();
            Encuesta encuestaIdNew = encuestahaspregunta.getEncuestaId();
            if (preguntaIdNew != null) {
                preguntaIdNew = em.getReference(preguntaIdNew.getClass(), preguntaIdNew.getId());
                encuestahaspregunta.setPreguntaId(preguntaIdNew);
            }
            if (encuestaIdNew != null) {
                encuestaIdNew = em.getReference(encuestaIdNew.getClass(), encuestaIdNew.getId());
                encuestahaspregunta.setEncuestaId(encuestaIdNew);
            }
            encuestahaspregunta = em.merge(encuestahaspregunta);
            if (preguntaIdOld != null && !preguntaIdOld.equals(preguntaIdNew)) {
                preguntaIdOld.getEncuestahaspreguntaList().remove(encuestahaspregunta);
                preguntaIdOld = em.merge(preguntaIdOld);
            }
            if (preguntaIdNew != null && !preguntaIdNew.equals(preguntaIdOld)) {
                preguntaIdNew.getEncuestahaspreguntaList().add(encuestahaspregunta);
                preguntaIdNew = em.merge(preguntaIdNew);
            }
            if (encuestaIdOld != null && !encuestaIdOld.equals(encuestaIdNew)) {
                encuestaIdOld.getEncuestahaspreguntaList().remove(encuestahaspregunta);
                encuestaIdOld = em.merge(encuestaIdOld);
            }
            if (encuestaIdNew != null && !encuestaIdNew.equals(encuestaIdOld)) {
                encuestaIdNew.getEncuestahaspreguntaList().add(encuestahaspregunta);
                encuestaIdNew = em.merge(encuestaIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = encuestahaspregunta.getId();
                if (findEncuestahaspregunta(id) == null) {
                    throw new NonexistentEntityException("The encuestahaspregunta with id " + id + " no longer exists.");
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
            Encuestahaspregunta encuestahaspregunta;
            try {
                encuestahaspregunta = em.getReference(Encuestahaspregunta.class, id);
                encuestahaspregunta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The encuestahaspregunta with id " + id + " no longer exists.", enfe);
            }
            Pregunta preguntaId = encuestahaspregunta.getPreguntaId();
            if (preguntaId != null) {
                preguntaId.getEncuestahaspreguntaList().remove(encuestahaspregunta);
                preguntaId = em.merge(preguntaId);
            }
            Encuesta encuestaId = encuestahaspregunta.getEncuestaId();
            if (encuestaId != null) {
                encuestaId.getEncuestahaspreguntaList().remove(encuestahaspregunta);
                encuestaId = em.merge(encuestaId);
            }
            em.remove(encuestahaspregunta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Encuestahaspregunta> findEncuestahaspreguntaEntities() {
        return findEncuestahaspreguntaEntities(true, -1, -1);
    }

    public List<Encuestahaspregunta> findEncuestahaspreguntaEntities(int maxResults, int firstResult) {
        return findEncuestahaspreguntaEntities(false, maxResults, firstResult);
    }

    private List<Encuestahaspregunta> findEncuestahaspreguntaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Encuestahaspregunta.class));
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

    public Encuestahaspregunta findEncuestahaspregunta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Encuestahaspregunta.class, id);
        } finally {
            em.close();
        }
    }

    public Encuestahaspregunta findEncuestahaspregunta(Integer idp, Integer ide) {
        EntityManager em = getEntityManager();
        try {
            Pregunta p = em.find(Pregunta.class, idp);
            Encuesta e = em.find(Encuesta.class, ide);
            Query q = em.createQuery("SELECT ep FROM Encuestahaspregunta ep WHERE ep.encuestaId=:enc and ep.preguntaId=:pre");
            q.setParameter("pre", p);
            q.setParameter("enc", e);
            List<Encuestahaspregunta> lis = q.getResultList();
            if (lis.size() > 0) {
                return lis.get(0);
            } else {
                return null;
            }

        } finally {
            em.close();
        }
    }

    public int getEncuestahaspreguntaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Encuestahaspregunta> rt = cq.from(Encuestahaspregunta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
