/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.navigation;

import controller.sqlController;
import entity.controller.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;
import model.Action;

/**
 *
 * @author Arturo González
 */
public class navegacion implements Action {

    public String procesar(HttpServletRequest request)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        ProgramaJpaController conPrograma = new ProgramaJpaController();
        PrivilegioJpaController conPivilegio = new PrivilegioJpaController();
        RepresentanteJpaController conRepresentante = new RepresentanteJpaController();
        FactorJpaController conFactor = new FactorJpaController();
        IndicadorJpaController conIndicador = new IndicadorJpaController();
        CaracteristicaJpaController conCaracteristica = new CaracteristicaJpaController();
        PreguntaJpaController conPregunta = new PreguntaJpaController();
        EncuestaJpaController conEncuesta = new EncuestaJpaController();

        String path = request.getParameter("action");

        if (path.equals("CrearProcesoAI")) {
            path = "autoevaluacionInstitucional/proceso/crear";
        } else if (path.equals("detalleProcesoAI")) {
            path = "autoevaluacionInstitucional/proceso/detalle";
        } else if (path.equals("menuAI")) {
            path = "autoevaluacionInstitucional/menu";
        } else if (path.equals("selectorAsignarEncuestasAI")) {
            path = "autoevaluacionInstitucional/proceso/encuesta/selectorAsignarE";
        } else if (path.equals("ponderacionAjax")) {
            path = "autoevaluacionInstitucional/proceso/ponderacion/ponderacionAjax";
        } else if (path.equals("conglomerado")) {
            path = "autoevaluacionInstitucional/proceso/muestra/cargarConglomerado";
        } else if (path.equals("configurarParametrosMuestraAI")) {
            path = "autoevaluacionInstitucional/proceso/muestra/cargarConfigurarParametros";
        } else if (path.equals("nuevoConglomeradoAI")) {
            sqlController conSql = new sqlController();
            String bd = (String) session.getAttribute("bd");
            Result rs = conSql.CargarSql2("Select*  from criterio", bd);
            session.setAttribute("listCriterio", rs);
            path = "autoevaluacionInstitucional/proceso/muestra/nuevoConglomerado";
        } else if (path.equals("conglomeradoExistenteAI")) {
            sqlController conSql = new sqlController();
            String bd = (String) session.getAttribute("bd");
            Result rs = conSql.CargarSql2("Select*  from criterio", bd);
            session.setAttribute("listCriterio", rs);
            path = "autoevaluacionInstitucional/proceso/muestra/conglomeradosExistentes";
        } else if (path.equals("selectorAsignarMuestraAI")) {
            path = "autoevaluacionInstitucional/proceso/muestra/selectorAsignarM";
        } else if (path.equals("selectorAsignarMuestra2AI")) {
            path = "autoevaluacionInstitucional/proceso/muestra/selectorAsignarM2";
        } else if (path.equals("selectorAsignarMuestra3AI")) {
            path = "autoevaluacionInstitucional/proceso/muestra/selectorAsignarM3";
        } else if (path.equals("muestraCalculada")) {
            path = "autoevaluacionInstitucional/proceso/muestra/muestraCalculada";
        } else if (path.equals("indexCC")) {
            path = "comiteCentral/index";
        } else if (path.equals("listarProceso")) {
            path = "autoevaluacionInstitucional/proceso/listarP";
        } else if (path.equals("listarPonderacionFactor")) {
            path = "autoevaluacionInstitucional/proceso/ponderacion/listarF";
        } else if (path.equals("listarPonderacionCaracteristica")) {
            path = "autoevaluacionInstitucional/proceso/ponderacion/listarC";
        } else if (path.equals("listarEvaluarDoc")) {
            path = "autoevaluacionInstitucional/proceso/documentalNumerica/listarEvaluarDoc";
        } else if (path.equals("listarEvaluarNum")) {
            path = "autoevaluacionInstitucional/proceso/documentalNumerica/listarEvaluarNum";
        } else if (path.equals("detalleProceso")) {
            path = "autoevaluacionInstitucional/proceso/detalleP";
        } else if (path.equals("validar1")) {
            path = "autoevaluacionInstitucional/proceso/validar1";
        } else if (path.equals("validar2")) {
            path = "autoevaluacionInstitucional/proceso/validar2";
        } else if (path.equals("listarRepresentante")) {
            path = "comiteCentral/representante/listar";
            session.setAttribute("listrepresentantes", conRepresentante.findRepresentanteEntities());
        } else if (path.equals("crearRepresentante")) {
            session.setAttribute("programas", conPrograma.findProgramaEntities());
            session.setAttribute("privilegios", conPivilegio.findPrivilegioEntities());
            path = "comiteCentral/representante/crear";
        } else if (path.equals("indexAI")) {
            path = "autoevaluacionInstitucional/index2";
        } else if (path.equals("listarFactoresCC")) {
            session.setAttribute("listfactores", conFactor.findFactorEntities());
            path = "comiteCentral/factor/listar";
        } else if (path.equals("listarIndicadoresCC")) {
            session.setAttribute("listindicadores", conIndicador.findIndicadorEntities());
            path = "comiteCentral/indicador/listar";
        } else if (path.equals("listarCaracteristicasCC")) {
            session.setAttribute("listcaracteristicas", conCaracteristica.findCaracteristicaEntities());
            path = "comiteCentral/caracteristica/listar";
        } else if (path.equals("listarPreguntasCC")) {
            session.setAttribute("listpreguntas", conPregunta.findPreguntaEntities());
            path = "comiteCentral/pregunta/listar";
        } else if (path.equals("listarEncuestasCC")) {
            session.setAttribute("listencuestas", conEncuesta.findEncuestaEntities());
            path = "comiteCentral/encuesta/listar";
        } else if (path.equals("indexF")) {
            path = "fuente/index";
        } else if (path.equals("inicio")) {
            path = "fuente/inicio";
        }



        String url = "/WEB-INF/vista/" + path + ".jsp";



        return url;
    }
}
