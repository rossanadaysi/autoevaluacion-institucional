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
        Result matrizFactores1 = null;
        Result matrizFactores2 = null;
        Result matrizFactores3 = null;

        String sql2 = "SELECT c1.fid, c1.fno, c1.fpo,"
                + " format( SUM( c1.ponderacionCara * (case when c1.cumplimientoCara IS null THEN c1.cump2 else ((c1.cumplimientoCara+c1.cump2)/2) end)) / SUM( c1.ponderacionCara ) , 2 ) AS cumplimientoFact,"
                + " format( (SUM( c1.ponderacionCara * (case when c1.cumplimientoCara IS null THEN c1.cump2 else ((c1.cumplimientoCara+c1.cump2)/2) end) ) / SUM( c1.ponderacionCara )) * c1.fpo, 2 ) AS evaluacion,"
                + " c1.fpo *5 AS ideal,"
                + " format( (SUM( c1.ponderacionCara * (case when c1.cumplimientoCara IS null THEN c1.cump2 else ((c1.cumplimientoCara+c1.cump2)/2) end)) / SUM( c1.ponderacionCara ))*20 , 2 ) AS relacion"
                + " FROM ("
                + " SELECT factor.id AS fid,  factor.nombre AS fno, ponderacionfactor.ponderacion AS fpo, caracteristica.id as cara,"
                + " ponderacioncaracteristica.ponderacion as ponderacionCara, format("
                + " (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+"
                + " sum( case when respuesta='2'  THEN 2 ELSE 0 end)+"
                + " sum( case when respuesta='3'  THEN 3 ELSE 0 end)+"
                + " sum( case when respuesta='4'  THEN 4 ELSE 0 end)+"
                + " sum( case when respuesta='5'  THEN 5 ELSE 0 end))/"
                + " (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end)),2) AS cumplimientoCara,"
                + " avg (   numericadocumental.evaluacion ) AS cump2"
                + " FROM factor"
                + " INNER JOIN caracteristica ON caracteristica.factor_id = factor.id"
                + " INNER JOIN ponderacionfactor ON ponderacionfactor.factor_id = factor.id"
                + " INNER JOIN ponderacioncaracteristica ON ponderacioncaracteristica.caracteristica_id = caracteristica.id"
                + " INNER JOIN indicador ON indicador.caracteristica_id = caracteristica.id"
                + " LEFT JOIN numericadocumental ON numericadocumental.indicador_id = indicador.id"
                + " LEFT JOIN pregunta ON pregunta.indicador_id = indicador.id"
                + " LEFT JOIN resultadoevaluacion ON resultadoevaluacion.pregunta_id = pregunta.id"
                + " LEFT JOIN encabezado ON encabezado.id = resultadoevaluacion.encabezado_id"
                + " GROUP BY caracteristica.id"
                + " ) AS c1"
                + " group by c1.fid";
        matrizFactores1 = conSql.CargarSql2(sql2, bd);
        session.setAttribute("matrizFactores1", matrizFactores1);

        String sql3 = "SELECT c1.fid, c1.fno, c1.fpo, format( SUM( c1.ponderacion * c1.cumplimiento ) / SUM( c1.ponderacion ) , 2 ) AS cumplimiento, format( SUM( c1.ponderacion * c1.cumplimiento ) / SUM( c1.ponderacion ) * c1.fpo, 2 ) AS evaluacion, c1.fpo *5 AS ideal, format( c1.cumplimiento *20, 2 ) AS relacion"
                + " FROM ("
                + " SELECT factor.id AS fid, factor.nombre AS fno, ponderacionfactor.ponderacion AS fpo, ponderacioncaracteristica.ponderacion, avg( respuesta ) AS cumplimiento"
                + " FROM factor"
                + " INNER JOIN caracteristica ON caracteristica.factor_id = factor.id"
                + " INNER JOIN ponderacionfactor ON ponderacionfactor.factor_id = factor.id"
                + " INNER JOIN ponderacioncaracteristica ON ponderacioncaracteristica.caracteristica_id = caracteristica.id"
                + " INNER JOIN indicador ON indicador.caracteristica_id = caracteristica.id"
                + " INNER JOIN pregunta ON pregunta.indicador_id = indicador.id"
                + " INNER JOIN resultadoevaluacion ON resultadoevaluacion.pregunta_id = pregunta.id"
                + " INNER JOIN encabezado ON encabezado.id = resultadoevaluacion.encabezado_id"
                + " WHERE pregunta.tipo = 'elegir 1-5' and encabezado.estado='terminado'"
                + " GROUP BY caracteristica.id"
                + " ) AS c1"
                + " GROUP BY c1.fid";
        matrizFactores2 = conSql.CargarSql2(sql3, bd);
        session.setAttribute("matrizFactores2", matrizFactores2);

        String sql4 = "SELECT c1.fid, c1.fno, c1.fpo, format( SUM( c1.ponderacion * c1.cumplimiento ) / SUM( c1.ponderacion ) , 2 ) AS cumplimiento, format( SUM( c1.ponderacion * c1.cumplimiento ) / SUM( c1.ponderacion ) * c1.fpo, 2 ) AS evaluacion, c1.fpo *5 AS ideal, format( c1.cumplimiento *20, 2 ) AS relacion"
                + " FROM ("
                + " SELECT factor.id AS fid, factor.nombre AS fno, ponderacionfactor.ponderacion AS fpo, ponderacioncaracteristica.ponderacion, avg( numericadocumental.evaluacion ) AS cumplimiento"
                + " FROM factor"
                + " INNER JOIN caracteristica ON caracteristica.factor_id = factor.id"
                + " INNER JOIN ponderacionfactor ON ponderacionfactor.factor_id = factor.id"
                + " INNER JOIN ponderacioncaracteristica ON ponderacioncaracteristica.caracteristica_id = caracteristica.id"
                + " INNER JOIN indicador ON indicador.caracteristica_id = caracteristica.id"
                + " INNER JOIN numericadocumental ON numericadocumental.indicador_id = indicador.id"
                + " GROUP BY caracteristica.id"
                + " ) AS c1 GROUP BY c1.fid";
        matrizFactores3 = conSql.CargarSql2(sql4, bd);
        session.setAttribute("matrizFactores3", matrizFactores3);

        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/informe/informeMatrizFact.jsp";
        return url;
    }
}
