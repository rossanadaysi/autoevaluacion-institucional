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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

                for (int i = 1; i <= numRows; i++) {
                    String id = request.getParameter("id" + i);
                    String ponderacion = request.getParameter("ponderacion" + i);
                    String justificacion = request.getParameter("justificacion" + i);


                    conSql.UpdateSql("INSERT INTO `ponderacionfactor` (`id`, `ponderacion`, `justificacion`, `proceso_id`, `factor_id`) VALUES (NULL, '" + ponderacion + "', '" + justificacion + "', '" + idProceso + "', '" + id + "');", bd);
                }


            } else if (request.getParameter("action").equals("asignarPonderacionCaracteristicaAIp")) {

                HttpSession session = request.getSession();
                sqlController conSql = new sqlController();

                int numRows = Integer.parseInt(request.getParameter("count"));
                int idproceso = 0;
                ResultSet rs = null;
                String bd = (String) session.getAttribute("bd");

                Proceso proceso = (Proceso) session.getAttribute("proceso");
                int idProceso = proceso.getId();

                session.setAttribute("idproceso", idproceso);

                for (int i = 1; i <= numRows; i++) {
                    String id = request.getParameter("id" + i);
                    String ponderacion = request.getParameter("ponderacion" + i);
                    String justificacion = request.getParameter("justificacion" + i);


                    conSql.UpdateSql("INSERT INTO `ponderacioncaracteristica` (`id`, `ponderacion`, `justificacion`, `proceso_id`, `caracteristica_id`) VALUES (NULL, '" + ponderacion + "', '" + justificacion + "', '" + idProceso + "', '" + id + "');", bd);

                }
            } else if (request.getParameter("action").equals("asignarEncuestasAIp")) {

                HttpSession session = request.getSession();
                Proceso proceso = (Proceso) session.getAttribute("proceso");
                Asignacionencuesta ae = new Asignacionencuesta();

                sqlController conSql = new sqlController();


                String idFuente = request.getParameter("fuente");

                int id = Integer.valueOf(idFuente);

                String bd = (String) session.getAttribute("bd");

                /*
                 * String sql = "Select* from fuente where id = " + id;
                 *
                 * ResultSet rs2 = conSql.CargarSql(sql, bd);
                 *
                 *
                 * Fuente fuente = (Fuente) rs2.getArray(0);
                 */

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

            } else if (request.getParameter("action").equals("crearProcesoAIp")) {

                HttpSession session = request.getSession();

                sqlController conSql = new sqlController();
                Programa programa = new Programa();
                Proceso proceso = new Proceso();
                ProcesoJpaController conProceso = new ProcesoJpaController();

                proceso.setFechainicio("Proceso en Configuración.");
                proceso.setDescripcion(request.getParameter("descripcion"));

                programa = (Programa) session.getAttribute("programa");
                proceso.setProgramaId(programa);

                conProceso.create(proceso);

                session.setAttribute("proceso", proceso);
                session.setAttribute("aux_index2", 1);
                session.setAttribute("aux2_index2", 1);

                try {
                    conSql.newDb(proceso);
                } catch (SQLException ex) {
                    // Logger.getLogger(fontController.class.getName()).log(Level.SEVERE, null, ex);
                }


                String nombreBd = programa.getNombre() + proceso.getId();

                session.setAttribute("proceso", proceso);
                session.setAttribute("bd", nombreBd);



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
