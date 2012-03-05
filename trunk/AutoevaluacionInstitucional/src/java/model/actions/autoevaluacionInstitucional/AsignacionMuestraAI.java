/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.autoevaluacionInstitucional;

import controller.formController;
import controller.sqlController;
import entity.Proceso;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;
import model.Action;

/**
 *
 * @author Arturo González
 */
public class AsignacionMuestraAI implements Action {

    public String procesar(HttpServletRequest request)
            throws IOException, ServletException {

        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/muestra/asignarM.jsp";
        HttpSession session = request.getSession();

        sqlController conSql = new sqlController();
        Proceso proceso = (Proceso) session.getAttribute("proceso");
        int idProceso = proceso.getId();
        String bd = (String) session.getAttribute("bd");

        int idMuestra = 0;

        ResultSet rs3 = null;

        String sql2 = null;
        int ayu = 0;

        sql2 = "Select id from muestra where proceso_id = " + idProceso;
        rs3 = conSql.CargarSql(sql2, bd);
        try {


            while (rs3.next()) {
                ayu = 1;
                idMuestra = Integer.parseInt(rs3.getString(1));
                session.setAttribute("idMuestra", idMuestra);
            }

        } catch (SQLException ex) {
            Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (ayu == 0) {
            System.out.println("ayuda");
            conSql.UpdateSql("INSERT INTO `" + bd + "`.`muestra` (`id`, `formula`, `proceso_id`) VALUES (NULL, NULL, '" + idProceso + "')", bd);
            try {


                while (rs3.next()) {
                    ayu = 1;
                    idMuestra = Integer.parseInt(rs3.getString(1));
                    session.setAttribute("idMuestra", idMuestra);
                }

            } catch (SQLException ex) {
                Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        Result rs2 = null;
        sql2 = "Select* from fuente";
        rs2 = conSql.CargarSql2(sql2, bd);


        if (rs2.getRowCount() != 0) {
            session.setAttribute("fuentes", rs2);
        }

        rs2 = null;
        sql2 = "Select* from programa order by programa.nombre";
        rs2 = conSql.CargarSql2(sql2, bd);


        if (rs2.getRowCount() != 0) {
            session.setAttribute("programas", rs2);
        }

        conSql.cerrarConexion();
        return url;
    }
}
