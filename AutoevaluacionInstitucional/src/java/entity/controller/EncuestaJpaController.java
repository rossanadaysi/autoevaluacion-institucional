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
import java.util.ArrayList;
import java.util.List;
import entity.Asignacionencuesta;
import entity.Encabezado;
import entity.Encuesta;
import entity.controller.exceptions.IllegalOrphanException;
import entity.controller.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class EncuestaJpaController implements Serializable {

   public EncuestaJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Encuesta encuesta) {
        if (encuesta.getPreguntaList() == null) {
            encuesta.setPreguntaList(new ArrayList<Pregunta>());
        }
        if (encuesta.getAsignacionencuestaList() == null) {
            encuesta.setAsignacionencuestaList(new ArrayList<Asignacionencuesta>());
        }
        if (encuesta.getEncabezadoList() == null) {
            encuesta.setEncabezadoList(new ArrayList<Encabezado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pregunta> attachedPreguntaList = new ArrayList<Pregunta>();
            for (Pregunta preguntaListPreguntaToAttach : encuesta.getPreguntaList()) {
                preguntaListPreguntaToAttach = em.getReference(preguntaListPreguntaToAttach.getClass(), preguntaListPreguntaToAttach.getId());
                attachedPreguntaList.add(preguntaListPreguntaToAttach);
            }
            encuesta.setPreguntaList(attachedPreguntaList);
            List<Asignacionencuesta> attachedAsignacionencuestaList = new ArrayList<Asignacionencuesta>();
            for (Asignacionencuesta asignacionencuestaListAsignacionencuestaToAttach : encuesta.getAsignacionencuestaList()) {
                asignacionencuestaListAsignacionencuestaToAttach = em.getReference(asignacionencuestaListAsignacionencuestaToAttach.getClass(), asignacionencuestaListAsignacionencuestaToAttach.getId());
                attachedAsignacionencuestaList.add(asignacionencuestaListAsignacionencuestaToAttach);
            }
            encuesta.setAsignacionencuestaList(attachedAsignacionencuestaList);
            List<Encabezado> attachedEncabezadoList = new ArrayList<Encabezado>();
            for (Encabezado encabezadoListEncabezadoToAttach : encuesta.getEncabezadoList()) {
                encabezadoListEncabezadoToAttach = em.getReference(encabezadoListEncabezadoToAttach.getClass(), encabezadoListEncabezadoToAttach.getId());
                attachedEncabezadoList.add(encabezadoListEncabezadoToAttach);
            }
            encuesta.setEncabezadoList(attachedEncabezadoList);
            em.persist(encuesta);
            for (Pregunta preguntaListPregunta : encuesta.getPreguntaList()) {
                preguntaListPregunta.getEncuestaList().add(encuesta);
                preguntaListPregunta = em.merge(preguntaListPregunta);
            }
            for (Asignacionencuesta asignacionencuestaListAsignacionencuesta : encuesta.getAsignacionencuestaList()) {
                Encuesta oldEncuestaIdOfAsignacionencuestaListAsignacionencuesta = asignacionencuestaListAsignacionencuesta.getEncuestaId();
                asignacionencuestaListAsignacionencuesta.setEncuestaId(encuesta);
                asignacionencuestaListAsignacionencuesta = em.merge(asignacionencuestaListAsignacionencuesta);
                if (oldEncuestaIdOfAsignacionencuestaListAsignacionencuesta != null) {
                    oldEncuestaIdOfAsignacionencuestaListAsignacionencuesta.getAsignacionencuestaList().remove(asignacionencuestaListAsignacionencuesta);
                    oldEncuestaIdOfAsignacionencuestaListAsignacionencuesta = em.merge(oldEncuestaIdOfAsignacionencuestaListAsignacionencuesta);
                }
            }
            for (Encabezado encabezadoListEncabezado : encuesta.getEncabezadoList()) {
                Encuesta oldEncuestaIdOfEncabezadoListEncabezado = encabezadoListEncabezado.getEncuestaId();
                encabezadoListEncabezado.setEncuestaId(encuesta);
                encabezadoListEncabezado = em.merge(encabezadoListEncabezado);
                if (oldEncuestaIdOfEncabezadoListEncabezado != null) {
                    oldEncuestaIdOfEncabezadoListEncabezado.getEncabezadoList().remove(encabezadoListEncabezado);
                    oldEncuestaIdOfEncabezadoListEncabezado = em.merge(oldEncuestaIdOfEncabezadoListEncabezado);
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
            List<Pregunta> preguntaListOld = persistentEncuesta.getPreguntaList();
            List<Pregunta> preguntaListNew = encuesta.getPreguntaList();
            List<Asignacionencuesta> asignacionencuestaListOld = persistentEncuesta.getAsignacionencuestaList();
            List<Asignacionencuesta> asignacionencuestaListNew = encuesta.getAsignacionencuestaList();
            List<Encabezado> encabezadoListOld = persistentEncuesta.getEncabezadoList();
            List<Encabezado> encabezadoListNew = encuesta.getEncabezadoList();
            List<String> illegalOrphanMessages = null;
            for (Asignacionencuesta asignacionencuestaListOldAsignacionencuesta : asignacionencuestaListOld) {
                if (!asignacionencuestaListNew.contains(asignacionencuestaListOldAsignacionencuesta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asignacionencuesta " + asignacionencuestaListOldAsignacionencuesta + " since its encuestaId field is not nullable.");
                }
            }
            for (Encabezado encabezadoListOldEncabezado : encabezadoListOld) {
                if (!encabezadoListNew.contains(encabezadoListOldEncabezado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Encabezado " + encabezadoListOldEncabezado + " since its encuestaId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Pregunta> attachedPreguntaListNew = new ArrayList<Pregunta>();
            for (Pregunta preguntaListNewPreguntaToAttach : preguntaListNew) {
                preguntaListNewPreguntaToAttach = em.getReference(preguntaListNewPreguntaToAttach.getClass(), preguntaListNewPreguntaToAttach.getId());
                attachedPreguntaListNew.add(preguntaListNewPreguntaToAttach);
            }
            preguntaListNew = attachedPreguntaListNew;
            encuesta.setPreguntaList(preguntaListNew);
            List<Asignacionencuesta> attachedAsignacionencuestaListNew = new ArrayList<Asignacionencuesta>();
            for (Asignacionencuesta asignacionencuestaListNewAsignacionencuestaToAttach : asignacionencuestaListNew) {
                asignacionencuestaListNewAsignacionencuestaToAttach = em.getReference(asignacionencuestaListNewAsignacionencuestaToAttach.getClass(), asignacionencuestaListNewAsignacionencuestaToAttach.getId());
                attachedAsignacionencuestaListNew.add(asignacionencuestaListNewAsignacionencuestaToAttach);
            }
            asignacionencuestaListNew = attachedAsignacionencuestaListNew;
            encuesta.setAsignacionencuestaList(asignacionencuestaListNew);
            List<Encabezado> attachedEncabezadoListNew = new ArrayList<Encabezado>();
            for (Encabezado encabezadoListNewEncabezadoToAttach : encabezadoListNew) {
                encabezadoListNewEncabezadoToAttach = em.getReference(encabezadoListNewEncabezadoToAttach.getClass(), encabezadoListNewEncabezadoToAttach.getId());
                attachedEncabezadoListNew.add(encabezadoListNewEncabezadoToAttach);
            }
            encabezadoListNew = attachedEncabezadoListNew;
            encuesta.setEncabezadoList(encabezadoListNew);
            encuesta = em.merge(encuesta);
            for (Pregunta preguntaListOldPregunta : preguntaListOld) {
                if (!preguntaListNew.contains(preguntaListOldPregunta)) {
                    preguntaListOldPregunta.getEncuestaList().remove(encuesta);
                    preguntaListOldPregunta = em.merge(preguntaListOldPregunta);
                }
            }
            for (Pregunta preguntaListNewPregunta : preguntaListNew) {
                if (!preguntaListOld.contains(preguntaListNewPregunta)) {
                    preguntaListNewPregunta.getEncuestaList().add(encuesta);
                    preguntaListNewPregunta = em.merge(preguntaListNewPregunta);
                }
            }
            for (Asignacionencuesta asignacionencuestaListNewAsignacionencuesta : asignacionencuestaListNew) {
                if (!asignacionencuestaListOld.contains(asignacionencuestaListNewAsignacionencuesta)) {
                    Encuesta oldEncuestaIdOfAsignacionencuestaListNewAsignacionencuesta = asignacionencuestaListNewAsignacionencuesta.getEncuestaId();
                    asignacionencuestaListNewAsignacionencuesta.setEncuestaId(encuesta);
                    asignacionencuestaListNewAsignacionencuesta = em.merge(asignacionencuestaListNewAsignacionencuesta);
                    if (oldEncuestaIdOfAsignacionencuestaListNewAsignacionencuesta != null && !oldEncuestaIdOfAsignacionencuestaListNewAsignacionencuesta.equals(encuesta)) {
                        oldEncuestaIdOfAsignacionencuestaListNewAsignacionencuesta.getAsignacionencuestaList().remove(asignacionencuestaListNewAsignacionencuesta);
                        oldEncuestaIdOfAsignacionencuestaListNewAsignacionencuesta = em.merge(oldEncuestaIdOfAsignacionencuestaListNewAsignacionencuesta);
                    }
                }
            }
            for (Encabezado encabezadoListNewEncabezado : encabezadoListNew) {
                if (!encabezadoListOld.contains(encabezadoListNewEncabezado)) {
                    Encuesta oldEncuestaIdOfEncabezadoListNewEncabezado = encabezadoListNewEncabezado.getEncuestaId();
                    encabezadoListNewEncabezado.setEncuestaId(encuesta);
                    encabezadoListNewEncabezado = em.merge(encabezadoListNewEncabezado);
                    if (oldEncuestaIdOfEncabezadoListNewEncabezado != null && !oldEncuestaIdOfEncabezadoListNewEncabezado.equals(encuesta)) {
                        oldEncuestaIdOfEncabezadoListNewEncabezado.getEncabezadoList().remove(encabezadoListNewEncabezado);
                        oldEncuestaIdOfEncabezadoListNewEncabezado = em.merge(oldEncuestaIdOfEncabezadoListNewEncabezado);
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
            List<Asignacionencuesta> asignacionencuestaListOrphanCheck = encuesta.getAsignacionencuestaList();
            for (Asignacionencuesta asignacionencuestaListOrphanCheckAsignacionencuesta : asignacionencuestaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Encuesta (" + encuesta + ") cannot be destroyed since the Asignacionencuesta " + asignacionencuestaListOrphanCheckAsignacionencuesta + " in its asignacionencuestaList field has a non-nullable encuestaId field.");
            }
            List<Encabezado> encabezadoListOrphanCheck = encuesta.getEncabezadoList();
            for (Encabezado encabezadoListOrphanCheckEncabezado : encabezadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Encuesta (" + encuesta + ") cannot be destroyed since the Encabezado " + encabezadoListOrphanCheckEncabezado + " in its encabezadoList field has a non-nullable encuestaId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Pregunta> preguntaList = encuesta.getPreguntaList();
            for (Pregunta preguntaListPregunta : preguntaList) {
                preguntaListPregunta.getEncuestaList().remove(encuesta);
                preguntaListPregunta = em.merge(preguntaListPregunta);
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
