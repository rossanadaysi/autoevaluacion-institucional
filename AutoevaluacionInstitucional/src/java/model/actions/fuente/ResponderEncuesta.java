/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.fuente;

import controller.sqlController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;
import model.Action;

public class ResponderEncuesta implements Action {

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        System.out.println("procesando");
        HttpSession session = request.getSession();
        String idencuesta = request.getParameter("idE");
        String bd = (String) session.getAttribute("bd");
        sqlController conSql = new sqlController();
        Result encuesta = null;
        String sql = "Select encuesta.nombre, encuesta.descripcion, encuesta.instrucciones , encuesta.id from encuesta where encuesta.id = " + idencuesta + "";
        encuesta = conSql.CargarSql2(sql, bd);
        session.setAttribute("encuesta", encuesta);
        Result preguntas = null;
        String sql2 = "SELECT pregunta.id, pregunta.pregunta, pregunta.tipo FROM `pregunta`"
                + " INNER JOIN encuestahaspregunta ON pregunta.ID = encuestahaspregunta.PREGUNTA_ID"
                + " INNER JOIN encuesta ON encuestahaspregunta.encuesta_id  = encuesta.id "
                + " where encuesta.id = "+idencuesta+"";
        preguntas = conSql.CargarSql2(sql2, bd);
        session.setAttribute("preguntas", preguntas);



        String url = "/WEB-INF/vista/fuente/responderEncuesta.jsp";
        return url;
    }
}
