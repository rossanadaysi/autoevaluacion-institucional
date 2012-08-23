/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.autoevaluacionInstitucional;

import controller.sqlController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;
import model.Action;

public class informeDetallePreguntaAI implements Action {

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String bd = (String) session.getAttribute("bd");
        String idP = (String) request.getParameter("idP");
        sqlController conSql = new sqlController();
        Result detallePregunta = null;

        String sql2 = "SELECT pregunta.pregunta, encuesta.nombre, format(avg(respuesta),2), pregunta.codigo FROM `resultadoevaluacion`"
                + " INNER JOIN encabezado ON encabezado.id = resultadoevaluacion.encabezado_id"
                + " INNER JOIN encuesta ON encuesta.id = encabezado.encuesta_id"
                + " INNER JOIN pregunta ON pregunta.id = resultadoevaluacion.pregunta_id"
                + " WHERE pregunta.id ="+idP+" group by encuesta.id";
        detallePregunta = conSql.CargarSql2(sql2, bd);
        session.setAttribute("detallePregunta", detallePregunta);

        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/informe/informeDetallePregunta.jsp";
        return url;
    }
}
