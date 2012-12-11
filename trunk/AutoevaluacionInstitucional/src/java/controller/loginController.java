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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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


        if (tp != null && !tp.equals("Administrador")) {

            try {

                Persona persona = conPersona.findPersona(un);

                if (persona != null) {
                    if (persona.getPassword().equals(pw)) {
                        String tipo = tp;
                        if (tipo.equals("Autoevaluacion Institucional") || tipo.equals("Comite Central") || tipo.equals("Comite Facultad")) {
                            List<Representante> representantes = persona.getRepresentanteList();
                            if (representantes != null && representantes.size() > 0) {
                                for (Representante r : representantes) {
                                    //int menu = 0;
                                    ///LOGIN AUTOEVALUACION INSTITUCIONALF
                                    if (r.getRol().equals("Comite Facultad") && tipo.equals("Comite Facultad")) {


                                        session.setAttribute("tipoLogin", "comitefacultad");
                                        System.out.println("Usuario de comite de facultad autorizado");
                                        session.setAttribute("auxAsignarF", 0);
                                        session.setAttribute("auxAsignarC", 0);
                                        session.setAttribute("aux_IniciarP", 0);
                                        session.setAttribute("auxAsignarC1", 0);
                                        session.setAttribute("auxInfoDocumental", 0);
                                        session.setAttribute("proActivo", 0);


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

                                        int aux = 3;
                                        Date d0 = null;
                                        Date d1 = null;
                                        Proceso elmasviejo = null;

                                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        try {
                                            d0 = dateFormat.parse("1000-01-01 15:18:19");
                                        } catch (ParseException ex) {
                                            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        if (listProceso.size() > 0) {
                                            for (Proceso proceso : listProceso) {
                                                if (proceso.getFechacierre() == null && proceso.getProgramaId().getId() == programa.getId()) {
                                                    session.setAttribute("proceso", proceso);
                                                    aux = 1;

                                                    session.setAttribute("proActivo", 1);
                                                    String nombreBd = programa.getNombre() + proceso.getId();
                                                    session.setAttribute("bd", nombreBd);
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
                                                    sql = "select indicador.id, indicador.nombre, numericadocumental.documento, numericadocumental.responsable, numericadocumental.medio, numericadocumental.lugar, numericadocumental.evaluacion, numericadocumental.accion from numericadocumental inner join indicador on numericadocumental.indicador_id = indicador.id inner join instrumentohasindicador on indicador.id = instrumentohasindicador.indicador_id where instrumentohasindicador.instrumento_id = 1 and numericadocumental.proceso_id = '" + proceso.getId() + "'";
                                                    rs2 = conSql.CargarSql2(sql, nombreBd);



                                                    if (rs2 != null) {
                                                        session.setAttribute("auxInfoDocumental", 1);
                                                    }

                                                    if (proceso.getFechainicio().equals("Proceso en Configuración.")) {
                                                        session.setAttribute("aux_index2", 1);
                                                        session.setAttribute("aux_IniciarP", 0);
                                                        out.println(1);
                                                    } else {
                                                        out.println(0);
                                                        session.setAttribute("aux_index2", 2);
                                                        session.setAttribute("aux_IniciarP", 1);
                                                        session.setAttribute("proActivo", 1);

                                                    }

                                                } else if (proceso.getFechacierre() != null) {
                                                    String fe = proceso.getFechacierre();
                                                    DateFormat lFormatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
                                                    try {
                                                        d1 = (Date) lFormatter.parse(fe);
                                                    } catch (ParseException ex) {
                                                        Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                    int results = d0.compareTo(d1);

                                                    if (results < 0) {
                                                        elmasviejo = proceso;
                                                    }
                                                }
                                            }

                                        } else {
                                            //out.println(1);
                                            System.out.println("No hay procesos");
                                            aux = 0;
                                            session.setAttribute("aux_index2", 0);
                                        }
                                        if (aux == 3) {
                                            //out.println(1);
                                            System.out.println("No hay procesos acitvos");
                                            session.setAttribute("aux_IniciarP", 2);
                                            session.setAttribute("aux_index2", 3);
                                            session.setAttribute("proceso", elmasviejo);
                                            System.out.println("FEcha Escojida: " + elmasviejo.getFechacierre());
                                            String nombreBd = programa.getNombre() + elmasviejo.getId();
                                            session.setAttribute("bd", nombreBd);
                                        }


                                    } else if (r.getRol().equals("Autoevaluacion Institucional") && tipo.equals("Autoevaluacion Institucional")) {

                                        out.println(0);
                                        session.setAttribute("tipoLogin", "autoevaluacionInstitucional");
                                        session.setAttribute("auxAsignarF", 0);
                                        session.setAttribute("auxAsignarC", 0);
                                        session.setAttribute("aux_IniciarP", 0);
                                        session.setAttribute("auxAsignarC1", 0);
                                        session.setAttribute("auxInfoDocumental", 0);
                                        session.setAttribute("proActivo", 0);


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

                                        int aux = 3;
                                        Date d0 = null;
                                        Date d1 = null;
                                        Proceso elmasviejo = null;

                                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        try {
                                            d0 = dateFormat.parse("1000-01-01 15:18:19");
                                        } catch (ParseException ex) {
                                            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        if (listProceso.size() > 0) {
                                            for (Proceso proceso : listProceso) {
                                                if (proceso.getFechacierre() == null && proceso.getProgramaId().getId() == programa.getId()) {
                                                    session.setAttribute("proceso", proceso);
                                                    aux = 1;

                                                    session.setAttribute("proActivo", 1);
                                                    String nombreBd = programa.getNombre() + proceso.getId();
                                                    session.setAttribute("bd", nombreBd);
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
                                                    sql = "select indicador.id, indicador.nombre, numericadocumental.documento, numericadocumental.responsable, numericadocumental.medio, numericadocumental.lugar, numericadocumental.evaluacion, numericadocumental.accion from numericadocumental inner join indicador on numericadocumental.indicador_id = indicador.id inner join instrumentohasindicador on indicador.id = instrumentohasindicador.indicador_id where instrumentohasindicador.instrumento_id = 1 and numericadocumental.proceso_id = '" + proceso.getId() + "'";
                                                    rs2 = conSql.CargarSql2(sql, nombreBd);



                                                    if (rs2 != null) {
                                                        session.setAttribute("auxInfoDocumental", 1);
                                                    }

                                                    if (proceso.getFechainicio().equals("Proceso en Configuración.")) {
                                                        session.setAttribute("aux_index2", 1);
                                                        session.setAttribute("aux_IniciarP", 0);
                                                        System.out.println("Proceso en configuración");
                                                    } else {
                                                        session.setAttribute("aux_index2", 2);
                                                        session.setAttribute("aux_IniciarP", 1);

                                                        session.setAttribute("proActivo", 1);

                                                        String idMuestra = "";
                                                        String sql2 = "Select id from muestra where proceso_id = " + idProceso;
                                                        ResultSet rs3 = conSql.CargarSql(sql2, nombreBd);
                                                        try {

                                                            while (rs3.next()) {
                                                                idMuestra = rs3.getString(1);
                                                                session.setAttribute("idMuestra", idMuestra);
                                                            }

                                                        } catch (SQLException ex) {
                                                            Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                                                        }


                                                        String sql1 = "select c1.total, c1.terminados, format((c1.terminados*100/c1.total),2) as porcentaje,(c1.total-c1.terminados) as faltantes, 100-format((c1.terminados*100/c1.total),2) as porFal "
                                                                + " from("
                                                                + " select ("
                                                                + " (select count(*) from muestraestudiante where muestraestudiante.muestra_id=" + idMuestra + ")+"
                                                                + " (select count(*) from muestradocente where muestradocente.muestra_id=" + idMuestra + ") +"
                                                                + " (select count(*) from muestraadministrativo where muestraadministrativo.muestra_id=" + idMuestra + ")+"
                                                                + " (select count(*) from muestraegresado where muestraegresado.muestra_id=" + idMuestra + ")+"
                                                                + " (select count(*) from muestradirector where muestradirector.muestra_id=" + idMuestra + ")+"
                                                                + " (select count(*) from muestraagencia where muestraagencia.muestra_id=" + idMuestra + ")+"
                                                                + " (select count(*) from muestraempleador where muestraempleador.muestra_id=" + idMuestra + ")) AS total "
                                                                + " ,(select count(`persona_id`) as terminados from encabezado where "
                                                                + " estado='terminado') as terminados"
                                                                + " ) AS c1";
                                                        ResultSet rs = conSql.CargarSql(sql1, nombreBd);

                                                        try {

                                                            while (rs.next()) {
                                                                String por = rs.getString(3);
                                                                session.setAttribute("porceEstadoProceso", por + "%");
                                                            }

                                                        } catch (SQLException ex) {
                                                            Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                                                        }

                                                    }

                                                } else if (proceso.getFechacierre() != null) {
                                                    String fe = proceso.getFechacierre();
                                                    DateFormat lFormatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
                                                    try {
                                                        d1 = (Date) lFormatter.parse(fe);
                                                    } catch (ParseException ex) {
                                                        Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                    int results = d0.compareTo(d1);

                                                    if (results < 0) {
                                                        elmasviejo = proceso;
                                                    }
                                                }
                                            }

                                        } else {
                                            System.out.println("No hay procesos");
                                            aux = 0;
                                            session.setAttribute("aux_index2", 0);
                                        }
                                        if (aux == 3) {
                                            System.out.println("No hay procesos acitvos");
                                            session.setAttribute("aux_IniciarP", 2);
                                            session.setAttribute("aux_index2", 3);
                                            session.setAttribute("proceso", elmasviejo);
                                            System.out.println("FEcha Escojida: " + elmasviejo.getFechacierre());
                                            String nombreBd = programa.getNombre() + elmasviejo.getId();
                                            session.setAttribute("bd", nombreBd);
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
                                                    + " WHERE persona.id = '" + persona.getId() + "'"
                                                    + " AND proceso.`FECHACIERRE` IS NULL"
                                                    + " AND proceso.fechainicio !='Proceso en Configuración.'"
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

                                if (tipo.equals("Docentes")) {
                                    List<Docente> docL = persona.getDocenteList();

                                    if (docL.size() > 0) {
                                        out.println(0);
                                        session.setAttribute("tipoLogin", "Fuente");
                                        session.setAttribute("listDocente", docL);
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
                                                String idFuenteDocente = "2";
                                                String sql2 = "SELECT encuesta.id , encuesta.nombre"
                                                        + " FROM encuesta"
                                                        + " INNER JOIN asignacionencuesta ON asignacionencuesta.ENCUESTA_ID = encuesta.ID"
                                                        + " INNER JOIN proceso ON asignacionencuesta.PROCESO_ID = proceso.ID"
                                                        + " INNER JOIN muestra ON asignacionencuesta.PROCESO_ID = muestra.PROCESO_ID"
                                                        + " INNER JOIN muestradocente ON muestra.ID = muestradocente.MUESTRA_ID"
                                                        + " INNER JOIN docente ON muestradocente.DOCENTE_ID = docente.ID"
                                                        + " INNER JOIN persona ON docente.PERSONA_ID = persona.ID"
                                                        + " WHERE persona.id = '" + persona.getId() + "'"
                                                        + " AND proceso.`FECHACIERRE` IS NULL"
                                                        + " AND proceso.fechainicio !='Proceso en Configuración.'"
                                                        + " AND asignacionencuesta.fuente_id=" + idFuenteDocente + ""
                                                        + " AND (asignacionencuesta.PROCESO_ID, persona.id, asignacionencuesta.ENCUESTA_ID, asignacionencuesta.FUENTE_ID) NOT IN "
                                                        + " (select encabezado.PROCESO_ID, encabezado.PERSONA_ID, encabezado.ENCUESTA_ID, encabezado.FUENTE_ID from encabezado where encabezado.estado ='terminado') "
                                                        + "";

                                                Result encuestasDisponibles = conSql.CargarSql2(sql2, nombreBd2);
                                                session.setAttribute("listaEncuestasDisponibles", encuestasDisponibles); //session--------------
                                                session.setAttribute("idfuente", idFuenteDocente); //session---------------------------------

                                            }
                                        }
                                    } else {
                                        out.println(1);
                                        System.out.println("Usuario No Posee Permisos Para Ingresas bajo ese perfil.");
                                        session.setAttribute("errorLogIn", "[Usuario No Posee Permisos Para Ingresas bajo ese perfil!]");
                                    }




                                } else {

                                    if (tipo.equals("Administrativos")) {

                                        List<Administrativo> admL = persona.getAdministrativoList();

                                        if (admL.size() > 0) {
                                            out.println(0);
                                            session.setAttribute("tipoLogin", "Fuente");
                                            session.setAttribute("listAdministrativo", admL);
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
                                                    String idFuenteAdministrativo = "3";
                                                    String sql2 = "SELECT encuesta.id , encuesta.nombre"
                                                            + " FROM encuesta"
                                                            + " INNER JOIN asignacionencuesta ON asignacionencuesta.ENCUESTA_ID = encuesta.ID"
                                                            + " INNER JOIN proceso ON asignacionencuesta.PROCESO_ID = proceso.ID"
                                                            + " INNER JOIN muestra ON asignacionencuesta.PROCESO_ID = muestra.PROCESO_ID"
                                                            + " INNER JOIN muestraadministrativo ON muestra.ID = muestraadministrativo.MUESTRA_ID"
                                                            + " INNER JOIN administrativo ON muestraadministrativo.ADMINISTRATIVO_ID = administrativo.ID"
                                                            + " INNER JOIN persona ON administrativo.PERSONA_ID = persona.ID"
                                                            + " WHERE persona.id = '" + persona.getId() + "'"
                                                            + " AND proceso.`FECHACIERRE` IS NULL"
                                                            + " AND proceso.fechainicio !='Proceso en Configuración.'"
                                                            + " AND asignacionencuesta.fuente_id=" + idFuenteAdministrativo + ""
                                                            + " AND (asignacionencuesta.PROCESO_ID, persona.id, asignacionencuesta.ENCUESTA_ID, asignacionencuesta.FUENTE_ID) NOT IN "
                                                            + " (select encabezado.PROCESO_ID, encabezado.PERSONA_ID, encabezado.ENCUESTA_ID, encabezado.FUENTE_ID from encabezado where encabezado.estado ='terminado') "
                                                            + "";

                                                    Result encuestasDisponibles = conSql.CargarSql2(sql2, nombreBd2);
                                                    session.setAttribute("listaEncuestasDisponibles", encuestasDisponibles); //session--------------
                                                    session.setAttribute("idfuente", idFuenteAdministrativo); //session---------------------------------

                                                }
                                            }
                                        } else {
                                            out.println(1);
                                            System.out.println("Usuario No Posee Permisos Para Ingresas bajo ese perfil.");
                                            session.setAttribute("errorLogIn", "[Usuario No Posee Permisos Para Ingresas bajo ese perfil!]");
                                        }





                                    } else {

                                        if (tipo.equals("Egresados")) {

                                            List<Egresado> egreL = persona.getEgresadoList();

                                            if (egreL.size() > 0) {
                                                out.println(0);
                                                session.setAttribute("tipoLogin", "Fuente");
                                                session.setAttribute("listEgresado", egreL);
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
                                                        String idFuenteEgresado = "5";
                                                        String sql2 = "SELECT encuesta.id , encuesta.nombre"
                                                                + " FROM encuesta"
                                                                + " INNER JOIN asignacionencuesta ON asignacionencuesta.ENCUESTA_ID = encuesta.ID"
                                                                + " INNER JOIN proceso ON asignacionencuesta.PROCESO_ID = proceso.ID"
                                                                + " INNER JOIN muestra ON asignacionencuesta.PROCESO_ID = muestra.PROCESO_ID"
                                                                + " INNER JOIN muestraegresado ON muestra.ID = muestraegresado.MUESTRA_ID"
                                                                + " INNER JOIN egresado ON muestraegresado.EGRESADO_ID = egresado.ID"
                                                                + " INNER JOIN persona ON egresado.PERSONA_ID = persona.ID"
                                                                + " WHERE persona.id = '" + persona.getId() + "'"
                                                                + " AND proceso.`FECHACIERRE` IS NULL"
                                                                + " AND proceso.fechainicio !='Proceso en Configuración.'"
                                                                + " AND asignacionencuesta.fuente_id=" + idFuenteEgresado + ""
                                                                + " AND (asignacionencuesta.PROCESO_ID, persona.id, asignacionencuesta.ENCUESTA_ID, asignacionencuesta.FUENTE_ID) NOT IN "
                                                                + " (select encabezado.PROCESO_ID, encabezado.PERSONA_ID, encabezado.ENCUESTA_ID, encabezado.FUENTE_ID from encabezado where encabezado.estado ='terminado') "
                                                                + "";

                                                        Result encuestasDisponibles = conSql.CargarSql2(sql2, nombreBd2);
                                                        session.setAttribute("listaEncuestasDisponibles", encuestasDisponibles); //session--------------
                                                        session.setAttribute("idfuente", idFuenteEgresado); //session---------------------------------

                                                    }
                                                }
                                            } else {
                                                out.println(1);
                                                System.out.println("Usuario No Posee Permisos Para Ingresas bajo ese perfil.");
                                                session.setAttribute("errorLogIn", "[Usuario No Posee Permisos Para Ingresas bajo ese perfil!]");
                                            }





                                        } else {

                                            if (tipo.equals("Directivos")) {

                                                List<Directorprograma> dirL = persona.getDirectorprogramaList();

                                                if (dirL.size() > 0) {
                                                    out.println(0);
                                                    session.setAttribute("tipoLogin", "Fuente");
                                                    session.setAttribute("listDirectivo", dirL);
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
                                                            String idFuenteDirectivo = "4";
                                                            String sql2 = "SELECT encuesta.id , encuesta.nombre"
                                                                    + " FROM encuesta"
                                                                    + " INNER JOIN asignacionencuesta ON asignacionencuesta.ENCUESTA_ID = encuesta.ID"
                                                                    + " INNER JOIN proceso ON asignacionencuesta.PROCESO_ID = proceso.ID"
                                                                    + " INNER JOIN muestra ON asignacionencuesta.PROCESO_ID = muestra.PROCESO_ID"
                                                                    + " INNER JOIN muestradirector ON muestra.ID = muestradirector.MUESTRA_ID"
                                                                    + " INNER JOIN directorprograma ON muestradirector.DIRECTORPROGRAMA_ID = directorprograma.ID"
                                                                    + " INNER JOIN persona ON directorprograma.PERSONA_ID = persona.ID"
                                                                    + " WHERE persona.id = '" + persona.getId() + "'"
                                                                    + " AND proceso.`FECHACIERRE` IS NULL"
                                                                    + " AND proceso.fechainicio !='Proceso en Configuración.'"
                                                                    + " AND asignacionencuesta.fuente_id=" + idFuenteDirectivo + ""
                                                                    + " AND (asignacionencuesta.PROCESO_ID, persona.id, asignacionencuesta.ENCUESTA_ID, asignacionencuesta.FUENTE_ID) NOT IN "
                                                                    + " (select encabezado.PROCESO_ID, encabezado.PERSONA_ID, encabezado.ENCUESTA_ID, encabezado.FUENTE_ID from encabezado where encabezado.estado ='terminado') "
                                                                    + "";

                                                            Result encuestasDisponibles = conSql.CargarSql2(sql2, nombreBd2);
                                                            session.setAttribute("listaEncuestasDisponibles", encuestasDisponibles); //session--------------
                                                            session.setAttribute("idfuente", idFuenteDirectivo); //session---------------------------------

                                                        }
                                                    }
                                                } else {
                                                    out.println(1);
                                                    System.out.println("Usuario No Posee Permisos Para Ingresas bajo ese perfil.");
                                                    session.setAttribute("errorLogIn", "[Usuario No Posee Permisos Para Ingresas bajo ese perfil!]");
                                                }





                                            } else {
                                                if (tipo.equals("Empleadores")) {

                                                    List<Empleador> empL = persona.getEmpleadorList();

                                                    if (empL.size() > 0) {
                                                        out.println(0);
                                                        session.setAttribute("tipoLogin", "Fuente");
                                                        session.setAttribute("listEmpleador", empL);
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
                                                                String idFuenteEmpleador = "6";
                                                                String sql2 = "SELECT encuesta.id , encuesta.nombre"
                                                                        + " FROM encuesta"
                                                                        + " INNER JOIN asignacionencuesta ON asignacionencuesta.ENCUESTA_ID = encuesta.ID"
                                                                        + " INNER JOIN proceso ON asignacionencuesta.PROCESO_ID = proceso.ID"
                                                                        + " INNER JOIN muestra ON asignacionencuesta.PROCESO_ID = muestra.PROCESO_ID"
                                                                        + " INNER JOIN muestraempleador ON muestra.ID = muestraempleador.MUESTRA_ID"
                                                                        + " INNER JOIN empleador ON muestraempleador.EMPLEADOR_ID = empleador.ID"
                                                                        + " INNER JOIN persona ON empleador.PERSONA_ID = persona.ID"
                                                                        + " WHERE persona.id = '" + persona.getId() + "'"
                                                                        + " AND proceso.`FECHACIERRE` IS NULL"
                                                                        + " AND proceso.fechainicio !='Proceso en Configuración.'"
                                                                        + " AND asignacionencuesta.fuente_id=" + idFuenteEmpleador + ""
                                                                        + " AND (asignacionencuesta.PROCESO_ID, persona.id, asignacionencuesta.ENCUESTA_ID, asignacionencuesta.FUENTE_ID) NOT IN "
                                                                        + " (select encabezado.PROCESO_ID, encabezado.PERSONA_ID, encabezado.ENCUESTA_ID, encabezado.FUENTE_ID from encabezado where encabezado.estado ='terminado') "
                                                                        + "";

                                                                Result encuestasDisponibles = conSql.CargarSql2(sql2, nombreBd2);
                                                                session.setAttribute("listaEncuestasDisponibles", encuestasDisponibles); //session--------------
                                                                session.setAttribute("idfuente", idFuenteEmpleador); //session---------------------------------

                                                            }
                                                        }
                                                    } else {
                                                        out.println(1);
                                                        System.out.println("Usuario No Posee Permisos Para Ingresas bajo ese perfil.");
                                                        session.setAttribute("errorLogIn", "[Usuario No Posee Permisos Para Ingresas bajo ese perfil!]");
                                                    }





                                                } else {

                                                    if (tipo.equals("Agencias Gubernamentales")) {

                                                        List<Agenciagubernamental> ageL = persona.getAgenciagubernamentalList();

                                                        if (ageL.size() > 0) {
                                                            out.println(0);
                                                            session.setAttribute("tipoLogin", "Fuente");
                                                            session.setAttribute("listAgencia", ageL);
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
                                                                    String idFuenteAgencia = "7";
                                                                    String sql2 = "SELECT encuesta.id , encuesta.nombre"
                                                                            + " FROM encuesta"
                                                                            + " INNER JOIN asignacionencuesta ON asignacionencuesta.ENCUESTA_ID = encuesta.ID"
                                                                            + " INNER JOIN proceso ON asignacionencuesta.PROCESO_ID = proceso.ID"
                                                                            + " INNER JOIN muestra ON asignacionencuesta.PROCESO_ID = muestra.PROCESO_ID"
                                                                            + " INNER JOIN muestraagencia ON muestra.ID = muestraagencia.MUESTRA_ID"
                                                                            + " INNER JOIN agenciagubernamental ON muestraagencia.AGENCIAGUBERNAMENTAL_ID = agenciagubernamental.ID"
                                                                            + " INNER JOIN persona ON agenciagubernamental.PERSONA_ID = persona.ID"
                                                                            + " WHERE persona.id = '" + persona.getId() + "'"
                                                                            + " AND proceso.`FECHACIERRE` IS NULL"
                                                                            + " AND proceso.fechainicio !='Proceso en Configuración.'"
                                                                            + " AND asignacionencuesta.fuente_id=" + idFuenteAgencia + ""
                                                                            + " AND (asignacionencuesta.PROCESO_ID, persona.id, asignacionencuesta.ENCUESTA_ID, asignacionencuesta.FUENTE_ID) NOT IN "
                                                                            + " (select encabezado.PROCESO_ID, encabezado.PERSONA_ID, encabezado.ENCUESTA_ID, encabezado.FUENTE_ID from encabezado where encabezado.estado ='terminado') "
                                                                            + "";

                                                                    Result encuestasDisponibles = conSql.CargarSql2(sql2, nombreBd2);
                                                                    session.setAttribute("listaEncuestasDisponibles", encuestasDisponibles); //session--------------
                                                                    session.setAttribute("idfuente", idFuenteAgencia); //session---------------------------------

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



                                            }


                                        }




                                    }
                                }
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
        } else {
            if (tp != null && tp.equals("Administrador")) {

                try {

                    Persona persona = conPersona.findPersona(un);

                    if (persona != null) {
                        if (persona.getPassword().equals(pw)) {
                            session.setAttribute("tipoLogin", "Administrador");
                            out.println(0);
                        } else {
                            out.println(1);
                        }
                    }
                } catch (Exception e) {
                    out.println(1);
                }
            }
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
