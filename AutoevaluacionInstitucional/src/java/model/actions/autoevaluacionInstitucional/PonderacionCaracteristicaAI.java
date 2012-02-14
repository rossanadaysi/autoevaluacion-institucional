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

        try {

            ResultSet rs = null;
            String sql = "Select* from ponderacioncaracteristica where proceso_id = " + idProceso + "";
            System.out.println(sql);
            rs = conSql.CargarSql(sql, bd);


            if (rs.next()) {
                session.setAttribute("auxAsignarC", 0);
                System.out.println("si hay ponderacion de caracteristicas");
            } else {
                session.setAttribute("auxAsignarC", 1);
                System.out.println("no hay ponderacion de caracteristicas");
                Result rs2 = null;
                rs2 = conSql.CargarSql2("Select* from caracteristica", bd);

                session.setAttribute("caracteristicas", rs2);
                conSql.cerrarConexion();
                System.out.println("no hay ponderacion de caracteristicas");

            }


        } catch (SQLException ex) {
            //  Logger.getLogger(fontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return url;
    }
}
