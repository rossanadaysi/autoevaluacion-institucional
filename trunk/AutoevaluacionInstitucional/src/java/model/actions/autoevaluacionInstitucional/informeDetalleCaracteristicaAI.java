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


public class informeDetalleCaracteristicaAI implements Action{
 @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String bd = (String) session.getAttribute("bd");
        String idC = (String) request.getParameter("idC");
        sqlController conSql = new sqlController();
        Result detalleCaracteristica = null;
        String sql2 = "SELECT caracteristica.id AS cid, caracteristica.nombre AS cno, indicador.id, indicador.nombre, format(avg(respuesta),2)"
                + " FROM Caracteristica"
                + " INNER JOIN ponderacioncaracteristica ON ponderacioncaracteristica.caracteristica_id = caracteristica.id"
                + " INNER JOIN indicador ON indicador.caracteristica_id = caracteristica.id"
                + " INNER JOIN pregunta ON pregunta.indicador_id = indicador.id"
                + " INNER JOIN resultadoevaluacion ON resultadoevaluacion.pregunta_id = pregunta.id"
                + " WHERE pregunta.tipo = 'elegir 1-5' and resultadoevaluacion.respuesta!=0 and caracteristica.id ="+idC
                + " GROUP BY indicador.id";
        detalleCaracteristica = conSql.CargarSql2(sql2, bd);
        session.setAttribute("detalleCaracteristica", detalleCaracteristica);

        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/informe/informeDetalleCaracteristica.jsp";
        return url;
    }
}
