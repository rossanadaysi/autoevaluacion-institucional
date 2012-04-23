/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Proceso;
import entity.Programa;
import entity.controller.ProcesoJpaController;
import entity.controller.ProgramaJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;

/**
 *
 * @author Arturo González
 */
public class jsonController extends HttpServlet {

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
        HttpSession session = request.getSession();
        try {
            String aux3 = "";

            if (request.getParameter("ejecucion").equals("detalleProceso")) {

                ProgramaJpaController conPrograma = new ProgramaJpaController();
                ProcesoJpaController conProceso = new ProcesoJpaController();
                Programa programa = conPrograma.findPrograma(1);
                session.setAttribute("programa", programa);

                List<Proceso> listProceso = conProceso.findProcesoEntities();
                List<Proceso> listProceso2 = new ArrayList<Proceso>();

                int aux = 0;
                int i = 0;

                for (Proceso proceso : listProceso) {
                    i++;
                    if (proceso.getProgramaId().getId() == programa.getId()) {
                        if (proceso.getFechacierre() == null) {
                            session.setAttribute("proceso", proceso);
                            session.setAttribute("msjLogIn1", "Existe un Proceso en Ejecución!");
                            aux = 1;
                            session.setAttribute("aux_index2", aux);
                            // System.out.println("hay proceso en ejecucion");
                            String nombreBd = programa.getNombre() + proceso.getId();
                            session.setAttribute("bd", nombreBd);
                            listProceso2.add(proceso);
                        }
                    }
                    session.setAttribute("detailProceso", listProceso2);
                }
            } else if (request.getParameter("ejecucion").equals("listarPonderacionFactor")) {

                Result rs = null;
                Proceso proceso = (Proceso) session.getAttribute("proceso");
                int idProceso = proceso.getId();
                String bd = (String) session.getAttribute("bd");
                sqlController conSql = new sqlController();
                String aux4 = "";
                rs = conSql.CargarSql2("Select factor.`id`, factor.nombre, ponderacionfactor.ponderacion, ponderacionfactor.justificacion from ponderacionfactor inner join factor on ponderacionfactor.`factor_id` = factor.`id` where proceso_id = " + idProceso + "", bd);
                session.setAttribute("listPonderacionFactores", rs);

            } else if (request.getParameter("ejecucion").equals("listarPonderacionCaracteristica")) {

                Proceso proceso = (Proceso) session.getAttribute("proceso");
                int idProceso = proceso.getId();
                String bd = (String) session.getAttribute("bd");
                sqlController conSql = new sqlController();
                String aux4 = "";
                Result rs = conSql.CargarSql2("Select caracteristica.id,"
                        + " caracteristica.nombre, ponderacioncaracteristica.ponderacion,"
                        + " ponderacioncaracteristica.justificacion, ponderacioncaracteristica.nivelimportancia from"
                        + " ponderacioncaracteristica inner join caracteristica on"
                        + " ponderacioncaracteristica.`caracteristica_id` ="
                        + " caracteristica.`id` where proceso_id = " + idProceso + "", bd);
                session.setAttribute("listPonderacionCaracteristica", rs);

            }
            if (request.getParameter("ejecucion").equals("listarProcesos")) {

                ProgramaJpaController conPrograma = new ProgramaJpaController();
                ProcesoJpaController conProceso = new ProcesoJpaController();

                Programa programa = (Programa) session.getAttribute("programa");

                List<Proceso> listProceso = conProceso.findProcesoEntities();
                List<Proceso> listProceso2 = new ArrayList<Proceso>();


                int i = 0;

                System.out.println(listProceso.size());

                for (Proceso proceso : listProceso) {
                    i++;
                    int id1 = proceso.getProgramaId().getId();
                    int id2 = programa.getId();
                    if (id1 == id2) {
                        listProceso2.add(proceso);
                    }
                }

                session.setAttribute("listProceso", listProceso2);
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
