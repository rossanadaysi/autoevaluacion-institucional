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
public class PonderacionCaracteristicaAI implements Action {

    public String procesar(HttpServletRequest request)
            throws IOException, ServletException {


        HttpSession session = request.getSession();

        sqlController conSql = new sqlController();
        Proceso proceso = (Proceso) session.getAttribute("proceso");
        int idProceso = proceso.getId();
        String bd = (String) session.getAttribute("bd");
        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/ponderacion/asignarC.jsp";


        Result rs3 = null;
        String sql3 = "Select factor.id, ponderacion, justificacion, proceso_id, factor_id, nombre from ponderacionfactor inner join factor on ponderacionfactor.factor_id = factor.id where proceso_id = " + idProceso;
        rs3 = conSql.CargarSql2(sql3, bd);

        if (rs3.getRowCount() > 0) {

            session.setAttribute("auxAsignarC1", 1);


            Result rs = null;
            String sql = "Select caracteristica.id, ponderacion, justificacion, proceso_id, caracteristica_id, nombre, nivelimportancia from ponderacioncaracteristica inner join caracteristica on ponderacioncaracteristica.caracteristica_id = caracteristica.id where proceso_id = " + idProceso + " order by caracteristica.id";
            rs = conSql.CargarSql2(sql, bd);

            Result rs2 = null;
            rs2 = conSql.CargarSql2("Select* from caracteristica order by caracteristica.id", bd);

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
        } else {
            System.out.println("negativo");
            session.setAttribute("auxAsignarC1", 0);
        }

        return url;
    }
}
