/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.autoevaluacionInstitucional;

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

public class estadoProcesoAI implements Action {

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession session = request.getSession();
        sqlController conSql = new sqlController();
        Proceso proceso2 = (Proceso) session.getAttribute("proceso");
        int idProceso = proceso2.getId();
        String bd = (String) session.getAttribute("bd");
        String url = "/WEB-INF/vista/autoevaluacionInstitucional/proceso/informe/estadoProceso.jsp";
        String idFuenteEst = "1";
        String idFuenteDoc = "2";
        String idFuenteAdm = "3";
        String idFuenteDir = "4";
        String idFuenteEgre = "5";
        String idFuenteEmple = "6";
        String idFuenteAgencia = "7";

        Result tabla1 = null;

        Result rs = null;
        Proceso pro = (Proceso) session.getAttribute("proceso");
        rs = conSql.CargarSql2("Select * from proceso where proceso.id = " + pro.getId(), bd);
        session.setAttribute("detailProceso", rs);
        int idMuestra = 0;
        ResultSet rsMuestra = conSql.CargarSql("Select muestra.id from muestra where muestra.proceso_id=" + pro.getId(), bd);
        try {
            while (rsMuestra.next()) {
                idMuestra = Integer.parseInt(rsMuestra.getString(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(estadoProcesoAI.class.getName()).log(Level.SEVERE, null, ex);
        }


        String sql1 = "select c1.total, c1.terminados, format((c1.terminados*100/c1.total),2) as porcentaje,(c1.total-c1.terminados) as faltantes, 100-format((c1.terminados*100/c1.total),2) as porFal "
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
        tabla1 = conSql.CargarSql2(sql1, bd);
        session.setAttribute("tabla1", tabla1);


        Result tabla2 = null;
        String sql2 = "select * from("
                + " (select count(muestraestudiante.estudiante_id) as totalEstu from muestraestudiante where muestraestudiante.muestra_id=" + idMuestra + ") as totalEst,"
                + " (select count(muestraestudiante.estudiante_id) as estudiantes  from muestraestudiante "
                + " inner join estudiante on muestraestudiante.estudiante_id =estudiante.id"
                + " inner join encabezado on encabezado.persona_id = estudiante.persona_id"
                + " where muestraestudiante.muestra_id=" + idMuestra + " and encabezado.fuente_id=1 and encabezado.estado='terminado') as est,"
                + " (select count(muestradocente.docente_id) as totalDoce from muestradocente where muestradocente.muestra_id=" + idMuestra + ") as totalDoc,"
                + " (select count(muestradocente.docente_id) as docentes from muestradocente "
                + " inner join docente on muestradocente.docente_id =docente.id"
                + " inner join encabezado on encabezado.persona_id = docente.persona_id"
                + " where muestradocente.muestra_id=" + idMuestra + " and encabezado.fuente_id=2 and encabezado.estado='terminado') as doc,"
                + " (select count(muestraadministrativo.administrativo_id) as totalAdmi from muestraadministrativo where muestraadministrativo.muestra_id=" + idMuestra + ") as totalAdm,"
                + " (select count(muestraadministrativo.administrativo_id) as administrativos from muestraadministrativo "
                + " inner join administrativo on muestraadministrativo.administrativo_id =administrativo.id"
                + " inner join encabezado on encabezado.persona_id = administrativo.persona_id"
                + " where muestraadministrativo.muestra_id=" + idMuestra + " and encabezado.fuente_id=3 and encabezado.estado='terminado') as adm,"
                + " (select count(muestradirector.directorprograma_id) as totalDire from muestradirector where muestradirector.muestra_id=" + idMuestra + ") as totalDir,"
                + " (select count(muestradirector.directorprograma_id) as directores from muestradirector "
                + " inner join directorprograma on muestradirector.directorprograma_id=directorprograma.id"
                + " inner join encabezado on encabezado.persona_id = directorprograma.persona_id"
                + " where muestradirector.muestra_id=" + idMuestra + " and encabezado.fuente_id=4 and encabezado.estado='terminado') as dir,"
                + " (select count(muestraegresado.egresado_id) as totalEgre from muestraegresado where muestraegresado.muestra_id=" + idMuestra + ") as totalEgr,"
                + " (select count(muestraegresado.egresado_id) as egresados from muestraegresado "
                + " inner join egresado on muestraegresado.egresado_id=egresado.id"
                + " inner join encabezado on encabezado.persona_id = egresado.persona_id"
                + " where muestraegresado.muestra_id=" + idMuestra + " and encabezado.fuente_id=5 and encabezado.estado='terminado') as egr,"
                + " (select count(muestraempleador.empleador_id) as totalEmpl from muestraempleador where muestraempleador.muestra_id=" + idMuestra + ") as totalEmp, "
                + " (select count(muestraempleador.empleador_id) as empleadores  from muestraempleador "
                + " inner join empleador on muestraempleador.empleador_id=empleador.id"
                + " inner join encabezado on encabezado.persona_id = empleador.persona_id"
                + " where muestraempleador.muestra_id=" + idMuestra + " and encabezado.fuente_id=6 and encabezado.estado='terminado') as emp,"
                + " (select count(muestraagencia.agenciagubernamental_id) as totalAgen from muestraagencia where muestraagencia.muestra_id=" + idMuestra + ") as totalAge, "
                + " (select count(muestraagencia.agenciagubernamental_id) as agencias from muestraagencia "
                + " inner join agenciagubernamental on muestraagencia.agenciagubernamental_id=agenciagubernamental.id"
                + " inner join encabezado on encabezado.persona_id = agenciagubernamental.persona_id"
                + " where muestraagencia.muestra_id=" + idMuestra + " and encabezado.fuente_id=7 and encabezado.estado='terminado') as age)";
        tabla2 = conSql.CargarSql2(sql2, bd);
        session.setAttribute("tabla2", tabla2);

        String sql3 = "SELECT facultad.nombre, programa.id, programa.nombre, Count( * ) , COUNT(CASE WHEN encabezado.fuente_id =" + idFuenteEst + " AND encabezado.estado = 'terminado' THEN 1 END ), facultad.id "
                + "FROM muestraestudiante "
                + "INNER JOIN estudiante ON muestraestudiante.estudiante_id = estudiante.id "
                + "INNER JOIN programa ON estudiante.programa_id = programa.id "
                + "INNER JOIN facultad ON programa.facultad_id = facultad.id "
                + "LEFT JOIN encabezado ON encabezado.persona_id = estudiante.persona_id "
                + "WHERE muestraestudiante.muestra_id = " + idMuestra + " "
                + "GROUP BY facultad.nombre, programa.nombre "
                + "WITH ROLLUP";

        Result estudiantesPorFac = null;
        estudiantesPorFac = conSql.CargarSql2(sql3, bd);

        session.setAttribute("estudiantesPorFac", estudiantesPorFac);

        String sql4 = "SELECT facultad.nombre, programa.id, programa.nombre, Count( * ) , COUNT(CASE WHEN encabezado.fuente_id =" + idFuenteDoc + " AND encabezado.estado = 'terminado' THEN 1 END ), facultad.id "
                + "FROM muestradocente "
                + "INNER JOIN docente ON muestradocente.docente_id = docente.id "
                + "INNER JOIN programa ON docente.programa_id = programa.id "
                + "INNER JOIN facultad ON programa.facultad_id = facultad.id "
                + "LEFT JOIN encabezado ON encabezado.persona_id = docente.persona_id "
                + "WHERE muestradocente.muestra_id = " + idMuestra + " "
                + "GROUP BY facultad.nombre, programa.nombre "
                + "WITH ROLLUP";

        Result docentesPorFac = null;
        docentesPorFac = conSql.CargarSql2(sql4, bd);

        session.setAttribute("docentesPorFac", docentesPorFac);


        String sql5 = "SELECT facultad.nombre, programa.id, programa.nombre, Count( * ) , COUNT(CASE WHEN encabezado.fuente_id =" + idFuenteEgre + " AND encabezado.estado = 'terminado' THEN 1 END ), facultad.id "
                + "FROM muestraegresado "
                + "INNER JOIN egresado ON muestraegresado.egresado_id = egresado.id "
                + "INNER JOIN programa ON egresado.programa_id = programa.id "
                + "INNER JOIN facultad ON programa.facultad_id = facultad.id "
                + "LEFT JOIN encabezado ON encabezado.persona_id = egresado.persona_id "
                + "WHERE muestraegresado.muestra_id = " + idMuestra + " "
                + "GROUP BY facultad.nombre, programa.nombre "
                + "WITH ROLLUP";

        Result egresadosPorFac = null;
        egresadosPorFac = conSql.CargarSql2(sql5, bd);

        session.setAttribute("egresadosPorFac", egresadosPorFac);

        String sql6 = "select criterio.nombre, descripcioncriterio.nombre, Count( * ), COUNT(CASE WHEN encabezado.fuente_id =" + idFuenteAdm + " AND encabezado.estado = 'terminado' THEN 1 END ) "
                + "from muestraadministrativo "
                + "inner join administrativo on muestraadministrativo.administrativo_id =administrativo.id "
                + "inner join muestracriterio on administrativo.persona_id=muestracriterio.persona_id "
                + "inner join descripcioncriterio on muestracriterio.descripcioncriterio_id=descripcioncriterio.id "
                +"inner join criterio  on descripcioncriterio.criterio_id=criterio.id "
                + "left join encabezado on encabezado.persona_id = administrativo.persona_id "
                + "where muestraadministrativo.muestra_id=" + idMuestra + "  "
                + "group by descripcioncriterio.nombre";

        Result administrativosPorFac = null;
        administrativosPorFac = conSql.CargarSql2(sql6, bd);

        session.setAttribute("administrativosPorFac", administrativosPorFac);





        return url;

    }
}
