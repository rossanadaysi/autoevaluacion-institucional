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

        String instrumentoId="2";
        Result rs2 = null;

        //Cambiar proceso
        String sql= "SELECT indicador.id, indicador.nombre, numericadocumental.documento, numericadocumental.responsable, numericadocumental.medio, "
                + "numericadocumental.lugar, numericadocumental.evaluacion, numericadocumental.accion  FROM indicador "
                + "LEFT JOIN numericadocumental ON indicador.id = numericadocumental.indicador_id "
                + "INNER JOIN instrumentohasindicador ON indicador.id = instrumentohasindicador.indicador_id "
                + "WHERE instrumentohasindicador.instrumento_id ='" + instrumentoId + "' AND (proceso_id ='" + idProceso + "' OR proceso_id IS NULL)";       
        
        rs2 = conSql.CargarSql2(sql, bd);

        Result rs = null;
        rs = conSql.CargarSql2("Select indicador.id, indicador.codigo, factor.id, indicador.nombre from indicador"
                + " inner join instrumentohasindicador on indicador.id = instrumentohasindicador.indicador_id"
                + " inner join caracteristica on caracteristica.id = indicador.caracteristica_id"
                + " inner join factor on factor.id = caracteristica.factor_id "
                + " where instrumentohasindicador.instrumento_id = 2 order by indicador.id", bd);
        session.setAttribute("auxInfoDocumental", 1);


        if (rs2.getRowCount() > 0) {
            session.setAttribute("auxInfoDocumental", 1);
            session.setAttribute("evaluarcionDocumental", rs2);
            session.setAttribute("indicadoresDocumental", rs);
        } else {
            session.setAttribute("auxInfoDocumental", 0);
            session.setAttribute("indicadoresDocumental", rs);
            conSql.cerrarConexion();

        }

        conSql.cerrarConexion();

        return url;
    }
}
