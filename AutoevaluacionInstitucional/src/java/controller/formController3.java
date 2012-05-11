/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.*;
import entity.controller.EncuestaJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
 * @author Ususario
 */
public class formController3 extends HttpServlet {

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
        HttpSession session = request.getSession();

        if (request.getParameter("action").equals("responderE")) {

            Proceso p = (Proceso) session.getAttribute("proceso");
            String idF =  (String) session.getAttribute("idfuente");
            List<Estudiante> est = (List<Estudiante>) session.getAttribute("listEstudiante");
            Persona per = est.get(0).getPersonaId();
            Encuesta e = (Encuesta) session.getAttribute("encuesta");
            String nombreBd = (String) session.getAttribute("bd");
            sqlController conSql = new sqlController();
            ResultSet rs3 = null;
            String sql = "INSERT INTO encabezado ("
                    + "`id` ," + "`fecha` ,`persona_id` ,`proceso_id` ,`encuesta_id` ,`fuente_id`)"
                    + "VALUES ("
                    + "NULL , '" + new Date() + "', '" + per.getId() + "', '" + p.getId() + "', '" + e.getId() + "', '" + idF + "'"
                    + ");";
            rs3 = conSql.CargarSql(sql, nombreBd);


            ResultSet rs4 = null;
            String sql2 = "SELECT `id`"
                    + "FROM `encabezado`"
                    + "WHERE `proceso_id` =" + p.getId() + ""
                    + "AND `encuesta_id` =" + e.getId() + ""
                    + "AND `fuente_id` =" + idF + ""
                    + "AND `persona_id` =" + per.getId() + "";

            rs4 = conSql.CargarSql(sql2, nombreBd);
            try {
                while (rs4.next()) {
                    int idEncabezado = rs4.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(formController3.class.getName()).log(Level.SEVERE, null, ex);
            }

            List<Pregunta> listPreguntas = e.getPreguntaList();
            for (Pregunta pregunta : listPreguntas) {
                System.out.println("ok");
            }



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
