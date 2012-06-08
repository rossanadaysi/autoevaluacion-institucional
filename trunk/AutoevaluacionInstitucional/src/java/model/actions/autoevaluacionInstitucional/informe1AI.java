/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.actions.autoevaluacionInstitucional;

import controller.sqlController;
import entity.Proceso;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;
import model.Action;


public class informe1AI implements Action {

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession session = request.getSession();

        sqlController conSql = new sqlController();
        Proceso proceso = (Proceso) session.getAttribute("proceso");
        int idProceso = proceso.getId();
        String bd = (String) session.getAttribute("bd");
        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/informe/informeDeResultadosPorEncuesta.jsp";


        Result rs2 = null;
        String sql = "Select encuesta.id, encuesta.nombre from asignacionencuesta"
                + " INNER JOIN encuesta on asignacionencuesta.id=encuesta.id"
                + " where asignacionencuesta.proceso_id="+idProceso;
        rs2 = conSql.CargarSql2(sql, bd);

        session.setAttribute("listencuestas", rs2);
        return url;

    }

}
