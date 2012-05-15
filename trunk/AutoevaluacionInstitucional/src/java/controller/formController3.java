/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.org.apache.xerces.internal.impl.dv.dtd.IDDatatypeValidator;
import entity.*;
import entity.controller.EncuestaJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
import javax.servlet.jsp.jstl.sql.Result;

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
            Persona per = (Persona) session.getAttribute("persona");
            
            String idF =  (String) session.getAttribute("idfuente");
            Result encuesta = (Result) session.getAttribute("encuesta");
            Result preguntas = (Result) session.getAttribute("preguntas");
            
            String nombreBd = (String) session.getAttribute("bd");
            
            sqlController conSql = new sqlController();
            
            String sql = "INSERT INTO encabezado ("
                    + "`id` ," + "`fecha` ,`persona_id` ,`proceso_id` ,`encuesta_id` ,`fuente_id`)"
                    + "VALUES ("
                    + "NULL , '" + new Date(1) + "', '" + per.getId() + "', '" + p.getId() + "', '" + encuesta.getRowsByIndex()[0][3] + "', '" + idF + "'"
                    + ");";
            conSql.UpdateSql(sql, nombreBd);
            System.out.println("INSERT INTO encabezado ("
                    + "`id` ," + "`fecha` ,`persona_id` ,`proceso_id` ,`encuesta_id` ,`fuente_id`)"
                    + "VALUES ("
                    + "NULL , '" + new Date(1) + "', '" + per.getId() + "', '" + p.getId() + "', '" + encuesta.getRowsByIndex()[0][3] + "', '" + idF + "'"
                    + ");");    

            
            String sql2 = "SELECT `id`"
                    + " FROM `encabezado`"
                    + " WHERE `proceso_id` =" + p.getId() + ""
                    + " AND `encuesta_id` =" + encuesta.getRowsByIndex()[0][3] + ""
                    + " AND `fuente_id` =" + idF + ""
                    + " AND `persona_id` =" + per.getId() + "";

            ResultSet rs4 = conSql.CargarSql(sql2, nombreBd);
            int idEncabezado=0;
            try {
                while (rs4.next()) {
                    idEncabezado = rs4.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(formController3.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           for(int i=0;i< preguntas.getRowCount();i++){
               String res = request.getParameter("pregunta" + preguntas.getRowsByIndex()[i][0] + "");
                String sqlResultado = "INSERT INTO resultadoevaluacion ("
                    + "`idResultadoEvaluacion` ,`respuesta` ,`encabezado_id` ,`pregunta_id` )"
                    + "VALUES ("
                    + "NULL , '" + res + "', '" + idEncabezado + "', '" + p.getId() + "'"
                    + ");";
                            
                conSql.UpdateSql(sqlResultado, nombreBd);
               
            }
                                                
                                               
                                                String sql6 = "SELECT encuesta.id , encuesta.nombre"
                                                + " FROM encuesta"
                                                + " INNER JOIN asignacionencuesta ON asignacionencuesta.ENCUESTA_ID = encuesta.ID"
                                                + " INNER JOIN proceso ON asignacionencuesta.PROCESO_ID = proceso.ID"
                                                + " INNER JOIN muestra ON asignacionencuesta.PROCESO_ID = muestra.PROCESO_ID"
                                                + " INNER JOIN muestraestudiante ON muestra.ID = muestraestudiante.MUESTRA_ID"
                                                + " INNER JOIN estudiante ON muestraestudiante.ESTUDIANTE_ID = estudiante.ID"
                                                + " INNER JOIN persona ON estudiante.PERSONA_ID = persona.ID"
                                                + " WHERE persona.id = " + per.getId() + ""
                                                + " AND proceso.`FECHACIERRE` IS NULL"
                                                + " AND asignacionencuesta.fuente_id=" + idF + ""
                                                + " AND (asignacionencuesta.PROCESO_ID, persona.id, asignacionencuesta.ENCUESTA_ID, asignacionencuesta.FUENTE_ID) NOT IN "
                                                + " (select encabezado.PROCESO_ID, encabezado.PERSONA_ID, encabezado.ENCUESTA_ID, encabezado.FUENTE_ID from encabezado) "
                                                + "";
                                        
                                                


                                        Result encuestasDisponibles = conSql.CargarSql2(sql6, nombreBd);
                                        session.setAttribute("listaEncuestasDisponibles", encuestasDisponibles);

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
