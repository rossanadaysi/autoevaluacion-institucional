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

public class informeMatrizFactAI implements Action {

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String bd = (String) session.getAttribute("bd");
        sqlController conSql = new sqlController();
        Result matrizFactores = null;
        String sql2 = "SELECT c1.fid, c1.fno, c1.fpo, format( SUM( c1.ponderacion * c1.cumplimiento ) / SUM( c1.ponderacion ) , 2 ) AS cumplimiento, format( SUM( c1.ponderacion * c1.cumplimiento ) / SUM( c1.ponderacion ) * c1.fpo, 2 ) AS evaluacion, c1.fpo *5 AS ideal, format( c1.cumplimiento *20, 2 ) AS relacion"
                + " FROM ("
                + " SELECT factor.id AS fid, factor.nombre AS fno, ponderacionfactor.ponderacion AS fpo, ponderacioncaracteristica.ponderacion, avg( respuesta ) AS cumplimiento"
                + " FROM factor"
                + " INNER JOIN caracteristica ON caracteristica.factor_id = factor.id"
                + " INNER JOIN ponderacionfactor ON ponderacionfactor.factor_id = factor.id"
                + " INNER JOIN ponderacioncaracteristica ON ponderacioncaracteristica.caracteristica_id = caracteristica.id"
                + " INNER JOIN indicador ON indicador.caracteristica_id = caracteristica.id"
                + " INNER JOIN pregunta ON pregunta.indicador_id = indicador.id"
                + " INNER JOIN resultadoevaluacion ON resultadoevaluacion.pregunta_id = pregunta.id"
                + " WHERE pregunta.tipo = 'elegir 1-5'"
                + " GROUP BY caracteristica.id"
                + " ) AS c1"
                + " GROUP BY c1.fid";
        matrizFactores = conSql.CargarSql2(sql2, bd);
        session.setAttribute("matrizFactores", matrizFactores);

        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/informe/informeMatrizFact.jsp";
        return url;
    }
}
