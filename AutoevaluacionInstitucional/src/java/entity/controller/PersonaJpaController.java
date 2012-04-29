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
import entity.controller.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Oscar
 */
public class PersonaJpaController implements Serializable {

    public PersonaJpaController() {
    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Persona persona) throws PreexistingEntityException, Exception {
        if (persona.getAdministrativoList() == null) {
            persona.setAdministrativoList(new ArrayList<Administrativo>());
        }
        if (persona.getEstudianteList() == null) {
            persona.setEstudianteList(new ArrayList<Estudiante>());
        }
        if (persona.getDirectorprogramaList() == null) {
            persona.setDirectorprogramaList(new ArrayList<Directorprograma>());
        }
        if (persona.getEgresadoList() == null) {
            persona.setEgresadoList(new ArrayList<Egresado>());
        }
        if (persona.getRepresentanteList() == null) {
            persona.setRepresentanteList(new ArrayList<Representante>());
        }
        if (persona.getAgenciagubernamentalList() == null) {
            persona.setAgenciagubernamentalList(new ArrayList<Agenciagubernamental>());
        }
        if (persona.getEmpleadorList() == null) {
            persona.setEmpleadorList(new ArrayList<Empleador>());
        }
        if (persona.getDocenteList() == null) {
            persona.setDocenteList(new ArrayList<Docente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Administrativo> attachedAdministrativoList = new ArrayList<Administrativo>();
            for (Administrativo administrativoListAdministrativoToAttach : persona.getAdministrativoList()) {
                administrativoListAdministrativoToAttach = em.getReference(administrativoListAdministrativoToAttach.getClass(), administrativoListAdministrativoToAttach.getId());
                attachedAdministrativoList.add(administrativoListAdministrativoToAttach);
            }
            persona.setAdministrativoList(attachedAdministrativoList);
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : persona.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            persona.setEstudianteList(attachedEstudianteList);
            List<Directorprograma> attachedDirectorprogramaList = new ArrayList<Directorprograma>();
            for (Directorprograma directorprogramaListDirectorprogramaToAttach : persona.getDirectorprogramaList()) {
                directorprogramaListDirectorprogramaToAttach = em.getReference(directorprogramaListDirectorprogramaToAttach.getClass(), directorprogramaListDirectorprogramaToAttach.getId());
                attachedDirectorprogramaList.add(directorprogramaListDirectorprogramaToAttach);
            }
            persona.setDirectorprogramaList(attachedDirectorprogramaList);
            List<Egresado> attachedEgresadoList = new ArrayList<Egresado>();
            for (Egresado egresadoListEgresadoToAttach : persona.getEgresadoList()) {
                egresadoListEgresadoToAttach = em.getReference(egresadoListEgresadoToAttach.getClass(), egresadoListEgresadoToAttach.getId());
                attachedEgresadoList.add(egresadoListEgresadoToAttach);
            }
            persona.setEgresadoList(attachedEgresadoList);
            List<Representante> attachedRepresentanteList = new ArrayList<Representante>();
            for (Representante representanteListRepresentanteToAttach : persona.getRepresentanteList()) {
                representanteListRepresentanteToAttach = em.getReference(representanteListRepresentanteToAttach.getClass(), representanteListRepresentanteToAttach.getId());
                attachedRepresentanteList.add(representanteListRepresentanteToAttach);
            }
            persona.setRepresentanteList(attachedRepresentanteList);
            List<Agenciagubernamental> attachedAgenciagubernamentalList = new ArrayList<Agenciagubernamental>();
            for (Agenciagubernamental agenciagubernamentalListAgenciagubernamentalToAttach : persona.getAgenciagubernamentalList()) {
                agenciagubernamentalListAgenciagubernamentalToAttach = em.getReference(agenciagubernamentalListAgenciagubernamentalToAttach.getClass(), agenciagubernamentalListAgenciagubernamentalToAttach.getId());
                attachedAgenciagubernamentalList.add(agenciagubernamentalListAgenciagubernamentalToAttach);
            }
            persona.setAgenciagubernamentalList(attachedAgenciagubernamentalList);
            List<Empleador> attachedEmpleadorList = new ArrayList<Empleador>();
            for (Empleador empleadorListEmpleadorToAttach : persona.getEmpleadorList()) {
                empleadorListEmpleadorToAttach = em.getReference(empleadorListEmpleadorToAttach.getClass(), empleadorListEmpleadorToAttach.getId());
                attachedEmpleadorList.add(empleadorListEmpleadorToAttach);
            }
            persona.setEmpleadorList(attachedEmpleadorList);
            List<Docente> attachedDocenteList = new ArrayList<Docente>();
            for (Docente docenteListDocenteToAttach : persona.getDocenteList()) {
                docenteListDocenteToAttach = em.getReference(docenteListDocenteToAttach.getClass(), docenteListDocenteToAttach.getId());
                attachedDocenteList.add(docenteListDocenteToAttach);
            }
            persona.setDocenteList(attachedDocenteList);
            em.persist(persona);
            for (Administrativo administrativoListAdministrativo : persona.getAdministrativoList()) {
                Persona oldPersonaIdOfAdministrativoListAdministrativo = administrativoListAdministrativo.getPersonaId();
                administrativoListAdministrativo.setPersonaId(persona);
                administrativoListAdministrativo = em.merge(administrativoListAdministrativo);
                if (oldPersonaIdOfAdministrativoListAdministrativo != null) {
                    oldPersonaIdOfAdministrativoListAdministrativo.getAdministrativoList().remove(administrativoListAdministrativo);
                    oldPersonaIdOfAdministrativoListAdministrativo = em.merge(oldPersonaIdOfAdministrativoListAdministrativo);
                }
            }
            for (Estudiante estudianteListEstudiante : persona.getEstudianteList()) {
                Persona oldPersonaIdOfEstudianteListEstudiante = estudianteListEstudiante.getPersonaId();
                estudianteListEstudiante.setPersonaId(persona);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldPersonaIdOfEstudianteListEstudiante != null) {
                    oldPersonaIdOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldPersonaIdOfEstudianteListEstudiante = em.merge(oldPersonaIdOfEstudianteListEstudiante);
                }
            }
            for (Directorprograma directorprogramaListDirectorprograma : persona.getDirectorprogramaList()) {
                Persona oldPersonaIdOfDirectorprogramaListDirectorprograma = directorprogramaListDirectorprograma.getPersonaId();
                directorprogramaListDirectorprograma.setPersonaId(persona);
                directorprogramaListDirectorprograma = em.merge(directorprogramaListDirectorprograma);
                if (oldPersonaIdOfDirectorprogramaListDirectorprograma != null) {
                    oldPersonaIdOfDirectorprogramaListDirectorprograma.getDirectorprogramaList().remove(directorprogramaListDirectorprograma);
                    oldPersonaIdOfDirectorprogramaListDirectorprograma = em.merge(oldPersonaIdOfDirectorprogramaListDirectorprograma);
                }
            }
            for (Egresado egresadoListEgresado : persona.getEgresadoList()) {
                Persona oldPersonaIdOfEgresadoListEgresado = egresadoListEgresado.getPersonaId();
                egresadoListEgresado.setPersonaId(persona);
                egresadoListEgresado = em.merge(egresadoListEgresado);
                if (oldPersonaIdOfEgresadoListEgresado != null) {
                    oldPersonaIdOfEgresadoListEgresado.getEgresadoList().remove(egresadoListEgresado);
                    oldPersonaIdOfEgresadoListEgresado = em.merge(oldPersonaIdOfEgresadoListEgresado);
                }
            }
            for (Representante representanteListRepresentante : persona.getRepresentanteList()) {
                Persona oldPersonaIdOfRepresentanteListRepresentante = representanteListRepresentante.getPersonaId();
                representanteListRepresentante.setPersonaId(persona);
                representanteListRepresentante = em.merge(representanteListRepresentante);
                if (oldPersonaIdOfRepresentanteListRepresentante != null) {
                    oldPersonaIdOfRepresentanteListRepresentante.getRepresentanteList().remove(representanteListRepresentante);
                    oldPersonaIdOfRepresentanteListRepresentante = em.merge(oldPersonaIdOfRepresentanteListRepresentante);
                }
            }
            for (Agenciagubernamental agenciagubernamentalListAgenciagubernamental : persona.getAgenciagubernamentalList()) {
                Persona oldPersonaIdOfAgenciagubernamentalListAgenciagubernamental = agenciagubernamentalListAgenciagubernamental.getPersonaId();
                agenciagubernamentalListAgenciagubernamental.setPersonaId(persona);
                agenciagubernamentalListAgenciagubernamental = em.merge(agenciagubernamentalListAgenciagubernamental);
                if (oldPersonaIdOfAgenciagubernamentalListAgenciagubernamental != null) {
                    oldPersonaIdOfAgenciagubernamentalListAgenciagubernamental.getAgenciagubernamentalList().remove(agenciagubernamentalListAgenciagubernamental);
                    oldPersonaIdOfAgenciagubernamentalListAgenciagubernamental = em.merge(oldPersonaIdOfAgenciagubernamentalListAgenciagubernamental);
                }
            }
            for (Empleador empleadorListEmpleador : persona.getEmpleadorList()) {
                Persona oldPersonaIdOfEmpleadorListEmpleador = empleadorListEmpleador.getPersonaId();
                empleadorListEmpleador.setPersonaId(persona);
                empleadorListEmpleador = em.merge(empleadorListEmpleador);
                if (oldPersonaIdOfEmpleadorListEmpleador != null) {
                    oldPersonaIdOfEmpleadorListEmpleador.getEmpleadorList().remove(empleadorListEmpleador);
                    oldPersonaIdOfEmpleadorListEmpleador = em.merge(oldPersonaIdOfEmpleadorListEmpleador);
                }
            }
            for (Docente docenteListDocente : persona.getDocenteList()) {
                Persona oldPersonaIdOfDocenteListDocente = docenteListDocente.getPersonaId();
                docenteListDocente.setPersonaId(persona);
                docenteListDocente = em.merge(docenteListDocente);
                if (oldPersonaIdOfDocenteListDocente != null) {
                    oldPersonaIdOfDocenteListDocente.getDocenteList().remove(docenteListDocente);
                    oldPersonaIdOfDocenteListDocente = em.merge(oldPersonaIdOfDocenteListDocente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPersona(persona.getId()) != null) {
                throw new PreexistingEntityException("Persona " + persona + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Persona persona) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persistentPersona = em.find(Persona.class, persona.getId());
            List<Administrativo> administrativoListOld = persistentPersona.getAdministrativoList();
            List<Administrativo> administrativoListNew = persona.getAdministrativoList();
            List<Estudiante> estudianteListOld = persistentPersona.getEstudianteList();
            List<Estudiante> estudianteListNew = persona.getEstudianteList();
            List<Directorprograma> directorprogramaListOld = persistentPersona.getDirectorprogramaList();
            List<Directorprograma> directorprogramaListNew = persona.getDirectorprogramaList();
            List<Egresado> egresadoListOld = persistentPersona.getEgresadoList();
            List<Egresado> egresadoListNew = persona.getEgresadoList();
            List<Representante> representanteListOld = persistentPersona.getRepresentanteList();
            List<Representante> representanteListNew = persona.getRepresentanteList();
            List<Agenciagubernamental> agenciagubernamentalListOld = persistentPersona.getAgenciagubernamentalList();
            List<Agenciagubernamental> agenciagubernamentalListNew = persona.getAgenciagubernamentalList();
            List<Empleador> empleadorListOld = persistentPersona.getEmpleadorList();
            List<Empleador> empleadorListNew = persona.getEmpleadorList();
            List<Docente> docenteListOld = persistentPersona.getDocenteList();
            List<Docente> docenteListNew = persona.getDocenteList();
            List<String> illegalOrphanMessages = null;
            for (Administrativo administrativoListOldAdministrativo : administrativoListOld) {
                if (!administrativoListNew.contains(administrativoListOldAdministrativo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Administrativo " + administrativoListOldAdministrativo + " since its personaId field is not nullable.");
                }
            }
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its personaId field is not nullable.");
                }
            }
            for (Directorprograma directorprogramaListOldDirectorprograma : directorprogramaListOld) {
                if (!directorprogramaListNew.contains(directorprogramaListOldDirectorprograma)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Directorprograma " + directorprogramaListOldDirectorprograma + " since its personaId field is not nullable.");
                }
            }
            for (Egresado egresadoListOldEgresado : egresadoListOld) {
                if (!egresadoListNew.contains(egresadoListOldEgresado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Egresado " + egresadoListOldEgresado + " since its personaId field is not nullable.");
                }
            }
            for (Representante representanteListOldRepresentante : representanteListOld) {
                if (!representanteListNew.contains(representanteListOldRepresentante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Representante " + representanteListOldRepresentante + " since its personaId field is not nullable.");
                }
            }
            for (Agenciagubernamental agenciagubernamentalListOldAgenciagubernamental : agenciagubernamentalListOld) {
                if (!agenciagubernamentalListNew.contains(agenciagubernamentalListOldAgenciagubernamental)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Agenciagubernamental " + agenciagubernamentalListOldAgenciagubernamental + " since its personaId field is not nullable.");
                }
            }
            for (Empleador empleadorListOldEmpleador : empleadorListOld) {
                if (!empleadorListNew.contains(empleadorListOldEmpleador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empleador " + empleadorListOldEmpleador + " since its personaId field is not nullable.");
                }
            }
            for (Docente docenteListOldDocente : docenteListOld) {
                if (!docenteListNew.contains(docenteListOldDocente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Docente " + docenteListOldDocente + " since its personaId field is not nullable.");
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
            persona.setAdministrativoList(administrativoListNew);
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getId());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            persona.setEstudianteList(estudianteListNew);
            List<Directorprograma> attachedDirectorprogramaListNew = new ArrayList<Directorprograma>();
            for (Directorprograma directorprogramaListNewDirectorprogramaToAttach : directorprogramaListNew) {
                directorprogramaListNewDirectorprogramaToAttach = em.getReference(directorprogramaListNewDirectorprogramaToAttach.getClass(), directorprogramaListNewDirectorprogramaToAttach.getId());
                attachedDirectorprogramaListNew.add(directorprogramaListNewDirectorprogramaToAttach);
            }
            directorprogramaListNew = attachedDirectorprogramaListNew;
            persona.setDirectorprogramaList(directorprogramaListNew);
            List<Egresado> attachedEgresadoListNew = new ArrayList<Egresado>();
            for (Egresado egresadoListNewEgresadoToAttach : egresadoListNew) {
                egresadoListNewEgresadoToAttach = em.getReference(egresadoListNewEgresadoToAttach.getClass(), egresadoListNewEgresadoToAttach.getId());
                attachedEgresadoListNew.add(egresadoListNewEgresadoToAttach);
            }
            egresadoListNew = attachedEgresadoListNew;
            persona.setEgresadoList(egresadoListNew);
            List<Representante> attachedRepresentanteListNew = new ArrayList<Representante>();
            for (Representante representanteListNewRepresentanteToAttach : representanteListNew) {
                representanteListNewRepresentanteToAttach = em.getReference(representanteListNewRepresentanteToAttach.getClass(), representanteListNewRepresentanteToAttach.getId());
                attachedRepresentanteListNew.add(representanteListNewRepresentanteToAttach);
            }
            representanteListNew = attachedRepresentanteListNew;
            persona.setRepresentanteList(representanteListNew);
            List<Agenciagubernamental> attachedAgenciagubernamentalListNew = new ArrayList<Agenciagubernamental>();
            for (Agenciagubernamental agenciagubernamentalListNewAgenciagubernamentalToAttach : agenciagubernamentalListNew) {
                agenciagubernamentalListNewAgenciagubernamentalToAttach = em.getReference(agenciagubernamentalListNewAgenciagubernamentalToAttach.getClass(), agenciagubernamentalListNewAgenciagubernamentalToAttach.getId());
                attachedAgenciagubernamentalListNew.add(agenciagubernamentalListNewAgenciagubernamentalToAttach);
            }
            agenciagubernamentalListNew = attachedAgenciagubernamentalListNew;
            persona.setAgenciagubernamentalList(agenciagubernamentalListNew);
            List<Empleador> attachedEmpleadorListNew = new ArrayList<Empleador>();
            for (Empleador empleadorListNewEmpleadorToAttach : empleadorListNew) {
                empleadorListNewEmpleadorToAttach = em.getReference(empleadorListNewEmpleadorToAttach.getClass(), empleadorListNewEmpleadorToAttach.getId());
                attachedEmpleadorListNew.add(empleadorListNewEmpleadorToAttach);
            }
            empleadorListNew = attachedEmpleadorListNew;
            persona.setEmpleadorList(empleadorListNew);
            List<Docente> attachedDocenteListNew = new ArrayList<Docente>();
            for (Docente docenteListNewDocenteToAttach : docenteListNew) {
                docenteListNewDocenteToAttach = em.getReference(docenteListNewDocenteToAttach.getClass(), docenteListNewDocenteToAttach.getId());
                attachedDocenteListNew.add(docenteListNewDocenteToAttach);
            }
            docenteListNew = attachedDocenteListNew;
            persona.setDocenteList(docenteListNew);
            persona = em.merge(persona);
            for (Administrativo administrativoListNewAdministrativo : administrativoListNew) {
                if (!administrativoListOld.contains(administrativoListNewAdministrativo)) {
                    Persona oldPersonaIdOfAdministrativoListNewAdministrativo = administrativoListNewAdministrativo.getPersonaId();
                    administrativoListNewAdministrativo.setPersonaId(persona);
                    administrativoListNewAdministrativo = em.merge(administrativoListNewAdministrativo);
                    if (oldPersonaIdOfAdministrativoListNewAdministrativo != null && !oldPersonaIdOfAdministrativoListNewAdministrativo.equals(persona)) {
                        oldPersonaIdOfAdministrativoListNewAdministrativo.getAdministrativoList().remove(administrativoListNewAdministrativo);
                        oldPersonaIdOfAdministrativoListNewAdministrativo = em.merge(oldPersonaIdOfAdministrativoListNewAdministrativo);
                    }
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    Persona oldPersonaIdOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getPersonaId();
                    estudianteListNewEstudiante.setPersonaId(persona);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldPersonaIdOfEstudianteListNewEstudiante != null && !oldPersonaIdOfEstudianteListNewEstudiante.equals(persona)) {
                        oldPersonaIdOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldPersonaIdOfEstudianteListNewEstudiante = em.merge(oldPersonaIdOfEstudianteListNewEstudiante);
                    }
                }
            }
            for (Directorprograma directorprogramaListNewDirectorprograma : directorprogramaListNew) {
                if (!directorprogramaListOld.contains(directorprogramaListNewDirectorprograma)) {
                    Persona oldPersonaIdOfDirectorprogramaListNewDirectorprograma = directorprogramaListNewDirectorprograma.getPersonaId();
                    directorprogramaListNewDirectorprograma.setPersonaId(persona);
                    directorprogramaListNewDirectorprograma = em.merge(directorprogramaListNewDirectorprograma);
                    if (oldPersonaIdOfDirectorprogramaListNewDirectorprograma != null && !oldPersonaIdOfDirectorprogramaListNewDirectorprograma.equals(persona)) {
                        oldPersonaIdOfDirectorprogramaListNewDirectorprograma.getDirectorprogramaList().remove(directorprogramaListNewDirectorprograma);
                        oldPersonaIdOfDirectorprogramaListNewDirectorprograma = em.merge(oldPersonaIdOfDirectorprogramaListNewDirectorprograma);
                    }
                }
            }
            for (Egresado egresadoListNewEgresado : egresadoListNew) {
                if (!egresadoListOld.contains(egresadoListNewEgresado)) {
                    Persona oldPersonaIdOfEgresadoListNewEgresado = egresadoListNewEgresado.getPersonaId();
                    egresadoListNewEgresado.setPersonaId(persona);
                    egresadoListNewEgresado = em.merge(egresadoListNewEgresado);
                    if (oldPersonaIdOfEgresadoListNewEgresado != null && !oldPersonaIdOfEgresadoListNewEgresado.equals(persona)) {
                        oldPersonaIdOfEgresadoListNewEgresado.getEgresadoList().remove(egresadoListNewEgresado);
                        oldPersonaIdOfEgresadoListNewEgresado = em.merge(oldPersonaIdOfEgresadoListNewEgresado);
                    }
                }
            }
            for (Representante representanteListNewRepresentante : representanteListNew) {
                if (!representanteListOld.contains(representanteListNewRepresentante)) {
                    Persona oldPersonaIdOfRepresentanteListNewRepresentante = representanteListNewRepresentante.getPersonaId();
                    representanteListNewRepresentante.setPersonaId(persona);
                    representanteListNewRepresentante = em.merge(representanteListNewRepresentante);
                    if (oldPersonaIdOfRepresentanteListNewRepresentante != null && !oldPersonaIdOfRepresentanteListNewRepresentante.equals(persona)) {
                        oldPersonaIdOfRepresentanteListNewRepresentante.getRepresentanteList().remove(representanteListNewRepresentante);
                        oldPersonaIdOfRepresentanteListNewRepresentante = em.merge(oldPersonaIdOfRepresentanteListNewRepresentante);
                    }
                }
            }
            for (Agenciagubernamental agenciagubernamentalListNewAgenciagubernamental : agenciagubernamentalListNew) {
                if (!agenciagubernamentalListOld.contains(agenciagubernamentalListNewAgenciagubernamental)) {
                    Persona oldPersonaIdOfAgenciagubernamentalListNewAgenciagubernamental = agenciagubernamentalListNewAgenciagubernamental.getPersonaId();
                    agenciagubernamentalListNewAgenciagubernamental.setPersonaId(persona);
                    agenciagubernamentalListNewAgenciagubernamental = em.merge(agenciagubernamentalListNewAgenciagubernamental);
                    if (oldPersonaIdOfAgenciagubernamentalListNewAgenciagubernamental != null && !oldPersonaIdOfAgenciagubernamentalListNewAgenciagubernamental.equals(persona)) {
                        oldPersonaIdOfAgenciagubernamentalListNewAgenciagubernamental.getAgenciagubernamentalList().remove(agenciagubernamentalListNewAgenciagubernamental);
                        oldPersonaIdOfAgenciagubernamentalListNewAgenciagubernamental = em.merge(oldPersonaIdOfAgenciagubernamentalListNewAgenciagubernamental);
                    }
                }
            }
            for (Empleador empleadorListNewEmpleador : empleadorListNew) {
                if (!empleadorListOld.contains(empleadorListNewEmpleador)) {
                    Persona oldPersonaIdOfEmpleadorListNewEmpleador = empleadorListNewEmpleador.getPersonaId();
                    empleadorListNewEmpleador.setPersonaId(persona);
                    empleadorListNewEmpleador = em.merge(empleadorListNewEmpleador);
                    if (oldPersonaIdOfEmpleadorListNewEmpleador != null && !oldPersonaIdOfEmpleadorListNewEmpleador.equals(persona)) {
                        oldPersonaIdOfEmpleadorListNewEmpleador.getEmpleadorList().remove(empleadorListNewEmpleador);
                        oldPersonaIdOfEmpleadorListNewEmpleador = em.merge(oldPersonaIdOfEmpleadorListNewEmpleador);
                    }
                }
            }
            for (Docente docenteListNewDocente : docenteListNew) {
                if (!docenteListOld.contains(docenteListNewDocente)) {
                    Persona oldPersonaIdOfDocenteListNewDocente = docenteListNewDocente.getPersonaId();
                    docenteListNewDocente.setPersonaId(persona);
                    docenteListNewDocente = em.merge(docenteListNewDocente);
                    if (oldPersonaIdOfDocenteListNewDocente != null && !oldPersonaIdOfDocenteListNewDocente.equals(persona)) {
                        oldPersonaIdOfDocenteListNewDocente.getDocenteList().remove(docenteListNewDocente);
                        oldPersonaIdOfDocenteListNewDocente = em.merge(oldPersonaIdOfDocenteListNewDocente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = persona.getId();
                if (findPersona(id) == null) {
                    throw new NonexistentEntityException("The persona with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persona;
            try {
                persona = em.getReference(Persona.class, id);
                persona.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The persona with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Administrativo> administrativoListOrphanCheck = persona.getAdministrativoList();
            for (Administrativo administrativoListOrphanCheckAdministrativo : administrativoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Administrativo " + administrativoListOrphanCheckAdministrativo + " in its administrativoList field has a non-nullable personaId field.");
            }
            List<Estudiante> estudianteListOrphanCheck = persona.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable personaId field.");
            }
            List<Directorprograma> directorprogramaListOrphanCheck = persona.getDirectorprogramaList();
            for (Directorprograma directorprogramaListOrphanCheckDirectorprograma : directorprogramaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Directorprograma " + directorprogramaListOrphanCheckDirectorprograma + " in its directorprogramaList field has a non-nullable personaId field.");
            }
            List<Egresado> egresadoListOrphanCheck = persona.getEgresadoList();
            for (Egresado egresadoListOrphanCheckEgresado : egresadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Egresado " + egresadoListOrphanCheckEgresado + " in its egresadoList field has a non-nullable personaId field.");
            }
            List<Representante> representanteListOrphanCheck = persona.getRepresentanteList();
            for (Representante representanteListOrphanCheckRepresentante : representanteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Representante " + representanteListOrphanCheckRepresentante + " in its representanteList field has a non-nullable personaId field.");
            }
            List<Agenciagubernamental> agenciagubernamentalListOrphanCheck = persona.getAgenciagubernamentalList();
            for (Agenciagubernamental agenciagubernamentalListOrphanCheckAgenciagubernamental : agenciagubernamentalListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Agenciagubernamental " + agenciagubernamentalListOrphanCheckAgenciagubernamental + " in its agenciagubernamentalList field has a non-nullable personaId field.");
            }
            List<Empleador> empleadorListOrphanCheck = persona.getEmpleadorList();
            for (Empleador empleadorListOrphanCheckEmpleador : empleadorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Empleador " + empleadorListOrphanCheckEmpleador + " in its empleadorList field has a non-nullable personaId field.");
            }
            List<Docente> docenteListOrphanCheck = persona.getDocenteList();
            for (Docente docenteListOrphanCheckDocente : docenteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Docente " + docenteListOrphanCheckDocente + " in its docenteList field has a non-nullable personaId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(persona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Persona> findPersonaEntities() {
        return findPersonaEntities(true, -1, -1);
    }

    public List<Persona> findPersonaEntities(int maxResults, int firstResult) {
        return findPersonaEntities(false, maxResults, firstResult);
    }

    private List<Persona> findPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Persona.class));
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

    public Persona findPersona(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Persona.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Persona> rt = cq.from(Persona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
