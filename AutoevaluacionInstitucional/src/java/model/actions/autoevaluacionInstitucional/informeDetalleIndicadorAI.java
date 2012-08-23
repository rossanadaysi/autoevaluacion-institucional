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

public class informeDetalleIndicadorAI implements Action {

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String bd = (String) session.getAttribute("bd");
        String idI = (String) request.getParameter("idI");
        sqlController conSql = new sqlController();
        Result detalleIndicador = null;
        String sql2 = "SELECT indicador.id, indicador.nombre AS ino, pregunta.id AS pi, pregunta.pregunta, format(avg(respuesta),2), caracteristica.id"
                + " FROM Indicador"
                + " INNER JOIN caracteristica ON indicador.caracteristica_id = caracteristica.id"
                + " INNER JOIN pregunta ON pregunta.indicador_id = indicador.id"
                + " INNER JOIN resultadoevaluacion ON resultadoevaluacion.pregunta_id = pregunta.id"
                + " WHERE pregunta.tipo = 'elegir 1-5' and resultadoevaluacion.respuesta != 0"
                + " AND indicador.id =" + idI
                + " GROUP BY pregunta.id";
        detalleIndicador = conSql.CargarSql2(sql2, bd);
        session.setAttribute("detalleIndicador", detalleIndicador);

        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/informe/informeDetalleIndicador.jsp";
        return url;
    }
}
