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


public class informeRealAI implements Action{

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String idencuesta = request.getParameter("encuesta");
        String bd = (String) session.getAttribute("bd");
        sqlController conSql = new sqlController();
        Result preguntas = null;
        String sql2 = "SELECT pregunta.id, pregunta.pregunta, pregunta.tipo FROM `pregunta`"
                + " INNER JOIN encuestahaspregunta ON pregunta.ID = encuestahaspregunta.PREGUNTA_ID"
                + " INNER JOIN encuesta ON encuestahaspregunta.encuesta_id  = encuesta.id "
                + " where encuesta.id = " + idencuesta + "";
        preguntas = conSql.CargarSql2(sql2, bd);
        session.setAttribute("preguntas", preguntas);
        
        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/informe/informeReal.jsp";
        return url;
    }

}
