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
 * @author Arturo
 */
public class infoDocumentalAI implements Action {

    public String procesar(HttpServletRequest request)
            throws IOException, ServletException {

        HttpSession session = request.getSession();

        sqlController conSql = new sqlController();
        Proceso proceso = (Proceso) session.getAttribute("proceso");
        int idProceso = proceso.getId();
        String bd = (String) session.getAttribute("bd");
        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/documentalNumerica/evaluarDocumental.jsp";


        Result rs2 = null;
        
        //Cambiar proceso
        String sql = "Select indicador.id, indicador.nombre, numericadocumental.nombre, numericadocumental.accion, numericadocumental.responsable from numericadocumental inner join indicador on numericadocumental.indicador_id = indicador.id where indicador.id = (select indicador_id from instrumentohasindicador WHERE  instrumento_id = 1)";
        rs2 = conSql.CargarSql2(sql, bd);

        Result rs = null;
        rs = conSql.CargarSql2("Select* from indicador order by factor.id", bd);
        session.setAttribute("auxAsignarF1", 1);


        if (rs2.getRowCount() > 0) {
            session.setAttribute("auxAsignarF", 1);
            session.setAttribute("pondeFactores", rs2);
            session.setAttribute("factores", rs);
        } else {
            session.setAttribute("auxAsignarF", 0);


            session.setAttribute("factores", rs);
            conSql.cerrarConexion();

        }

        conSql.cerrarConexion();

        return url;
    }
}
