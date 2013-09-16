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

public class informeMatrizFactPAI implements Action {

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String bd = (String) session.getAttribute("bd");
        sqlController conSql = new sqlController();
        Result matrizCaracteristicas = null;
        Result matrizFactores = null;

        String sql2 = "select c1.cara, c1.nombre, c1.nivel, c1.ponderacionCara, format(avg(c1.cumplimiento),1), format(avg(cumplimiento)*c1.ponderacionCara,1), format(5*c1.ponderacionCara,1), format(avg(cumplimiento)*20,1) , c1.fid   from(\n"
                + "                SELECT factor.id AS fid,  factor.nombre AS fno, ponderacionfactor.ponderacion AS fpo, caracteristica.id as cara, \n"
                + "                                   caracteristica.nombre as nombre, ponderacioncaracteristica.nivelimportancia as nivel, ponderacioncaracteristica.ponderacion as ponderacionCara, \n"
                + "                                   (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+\n"
                + "                                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+\n"
                + "																	 sum( case when respuesta='3'  THEN 3 ELSE 0 end)+\n"
                + "                                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+\n"
                + "                                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/\n"
                + "                                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end))\n"
                + "                        as cumplimiento\n"
                + "                                   FROM factor\n"
                + "                                   INNER JOIN caracteristica ON caracteristica.factor_id = factor.id\n"
                + "                                   INNER JOIN ponderacionfactor ON ponderacionfactor.factor_id = factor.id\n"
                + "                                   INNER JOIN ponderacioncaracteristica ON ponderacioncaracteristica.caracteristica_id = caracteristica.id\n"
                + "                                   INNER JOIN indicador ON indicador.caracteristica_id = caracteristica.id\n"
                + "                                   LEFT JOIN numericadocumental ON numericadocumental.indicador_id = indicador.id\n"
                + "                                   LEFT JOIN pregunta ON pregunta.indicador_id = indicador.id\n"
                + "                                   LEFT JOIN resultadoevaluacion ON resultadoevaluacion.pregunta_id = pregunta.id\n"
                + "                                   group by indicador.id\n"
                + "                ) as c1\n"
                + "                group by c1.cara";
        matrizCaracteristicas = conSql.CargarSql2(sql2, bd);

        String sql3 = "select c2.fid2, c2.fno2, c2.fpo2, format(sum(c2.cumpC*c2.pondC)/sum(c2.pondC),1), format((sum(c2.cumpC*c2.pondC)/sum(c2.pondC))*c2.fpo2,1), format(c2.fpo2*5,1), format((sum(c2.cumpC*c2.pondC)/sum(c2.pondC))*20,1) from (select c1.fid as fid2, c1.fno as fno2, c1.fpo as fpo2, c1.cara, c1.nombre, c1.nivel, c1.ponderacionCara as pondC, avg(c1.cumplimiento) as cumpC, avg(cumplimiento)*c1.ponderacionCara, 5*c1.ponderacionCara, avg(cumplimiento)*20    \n"
                + "from(\n"
                + "SELECT factor.id AS fid,  factor.nombre AS fno, ponderacionfactor.ponderacion AS fpo, caracteristica.id as cara, \n"
                + "caracteristica.nombre as nombre, ponderacioncaracteristica.nivelimportancia as nivel, ponderacioncaracteristica.ponderacion as ponderacionCara, \n"
                + "																	 (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+\n"
                + "                                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+\n"
                + "                                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+\n"
                + "                                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+\n"
                + "                                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/\n"
                + "                                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') \n"
                + "																					THEN 1 else null END))\n"
                + "                            \n"
                + "                         as cumplimiento\n"
                + "                                   FROM factor\n"
                + "                                   INNER JOIN caracteristica ON caracteristica.factor_id = factor.id\n"
                + "                                   INNER JOIN ponderacionfactor ON ponderacionfactor.factor_id = factor.id\n"
                + "                                   INNER JOIN ponderacioncaracteristica ON ponderacioncaracteristica.caracteristica_id = caracteristica.id\n"
                + "                                   INNER JOIN indicador ON indicador.caracteristica_id = caracteristica.id\n"
                + "                                   LEFT JOIN numericadocumental ON numericadocumental.indicador_id = indicador.id\n"
                + "                                   LEFT JOIN pregunta ON pregunta.indicador_id = indicador.id\n"
                + "                                   LEFT JOIN resultadoevaluacion ON resultadoevaluacion.pregunta_id = pregunta.id\n"
                + "                                   group by indicador.id\n"
                + "                \n"
                + "                ) as c1\n"
                + "                \n"
                + "                group by c1.cara) as c2\n"
                + "                group by c2.fid2";

        matrizFactores = conSql.CargarSql2(sql3, bd);
        for (int f = 0; f < matrizFactores.getRowCount(); f++) {
            float sumaPon = (float) 0.0;
            float suma2 = (float) 0.0;
            for (int i = 0; i < matrizCaracteristicas.getRowCount(); i++) {
                if (matrizFactores.getRowsByIndex()[f][0] == matrizCaracteristicas.getRowsByIndex()[i][8]) {
                    if (matrizCaracteristicas.getRowsByIndex()[i][4] != null) {
                        sumaPon += Float.parseFloat("" + matrizCaracteristicas.getRowsByIndex()[i][3]);
                        suma2 += Float.parseFloat("" + matrizCaracteristicas.getRowsByIndex()[i][4]) * Float.parseFloat("" + matrizCaracteristicas.getRowsByIndex()[i][3]);
                    }

                }

            }
            if (sumaPon != 0) {
                matrizFactores.getRowsByIndex()[f][3] = (float) (Math.rint(suma2 / sumaPon * 10) / 10);
            }

        }



        session.setAttribute("matrizFactores1", matrizFactores);
        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/informe/informeMatrizFactP.jsp";
        return url;
    }
}
