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

        String sql2 = "select c2.fid2, c2.fno2, c2.fpo2, format(sum(c2.cumpC*c2.pondC)/sum(c2.pondC),1), format((sum(c2.cumpC*c2.pondC)/sum(c2.pondC))*c2.fpo2,1), format(c2.fpo2*5,1), format((sum(c2.cumpC*c2.pondC)/sum(c2.pondC))*20,1) from (select c1.fid as fid2, c1.fno as fno2, c1.fpo as fpo2, c1.cara, c1.nombre, c1.nivel, c1.ponderacionCara as pondC, avg(c1.cumplimiento) as cumpC, avg(cumplimiento)*c1.ponderacionCara, 5*c1.ponderacionCara, avg(cumplimiento)*20    from(\n"
                + "\n"
                + "SELECT factor.id AS fid,  factor.nombre AS fno, ponderacionfactor.ponderacion AS fpo, caracteristica.id as cara, \n"
                + "                   caracteristica.nombre as nombre, ponderacioncaracteristica.nivelimportancia as nivel, ponderacioncaracteristica.ponderacion as ponderacionCara, \n"
                + "                   case \n"
                + "           when ( (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/\n"
                + "                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end))) is null \n"
                + "           then  avg (numericadocumental.evaluacion ) \n"
                + "           when avg (numericadocumental.evaluacion )is null \n"
                + "           then (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+\n"
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
                + "       end  as cumplimiento\n"
                + "                   FROM factor\n"
                + "                   INNER JOIN caracteristica ON caracteristica.factor_id = factor.id\n"
                + "                   INNER JOIN ponderacionfactor ON ponderacionfactor.factor_id = factor.id\n"
                + "                   INNER JOIN ponderacioncaracteristica ON ponderacioncaracteristica.caracteristica_id = caracteristica.id\n"
                + "                   INNER JOIN indicador ON indicador.caracteristica_id = caracteristica.id\n"
                + "                   LEFT JOIN numericadocumental ON numericadocumental.indicador_id = indicador.id\n"
                + "                   LEFT JOIN pregunta ON pregunta.indicador_id = indicador.id\n"
                + "                   LEFT JOIN resultadoevaluacion ON resultadoevaluacion.pregunta_id = pregunta.id\n"
                + "                   group by indicador.id\n"
                + "\n"
                + ") as c1\n"
                + "\n"
                + "group by c1.cara) as c2\n"
                + "group by c2.fid2\n"
                + "";
        matrizFactores1 = conSql.CargarSql2(sql2, bd);
        session.setAttribute("matrizFactores1", matrizFactores1);
        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/informe/informeMatrizFact.jsp";
        return url;
    }
}
