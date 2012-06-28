/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import entity.*;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.controller.exceptions.IllegalOrphanException;
import entity.controller.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Oscar
 */
public class EncabezadoJpaController implements Serializable {

    public EncabezadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Encabezado encabezado) {
        if (encabezado.getResultadoevaluacionList() == null) {
            encabezado.setResultadoevaluacionList(new ArrayList<Resultadoevaluacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fuente fuenteId = encabezado.getFuenteId();
            if (fuenteId != null) {
                fuenteId = em.getReference(fuenteId.getClass(), fuenteId.getId());
                encabezado.setFuenteId(fuenteId);
            }
            Encuesta encuestaId = encabezado.getEncuestaId();
            if (encuestaId != null) {
                encuestaId = em.getReference(encuestaId.getClass(), encuestaId.getId());
                encabezado.setEncuestaId(encuestaId);
            }
            Proceso procesoId = encabezado.getProcesoId();
            if (procesoId != null) {
                procesoId = em.getReference(procesoId.getClass(), procesoId.getId());
                encabezado.setProcesoId(procesoId);
            }
            Persona personaId = encabezado.getPersonaId();
            if (personaId != null) {
                personaId = em.getReference(personaId.getClass(), personaId.getId());
                encabezado.setPersonaId(personaId);
            }
            List<Resultadoevaluacion> attachedResultadoevaluacionList = new ArrayList<Resultadoevaluacion>();
            for (Resultadoevaluacion resultadoevaluacionListResultadoevaluacionToAttach : encabezado.getResultadoevaluacionList()) {
                resultadoevaluacionListResultadoevaluacionToAttach = em.getReference(resultadoevaluacionListResultadoevaluacionToAttach.getClass(), resultadoevaluacionListResultadoevaluacionToAttach.getIdResultadoEvaluacion());
                attachedResultadoevaluacionList.add(resultadoevaluacionListResultadoevaluacionToAttach);
            }
            encabezado.setResultadoevaluacionList(attachedResultadoevaluacionList);
            em.persist(encabezado);
            if (fuenteId != null) {
                fuenteId.getEncabezadoList().add(encabezado);
                fuenteId = em.merge(fuenteId);
            }
            if (encuestaId != null) {
                encuestaId.getEncabezadoList().add(encabezado);
                encuestaId = em.merge(encuestaId);
            }
            if (procesoId != null) {
                procesoId.getEncabezadoList().add(encabezado);
                procesoId = em.merge(procesoId);
            }
            if (personaId != null) {
                personaId.getEncabezadoList().add(encabezado);
                personaId = em.merge(personaId);
            }
            for (Resultadoevaluacion resultadoevaluacionListResultadoevaluacion : encabezado.getResultadoevaluacionList()) {
                Encabezado oldEncabezadoIdOfResultadoevaluacionListResultadoevaluacion = resultadoevaluacionListResultadoevaluacion.getEncabezadoId();
                resultadoevaluacionListResultadoevaluacion.setEncabezadoId(encabezado);
                resultadoevaluacionListResultadoevaluacion = em.merge(resultadoevaluacionListResultadoevaluacion);
                if (oldEncabezadoIdOfResultadoevaluacionListResultadoevaluacion != null) {
                    oldEncabezadoIdOfResultadoevaluacionListResultadoevaluacion.getResultadoevaluacionList().remove(resultadoevaluacionListResultadoevaluacion);
                    oldEncabezadoIdOfResultadoevaluacionListResultadoevaluacion = em.merge(oldEncabezadoIdOfResultadoevaluacionListResultadoevaluacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Encabezado encabezado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Encabezado persistentEncabezado = em.find(Encabezado.class, encabezado.getId());
            Fuente fuenteIdOld = persistentEncabezado.getFuenteId();
            Fuente fuenteIdNew = encabezado.getFuenteId();
            Encuesta encuestaIdOld = persistentEncabezado.getEncuestaId();
            Encuesta encuestaIdNew = encabezado.getEncuestaId();
            Proceso procesoIdOld = persistentEncabezado.getProcesoId();
            Proceso procesoIdNew = encabezado.getProcesoId();
            Persona personaIdOld = persistentEncabezado.getPersonaId();
            Persona personaIdNew = encabezado.getPersonaId();
            List<Resultadoevaluacion> resultadoevaluacionListOld = persistentEncabezado.getResultadoevaluacionList();
            List<Resultadoevaluacion> resultadoevaluacionListNew = encabezado.getResultadoevaluacionList();
            List<String> illegalOrphanMessages = null;
            for (Resultadoevaluacion resultadoevaluacionListOldResultadoevaluacion : resultadoevaluacionListOld) {
                if (!resultadoevaluacionListNew.contains(resultadoevaluacionListOldResultadoevaluacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Resultadoevaluacion " + resultadoevaluacionListOldResultadoevaluacion + " since its encabezadoId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fuenteIdNew != null) {
                fuenteIdNew = em.getReference(fuenteIdNew.getClass(), fuenteIdNew.getId());
                encabezado.setFuenteId(fuenteIdNew);
            }
            if (encuestaIdNew != null) {
                encuestaIdNew = em.getReference(encuestaIdNew.getClass(), encuestaIdNew.getId());
                encabezado.setEncuestaId(encuestaIdNew);
            }
            if (procesoIdNew != null) {
                procesoIdNew = em.getReference(procesoIdNew.getClass(), procesoIdNew.getId());
                encabezado.setProcesoId(procesoIdNew);
            }
            if (personaIdNew != null) {
                personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
                encabezado.setPersonaId(personaIdNew);
            }
            List<Resultadoevaluacion> attachedResultadoevaluacionListNew = new ArrayList<Resultadoevaluacion>();
            for (Resultadoevaluacion resultadoevaluacionListNewResultadoevaluacionToAttach : resultadoevaluacionListNew) {
                resultadoevaluacionListNewResultadoevaluacionToAttach = em.getReference(resultadoevaluacionListNewResultadoevaluacionToAttach.getClass(), resultadoevaluacionListNewResultadoevaluacionToAttach.getIdResultadoEvaluacion());
                attachedResultadoevaluacionListNew.add(resultadoevaluacionListNewResultadoevaluacionToAttach);
            }
            resultadoevaluacionListNew = attachedResultadoevaluacionListNew;
            encabezado.setResultadoevaluacionList(resultadoevaluacionListNew);
            encabezado = em.merge(encabezado);
            if (fuenteIdOld != null && !fuenteIdOld.equals(fuenteIdNew)) {
                fuenteIdOld.getEncabezadoList().remove(encabezado);
                fuenteIdOld = em.merge(fuenteIdOld);
            }
            if (fuenteIdNew != null && !fuenteIdNew.equals(fuenteIdOld)) {
                fuenteIdNew.getEncabezadoList().add(encabezado);
                fuenteIdNew = em.merge(fuenteIdNew);
            }
            if (encuestaIdOld != null && !encuestaIdOld.equals(encuestaIdNew)) {
                encuestaIdOld.getEncabezadoList().remove(encabezado);
                encuestaIdOld = em.merge(encuestaIdOld);
            }
            if (encuestaIdNew != null && !encuestaIdNew.equals(encuestaIdOld)) {
                encuestaIdNew.getEncabezadoList().add(encabezado);
                encuestaIdNew = em.merge(encuestaIdNew);
            }
            if (procesoIdOld != null && !procesoIdOld.equals(procesoIdNew)) {
                procesoIdOld.getEncabezadoList().remove(encabezado);
                procesoIdOld = em.merge(procesoIdOld);
            }
            if (procesoIdNew != null && !procesoIdNew.equals(procesoIdOld)) {
                procesoIdNew.getEncabezadoList().add(encabezado);
                procesoIdNew = em.merge(procesoIdNew);
            }
            if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
                personaIdOld.getEncabezadoList().remove(encabezado);
                personaIdOld = em.merge(personaIdOld);
            }
            if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
                personaIdNew.getEncabezadoList().add(encabezado);
                personaIdNew = em.merge(personaIdNew);
            }
            for (Resultadoevaluacion resultadoevaluacionListNewResultadoevaluacion : resultadoevaluacionListNew) {
                if (!resultadoevaluacionListOld.contains(resultadoevaluacionListNewResultadoevaluacion)) {
                    Encabezado oldEncabezadoIdOfResultadoevaluacionListNewResultadoevaluacion = resultadoevaluacionListNewResultadoevaluacion.getEncabezadoId();
                    resultadoevaluacionListNewResultadoevaluacion.setEncabezadoId(encabezado);
                    resultadoevaluacionListNewResultadoevaluacion = em.merge(resultadoevaluacionListNewResultadoevaluacion);
                    if (oldEncabezadoIdOfResultadoevaluacionListNewResultadoevaluacion != null && !oldEncabezadoIdOfResultadoevaluacionListNewResultadoevaluacion.equals(encabezado)) {
                        oldEncabezadoIdOfResultadoevaluacionListNewResultadoevaluacion.getResultadoevaluacionList().remove(resultadoevaluacionListNewResultadoevaluacion);
                        oldEncabezadoIdOfResultadoevaluacionListNewResultadoevaluacion = em.merge(oldEncabezadoIdOfResultadoevaluacionListNewResultadoevaluacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = encabezado.getId();
                if (findEncabezado(id) == null) {
                    throw new NonexistentEntityException("The encabezado with id " + id + " no longer exists.");
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
            Encabezado encabezado;
            try {
                encabezado = em.getReference(Encabezado.class, id);
                encabezado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The encabezado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Resultadoevaluacion> resultadoevaluacionListOrphanCheck = encabezado.getResultadoevaluacionList();
            for (Resultadoevaluacion resultadoevaluacionListOrphanCheckResultadoevaluacion : resultadoevaluacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Encabezado (" + encabezado + ") cannot be destroyed since the Resultadoevaluacion " + resultadoevaluacionListOrphanCheckResultadoevaluacion + " in its resultadoevaluacionList field has a non-nullable encabezadoId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Fuente fuenteId = encabezado.getFuenteId();
            if (fuenteId != null) {
                fuenteId.getEncabezadoList().remove(encabezado);
                fuenteId = em.merge(fuenteId);
            }
            Encuesta encuestaId = encabezado.getEncuestaId();
            if (encuestaId != null) {
                encuestaId.getEncabezadoList().remove(encabezado);
                encuestaId = em.merge(encuestaId);
            }
            Proceso procesoId = encabezado.getProcesoId();
            if (procesoId != null) {
                procesoId.getEncabezadoList().remove(encabezado);
                procesoId = em.merge(procesoId);
            }
            Persona personaId = encabezado.getPersonaId();
            if (personaId != null) {
                personaId.getEncabezadoList().remove(encabezado);
                personaId = em.merge(personaId);
            }
            em.remove(encabezado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Encabezado> findEncabezadoEntities() {
        return findEncabezadoEntities(true, -1, -1);
    }

    public List<Encabezado> findEncabezadoEntities(int maxResults, int firstResult) {
        return findEncabezadoEntities(false, maxResults, firstResult);
    }

    private List<Encabezado> findEncabezadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Encabezado.class));
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

    public Encabezado findEncabezado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Encabezado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEncabezadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Encabezado> rt = cq.from(Encabezado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
