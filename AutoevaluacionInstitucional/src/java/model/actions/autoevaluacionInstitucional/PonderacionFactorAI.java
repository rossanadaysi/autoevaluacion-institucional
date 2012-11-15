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


        String idMuestra = "";
        String sql2 = "Select id from muestra where proceso_id = " + idProceso;
        ResultSet rs3 = conSql.CargarSql(sql2, bd);
        try {

            while (rs3.next()) {
                idMuestra = rs3.getString(1);
                session.setAttribute("idMuestra", idMuestra);
            }

        } catch (SQLException ex) {
            Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
        }


        String sql1 = "select c1.total, c1.terminados, format((c1.terminados*100/c1.total),1) as porcentaje,(c1.total-c1.terminados) as faltantes, 100-format((c1.terminados*100/c1.total),1) as porFal "
                + " from("
                + " select ("
                + " (select count(*) from muestraestudiante where muestraestudiante.muestra_id=" + idMuestra + ")+"
                + " (select count(*) from muestradocente where muestradocente.muestra_id=" + idMuestra + ") +"
                + " (select count(*) from muestraadministrativo where muestraadministrativo.muestra_id=" + idMuestra + ")+"
                + " (select count(*) from muestraegresado where muestraegresado.muestra_id=" + idMuestra + ")+"
                + " (select count(*) from muestradirector where muestradirector.muestra_id=" + idMuestra + ")+"
                + " (select count(*) from muestraagencia where muestraagencia.muestra_id=" + idMuestra + ")+"
                + " (select count(*) from muestraempleador where muestraempleador.muestra_id=" + idMuestra + ")) AS total "
                + " ,(select count(`persona_id`) as terminados from encabezado where "
                + " estado='terminado') as terminados"
                + " ) AS c1";
        ResultSet rs = conSql.CargarSql(sql1, bd);

        try {

            while (rs.next()) {
                String por = rs.getString(3);
                session.setAttribute("porceEstadoProceso", por + "%");
            }

        } catch (SQLException ex) {
            Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
        }

        conSql.cerrarConexion();
        return url;
    }
}
