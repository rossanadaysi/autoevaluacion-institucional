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


public class informeRealAI implements Action{

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String idencuesta = request.getParameter("encuesta");
        String bd = (String) session.getAttribute("bd");
        sqlController conSql = new sqlController();
        Result preguntas = null;
        String sql2 = "SELECT pregunta.id, pregunta.pregunta, pregunta.tipo, count( CASE WHEN respuesta = 'Si' THEN 1 ELSE null end) AS 'Si' , count( CASE WHEN respuesta = 'No' THEN 1 ELSE null end) AS 'No' , count( CASE WHEN respuesta = '0' THEN 1 ELSE null end) AS '0' , count( CASE WHEN respuesta = '1' THEN 1 ELSE null end) AS '1',count( CASE WHEN respuesta = '2' THEN 1 ELSE null end) AS '2', count( CASE WHEN respuesta = '3' THEN 1 ELSE null end) AS '3', count( CASE WHEN respuesta = '4' THEN 1 ELSE null end) AS '4', count( CASE WHEN respuesta = '5' THEN 1 ELSE null end) AS '5'"
+" FROM `encuesta`"
+" INNER JOIN encabezado ON encuesta.id = encabezado.encuesta_id"
+" INNER JOIN resultadoevaluacion ON encabezado.id = resultadoevaluacion.encabezado_id"
+" INNER JOIN pregunta ON resultadoevaluacion.pregunta_id = pregunta.id"
+" WHERE encuesta.id =1"
+" GROUP BY pregunta.id";
        preguntas = conSql.CargarSql2(sql2, bd);
        session.setAttribute("preguntas", preguntas);
        
        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/informe/informeReal.jsp";
        return url;
    }

}
