/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.autoevaluacionInstitucional;

import controller.sqlController;
import entity.Persona;
import entity.Proceso;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;
import model.Action;

public class EncuestaAleatoriaAI implements Action {

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String bd = (String) session.getAttribute("bd");
        int idencuesta = 0;
        int idEncabezadoExistente = 0;
        sqlController conSql = new sqlController();
        boolean busca = true;
        int limite = 0;
        while (busca && limite<20) {

            int idsEncuestas[] = {1, 2, 3, 4, 5, 6, 7};
            Random generador = new Random();
            idencuesta = idsEncuestas[generador.nextInt(idsEncuestas.length)];
            
            //

            Result encuesta = null;

            String sql = "Select encuesta.nombre, encuesta.descripcion, encuesta.instrucciones , encuesta.id from encuesta where encuesta.id = " + idencuesta + "";
            encuesta = conSql.CargarSql2(sql, bd);
            session.setAttribute("encuesta", encuesta);

            Result preguntas = null;
            String sql2 = "SELECT pregunta.id, pregunta.pregunta, pregunta.tipo FROM `pregunta`"
                    + " INNER JOIN encuestahaspregunta ON pregunta.ID = encuestahaspregunta.PREGUNTA_ID"
                    + " INNER JOIN encuesta ON encuestahaspregunta.encuesta_id  = encuesta.id "
                    + " where encuesta.id = " + idencuesta + "";
            preguntas = conSql.CargarSql2(sql2, bd);
            session.setAttribute("preguntas", preguntas);




            Proceso p = (Proceso) session.getAttribute("proceso");

            Persona per = (Persona) session.getAttribute("persona");
            String sqlPreguntando = "SELECT `id`"
                    + " FROM `encabezado`"
                    + " WHERE `proceso_id` =" + p.getId() + ""
                    + " AND `encuesta_id` =" + encuesta.getRowsByIndex()[0][3] + ""
                    + " AND estado = 'terminado'"
                    + " ORDER BY RAND() LIMIT 1";


            ResultSet rs44 = conSql.CargarSql(sqlPreguntando, bd);

            
            try {
                while (rs44.next()) {
                    idEncabezadoExistente = rs44.getInt(1);
                }
            } catch (SQLException ex) {
                System.out.println("una f excepcion");
            }
            if (idEncabezadoExistente != 0) {
                busca = false;
            }
            limite++;
        }




        String ResultadosEvaluacion = "SELECT pregunta.id, pregunta.pregunta, pregunta.tipo, resultadoevaluacion.respuesta FROM `pregunta`"
                + " INNER JOIN encuestahaspregunta ON pregunta.ID = encuestahaspregunta.PREGUNTA_ID"
                + " INNER JOIN encuesta ON encuestahaspregunta.encuesta_id  = encuesta.id "
                + " INNER JOIN encabezado ON encuesta.id  = encabezado.encuesta_id "
                + " INNER JOIN resultadoevaluacion ON (encabezado.id  = resultadoevaluacion.encabezado_id) AND (pregunta.id  = resultadoevaluacion.pregunta_id)"
                + " where encuesta.id =" + idencuesta + " and encabezado.id=" + idEncabezadoExistente + "";


        Result respuestas = conSql.CargarSql2(ResultadosEvaluacion, bd);
        session.setAttribute("respuestas", respuestas);


        //
        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/informe/encuestaAleatoria.jsp";
        return url;
    }
}
