/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Asignacionencuesta;
import entity.Proceso;
import entity.Programa;
import entity.controller.ProcesoJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
 * @author Arturo González
 */
@WebServlet(name = "formController", urlPatterns = {"/formController"})
public class formController extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            if (request.getParameter("action").equals("asignarPonderacionFactorAIp")) {
                HttpSession session = request.getSession();
                sqlController conSql = new sqlController();

                int numRows = Integer.parseInt(request.getParameter("count"));
                int idproceso = 0;
                ResultSet rs = null;
                String bd = (String) session.getAttribute("bd");

                Proceso proceso = (Proceso) session.getAttribute("proceso");
                int idProceso = proceso.getId();

                session.setAttribute("idproceso", idproceso);

                int contador = 0;
                for (int i = 1; i <= numRows; i++) {
                    int ponderacion = Integer.parseInt(request.getParameter("ponderacion" + i));
                    contador = contador + ponderacion;
                }
                if (contador != 100) {
                    System.out.println("Distinto de 100");
                    session.setAttribute("auxAsignarF1", 0);
                } else {
                    System.out.println("igual a 100");
                    session.setAttribute("auxAsignarF1", 1);

                    if (session.getAttribute("auxAsignarF").equals(0)) {
                        for (int i = 1; i <= numRows; i++) {
                            String id = request.getParameter("id" + i);
                            String ponderacion = request.getParameter("ponderacion" + i);
                            String justificacion = request.getParameter("justificacion" + i);



                            conSql.UpdateSql("INSERT INTO `ponderacionfactor` (`id`, `ponderacion`, `justificacion`, `proceso_id`, `factor_id`) VALUES (NULL, '" + ponderacion + "', '" + justificacion + "', '" + idProceso + "', '" + id + "');", bd);

                            //  conSql.UpdateSql("UPDATE `ponderacionfactor` SET `ponderacion` = '" + ponderacion + "',`justificacion` = '" + justificacion + "' WHERE `ponderacionfactor`.`id` ='" + idPonderacion + "'", bd);
                        }

                        session.setAttribute("auxAsignarF", 1);
                        session.setAttribute("auxAsignarC1", 1);

                    } else {
                        for (int i = 1; i <= numRows; i++) {
                            String id = request.getParameter("id" + i);
                            String ponderacion = request.getParameter("ponderacion" + i);
                            String justificacion = request.getParameter("justificacion" + i);
                            int idPonderacion = 0;


                            rs = conSql.CargarSql("Select id from ponderacionfactor where ponderacionfactor.proceso_id = '" + idProceso + "' and ponderacionfactor.factor_id = '" + id + "'", bd);
                            try {
                                while (rs.next()) {
                                    idPonderacion = Integer.parseInt(rs.getString(1));
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                            }


                            conSql.UpdateSql("UPDATE `ponderacionfactor` SET `ponderacion` = '" + ponderacion + "',`justificacion` = '" + justificacion + "' WHERE `ponderacionfactor`.`id` ='" + idPonderacion + "'", bd);
                        }

                    }
                }

            } else if (request.getParameter(
                    "action").equals("asignarPonderacionCaracteristicaAIp")) {

                HttpSession session = request.getSession();
                sqlController conSql = new sqlController();

                int numRows = Integer.parseInt(request.getParameter("count"));
                int idproceso = 0;
                ResultSet rs = null;
                String bd = (String) session.getAttribute("bd");

                Proceso proceso = (Proceso) session.getAttribute("proceso");
                int idProceso = proceso.getId();

                session.setAttribute("idproceso", idproceso);

                if (session.getAttribute("auxAsignarC").equals(0)) {
                    System.out.println("count " + numRows);
                    for (int i = 1; i <= numRows; i++) {
                        String id = request.getParameter("id" + i);
                        String ponderacion = request.getParameter("ponderacion" + i);
                        String justificacion = request.getParameter("justificacion" + i);
                        int ponde = Integer.parseInt(ponderacion);



                        conSql.UpdateSql("INSERT INTO `ponderacioncaracteristica` (`id`, `nivelimportancia`, `ponderacion`, `justificacion`, `proceso_id`, `caracteristica_id`) VALUES (NULL, '" + ponderacion + "', '" + 0 + "', '" + justificacion + "', '" + idProceso + "', '" + id + "');", bd);



                        int suma = 0;
                        int ponfa = 0;
                    }
                    for (int i = 1; i <= numRows; i++) {
                        String id = request.getParameter("id" + i);
                        String ponderacion = request.getParameter("ponderacion" + i);
                        String justificacion = request.getParameter("justificacion" + i);
                        double ponde = Double.parseDouble(ponderacion);

                        double suma = 0;
                        double ponfa = 0;

                        ResultSet rs1 = conSql.CargarSql("SELECT SUM(ponderacioncaracteristica.nivelimportancia),ponderacionfactor.ponderacion from ponderacioncaracteristica inner join caracteristica on ponderacioncaracteristica.caracteristica_id = caracteristica.id inner join ponderacionfactor on caracteristica.factor_id = ponderacionfactor.factor_id WHERE ponderacioncaracteristica.proceso_id = '" + idProceso + "' and caracteristica.factor_id = (SELECT factor_id from caracteristica where caracteristica.id = '" + id + "')", bd);
                        try {
                            while (rs1.next()) {
                                String s = rs1.getString(1);
                                System.out.println("suma: " + s);
                                suma = Double.parseDouble(s);
                                String p = rs1.getString(2);
                                System.out.println("ponderacionF:" + p);
                                ponfa = Double.parseDouble(p);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        double a = (100 * ponde) / suma;
                        System.out.println("a> " + a);
                        double b = ((ponfa * a) / 100);

                        double r;


                        int decimalPlaces = 2;
                        BigDecimal bde = new BigDecimal(b);

// setScale is immutable
                        bde = bde.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
                        r = bde.doubleValue();

                        conSql.UpdateSql("UPDATE `ponderacioncaracteristica` SET `ponderacion` = '" + r + "' WHERE `ponderacioncaracteristica`.`proceso_id` = '" + idProceso + "' and `ponderacioncaracteristica`.`caracteristica_id` = '" + id + "'", bd);



                    }
                } else {
                    for (int i = 1; i <= numRows; i++) {
                        String id = request.getParameter("id" + i);
                        String ponderacion = request.getParameter("ponderacion" + i);
                        String ponderacion2 = request.getParameter("ponderacion2" + i);
                        String justificacion = request.getParameter("justificacion" + i);
                        int idPonderacion = 0;


                        rs = conSql.CargarSql("Select id from ponderacioncaracteristica where ponderacioncaracteristica.proceso_id = '" + idProceso + "' and ponderacioncaracteristica.caracteristica_id = '" + id + "'", bd);
                        try {
                            while (rs.next()) {
                                idPonderacion = Integer.parseInt(rs.getString(1));
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                        }


                        conSql.UpdateSql("UPDATE `ponderacioncaracteristica` SET `nivelimportancia` = '" + ponderacion + "',`ponderacion` = '" + ponderacion2 + "',`justificacion` = '" + justificacion + "' WHERE `ponderacioncaracteristica`.`id` = '" + idPonderacion + "'", bd);
                    }
                }
            } else if (request.getParameter(
                    "action").equals("cargarPonde")) {

                HttpSession session = request.getSession();
                sqlController conSql = new sqlController();


                String bd = (String) session.getAttribute("bd");

                Proceso proceso = (Proceso) session.getAttribute("proceso");
                int idProceso = proceso.getId();

                String id = request.getParameter("idc");
                String ponderacion = request.getParameter("ponderacion" + id);
                double ponde = Double.parseDouble(ponderacion);

                System.out.println(id);
                System.out.println(ponde);
                double suma = 0;
                double ponfa = 0;

                ResultSet rs1 = conSql.CargarSql("SELECT SUM(ponderacioncaracteristica.nivelimportancia),ponderacionfactor.ponderacion from ponderacioncaracteristica inner join caracteristica on ponderacioncaracteristica.caracteristica_id = caracteristica.id inner join ponderacionfactor on caracteristica.factor_id = ponderacionfactor.factor_id WHERE ponderacioncaracteristica.proceso_id = '" + idProceso + "' and caracteristica.factor_id = (SELECT factor_id from caracteristica where caracteristica.id = '" + id + "')", bd);
                try {
                    while (rs1.next()) {
                        String s = rs1.getString(1);
                        suma = Double.parseDouble(s);
                        String p = rs1.getString(2);
                        ponfa = Double.parseDouble(p);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                }

                double a = (100 * ponde) / suma;
                System.out.println("a> " + a);
                double b = ((ponfa * a) / 100);

                double r;


                int decimalPlaces = 2;
                BigDecimal bde = new BigDecimal(b);

// setScale is immutable
                bde = bde.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
                r = bde.doubleValue();
                System.out.println("r: " + r);
                session.setAttribute("pondec", r);
                session.setAttribute("idc", id);
                //  conSql.UpdateSql("UPDATE `ponderacioncaracteristica` SET `ponderacion` = '" + r + "' WHERE `ponderacioncaracteristica`.`proceso_id` = '" + idProceso + "' and `ponderacioncaracteristica`.`caracteristica_id` = '" + id + "'", bd);


            } else if (request.getParameter(
                    "action").equals("asignarEncuestasAIp")) {

                HttpSession session = request.getSession();
                Proceso proceso = (Proceso) session.getAttribute("proceso");
                Asignacionencuesta ae = new Asignacionencuesta();

                sqlController conSql = new sqlController();


                String idFuente = request.getParameter("fuente");

                int id = Integer.valueOf(idFuente);


                String bd = (String) session.getAttribute("bd");
                int idAsignacion;
                int aux = 0;

                if (session.getAttribute("aux_asignarE").equals(1)) {

                    String sql2 = "delete from `asignacionencuesta` where `proceso_id` = '" + proceso.getId() + "' and  `fuente_id` = '" + id + "'";
                    conSql.UpdateSql(sql2, bd);

                }

                ResultSet rs = null;
                String sql = "Select* from encuesta";
                System.out.println("hey");
                rs = conSql.CargarSql(sql, bd);
                try {
                    while (rs.next()) {

                        if (request.getParameter(rs.getString(2)).equals("1")) {
                            System.out.println("hey2");
                            String sql2 = "INSERT INTO `asignacionencuesta` (`id`, `proceso_id`, `fuente_id`, `encuesta_id`) VALUES (NULL, '" + proceso.getId() + "', '" + id + "', '" + rs.getString(1) + "')";
                            conSql.UpdateSql(sql2, bd);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (request.getParameter(
                    "action").equals("selectorAsignarEncuestasAI")) {
                HttpSession session = request.getSession();

                sqlController conSql = new sqlController();
                Proceso proceso = (Proceso) session.getAttribute("proceso");
                int idProceso = proceso.getId();
                String bd = (String) session.getAttribute("bd");

                Result rs2 = null;
                String sql2 = "Select* from encuesta";
                rs2 = conSql.CargarSql2(sql2, bd);
                if (rs2 != null) {
                    session.setAttribute("encuestas", rs2);
                }

                try {
                    String idFuente = request.getParameter("fuente");
                    int id = Integer.valueOf(idFuente);
                    Result rs = null;
                    String sql = "Select* from asignacionencuesta where proceso_id = " + idProceso + " and fuente_id = " + id;
                    System.out.println("id: " + id);
                    rs = conSql.CargarSql2(sql, bd);
                    if (rs.getRowCount() != 0) {
                        System.out.println("si hay asignacion de encuestas");
                        session.setAttribute("encuestasSeleccionadas", rs);
                        session.setAttribute("aux_asignarE", 1);
                    } else {
                        System.out.println("no hay asignacion de encuestas!!!!!");

                        session.setAttribute("aux_asignarE", 0);
                    }
                } catch (Error ex) {
                    //  Logger.getLogger(fontController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (request.getParameter(
                    "action").equals("asignarMuestraAIp")) {

                HttpSession session = request.getSession();
                Proceso proceso = (Proceso) session.getAttribute("proceso");
                Asignacionencuesta ae = new Asignacionencuesta();

                sqlController conSql = new sqlController();


                String idFuente = request.getParameter("fuente");

                int id = Integer.valueOf(idFuente);


                String bd = (String) session.getAttribute("bd");

                int idMuestra = (Integer) session.getAttribute("idMuestra");

                String tabla = null;
                String tabla1 = null;

                //Estatico
                if (id == 1) {

                    tabla = "muestraestudiante";
                    tabla1 = "estudiante";

                } else if (id == 2) {

                    tabla = "muestradocente";
                    tabla1 = "docente";
                }


                if (session.getAttribute("aux_asignarM").equals(1)) {
                    System.out.println("sisasasasasas");
                    String sql2 = "delete from `" + tabla + "` where `muestra_id` = " + idMuestra;
                    conSql.UpdateSql(sql2, bd);

                }

                ResultSet rs = null;
                String sql = "Select* from " + tabla1;


                rs = conSql.CargarSql(sql, bd);
                try {
                    while (rs.next()) {
                        if (request.getParameter(rs.getString(1)).equals("1")) {
                            String sql2 = "INSERT INTO `" + tabla + "` (`id`, `muestra_id`, `" + tabla1 + "_id`) VALUES (NULL, '" + idMuestra + "', '" + rs.getString(1) + "')";
                            conSql.UpdateSql(sql2, bd);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (request.getParameter(
                    "action").equals("selectorAsignarMuestraAI")) {
                HttpSession session = request.getSession();
                sqlController conSql = new sqlController();
                Proceso proceso = (Proceso) session.getAttribute("proceso");
                int idProceso = proceso.getId();
                String bd = (String) session.getAttribute("bd");

                String idFuente = request.getParameter("fuente");
                int id = Integer.valueOf(idFuente);

                int idMuestra = (Integer) session.getAttribute("idMuestra");

                String idP = request.getParameter("programas");
                String idS = request.getParameter("semestres");


                Result rs2 = null;
                String tabla = null;
                String tabla1 = null;
                String sql2 = null;


                int fil = 0;

                //Estatico
                if (id == 1) {

                    tabla = "muestraestudiante";
                    tabla1 = "estudiante";
                    if (!idP.equals("--") && !idS.equals("--")) {

                        sql2 = "Select " + tabla1 + ".id, persona.id, persona.nombre, persona.apellido from " + tabla1 + " inner join persona on " + tabla1 + ".persona_id = persona.id  where " + tabla1 + ".programa_id = " + idP + " and " + tabla1 + ".semestre = " + idS;
                        System.out.println(sql2);
                        fil = 1;
                    } else if (!idP.equals("--")) {

                        sql2 = "Select " + tabla1 + ".id, persona.id, persona.nombre, persona.apellido from " + tabla1 + " inner join persona on " + tabla1 + ".persona_id = persona.id  where " + tabla1 + ".programa_id = " + idP;
                        System.out.println(sql2);
                        fil = 1;
                    } else if (!idS.equals("--")) {

                        sql2 = "Select " + tabla1 + ".id, persona.id, persona.nombre, persona.apellido from " + tabla1 + " inner join persona on " + tabla1 + ".persona_id = persona.id  where " + tabla1 + ".semestre = " + idS;
                        System.out.println(sql2);
                        fil = 1;
                    }

                } else if (id == 2) {

                    tabla = "muestradocente";
                    tabla1 = "docente";
                }

                if (fil == 0) {
                    System.out.println("nott");
                    sql2 = "Select " + tabla1 + ".id, persona.id, persona.nombre, persona.apellido from " + tabla1 + " inner join persona on " + tabla1 + ".persona_id = persona.id";

                }

                rs2 = conSql.CargarSql2(sql2, bd);
                if (rs2 != null) {
                    session.setAttribute("muestras", rs2);
                }

//*                
                try {

                    Result rs = null;
                    String sql = "Select* from " + tabla + " where muestra_id = " + idMuestra;
                    rs = conSql.CargarSql2(sql, bd);
                    if (rs.getRowCount() != 0) {
                        System.out.println("si hay asignacion de muestras");
                        session.setAttribute("muestrasSeleccionadas", rs);
                        session.setAttribute("aux_asignarM", 1);
                    } else {
                        System.out.println("no hay asignacion de muestras!!!!!");
                        session.setAttribute("aux_asignarM", 0);
                    }
                } catch (Error ex) {
                    //  Logger.getLogger(fontController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (request.getParameter(
                    "action").equals("crearProcesoAIp")) {

                HttpSession session = request.getSession();
                sqlController conSql = new sqlController();
                Programa programa = new Programa();
                Proceso proceso = new Proceso();
                ProcesoJpaController conProceso = new ProcesoJpaController();


                if (session.getAttribute("aux_index2").equals(1)) {
                    System.out.println("aja1");
                    proceso = (Proceso) session.getAttribute("proceso");
                    int idProceso = proceso.getId();
                    String bd = (String) session.getAttribute("bd");
                    proceso.setDescripcion(request.getParameter("descripcion"));
                    try {
                        conProceso.edit(proceso);
                    } catch (entity.controller.exceptions.NonexistentEntityException ex) {
                        Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    session.setAttribute("proceso", proceso);
                } else {
                    proceso.setFechainicio("Proceso en Configuración.");
                    proceso.setDescripcion(request.getParameter("descripcion"));

                    programa = (Programa) session.getAttribute("programa");
                    proceso.setProgramaId(programa);

                    conProceso.create(proceso);

                    session.setAttribute("proceso", proceso);
                    session.setAttribute("aux_index2", 1);
                    session.setAttribute("aux2_index2", 1);
                    session.setAttribute("msjLogIn1", "Existe un Proceso en Ejecución!");
                    session.setAttribute("msjLogIn2", "Detalle del Proceso.");
                    session.setAttribute("estadoProceso", 0);

                    try {
                        String str = request.getSession().getServletContext().getRealPath("/scriptsSql/script.sql");
                        String str2 = request.getSession().getServletContext().getRealPath("/scriptsSql/script2.sql");
                        conSql.newDb(proceso, str, str2);
                    } catch (SQLException ex) {
                        // Logger.getLogger(fontController.class.getName()).log(Level.SEVERE, null, ex);
                    }


                    String nombreBd = programa.getNombre() + proceso.getId();

                    session.setAttribute("proceso", proceso);
                    session.setAttribute("bd", nombreBd);
                }



            } else if (request.getParameter(
                    "action").equals("CerrarProcesoAI")) {

                HttpSession session = request.getSession();
                Proceso p = (Proceso) session.getAttribute("proceso");
                ProcesoJpaController pc = new ProcesoJpaController();
                Date d = new Date();
                String date = String.valueOf(d);
                p.setFechacierre(date);
                try {
                    pc.edit(p);
                } catch (entity.controller.exceptions.NonexistentEntityException ex) {
                    Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                }

                session.setAttribute("aux_index2", 0);
                System.out.println("modificado");
                session.setAttribute("estadoProceso", 2);


            } else if (request.getParameter(
                    "action").equals("IniciarProcesoAI")) {

                HttpSession session = request.getSession();
                Proceso p = (Proceso) session.getAttribute("proceso");
                ProcesoJpaController pc = new ProcesoJpaController();
                Date d = new Date();
                String date = String.valueOf(d);
                p.setFechainicio(date);
                /*
                 * int f = (Integer) session.getAttribute("auxAsignarF");
                 *
                 * if (f == 0) { System.out.println("Debe asignar la podenracion
                 * de los factores."); session.setAttribute("aux_IniciarP", 0);
                 * } int c = (Integer) session.getAttribute("auxAsignarC");
                 * System.out.println("cccccccccccccccc" +c); if (c == 0) {
                 * System.out.println("Debe asignar la podenracion de los
                 * caracteristicas."); session.setAttribute("aux_IniciarP", 0);
                 * }
                 *
                 *
                 *
                 *
                 *
                 * //valida asig encuesta String bd = (String)
                 * session.getAttribute("bd"); sqlController conSql = new
                 * sqlController(); Proceso proceso = (Proceso)
                 * session.getAttribute("proceso"); int idProceso =
                 * proceso.getId(); int count = 0; int count2 = 0;
                 *
                 * ResultSet rs2 = conSql.CargarSql("Select id from fuente",
                 * bd); try { while (rs2.next()) { count2++; Result rs = null;
                 * String sql = "Select* from asignacionencuesta where
                 * proceso_id = " + idProceso + " and fuente_id = " +
                 * rs2.getString(1); rs = conSql.CargarSql2(sql, bd); if
                 * (rs.getRowCount() != 0) { count++; } } } catch (SQLException
                 * ex) {
                 * Logger.getLogger(formController.class.getName()).log(Level.SEVERE,
                 * null, ex); }
                 *
                 * int e = 0; System.out.println("()" + count + "-" + count2);
                 * if (count == count2) { e = 1;
                 *
                 * } else { System.out.println("Debe asignar las Encuestas.");
                 * session.setAttribute("aux_IniciarP", 0); }
                 *
                 *
                 *
                 *
                 * if (f != 0 && c != 0 && e != 0) {
                 */
                try {
                    pc.edit(p);
                } catch (entity.controller.exceptions.NonexistentEntityException ex) {
                    Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                }

                session.setAttribute("aux2_index2", 0);
                session.setAttribute("estadoProceso", 1);
            }
            //  }
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

        processRequest(request, response);
        /*
         * if
         * (request.getParameter("action").equals("asignarPonderacionFactorAIp"))
         * { HttpSession session = request.getSession(); sqlController conSql =
         * new sqlController();
         *
         * int numRows = Integer.parseInt(request.getParameter("count")); int
         * idproceso = 0; ResultSet rs = null; String bd = (String)
         * session.getAttribute("bd");
         *
         * Proceso proceso = (Proceso) session.getAttribute("proceso"); int
         * idProceso = proceso.getId();
         *
         * session.setAttribute("idproceso", idproceso);
         *
         * for (int i = 1; i <= numRows; i++) { String id =
         * request.getParameter("id" + i); String ponderacion =
         * request.getParameter("ponderacion" + i); String justificacion =
         * request.getParameter("justificacion" + i);
         *
         *
         * conSql.UpdateSql("INSERT INTO `ponderacionfactor` (`id`,
         * `ponderacion`, `justificacion`, `proceso_id`, `factor_id`) VALUES
         * (NULL, '" + ponderacion + "', '" + justificacion + "', '" + idProceso
         * + "', '" + id + "');", bd); }
         *
         *
         * }
         */
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
