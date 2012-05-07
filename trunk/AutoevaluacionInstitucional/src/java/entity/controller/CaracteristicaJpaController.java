/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.controller;

import connection.jpaConnection;
import entity.Caracteristica;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Factor;
import entity.Ponderacioncaracteristica;
import java.util.ArrayList;
import java.util.List;
import entity.Indicador;
import entity.controller.exceptions.IllegalOrphanException;
import entity.controller.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class CaracteristicaJpaController implements Serializable {

    public CaracteristicaJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Caracteristica caracteristica) {
        if (caracteristica.getPonderacioncaracteristicaList() == null) {
            caracteristica.setPonderacioncaracteristicaList(new ArrayList<Ponderacioncaracteristica>());
        }
        if (caracteristica.getIndicadorList() == null) {
            caracteristica.setIndicadorList(new ArrayList<Indicador>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factor factorId = caracteristica.getFactorId();
            if (factorId != null) {
                factorId = em.getReference(factorId.getClass(), factorId.getId());
                caracteristica.setFactorId(factorId);
            }
            List<Ponderacioncaracteristica> attachedPonderacioncaracteristicaList = new ArrayList<Ponderacioncaracteristica>();
            for (Ponderacioncaracteristica ponderacioncaracteristicaListPonderacioncaracteristicaToAttach : caracteristica.getPonderacioncaracteristicaList()) {
                ponderacioncaracteristicaListPonderacioncaracteristicaToAttach = em.getReference(ponderacioncaracteristicaListPonderacioncaracteristicaToAttach.getClass(), ponderacioncaracteristicaListPonderacioncaracteristicaToAttach.getId());
                attachedPonderacioncaracteristicaList.add(ponderacioncaracteristicaListPonderacioncaracteristicaToAttach);
            }
            caracteristica.setPonderacioncaracteristicaList(attachedPonderacioncaracteristicaList);
            List<Indicador> attachedIndicadorList = new ArrayList<Indicador>();
            for (Indicador indicadorListIndicadorToAttach : caracteristica.getIndicadorList()) {
                indicadorListIndicadorToAttach = em.getReference(indicadorListIndicadorToAttach.getClass(), indicadorListIndicadorToAttach.getId());
                attachedIndicadorList.add(indicadorListIndicadorToAttach);
            }
            caracteristica.setIndicadorList(attachedIndicadorList);
            em.persist(caracteristica);
            if (factorId != null) {
                factorId.getCaracteristicaList().add(caracteristica);
                factorId = em.merge(factorId);
            }
            for (Ponderacioncaracteristica ponderacioncaracteristicaListPonderacioncaracteristica : caracteristica.getPonderacioncaracteristicaList()) {
                Caracteristica oldCaracteristicaIdOfPonderacioncaracteristicaListPonderacioncaracteristica = ponderacioncaracteristicaListPonderacioncaracteristica.getCaracteristicaId();
                ponderacioncaracteristicaListPonderacioncaracteristica.setCaracteristicaId(caracteristica);
                ponderacioncaracteristicaListPonderacioncaracteristica = em.merge(ponderacioncaracteristicaListPonderacioncaracteristica);
                if (oldCaracteristicaIdOfPonderacioncaracteristicaListPonderacioncaracteristica != null) {
                    oldCaracteristicaIdOfPonderacioncaracteristicaListPonderacioncaracteristica.getPonderacioncaracteristicaList().remove(ponderacioncaracteristicaListPonderacioncaracteristica);
                    oldCaracteristicaIdOfPonderacioncaracteristicaListPonderacioncaracteristica = em.merge(oldCaracteristicaIdOfPonderacioncaracteristicaListPonderacioncaracteristica);
                }
            }
            for (Indicador indicadorListIndicador : caracteristica.getIndicadorList()) {
                Caracteristica oldCaracteristicaIdOfIndicadorListIndicador = indicadorListIndicador.getCaracteristicaId();
                indicadorListIndicador.setCaracteristicaId(caracteristica);
                indicadorListIndicador = em.merge(indicadorListIndicador);
                if (oldCaracteristicaIdOfIndicadorListIndicador != null) {
                    oldCaracteristicaIdOfIndicadorListIndicador.getIndicadorList().remove(indicadorListIndicador);
                    oldCaracteristicaIdOfIndicadorListIndicador = em.merge(oldCaracteristicaIdOfIndicadorListIndicador);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Caracteristica caracteristica) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Caracteristica persistentCaracteristica = em.find(Caracteristica.class, caracteristica.getId());
            Factor factorIdOld = persistentCaracteristica.getFactorId();
            Factor factorIdNew = caracteristica.getFactorId();
            List<Ponderacioncaracteristica> ponderacioncaracteristicaListOld = persistentCaracteristica.getPonderacioncaracteristicaList();
            List<Ponderacioncaracteristica> ponderacioncaracteristicaListNew = caracteristica.getPonderacioncaracteristicaList();
            List<Indicador> indicadorListOld = persistentCaracteristica.getIndicadorList();
            List<Indicador> indicadorListNew = caracteristica.getIndicadorList();
            List<String> illegalOrphanMessages = null;
            for (Ponderacioncaracteristica ponderacioncaracteristicaListOldPonderacioncaracteristica : ponderacioncaracteristicaListOld) {
                if (!ponderacioncaracteristicaListNew.contains(ponderacioncaracteristicaListOldPonderacioncaracteristica)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ponderacioncaracteristica " + ponderacioncaracteristicaListOldPonderacioncaracteristica + " since its caracteristicaId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (factorIdNew != null) {
                factorIdNew = em.getReference(factorIdNew.getClass(), factorIdNew.getId());
                caracteristica.setFactorId(factorIdNew);
            }
            List<Ponderacioncaracteristica> attachedPonderacioncaracteristicaListNew = new ArrayList<Ponderacioncaracteristica>();
            for (Ponderacioncaracteristica ponderacioncaracteristicaListNewPonderacioncaracteristicaToAttach : ponderacioncaracteristicaListNew) {
                ponderacioncaracteristicaListNewPonderacioncaracteristicaToAttach = em.getReference(ponderacioncaracteristicaListNewPonderacioncaracteristicaToAttach.getClass(), ponderacioncaracteristicaListNewPonderacioncaracteristicaToAttach.getId());
                attachedPonderacioncaracteristicaListNew.add(ponderacioncaracteristicaListNewPonderacioncaracteristicaToAttach);
            }
            ponderacioncaracteristicaListNew = attachedPonderacioncaracteristicaListNew;
            caracteristica.setPonderacioncaracteristicaList(ponderacioncaracteristicaListNew);
            List<Indicador> attachedIndicadorListNew = new ArrayList<Indicador>();
            for (Indicador indicadorListNewIndicadorToAttach : indicadorListNew) {
                indicadorListNewIndicadorToAttach = em.getReference(indicadorListNewIndicadorToAttach.getClass(), indicadorListNewIndicadorToAttach.getId());
                attachedIndicadorListNew.add(indicadorListNewIndicadorToAttach);
            }
            indicadorListNew = attachedIndicadorListNew;
            caracteristica.setIndicadorList(indicadorListNew);
            caracteristica = em.merge(caracteristica);
            if (factorIdOld != null && !factorIdOld.equals(factorIdNew)) {
                factorIdOld.getCaracteristicaList().remove(caracteristica);
                factorIdOld = em.merge(factorIdOld);
            }
            if (factorIdNew != null && !factorIdNew.equals(factorIdOld)) {
                factorIdNew.getCaracteristicaList().add(caracteristica);
                factorIdNew = em.merge(factorIdNew);
            }
            for (Ponderacioncaracteristica ponderacioncaracteristicaListNewPonderacioncaracteristica : ponderacioncaracteristicaListNew) {
                if (!ponderacioncaracteristicaListOld.contains(ponderacioncaracteristicaListNewPonderacioncaracteristica)) {
                    Caracteristica oldCaracteristicaIdOfPonderacioncaracteristicaListNewPonderacioncaracteristica = ponderacioncaracteristicaListNewPonderacioncaracteristica.getCaracteristicaId();
                    ponderacioncaracteristicaListNewPonderacioncaracteristica.setCaracteristicaId(caracteristica);
                    ponderacioncaracteristicaListNewPonderacioncaracteristica = em.merge(ponderacioncaracteristicaListNewPonderacioncaracteristica);
                    if (oldCaracteristicaIdOfPonderacioncaracteristicaListNewPonderacioncaracteristica != null && !oldCaracteristicaIdOfPonderacioncaracteristicaListNewPonderacioncaracteristica.equals(caracteristica)) {
                        oldCaracteristicaIdOfPonderacioncaracteristicaListNewPonderacioncaracteristica.getPonderacioncaracteristicaList().remove(ponderacioncaracteristicaListNewPonderacioncaracteristica);
                        oldCaracteristicaIdOfPonderacioncaracteristicaListNewPonderacioncaracteristica = em.merge(oldCaracteristicaIdOfPonderacioncaracteristicaListNewPonderacioncaracteristica);
                    }
                }
            }
            for (Indicador indicadorListOldIndicador : indicadorListOld) {
                if (!indicadorListNew.contains(indicadorListOldIndicador)) {
                    indicadorListOldIndicador.setCaracteristicaId(null);
                    indicadorListOldIndicador = em.merge(indicadorListOldIndicador);
                }
            }
            for (Indicador indicadorListNewIndicador : indicadorListNew) {
                if (!indicadorListOld.contains(indicadorListNewIndicador)) {
                    Caracteristica oldCaracteristicaIdOfIndicadorListNewIndicador = indicadorListNewIndicador.getCaracteristicaId();
                    indicadorListNewIndicador.setCaracteristicaId(caracteristica);
                    indicadorListNewIndicador = em.merge(indicadorListNewIndicador);
                    if (oldCaracteristicaIdOfIndicadorListNewIndicador != null && !oldCaracteristicaIdOfIndicadorListNewIndicador.equals(caracteristica)) {
                        oldCaracteristicaIdOfIndicadorListNewIndicador.getIndicadorList().remove(indicadorListNewIndicador);
                        oldCaracteristicaIdOfIndicadorListNewIndicador = em.merge(oldCaracteristicaIdOfIndicadorListNewIndicador);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = caracteristica.getId();
                if (findCaracteristica(id) == null) {
                    throw new NonexistentEntityException("The caracteristica with id " + id + " no longer exists.");
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
            Caracteristica caracteristica;
            try {
                caracteristica = em.getReference(Caracteristica.class, id);
                caracteristica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The caracteristica with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ponderacioncaracteristica> ponderacioncaracteristicaListOrphanCheck = caracteristica.getPonderacioncaracteristicaList();
            for (Ponderacioncaracteristica ponderacioncaracteristicaListOrphanCheckPonderacioncaracteristica : ponderacioncaracteristicaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Caracteristica (" + caracteristica + ") cannot be destroyed since the Ponderacioncaracteristica " + ponderacioncaracteristicaListOrphanCheckPonderacioncaracteristica + " in its ponderacioncaracteristicaList field has a non-nullable caracteristicaId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Factor factorId = caracteristica.getFactorId();
            if (factorId != null) {
                factorId.getCaracteristicaList().remove(caracteristica);
                factorId = em.merge(factorId);
            }
            List<Indicador> indicadorList = caracteristica.getIndicadorList();
            for (Indicador indicadorListIndicador : indicadorList) {
                indicadorListIndicador.setCaracteristicaId(null);
                indicadorListIndicador = em.merge(indicadorListIndicador);
            }
            em.remove(caracteristica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Caracteristica> findCaracteristicaEntities() {
        return findCaracteristicaEntities(true, -1, -1);
    }

    public List<Caracteristica> findCaracteristicaEntities(int maxResults, int firstResult) {
        return findCaracteristicaEntities(false, maxResults, firstResult);
    }

    private List<Caracteristica> findCaracteristicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Caracteristica.class));
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

    public Caracteristica findCaracteristica(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Caracteristica.class, id);
        } finally {
            em.close();
        }
    }

    public int getCaracteristicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Caracteristica> rt = cq.from(Caracteristica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
