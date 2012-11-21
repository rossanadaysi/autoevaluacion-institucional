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

public class informeMatrizAI implements Action {

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String bd = (String) session.getAttribute("bd");
        sqlController conSql = new sqlController();
        Result matriz = null;
        String sql2 = "select c1.cara, c1.nombre, c1.nivel, c1.ponderacionCara, format(avg(c1.cumplimiento),1), format(avg(cumplimiento)*c1.ponderacionCara,1), format(5*c1.ponderacionCara,1), format(avg(cumplimiento)*20,1) , c1.fid   from(\n"
                + "\n"
                + "SELECT factor.id AS fid,  factor.nombre AS fno, ponderacionfactor.ponderacion AS fpo, caracteristica.id as cara, \n"
                + "                   caracteristica.nombre as nombre, ponderacioncaracteristica.nivelimportancia as nivel, ponderacioncaracteristica.ponderacion as ponderacionCara, \n"
                + "                   format(case \n"
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
                + "       end ,1) as cumplimiento\n"
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
                + "group by c1.cara\n"
                + "";
        matriz = conSql.CargarSql2(sql2, bd);
        session.setAttribute("matriz", matriz);

        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/informe/informeMatriz.jsp";
        return url;
    }
}
