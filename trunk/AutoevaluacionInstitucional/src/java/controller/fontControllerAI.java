/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Action;

/**
 *
 * @author Arturo González
 */
public class fontControllerAI extends HttpServlet {

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




        Enumeration<String> e = request.getParameterNames();

        while (e.hasMoreElements()) {
            String s = e.nextElement();
            //System.out.println(s + ":: " + request.getParameter(s));
        }


        try {



            ResourceBundle rb = ResourceBundle.getBundle("/properties/actions");
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

        } catch (Exception ex) {
            String ruta = "error.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(ruta);
            rd.forward(request, response);
            //throw new ServletException("ha ocurrido un error");

            // System.out.println("action: '" + action + "' procesada.");


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
