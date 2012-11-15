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
        String sql2 = "SELECT caracteristica.id as cara, \n"
                + "                   caracteristica.nombre, ponderacioncaracteristica.nivelimportancia, ponderacioncaracteristica.ponderacion as ponderacionCara, \n"
                + "                   format(case when ( (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/\n"
                + "                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end))) is null then  avg (   numericadocumental.evaluacion ) \n"
                + "                   else (( (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/\n"
                + "                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end)))+avg (numericadocumental.evaluacion))/2 end ,1) as cumplimiento,\n"
                + "                    format( ponderacioncaracteristica.ponderacion * case when ( (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/\n"
                + "                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end))) is null then  avg (   numericadocumental.evaluacion ) \n"
                + "                   else (( (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/\n"
                + "                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end)))+avg (numericadocumental.evaluacion))/2 end , 1)as evaluacion,\n"
                + "                   format(5 * ponderacioncaracteristica.ponderacion ,1) as ideal,\n"
                + "                   format((ponderacioncaracteristica.ponderacion * case when ( (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/\n"
                + "                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end))) is null then  avg (   numericadocumental.evaluacion ) \n"
                + "                   else (( (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+\n"
                + "                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/\n"
                + "                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end)))+avg (numericadocumental.evaluacion))/2 end / ( ponderacioncaracteristica.ponderacion))*20 ,1)as relacion, \n"
                + "                   factor.id FROM factor\n"
                + "                   INNER JOIN caracteristica ON caracteristica.factor_id = factor.id\n"
                + "                   INNER JOIN ponderacionfactor ON ponderacionfactor.factor_id = factor.id\n"
                + "                   INNER JOIN ponderacioncaracteristica ON ponderacioncaracteristica.caracteristica_id = caracteristica.id\n"
                + "                   INNER JOIN indicador ON indicador.caracteristica_id = caracteristica.id\n"
                + "                   LEFT JOIN numericadocumental ON numericadocumental.indicador_id = indicador.id\n"
                + "                   LEFT JOIN pregunta ON pregunta.indicador_id = indicador.id\n"
                + "                   LEFT JOIN resultadoevaluacion ON resultadoevaluacion.pregunta_id = pregunta.id\n"
                + "                   LEFT JOIN encabezado ON encabezado.id = resultadoevaluacion.encabezado_id\n"
                + "                   GROUP BY caracteristica.id;";
        matriz = conSql.CargarSql2(sql2, bd);
        session.setAttribute("matriz", matriz);

        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/informe/informeMatriz.jsp";
        return url;
    }
}
