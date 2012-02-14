/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.autoevaluacionInstitucional;

import entity.Proceso;
import entity.Programa;
import entity.controller.ProcesoJpaController;
import entity.controller.ProgramaJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Action;

/**
 *
 * @author Usuario
 */
public class index implements Action {

    public String procesar(HttpServletRequest request)
            throws IOException, ServletException {

        HttpServletResponse response = null;

        HttpSession session = request.getSession();
        String url = "/WEB-INF/vista/autoevaluacionInstitucional/index2.jsp";

        ProgramaJpaController conPrograma = new ProgramaJpaController();
        ProcesoJpaController conProceso = new ProcesoJpaController();
        Programa programa = conPrograma.findPrograma(1);
        session.setAttribute("programa", programa);

        List<Proceso> listProceso = conProceso.findProcesoEntities();


        int aux = 0;
        int aux1 = 0;

        for (Proceso proceso : listProceso) {
            if (proceso.getFechacierre() == null && proceso.getProgramaId().getId() == programa.getId()) {
                session.setAttribute("msjLogIn1", "Existe un Proceso en Ejecución!");
                session.setAttribute("proceso", proceso);
                aux = 1;
                session.setAttribute("aux_index2", aux);
                String nombreBd = programa.getNombre() + proceso.getId();
                session.setAttribute("bd", nombreBd);

                if (proceso.getFechainicio().equals("Proceso en Configuración.")) {
                    aux1 = 1;
                    session.setAttribute("aux2_index2", aux1);
                }

                PrintWriter out = response.getWriter();
                try {

                    out.println(
                            "[ "
                            + "{"
                            + " \"id\": \"" + proceso.getId() + "\" ,"
                            + " \"FechaInicio\": \"" + proceso.getFechainicio() + "\""
                            + "},"
                            + "{"
                            + "\"id\": \"mi id2\","
                            + "\"title\":\"titulo2\""
                            + "}"
                            + "]");
                } finally {
                    out.close();
                }


            }
        }

        if (aux == 0) {
            session.setAttribute("msjLogIn1", "No Existe Proceso en Ejecución!");
            //System.out.println("No hay proceso en ejecucion");
            session.setAttribute("aux_index2", aux);
        }

        return url;
    }
}