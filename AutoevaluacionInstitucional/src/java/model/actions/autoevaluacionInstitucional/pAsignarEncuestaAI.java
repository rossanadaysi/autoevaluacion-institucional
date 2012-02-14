/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.autoevaluacionInstitucional;

import controller.sqlController;
import entity.Asignacionencuesta;
import entity.Proceso;
import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Action;

/**
 *
 * @author Arturo González
 */
public class pAsignarEncuestaAI implements Action {

    public String procesar(HttpServletRequest request)
            throws IOException, ServletException {



        String url = "/ControllerAI?action=asignarEncuestaAI";
        HttpSession session = request.getSession();
        Proceso proceso = (Proceso) session.getAttribute("proceso");
        Asignacionencuesta ae = new Asignacionencuesta();

        sqlController conSql = new sqlController();

        try {
            String idFuente = request.getParameter("fuente");

            int id = Integer.valueOf(idFuente);

            String bd = (String) session.getAttribute("bd");

            /*  String sql = "Select* from fuente where id = " + id;
            
            ResultSet rs2 = conSql.CargarSql(sql, bd);
            
            
            Fuente fuente = (Fuente) rs2.getArray(0);*/

            ResultSet rs = null;
            String sql = "Select* from encuesta";

            rs = conSql.CargarSql(sql, bd);


            while (rs.next()) {
                if (request.getParameter(rs.getString(2)).equals("1")) {
                    String sql2 = "INSERT INTO `asignacionencuesta` (`id`, `proceso_id`, `fuente_id`, `encuesta_id`) VALUES (NULL, '" + proceso.getId() + "', '" + id + "', '" + rs.getString(0) + "')";
                    conSql.UpdateSql(sql2, bd);
                }
            }



        } catch (Exception ex) {
        }

        return url;
    }
}
