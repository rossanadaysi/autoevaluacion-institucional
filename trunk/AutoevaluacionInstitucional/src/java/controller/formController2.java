/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Caracteristica;
import entity.Factor;
import entity.Indicador;
import entity.controller.CaracteristicaJpaController;
import entity.controller.FactorJpaController;
import entity.controller.IndicadorJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ususario
 */
public class formController2 extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            if (request.getParameter("action").equals("crearFactorAI")) {
                HttpSession session = request.getSession();
                Factor fa = new Factor();
                FactorJpaController conFa = new FactorJpaController();
                CaracteristicaJpaController conCa = new CaracteristicaJpaController();
                fa.setNombre(request.getParameter("nombre"));
                fa.setDescripcion(request.getParameter("descripcion"));

                List<Caracteristica> listadeCaracteristicas = conCa.findCaracteristicaEntities();
                List<Caracteristica> aux = new ArrayList<Caracteristica>();
                for (int i = 0; i < listadeCaracteristicas.size(); i++) {
                    if (request.getParameter(listadeCaracteristicas.get(i).getNombre()).equals("1")) {
                        aux.add(listadeCaracteristicas.get(i));

                    }
                }
                fa.setCaracteristicaList(aux);
                conFa.create(fa);
            }
            if (request.getParameter("action").equals("crearCaracteristicaAI")) {
                HttpSession session = request.getSession();
                Caracteristica ca = new Caracteristica();
                CaracteristicaJpaController conCa = new CaracteristicaJpaController();
                IndicadorJpaController conIn = new IndicadorJpaController();
                ca.setNombre(request.getParameter("nombre"));
                ca.setDescripcion(request.getParameter("descripcion"));
                FactorJpaController conFa = new FactorJpaController();
                Factor fa = conFa.findFactor(Integer.parseInt(request.getParameter("factores")));
                ca.setFactorId(fa);

                List<Indicador> listadeIndicadores = conIn.findIndicadorEntities();
                List<Indicador> aux = new ArrayList<Indicador>();
                for (int i = 0; i < listadeIndicadores.size(); i++) {
                    if (request.getParameter(listadeIndicadores.get(i).getNombre()).equals("1")) {
                        aux.add(listadeIndicadores.get(i));

                    }
                }
                ca.setIndicadorList(aux);
                conCa.create(ca);
            }
            
        } finally {
            out.close();
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
