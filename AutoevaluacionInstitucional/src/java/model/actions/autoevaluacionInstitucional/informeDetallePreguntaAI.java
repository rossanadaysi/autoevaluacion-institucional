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

        String sql2 = "SELECT pregunta.pregunta, encuesta.nombre, "
                + "format((sum( case when respuesta='1'  THEN 1 ELSE 0 end)+ "
                + "sum( case when respuesta='2'  THEN 2 ELSE 0 end)+ "
                + "sum( case when respuesta='3'  THEN 3 ELSE 0 end)+ "
                + "sum( case when respuesta='4'  THEN 4 ELSE 0 end)+ "
                + "sum( case when respuesta='5'  THEN 5 ELSE 0 end))/ "
                + "(count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end)),1), "
                + "pregunta.codigo, encuesta.id, "
                + "count( CASE WHEN respuesta = '0' THEN 1 ELSE null end) , "
        + "count( CASE WHEN respuesta = '1' THEN 1 ELSE null end) , "
        + "count( CASE WHEN respuesta = '2' THEN 1 ELSE null end) , "
        + "count( CASE WHEN respuesta = '3' THEN 1 ELSE null end) , "
        + "count( CASE WHEN respuesta = '4' THEN 1 ELSE null end) , "
        + "count( CASE WHEN respuesta = '5' THEN 1 ELSE null end) "
        + "FROM `resultadoevaluacion`  "
        + "INNER JOIN encabezado ON encabezado.id = resultadoevaluacion.encabezado_id "
        + "INNER JOIN encuesta ON encuesta.id = encabezado.encuesta_id "
        + "INNER JOIN pregunta ON pregunta.id = resultadoevaluacion.pregunta_id "
        + "WHERE pregunta.id ="+idP+" "
        + "group by encuesta.id";
        detallePregunta = conSql.CargarSql2(sql2, bd);
        session.setAttribute("detallePregunta", detallePregunta);

        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/informe/informeDetallePregunta.jsp";
        return url;
    }
}
