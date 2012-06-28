/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import connection.jpaConnection;
import entity.*;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import entity.controller.exceptions.IllegalOrphanException;
import entity.controller.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Oscar
 */
public class IndicadorJpaController implements Serializable {

    public IndicadorJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Indicador indicador) {
        if (indicador.getInstrumentoList() == null) {
            indicador.setInstrumentoList(new ArrayList<Instrumento>());
        }
        if (indicador.getNumericadocumentalList() == null) {
            indicador.setNumericadocumentalList(new ArrayList<Numericadocumental>());
        }
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
            List<Instrumento> attachedInstrumentoList = new ArrayList<Instrumento>();
            for (Instrumento instrumentoListInstrumentoToAttach : indicador.getInstrumentoList()) {
                instrumentoListInstrumentoToAttach = em.getReference(instrumentoListInstrumentoToAttach.getClass(), instrumentoListInstrumentoToAttach.getId());
                attachedInstrumentoList.add(instrumentoListInstrumentoToAttach);
            }
            indicador.setInstrumentoList(attachedInstrumentoList);
            List<Numericadocumental> attachedNumericadocumentalList = new ArrayList<Numericadocumental>();
            for (Numericadocumental numericadocumentalListNumericadocumentalToAttach : indicador.getNumericadocumentalList()) {
                numericadocumentalListNumericadocumentalToAttach = em.getReference(numericadocumentalListNumericadocumentalToAttach.getClass(), numericadocumentalListNumericadocumentalToAttach.getId());
                attachedNumericadocumentalList.add(numericadocumentalListNumericadocumentalToAttach);
            }
            indicador.setNumericadocumentalList(attachedNumericadocumentalList);
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
            for (Instrumento instrumentoListInstrumento : indicador.getInstrumentoList()) {
                instrumentoListInstrumento.getIndicadorList().add(indicador);
                instrumentoListInstrumento = em.merge(instrumentoListInstrumento);
            }
            for (Numericadocumental numericadocumentalListNumericadocumental : indicador.getNumericadocumentalList()) {
                Indicador oldIndicadorIdOfNumericadocumentalListNumericadocumental = numericadocumentalListNumericadocumental.getIndicadorId();
                numericadocumentalListNumericadocumental.setIndicadorId(indicador);
                numericadocumentalListNumericadocumental = em.merge(numericadocumentalListNumericadocumental);
                if (oldIndicadorIdOfNumericadocumentalListNumericadocumental != null) {
                    oldIndicadorIdOfNumericadocumentalListNumericadocumental.getNumericadocumentalList().remove(numericadocumentalListNumericadocumental);
                    oldIndicadorIdOfNumericadocumentalListNumericadocumental = em.merge(oldIndicadorIdOfNumericadocumentalListNumericadocumental);
                }
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
            List<Instrumento> instrumentoListOld = persistentIndicador.getInstrumentoList();
            List<Instrumento> instrumentoListNew = indicador.getInstrumentoList();
            List<Numericadocumental> numericadocumentalListOld = persistentIndicador.getNumericadocumentalList();
            List<Numericadocumental> numericadocumentalListNew = indicador.getNumericadocumentalList();
            List<Pregunta> preguntaListOld = persistentIndicador.getPreguntaList();
            List<Pregunta> preguntaListNew = indicador.getPreguntaList();
            List<String> illegalOrphanMessages = null;
            for (Numericadocumental numericadocumentalListOldNumericadocumental : numericadocumentalListOld) {
                if (!numericadocumentalListNew.contains(numericadocumentalListOldNumericadocumental)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Numericadocumental " + numericadocumentalListOldNumericadocumental + " since its indicadorId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (caracteristicaIdNew != null) {
                caracteristicaIdNew = em.getReference(caracteristicaIdNew.getClass(), caracteristicaIdNew.getId());
                indicador.setCaracteristicaId(caracteristicaIdNew);
            }
            List<Instrumento> attachedInstrumentoListNew = new ArrayList<Instrumento>();
            for (Instrumento instrumentoListNewInstrumentoToAttach : instrumentoListNew) {
                instrumentoListNewInstrumentoToAttach = em.getReference(instrumentoListNewInstrumentoToAttach.getClass(), instrumentoListNewInstrumentoToAttach.getId());
                attachedInstrumentoListNew.add(instrumentoListNewInstrumentoToAttach);
            }
            instrumentoListNew = attachedInstrumentoListNew;
            indicador.setInstrumentoList(instrumentoListNew);
            List<Numericadocumental> attachedNumericadocumentalListNew = new ArrayList<Numericadocumental>();
            for (Numericadocumental numericadocumentalListNewNumericadocumentalToAttach : numericadocumentalListNew) {
                numericadocumentalListNewNumericadocumentalToAttach = em.getReference(numericadocumentalListNewNumericadocumentalToAttach.getClass(), numericadocumentalListNewNumericadocumentalToAttach.getId());
                attachedNumericadocumentalListNew.add(numericadocumentalListNewNumericadocumentalToAttach);
            }
            numericadocumentalListNew = attachedNumericadocumentalListNew;
            indicador.setNumericadocumentalList(numericadocumentalListNew);
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
            for (Instrumento instrumentoListOldInstrumento : instrumentoListOld) {
                if (!instrumentoListNew.contains(instrumentoListOldInstrumento)) {
                    instrumentoListOldInstrumento.getIndicadorList().remove(indicador);
                    instrumentoListOldInstrumento = em.merge(instrumentoListOldInstrumento);
                }
            }
            for (Instrumento instrumentoListNewInstrumento : instrumentoListNew) {
                if (!instrumentoListOld.contains(instrumentoListNewInstrumento)) {
                    instrumentoListNewInstrumento.getIndicadorList().add(indicador);
                    instrumentoListNewInstrumento = em.merge(instrumentoListNewInstrumento);
                }
            }
            for (Numericadocumental numericadocumentalListNewNumericadocumental : numericadocumentalListNew) {
                if (!numericadocumentalListOld.contains(numericadocumentalListNewNumericadocumental)) {
                    Indicador oldIndicadorIdOfNumericadocumentalListNewNumericadocumental = numericadocumentalListNewNumericadocumental.getIndicadorId();
                    numericadocumentalListNewNumericadocumental.setIndicadorId(indicador);
                    numericadocumentalListNewNumericadocumental = em.merge(numericadocumentalListNewNumericadocumental);
                    if (oldIndicadorIdOfNumericadocumentalListNewNumericadocumental != null && !oldIndicadorIdOfNumericadocumentalListNewNumericadocumental.equals(indicador)) {
                        oldIndicadorIdOfNumericadocumentalListNewNumericadocumental.getNumericadocumentalList().remove(numericadocumentalListNewNumericadocumental);
                        oldIndicadorIdOfNumericadocumentalListNewNumericadocumental = em.merge(oldIndicadorIdOfNumericadocumentalListNewNumericadocumental);
                    }
                }
            }
            for (Pregunta preguntaListOldPregunta : preguntaListOld) {
                if (!preguntaListNew.contains(preguntaListOldPregunta)) {
                    preguntaListOldPregunta.setIndicadorId(null);
                    preguntaListOldPregunta = em.merge(preguntaListOldPregunta);
                }
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
            List<Numericadocumental> numericadocumentalListOrphanCheck = indicador.getNumericadocumentalList();
            for (Numericadocumental numericadocumentalListOrphanCheckNumericadocumental : numericadocumentalListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Indicador (" + indicador + ") cannot be destroyed since the Numericadocumental " + numericadocumentalListOrphanCheckNumericadocumental + " in its numericadocumentalList field has a non-nullable indicadorId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Caracteristica caracteristicaId = indicador.getCaracteristicaId();
            if (caracteristicaId != null) {
                caracteristicaId.getIndicadorList().remove(indicador);
                caracteristicaId = em.merge(caracteristicaId);
            }
            List<Instrumento> instrumentoList = indicador.getInstrumentoList();
            for (Instrumento instrumentoListInstrumento : instrumentoList) {
                instrumentoListInstrumento.getIndicadorList().remove(indicador);
                instrumentoListInstrumento = em.merge(instrumentoListInstrumento);
            }
            List<Pregunta> preguntaList = indicador.getPreguntaList();
            for (Pregunta preguntaListPregunta : preguntaList) {
                preguntaListPregunta.setIndicadorId(null);
                preguntaListPregunta = em.merge(preguntaListPregunta);
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
