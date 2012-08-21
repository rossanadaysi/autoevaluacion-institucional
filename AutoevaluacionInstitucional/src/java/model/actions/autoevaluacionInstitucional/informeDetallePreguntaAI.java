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

        /*
        SELECT * FROM pregunta 
        * INNER JOIN encuestahaspregunta on encuestahaspregunta.pregunta_id = pregunta.id 
        * INNER JOIN encuesta on encuesta.id = encuestahaspregunta.encuesta_id 
        * where pregunta.id=5
        */


        String sql2 = "SELECT indicador.id, indicador.nombre AS ino, pregunta.id AS pi, pregunta.pregunta, format(avg(respuesta),2)"
                + " FROM Indicador"
                + " INNER JOIN pregunta ON pregunta.indicador_id = indicador.id"
                + " INNER JOIN resultadoevaluacion ON resultadoevaluacion.pregunta_id = pregunta.id"
                + " WHERE pregunta.tipo = 'elegir 1-5' and resultadoevaluacion.respuesta != 0"
                + " AND indicador.id =" + idP
                + " GROUP BY pregunta.id";
        detallePregunta = conSql.CargarSql2(sql2, bd);
        session.setAttribute("detallePregunta", detallePregunta);

        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/informe/informeDetallePregunta.jsp";
        return url;
    }
}
