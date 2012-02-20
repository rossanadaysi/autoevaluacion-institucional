/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.autoevaluacionInstitucional;

import controller.sqlController;
import entity.Proceso;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;
import model.Action;

/**
 *
 * @author Arturo González
 */
public class PonderacionCaracteristicaAI implements Action {

    public String procesar(HttpServletRequest request)
            throws IOException, ServletException {


        HttpSession session = request.getSession();

        sqlController conSql = new sqlController();
        Proceso proceso = (Proceso) session.getAttribute("proceso");
        int idProceso = proceso.getId();
        String bd = (String) session.getAttribute("bd");
        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/ponderacion/asignarC.jsp";

        Result rs = null;
        String sql = "Select caracteristica.id, ponderacion, justificacion, proceso_id, caracteristica_id, nombre from ponderacioncaracteristica inner join caracteristica on ponderacioncaracteristica.caracteristica_id = caracteristica.id where proceso_id = " + idProceso + "";
        rs = conSql.CargarSql2(sql, bd);

        Result rs2 = null;
        rs2 = conSql.CargarSql2("Select* from caracteristica", bd);

        if (rs.getRowCount() > 0) {
            session.setAttribute("auxAsignarC", 1);
            System.out.println("si hay ponderacion de caracteristicas");
            session.setAttribute("pondeCaracteristicas", rs);
            session.setAttribute("caracteristicas", rs2);
        } else {
            session.setAttribute("auxAsignarC", 0);
            System.out.println("no hay ponderacion de caracteristicas");


            session.setAttribute("caracteristicas", rs2);
            conSql.cerrarConexion();
            System.out.println("no hay ponderacion de caracteristicas");
        }


        return url;
    }
}
