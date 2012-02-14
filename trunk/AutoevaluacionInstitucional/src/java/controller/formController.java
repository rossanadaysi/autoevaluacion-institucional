/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Proceso;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
