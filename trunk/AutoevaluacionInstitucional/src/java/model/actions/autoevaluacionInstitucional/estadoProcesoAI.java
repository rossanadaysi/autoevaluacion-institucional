/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.autoevaluacionInstitucional;

import controller.sqlController;
import entity.Proceso;
import entity.Programa;
import entity.controller.ProcesoJpaController;
import entity.controller.ProgramaJpaController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;
import model.Action;

public class estadoProcesoAI implements Action {

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession session = request.getSession();
        sqlController conSql = new sqlController();
        Proceso proceso2 = (Proceso) session.getAttribute("proceso");
        int idProceso = proceso2.getId();
        String bd = (String) session.getAttribute("bd");
        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/informe/estadoProceso.jsp";


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
                    session.setAttribute("detailProceso2", listProceso2);
                }
    
        return url;

    }
}
