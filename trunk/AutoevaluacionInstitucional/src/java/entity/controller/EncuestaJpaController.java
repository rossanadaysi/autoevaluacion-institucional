/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import connection.jpaConnection;
import entity.Encuesta;
import entity.controller.exceptions.IllegalOrphanException;
import entity.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Encuestahaspregunta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vanesa
 */
public class EncuestaJpaController implements Serializable {

    public EncuestaJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Encuesta encuesta) {
        if (encuesta.getEncuestahaspreguntaList() == null) {
            encuesta.setEncuestahaspreguntaList(new ArrayList<Encuestahaspregunta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Encuestahaspregunta> attachedEncuestahaspreguntaList = new ArrayList<Encuestahaspregunta>();
            for (Encuestahaspregunta encuestahaspreguntaListEncuestahaspreguntaToAttach : encuesta.getEncuestahaspreguntaList()) {
                encuestahaspreguntaListEncuestahaspreguntaToAttach = em.getReference(encuestahaspreguntaListEncuestahaspreguntaToAttach.getClass(), encuestahaspreguntaListEncuestahaspreguntaToAttach.getId());
                attachedEncuestahaspreguntaList.add(encuestahaspreguntaListEncuestahaspreguntaToAttach);
            }
            encuesta.setEncuestahaspreguntaList(attachedEncuestahaspreguntaList);
            em.persist(encuesta);
            for (Encuestahaspregunta encuestahaspreguntaListEncuestahaspregunta : encuesta.getEncuestahaspreguntaList()) {
                Encuesta oldEncuestaIdOfEncuestahaspreguntaListEncuestahaspregunta = encuestahaspreguntaListEncuestahaspregunta.getEncuestaId();
                encuestahaspreguntaListEncuestahaspregunta.setEncuestaId(encuesta);
                encuestahaspreguntaListEncuestahaspregunta = em.merge(encuestahaspreguntaListEncuestahaspregunta);
                if (oldEncuestaIdOfEncuestahaspreguntaListEncuestahaspregunta != null) {
                    oldEncuestaIdOfEncuestahaspreguntaListEncuestahaspregunta.getEncuestahaspreguntaList().remove(encuestahaspreguntaListEncuestahaspregunta);
                    oldEncuestaIdOfEncuestahaspreguntaListEncuestahaspregunta = em.merge(oldEncuestaIdOfEncuestahaspreguntaListEncuestahaspregunta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Encuesta encuesta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Encuesta persistentEncuesta = em.find(Encuesta.class, encuesta.getId());
            List<Encuestahaspregunta> encuestahaspreguntaListOld = persistentEncuesta.getEncuestahaspreguntaList();
            List<Encuestahaspregunta> encuestahaspreguntaListNew = encuesta.getEncuestahaspreguntaList();
            List<String> illegalOrphanMessages = null;
            for (Encuestahaspregunta encuestahaspreguntaListOldEncuestahaspregunta : encuestahaspreguntaListOld) {
                if (!encuestahaspreguntaListNew.contains(encuestahaspreguntaListOldEncuestahaspregunta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Encuestahaspregunta " + encuestahaspreguntaListOldEncuestahaspregunta + " since its encuestaId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Encuestahaspregunta> attachedEncuestahaspreguntaListNew = new ArrayList<Encuestahaspregunta>();
            for (Encuestahaspregunta encuestahaspreguntaListNewEncuestahaspreguntaToAttach : encuestahaspreguntaListNew) {
                encuestahaspreguntaListNewEncuestahaspreguntaToAttach = em.getReference(encuestahaspreguntaListNewEncuestahaspreguntaToAttach.getClass(), encuestahaspreguntaListNewEncuestahaspreguntaToAttach.getId());
                attachedEncuestahaspreguntaListNew.add(encuestahaspreguntaListNewEncuestahaspreguntaToAttach);
            }
            encuestahaspreguntaListNew = attachedEncuestahaspreguntaListNew;
            encuesta.setEncuestahaspreguntaList(encuestahaspreguntaListNew);
            encuesta = em.merge(encuesta);
            for (Encuestahaspregunta encuestahaspreguntaListNewEncuestahaspregunta : encuestahaspreguntaListNew) {
                if (!encuestahaspreguntaListOld.contains(encuestahaspreguntaListNewEncuestahaspregunta)) {
                    Encuesta oldEncuestaIdOfEncuestahaspreguntaListNewEncuestahaspregunta = encuestahaspreguntaListNewEncuestahaspregunta.getEncuestaId();
                    encuestahaspreguntaListNewEncuestahaspregunta.setEncuestaId(encuesta);
                    encuestahaspreguntaListNewEncuestahaspregunta = em.merge(encuestahaspreguntaListNewEncuestahaspregunta);
                    if (oldEncuestaIdOfEncuestahaspreguntaListNewEncuestahaspregunta != null && !oldEncuestaIdOfEncuestahaspreguntaListNewEncuestahaspregunta.equals(encuesta)) {
                        oldEncuestaIdOfEncuestahaspreguntaListNewEncuestahaspregunta.getEncuestahaspreguntaList().remove(encuestahaspreguntaListNewEncuestahaspregunta);
                        oldEncuestaIdOfEncuestahaspreguntaListNewEncuestahaspregunta = em.merge(oldEncuestaIdOfEncuestahaspreguntaListNewEncuestahaspregunta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = encuesta.getId();
                if (findEncuesta(id) == null) {
                    throw new NonexistentEntityException("The encuesta with id " + id + " no longer exists.");
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
            Encuesta encuesta;
            try {
                encuesta = em.getReference(Encuesta.class, id);
                encuesta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The encuesta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Encuestahaspregunta> encuestahaspreguntaListOrphanCheck = encuesta.getEncuestahaspreguntaList();
            for (Encuestahaspregunta encuestahaspreguntaListOrphanCheckEncuestahaspregunta : encuestahaspreguntaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Encuesta (" + encuesta + ") cannot be destroyed since the Encuestahaspregunta " + encuestahaspreguntaListOrphanCheckEncuestahaspregunta + " in its encuestahaspreguntaList field has a non-nullable encuestaId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(encuesta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Encuesta> findEncuestaEntities() {
        return findEncuestaEntities(true, -1, -1);
    }

    public List<Encuesta> findEncuestaEntities(int maxResults, int firstResult) {
        return findEncuestaEntities(false, maxResults, firstResult);
    }

    private List<Encuesta> findEncuestaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Encuesta.class));
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

    public Encuesta findEncuesta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Encuesta.class, id);
        } finally {
            em.close();
        }
    }

    public int getEncuestaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Encuesta> rt = cq.from(Encuesta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
