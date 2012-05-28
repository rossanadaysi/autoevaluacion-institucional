/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.*;
import entity.controller.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;

/**
 *
 * @author Arturo
 */
@WebServlet(name = "loginController", urlPatterns = {"/loginController"})
public class loginController extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here out.println("<html>");
             * out.println("<head>"); out.println("<title>Servlet
             * loginController</title>"); out.println("</head>");
             * out.println("<body>"); out.println("<h1>Servlet loginController
             * at " + request.getContextPath () + "</h1>");
             * out.println("</body>"); out.println("</html>");
             */
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String da = (String) request.getParameter("un");

        String un = (String) request.getParameter("un");
        String pw = (String) request.getParameter("pw");
        String tp = (String) request.getParameter("tp");


        HttpSession session = request.getSession();
        ProgramaJpaController conPrograma;
        PrivilegioJpaController conPivilegio = new PrivilegioJpaController();
        RepresentanteJpaController conRepresentante = new RepresentanteJpaController();
        PersonaJpaController conPersona = new PersonaJpaController();
        RepresentantehasprivilegioJpaController conReprePrivi = new RepresentantehasprivilegioJpaController();
        sqlController conSql = new sqlController();

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();



        try {

            Persona persona = conPersona.findPersona(un);

            System.out.println("Procesando..");
            if (persona != null) {
                if (persona.getPassword().equals(pw)) {
                    String tipo = tp;
                    if (tipo.equals("Autoevaluacion Institucional") || tipo.equals("Comite Central")) {

                        List<Representante> representantes = persona.getRepresentanteList();
                        if (representantes != null) {
                            for (Representante r : representantes) {
                                //int menu = 0;
                                ///LOGIN AUTOEVALUACION INSTITUCIONALF
                                if (r.getRol().equals("Autoevaluacion Institucional") && tipo.equals("Autoevaluacion Institucional")) {

                                    out.println(0);
                                    session.setAttribute("tipoLogin", "autoevaluacionInstitucional");
                                    System.out.println("Usuario de autoevaluacion institucional autorizado");
                                    session.setAttribute("auxAsignarF", 0);
                                    session.setAttribute("auxAsignarC", 0);
                                    session.setAttribute("aux_IniciarP", 0);
                                    session.setAttribute("auxAsignarC1", 0);
                                    session.setAttribute("auxInfoDocumental", 0);

                                    // menu = 1;
                                    //session.setAttribute("auxIndex2", menu);
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
                                            session.setAttribute("aux_IniciarP", 0);

                                            session.setAttribute("proceso", proceso);
                                            aux = 1;
                                            session.setAttribute("aux_index2", aux);
                                            System.out.println("hay proceso en ejecucion");
                                            session.setAttribute("msjLogIn1", "Existe un Proceso en Ejecución!");
                                            session.setAttribute("msjLogIn2", "Detalle del Proceso.");
                                            // session.setAttribute("msjLogIn2", "Se ha Encontrado Un Proceso de Autoevaluación Institucional en Ejecución!");
                                            String nombreBd = programa.getNombre() + proceso.getId();
                                            session.setAttribute("bd", nombreBd);
                                            int aux1;
                                            int idProceso = proceso.getId();

                                            Result rs2 = null;
                                            String sql = "Select factor.id, ponderacion, justificacion, proceso_id, factor_id, nombre from ponderacionfactor inner join factor on ponderacionfactor.factor_id = factor.id where proceso_id = " + idProceso + "";
                                            rs2 = conSql.CargarSql2(sql, nombreBd);

                                            if (rs2.getRowCount() > 0) {
                                                session.setAttribute("auxAsignarF", 1);
                                            }

                                            rs2 = null;
                                            sql = "Select caracteristica.id, ponderacion, justificacion, proceso_id, caracteristica_id, nombre from ponderacioncaracteristica inner join caracteristica on ponderacioncaracteristica.caracteristica_id = caracteristica.id where proceso_id = " + idProceso + "";
                                            rs2 = conSql.CargarSql2(sql, nombreBd);

                                            if (rs2.getRowCount() > 0) {
                                                session.setAttribute("auxAsignarC", 1);
                                            }


                                            rs2 = null;

                                            //Cambiar proceso
                                            sql = "select indicador.id, indicador.nombre, numericadocumental.evaluacion, numericadocumental.nombre, numericadocumental.accion, numericadocumental.responsable from numericadocumental inner join indicador on numericadocumental.indicador_id = indicador.id inner join instrumentohasindicador on indicador.id = instrumentohasindicador.indicador_id where instrumentohasindicador.instrumento_id = 1 and numericadocumental.proceso_id = '" + proceso.getId() + "'";
                                            rs2 = conSql.CargarSql2(sql, nombreBd);



                                            if (rs2 != null) {
                                                session.setAttribute("auxInfoDocumental", 1);
                                            }

                                            if (proceso.getFechainicio().equals("Proceso en Configuración.")) {
                                                aux1 = 1;
                                                session.setAttribute("aux2_index2", aux1);
                                                session.setAttribute("aux_IniciarP", 0);
                                            }

                                            ResultSet rs3 = conSql.CargarSql("select id from proceso where id = " + proceso.getId() + " and fechainicio = 'Proceso en Configuración.'", nombreBd);
                                            try {
                                                if (rs3.next()) {
                                                    session.setAttribute("aux_IniciarP", 0);
                                                    System.out.println("Proceso en configuración.");
                                                }
                                            } catch (SQLException ex) {
                                                Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    }
                                    if (aux == 0) {
                                        session.setAttribute("msjLogIn1", "No Existe Proceso en Ejecución!");
                                        session.setAttribute("msjLogIn2", "Procesos Anteriores.");
                                        System.out.println("No hay proceso en ejecucion");
                                        session.setAttribute("aux_index2", 0);
                                    }


                                } else if (r.getRol().equals("Comite Central") && tipo.equals("Comite Central")) {
                                    out.println(0);
                                    session.setAttribute("tipoLogin", "Comite Central");
                                    session.setAttribute("representante", r);
                                    System.out.println("usuario de comite central autorizado.");
                                } else {
                                    out.println(1);
                                    System.out.println("Usuario No Posee Permisos Para Ingresas bajo ese perfil.");
                                    session.setAttribute("errorLogIn", "[Usuario No Posee Permisos Para Ingresas bajo ese perfil!]");

                                }
                            }
                        } else {
                            out.println(1);
                            System.out.println("Usuario No Posee Permisos Para Ingresas bajo ese perfil.");
                            session.setAttribute("errorLogIn", "[Usuario No Posee Permisos Para Ingresas bajo ese perfil!]");

                        }
                    } else {
                        if (tipo.equals("Estudiantes")) {

                            List<Estudiante> est = persona.getEstudianteList();
                            if (est.size() > 0) {
                                out.println(0);
                                session.setAttribute("tipoLogin", "Fuente");
                                session.setAttribute("listEstudiante", est);
                                session.setAttribute("persona", persona);

                                ProgramaJpaController conPrograma2 = new ProgramaJpaController();
                                ProcesoJpaController conProceso2 = new ProcesoJpaController();
                                Programa programa2 = conPrograma2.findPrograma(1);
                                session.setAttribute("programa2", programa2);


                                List<Proceso> listProceso2 = conProceso2.findProcesoEntities();

                                int aux = 0;

                                for (Proceso proceso2 : listProceso2) {
                                    if (proceso2.getFechacierre() == null && proceso2.getProgramaId().getId() == programa2.getId()) {
                                        session.setAttribute("proceso", proceso2); // session------------------------------
                                        String nombreBd2 = programa2.getNombre() + proceso2.getId();
                                        session.setAttribute("bd", nombreBd2); //session------------------------------------
                                        String idFuenteEstudiante = "1";
                                        String sql2 = "SELECT encuesta.id , encuesta.nombre"
                                                + " FROM encuesta"
                                                + " INNER JOIN asignacionencuesta ON asignacionencuesta.ENCUESTA_ID = encuesta.ID"
                                                + " INNER JOIN proceso ON asignacionencuesta.PROCESO_ID = proceso.ID"
                                                + " INNER JOIN muestra ON asignacionencuesta.PROCESO_ID = muestra.PROCESO_ID"
                                                + " INNER JOIN muestraestudiante ON muestra.ID = muestraestudiante.MUESTRA_ID"
                                                + " INNER JOIN estudiante ON muestraestudiante.ESTUDIANTE_ID = estudiante.ID"
                                                + " INNER JOIN persona ON estudiante.PERSONA_ID = persona.ID"
                                                + " WHERE persona.id = " + persona.getId() + ""
                                                + " AND proceso.`FECHACIERRE` IS NULL"
                                                + " AND asignacionencuesta.fuente_id=" + idFuenteEstudiante + ""
                                                + " AND (asignacionencuesta.PROCESO_ID, persona.id, asignacionencuesta.ENCUESTA_ID, asignacionencuesta.FUENTE_ID) NOT IN "
                                                + " (select encabezado.PROCESO_ID, encabezado.PERSONA_ID, encabezado.ENCUESTA_ID, encabezado.FUENTE_ID from encabezado where encabezado.estado ='terminado') "
                                                + "";
                                        
                                        Result encuestasDisponibles = conSql.CargarSql2(sql2, nombreBd2);
                                        session.setAttribute("listaEncuestasDisponibles", encuestasDisponibles); //session--------------
                                        session.setAttribute("idfuente", idFuenteEstudiante); //session---------------------------------

                                    }
                                }
                            } else {
                                out.println(1);
                                System.out.println("Usuario No Posee Permisos Para Ingresas bajo ese perfil.");
                                session.setAttribute("errorLogIn", "[Usuario No Posee Permisos Para Ingresas bajo ese perfil!]");
                            }



                        } else {
                            out.println(1);
                            System.out.println("Usuario No Posee Permisos Para Ingresas bajo ese perfil.");
                            session.setAttribute("errorLogIn", "[Usuario No Posee Permisos Para Ingresas bajo ese perfil!]");
                        }




                    }
                } else {
                    out.println(2);
                    System.out.println("Contraseña Incorrecta");
                    session.setAttribute("errorLogIn", "[Contraseña Incorrecta!]");

                }
            } else {
                out.println(3);
                System.out.println("Usuario No Existe");
                session.setAttribute("errorLogIn", "[Usuario No Registrado!]");

            }
        } catch (java.lang.NumberFormatException e) {
            out.println(4);
        }





    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
