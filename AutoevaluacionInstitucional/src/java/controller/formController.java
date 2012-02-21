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
        response.setContentType("text/html;charset=UTF-8");
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
                if (session.getAttribute("auxAsignarF").equals(0)) {
                    for (int i = 1; i <= numRows; i++) {
                        String id = request.getParameter("id" + i);
                        String ponderacion = request.getParameter("ponderacion" + i);
                        String justificacion = request.getParameter("justificacion" + i);
                        int idPonderacion = 0;



                        conSql.UpdateSql("INSERT INTO `ponderacionfactor` (`id`, `ponderacion`, `justificacion`, `proceso_id`, `factor_id`) VALUES (NULL, '" + ponderacion + "', '" + justificacion + "', '" + idProceso + "', '" + id + "');", bd);



                        conSql.UpdateSql("UPDATE `ponderacionfactor` SET `ponderacion` = '" + ponderacion + "',`justificacion` = '" + justificacion + "' WHERE `ponderacionfactor`.`id` ='" + idPonderacion + "'", bd);
                    }
                    session.setAttribute("auxAsignarF", 1);
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
                    for (int i = 1; i <= numRows; i++) {
                        String id = request.getParameter("id" + i);
                        String ponderacion = request.getParameter("ponderacion" + i);
                        String justificacion = request.getParameter("justificacion" + i);


                        conSql.UpdateSql("INSERT INTO `ponderacioncaracteristica` (`id`, `ponderacion`, `justificacion`, `proceso_id`, `caracteristica_id`) VALUES (NULL, '" + ponderacion + "', '" + justificacion + "', '" + idProceso + "', '" + id + "');", bd);

                    }
                } else {
                    System.out.println("hola");
                    for (int i = 1; i <= numRows; i++) {
                        String id = request.getParameter("id" + i);
                        String ponderacion = request.getParameter("ponderacion" + i);
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


                        conSql.UpdateSql("UPDATE `ponderacioncaracteristica` SET `ponderacion` = '" + ponderacion + "',`justificacion` = '" + justificacion + "' WHERE `ponderacioncaracteristica`.`id` ='" + idPonderacion + "'", bd);
                    }
                }
            } else if (request.getParameter(
                    "action").equals("asignarEncuestasAIp")) {

                HttpSession session = request.getSession();
                Proceso proceso = (Proceso) session.getAttribute("proceso");
                Asignacionencuesta ae = new Asignacionencuesta();

                sqlController conSql = new sqlController();


                String idFuente = request.getParameter("fuente");

                int id = Integer.valueOf(idFuente);

                String bd = (String) session.getAttribute("bd");

                ResultSet rs = null;
                String sql = "Select* from encuesta";

                rs = conSql.CargarSql(sql, bd);
                try {
                    while (rs.next()) {
                        if (request.getParameter(rs.getString(2)).equals("1")) {
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
                        Result rs2 = null;
                        String sql2 = "Select* from encuesta";
                        rs2 = conSql.CargarSql2(sql2, bd);
                        if (rs2 != null) {
                            session.setAttribute("encuestas", rs2);
                        }
                        session.setAttribute("aux_asignarE", 0);
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
                    System.out.println("aja");
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

                    try {
                        conSql.newDb(proceso);
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


            } else if (request.getParameter(
                    "action").equals("IniciarProcesoAI")) {

                HttpSession session = request.getSession();
                Proceso p = (Proceso) session.getAttribute("proceso");
                ProcesoJpaController pc = new ProcesoJpaController();
                Date d = new Date();
                String date = String.valueOf(d);
                p.setFechainicio(date);
                try {
                    pc.edit(p);
                } catch (entity.controller.exceptions.NonexistentEntityException ex) {
                    Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                }

                session.setAttribute("aux2_index2", 0);
            }
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
