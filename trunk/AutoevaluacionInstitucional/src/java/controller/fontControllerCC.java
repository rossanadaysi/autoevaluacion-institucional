/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import model.Action;

/**
 *
 * @author Ususario
 */
public class fontControllerCC extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
       Enumeration<String> e = request.getParameterNames();

        while (e.hasMoreElements()) {
            String s = e.nextElement();
            //System.out.println(s + ":: " + request.getParameter(s));
        }


        try {



            ResourceBundle rb = ResourceBundle.getBundle("/properties/actions2");
            String action = request.getParameter("action");
            String clase = null;
            clase = rb.getString(action);

            Action objeto = (Action) Class.forName(clase).newInstance();

            String ruta = objeto.procesar(request);

           // System.out.println("action: '" + action + "' procesada.");

            RequestDispatcher rd = request.getRequestDispatcher(ruta);

            if (rd == null) {
                throw new ServletException("Vista NO encontrada");
            }

            rd.forward(request, response);

        } catch (NullPointerException ex) {

            ex.printStackTrace();
            throw new ServletException("Accion no encontrada");

        } catch (ClassNotFoundException ex) {

            ex.printStackTrace();
            throw new ServletException("class no encontrada");

        } catch (InstantiationException ex) {

            ex.printStackTrace();
            throw new ServletException("error al instanciar");

        } catch (IllegalAccessException ex) {

            ex.printStackTrace();
            throw new ServletException("acceso ilegal");

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
