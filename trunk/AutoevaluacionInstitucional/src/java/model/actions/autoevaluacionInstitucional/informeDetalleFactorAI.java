package model.actions.autoevaluacionInstitucional;

import controller.sqlController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;
import model.Action;


public class informeDetalleFactorAI implements Action{

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String bd = (String) session.getAttribute("bd");
        String idF = (String) request.getParameter("idF");
        sqlController conSql = new sqlController();
        Result detalleFactor = null;
        String sql2 = "SELECT factor.id AS fid, factor.nombre AS fno, ponderacionfactor.ponderacion, caracteristica.id, caracteristica.nombre,"
                + " ponderacioncaracteristica.nivelimportancia as importancia, ponderacioncaracteristica.ponderacion as ponderacion, format(avg( respuesta ),2) AS cumplimiento,"
                + " format(ponderacioncaracteristica.ponderacion* avg(respuesta),2) AS evaluacion,"
                + " format(5 * ponderacioncaracteristica.ponderacion ,2) as ideal,"
                + " format((ponderacioncaracteristica.ponderacion * avg(respuesta) / (5 * ponderacioncaracteristica.ponderacion))*100,2) as Relacion"
                + " FROM factor"
                + " INNER JOIN caracteristica ON caracteristica.factor_id = factor.id"
                + " INNER JOIN ponderacionfactor ON ponderacionfactor.factor_id = factor.id"
                + " INNER JOIN ponderacioncaracteristica ON ponderacioncaracteristica.caracteristica_id = caracteristica.id"
                + " INNER JOIN indicador ON indicador.caracteristica_id = caracteristica.id"
                + " INNER JOIN pregunta ON pregunta.indicador_id = indicador.id"
                + " INNER JOIN resultadoevaluacion ON resultadoevaluacion.pregunta_id = pregunta.id"
                + " WHERE pregunta.tipo = 'elegir 1-5' and factor.id="+idF+" and resultadoevaluacion.respuesta!=0"
                + " GROUP BY caracteristica.id";
        detalleFactor = conSql.CargarSql2(sql2, bd);
        session.setAttribute("detalleFactor", detalleFactor);
        

        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/informe/informeDetalleFactor.jsp";
        return url;
    }
}
