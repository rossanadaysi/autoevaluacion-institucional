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
import model.Action;

/**
 *
 * @author Arturo González
 */
public class CrearProcesoAI implements Action {

    public String procesar(HttpServletRequest request)
            throws IOException, ServletException {


        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/crearP.jsp";
        HttpSession session = request.getSession();

        sqlController conSql = new sqlController();
        Proceso proceso = null;
        if (session.getAttribute("proceso") == null) {
            System.out.println("null");
        } else {
            proceso = (Proceso) session.getAttribute("proceso");
            int idProceso = proceso.getId();
            String bd = (String) session.getAttribute("bd");
            System.out.println("not null");
            session.setAttribute("descripcionProceso", proceso.getDescripcion());
        }
        /*
         * try {
         *
         * Result rs = null; String sql = "Select* from asignacionencuesta where
         * proceso_id = " + idProceso + ""; rs = conSql.CargarSql2(sql, bd);
         *
         *
         * if (rs.getRowCount() != 0) { session.setAttribute("aux", 0);
         * System.out.println("si hay asignacion de encuestas"); } else {
         * System.out.println("no hay asignacion de encuestas");
         * session.setAttribute("aux", 1);
         *
         * Result rs2 = null; String sql2 = "Select* from encuesta"; rs2 =
         * conSql.CargarSql2(sql2, bd);
         *
         *
         * if (rs2 != null) { session.setAttribute("encuestas", rs2); }
         *
         * sql2 = "Select* from fuente"; rs2 = conSql.CargarSql2(sql2, bd); if
         * (rs2.getRowCount() != 0) { session.setAttribute("fuentes", rs2);
         *
         * }
         *
         * }
         *
         * } catch (Error ex) { //
         * Logger.getLogger(fontController.class.getName()).log(Level.SEVERE,
         * null, ex); }
         *
         *
         *
         *
         *
         *
         * conSql.cerrarConexion();
         */

        return url;
    }
}
