/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.logIn;

import controller.sqlController;
import entity.Persona;
import entity.Proceso;
import entity.Programa;
import entity.Representante;
import entity.controller.PersonaJpaController;
import entity.controller.PrivilegioJpaController;
import entity.controller.ProcesoJpaController;
import entity.controller.ProgramaJpaController;
import entity.controller.RepresentanteJpaController;
import entity.controller.RepresentantehasprivilegioJpaController;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Action;

/**
 *
 * @author vanesa
 */
public class logIn implements Action {

    public String procesar(HttpServletRequest request)
            throws IOException, ServletException {

        String url = "/WEB-INF/vista/autoevaluacionInstitucional/index2.jsp";
        HttpSession session = request.getSession();
        ProgramaJpaController conPrograma = new ProgramaJpaController();
        PrivilegioJpaController conPivilegio = new PrivilegioJpaController();
        RepresentanteJpaController conRepresentante = new RepresentanteJpaController();
        PersonaJpaController conPersona = new PersonaJpaController();
        RepresentantehasprivilegioJpaController conReprePrivi = new RepresentantehasprivilegioJpaController();
        sqlController conSql = new sqlController();

        Persona persona = new Persona();
        persona = conPersona.findPersona(request.getParameter("usuario"));
        System.out.println("Procesando..");
        if (persona != null) {
            if (persona.getPassword().equals(request.getParameter("password"))) {
                String tipo = request.getParameter("tipo");
                if (tipo.equals("Autoevaluacion Institucional") || tipo.equals("Comite Central")) {
                    List<Representante> representantes = persona.getRepresentanteList();
                    if (representantes != null) {
                        for (Representante r : representantes) {
                            int menu = 0;
                            ///LOGIN AUTOEVALUACION INSTITUCIONALF
                            if (r.getRol().equals("Autoevaluacion Institucional") && tipo.equals("Autoevaluacion Institucional")) {
                                session.setAttribute("tipoLogin","autoevaluacionInstitucional");
                                System.out.println("Usuario de autoevaluacion institucional autorizado");
                                System.out.println("seaflkaslfaksdlk" + session.getAttribute("tipoLogin"));
                                menu = 1;
                                session.setAttribute("aux", menu);
                                session.setAttribute("mensaje", "Autoevaluación Institucional");
                                session.setAttribute("representante", r);
                                session.setAttribute("page", "/WEB-INF/vista/autoevaluacionInstitucional/proceso/detalle.jsp");

                                conPrograma = new ProgramaJpaController();
                                ProcesoJpaController conProceso = new ProcesoJpaController();
                                Programa programa = conPrograma.findPrograma(1);
                                session.setAttribute("programa", programa);

                                List<Proceso> listProceso = conProceso.findProcesoEntities();

                                int aux = 0;

                                for (Proceso proceso : listProceso) {
                                    if (proceso.getFechacierre() == null && proceso.getProgramaId().getId() == programa.getId()) {
                                        session.setAttribute("proceso", proceso);
                                        aux = 1;
                                        session.setAttribute("aux", aux);
                                        System.out.println("hay proceso en ejecucion");
                                        session.setAttribute("msjLogIn1", "Existe un Proceso en Ejecución!");
                                        // session.setAttribute("msjLogIn2", "Se ha Encontrado Un Proceso de Autoevaluación Institucional en Ejecución!");
                                        String nombreBd = programa.getNombre() + proceso.getId();
                                        session.setAttribute("bd", nombreBd);
                                    }
                                }
                                if (aux == 0) {
                                    System.out.println("No hay proceso en ejecucion");
                                    session.setAttribute("msjLogIn", "No Se ha Encontrado Proceso en Ejecución!");
                                    session.setAttribute("aux", 0);
                                }


                            } else if (r.getRol().equals("Comite Central") && tipo.equals("Comite Central")) {
                                System.out.println("usuario de comite central autorizado.");
                                session.setAttribute("tipoLogin", "comiteCentral");
                            } else {
                                System.out.println("Usuario No Posee Permisos Para Ingresas bajo ese perfil.");
                                session.setAttribute("errorLogIn", "[Usuario No Posee Permisos Para Ingresas bajo ese perfil!]");
                                url = "index.jsp";
                            }
                        }
                    } else {
                        System.out.println("Usuario No Posee Permisos Para Ingresas bajo ese perfil.");
                        session.setAttribute("errorLogIn", "[Usuario No Posee Permisos Para Ingresas bajo ese perfil!]");
                        url = "index.jsp";
                    }
                } else {
                    System.out.println("Usuario No Posee Permisos Para Ingresas bajo ese perfil.");
                    session.setAttribute("errorLogIn", "[Usuario No Posee Permisos Para Ingresas bajo ese perfil!]");
                    url = "index.jsp";
                }
            } else {
                System.out.println("Contraseña Incorrecta");
                session.setAttribute("errorLogIn", "[Contraseña Incorrecta!]");
                url = "index.jsp";
            }
        } else {
            System.out.println("Usuario No Existe");
            session.setAttribute("errorLogIn", "[Usuario No Registrado!]");
            url = "index.jsp";
        }
        return url;
    }
}
