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

        try {
            ResultSet rs2 = null;
            String sql = "Select* from ponderacionfactor where proceso_id = " + idProceso + "";
            rs2 = conSql.CargarSql(sql, bd);


            if (rs2.next()) {
                session.setAttribute("auxAsignarF", 0);
                System.out.println("si hay ponderacion de factores");
            } else {
                session.setAttribute("auxAsignarF", 1);

                Result rs = null;
                rs = conSql.CargarSql2("Select* from factor", bd);

                session.setAttribute("factores", rs);
                conSql.cerrarConexion();
                System.out.println("no hay ponderacion de factores");

            }
        } catch (SQLException ex) {
            //  Logger.getLogger(fontController.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        conSql.cerrarConexion();





        return url;
    }
}
