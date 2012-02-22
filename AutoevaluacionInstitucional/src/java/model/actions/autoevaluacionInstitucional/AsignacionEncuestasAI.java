/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.autoevaluacionInstitucional;

import controller.sqlController;
import entity.Proceso;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;
import model.Action;

/**
 *
 * @author Arturo González
 */
public class AsignacionEncuestasAI implements Action {

    public String procesar(HttpServletRequest request)
            throws IOException, ServletException {


        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/encuesta/asignarE.jsp";
        HttpSession session = request.getSession();

        sqlController conSql = new sqlController();
        Proceso proceso = (Proceso) session.getAttribute("proceso");
        int idProceso = proceso.getId();
        String bd = (String) session.getAttribute("bd");

        Result rs2 = null;
        String sql2 = "Select* from fuente";
        rs2 = conSql.CargarSql2(sql2, bd);

        if (rs2.getRowCount() != 0) {
            session.setAttribute("fuentes", rs2);

        }

        conSql.cerrarConexion();
        return url;
    }
}
