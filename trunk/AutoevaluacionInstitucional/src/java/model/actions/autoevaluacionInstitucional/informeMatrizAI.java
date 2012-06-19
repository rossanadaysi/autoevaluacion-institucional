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


public class informeMatrizAI implements Action{
@Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String bd = (String) session.getAttribute("bd");
        sqlController conSql = new sqlController();
        Result matriz = null;
        String sql2 = "SELECT caracteristica.id, caracteristica.nombre, nivelimportancia, ponderacioncaracteristica.ponderacion AS pc,  format(avg(respuesta),2) AS cumplimiento,"
+" format(ponderacioncaracteristica.ponderacion* avg(respuesta),2) AS evaluacion,"
+" format(5 * ponderacioncaracteristica.ponderacion ,2) as ideal,"
+" format((ponderacioncaracteristica.ponderacion * avg(respuesta) / (5 * ponderacioncaracteristica.ponderacion))*100,2) as Relacion"
+" FROM caracteristica"
+" INNER JOIN ponderacioncaracteristica ON ponderacioncaracteristica.caracteristica_id = caracteristica.id"
+" INNER JOIN indicador on indicador.caracteristica_id = caracteristica.id"
+" INNER JOIN pregunta on pregunta.indicador_id = indicador.id"
+" INNER JOIN resultadoevaluacion on resultadoevaluacion.pregunta_id=pregunta.id"
+" where pregunta.tipo='elegir 1-5'"
+" group by caracteristica.id";
        matriz = conSql.CargarSql2(sql2, bd);
        session.setAttribute("matriz", matriz);
        
        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/informe/informeMatriz.jsp";
        return url;
    }
}


