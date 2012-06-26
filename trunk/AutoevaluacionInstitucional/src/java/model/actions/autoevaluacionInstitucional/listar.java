/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.autoevaluacionInstitucional;

import controller.sqlController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;
import model.Action;

/**
 *
 * @author Usuario
 */
public class listar implements Action {

    public String procesar(HttpServletRequest request)
            throws IOException, ServletException {
        String path = request.getParameter("action");

        if (path.equals("descripcionConglomerado")) {
            HttpSession session = request.getSession();
            sqlController conSql = new sqlController();
            String bd = (String) session.getAttribute("bd");
            String conglomerado = request.getParameter("conglomerado");

            String aux = (String) request.getParameter("selectCriterio");
            session.setAttribute("criterio", aux);

            String criterio = (String) request.getParameter("selectCriterio");

            Result r = conSql.CargarSql2("Select nombre from descripcioncriterio where criterio_id = '" + criterio + "'", bd);
            session.setAttribute("listDescripcionCriterio", r);

            path = "autoevaluacionInstitucional/proceso/muestra/descripcionConglomerado";
        }

        String url = "/WEB-INF/vista/" + path + ".jsp";



        return url;
    }
}
