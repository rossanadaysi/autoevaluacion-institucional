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
public class MuestraJpaController implements Serializable {

    public MuestraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Muestra muestra) {
        if (muestra.getMuestradocenteList() == null) {
            muestra.setMuestradocenteList(new ArrayList<Muestradocente>());
        }
        if (muestra.getMuestraadministrativoList() == null) {
            muestra.setMuestraadministrativoList(new ArrayList<Muestraadministrativo>());
        }
        if (muestra.getMuestradirectorList() == null) {
            muestra.setMuestradirectorList(new ArrayList<Muestradirector>());
        }
        if (muestra.getMuestraegresadoList() == null) {
            muestra.setMuestraegresadoList(new ArrayList<Muestraegresado>());
        }
        if (muestra.getMuestraagenciaList() == null) {
            muestra.setMuestraagenciaList(new ArrayList<Muestraagencia>());
        }
        if (muestra.getMuestraestudianteList() == null) {
            muestra.setMuestraestudianteList(new ArrayList<Muestraestudiante>());
        }
        if (muestra.getMuestraempleadorList() == null) {
            muestra.setMuestraempleadorList(new ArrayList<Muestraempleador>());
        }
        if (muestra.getMuestracriterioList() == null) {
            muestra.setMuestracriterioList(new ArrayList<Muestracriterio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proceso procesoId = muestra.getProcesoId();
            if (procesoId != null) {
                procesoId = em.getReference(procesoId.getClass(), procesoId.getId());
                muestra.setProcesoId(procesoId);
            }
            List<Muestradocente> attachedMuestradocenteList = new ArrayList<Muestradocente>();
            for (Muestradocente muestradocenteListMuestradocenteToAttach : muestra.getMuestradocenteList()) {
                muestradocenteListMuestradocenteToAttach = em.getReference(muestradocenteListMuestradocenteToAttach.getClass(), muestradocenteListMuestradocenteToAttach.getId());
                attachedMuestradocenteList.add(muestradocenteListMuestradocenteToAttach);
            }
            muestra.setMuestradocenteList(attachedMuestradocenteList);
            List<Muestraadministrativo> attachedMuestraadministrativoList = new ArrayList<Muestraadministrativo>();
            for (Muestraadministrativo muestraadministrativoListMuestraadministrativoToAttach : muestra.getMuestraadministrativoList()) {
                muestraadministrativoListMuestraadministrativoToAttach = em.getReference(muestraadministrativoListMuestraadministrativoToAttach.getClass(), muestraadministrativoListMuestraadministrativoToAttach.getId());
                attachedMuestraadministrativoList.add(muestraadministrativoListMuestraadministrativoToAttach);
            }
            muestra.setMuestraadministrativoList(attachedMuestraadministrativoList);
            List<Muestradirector> attachedMuestradirectorList = new ArrayList<Muestradirector>();
            for (Muestradirector muestradirectorListMuestradirectorToAttach : muestra.getMuestradirectorList()) {
                muestradirectorListMuestradirectorToAttach = em.getReference(muestradirectorListMuestradirectorToAttach.getClass(), muestradirectorListMuestradirectorToAttach.getId());
                attachedMuestradirectorList.add(muestradirectorListMuestradirectorToAttach);
            }
            muestra.setMuestradirectorList(attachedMuestradirectorList);
            List<Muestraegresado> attachedMuestraegresadoList = new ArrayList<Muestraegresado>();
            for (Muestraegresado muestraegresadoListMuestraegresadoToAttach : muestra.getMuestraegresadoList()) {
                muestraegresadoListMuestraegresadoToAttach = em.getReference(muestraegresadoListMuestraegresadoToAttach.getClass(), muestraegresadoListMuestraegresadoToAttach.getId());
                attachedMuestraegresadoList.add(muestraegresadoListMuestraegresadoToAttach);
            }
            muestra.setMuestraegresadoList(attachedMuestraegresadoList);
            List<Muestraagencia> attachedMuestraagenciaList = new ArrayList<Muestraagencia>();
            for (Muestraagencia muestraagenciaListMuestraagenciaToAttach : muestra.getMuestraagenciaList()) {
                muestraagenciaListMuestraagenciaToAttach = em.getReference(muestraagenciaListMuestraagenciaToAttach.getClass(), muestraagenciaListMuestraagenciaToAttach.getId());
                attachedMuestraagenciaList.add(muestraagenciaListMuestraagenciaToAttach);
            }
            muestra.setMuestraagenciaList(attachedMuestraagenciaList);
            List<Muestraestudiante> attachedMuestraestudianteList = new ArrayList<Muestraestudiante>();
            for (Muestraestudiante muestraestudianteListMuestraestudianteToAttach : muestra.getMuestraestudianteList()) {
                muestraestudianteListMuestraestudianteToAttach = em.getReference(muestraestudianteListMuestraestudianteToAttach.getClass(), muestraestudianteListMuestraestudianteToAttach.getId());
                attachedMuestraestudianteList.add(muestraestudianteListMuestraestudianteToAttach);
            }
            muestra.setMuestraestudianteList(attachedMuestraestudianteList);
            List<Muestraempleador> attachedMuestraempleadorList = new ArrayList<Muestraempleador>();
            for (Muestraempleador muestraempleadorListMuestraempleadorToAttach : muestra.getMuestraempleadorList()) {
                muestraempleadorListMuestraempleadorToAttach = em.getReference(muestraempleadorListMuestraempleadorToAttach.getClass(), muestraempleadorListMuestraempleadorToAttach.getId());
                attachedMuestraempleadorList.add(muestraempleadorListMuestraempleadorToAttach);
            }
            muestra.setMuestraempleadorList(attachedMuestraempleadorList);
            List<Muestracriterio> attachedMuestracriterioList = new ArrayList<Muestracriterio>();
            for (Muestracriterio muestracriterioListMuestracriterioToAttach : muestra.getMuestracriterioList()) {
                muestracriterioListMuestracriterioToAttach = em.getReference(muestracriterioListMuestracriterioToAttach.getClass(), muestracriterioListMuestracriterioToAttach.getId());
                attachedMuestracriterioList.add(muestracriterioListMuestracriterioToAttach);
            }
            muestra.setMuestracriterioList(attachedMuestracriterioList);
            em.persist(muestra);
            if (procesoId != null) {
                procesoId.getMuestraList().add(muestra);
                procesoId = em.merge(procesoId);
            }
            for (Muestradocente muestradocenteListMuestradocente : muestra.getMuestradocenteList()) {
                Muestra oldMuestraIdOfMuestradocenteListMuestradocente = muestradocenteListMuestradocente.getMuestraId();
                muestradocenteListMuestradocente.setMuestraId(muestra);
                muestradocenteListMuestradocente = em.merge(muestradocenteListMuestradocente);
                if (oldMuestraIdOfMuestradocenteListMuestradocente != null) {
                    oldMuestraIdOfMuestradocenteListMuestradocente.getMuestradocenteList().remove(muestradocenteListMuestradocente);
                    oldMuestraIdOfMuestradocenteListMuestradocente = em.merge(oldMuestraIdOfMuestradocenteListMuestradocente);
                }
            }
            for (Muestraadministrativo muestraadministrativoListMuestraadministrativo : muestra.getMuestraadministrativoList()) {
                Muestra oldMuestraIdOfMuestraadministrativoListMuestraadministrativo = muestraadministrativoListMuestraadministrativo.getMuestraId();
                muestraadministrativoListMuestraadministrativo.setMuestraId(muestra);
                muestraadministrativoListMuestraadministrativo = em.merge(muestraadministrativoListMuestraadministrativo);
                if (oldMuestraIdOfMuestraadministrativoListMuestraadministrativo != null) {
                    oldMuestraIdOfMuestraadministrativoListMuestraadministrativo.getMuestraadministrativoList().remove(muestraadministrativoListMuestraadministrativo);
                    oldMuestraIdOfMuestraadministrativoListMuestraadministrativo = em.merge(oldMuestraIdOfMuestraadministrativoListMuestraadministrativo);
                }
            }
            for (Muestradirector muestradirectorListMuestradirector : muestra.getMuestradirectorList()) {
                Muestra oldMuestraIdOfMuestradirectorListMuestradirector = muestradirectorListMuestradirector.getMuestraId();
                muestradirectorListMuestradirector.setMuestraId(muestra);
                muestradirectorListMuestradirector = em.merge(muestradirectorListMuestradirector);
                if (oldMuestraIdOfMuestradirectorListMuestradirector != null) {
                    oldMuestraIdOfMuestradirectorListMuestradirector.getMuestradirectorList().remove(muestradirectorListMuestradirector);
                    oldMuestraIdOfMuestradirectorListMuestradirector = em.merge(oldMuestraIdOfMuestradirectorListMuestradirector);
                }
            }
            for (Muestraegresado muestraegresadoListMuestraegresado : muestra.getMuestraegresadoList()) {
                Muestra oldMuestraIdOfMuestraegresadoListMuestraegresado = muestraegresadoListMuestraegresado.getMuestraId();
                muestraegresadoListMuestraegresado.setMuestraId(muestra);
                muestraegresadoListMuestraegresado = em.merge(muestraegresadoListMuestraegresado);
                if (oldMuestraIdOfMuestraegresadoListMuestraegresado != null) {
                    oldMuestraIdOfMuestraegresadoListMuestraegresado.getMuestraegresadoList().remove(muestraegresadoListMuestraegresado);
                    oldMuestraIdOfMuestraegresadoListMuestraegresado = em.merge(oldMuestraIdOfMuestraegresadoListMuestraegresado);
                }
            }
            for (Muestraagencia muestraagenciaListMuestraagencia : muestra.getMuestraagenciaList()) {
                Muestra oldMuestraIdOfMuestraagenciaListMuestraagencia = muestraagenciaListMuestraagencia.getMuestraId();
                muestraagenciaListMuestraagencia.setMuestraId(muestra);
                muestraagenciaListMuestraagencia = em.merge(muestraagenciaListMuestraagencia);
                if (oldMuestraIdOfMuestraagenciaListMuestraagencia != null) {
                    oldMuestraIdOfMuestraagenciaListMuestraagencia.getMuestraagenciaList().remove(muestraagenciaListMuestraagencia);
                    oldMuestraIdOfMuestraagenciaListMuestraagencia = em.merge(oldMuestraIdOfMuestraagenciaListMuestraagencia);
                }
            }
            for (Muestraestudiante muestraestudianteListMuestraestudiante : muestra.getMuestraestudianteList()) {
                Muestra oldMuestraIdOfMuestraestudianteListMuestraestudiante = muestraestudianteListMuestraestudiante.getMuestraId();
                muestraestudianteListMuestraestudiante.setMuestraId(muestra);
                muestraestudianteListMuestraestudiante = em.merge(muestraestudianteListMuestraestudiante);
                if (oldMuestraIdOfMuestraestudianteListMuestraestudiante != null) {
                    oldMuestraIdOfMuestraestudianteListMuestraestudiante.getMuestraestudianteList().remove(muestraestudianteListMuestraestudiante);
                    oldMuestraIdOfMuestraestudianteListMuestraestudiante = em.merge(oldMuestraIdOfMuestraestudianteListMuestraestudiante);
                }
            }
            for (Muestraempleador muestraempleadorListMuestraempleador : muestra.getMuestraempleadorList()) {
                Muestra oldMuestraIdOfMuestraempleadorListMuestraempleador = muestraempleadorListMuestraempleador.getMuestraId();
                muestraempleadorListMuestraempleador.setMuestraId(muestra);
                muestraempleadorListMuestraempleador = em.merge(muestraempleadorListMuestraempleador);
                if (oldMuestraIdOfMuestraempleadorListMuestraempleador != null) {
                    oldMuestraIdOfMuestraempleadorListMuestraempleador.getMuestraempleadorList().remove(muestraempleadorListMuestraempleador);
                    oldMuestraIdOfMuestraempleadorListMuestraempleador = em.merge(oldMuestraIdOfMuestraempleadorListMuestraempleador);
                }
            }
            for (Muestracriterio muestracriterioListMuestracriterio : muestra.getMuestracriterioList()) {
                Muestra oldMuestraIdOfMuestracriterioListMuestracriterio = muestracriterioListMuestracriterio.getMuestraId();
                muestracriterioListMuestracriterio.setMuestraId(muestra);
                muestracriterioListMuestracriterio = em.merge(muestracriterioListMuestracriterio);
                if (oldMuestraIdOfMuestracriterioListMuestracriterio != null) {
                    oldMuestraIdOfMuestracriterioListMuestracriterio.getMuestracriterioList().remove(muestracriterioListMuestracriterio);
                    oldMuestraIdOfMuestracriterioListMuestracriterio = em.merge(oldMuestraIdOfMuestracriterioListMuestracriterio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Muestra muestra) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Muestra persistentMuestra = em.find(Muestra.class, muestra.getId());
            Proceso procesoIdOld = persistentMuestra.getProcesoId();
            Proceso procesoIdNew = muestra.getProcesoId();
            List<Muestradocente> muestradocenteListOld = persistentMuestra.getMuestradocenteList();
            List<Muestradocente> muestradocenteListNew = muestra.getMuestradocenteList();
            List<Muestraadministrativo> muestraadministrativoListOld = persistentMuestra.getMuestraadministrativoList();
            List<Muestraadministrativo> muestraadministrativoListNew = muestra.getMuestraadministrativoList();
            List<Muestradirector> muestradirectorListOld = persistentMuestra.getMuestradirectorList();
            List<Muestradirector> muestradirectorListNew = muestra.getMuestradirectorList();
            List<Muestraegresado> muestraegresadoListOld = persistentMuestra.getMuestraegresadoList();
            List<Muestraegresado> muestraegresadoListNew = muestra.getMuestraegresadoList();
            List<Muestraagencia> muestraagenciaListOld = persistentMuestra.getMuestraagenciaList();
            List<Muestraagencia> muestraagenciaListNew = muestra.getMuestraagenciaList();
            List<Muestraestudiante> muestraestudianteListOld = persistentMuestra.getMuestraestudianteList();
            List<Muestraestudiante> muestraestudianteListNew = muestra.getMuestraestudianteList();
            List<Muestraempleador> muestraempleadorListOld = persistentMuestra.getMuestraempleadorList();
            List<Muestraempleador> muestraempleadorListNew = muestra.getMuestraempleadorList();
            List<Muestracriterio> muestracriterioListOld = persistentMuestra.getMuestracriterioList();
            List<Muestracriterio> muestracriterioListNew = muestra.getMuestracriterioList();
            List<String> illegalOrphanMessages = null;
            for (Muestradocente muestradocenteListOldMuestradocente : muestradocenteListOld) {
                if (!muestradocenteListNew.contains(muestradocenteListOldMuestradocente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Muestradocente " + muestradocenteListOldMuestradocente + " since its muestraId field is not nullable.");
                }
            }
            for (Muestraadministrativo muestraadministrativoListOldMuestraadministrativo : muestraadministrativoListOld) {
                if (!muestraadministrativoListNew.contains(muestraadministrativoListOldMuestraadministrativo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Muestraadministrativo " + muestraadministrativoListOldMuestraadministrativo + " since its muestraId field is not nullable.");
                }
            }
            for (Muestradirector muestradirectorListOldMuestradirector : muestradirectorListOld) {
                if (!muestradirectorListNew.contains(muestradirectorListOldMuestradirector)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Muestradirector " + muestradirectorListOldMuestradirector + " since its muestraId field is not nullable.");
                }
            }
            for (Muestraegresado muestraegresadoListOldMuestraegresado : muestraegresadoListOld) {
                if (!muestraegresadoListNew.contains(muestraegresadoListOldMuestraegresado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Muestraegresado " + muestraegresadoListOldMuestraegresado + " since its muestraId field is not nullable.");
                }
            }
            for (Muestraagencia muestraagenciaListOldMuestraagencia : muestraagenciaListOld) {
                if (!muestraagenciaListNew.contains(muestraagenciaListOldMuestraagencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Muestraagencia " + muestraagenciaListOldMuestraagencia + " since its muestraId field is not nullable.");
                }
            }
            for (Muestraestudiante muestraestudianteListOldMuestraestudiante : muestraestudianteListOld) {
                if (!muestraestudianteListNew.contains(muestraestudianteListOldMuestraestudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Muestraestudiante " + muestraestudianteListOldMuestraestudiante + " since its muestraId field is not nullable.");
                }
            }
            for (Muestraempleador muestraempleadorListOldMuestraempleador : muestraempleadorListOld) {
                if (!muestraempleadorListNew.contains(muestraempleadorListOldMuestraempleador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Muestraempleador " + muestraempleadorListOldMuestraempleador + " since its muestraId field is not nullable.");
                }
            }
            for (Muestracriterio muestracriterioListOldMuestracriterio : muestracriterioListOld) {
                if (!muestracriterioListNew.contains(muestracriterioListOldMuestracriterio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Muestracriterio " + muestracriterioListOldMuestracriterio + " since its muestraId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (procesoIdNew != null) {
                procesoIdNew = em.getReference(procesoIdNew.getClass(), procesoIdNew.getId());
                muestra.setProcesoId(procesoIdNew);
            }
            List<Muestradocente> attachedMuestradocenteListNew = new ArrayList<Muestradocente>();
            for (Muestradocente muestradocenteListNewMuestradocenteToAttach : muestradocenteListNew) {
                muestradocenteListNewMuestradocenteToAttach = em.getReference(muestradocenteListNewMuestradocenteToAttach.getClass(), muestradocenteListNewMuestradocenteToAttach.getId());
                attachedMuestradocenteListNew.add(muestradocenteListNewMuestradocenteToAttach);
            }
            muestradocenteListNew = attachedMuestradocenteListNew;
            muestra.setMuestradocenteList(muestradocenteListNew);
            List<Muestraadministrativo> attachedMuestraadministrativoListNew = new ArrayList<Muestraadministrativo>();
            for (Muestraadministrativo muestraadministrativoListNewMuestraadministrativoToAttach : muestraadministrativoListNew) {
                muestraadministrativoListNewMuestraadministrativoToAttach = em.getReference(muestraadministrativoListNewMuestraadministrativoToAttach.getClass(), muestraadministrativoListNewMuestraadministrativoToAttach.getId());
                attachedMuestraadministrativoListNew.add(muestraadministrativoListNewMuestraadministrativoToAttach);
            }
            muestraadministrativoListNew = attachedMuestraadministrativoListNew;
            muestra.setMuestraadministrativoList(muestraadministrativoListNew);
            List<Muestradirector> attachedMuestradirectorListNew = new ArrayList<Muestradirector>();
            for (Muestradirector muestradirectorListNewMuestradirectorToAttach : muestradirectorListNew) {
                muestradirectorListNewMuestradirectorToAttach = em.getReference(muestradirectorListNewMuestradirectorToAttach.getClass(), muestradirectorListNewMuestradirectorToAttach.getId());
                attachedMuestradirectorListNew.add(muestradirectorListNewMuestradirectorToAttach);
            }
            muestradirectorListNew = attachedMuestradirectorListNew;
            muestra.setMuestradirectorList(muestradirectorListNew);
            List<Muestraegresado> attachedMuestraegresadoListNew = new ArrayList<Muestraegresado>();
            for (Muestraegresado muestraegresadoListNewMuestraegresadoToAttach : muestraegresadoListNew) {
                muestraegresadoListNewMuestraegresadoToAttach = em.getReference(muestraegresadoListNewMuestraegresadoToAttach.getClass(), muestraegresadoListNewMuestraegresadoToAttach.getId());
                attachedMuestraegresadoListNew.add(muestraegresadoListNewMuestraegresadoToAttach);
            }
            muestraegresadoListNew = attachedMuestraegresadoListNew;
            muestra.setMuestraegresadoList(muestraegresadoListNew);
            List<Muestraagencia> attachedMuestraagenciaListNew = new ArrayList<Muestraagencia>();
            for (Muestraagencia muestraagenciaListNewMuestraagenciaToAttach : muestraagenciaListNew) {
                muestraagenciaListNewMuestraagenciaToAttach = em.getReference(muestraagenciaListNewMuestraagenciaToAttach.getClass(), muestraagenciaListNewMuestraagenciaToAttach.getId());
                attachedMuestraagenciaListNew.add(muestraagenciaListNewMuestraagenciaToAttach);
            }
            muestraagenciaListNew = attachedMuestraagenciaListNew;
            muestra.setMuestraagenciaList(muestraagenciaListNew);
            List<Muestraestudiante> attachedMuestraestudianteListNew = new ArrayList<Muestraestudiante>();
            for (Muestraestudiante muestraestudianteListNewMuestraestudianteToAttach : muestraestudianteListNew) {
                muestraestudianteListNewMuestraestudianteToAttach = em.getReference(muestraestudianteListNewMuestraestudianteToAttach.getClass(), muestraestudianteListNewMuestraestudianteToAttach.getId());
                attachedMuestraestudianteListNew.add(muestraestudianteListNewMuestraestudianteToAttach);
            }
            muestraestudianteListNew = attachedMuestraestudianteListNew;
            muestra.setMuestraestudianteList(muestraestudianteListNew);
            List<Muestraempleador> attachedMuestraempleadorListNew = new ArrayList<Muestraempleador>();
            for (Muestraempleador muestraempleadorListNewMuestraempleadorToAttach : muestraempleadorListNew) {
                muestraempleadorListNewMuestraempleadorToAttach = em.getReference(muestraempleadorListNewMuestraempleadorToAttach.getClass(), muestraempleadorListNewMuestraempleadorToAttach.getId());
                attachedMuestraempleadorListNew.add(muestraempleadorListNewMuestraempleadorToAttach);
            }
            muestraempleadorListNew = attachedMuestraempleadorListNew;
            muestra.setMuestraempleadorList(muestraempleadorListNew);
            List<Muestracriterio> attachedMuestracriterioListNew = new ArrayList<Muestracriterio>();
            for (Muestracriterio muestracriterioListNewMuestracriterioToAttach : muestracriterioListNew) {
                muestracriterioListNewMuestracriterioToAttach = em.getReference(muestracriterioListNewMuestracriterioToAttach.getClass(), muestracriterioListNewMuestracriterioToAttach.getId());
                attachedMuestracriterioListNew.add(muestracriterioListNewMuestracriterioToAttach);
            }
            muestracriterioListNew = attachedMuestracriterioListNew;
            muestra.setMuestracriterioList(muestracriterioListNew);
            muestra = em.merge(muestra);
            if (procesoIdOld != null && !procesoIdOld.equals(procesoIdNew)) {
                procesoIdOld.getMuestraList().remove(muestra);
                procesoIdOld = em.merge(procesoIdOld);
            }
            if (procesoIdNew != null && !procesoIdNew.equals(procesoIdOld)) {
                procesoIdNew.getMuestraList().add(muestra);
                procesoIdNew = em.merge(procesoIdNew);
            }
            for (Muestradocente muestradocenteListNewMuestradocente : muestradocenteListNew) {
                if (!muestradocenteListOld.contains(muestradocenteListNewMuestradocente)) {
                    Muestra oldMuestraIdOfMuestradocenteListNewMuestradocente = muestradocenteListNewMuestradocente.getMuestraId();
                    muestradocenteListNewMuestradocente.setMuestraId(muestra);
                    muestradocenteListNewMuestradocente = em.merge(muestradocenteListNewMuestradocente);
                    if (oldMuestraIdOfMuestradocenteListNewMuestradocente != null && !oldMuestraIdOfMuestradocenteListNewMuestradocente.equals(muestra)) {
                        oldMuestraIdOfMuestradocenteListNewMuestradocente.getMuestradocenteList().remove(muestradocenteListNewMuestradocente);
                        oldMuestraIdOfMuestradocenteListNewMuestradocente = em.merge(oldMuestraIdOfMuestradocenteListNewMuestradocente);
                    }
                }
            }
            for (Muestraadministrativo muestraadministrativoListNewMuestraadministrativo : muestraadministrativoListNew) {
                if (!muestraadministrativoListOld.contains(muestraadministrativoListNewMuestraadministrativo)) {
                    Muestra oldMuestraIdOfMuestraadministrativoListNewMuestraadministrativo = muestraadministrativoListNewMuestraadministrativo.getMuestraId();
                    muestraadministrativoListNewMuestraadministrativo.setMuestraId(muestra);
                    muestraadministrativoListNewMuestraadministrativo = em.merge(muestraadministrativoListNewMuestraadministrativo);
                    if (oldMuestraIdOfMuestraadministrativoListNewMuestraadministrativo != null && !oldMuestraIdOfMuestraadministrativoListNewMuestraadministrativo.equals(muestra)) {
                        oldMuestraIdOfMuestraadministrativoListNewMuestraadministrativo.getMuestraadministrativoList().remove(muestraadministrativoListNewMuestraadministrativo);
                        oldMuestraIdOfMuestraadministrativoListNewMuestraadministrativo = em.merge(oldMuestraIdOfMuestraadministrativoListNewMuestraadministrativo);
                    }
                }
            }
            for (Muestradirector muestradirectorListNewMuestradirector : muestradirectorListNew) {
                if (!muestradirectorListOld.contains(muestradirectorListNewMuestradirector)) {
                    Muestra oldMuestraIdOfMuestradirectorListNewMuestradirector = muestradirectorListNewMuestradirector.getMuestraId();
                    muestradirectorListNewMuestradirector.setMuestraId(muestra);
                    muestradirectorListNewMuestradirector = em.merge(muestradirectorListNewMuestradirector);
                    if (oldMuestraIdOfMuestradirectorListNewMuestradirector != null && !oldMuestraIdOfMuestradirectorListNewMuestradirector.equals(muestra)) {
                        oldMuestraIdOfMuestradirectorListNewMuestradirector.getMuestradirectorList().remove(muestradirectorListNewMuestradirector);
                        oldMuestraIdOfMuestradirectorListNewMuestradirector = em.merge(oldMuestraIdOfMuestradirectorListNewMuestradirector);
                    }
                }
            }
            for (Muestraegresado muestraegresadoListNewMuestraegresado : muestraegresadoListNew) {
                if (!muestraegresadoListOld.contains(muestraegresadoListNewMuestraegresado)) {
                    Muestra oldMuestraIdOfMuestraegresadoListNewMuestraegresado = muestraegresadoListNewMuestraegresado.getMuestraId();
                    muestraegresadoListNewMuestraegresado.setMuestraId(muestra);
                    muestraegresadoListNewMuestraegresado = em.merge(muestraegresadoListNewMuestraegresado);
                    if (oldMuestraIdOfMuestraegresadoListNewMuestraegresado != null && !oldMuestraIdOfMuestraegresadoListNewMuestraegresado.equals(muestra)) {
                        oldMuestraIdOfMuestraegresadoListNewMuestraegresado.getMuestraegresadoList().remove(muestraegresadoListNewMuestraegresado);
                        oldMuestraIdOfMuestraegresadoListNewMuestraegresado = em.merge(oldMuestraIdOfMuestraegresadoListNewMuestraegresado);
                    }
                }
            }
            for (Muestraagencia muestraagenciaListNewMuestraagencia : muestraagenciaListNew) {
                if (!muestraagenciaListOld.contains(muestraagenciaListNewMuestraagencia)) {
                    Muestra oldMuestraIdOfMuestraagenciaListNewMuestraagencia = muestraagenciaListNewMuestraagencia.getMuestraId();
                    muestraagenciaListNewMuestraagencia.setMuestraId(muestra);
                    muestraagenciaListNewMuestraagencia = em.merge(muestraagenciaListNewMuestraagencia);
                    if (oldMuestraIdOfMuestraagenciaListNewMuestraagencia != null && !oldMuestraIdOfMuestraagenciaListNewMuestraagencia.equals(muestra)) {
                        oldMuestraIdOfMuestraagenciaListNewMuestraagencia.getMuestraagenciaList().remove(muestraagenciaListNewMuestraagencia);
                        oldMuestraIdOfMuestraagenciaListNewMuestraagencia = em.merge(oldMuestraIdOfMuestraagenciaListNewMuestraagencia);
                    }
                }
            }
            for (Muestraestudiante muestraestudianteListNewMuestraestudiante : muestraestudianteListNew) {
                if (!muestraestudianteListOld.contains(muestraestudianteListNewMuestraestudiante)) {
                    Muestra oldMuestraIdOfMuestraestudianteListNewMuestraestudiante = muestraestudianteListNewMuestraestudiante.getMuestraId();
                    muestraestudianteListNewMuestraestudiante.setMuestraId(muestra);
                    muestraestudianteListNewMuestraestudiante = em.merge(muestraestudianteListNewMuestraestudiante);
                    if (oldMuestraIdOfMuestraestudianteListNewMuestraestudiante != null && !oldMuestraIdOfMuestraestudianteListNewMuestraestudiante.equals(muestra)) {
                        oldMuestraIdOfMuestraestudianteListNewMuestraestudiante.getMuestraestudianteList().remove(muestraestudianteListNewMuestraestudiante);
                        oldMuestraIdOfMuestraestudianteListNewMuestraestudiante = em.merge(oldMuestraIdOfMuestraestudianteListNewMuestraestudiante);
                    }
                }
            }
            for (Muestraempleador muestraempleadorListNewMuestraempleador : muestraempleadorListNew) {
                if (!muestraempleadorListOld.contains(muestraempleadorListNewMuestraempleador)) {
                    Muestra oldMuestraIdOfMuestraempleadorListNewMuestraempleador = muestraempleadorListNewMuestraempleador.getMuestraId();
                    muestraempleadorListNewMuestraempleador.setMuestraId(muestra);
                    muestraempleadorListNewMuestraempleador = em.merge(muestraempleadorListNewMuestraempleador);
                    if (oldMuestraIdOfMuestraempleadorListNewMuestraempleador != null && !oldMuestraIdOfMuestraempleadorListNewMuestraempleador.equals(muestra)) {
                        oldMuestraIdOfMuestraempleadorListNewMuestraempleador.getMuestraempleadorList().remove(muestraempleadorListNewMuestraempleador);
                        oldMuestraIdOfMuestraempleadorListNewMuestraempleador = em.merge(oldMuestraIdOfMuestraempleadorListNewMuestraempleador);
                    }
                }
            }
            for (Muestracriterio muestracriterioListNewMuestracriterio : muestracriterioListNew) {
                if (!muestracriterioListOld.contains(muestracriterioListNewMuestracriterio)) {
                    Muestra oldMuestraIdOfMuestracriterioListNewMuestracriterio = muestracriterioListNewMuestracriterio.getMuestraId();
                    muestracriterioListNewMuestracriterio.setMuestraId(muestra);
                    muestracriterioListNewMuestracriterio = em.merge(muestracriterioListNewMuestracriterio);
                    if (oldMuestraIdOfMuestracriterioListNewMuestracriterio != null && !oldMuestraIdOfMuestracriterioListNewMuestracriterio.equals(muestra)) {
                        oldMuestraIdOfMuestracriterioListNewMuestracriterio.getMuestracriterioList().remove(muestracriterioListNewMuestracriterio);
                        oldMuestraIdOfMuestracriterioListNewMuestracriterio = em.merge(oldMuestraIdOfMuestracriterioListNewMuestracriterio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = muestra.getId();
                if (findMuestra(id) == null) {
                    throw new NonexistentEntityException("The muestra with id " + id + " no longer exists.");
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
            Muestra muestra;
            try {
                muestra = em.getReference(Muestra.class, id);
                muestra.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The muestra with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Muestradocente> muestradocenteListOrphanCheck = muestra.getMuestradocenteList();
            for (Muestradocente muestradocenteListOrphanCheckMuestradocente : muestradocenteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Muestra (" + muestra + ") cannot be destroyed since the Muestradocente " + muestradocenteListOrphanCheckMuestradocente + " in its muestradocenteList field has a non-nullable muestraId field.");
            }
            List<Muestraadministrativo> muestraadministrativoListOrphanCheck = muestra.getMuestraadministrativoList();
            for (Muestraadministrativo muestraadministrativoListOrphanCheckMuestraadministrativo : muestraadministrativoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Muestra (" + muestra + ") cannot be destroyed since the Muestraadministrativo " + muestraadministrativoListOrphanCheckMuestraadministrativo + " in its muestraadministrativoList field has a non-nullable muestraId field.");
            }
            List<Muestradirector> muestradirectorListOrphanCheck = muestra.getMuestradirectorList();
            for (Muestradirector muestradirectorListOrphanCheckMuestradirector : muestradirectorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Muestra (" + muestra + ") cannot be destroyed since the Muestradirector " + muestradirectorListOrphanCheckMuestradirector + " in its muestradirectorList field has a non-nullable muestraId field.");
            }
            List<Muestraegresado> muestraegresadoListOrphanCheck = muestra.getMuestraegresadoList();
            for (Muestraegresado muestraegresadoListOrphanCheckMuestraegresado : muestraegresadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Muestra (" + muestra + ") cannot be destroyed since the Muestraegresado " + muestraegresadoListOrphanCheckMuestraegresado + " in its muestraegresadoList field has a non-nullable muestraId field.");
            }
            List<Muestraagencia> muestraagenciaListOrphanCheck = muestra.getMuestraagenciaList();
            for (Muestraagencia muestraagenciaListOrphanCheckMuestraagencia : muestraagenciaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Muestra (" + muestra + ") cannot be destroyed since the Muestraagencia " + muestraagenciaListOrphanCheckMuestraagencia + " in its muestraagenciaList field has a non-nullable muestraId field.");
            }
            List<Muestraestudiante> muestraestudianteListOrphanCheck = muestra.getMuestraestudianteList();
            for (Muestraestudiante muestraestudianteListOrphanCheckMuestraestudiante : muestraestudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Muestra (" + muestra + ") cannot be destroyed since the Muestraestudiante " + muestraestudianteListOrphanCheckMuestraestudiante + " in its muestraestudianteList field has a non-nullable muestraId field.");
            }
            List<Muestraempleador> muestraempleadorListOrphanCheck = muestra.getMuestraempleadorList();
            for (Muestraempleador muestraempleadorListOrphanCheckMuestraempleador : muestraempleadorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Muestra (" + muestra + ") cannot be destroyed since the Muestraempleador " + muestraempleadorListOrphanCheckMuestraempleador + " in its muestraempleadorList field has a non-nullable muestraId field.");
            }
            List<Muestracriterio> muestracriterioListOrphanCheck = muestra.getMuestracriterioList();
            for (Muestracriterio muestracriterioListOrphanCheckMuestracriterio : muestracriterioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Muestra (" + muestra + ") cannot be destroyed since the Muestracriterio " + muestracriterioListOrphanCheckMuestracriterio + " in its muestracriterioList field has a non-nullable muestraId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Proceso procesoId = muestra.getProcesoId();
            if (procesoId != null) {
                procesoId.getMuestraList().remove(muestra);
                procesoId = em.merge(procesoId);
            }
            em.remove(muestra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Muestra> findMuestraEntities() {
        return findMuestraEntities(true, -1, -1);
    }

    public List<Muestra> findMuestraEntities(int maxResults, int firstResult) {
        return findMuestraEntities(false, maxResults, firstResult);
    }

    private List<Muestra> findMuestraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Muestra.class));
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

    public Muestra findMuestra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Muestra.class, id);
        } finally {
            em.close();
        }
    }

    public int getMuestraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Muestra> rt = cq.from(Muestra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
