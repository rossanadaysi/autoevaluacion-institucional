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
         String sql = "select indicador.id, indicador.nombre, numericadocumental.documento, numericadocumental.responsable, numericadocumental.medio, numericadocumental.lugar, numericadocumental.evaluacion, numericadocumental.accion "
                 + "from numericadocumental "
                 + "inner join indicador on numericadocumental.indicador_id = indicador.id "
                 + "inner join instrumentohasindicador on indicador.id = instrumentohasindicador.indicador_id "
                 + "where instrumentohasindicador.instrumento_id = '" + instrumentoId + "' and numericadocumental.proceso_id = '" + idProceso + "'";
        
        rs2 = conSql.CargarSql2(sql, bd);

        Result rs = null;
        rs = conSql.CargarSql2("Select indicador.id, indicador.codigo, factor.id, indicador.nombre from indicador"
                + " inner join instrumentohasindicador on indicador.id = instrumentohasindicador.indicador_id"
                + " inner join caracteristica on caracteristica.id = indicador.caracteristica_id"
                + " inner join factor on factor.id = caracteristica.factor_id "
                + " where instrumentohasindicador.instrumento_id = '" + instrumentoId + "' order by indicador.id", bd);
        session.setAttribute("auxInfoDocumental", 1);


        if (rs2.getRowCount() > 0) {
            sql = "SELECT indicador.id, indicador.nombre, numericadocumental.documento, numericadocumental.responsable, numericadocumental.medio, numericadocumental.lugar, numericadocumental.evaluacion, numericadocumental.accion, numericadocumental.id, indicador.codigo, factor.id "
                    + "FROM indicador "
                    + "INNER JOIN instrumentohasindicador ON instrumentohasindicador.indicador_id = indicador.id "
                    + "INNER JOIN instrumento ON instrumento.id = instrumentohasindicador.instrumento_id "
                    + "INNER JOIN caracteristica ON caracteristica.id = indicador.caracteristica_id "
                    + "INNER JOIN factor ON factor.id = caracteristica.factor_id "
                    + "LEFT JOIN numericadocumental ON ( numericadocumental.indicador_id = indicador.id AND numericadocumental.instrumento_id = instrumento.id ) "
                    + "WHERE instrumento.id ='"+instrumentoId+"' ORDER BY indicador.id";

            rs2 = conSql.CargarSql2(sql, bd);
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
