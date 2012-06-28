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
public class FuenteJpaController implements Serializable {

    public FuenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fuente fuente) {
        if (fuente.getAdministrativoList() == null) {
            fuente.setAdministrativoList(new ArrayList<Administrativo>());
        }
        if (fuente.getEstudianteList() == null) {
            fuente.setEstudianteList(new ArrayList<Estudiante>());
        }
        if (fuente.getDirectorprogramaList() == null) {
            fuente.setDirectorprogramaList(new ArrayList<Directorprograma>());
        }
        if (fuente.getAsignacionencuestaList() == null) {
            fuente.setAsignacionencuestaList(new ArrayList<Asignacionencuesta>());
        }
        if (fuente.getEncabezadoList() == null) {
            fuente.setEncabezadoList(new ArrayList<Encabezado>());
        }
        if (fuente.getEgresadoList() == null) {
            fuente.setEgresadoList(new ArrayList<Egresado>());
        }
        if (fuente.getAgenciagubernamentalList() == null) {
            fuente.setAgenciagubernamentalList(new ArrayList<Agenciagubernamental>());
        }
        if (fuente.getEmpleadorList() == null) {
            fuente.setEmpleadorList(new ArrayList<Empleador>());
        }
        if (fuente.getMuestracriterioList() == null) {
            fuente.setMuestracriterioList(new ArrayList<Muestracriterio>());
        }
        if (fuente.getDocenteList() == null) {
            fuente.setDocenteList(new ArrayList<Docente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Administrativo> attachedAdministrativoList = new ArrayList<Administrativo>();
            for (Administrativo administrativoListAdministrativoToAttach : fuente.getAdministrativoList()) {
                administrativoListAdministrativoToAttach = em.getReference(administrativoListAdministrativoToAttach.getClass(), administrativoListAdministrativoToAttach.getId());
                attachedAdministrativoList.add(administrativoListAdministrativoToAttach);
            }
            fuente.setAdministrativoList(attachedAdministrativoList);
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : fuente.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            fuente.setEstudianteList(attachedEstudianteList);
            List<Directorprograma> attachedDirectorprogramaList = new ArrayList<Directorprograma>();
            for (Directorprograma directorprogramaListDirectorprogramaToAttach : fuente.getDirectorprogramaList()) {
                directorprogramaListDirectorprogramaToAttach = em.getReference(directorprogramaListDirectorprogramaToAttach.getClass(), directorprogramaListDirectorprogramaToAttach.getId());
                attachedDirectorprogramaList.add(directorprogramaListDirectorprogramaToAttach);
            }
            fuente.setDirectorprogramaList(attachedDirectorprogramaList);
            List<Asignacionencuesta> attachedAsignacionencuestaList = new ArrayList<Asignacionencuesta>();
            for (Asignacionencuesta asignacionencuestaListAsignacionencuestaToAttach : fuente.getAsignacionencuestaList()) {
                asignacionencuestaListAsignacionencuestaToAttach = em.getReference(asignacionencuestaListAsignacionencuestaToAttach.getClass(), asignacionencuestaListAsignacionencuestaToAttach.getId());
                attachedAsignacionencuestaList.add(asignacionencuestaListAsignacionencuestaToAttach);
            }
            fuente.setAsignacionencuestaList(attachedAsignacionencuestaList);
            List<Encabezado> attachedEncabezadoList = new ArrayList<Encabezado>();
            for (Encabezado encabezadoListEncabezadoToAttach : fuente.getEncabezadoList()) {
                encabezadoListEncabezadoToAttach = em.getReference(encabezadoListEncabezadoToAttach.getClass(), encabezadoListEncabezadoToAttach.getId());
                attachedEncabezadoList.add(encabezadoListEncabezadoToAttach);
            }
            fuente.setEncabezadoList(attachedEncabezadoList);
            List<Egresado> attachedEgresadoList = new ArrayList<Egresado>();
            for (Egresado egresadoListEgresadoToAttach : fuente.getEgresadoList()) {
                egresadoListEgresadoToAttach = em.getReference(egresadoListEgresadoToAttach.getClass(), egresadoListEgresadoToAttach.getId());
                attachedEgresadoList.add(egresadoListEgresadoToAttach);
            }
            fuente.setEgresadoList(attachedEgresadoList);
            List<Agenciagubernamental> attachedAgenciagubernamentalList = new ArrayList<Agenciagubernamental>();
            for (Agenciagubernamental agenciagubernamentalListAgenciagubernamentalToAttach : fuente.getAgenciagubernamentalList()) {
                agenciagubernamentalListAgenciagubernamentalToAttach = em.getReference(agenciagubernamentalListAgenciagubernamentalToAttach.getClass(), agenciagubernamentalListAgenciagubernamentalToAttach.getId());
                attachedAgenciagubernamentalList.add(agenciagubernamentalListAgenciagubernamentalToAttach);
            }
            fuente.setAgenciagubernamentalList(attachedAgenciagubernamentalList);
            List<Empleador> attachedEmpleadorList = new ArrayList<Empleador>();
            for (Empleador empleadorListEmpleadorToAttach : fuente.getEmpleadorList()) {
                empleadorListEmpleadorToAttach = em.getReference(empleadorListEmpleadorToAttach.getClass(), empleadorListEmpleadorToAttach.getId());
                attachedEmpleadorList.add(empleadorListEmpleadorToAttach);
            }
            fuente.setEmpleadorList(attachedEmpleadorList);
            List<Muestracriterio> attachedMuestracriterioList = new ArrayList<Muestracriterio>();
            for (Muestracriterio muestracriterioListMuestracriterioToAttach : fuente.getMuestracriterioList()) {
                muestracriterioListMuestracriterioToAttach = em.getReference(muestracriterioListMuestracriterioToAttach.getClass(), muestracriterioListMuestracriterioToAttach.getId());
                attachedMuestracriterioList.add(muestracriterioListMuestracriterioToAttach);
            }
            fuente.setMuestracriterioList(attachedMuestracriterioList);
            List<Docente> attachedDocenteList = new ArrayList<Docente>();
            for (Docente docenteListDocenteToAttach : fuente.getDocenteList()) {
                docenteListDocenteToAttach = em.getReference(docenteListDocenteToAttach.getClass(), docenteListDocenteToAttach.getId());
                attachedDocenteList.add(docenteListDocenteToAttach);
            }
            fuente.setDocenteList(attachedDocenteList);
            em.persist(fuente);
            for (Administrativo administrativoListAdministrativo : fuente.getAdministrativoList()) {
                Fuente oldFuenteIdOfAdministrativoListAdministrativo = administrativoListAdministrativo.getFuenteId();
                administrativoListAdministrativo.setFuenteId(fuente);
                administrativoListAdministrativo = em.merge(administrativoListAdministrativo);
                if (oldFuenteIdOfAdministrativoListAdministrativo != null) {
                    oldFuenteIdOfAdministrativoListAdministrativo.getAdministrativoList().remove(administrativoListAdministrativo);
                    oldFuenteIdOfAdministrativoListAdministrativo = em.merge(oldFuenteIdOfAdministrativoListAdministrativo);
                }
            }
            for (Estudiante estudianteListEstudiante : fuente.getEstudianteList()) {
                Fuente oldFuenteIdOfEstudianteListEstudiante = estudianteListEstudiante.getFuenteId();
                estudianteListEstudiante.setFuenteId(fuente);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldFuenteIdOfEstudianteListEstudiante != null) {
                    oldFuenteIdOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldFuenteIdOfEstudianteListEstudiante = em.merge(oldFuenteIdOfEstudianteListEstudiante);
                }
            }
            for (Directorprograma directorprogramaListDirectorprograma : fuente.getDirectorprogramaList()) {
                Fuente oldFuenteIdOfDirectorprogramaListDirectorprograma = directorprogramaListDirectorprograma.getFuenteId();
                directorprogramaListDirectorprograma.setFuenteId(fuente);
                directorprogramaListDirectorprograma = em.merge(directorprogramaListDirectorprograma);
                if (oldFuenteIdOfDirectorprogramaListDirectorprograma != null) {
                    oldFuenteIdOfDirectorprogramaListDirectorprograma.getDirectorprogramaList().remove(directorprogramaListDirectorprograma);
                    oldFuenteIdOfDirectorprogramaListDirectorprograma = em.merge(oldFuenteIdOfDirectorprogramaListDirectorprograma);
                }
            }
            for (Asignacionencuesta asignacionencuestaListAsignacionencuesta : fuente.getAsignacionencuestaList()) {
                Fuente oldFuenteIdOfAsignacionencuestaListAsignacionencuesta = asignacionencuestaListAsignacionencuesta.getFuenteId();
                asignacionencuestaListAsignacionencuesta.setFuenteId(fuente);
                asignacionencuestaListAsignacionencuesta = em.merge(asignacionencuestaListAsignacionencuesta);
                if (oldFuenteIdOfAsignacionencuestaListAsignacionencuesta != null) {
                    oldFuenteIdOfAsignacionencuestaListAsignacionencuesta.getAsignacionencuestaList().remove(asignacionencuestaListAsignacionencuesta);
                    oldFuenteIdOfAsignacionencuestaListAsignacionencuesta = em.merge(oldFuenteIdOfAsignacionencuestaListAsignacionencuesta);
                }
            }
            for (Encabezado encabezadoListEncabezado : fuente.getEncabezadoList()) {
                Fuente oldFuenteIdOfEncabezadoListEncabezado = encabezadoListEncabezado.getFuenteId();
                encabezadoListEncabezado.setFuenteId(fuente);
                encabezadoListEncabezado = em.merge(encabezadoListEncabezado);
                if (oldFuenteIdOfEncabezadoListEncabezado != null) {
                    oldFuenteIdOfEncabezadoListEncabezado.getEncabezadoList().remove(encabezadoListEncabezado);
                    oldFuenteIdOfEncabezadoListEncabezado = em.merge(oldFuenteIdOfEncabezadoListEncabezado);
                }
            }
            for (Egresado egresadoListEgresado : fuente.getEgresadoList()) {
                Fuente oldFuenteIdOfEgresadoListEgresado = egresadoListEgresado.getFuenteId();
                egresadoListEgresado.setFuenteId(fuente);
                egresadoListEgresado = em.merge(egresadoListEgresado);
                if (oldFuenteIdOfEgresadoListEgresado != null) {
                    oldFuenteIdOfEgresadoListEgresado.getEgresadoList().remove(egresadoListEgresado);
                    oldFuenteIdOfEgresadoListEgresado = em.merge(oldFuenteIdOfEgresadoListEgresado);
                }
            }
            for (Agenciagubernamental agenciagubernamentalListAgenciagubernamental : fuente.getAgenciagubernamentalList()) {
                Fuente oldFuenteIdOfAgenciagubernamentalListAgenciagubernamental = agenciagubernamentalListAgenciagubernamental.getFuenteId();
                agenciagubernamentalListAgenciagubernamental.setFuenteId(fuente);
                agenciagubernamentalListAgenciagubernamental = em.merge(agenciagubernamentalListAgenciagubernamental);
                if (oldFuenteIdOfAgenciagubernamentalListAgenciagubernamental != null) {
                    oldFuenteIdOfAgenciagubernamentalListAgenciagubernamental.getAgenciagubernamentalList().remove(agenciagubernamentalListAgenciagubernamental);
                    oldFuenteIdOfAgenciagubernamentalListAgenciagubernamental = em.merge(oldFuenteIdOfAgenciagubernamentalListAgenciagubernamental);
                }
            }
            for (Empleador empleadorListEmpleador : fuente.getEmpleadorList()) {
                Fuente oldFuenteIdOfEmpleadorListEmpleador = empleadorListEmpleador.getFuenteId();
                empleadorListEmpleador.setFuenteId(fuente);
                empleadorListEmpleador = em.merge(empleadorListEmpleador);
                if (oldFuenteIdOfEmpleadorListEmpleador != null) {
                    oldFuenteIdOfEmpleadorListEmpleador.getEmpleadorList().remove(empleadorListEmpleador);
                    oldFuenteIdOfEmpleadorListEmpleador = em.merge(oldFuenteIdOfEmpleadorListEmpleador);
                }
            }
            for (Muestracriterio muestracriterioListMuestracriterio : fuente.getMuestracriterioList()) {
                Fuente oldFuenteIdOfMuestracriterioListMuestracriterio = muestracriterioListMuestracriterio.getFuenteId();
                muestracriterioListMuestracriterio.setFuenteId(fuente);
                muestracriterioListMuestracriterio = em.merge(muestracriterioListMuestracriterio);
                if (oldFuenteIdOfMuestracriterioListMuestracriterio != null) {
                    oldFuenteIdOfMuestracriterioListMuestracriterio.getMuestracriterioList().remove(muestracriterioListMuestracriterio);
                    oldFuenteIdOfMuestracriterioListMuestracriterio = em.merge(oldFuenteIdOfMuestracriterioListMuestracriterio);
                }
            }
            for (Docente docenteListDocente : fuente.getDocenteList()) {
                Fuente oldFuenteIdOfDocenteListDocente = docenteListDocente.getFuenteId();
                docenteListDocente.setFuenteId(fuente);
                docenteListDocente = em.merge(docenteListDocente);
                if (oldFuenteIdOfDocenteListDocente != null) {
                    oldFuenteIdOfDocenteListDocente.getDocenteList().remove(docenteListDocente);
                    oldFuenteIdOfDocenteListDocente = em.merge(oldFuenteIdOfDocenteListDocente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fuente fuente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fuente persistentFuente = em.find(Fuente.class, fuente.getId());
            List<Administrativo> administrativoListOld = persistentFuente.getAdministrativoList();
            List<Administrativo> administrativoListNew = fuente.getAdministrativoList();
            List<Estudiante> estudianteListOld = persistentFuente.getEstudianteList();
            List<Estudiante> estudianteListNew = fuente.getEstudianteList();
            List<Directorprograma> directorprogramaListOld = persistentFuente.getDirectorprogramaList();
            List<Directorprograma> directorprogramaListNew = fuente.getDirectorprogramaList();
            List<Asignacionencuesta> asignacionencuestaListOld = persistentFuente.getAsignacionencuestaList();
            List<Asignacionencuesta> asignacionencuestaListNew = fuente.getAsignacionencuestaList();
            List<Encabezado> encabezadoListOld = persistentFuente.getEncabezadoList();
            List<Encabezado> encabezadoListNew = fuente.getEncabezadoList();
            List<Egresado> egresadoListOld = persistentFuente.getEgresadoList();
            List<Egresado> egresadoListNew = fuente.getEgresadoList();
            List<Agenciagubernamental> agenciagubernamentalListOld = persistentFuente.getAgenciagubernamentalList();
            List<Agenciagubernamental> agenciagubernamentalListNew = fuente.getAgenciagubernamentalList();
            List<Empleador> empleadorListOld = persistentFuente.getEmpleadorList();
            List<Empleador> empleadorListNew = fuente.getEmpleadorList();
            List<Muestracriterio> muestracriterioListOld = persistentFuente.getMuestracriterioList();
            List<Muestracriterio> muestracriterioListNew = fuente.getMuestracriterioList();
            List<Docente> docenteListOld = persistentFuente.getDocenteList();
            List<Docente> docenteListNew = fuente.getDocenteList();
            List<String> illegalOrphanMessages = null;
            for (Administrativo administrativoListOldAdministrativo : administrativoListOld) {
                if (!administrativoListNew.contains(administrativoListOldAdministrativo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Administrativo " + administrativoListOldAdministrativo + " since its fuenteId field is not nullable.");
                }
            }
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its fuenteId field is not nullable.");
                }
            }
            for (Directorprograma directorprogramaListOldDirectorprograma : directorprogramaListOld) {
                if (!directorprogramaListNew.contains(directorprogramaListOldDirectorprograma)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Directorprograma " + directorprogramaListOldDirectorprograma + " since its fuenteId field is not nullable.");
                }
            }
            for (Asignacionencuesta asignacionencuestaListOldAsignacionencuesta : asignacionencuestaListOld) {
                if (!asignacionencuestaListNew.contains(asignacionencuestaListOldAsignacionencuesta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asignacionencuesta " + asignacionencuestaListOldAsignacionencuesta + " since its fuenteId field is not nullable.");
                }
            }
            for (Encabezado encabezadoListOldEncabezado : encabezadoListOld) {
                if (!encabezadoListNew.contains(encabezadoListOldEncabezado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Encabezado " + encabezadoListOldEncabezado + " since its fuenteId field is not nullable.");
                }
            }
            for (Egresado egresadoListOldEgresado : egresadoListOld) {
                if (!egresadoListNew.contains(egresadoListOldEgresado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Egresado " + egresadoListOldEgresado + " since its fuenteId field is not nullable.");
                }
            }
            for (Agenciagubernamental agenciagubernamentalListOldAgenciagubernamental : agenciagubernamentalListOld) {
                if (!agenciagubernamentalListNew.contains(agenciagubernamentalListOldAgenciagubernamental)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Agenciagubernamental " + agenciagubernamentalListOldAgenciagubernamental + " since its fuenteId field is not nullable.");
                }
            }
            for (Empleador empleadorListOldEmpleador : empleadorListOld) {
                if (!empleadorListNew.contains(empleadorListOldEmpleador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empleador " + empleadorListOldEmpleador + " since its fuenteId field is not nullable.");
                }
            }
            for (Muestracriterio muestracriterioListOldMuestracriterio : muestracriterioListOld) {
                if (!muestracriterioListNew.contains(muestracriterioListOldMuestracriterio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Muestracriterio " + muestracriterioListOldMuestracriterio + " since its fuenteId field is not nullable.");
                }
            }
            for (Docente docenteListOldDocente : docenteListOld) {
                if (!docenteListNew.contains(docenteListOldDocente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Docente " + docenteListOldDocente + " since its fuenteId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Administrativo> attachedAdministrativoListNew = new ArrayList<Administrativo>();
            for (Administrativo administrativoListNewAdministrativoToAttach : administrativoListNew) {
                administrativoListNewAdministrativoToAttach = em.getReference(administrativoListNewAdministrativoToAttach.getClass(), administrativoListNewAdministrativoToAttach.getId());
                attachedAdministrativoListNew.add(administrativoListNewAdministrativoToAttach);
            }
            administrativoListNew = attachedAdministrativoListNew;
            fuente.setAdministrativoList(administrativoListNew);
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getId());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            fuente.setEstudianteList(estudianteListNew);
            List<Directorprograma> attachedDirectorprogramaListNew = new ArrayList<Directorprograma>();
            for (Directorprograma directorprogramaListNewDirectorprogramaToAttach : directorprogramaListNew) {
                directorprogramaListNewDirectorprogramaToAttach = em.getReference(directorprogramaListNewDirectorprogramaToAttach.getClass(), directorprogramaListNewDirectorprogramaToAttach.getId());
                attachedDirectorprogramaListNew.add(directorprogramaListNewDirectorprogramaToAttach);
            }
            directorprogramaListNew = attachedDirectorprogramaListNew;
            fuente.setDirectorprogramaList(directorprogramaListNew);
            List<Asignacionencuesta> attachedAsignacionencuestaListNew = new ArrayList<Asignacionencuesta>();
            for (Asignacionencuesta asignacionencuestaListNewAsignacionencuestaToAttach : asignacionencuestaListNew) {
                asignacionencuestaListNewAsignacionencuestaToAttach = em.getReference(asignacionencuestaListNewAsignacionencuestaToAttach.getClass(), asignacionencuestaListNewAsignacionencuestaToAttach.getId());
                attachedAsignacionencuestaListNew.add(asignacionencuestaListNewAsignacionencuestaToAttach);
            }
            asignacionencuestaListNew = attachedAsignacionencuestaListNew;
            fuente.setAsignacionencuestaList(asignacionencuestaListNew);
            List<Encabezado> attachedEncabezadoListNew = new ArrayList<Encabezado>();
            for (Encabezado encabezadoListNewEncabezadoToAttach : encabezadoListNew) {
                encabezadoListNewEncabezadoToAttach = em.getReference(encabezadoListNewEncabezadoToAttach.getClass(), encabezadoListNewEncabezadoToAttach.getId());
                attachedEncabezadoListNew.add(encabezadoListNewEncabezadoToAttach);
            }
            encabezadoListNew = attachedEncabezadoListNew;
            fuente.setEncabezadoList(encabezadoListNew);
            List<Egresado> attachedEgresadoListNew = new ArrayList<Egresado>();
            for (Egresado egresadoListNewEgresadoToAttach : egresadoListNew) {
                egresadoListNewEgresadoToAttach = em.getReference(egresadoListNewEgresadoToAttach.getClass(), egresadoListNewEgresadoToAttach.getId());
                attachedEgresadoListNew.add(egresadoListNewEgresadoToAttach);
            }
            egresadoListNew = attachedEgresadoListNew;
            fuente.setEgresadoList(egresadoListNew);
            List<Agenciagubernamental> attachedAgenciagubernamentalListNew = new ArrayList<Agenciagubernamental>();
            for (Agenciagubernamental agenciagubernamentalListNewAgenciagubernamentalToAttach : agenciagubernamentalListNew) {
                agenciagubernamentalListNewAgenciagubernamentalToAttach = em.getReference(agenciagubernamentalListNewAgenciagubernamentalToAttach.getClass(), agenciagubernamentalListNewAgenciagubernamentalToAttach.getId());
                attachedAgenciagubernamentalListNew.add(agenciagubernamentalListNewAgenciagubernamentalToAttach);
            }
            agenciagubernamentalListNew = attachedAgenciagubernamentalListNew;
            fuente.setAgenciagubernamentalList(agenciagubernamentalListNew);
            List<Empleador> attachedEmpleadorListNew = new ArrayList<Empleador>();
            for (Empleador empleadorListNewEmpleadorToAttach : empleadorListNew) {
                empleadorListNewEmpleadorToAttach = em.getReference(empleadorListNewEmpleadorToAttach.getClass(), empleadorListNewEmpleadorToAttach.getId());
                attachedEmpleadorListNew.add(empleadorListNewEmpleadorToAttach);
            }
            empleadorListNew = attachedEmpleadorListNew;
            fuente.setEmpleadorList(empleadorListNew);
            List<Muestracriterio> attachedMuestracriterioListNew = new ArrayList<Muestracriterio>();
            for (Muestracriterio muestracriterioListNewMuestracriterioToAttach : muestracriterioListNew) {
                muestracriterioListNewMuestracriterioToAttach = em.getReference(muestracriterioListNewMuestracriterioToAttach.getClass(), muestracriterioListNewMuestracriterioToAttach.getId());
                attachedMuestracriterioListNew.add(muestracriterioListNewMuestracriterioToAttach);
            }
            muestracriterioListNew = attachedMuestracriterioListNew;
            fuente.setMuestracriterioList(muestracriterioListNew);
            List<Docente> attachedDocenteListNew = new ArrayList<Docente>();
            for (Docente docenteListNewDocenteToAttach : docenteListNew) {
                docenteListNewDocenteToAttach = em.getReference(docenteListNewDocenteToAttach.getClass(), docenteListNewDocenteToAttach.getId());
                attachedDocenteListNew.add(docenteListNewDocenteToAttach);
            }
            docenteListNew = attachedDocenteListNew;
            fuente.setDocenteList(docenteListNew);
            fuente = em.merge(fuente);
            for (Administrativo administrativoListNewAdministrativo : administrativoListNew) {
                if (!administrativoListOld.contains(administrativoListNewAdministrativo)) {
                    Fuente oldFuenteIdOfAdministrativoListNewAdministrativo = administrativoListNewAdministrativo.getFuenteId();
                    administrativoListNewAdministrativo.setFuenteId(fuente);
                    administrativoListNewAdministrativo = em.merge(administrativoListNewAdministrativo);
                    if (oldFuenteIdOfAdministrativoListNewAdministrativo != null && !oldFuenteIdOfAdministrativoListNewAdministrativo.equals(fuente)) {
                        oldFuenteIdOfAdministrativoListNewAdministrativo.getAdministrativoList().remove(administrativoListNewAdministrativo);
                        oldFuenteIdOfAdministrativoListNewAdministrativo = em.merge(oldFuenteIdOfAdministrativoListNewAdministrativo);
                    }
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    Fuente oldFuenteIdOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getFuenteId();
                    estudianteListNewEstudiante.setFuenteId(fuente);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldFuenteIdOfEstudianteListNewEstudiante != null && !oldFuenteIdOfEstudianteListNewEstudiante.equals(fuente)) {
                        oldFuenteIdOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldFuenteIdOfEstudianteListNewEstudiante = em.merge(oldFuenteIdOfEstudianteListNewEstudiante);
                    }
                }
            }
            for (Directorprograma directorprogramaListNewDirectorprograma : directorprogramaListNew) {
                if (!directorprogramaListOld.contains(directorprogramaListNewDirectorprograma)) {
                    Fuente oldFuenteIdOfDirectorprogramaListNewDirectorprograma = directorprogramaListNewDirectorprograma.getFuenteId();
                    directorprogramaListNewDirectorprograma.setFuenteId(fuente);
                    directorprogramaListNewDirectorprograma = em.merge(directorprogramaListNewDirectorprograma);
                    if (oldFuenteIdOfDirectorprogramaListNewDirectorprograma != null && !oldFuenteIdOfDirectorprogramaListNewDirectorprograma.equals(fuente)) {
                        oldFuenteIdOfDirectorprogramaListNewDirectorprograma.getDirectorprogramaList().remove(directorprogramaListNewDirectorprograma);
                        oldFuenteIdOfDirectorprogramaListNewDirectorprograma = em.merge(oldFuenteIdOfDirectorprogramaListNewDirectorprograma);
                    }
                }
            }
            for (Asignacionencuesta asignacionencuestaListNewAsignacionencuesta : asignacionencuestaListNew) {
                if (!asignacionencuestaListOld.contains(asignacionencuestaListNewAsignacionencuesta)) {
                    Fuente oldFuenteIdOfAsignacionencuestaListNewAsignacionencuesta = asignacionencuestaListNewAsignacionencuesta.getFuenteId();
                    asignacionencuestaListNewAsignacionencuesta.setFuenteId(fuente);
                    asignacionencuestaListNewAsignacionencuesta = em.merge(asignacionencuestaListNewAsignacionencuesta);
                    if (oldFuenteIdOfAsignacionencuestaListNewAsignacionencuesta != null && !oldFuenteIdOfAsignacionencuestaListNewAsignacionencuesta.equals(fuente)) {
                        oldFuenteIdOfAsignacionencuestaListNewAsignacionencuesta.getAsignacionencuestaList().remove(asignacionencuestaListNewAsignacionencuesta);
                        oldFuenteIdOfAsignacionencuestaListNewAsignacionencuesta = em.merge(oldFuenteIdOfAsignacionencuestaListNewAsignacionencuesta);
                    }
                }
            }
            for (Encabezado encabezadoListNewEncabezado : encabezadoListNew) {
                if (!encabezadoListOld.contains(encabezadoListNewEncabezado)) {
                    Fuente oldFuenteIdOfEncabezadoListNewEncabezado = encabezadoListNewEncabezado.getFuenteId();
                    encabezadoListNewEncabezado.setFuenteId(fuente);
                    encabezadoListNewEncabezado = em.merge(encabezadoListNewEncabezado);
                    if (oldFuenteIdOfEncabezadoListNewEncabezado != null && !oldFuenteIdOfEncabezadoListNewEncabezado.equals(fuente)) {
                        oldFuenteIdOfEncabezadoListNewEncabezado.getEncabezadoList().remove(encabezadoListNewEncabezado);
                        oldFuenteIdOfEncabezadoListNewEncabezado = em.merge(oldFuenteIdOfEncabezadoListNewEncabezado);
                    }
                }
            }
            for (Egresado egresadoListNewEgresado : egresadoListNew) {
                if (!egresadoListOld.contains(egresadoListNewEgresado)) {
                    Fuente oldFuenteIdOfEgresadoListNewEgresado = egresadoListNewEgresado.getFuenteId();
                    egresadoListNewEgresado.setFuenteId(fuente);
                    egresadoListNewEgresado = em.merge(egresadoListNewEgresado);
                    if (oldFuenteIdOfEgresadoListNewEgresado != null && !oldFuenteIdOfEgresadoListNewEgresado.equals(fuente)) {
                        oldFuenteIdOfEgresadoListNewEgresado.getEgresadoList().remove(egresadoListNewEgresado);
                        oldFuenteIdOfEgresadoListNewEgresado = em.merge(oldFuenteIdOfEgresadoListNewEgresado);
                    }
                }
            }
            for (Agenciagubernamental agenciagubernamentalListNewAgenciagubernamental : agenciagubernamentalListNew) {
                if (!agenciagubernamentalListOld.contains(agenciagubernamentalListNewAgenciagubernamental)) {
                    Fuente oldFuenteIdOfAgenciagubernamentalListNewAgenciagubernamental = agenciagubernamentalListNewAgenciagubernamental.getFuenteId();
                    agenciagubernamentalListNewAgenciagubernamental.setFuenteId(fuente);
                    agenciagubernamentalListNewAgenciagubernamental = em.merge(agenciagubernamentalListNewAgenciagubernamental);
                    if (oldFuenteIdOfAgenciagubernamentalListNewAgenciagubernamental != null && !oldFuenteIdOfAgenciagubernamentalListNewAgenciagubernamental.equals(fuente)) {
                        oldFuenteIdOfAgenciagubernamentalListNewAgenciagubernamental.getAgenciagubernamentalList().remove(agenciagubernamentalListNewAgenciagubernamental);
                        oldFuenteIdOfAgenciagubernamentalListNewAgenciagubernamental = em.merge(oldFuenteIdOfAgenciagubernamentalListNewAgenciagubernamental);
                    }
                }
            }
            for (Empleador empleadorListNewEmpleador : empleadorListNew) {
                if (!empleadorListOld.contains(empleadorListNewEmpleador)) {
                    Fuente oldFuenteIdOfEmpleadorListNewEmpleador = empleadorListNewEmpleador.getFuenteId();
                    empleadorListNewEmpleador.setFuenteId(fuente);
                    empleadorListNewEmpleador = em.merge(empleadorListNewEmpleador);
                    if (oldFuenteIdOfEmpleadorListNewEmpleador != null && !oldFuenteIdOfEmpleadorListNewEmpleador.equals(fuente)) {
                        oldFuenteIdOfEmpleadorListNewEmpleador.getEmpleadorList().remove(empleadorListNewEmpleador);
                        oldFuenteIdOfEmpleadorListNewEmpleador = em.merge(oldFuenteIdOfEmpleadorListNewEmpleador);
                    }
                }
            }
            for (Muestracriterio muestracriterioListNewMuestracriterio : muestracriterioListNew) {
                if (!muestracriterioListOld.contains(muestracriterioListNewMuestracriterio)) {
                    Fuente oldFuenteIdOfMuestracriterioListNewMuestracriterio = muestracriterioListNewMuestracriterio.getFuenteId();
                    muestracriterioListNewMuestracriterio.setFuenteId(fuente);
                    muestracriterioListNewMuestracriterio = em.merge(muestracriterioListNewMuestracriterio);
                    if (oldFuenteIdOfMuestracriterioListNewMuestracriterio != null && !oldFuenteIdOfMuestracriterioListNewMuestracriterio.equals(fuente)) {
                        oldFuenteIdOfMuestracriterioListNewMuestracriterio.getMuestracriterioList().remove(muestracriterioListNewMuestracriterio);
                        oldFuenteIdOfMuestracriterioListNewMuestracriterio = em.merge(oldFuenteIdOfMuestracriterioListNewMuestracriterio);
                    }
                }
            }
            for (Docente docenteListNewDocente : docenteListNew) {
                if (!docenteListOld.contains(docenteListNewDocente)) {
                    Fuente oldFuenteIdOfDocenteListNewDocente = docenteListNewDocente.getFuenteId();
                    docenteListNewDocente.setFuenteId(fuente);
                    docenteListNewDocente = em.merge(docenteListNewDocente);
                    if (oldFuenteIdOfDocenteListNewDocente != null && !oldFuenteIdOfDocenteListNewDocente.equals(fuente)) {
                        oldFuenteIdOfDocenteListNewDocente.getDocenteList().remove(docenteListNewDocente);
                        oldFuenteIdOfDocenteListNewDocente = em.merge(oldFuenteIdOfDocenteListNewDocente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fuente.getId();
                if (findFuente(id) == null) {
                    throw new NonexistentEntityException("The fuente with id " + id + " no longer exists.");
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
            Fuente fuente;
            try {
                fuente = em.getReference(Fuente.class, id);
                fuente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fuente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Administrativo> administrativoListOrphanCheck = fuente.getAdministrativoList();
            for (Administrativo administrativoListOrphanCheckAdministrativo : administrativoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Fuente (" + fuente + ") cannot be destroyed since the Administrativo " + administrativoListOrphanCheckAdministrativo + " in its administrativoList field has a non-nullable fuenteId field.");
            }
            List<Estudiante> estudianteListOrphanCheck = fuente.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Fuente (" + fuente + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable fuenteId field.");
            }
            List<Directorprograma> directorprogramaListOrphanCheck = fuente.getDirectorprogramaList();
            for (Directorprograma directorprogramaListOrphanCheckDirectorprograma : directorprogramaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Fuente (" + fuente + ") cannot be destroyed since the Directorprograma " + directorprogramaListOrphanCheckDirectorprograma + " in its directorprogramaList field has a non-nullable fuenteId field.");
            }
            List<Asignacionencuesta> asignacionencuestaListOrphanCheck = fuente.getAsignacionencuestaList();
            for (Asignacionencuesta asignacionencuestaListOrphanCheckAsignacionencuesta : asignacionencuestaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Fuente (" + fuente + ") cannot be destroyed since the Asignacionencuesta " + asignacionencuestaListOrphanCheckAsignacionencuesta + " in its asignacionencuestaList field has a non-nullable fuenteId field.");
            }
            List<Encabezado> encabezadoListOrphanCheck = fuente.getEncabezadoList();
            for (Encabezado encabezadoListOrphanCheckEncabezado : encabezadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Fuente (" + fuente + ") cannot be destroyed since the Encabezado " + encabezadoListOrphanCheckEncabezado + " in its encabezadoList field has a non-nullable fuenteId field.");
            }
            List<Egresado> egresadoListOrphanCheck = fuente.getEgresadoList();
            for (Egresado egresadoListOrphanCheckEgresado : egresadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Fuente (" + fuente + ") cannot be destroyed since the Egresado " + egresadoListOrphanCheckEgresado + " in its egresadoList field has a non-nullable fuenteId field.");
            }
            List<Agenciagubernamental> agenciagubernamentalListOrphanCheck = fuente.getAgenciagubernamentalList();
            for (Agenciagubernamental agenciagubernamentalListOrphanCheckAgenciagubernamental : agenciagubernamentalListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Fuente (" + fuente + ") cannot be destroyed since the Agenciagubernamental " + agenciagubernamentalListOrphanCheckAgenciagubernamental + " in its agenciagubernamentalList field has a non-nullable fuenteId field.");
            }
            List<Empleador> empleadorListOrphanCheck = fuente.getEmpleadorList();
            for (Empleador empleadorListOrphanCheckEmpleador : empleadorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Fuente (" + fuente + ") cannot be destroyed since the Empleador " + empleadorListOrphanCheckEmpleador + " in its empleadorList field has a non-nullable fuenteId field.");
            }
            List<Muestracriterio> muestracriterioListOrphanCheck = fuente.getMuestracriterioList();
            for (Muestracriterio muestracriterioListOrphanCheckMuestracriterio : muestracriterioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Fuente (" + fuente + ") cannot be destroyed since the Muestracriterio " + muestracriterioListOrphanCheckMuestracriterio + " in its muestracriterioList field has a non-nullable fuenteId field.");
            }
            List<Docente> docenteListOrphanCheck = fuente.getDocenteList();
            for (Docente docenteListOrphanCheckDocente : docenteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Fuente (" + fuente + ") cannot be destroyed since the Docente " + docenteListOrphanCheckDocente + " in its docenteList field has a non-nullable fuenteId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(fuente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fuente> findFuenteEntities() {
        return findFuenteEntities(true, -1, -1);
    }

    public List<Fuente> findFuenteEntities(int maxResults, int firstResult) {
        return findFuenteEntities(false, maxResults, firstResult);
    }

    private List<Fuente> findFuenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fuente.class));
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

    public Fuente findFuente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fuente.class, id);
        } finally {
            em.close();
        }
    }

    public int getFuenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fuente> rt = cq.from(Fuente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
