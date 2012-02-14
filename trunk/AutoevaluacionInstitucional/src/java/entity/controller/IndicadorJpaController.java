/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import connection.jpaConnection;
import entity.Indicador;
import entity.controller.exceptions.IllegalOrphanException;
import entity.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Caracteristica;
import entity.Pregunta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vanesa
 */
public class IndicadorJpaController implements Serializable {

    public IndicadorJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Indicador indicador) {
        if (indicador.getPreguntaList() == null) {
            indicador.setPreguntaList(new ArrayList<Pregunta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Caracteristica caracteristicaId = indicador.getCaracteristicaId();
            if (caracteristicaId != null) {
                caracteristicaId = em.getReference(caracteristicaId.getClass(), caracteristicaId.getId());
                indicador.setCaracteristicaId(caracteristicaId);
            }
            List<Pregunta> attachedPreguntaList = new ArrayList<Pregunta>();
            for (Pregunta preguntaListPreguntaToAttach : indicador.getPreguntaList()) {
                preguntaListPreguntaToAttach = em.getReference(preguntaListPreguntaToAttach.getClass(), preguntaListPreguntaToAttach.getId());
                attachedPreguntaList.add(preguntaListPreguntaToAttach);
            }
            indicador.setPreguntaList(attachedPreguntaList);
            em.persist(indicador);
            if (caracteristicaId != null) {
                caracteristicaId.getIndicadorList().add(indicador);
                caracteristicaId = em.merge(caracteristicaId);
            }
            for (Pregunta preguntaListPregunta : indicador.getPreguntaList()) {
                Indicador oldIndicadorIdOfPreguntaListPregunta = preguntaListPregunta.getIndicadorId();
                preguntaListPregunta.setIndicadorId(indicador);
                preguntaListPregunta = em.merge(preguntaListPregunta);
                if (oldIndicadorIdOfPreguntaListPregunta != null) {
                    oldIndicadorIdOfPreguntaListPregunta.getPreguntaList().remove(preguntaListPregunta);
                    oldIndicadorIdOfPreguntaListPregunta = em.merge(oldIndicadorIdOfPreguntaListPregunta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Indicador indicador) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Indicador persistentIndicador = em.find(Indicador.class, indicador.getId());
            Caracteristica caracteristicaIdOld = persistentIndicador.getCaracteristicaId();
            Caracteristica caracteristicaIdNew = indicador.getCaracteristicaId();
            List<Pregunta> preguntaListOld = persistentIndicador.getPreguntaList();
            List<Pregunta> preguntaListNew = indicador.getPreguntaList();
            List<String> illegalOrphanMessages = null;
            for (Pregunta preguntaListOldPregunta : preguntaListOld) {
                if (!preguntaListNew.contains(preguntaListOldPregunta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pregunta " + preguntaListOldPregunta + " since its indicadorId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (caracteristicaIdNew != null) {
                caracteristicaIdNew = em.getReference(caracteristicaIdNew.getClass(), caracteristicaIdNew.getId());
                indicador.setCaracteristicaId(caracteristicaIdNew);
            }
            List<Pregunta> attachedPreguntaListNew = new ArrayList<Pregunta>();
            for (Pregunta preguntaListNewPreguntaToAttach : preguntaListNew) {
                preguntaListNewPreguntaToAttach = em.getReference(preguntaListNewPreguntaToAttach.getClass(), preguntaListNewPreguntaToAttach.getId());
                attachedPreguntaListNew.add(preguntaListNewPreguntaToAttach);
            }
            preguntaListNew = attachedPreguntaListNew;
            indicador.setPreguntaList(preguntaListNew);
            indicador = em.merge(indicador);
            if (caracteristicaIdOld != null && !caracteristicaIdOld.equals(caracteristicaIdNew)) {
                caracteristicaIdOld.getIndicadorList().remove(indicador);
                caracteristicaIdOld = em.merge(caracteristicaIdOld);
            }
            if (caracteristicaIdNew != null && !caracteristicaIdNew.equals(caracteristicaIdOld)) {
                caracteristicaIdNew.getIndicadorList().add(indicador);
                caracteristicaIdNew = em.merge(caracteristicaIdNew);
            }
            for (Pregunta preguntaListNewPregunta : preguntaListNew) {
                if (!preguntaListOld.contains(preguntaListNewPregunta)) {
                    Indicador oldIndicadorIdOfPreguntaListNewPregunta = preguntaListNewPregunta.getIndicadorId();
                    preguntaListNewPregunta.setIndicadorId(indicador);
                    preguntaListNewPregunta = em.merge(preguntaListNewPregunta);
                    if (oldIndicadorIdOfPreguntaListNewPregunta != null && !oldIndicadorIdOfPreguntaListNewPregunta.equals(indicador)) {
                        oldIndicadorIdOfPreguntaListNewPregunta.getPreguntaList().remove(preguntaListNewPregunta);
                        oldIndicadorIdOfPreguntaListNewPregunta = em.merge(oldIndicadorIdOfPreguntaListNewPregunta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = indicador.getId();
                if (findIndicador(id) == null) {
                    throw new NonexistentEntityException("The indicador with id " + id + " no longer exists.");
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
            Indicador indicador;
            try {
                indicador = em.getReference(Indicador.class, id);
                indicador.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The indicador with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Pregunta> preguntaListOrphanCheck = indicador.getPreguntaList();
            for (Pregunta preguntaListOrphanCheckPregunta : preguntaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Indicador (" + indicador + ") cannot be destroyed since the Pregunta " + preguntaListOrphanCheckPregunta + " in its preguntaList field has a non-nullable indicadorId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Caracteristica caracteristicaId = indicador.getCaracteristicaId();
            if (caracteristicaId != null) {
                caracteristicaId.getIndicadorList().remove(indicador);
                caracteristicaId = em.merge(caracteristicaId);
            }
            em.remove(indicador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Indicador> findIndicadorEntities() {
        return findIndicadorEntities(true, -1, -1);
    }

    public List<Indicador> findIndicadorEntities(int maxResults, int firstResult) {
        return findIndicadorEntities(false, maxResults, firstResult);
    }

    private List<Indicador> findIndicadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Indicador.class));
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

    public Indicador findIndicador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Indicador.class, id);
        } finally {
            em.close();
        }
    }

    public int getIndicadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Indicador> rt = cq.from(Indicador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
