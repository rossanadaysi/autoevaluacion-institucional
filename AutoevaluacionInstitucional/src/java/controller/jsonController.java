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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

            if (request.getParameter("ejecucion").equals("indexAI")) {

                ProgramaJpaController conPrograma = new ProgramaJpaController();
                ProcesoJpaController conProceso = new ProcesoJpaController();
                Programa programa = conPrograma.findPrograma(1);
                session.setAttribute("programa", programa);

                List<Proceso> listProceso = conProceso.findProcesoEntities();

                int aux = 0;
                int cont = listProceso.size();
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

                            try {

                                out.println(
                                        "[ "
                                        + "{"
                                        + " \"id\": \"" + proceso.getId() + "\" ,"
                                        + " \"fechaInicio\": \"" + proceso.getFechainicio() + "\" ,"
                                        + " \"fechaCierre\": \"" + proceso.getFechacierre() + "\" ,"
                                        + " \"descripcion\": \"" + proceso.getDescripcion() + "\" ,"
                                        + " \"programa\": \"" + proceso.getProgramaId().getNombre() + "\""
                                        + "}"
                                        + "]");
                            } finally {
                                out.close();
                            }


                        }
                    }
                }




            } else if (request.getParameter("ejecucion").equals("listarPonderacionFactor")) {
                ResultSet rs = null;
                Proceso proceso = (Proceso) session.getAttribute("proceso");
                int idProceso = proceso.getId();
                String bd = (String) session.getAttribute("bd");
                sqlController conSql = new sqlController();
                String aux4 = "";
                rs = conSql.CargarSql("Select factor.`id`, factor.nombre, ponderacionfactor.ponderacion, ponderacionfactor.justificacion from ponderacionfactor inner join factor on ponderacionfactor.`factor_id` = factor.`id` where proceso_id = " + idProceso + "", bd);
                try {
                    while (rs.next()) {

                        if (rs.isLast()) {
                            String aux5 = ""
                                    + "{"
                                    + " \"id\": \"" + rs.getString(1) + "\" ,"
                                    + " \"factor\": \"" + rs.getString(2) + "\" ,"
                                    + " \"ponderacion\": \"" + rs.getString(3) + "\" ,"
                                    + " \"justificacion\": \"" + rs.getString(4) + "\" "
                                    + "}"
                                    + "";

                            aux4 = aux4 + aux5;

                        } else {
                            String aux5 = ""
                                    + "{"
                                    + " \"id\": \"" + rs.getString(1) + "\" ,"
                                    + " \"factor\": \"" + rs.getString(2) + "\" ,"
                                    + " \"ponderacion\": \"" + rs.getString(3) + "\" ,"
                                    + " \"justificacion\": \"" + rs.getString(4) + "\" "
                                    + "},"
                                    + "";
                            aux4 = aux4 + aux5;
                        }


                    }

                    try {
                        out.println("[" + aux4 + "]");
                        System.out.println("[" + aux4 + "]");
                    } finally {
                        out.close();
                    }




                } catch (SQLException ex) {
                    Logger.getLogger(jsonController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (request.getParameter("ejecucion").equals("listarPonderacionCaracteristica")) {

                Proceso proceso = (Proceso) session.getAttribute("proceso");
                int idProceso = proceso.getId();
                String bd = (String) session.getAttribute("bd");
                sqlController conSql = new sqlController();
                String aux4 = "";


                ResultSet rs = conSql.CargarSql("Select caracteristica.id,"
                        + " caracteristica.nombre, ponderacioncaracteristica.ponderacion,"
                        + " ponderacioncaracteristica.justificacion from"
                        + " ponderacioncaracteristica inner join caracteristica on"
                        + " ponderacioncaracteristica.`caracteristica_id` ="
                        + " caracteristica.`id` where proceso_id = " + idProceso + "", bd);

                try {
                    while (rs.next()) {

                        if (rs.isLast()) {
                            String aux5 = ""
                                    + "{"
                                    + " \"id\": \"" + rs.getString(1) + "\" ,"
                                    + " \"caracteristica\": \"" + rs.getString(2) + "\" ,"
                                    + " \"ponderacion\": \"" + rs.getString(3) + "\" ,"
                                    + " \"justificacion\": \"" + rs.getString(4) + "\" "
                                    + "}"
                                    + "";

                            aux4 = aux4 + aux5;

                        } else {
                            String aux5 = ""
                                    + "{"
                                    + " \"id\": \"" + rs.getString(1) + "\" ,"
                                    + " \"caracteristica\": \"" + rs.getString(2) + "\" ,"
                                    + " \"ponderacion\": \"" + rs.getString(3) + "\" ,"
                                    + " \"justificacion\": \"" + rs.getString(4) + "\" "
                                    + "},"
                                    + "";
                            aux4 = aux4 + aux5;
                        }


                    }

                    try {
                        out.println("[" + aux4 + "]");
                        System.out.println("[" + aux4 + "]");
                    } finally {
                        out.close();
                    }




                } catch (SQLException ex) {
                    Logger.getLogger(jsonController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (request.getParameter("ejecucion").equals("listarProcesos")) {

                ProgramaJpaController conPrograma = new ProgramaJpaController();
                ProcesoJpaController conProceso = new ProcesoJpaController();
                Programa programa = conPrograma.findPrograma(1);
                session.setAttribute("programa", programa);

                List<Proceso> listProceso = conProceso.findProcesoEntities();


                int cont = listProceso.size();
                int i = 0;


                for (Proceso proceso : listProceso) {
                    i++;
                    if (proceso.getProgramaId().getId() == programa.getId()) {

                        if (i == cont) {
                            String aux5 = ""
                                    + "{"
                                    + " \"id\": \"" + proceso.getId() + "\" ,"
                                    + " \"fechaInicio\": \"" + proceso.getFechainicio() + "\" ,"
                                    + " \"fechaCierre\": \"" + proceso.getFechacierre() + "\" ,"
                                    + " \"descripcion\": \"" + proceso.getDescripcion() + "\" ,"
                                    + " \"programa\": \"" + proceso.getProgramaId().getNombre() + "\""
                                    + "}"
                                    + "";

                            aux3 = aux3 + aux5;

                        } else {
                            String aux5 = ""
                                    + "{"
                                    + " \"id\": \"" + proceso.getId() + "\" ,"
                                    + " \"fechaInicio\": \"" + proceso.getFechainicio() + "\" ,"
                                    + " \"fechaCierre\": \"" + proceso.getFechacierre() + "\" ,"
                                    + " \"descripcion\": \"" + proceso.getDescripcion() + "\" ,"
                                    + " \"programa\": \"" + proceso.getProgramaId().getNombre() + "\""
                                    + "},"
                                    + "";
                            aux3 = aux3 + aux5;
                        }
                    }
                }
                try {
                    out.println("[" + aux3 + "]");
                    System.out.println("[" + aux3 + "]");
                } finally {
                    out.close();
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
