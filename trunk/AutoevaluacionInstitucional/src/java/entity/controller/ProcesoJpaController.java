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
public class ProcesoJpaController implements Serializable {

     public ProcesoJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Proceso proceso) {
        if (proceso.getPonderacioncaracteristicaList() == null) {
            proceso.setPonderacioncaracteristicaList(new ArrayList<Ponderacioncaracteristica>());
        }
        if (proceso.getMuestraList() == null) {
            proceso.setMuestraList(new ArrayList<Muestra>());
        }
        if (proceso.getPonderacionfactorList() == null) {
            proceso.setPonderacionfactorList(new ArrayList<Ponderacionfactor>());
        }
        if (proceso.getAsignacionencuestaList() == null) {
            proceso.setAsignacionencuestaList(new ArrayList<Asignacionencuesta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa programaId = proceso.getProgramaId();
            if (programaId != null) {
                programaId = em.getReference(programaId.getClass(), programaId.getId());
                proceso.setProgramaId(programaId);
            }
            List<Ponderacioncaracteristica> attachedPonderacioncaracteristicaList = new ArrayList<Ponderacioncaracteristica>();
            for (Ponderacioncaracteristica ponderacioncaracteristicaListPonderacioncaracteristicaToAttach : proceso.getPonderacioncaracteristicaList()) {
                ponderacioncaracteristicaListPonderacioncaracteristicaToAttach = em.getReference(ponderacioncaracteristicaListPonderacioncaracteristicaToAttach.getClass(), ponderacioncaracteristicaListPonderacioncaracteristicaToAttach.getId());
                attachedPonderacioncaracteristicaList.add(ponderacioncaracteristicaListPonderacioncaracteristicaToAttach);
            }
            proceso.setPonderacioncaracteristicaList(attachedPonderacioncaracteristicaList);
            List<Muestra> attachedMuestraList = new ArrayList<Muestra>();
            for (Muestra muestraListMuestraToAttach : proceso.getMuestraList()) {
                muestraListMuestraToAttach = em.getReference(muestraListMuestraToAttach.getClass(), muestraListMuestraToAttach.getId());
                attachedMuestraList.add(muestraListMuestraToAttach);
            }
            proceso.setMuestraList(attachedMuestraList);
            List<Ponderacionfactor> attachedPonderacionfactorList = new ArrayList<Ponderacionfactor>();
            for (Ponderacionfactor ponderacionfactorListPonderacionfactorToAttach : proceso.getPonderacionfactorList()) {
                ponderacionfactorListPonderacionfactorToAttach = em.getReference(ponderacionfactorListPonderacionfactorToAttach.getClass(), ponderacionfactorListPonderacionfactorToAttach.getId());
                attachedPonderacionfactorList.add(ponderacionfactorListPonderacionfactorToAttach);
            }
            proceso.setPonderacionfactorList(attachedPonderacionfactorList);
            List<Asignacionencuesta> attachedAsignacionencuestaList = new ArrayList<Asignacionencuesta>();
            for (Asignacionencuesta asignacionencuestaListAsignacionencuestaToAttach : proceso.getAsignacionencuestaList()) {
                asignacionencuestaListAsignacionencuestaToAttach = em.getReference(asignacionencuestaListAsignacionencuestaToAttach.getClass(), asignacionencuestaListAsignacionencuestaToAttach.getId());
                attachedAsignacionencuestaList.add(asignacionencuestaListAsignacionencuestaToAttach);
            }
            proceso.setAsignacionencuestaList(attachedAsignacionencuestaList);
            em.persist(proceso);
            if (programaId != null) {
                programaId.getProcesoList().add(proceso);
                programaId = em.merge(programaId);
            }
            for (Ponderacioncaracteristica ponderacioncaracteristicaListPonderacioncaracteristica : proceso.getPonderacioncaracteristicaList()) {
                Proceso oldProcesoIdOfPonderacioncaracteristicaListPonderacioncaracteristica = ponderacioncaracteristicaListPonderacioncaracteristica.getProcesoId();
                ponderacioncaracteristicaListPonderacioncaracteristica.setProcesoId(proceso);
                ponderacioncaracteristicaListPonderacioncaracteristica = em.merge(ponderacioncaracteristicaListPonderacioncaracteristica);
                if (oldProcesoIdOfPonderacioncaracteristicaListPonderacioncaracteristica != null) {
                    oldProcesoIdOfPonderacioncaracteristicaListPonderacioncaracteristica.getPonderacioncaracteristicaList().remove(ponderacioncaracteristicaListPonderacioncaracteristica);
                    oldProcesoIdOfPonderacioncaracteristicaListPonderacioncaracteristica = em.merge(oldProcesoIdOfPonderacioncaracteristicaListPonderacioncaracteristica);
                }
            }
            for (Muestra muestraListMuestra : proceso.getMuestraList()) {
                Proceso oldProcesoIdOfMuestraListMuestra = muestraListMuestra.getProcesoId();
                muestraListMuestra.setProcesoId(proceso);
                muestraListMuestra = em.merge(muestraListMuestra);
                if (oldProcesoIdOfMuestraListMuestra != null) {
                    oldProcesoIdOfMuestraListMuestra.getMuestraList().remove(muestraListMuestra);
                    oldProcesoIdOfMuestraListMuestra = em.merge(oldProcesoIdOfMuestraListMuestra);
                }
            }
            for (Ponderacionfactor ponderacionfactorListPonderacionfactor : proceso.getPonderacionfactorList()) {
                Proceso oldProcesoIdOfPonderacionfactorListPonderacionfactor = ponderacionfactorListPonderacionfactor.getProcesoId();
                ponderacionfactorListPonderacionfactor.setProcesoId(proceso);
                ponderacionfactorListPonderacionfactor = em.merge(ponderacionfactorListPonderacionfactor);
                if (oldProcesoIdOfPonderacionfactorListPonderacionfactor != null) {
                    oldProcesoIdOfPonderacionfactorListPonderacionfactor.getPonderacionfactorList().remove(ponderacionfactorListPonderacionfactor);
                    oldProcesoIdOfPonderacionfactorListPonderacionfactor = em.merge(oldProcesoIdOfPonderacionfactorListPonderacionfactor);
                }
            }
            for (Asignacionencuesta asignacionencuestaListAsignacionencuesta : proceso.getAsignacionencuestaList()) {
                Proceso oldProcesoIdOfAsignacionencuestaListAsignacionencuesta = asignacionencuestaListAsignacionencuesta.getProcesoId();
                asignacionencuestaListAsignacionencuesta.setProcesoId(proceso);
                asignacionencuestaListAsignacionencuesta = em.merge(asignacionencuestaListAsignacionencuesta);
                if (oldProcesoIdOfAsignacionencuestaListAsignacionencuesta != null) {
                    oldProcesoIdOfAsignacionencuestaListAsignacionencuesta.getAsignacionencuestaList().remove(asignacionencuestaListAsignacionencuesta);
                    oldProcesoIdOfAsignacionencuestaListAsignacionencuesta = em.merge(oldProcesoIdOfAsignacionencuestaListAsignacionencuesta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proceso proceso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proceso persistentProceso = em.find(Proceso.class, proceso.getId());
            Programa programaIdOld = persistentProceso.getProgramaId();
            Programa programaIdNew = proceso.getProgramaId();
            List<Ponderacioncaracteristica> ponderacioncaracteristicaListOld = persistentProceso.getPonderacioncaracteristicaList();
            List<Ponderacioncaracteristica> ponderacioncaracteristicaListNew = proceso.getPonderacioncaracteristicaList();
            List<Muestra> muestraListOld = persistentProceso.getMuestraList();
            List<Muestra> muestraListNew = proceso.getMuestraList();
            List<Ponderacionfactor> ponderacionfactorListOld = persistentProceso.getPonderacionfactorList();
            List<Ponderacionfactor> ponderacionfactorListNew = proceso.getPonderacionfactorList();
            List<Asignacionencuesta> asignacionencuestaListOld = persistentProceso.getAsignacionencuestaList();
            List<Asignacionencuesta> asignacionencuestaListNew = proceso.getAsignacionencuestaList();
            List<String> illegalOrphanMessages = null;
            for (Ponderacioncaracteristica ponderacioncaracteristicaListOldPonderacioncaracteristica : ponderacioncaracteristicaListOld) {
                if (!ponderacioncaracteristicaListNew.contains(ponderacioncaracteristicaListOldPonderacioncaracteristica)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ponderacioncaracteristica " + ponderacioncaracteristicaListOldPonderacioncaracteristica + " since its procesoId field is not nullable.");
                }
            }
            for (Muestra muestraListOldMuestra : muestraListOld) {
                if (!muestraListNew.contains(muestraListOldMuestra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Muestra " + muestraListOldMuestra + " since its procesoId field is not nullable.");
                }
            }
            for (Ponderacionfactor ponderacionfactorListOldPonderacionfactor : ponderacionfactorListOld) {
                if (!ponderacionfactorListNew.contains(ponderacionfactorListOldPonderacionfactor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ponderacionfactor " + ponderacionfactorListOldPonderacionfactor + " since its procesoId field is not nullable.");
                }
            }
            for (Asignacionencuesta asignacionencuestaListOldAsignacionencuesta : asignacionencuestaListOld) {
                if (!asignacionencuestaListNew.contains(asignacionencuestaListOldAsignacionencuesta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asignacionencuesta " + asignacionencuestaListOldAsignacionencuesta + " since its procesoId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (programaIdNew != null) {
                programaIdNew = em.getReference(programaIdNew.getClass(), programaIdNew.getId());
                proceso.setProgramaId(programaIdNew);
            }
            List<Ponderacioncaracteristica> attachedPonderacioncaracteristicaListNew = new ArrayList<Ponderacioncaracteristica>();
            for (Ponderacioncaracteristica ponderacioncaracteristicaListNewPonderacioncaracteristicaToAttach : ponderacioncaracteristicaListNew) {
                ponderacioncaracteristicaListNewPonderacioncaracteristicaToAttach = em.getReference(ponderacioncaracteristicaListNewPonderacioncaracteristicaToAttach.getClass(), ponderacioncaracteristicaListNewPonderacioncaracteristicaToAttach.getId());
                attachedPonderacioncaracteristicaListNew.add(ponderacioncaracteristicaListNewPonderacioncaracteristicaToAttach);
            }
            ponderacioncaracteristicaListNew = attachedPonderacioncaracteristicaListNew;
            proceso.setPonderacioncaracteristicaList(ponderacioncaracteristicaListNew);
            List<Muestra> attachedMuestraListNew = new ArrayList<Muestra>();
            for (Muestra muestraListNewMuestraToAttach : muestraListNew) {
                muestraListNewMuestraToAttach = em.getReference(muestraListNewMuestraToAttach.getClass(), muestraListNewMuestraToAttach.getId());
                attachedMuestraListNew.add(muestraListNewMuestraToAttach);
            }
            muestraListNew = attachedMuestraListNew;
            proceso.setMuestraList(muestraListNew);
            List<Ponderacionfactor> attachedPonderacionfactorListNew = new ArrayList<Ponderacionfactor>();
            for (Ponderacionfactor ponderacionfactorListNewPonderacionfactorToAttach : ponderacionfactorListNew) {
                ponderacionfactorListNewPonderacionfactorToAttach = em.getReference(ponderacionfactorListNewPonderacionfactorToAttach.getClass(), ponderacionfactorListNewPonderacionfactorToAttach.getId());
                attachedPonderacionfactorListNew.add(ponderacionfactorListNewPonderacionfactorToAttach);
            }
            ponderacionfactorListNew = attachedPonderacionfactorListNew;
            proceso.setPonderacionfactorList(ponderacionfactorListNew);
            List<Asignacionencuesta> attachedAsignacionencuestaListNew = new ArrayList<Asignacionencuesta>();
            for (Asignacionencuesta asignacionencuestaListNewAsignacionencuestaToAttach : asignacionencuestaListNew) {
                asignacionencuestaListNewAsignacionencuestaToAttach = em.getReference(asignacionencuestaListNewAsignacionencuestaToAttach.getClass(), asignacionencuestaListNewAsignacionencuestaToAttach.getId());
                attachedAsignacionencuestaListNew.add(asignacionencuestaListNewAsignacionencuestaToAttach);
            }
            asignacionencuestaListNew = attachedAsignacionencuestaListNew;
            proceso.setAsignacionencuestaList(asignacionencuestaListNew);
            proceso = em.merge(proceso);
            if (programaIdOld != null && !programaIdOld.equals(programaIdNew)) {
                programaIdOld.getProcesoList().remove(proceso);
                programaIdOld = em.merge(programaIdOld);
            }
            if (programaIdNew != null && !programaIdNew.equals(programaIdOld)) {
                programaIdNew.getProcesoList().add(proceso);
                programaIdNew = em.merge(programaIdNew);
            }
            for (Ponderacioncaracteristica ponderacioncaracteristicaListNewPonderacioncaracteristica : ponderacioncaracteristicaListNew) {
                if (!ponderacioncaracteristicaListOld.contains(ponderacioncaracteristicaListNewPonderacioncaracteristica)) {
                    Proceso oldProcesoIdOfPonderacioncaracteristicaListNewPonderacioncaracteristica = ponderacioncaracteristicaListNewPonderacioncaracteristica.getProcesoId();
                    ponderacioncaracteristicaListNewPonderacioncaracteristica.setProcesoId(proceso);
                    ponderacioncaracteristicaListNewPonderacioncaracteristica = em.merge(ponderacioncaracteristicaListNewPonderacioncaracteristica);
                    if (oldProcesoIdOfPonderacioncaracteristicaListNewPonderacioncaracteristica != null && !oldProcesoIdOfPonderacioncaracteristicaListNewPonderacioncaracteristica.equals(proceso)) {
                        oldProcesoIdOfPonderacioncaracteristicaListNewPonderacioncaracteristica.getPonderacioncaracteristicaList().remove(ponderacioncaracteristicaListNewPonderacioncaracteristica);
                        oldProcesoIdOfPonderacioncaracteristicaListNewPonderacioncaracteristica = em.merge(oldProcesoIdOfPonderacioncaracteristicaListNewPonderacioncaracteristica);
                    }
                }
            }
            for (Muestra muestraListNewMuestra : muestraListNew) {
                if (!muestraListOld.contains(muestraListNewMuestra)) {
                    Proceso oldProcesoIdOfMuestraListNewMuestra = muestraListNewMuestra.getProcesoId();
                    muestraListNewMuestra.setProcesoId(proceso);
                    muestraListNewMuestra = em.merge(muestraListNewMuestra);
                    if (oldProcesoIdOfMuestraListNewMuestra != null && !oldProcesoIdOfMuestraListNewMuestra.equals(proceso)) {
                        oldProcesoIdOfMuestraListNewMuestra.getMuestraList().remove(muestraListNewMuestra);
                        oldProcesoIdOfMuestraListNewMuestra = em.merge(oldProcesoIdOfMuestraListNewMuestra);
                    }
                }
            }
            for (Ponderacionfactor ponderacionfactorListNewPonderacionfactor : ponderacionfactorListNew) {
                if (!ponderacionfactorListOld.contains(ponderacionfactorListNewPonderacionfactor)) {
                    Proceso oldProcesoIdOfPonderacionfactorListNewPonderacionfactor = ponderacionfactorListNewPonderacionfactor.getProcesoId();
                    ponderacionfactorListNewPonderacionfactor.setProcesoId(proceso);
                    ponderacionfactorListNewPonderacionfactor = em.merge(ponderacionfactorListNewPonderacionfactor);
                    if (oldProcesoIdOfPonderacionfactorListNewPonderacionfactor != null && !oldProcesoIdOfPonderacionfactorListNewPonderacionfactor.equals(proceso)) {
                        oldProcesoIdOfPonderacionfactorListNewPonderacionfactor.getPonderacionfactorList().remove(ponderacionfactorListNewPonderacionfactor);
                        oldProcesoIdOfPonderacionfactorListNewPonderacionfactor = em.merge(oldProcesoIdOfPonderacionfactorListNewPonderacionfactor);
                    }
                }
            }
            for (Asignacionencuesta asignacionencuestaListNewAsignacionencuesta : asignacionencuestaListNew) {
                if (!asignacionencuestaListOld.contains(asignacionencuestaListNewAsignacionencuesta)) {
                    Proceso oldProcesoIdOfAsignacionencuestaListNewAsignacionencuesta = asignacionencuestaListNewAsignacionencuesta.getProcesoId();
                    asignacionencuestaListNewAsignacionencuesta.setProcesoId(proceso);
                    asignacionencuestaListNewAsignacionencuesta = em.merge(asignacionencuestaListNewAsignacionencuesta);
                    if (oldProcesoIdOfAsignacionencuestaListNewAsignacionencuesta != null && !oldProcesoIdOfAsignacionencuestaListNewAsignacionencuesta.equals(proceso)) {
                        oldProcesoIdOfAsignacionencuestaListNewAsignacionencuesta.getAsignacionencuestaList().remove(asignacionencuestaListNewAsignacionencuesta);
                        oldProcesoIdOfAsignacionencuestaListNewAsignacionencuesta = em.merge(oldProcesoIdOfAsignacionencuestaListNewAsignacionencuesta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proceso.getId();
                if (findProceso(id) == null) {
                    throw new NonexistentEntityException("The proceso with id " + id + " no longer exists.");
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
            Proceso proceso;
            try {
                proceso = em.getReference(Proceso.class, id);
                proceso.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proceso with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ponderacioncaracteristica> ponderacioncaracteristicaListOrphanCheck = proceso.getPonderacioncaracteristicaList();
            for (Ponderacioncaracteristica ponderacioncaracteristicaListOrphanCheckPonderacioncaracteristica : ponderacioncaracteristicaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proceso (" + proceso + ") cannot be destroyed since the Ponderacioncaracteristica " + ponderacioncaracteristicaListOrphanCheckPonderacioncaracteristica + " in its ponderacioncaracteristicaList field has a non-nullable procesoId field.");
            }
            List<Muestra> muestraListOrphanCheck = proceso.getMuestraList();
            for (Muestra muestraListOrphanCheckMuestra : muestraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proceso (" + proceso + ") cannot be destroyed since the Muestra " + muestraListOrphanCheckMuestra + " in its muestraList field has a non-nullable procesoId field.");
            }
            List<Ponderacionfactor> ponderacionfactorListOrphanCheck = proceso.getPonderacionfactorList();
            for (Ponderacionfactor ponderacionfactorListOrphanCheckPonderacionfactor : ponderacionfactorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proceso (" + proceso + ") cannot be destroyed since the Ponderacionfactor " + ponderacionfactorListOrphanCheckPonderacionfactor + " in its ponderacionfactorList field has a non-nullable procesoId field.");
            }
            List<Asignacionencuesta> asignacionencuestaListOrphanCheck = proceso.getAsignacionencuestaList();
            for (Asignacionencuesta asignacionencuestaListOrphanCheckAsignacionencuesta : asignacionencuestaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proceso (" + proceso + ") cannot be destroyed since the Asignacionencuesta " + asignacionencuestaListOrphanCheckAsignacionencuesta + " in its asignacionencuestaList field has a non-nullable procesoId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Programa programaId = proceso.getProgramaId();
            if (programaId != null) {
                programaId.getProcesoList().remove(proceso);
                programaId = em.merge(programaId);
            }
            em.remove(proceso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proceso> findProcesoEntities() {
        return findProcesoEntities(true, -1, -1);
    }

    public List<Proceso> findProcesoEntities(int maxResults, int firstResult) {
        return findProcesoEntities(false, maxResults, firstResult);
    }

    private List<Proceso> findProcesoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proceso.class));
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

    public Proceso findProceso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proceso.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proceso> rt = cq.from(Proceso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
