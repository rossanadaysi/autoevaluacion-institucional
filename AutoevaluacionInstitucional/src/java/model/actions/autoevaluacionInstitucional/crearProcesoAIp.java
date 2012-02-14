/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.autoevaluacionInstitucional;

import controller.sqlController;
import entity.Proceso;
import entity.Programa;
import entity.controller.ProcesoJpaController;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Action;

/**
 *
 * @author Usuario
 */
public class crearProcesoAIp implements Action {

    public String procesar(HttpServletRequest request)
            throws IOException, ServletException {

        String url = "/ControllerAI?action=indexAI";
        HttpSession session = request.getSession();

        sqlController conSql = new sqlController();
        Programa programa = new Programa();
        Proceso proceso = new Proceso();
        ProcesoJpaController conProceso = new ProcesoJpaController();

        proceso.setFechainicio("Proceso en Configuración.");
        proceso.setDescripcion(request.getParameter("descripcion"));

        programa = (Programa) session.getAttribute("programa");
        proceso.setProgramaId(programa);

        conProceso.create(proceso);

        session.setAttribute("proceso", proceso);
        session.setAttribute("aux_index2", 1);
        session.setAttribute("aux2_index2", 1);

        try {
            conSql.newDb(proceso);
        } catch (SQLException ex) {
            // Logger.getLogger(fontController.class.getName()).log(Level.SEVERE, null, ex);
        }


        String nombreBd = programa.getNombre() + proceso.getId();

        session.setAttribute("proceso", proceso);
        session.setAttribute("bd", nombreBd);


        return url;
    }
}
