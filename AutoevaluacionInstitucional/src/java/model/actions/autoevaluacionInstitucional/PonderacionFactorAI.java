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
 * @author Arturo GonzÃ¡lez
 */
public class PonderacionFactorAI implements Action {

    public String procesar(HttpServletRequest request)
            throws IOException, ServletException {

        HttpSession session = request.getSession();

        sqlController conSql = new sqlController();
        Proceso proceso = (Proceso) session.getAttribute("proceso");
        int idProceso = proceso.getId();
        String bd = (String) session.getAttribute("bd");
        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/ponderacion/asignarF.jsp";


        Result rs2 = null;
        String sql = "Select factor.id, ponderacion, justificacion, proceso_id, factor_id, nombre from ponderacionfactor inner join factor on ponderacionfactor.factor_id = factor.id where proceso_id = " + idProceso + " order by factor.id";
        rs2 = conSql.CargarSql2(sql, bd);

        Result rs = null;
        rs = conSql.CargarSql2("Select* from factor order by factor.id", bd);
        session.setAttribute("auxAsignarF1", 1);


        if (rs2.getRowCount() > 0) {
            session.setAttribute("auxAsignarF", 1);
            System.out.println("si hay ponderacion de factores");
            session.setAttribute("pondeFactores", rs2);
            session.setAttribute("factores", rs);
        } else {
            session.setAttribute("auxAsignarF", 0);


            session.setAttribute("factores", rs);
            conSql.cerrarConexion();
            System.out.println("no hay ponderacion de factores");

        }

        conSql.cerrarConexion();





        return url;
    }
}
