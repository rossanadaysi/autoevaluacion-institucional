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

public class informeDetalleCaracteristicaAI implements Action {

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String bd = (String) session.getAttribute("bd");
        String idC = (String) request.getParameter("idC");
        sqlController conSql = new sqlController();
        Result detalleCaracteristica = null;
        String sql2 = "SELECT caracteristica.id AS cid, caracteristica.nombre AS cno, indicador.id, indicador.nombre, \n"
                + "format(case \n"
                + "           when ( (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/\n"
                + "                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end))) is null then  avg (numericadocumental.evaluacion ) \n"
                + "           when avg (numericadocumental.evaluacion )is null then (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/\n"
                + "                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end))\n"
                + "           else (( (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/\n"
                + "                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end)))+avg (numericadocumental.evaluacion))/2 \n"
                + "       end ,2) as cumplimiento, \n"
                + "factor.id, indicador.codigo, factor.nombre\n"
                + "FROM Caracteristica\n"
                + "INNER JOIN factor ON caracteristica.factor_id = factor.id\n"
                + "INNER JOIN ponderacioncaracteristica ON ponderacioncaracteristica.caracteristica_id = caracteristica.id\n"
                + "INNER JOIN indicador ON indicador.caracteristica_id = caracteristica.id\n"
                + "LEFT JOIN numericadocumental ON numericadocumental.indicador_id = indicador.id\n"
                + "LEFT JOIN pregunta ON pregunta.indicador_id = indicador.id\n"
                + "LEFT JOIN resultadoevaluacion ON resultadoevaluacion.pregunta_id = pregunta.id\n"
                + "where caracteristica.id ="+idC+" "
                + "GROUP BY indicador.id";
        detalleCaracteristica = conSql.CargarSql2(sql2, bd);
        session.setAttribute("detalleCaracteristica", detalleCaracteristica);

        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/informe/informeDetalleCaracteristica.jsp";
        return url;
    }
}
