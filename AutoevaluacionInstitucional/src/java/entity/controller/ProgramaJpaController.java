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

/**
 *
 * @author Usuario
 */
public class ProgramaJpaController implements Serializable {

    public ProgramaJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Programa programa) {
        if (programa.getAdministrativoList() == null) {
            programa.setAdministrativoList(new ArrayList<Administrativo>());
        }
        if (programa.getEgresadoList() == null) {
            programa.setEgresadoList(new ArrayList<Egresado>());
        }
        if (programa.getRepresentanteList() == null) {
            programa.setRepresentanteList(new ArrayList<Representante>());
        }
        if (programa.getProcesoList() == null) {
            programa.setProcesoList(new ArrayList<Proceso>());
        }
        if (programa.getEstudianteList() == null) {
            programa.setEstudianteList(new ArrayList<Estudiante>());
        }
        if (programa.getDirectorprogramaList() == null) {
            programa.setDirectorprogramaList(new ArrayList<Directorprograma>());
        }
        if (programa.getDocenteList() == null) {
            programa.setDocenteList(new ArrayList<Docente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sede sedeId = programa.getSedeId();
            if (sedeId != null) {
                sedeId = em.getReference(sedeId.getClass(), sedeId.getId());
                programa.setSedeId(sedeId);
            }
            Facultad facultadId = programa.getFacultadId();
            if (facultadId != null) {
                facultadId = em.getReference(facultadId.getClass(), facultadId.getId());
                programa.setFacultadId(facultadId);
            }
            List<Administrativo> attachedAdministrativoList = new ArrayList<Administrativo>();
            for (Administrativo administrativoListAdministrativoToAttach : programa.getAdministrativoList()) {
                administrativoListAdministrativoToAttach = em.getReference(administrativoListAdministrativoToAttach.getClass(), administrativoListAdministrativoToAttach.getId());
                attachedAdministrativoList.add(administrativoListAdministrativoToAttach);
            }
            programa.setAdministrativoList(attachedAdministrativoList);
            List<Egresado> attachedEgresadoList = new ArrayList<Egresado>();
            for (Egresado egresadoListEgresadoToAttach : programa.getEgresadoList()) {
                egresadoListEgresadoToAttach = em.getReference(egresadoListEgresadoToAttach.getClass(), egresadoListEgresadoToAttach.getId());
                attachedEgresadoList.add(egresadoListEgresadoToAttach);
            }
            programa.setEgresadoList(attachedEgresadoList);
            List<Representante> attachedRepresentanteList = new ArrayList<Representante>();
            for (Representante representanteListRepresentanteToAttach : programa.getRepresentanteList()) {
                representanteListRepresentanteToAttach = em.getReference(representanteListRepresentanteToAttach.getClass(), representanteListRepresentanteToAttach.getId());
                attachedRepresentanteList.add(representanteListRepresentanteToAttach);
            }
            programa.setRepresentanteList(attachedRepresentanteList);
            List<Proceso> attachedProcesoList = new ArrayList<Proceso>();
            for (Proceso procesoListProcesoToAttach : programa.getProcesoList()) {
                procesoListProcesoToAttach = em.getReference(procesoListProcesoToAttach.getClass(), procesoListProcesoToAttach.getId());
                attachedProcesoList.add(procesoListProcesoToAttach);
            }
            programa.setProcesoList(attachedProcesoList);
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : programa.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            programa.setEstudianteList(attachedEstudianteList);
            List<Directorprograma> attachedDirectorprogramaList = new ArrayList<Directorprograma>();
            for (Directorprograma directorprogramaListDirectorprogramaToAttach : programa.getDirectorprogramaList()) {
                directorprogramaListDirectorprogramaToAttach = em.getReference(directorprogramaListDirectorprogramaToAttach.getClass(), directorprogramaListDirectorprogramaToAttach.getId());
                attachedDirectorprogramaList.add(directorprogramaListDirectorprogramaToAttach);
            }
            programa.setDirectorprogramaList(attachedDirectorprogramaList);
            List<Docente> attachedDocenteList = new ArrayList<Docente>();
            for (Docente docenteListDocenteToAttach : programa.getDocenteList()) {
                docenteListDocenteToAttach = em.getReference(docenteListDocenteToAttach.getClass(), docenteListDocenteToAttach.getId());
                attachedDocenteList.add(docenteListDocenteToAttach);
            }
            programa.setDocenteList(attachedDocenteList);
            em.persist(programa);
            if (sedeId != null) {
                sedeId.getProgramaList().add(programa);
                sedeId = em.merge(sedeId);
            }
            if (facultadId != null) {
                facultadId.getProgramaList().add(programa);
                facultadId = em.merge(facultadId);
            }
            for (Administrativo administrativoListAdministrativo : programa.getAdministrativoList()) {
                Programa oldProgramaIdOfAdministrativoListAdministrativo = administrativoListAdministrativo.getProgramaId();
                administrativoListAdministrativo.setProgramaId(programa);
                administrativoListAdministrativo = em.merge(administrativoListAdministrativo);
                if (oldProgramaIdOfAdministrativoListAdministrativo != null) {
                    oldProgramaIdOfAdministrativoListAdministrativo.getAdministrativoList().remove(administrativoListAdministrativo);
                    oldProgramaIdOfAdministrativoListAdministrativo = em.merge(oldProgramaIdOfAdministrativoListAdministrativo);
                }
            }
            for (Egresado egresadoListEgresado : programa.getEgresadoList()) {
                Programa oldProgramaIdOfEgresadoListEgresado = egresadoListEgresado.getProgramaId();
                egresadoListEgresado.setProgramaId(programa);
                egresadoListEgresado = em.merge(egresadoListEgresado);
                if (oldProgramaIdOfEgresadoListEgresado != null) {
                    oldProgramaIdOfEgresadoListEgresado.getEgresadoList().remove(egresadoListEgresado);
                    oldProgramaIdOfEgresadoListEgresado = em.merge(oldProgramaIdOfEgresadoListEgresado);
                }
            }
            for (Representante representanteListRepresentante : programa.getRepresentanteList()) {
                Programa oldProgramaIdOfRepresentanteListRepresentante = representanteListRepresentante.getProgramaId();
                representanteListRepresentante.setProgramaId(programa);
                representanteListRepresentante = em.merge(representanteListRepresentante);
                if (oldProgramaIdOfRepresentanteListRepresentante != null) {
                    oldProgramaIdOfRepresentanteListRepresentante.getRepresentanteList().remove(representanteListRepresentante);
                    oldProgramaIdOfRepresentanteListRepresentante = em.merge(oldProgramaIdOfRepresentanteListRepresentante);
                }
            }
            for (Proceso procesoListProceso : programa.getProcesoList()) {
                Programa oldProgramaIdOfProcesoListProceso = procesoListProceso.getProgramaId();
                procesoListProceso.setProgramaId(programa);
                procesoListProceso = em.merge(procesoListProceso);
                if (oldProgramaIdOfProcesoListProceso != null) {
                    oldProgramaIdOfProcesoListProceso.getProcesoList().remove(procesoListProceso);
                    oldProgramaIdOfProcesoListProceso = em.merge(oldProgramaIdOfProcesoListProceso);
                }
            }
            for (Estudiante estudianteListEstudiante : programa.getEstudianteList()) {
                Programa oldProgramaIdOfEstudianteListEstudiante = estudianteListEstudiante.getProgramaId();
                estudianteListEstudiante.setProgramaId(programa);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldProgramaIdOfEstudianteListEstudiante != null) {
                    oldProgramaIdOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldProgramaIdOfEstudianteListEstudiante = em.merge(oldProgramaIdOfEstudianteListEstudiante);
                }
            }
            for (Directorprograma directorprogramaListDirectorprograma : programa.getDirectorprogramaList()) {
                Programa oldProgramaIdOfDirectorprogramaListDirectorprograma = directorprogramaListDirectorprograma.getProgramaId();
                directorprogramaListDirectorprograma.setProgramaId(programa);
                directorprogramaListDirectorprograma = em.merge(directorprogramaListDirectorprograma);
                if (oldProgramaIdOfDirectorprogramaListDirectorprograma != null) {
                    oldProgramaIdOfDirectorprogramaListDirectorprograma.getDirectorprogramaList().remove(directorprogramaListDirectorprograma);
                    oldProgramaIdOfDirectorprogramaListDirectorprograma = em.merge(oldProgramaIdOfDirectorprogramaListDirectorprograma);
                }
            }
            for (Docente docenteListDocente : programa.getDocenteList()) {
                Programa oldProgramaIdOfDocenteListDocente = docenteListDocente.getProgramaId();
                docenteListDocente.setProgramaId(programa);
                docenteListDocente = em.merge(docenteListDocente);
                if (oldProgramaIdOfDocenteListDocente != null) {
                    oldProgramaIdOfDocenteListDocente.getDocenteList().remove(docenteListDocente);
                    oldProgramaIdOfDocenteListDocente = em.merge(oldProgramaIdOfDocenteListDocente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programa programa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa persistentPrograma = em.find(Programa.class, programa.getId());
            Sede sedeIdOld = persistentPrograma.getSedeId();
            Sede sedeIdNew = programa.getSedeId();
            Facultad facultadIdOld = persistentPrograma.getFacultadId();
            Facultad facultadIdNew = programa.getFacultadId();
            List<Administrativo> administrativoListOld = persistentPrograma.getAdministrativoList();
            List<Administrativo> administrativoListNew = programa.getAdministrativoList();
            List<Egresado> egresadoListOld = persistentPrograma.getEgresadoList();
            List<Egresado> egresadoListNew = programa.getEgresadoList();
            List<Representante> representanteListOld = persistentPrograma.getRepresentanteList();
            List<Representante> representanteListNew = programa.getRepresentanteList();
            List<Proceso> procesoListOld = persistentPrograma.getProcesoList();
            List<Proceso> procesoListNew = programa.getProcesoList();
            List<Estudiante> estudianteListOld = persistentPrograma.getEstudianteList();
            List<Estudiante> estudianteListNew = programa.getEstudianteList();
            List<Directorprograma> directorprogramaListOld = persistentPrograma.getDirectorprogramaList();
            List<Directorprograma> directorprogramaListNew = programa.getDirectorprogramaList();
            List<Docente> docenteListOld = persistentPrograma.getDocenteList();
            List<Docente> docenteListNew = programa.getDocenteList();
            List<String> illegalOrphanMessages = null;
            for (Administrativo administrativoListOldAdministrativo : administrativoListOld) {
                if (!administrativoListNew.contains(administrativoListOldAdministrativo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Administrativo " + administrativoListOldAdministrativo + " since its programaId field is not nullable.");
                }
            }
            for (Egresado egresadoListOldEgresado : egresadoListOld) {
                if (!egresadoListNew.contains(egresadoListOldEgresado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Egresado " + egresadoListOldEgresado + " since its programaId field is not nullable.");
                }
            }
            for (Proceso procesoListOldProceso : procesoListOld) {
                if (!procesoListNew.contains(procesoListOldProceso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Proceso " + procesoListOldProceso + " since its programaId field is not nullable.");
                }
            }
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its programaId field is not nullable.");
                }
            }
            for (Directorprograma directorprogramaListOldDirectorprograma : directorprogramaListOld) {
                if (!directorprogramaListNew.contains(directorprogramaListOldDirectorprograma)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Directorprograma " + directorprogramaListOldDirectorprograma + " since its programaId field is not nullable.");
                }
            }
            for (Docente docenteListOldDocente : docenteListOld) {
                if (!docenteListNew.contains(docenteListOldDocente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Docente " + docenteListOldDocente + " since its programaId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (sedeIdNew != null) {
                sedeIdNew = em.getReference(sedeIdNew.getClass(), sedeIdNew.getId());
                programa.setSedeId(sedeIdNew);
            }
            if (facultadIdNew != null) {
                facultadIdNew = em.getReference(facultadIdNew.getClass(), facultadIdNew.getId());
                programa.setFacultadId(facultadIdNew);
            }
            List<Administrativo> attachedAdministrativoListNew = new ArrayList<Administrativo>();
            for (Administrativo administrativoListNewAdministrativoToAttach : administrativoListNew) {
                administrativoListNewAdministrativoToAttach = em.getReference(administrativoListNewAdministrativoToAttach.getClass(), administrativoListNewAdministrativoToAttach.getId());
                attachedAdministrativoListNew.add(administrativoListNewAdministrativoToAttach);
            }
            administrativoListNew = attachedAdministrativoListNew;
            programa.setAdministrativoList(administrativoListNew);
            List<Egresado> attachedEgresadoListNew = new ArrayList<Egresado>();
            for (Egresado egresadoListNewEgresadoToAttach : egresadoListNew) {
                egresadoListNewEgresadoToAttach = em.getReference(egresadoListNewEgresadoToAttach.getClass(), egresadoListNewEgresadoToAttach.getId());
                attachedEgresadoListNew.add(egresadoListNewEgresadoToAttach);
            }
            egresadoListNew = attachedEgresadoListNew;
            programa.setEgresadoList(egresadoListNew);
            List<Representante> attachedRepresentanteListNew = new ArrayList<Representante>();
            for (Representante representanteListNewRepresentanteToAttach : representanteListNew) {
                representanteListNewRepresentanteToAttach = em.getReference(representanteListNewRepresentanteToAttach.getClass(), representanteListNewRepresentanteToAttach.getId());
                attachedRepresentanteListNew.add(representanteListNewRepresentanteToAttach);
            }
            representanteListNew = attachedRepresentanteListNew;
            programa.setRepresentanteList(representanteListNew);
            List<Proceso> attachedProcesoListNew = new ArrayList<Proceso>();
            for (Proceso procesoListNewProcesoToAttach : procesoListNew) {
                procesoListNewProcesoToAttach = em.getReference(procesoListNewProcesoToAttach.getClass(), procesoListNewProcesoToAttach.getId());
                attachedProcesoListNew.add(procesoListNewProcesoToAttach);
            }
            procesoListNew = attachedProcesoListNew;
            programa.setProcesoList(procesoListNew);
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getId());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            programa.setEstudianteList(estudianteListNew);
            List<Directorprograma> attachedDirectorprogramaListNew = new ArrayList<Directorprograma>();
            for (Directorprograma directorprogramaListNewDirectorprogramaToAttach : directorprogramaListNew) {
                directorprogramaListNewDirectorprogramaToAttach = em.getReference(directorprogramaListNewDirectorprogramaToAttach.getClass(), directorprogramaListNewDirectorprogramaToAttach.getId());
                attachedDirectorprogramaListNew.add(directorprogramaListNewDirectorprogramaToAttach);
            }
            directorprogramaListNew = attachedDirectorprogramaListNew;
            programa.setDirectorprogramaList(directorprogramaListNew);
            List<Docente> attachedDocenteListNew = new ArrayList<Docente>();
            for (Docente docenteListNewDocenteToAttach : docenteListNew) {
                docenteListNewDocenteToAttach = em.getReference(docenteListNewDocenteToAttach.getClass(), docenteListNewDocenteToAttach.getId());
                attachedDocenteListNew.add(docenteListNewDocenteToAttach);
            }
            docenteListNew = attachedDocenteListNew;
            programa.setDocenteList(docenteListNew);
            programa = em.merge(programa);
            if (sedeIdOld != null && !sedeIdOld.equals(sedeIdNew)) {
                sedeIdOld.getProgramaList().remove(programa);
                sedeIdOld = em.merge(sedeIdOld);
            }
            if (sedeIdNew != null && !sedeIdNew.equals(sedeIdOld)) {
                sedeIdNew.getProgramaList().add(programa);
                sedeIdNew = em.merge(sedeIdNew);
            }
            if (facultadIdOld != null && !facultadIdOld.equals(facultadIdNew)) {
                facultadIdOld.getProgramaList().remove(programa);
                facultadIdOld = em.merge(facultadIdOld);
            }
            if (facultadIdNew != null && !facultadIdNew.equals(facultadIdOld)) {
                facultadIdNew.getProgramaList().add(programa);
                facultadIdNew = em.merge(facultadIdNew);
            }
            for (Administrativo administrativoListNewAdministrativo : administrativoListNew) {
                if (!administrativoListOld.contains(administrativoListNewAdministrativo)) {
                    Programa oldProgramaIdOfAdministrativoListNewAdministrativo = administrativoListNewAdministrativo.getProgramaId();
                    administrativoListNewAdministrativo.setProgramaId(programa);
                    administrativoListNewAdministrativo = em.merge(administrativoListNewAdministrativo);
                    if (oldProgramaIdOfAdministrativoListNewAdministrativo != null && !oldProgramaIdOfAdministrativoListNewAdministrativo.equals(programa)) {
                        oldProgramaIdOfAdministrativoListNewAdministrativo.getAdministrativoList().remove(administrativoListNewAdministrativo);
                        oldProgramaIdOfAdministrativoListNewAdministrativo = em.merge(oldProgramaIdOfAdministrativoListNewAdministrativo);
                    }
                }
            }
            for (Egresado egresadoListNewEgresado : egresadoListNew) {
                if (!egresadoListOld.contains(egresadoListNewEgresado)) {
                    Programa oldProgramaIdOfEgresadoListNewEgresado = egresadoListNewEgresado.getProgramaId();
                    egresadoListNewEgresado.setProgramaId(programa);
                    egresadoListNewEgresado = em.merge(egresadoListNewEgresado);
                    if (oldProgramaIdOfEgresadoListNewEgresado != null && !oldProgramaIdOfEgresadoListNewEgresado.equals(programa)) {
                        oldProgramaIdOfEgresadoListNewEgresado.getEgresadoList().remove(egresadoListNewEgresado);
                        oldProgramaIdOfEgresadoListNewEgresado = em.merge(oldProgramaIdOfEgresadoListNewEgresado);
                    }
                }
            }
            for (Representante representanteListOldRepresentante : representanteListOld) {
                if (!representanteListNew.contains(representanteListOldRepresentante)) {
                    representanteListOldRepresentante.setProgramaId(null);
                    representanteListOldRepresentante = em.merge(representanteListOldRepresentante);
                }
            }
            for (Representante representanteListNewRepresentante : representanteListNew) {
                if (!representanteListOld.contains(representanteListNewRepresentante)) {
                    Programa oldProgramaIdOfRepresentanteListNewRepresentante = representanteListNewRepresentante.getProgramaId();
                    representanteListNewRepresentante.setProgramaId(programa);
                    representanteListNewRepresentante = em.merge(representanteListNewRepresentante);
                    if (oldProgramaIdOfRepresentanteListNewRepresentante != null && !oldProgramaIdOfRepresentanteListNewRepresentante.equals(programa)) {
                        oldProgramaIdOfRepresentanteListNewRepresentante.getRepresentanteList().remove(representanteListNewRepresentante);
                        oldProgramaIdOfRepresentanteListNewRepresentante = em.merge(oldProgramaIdOfRepresentanteListNewRepresentante);
                    }
                }
            }
            for (Proceso procesoListNewProceso : procesoListNew) {
                if (!procesoListOld.contains(procesoListNewProceso)) {
                    Programa oldProgramaIdOfProcesoListNewProceso = procesoListNewProceso.getProgramaId();
                    procesoListNewProceso.setProgramaId(programa);
                    procesoListNewProceso = em.merge(procesoListNewProceso);
                    if (oldProgramaIdOfProcesoListNewProceso != null && !oldProgramaIdOfProcesoListNewProceso.equals(programa)) {
                        oldProgramaIdOfProcesoListNewProceso.getProcesoList().remove(procesoListNewProceso);
                        oldProgramaIdOfProcesoListNewProceso = em.merge(oldProgramaIdOfProcesoListNewProceso);
                    }
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    Programa oldProgramaIdOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getProgramaId();
                    estudianteListNewEstudiante.setProgramaId(programa);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldProgramaIdOfEstudianteListNewEstudiante != null && !oldProgramaIdOfEstudianteListNewEstudiante.equals(programa)) {
                        oldProgramaIdOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldProgramaIdOfEstudianteListNewEstudiante = em.merge(oldProgramaIdOfEstudianteListNewEstudiante);
                    }
                }
            }
            for (Directorprograma directorprogramaListNewDirectorprograma : directorprogramaListNew) {
                if (!directorprogramaListOld.contains(directorprogramaListNewDirectorprograma)) {
                    Programa oldProgramaIdOfDirectorprogramaListNewDirectorprograma = directorprogramaListNewDirectorprograma.getProgramaId();
                    directorprogramaListNewDirectorprograma.setProgramaId(programa);
                    directorprogramaListNewDirectorprograma = em.merge(directorprogramaListNewDirectorprograma);
                    if (oldProgramaIdOfDirectorprogramaListNewDirectorprograma != null && !oldProgramaIdOfDirectorprogramaListNewDirectorprograma.equals(programa)) {
                        oldProgramaIdOfDirectorprogramaListNewDirectorprograma.getDirectorprogramaList().remove(directorprogramaListNewDirectorprograma);
                        oldProgramaIdOfDirectorprogramaListNewDirectorprograma = em.merge(oldProgramaIdOfDirectorprogramaListNewDirectorprograma);
                    }
                }
            }
            for (Docente docenteListNewDocente : docenteListNew) {
                if (!docenteListOld.contains(docenteListNewDocente)) {
                    Programa oldProgramaIdOfDocenteListNewDocente = docenteListNewDocente.getProgramaId();
                    docenteListNewDocente.setProgramaId(programa);
                    docenteListNewDocente = em.merge(docenteListNewDocente);
                    if (oldProgramaIdOfDocenteListNewDocente != null && !oldProgramaIdOfDocenteListNewDocente.equals(programa)) {
                        oldProgramaIdOfDocenteListNewDocente.getDocenteList().remove(docenteListNewDocente);
                        oldProgramaIdOfDocenteListNewDocente = em.merge(oldProgramaIdOfDocenteListNewDocente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = programa.getId();
                if (findPrograma(id) == null) {
                    throw new NonexistentEntityException("The programa with id " + id + " no longer exists.");
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
            Programa programa;
            try {
                programa = em.getReference(Programa.class, id);
                programa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Administrativo> administrativoListOrphanCheck = programa.getAdministrativoList();
            for (Administrativo administrativoListOrphanCheckAdministrativo : administrativoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Programa (" + programa + ") cannot be destroyed since the Administrativo " + administrativoListOrphanCheckAdministrativo + " in its administrativoList field has a non-nullable programaId field.");
            }
            List<Egresado> egresadoListOrphanCheck = programa.getEgresadoList();
            for (Egresado egresadoListOrphanCheckEgresado : egresadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Programa (" + programa + ") cannot be destroyed since the Egresado " + egresadoListOrphanCheckEgresado + " in its egresadoList field has a non-nullable programaId field.");
            }
            List<Proceso> procesoListOrphanCheck = programa.getProcesoList();
            for (Proceso procesoListOrphanCheckProceso : procesoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Programa (" + programa + ") cannot be destroyed since the Proceso " + procesoListOrphanCheckProceso + " in its procesoList field has a non-nullable programaId field.");
            }
            List<Estudiante> estudianteListOrphanCheck = programa.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Programa (" + programa + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable programaId field.");
            }
            List<Directorprograma> directorprogramaListOrphanCheck = programa.getDirectorprogramaList();
            for (Directorprograma directorprogramaListOrphanCheckDirectorprograma : directorprogramaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Programa (" + programa + ") cannot be destroyed since the Directorprograma " + directorprogramaListOrphanCheckDirectorprograma + " in its directorprogramaList field has a non-nullable programaId field.");
            }
            List<Docente> docenteListOrphanCheck = programa.getDocenteList();
            for (Docente docenteListOrphanCheckDocente : docenteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Programa (" + programa + ") cannot be destroyed since the Docente " + docenteListOrphanCheckDocente + " in its docenteList field has a non-nullable programaId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Sede sedeId = programa.getSedeId();
            if (sedeId != null) {
                sedeId.getProgramaList().remove(programa);
                sedeId = em.merge(sedeId);
            }
            Facultad facultadId = programa.getFacultadId();
            if (facultadId != null) {
                facultadId.getProgramaList().remove(programa);
                facultadId = em.merge(facultadId);
            }
            List<Representante> representanteList = programa.getRepresentanteList();
            for (Representante representanteListRepresentante : representanteList) {
                representanteListRepresentante.setProgramaId(null);
                representanteListRepresentante = em.merge(representanteListRepresentante);
            }
            em.remove(programa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programa> findProgramaEntities() {
        return findProgramaEntities(true, -1, -1);
    }

    public List<Programa> findProgramaEntities(int maxResults, int firstResult) {
        return findProgramaEntities(false, maxResults, firstResult);
    }

    private List<Programa> findProgramaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Programa.class));
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

    public Programa findPrograma(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programa.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Programa> rt = cq.from(Programa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
