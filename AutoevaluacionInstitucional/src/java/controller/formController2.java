/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Caracteristica;
import entity.Factor;
import entity.Indicador;
import entity.Pregunta;
import entity.controller.CaracteristicaJpaController;
import entity.controller.FactorJpaController;
import entity.controller.IndicadorJpaController;
import entity.controller.PreguntaJpaController;
import entity.controller.exceptions.IllegalOrphanException;
import entity.controller.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class formController2 extends HttpServlet {

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
            if (request.getParameter("action").equals("crearFactorCC")) {
                HttpSession session = request.getSession();
                Factor fa = new Factor();
                FactorJpaController conFa = new FactorJpaController();
                CaracteristicaJpaController conCa = new CaracteristicaJpaController();
                fa.setNombre(request.getParameter("nombre"));
                fa.setDescripcion(request.getParameter("descripcion"));

                List<Caracteristica> listadeCaracteristicas = conCa.findCaracteristicaEntities();
                List<Caracteristica> aux = new ArrayList<Caracteristica>();
                for (int i = 0; i < listadeCaracteristicas.size(); i++) {
                    if (request.getParameter("C" + listadeCaracteristicas.get(i).getId()).equals("1")) {
                        aux.add(listadeCaracteristicas.get(i));

                    }
                }
                fa.setCaracteristicaList(aux);
                conFa.create(fa);
            }

            if (request.getParameter("action").equals("editarFactorCC")) {
                HttpSession session = request.getSession();
                String idF = request.getParameter("idF");
                FactorJpaController conFa = new FactorJpaController();
                Factor fa = conFa.findFactor(Integer.parseInt(idF));
                CaracteristicaJpaController conCa = new CaracteristicaJpaController();
                fa.setNombre(request.getParameter("nombre"));
                fa.setDescripcion(request.getParameter("descripcion"));

                List<Caracteristica> listadeCaracteristicas = conCa.findCaracteristicaEntities();
                List<Caracteristica> aux = new ArrayList<Caracteristica>();
                for (int i = 0; i < listadeCaracteristicas.size(); i++) {
                    if (request.getParameter("C"+listadeCaracteristicas.get(i).getId()).equals("1")) {
                        aux.add(listadeCaracteristicas.get(i));

                    }
                }
                fa.setCaracteristicaList(aux);
                try {
                    conFa.edit(fa);
                } catch (IllegalOrphanException ex) {
                    Logger.getLogger(formController2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(formController2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(formController2.class.getName()).log(Level.SEVERE, null, ex);
                }


            }


            if (request.getParameter("action").equals("editarIndicadorCC")) {
                HttpSession session = request.getSession();
                String idI = request.getParameter("idI");
                IndicadorJpaController conIndi = new IndicadorJpaController();
                CaracteristicaJpaController conCarac = new CaracteristicaJpaController();
                Indicador in = conIndi.findIndicador(Integer.parseInt(idI));
                PreguntaJpaController conPre = new PreguntaJpaController();
                in.setNombre(request.getParameter("nombre"));
                in.setDescripcion(request.getParameter("descripcion"));
                String car = request.getParameter("caracteristica");
                if(car!=null && !car.equals("")){
                Caracteristica ca = conCarac.findCaracteristica(Integer.parseInt(car));
                in.setCaracteristicaId(ca);
                }else{
                    in.setCaracteristicaId(null);
                }
                List<Pregunta> listadePreguntas = conPre.findPreguntaEntities();
                List<Pregunta> aux = new ArrayList<Pregunta>();
                
                
                
                for (int i = 0; i < listadePreguntas.size(); i++) {
                    if (request.getParameter("P"+listadePreguntas.get(i).getId()).equals("1")) {
                        aux.add(listadePreguntas.get(i));

                    }
                }
                in.setPreguntaList(aux);
                try {
                    conIndi.edit(in);
                } catch (IllegalOrphanException ex) {
                    Logger.getLogger(formController2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(formController2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(formController2.class.getName()).log(Level.SEVERE, null, ex);
                }


            }

            
            if (request.getParameter("action").equals("editarCaracteristicaCC")) {
                HttpSession session = request.getSession();
                String idC = request.getParameter("idC");
                CaracteristicaJpaController conCara = new CaracteristicaJpaController();
                FactorJpaController conFac = new FactorJpaController();
                Caracteristica ca = conCara.findCaracteristica(Integer.parseInt(idC));
                IndicadorJpaController conIndi = new IndicadorJpaController();
                ca.setNombre(request.getParameter("nombre"));
                ca.setDescripcion(request.getParameter("descripcion"));
                String fac = request.getParameter("factor");
                if(fac!=null && !fac.equals("")){
                Factor f = conFac.findFactor(Integer.parseInt(fac));
                ca.setFactorId(f);
                }else{
                    ca.setFactorId(null);
                }
                List<Indicador> listadeIndicadores = conIndi.findIndicadorEntities();
                List<Indicador> aux = new ArrayList<Indicador>();
                for (int i = 0; i < listadeIndicadores.size(); i++) {
                    if (request.getParameter("I"+listadeIndicadores.get(i).getId()).equals("1")) {
                        aux.add(listadeIndicadores.get(i));

                    }
                }
                ca.setIndicadorList(aux);
                try {
                    conCara.edit(ca);
                } catch (IllegalOrphanException ex) {
                    Logger.getLogger(formController2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(formController2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(formController2.class.getName()).log(Level.SEVERE, null, ex);
                }


            }

            if (request.getParameter("action").equals("crearCaracteristicaCC")) {
                Caracteristica ca = new Caracteristica();
                CaracteristicaJpaController conCa = new CaracteristicaJpaController();
                IndicadorJpaController conIn = new IndicadorJpaController();
                ca.setNombre(request.getParameter("nombre"));
                ca.setDescripcion(request.getParameter("descripcion"));
                FactorJpaController conFa = new FactorJpaController();
                String fac = (String) request.getParameter("factor");
                if(fac!=null && !fac.equals("")){
                Factor fa = conFa.findFactor(Integer.parseInt(fac));
                ca.setFactorId(fa);
                }else{
                    ca.setFactorId(null);
                }
                

                List<Indicador> listadeIndicadores = conIn.findIndicadorEntities();
                List<Indicador> aux = new ArrayList<Indicador>();
                for (int i = 0; i < listadeIndicadores.size(); i++) {
                    if (request.getParameter("I"+listadeIndicadores.get(i).getId()).equals("1")) {
                        aux.add(listadeIndicadores.get(i));

                    }
                }
                ca.setIndicadorList(aux);
                conCa.create(ca);
            }

            if (request.getParameter("action").equals("crearIndicadorCC")) {
                Indicador in = new Indicador();
                IndicadorJpaController conIn = new IndicadorJpaController();
                PreguntaJpaController conPre = new PreguntaJpaController();
                in.setNombre(request.getParameter("nombre"));
                in.setDescripcion(request.getParameter("descripcion"));
                CaracteristicaJpaController conCa = new CaracteristicaJpaController();
                String car = request.getParameter("caracteristica");
                if(car!=null && !car.equals("")){
                Caracteristica ca = conCa.findCaracteristica(Integer.parseInt(car));
                in.setCaracteristicaId(ca);
                }else{
                    in.setCaracteristicaId(null);
                }
                
                List<Pregunta> listadePreguntas = conPre.findPreguntaEntities();
                List<Pregunta> aux = new ArrayList<Pregunta>();
                for (int i = 0; i < listadePreguntas.size(); i++) {
                    if (request.getParameter("P"+listadePreguntas.get(i).getId()).equals("1")) {
                        aux.add(listadePreguntas.get(i));

                    }
                }
                in.setPreguntaList(aux);
                conIn.create(in);
            }

            if (request.getParameter("action").equals("crearPreguntaCC")) {
                HttpSession session = request.getSession();
                Pregunta p = new Pregunta();
                PreguntaJpaController conPre = new PreguntaJpaController();
                IndicadorJpaController conIn = new IndicadorJpaController();
                p.setPregunta(request.getParameter("pregunta"));
                p.setTipo(request.getParameter("tipo"));
                
                String ind = request.getParameter("indicador");
                
                if(ind!=null && !ind.equals("")){
                Indicador in = conIn.findIndicador(Integer.parseInt(ind));
                p.setIndicadorId(in);
                }else{
                    p.setIndicadorId(null);
                }
                
                

                conPre.create(p);

                session.setAttribute("listpreguntas", conPre.findPreguntaEntities());
            }

            if (request.getParameter("action").equals("editarPreguntaCC")) {
                HttpSession session = request.getSession();
                String idP = request.getParameter("idP");
                PreguntaJpaController conPre = new PreguntaJpaController();
                Pregunta pre = conPre.findPregunta(Integer.parseInt(idP));
                pre.setPregunta(request.getParameter("pregunta"));
                IndicadorJpaController conIn = new IndicadorJpaController();
                pre.setTipo(request.getParameter("tipo"));
                String ind = request.getParameter("indicador");
                
                if(ind!=null && !ind.equals("")){
                Indicador in = conIn.findIndicador(Integer.parseInt(ind));
                pre.setIndicadorId(in);
                }else{
                    pre.setIndicadorId(null);
                }
                try {
                    conPre.edit(pre);
                } catch (IllegalOrphanException ex) {
                    Logger.getLogger(formController2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(formController2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(formController2.class.getName()).log(Level.SEVERE, null, ex);
                }

                session.setAttribute("listpreguntas", conPre.findPreguntaEntities());
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
