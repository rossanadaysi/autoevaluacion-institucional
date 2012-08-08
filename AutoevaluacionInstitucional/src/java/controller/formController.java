/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Asignacionencuesta;
import entity.Fuente;
import entity.Proceso;
import entity.Programa;
import entity.controller.FuenteJpaController;
import entity.controller.ProcesoJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;
import model.Bean1;
import model.PasswordGenerator;

/**
 *
 * @author Arturo González
 */
@WebServlet(name = "formController", urlPatterns = {"/formController"})
public class formController extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            if (request.getParameter("action").equals("asignarPonderacionFactorAIp")) {
                HttpSession session = request.getSession();
                sqlController conSql = new sqlController();

                int numRows = Integer.parseInt(request.getParameter("count"));
                int idproceso = 0;
                ResultSet rs = null;
                String bd = (String) session.getAttribute("bd");

                Proceso proceso = (Proceso) session.getAttribute("proceso");
                int idProceso = proceso.getId();

                session.setAttribute("idproceso", idproceso);

                Double contador = 0.0;
                for (int i5 = 1; i5 <= numRows; i5++) {
                    Double ponderacion = Double.parseDouble(request.getParameter("ponderacion" + i5));
                    contador = contador + ponderacion;
                }
                if (contador != 100.0) {
                    session.setAttribute("auxAsignarF1", 0);
                } else {
                    session.setAttribute("auxAsignarF1", 1);

                    if (session.getAttribute("auxAsignarF").equals(0)) {

                        for (int i = 1; i <= numRows; i++) {
                            String id = request.getParameter("id" + i);
                            String ponderacion = request.getParameter("ponderacion" + i);
                            String justificacion = request.getParameter("justificacion" + i);



                            conSql.UpdateSql("INSERT INTO `ponderacionfactor` (`id`, `ponderacion`, `justificacion`, `proceso_id`, `factor_id`) VALUES (NULL, '" + ponderacion + "', '" + justificacion + "', '" + idProceso + "', '" + id + "');", bd);

                            //  conSql.UpdateSql("UPDATE `ponderacionfactor` SET `ponderacion` = '" + ponderacion + "',`justificacion` = '" + justificacion + "' WHERE `ponderacionfactor`.`id` ='" + idPonderacion + "'", bd);
                        }

                        session.setAttribute("auxAsignarF", 1);
                        session.setAttribute("auxAsignarC1", 1);

                    } else {

                        for (int i = 1; i <= numRows; i++) {
                            String id = request.getParameter("id" + i);
                            String ponderacion = request.getParameter("ponderacion" + i);
                            String justificacion = request.getParameter("justificacion" + i);
                            int idPonderacion = 0;

                            double ponfa = Double.parseDouble(ponderacion);


                            rs = conSql.CargarSql("Select id from ponderacionfactor where ponderacionfactor.proceso_id = '" + idProceso + "' and ponderacionfactor.factor_id = '" + id + "'", bd);
                            try {
                                while (rs.next()) {
                                    idPonderacion = Integer.parseInt(rs.getString(1));
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                            }


                            conSql.UpdateSql("UPDATE `ponderacionfactor` SET `ponderacion` = '" + ponderacion + "',`justificacion` = '" + justificacion + "' WHERE `ponderacionfactor`.`id` ='" + idPonderacion + "'", bd);


                            if (session.getAttribute("auxAsignarC").equals(1)) {


                                ResultSet rsx = conSql.CargarSql("Select* from ponderacioncaracteristica inner join caracteristica on ponderacioncaracteristica.`caracteristica_id` = caracteristica.id WHERE proceso_id = '" + idProceso + "' and caracteristica.factor_id = '" + id + "'", bd);

                                try {
                                    while (rsx.next()) {



                                        Double ponde = Double.parseDouble(rsx.getString(2));
                                        double suma = 0;

                                        ResultSet rs1 = conSql.CargarSql("SELECT SUM(ponderacioncaracteristica.nivelimportancia) from ponderacioncaracteristica inner join caracteristica on ponderacioncaracteristica.caracteristica_id = caracteristica.id inner join ponderacionfactor on caracteristica.factor_id = ponderacionfactor.factor_id WHERE ponderacioncaracteristica.proceso_id = '" + idProceso + "' and caracteristica.factor_id = (SELECT factor_id from caracteristica where caracteristica.id = '" + rsx.getString(6) + "')", bd);
                                        try {
                                            while (rs1.next()) {
                                                String s = rs1.getString(1);
                                                suma = Double.parseDouble(s);
                                            }
                                        } catch (SQLException ex) {
                                            Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        double a = (100 * ponde) / suma;
                                        double b = ((ponfa * a) / 100);
                                        double r;


                                        int decimalPlaces = 2;
                                        BigDecimal bde = new BigDecimal(b);

// setScale is immutable
                                        bde = bde.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
                                        r = bde.doubleValue();


                                        conSql.UpdateSql("UPDATE `ponderacioncaracteristica` SET `ponderacion` = '" + r + "' WHERE `ponderacioncaracteristica`.`caracteristica_id` = '" + rsx.getString(6) + "'", bd);
                                    }
                                } catch (SQLException ex) {
                                    Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        }//for
                    }
                }

            } else if (request.getParameter(
                    "action").equals("asignarPonderacionCaracteristicaAIp")) {

                HttpSession session = request.getSession();
                sqlController conSql = new sqlController();

                int numRows = Integer.parseInt(request.getParameter("count"));
                int idproceso = 0;
                ResultSet rs = null;
                String bd = (String) session.getAttribute("bd");

                Proceso proceso = (Proceso) session.getAttribute("proceso");
                int idProceso = proceso.getId();

                session.setAttribute("idproceso", idproceso);
                if (session.getAttribute("auxAsignarC").equals(0)) {
                    for (int i = 1; i <= numRows; i++) {
                        String id = request.getParameter("id" + i);
                        String ponderacion = request.getParameter("ponderacion" + i);
                        String justificacion = request.getParameter("justificacion" + i);
                        float ponde = Float.parseFloat(ponderacion);



                        conSql.UpdateSql("INSERT INTO `ponderacioncaracteristica` (`id`, `nivelimportancia`, `ponderacion`, `justificacion`, `proceso_id`, `caracteristica_id`) VALUES (NULL, '" + ponde + "', '" + 0 + "', '" + justificacion + "', '" + idProceso + "', '" + id + "');", bd);



                        int suma = 0;
                        int ponfa = 0;
                    }
                    for (int i = 1; i <= numRows; i++) {
                        String id = request.getParameter("id" + i);
                        String ponderacion = request.getParameter("ponderacion" + i);
                        String justificacion = request.getParameter("justificacion" + i);
                        double ponde = Double.parseDouble(ponderacion);

                        double suma = 0;
                        double ponfa = 0;

                        ResultSet rs1 = conSql.CargarSql("SELECT SUM(ponderacioncaracteristica.nivelimportancia),ponderacionfactor.ponderacion from ponderacioncaracteristica inner join caracteristica on ponderacioncaracteristica.caracteristica_id = caracteristica.id inner join ponderacionfactor on caracteristica.factor_id = ponderacionfactor.factor_id WHERE ponderacioncaracteristica.proceso_id = '" + idProceso + "' and caracteristica.factor_id = (SELECT factor_id from caracteristica where caracteristica.id = '" + id + "')", bd);
                        try {
                            while (rs1.next()) {
                                String s = rs1.getString(1);
                                suma = Double.parseDouble(s);
                                String p = rs1.getString(2);
                                ponfa = Double.parseDouble(p);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        double a = (100 * ponde) / suma;
                        double b = ((ponfa * a) / 100);

                        double r;


                        int decimalPlaces = 2;
                        BigDecimal bde = new BigDecimal(b);

// setScale is immutable
                        bde = bde.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
                        r = bde.doubleValue();
                        conSql.UpdateSql("UPDATE `ponderacioncaracteristica` SET `ponderacion` = '" + r + "' WHERE `ponderacioncaracteristica`.`proceso_id` = '" + idProceso + "' and `ponderacioncaracteristica`.`caracteristica_id` = '" + id + "'", bd);



                    }
                } else {
                    for (int i = 1; i <= numRows; i++) {
                        String id = request.getParameter("id" + i);
                        String ponderacion = request.getParameter("ponderacion" + i);
                        String ponderacion2 = request.getParameter("ponderacion2" + i);
                        String justificacion = request.getParameter("justificacion" + i);
                        int idPonderacion = 0;


                        rs = conSql.CargarSql("Select id from ponderacioncaracteristica where ponderacioncaracteristica.proceso_id = '" + idProceso + "' and ponderacioncaracteristica.caracteristica_id = '" + id + "'", bd);
                        try {
                            while (rs.next()) {
                                idPonderacion = Integer.parseInt(rs.getString(1));
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                        }


                        conSql.UpdateSql("UPDATE `ponderacioncaracteristica` SET `nivelimportancia` = '" + ponderacion + "',`ponderacion` = '" + ponderacion2 + "',`justificacion` = '" + justificacion + "' WHERE `ponderacioncaracteristica`.`id` = '" + idPonderacion + "'", bd);
                    }
                }
            } else if (request.getParameter(
                    "action").equals("cargarPonde")) {

                HttpSession session = request.getSession();
                sqlController conSql = new sqlController();


                String bd = (String) session.getAttribute("bd");

                Proceso proceso = (Proceso) session.getAttribute("proceso");
                int idProceso = proceso.getId();

                String id = request.getParameter("idc");
                String ponderacion = request.getParameter("ponderacion" + id);
                double ponde = Double.parseDouble(ponderacion);


                double suma = 0;
                double ponfa = 0;


                //ResultSet rsa = conSql.CargarSql("select caracteristica.id from caracteristica where caracteristica.factor_id = (select caracteristica.factor_id from caracteristica where caracteristica.id = '" + id + "')", bd);
                ResultSet rsa2 = conSql.CargarSql("select caracteristica.id, ponderacionfactor.ponderacion from caracteristica inner join ponderacionfactor on caracteristica.factor_id = ponderacionfactor . id where caracteristica.factor_id = (select caracteristica.factor_id from caracteristica where caracteristica.id = '" + id + "')", bd);

                try {

                    List<Double> pondera = new ArrayList<Double>();
                    List<Integer> Ids = new ArrayList<Integer>();
                    List<Double> nivelImportancia = new ArrayList<Double>();
                    String aux4 = "{ \"datos\":[";


                    while (rsa2.next()) {

                        String sponfa = rsa2.getString(2);
                        ponfa = Double.parseDouble(sponfa);

                        Ids.add(Integer.parseInt(rsa2.getString(1)));

                        String aux1 = request.getParameter("ponderacion2" + rsa2.getString(1));
                        Double au = Double.parseDouble(aux1);
                        pondera.add(au);

                        String aux2 = request.getParameter("ponderacion" + rsa2.getString(1));
                        Double au2 = Double.parseDouble(aux2);
                        nivelImportancia.add(au2);

                    }

                    for (int j = 0; j < nivelImportancia.size(); j++) {
                        suma += nivelImportancia.get(j);

                    }


                    for (int k = 0; k < nivelImportancia.size(); k++) {

                        double a = (100 * nivelImportancia.get(k)) / suma;
                        double b = ((ponfa * a) / 100);

                        double r;


                        int decimalPlaces = 2;
                        BigDecimal bde = new BigDecimal(b);

                        // setScale is immutable
                        bde = bde.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
                        r = bde.doubleValue();


                        if (k + 1 == nivelImportancia.size()) {
                            String aux5 = ""
                                    + "{"
                                    + " \"Pond\": \"" + r + "\" ,"
                                    + " \"Idc\": \"" + Ids.get(k)
                                    + "\" " + "}"
                                    + "";

                            aux4 += aux5 + "]}";
                        } else {
                            String aux5 = ""
                                    + "{"
                                    + "\"Pond\": \"" + r + "\" ," + " \"Idc\": \"" + Ids.get(k)
                                    + "\""
                                    + "},"
                                    + "";
                            aux4 += aux5;

                        }

                    }

                    out.println("[" + aux4 + "]");
                } catch (SQLException ex) {
                    Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (request.getParameter(
                    "action").equals("asignarEncuestasAIp")) {

                HttpSession session = request.getSession();
                Proceso proceso = (Proceso) session.getAttribute("proceso");
                Asignacionencuesta ae = new Asignacionencuesta();

                sqlController conSql = new sqlController();


                String idFuente = request.getParameter("fuente");

                int id = Integer.valueOf(idFuente);


                String bd = (String) session.getAttribute("bd");
                int idAsignacion;
                int aux = 0;

                if (session.getAttribute("aux_asignarE").equals(1)) {

                    String sql2 = "delete from `asignacionencuesta` where `proceso_id` = '" + proceso.getId() + "' and  `fuente_id` = '" + id + "'";
                    conSql.UpdateSql(sql2, bd);

                }

                ResultSet rs = null;
                String sql = "Select* from encuesta";
                rs = conSql.CargarSql(sql, bd);
                try {
                    while (rs.next()) {

                        if (request.getParameter(rs.getString(2)).equals("1")) {
                            String sql2 = "INSERT INTO `asignacionencuesta` (`id`, `proceso_id`, `fuente_id`, `encuesta_id`) VALUES (NULL, '" + proceso.getId() + "', '" + id + "', '" + rs.getString(1) + "')";
                            conSql.UpdateSql(sql2, bd);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (request.getParameter(
                    "action").equals("selectorAsignarEncuestasAI")) {
                HttpSession session = request.getSession();

                sqlController conSql = new sqlController();
                Proceso proceso = (Proceso) session.getAttribute("proceso");
                int idProceso = proceso.getId();
                String bd = (String) session.getAttribute("bd");

                Result rs2 = null;
                String sql2 = "Select* from encuesta";
                rs2 = conSql.CargarSql2(sql2, bd);
                if (rs2 != null) {
                    session.setAttribute("encuestas", rs2);
                }

                try {
                    String idFuente = request.getParameter("fuente");
                    int id = Integer.valueOf(idFuente);
                    Result rs = null;
                    String sql = "Select* from asignacionencuesta where proceso_id = " + idProceso + " and fuente_id = " + id;
                    rs = conSql.CargarSql2(sql, bd);
                    if (rs.getRowCount() != 0) {
                        System.out.println("si hay asignacion de encuestas");
                        session.setAttribute("encuestasSeleccionadas", rs);
                        session.setAttribute("aux_asignarE", 1);
                    } else {
                        System.out.println("no hay asignacion de encuestas!!!!!");
                        session.setAttribute("aux_asignarE", 0);
                    }
                } catch (Error ex) {
                    //  Logger.getLogger(fontController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (request.getParameter(
                    "action").equals("configurarParametrosMuestraAI")) {
                HttpSession session = request.getSession();
                sqlController conSql = new sqlController();
                String bd = (String) session.getAttribute("bd");
                String conglomerado = request.getParameter("conglomerado");

                session.setAttribute("conglomerado", conglomerado);

                if (conglomerado.equals("programa")) {
                    Result rs = conSql.CargarSql2("Select*  from programa", bd);
                    session.setAttribute("listProgramasMuestra", rs);
                }
                if (conglomerado.equals("nuevoCriterio")) {

                    String criterio = (String) session.getAttribute("criterio");
                    ResultSet rs = conSql.CargarSql("Select nombre from criterio where id = " + criterio, bd);
                    try {
                        while (rs.next()) {
                            session.setAttribute("listNombreCriterio", rs.getString(1));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Result r = conSql.CargarSql2("Select id, nombre from descripcioncriterio where criterio_id = '" + criterio + "'", bd);
                    session.setAttribute("listDescripcionCriterio2", r);
                }
            } else if (request.getParameter(
                    "action").equals("pnuevoConglomeradoAI")) {
                HttpSession session = request.getSession();
                sqlController conSql = new sqlController();
                String bd = (String) session.getAttribute("bd");
                String conglomerado = request.getParameter("conglomerado");


                if (request.getParameter("selectCriterio").equals("nuevoCriterio")) {
                    String criterio = (String) request.getParameter("nombreCriterio");
                    String descripcion = (String) request.getParameter("descripcionCriterio");

                    conSql.UpdateSql("INSERT INTO `criterio` (`id`, `nombre`, `descripcion`) VALUES (NULL, '" + criterio + "', '" + descripcion + "')", bd);



                    String countaux = (String) request.getParameter("countCriterio");
                    int count = Integer.parseInt(countaux);


                    for (int i = 0; i < count; i++) {

                        if (request.getParameter("criterio" + i) != null) {
                            String descripcionCriterio = request.getParameter("criterio" + i);

                            conSql.UpdateSql("INSERT INTO `descripcioncriterio` (`id`, `nombre`, `descripcion`, `criterio_id`) VALUES (NULL, '" + descripcionCriterio + "', '--', (Select id from criterio where nombre = '" + criterio + "'))", bd);
                        }
                    }

                }

            } else if (request.getParameter(
                    "action").equals("asignarMuestraAIp")) {


                HttpSession session = request.getSession();
                Proceso proceso = (Proceso) session.getAttribute("proceso");
                Asignacionencuesta ae = new Asignacionencuesta();

                sqlController conSql = new sqlController();


                String idFuente = request.getParameter("fuente");


                int id = Integer.valueOf(idFuente);


                String bd = (String) session.getAttribute("bd");

                int idMuestra = (Integer) session.getAttribute("idMuestra");

                String tabla = null;
                String tabla1 = null;


                String idP = request.getParameter("programas2");
                String idS = request.getParameter("semestres2");

                //Estatico
                if (id == 1) {

                    tabla = "muestraestudiante";
                    tabla1 = "estudiante";

                } else if (id == 2) {

                    tabla = "muestradocente";
                    tabla1 = "docente";
                }


                String sql2 = "delete t1 from `" + tabla + "` t1 inner join estudiante on t1.estudiante_id = estudiante.id  where `muestra_id` = " + idMuestra + " and estudiante.programa_id = " + idP + " and estudiante.semestre = " + idS;
                conSql.UpdateSql(sql2, bd);


                ResultSet rs = null;
                String sql = "Select* from " + tabla1 + " where estudiante.programa_id = " + idP + " and estudiante.semestre = " + idS;


                rs = conSql.CargarSql(sql, bd);
                try {
                    while (rs.next()) {
                        if (request.getParameter(rs.getString(1)).equals("1")) {
                            sql2 = "INSERT INTO `" + tabla + "` (`id`, `muestra_id`, `" + tabla1 + "_id`) VALUES (NULL, '" + idMuestra + "', '" + rs.getString(1) + "')";
                            conSql.UpdateSql(sql2, bd);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter(
                    "action").equals("selectorAsignarMuestraAI")) {
                HttpSession session = request.getSession();
                sqlController conSql = new sqlController();
                Proceso proceso = (Proceso) session.getAttribute("proceso");
                int idProceso = proceso.getId();
                String bd = (String) session.getAttribute("bd");

                String idFuente = request.getParameter("fuente");
                int id = Integer.valueOf(idFuente);
                int idMuestra = (Integer) session.getAttribute("idMuestra");

                Result rs2 = null;
                String tabla = null;
                String tabla1 = null;
                String sql2 = null;

                try {

                    //Estatico
                    if (id == 1) {
                        tabla = "muestraestudiante";
                        tabla1 = "estudiante";
                    } else if (id == 2) {
                        tabla = "muestradocente";
                        tabla1 = "docente";
                    } else if (id == 3) {
                        tabla = "muestraadministrativo";
                        tabla1 = "administrativo";
                    } else if (id == 4) {
                        tabla = "muestradirector";
                        tabla1 = "directorprograma";
                    } else if (id == 5) {
                        tabla = "muestraegresado";
                        tabla1 = "egresado";
                    } else if (id == 6) {
                        tabla = "muestraempleador";
                        tabla1 = "empleador";
                    } else if (id == 7) {
                        tabla = "muestraagencia";
                        tabla1 = "agenciagubernamental";
                    }

                    session.setAttribute("idFuenteMuestra", id);


                    Result rs = null;
                    String sql = "Select* from " + tabla + " where muestra_id = " + idMuestra;
                    rs = conSql.CargarSql2(sql, bd);

                    if (rs.getRowCount() != 0) {
                        session.setAttribute("muestrasSeleccionadas", rs);
                        session.setAttribute("aux_asignarM", 1);


                        sql = "Select conglomerado from " + tabla + " where muestra_id = " + idMuestra + " LIMIT 1";
                        ResultSet rst = conSql.CargarSql(sql, bd);
                        try {
                            while (rst.next()) {
                                String conglomerado = rst.getString(1);
                                session.setAttribute("conglomeradoFiltro", conglomerado);

                                if (conglomerado.equals("programa")) {
                                    rs = null;
                                    sql = "Select* from programa";
                                    rs = conSql.CargarSql2(sql, bd);
                                    session.setAttribute("descripcionFiltro", rs);
                                } else if (conglomerado.equals("tipo")) {
                                    sql = "Select distinct tipo from docente";
                                    rs = conSql.CargarSql2(sql, bd);
                                    session.setAttribute("descripcionFiltro", rs);
                                } else if (conglomerado.equals("cargo")) {
                                    sql = "Select distinct cargo from adiminstrativo";
                                    rs = conSql.CargarSql2(sql, bd);
                                    session.setAttribute("descripcionFiltro", rs);
                                } else if (conglomerado.equals("sectorempresarial")) {
                                    sql = "Select distinct sectorempresarial from empleador";
                                    rs = conSql.CargarSql2(sql, bd);
                                    session.setAttribute("descripcionFiltro", rs);
                                } else if (conglomerado.equals("nuevoCriterio")) {
                                    sql = "Select distinct descripcioncriterio.id, descripcioncriterio.nombre from muestracriterio inner join descripcioncriterio on muestracriterio.`descripcioncriterio_id` = descripcioncriterio.id where muestracriterio.fuente_id = '" + id + "'";
                                    rs = conSql.CargarSql2(sql, bd);
                                    session.setAttribute("descripcionFiltro", rs);
                                }

                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        //f  System.out.println("no hay asignacion de muestras!!!!!");
                        session.setAttribute("aux_asignarM", 0);
                    }
                } catch (Error ex) {
                    //  Logger.getLogger(fontController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter(
                    "action").equals("selectorAsignarMuestra3AI")) {

                HttpSession session = request.getSession();
                sqlController conSql = new sqlController();
                Proceso proceso = (Proceso) session.getAttribute("proceso");
                int idProceso = proceso.getId();
                String bd = (String) session.getAttribute("bd");

                String idFuente = request.getParameter("fuente");
                int id = Integer.valueOf(idFuente);
                int idMuestra = (Integer) session.getAttribute("idMuestra");



                Result rs2 = null;
                String tabla = null;
                String tabla1 = null;
                String sql2 = null;


                String idP = request.getParameter("programas2");
                String idS = request.getParameter("semestres2");
                int fil = 0;

                try {

                    //Estatico
                    if (id == 1) {
                        tabla = "muestraestudiante";
                        tabla1 = "estudiante";

                        if (!idP.equals("--") && !idS.equals("--")) {
                            fil = 1;
                            sql2 = "Select " + tabla1 + ".id, persona.id, persona.nombre , persona.apellido from " + tabla1 + " inner join persona on " + tabla1 + ".persona_id = persona.id where " + tabla1 + ".programa_id = " + idP
                                    + " and " + tabla1 + ".semestre = " + idS;
                            rs2 = conSql.CargarSql2(sql2, bd);
                            if (rs2 != null) {
                                session.setAttribute("muestras", rs2);
                            }

                            Result rs = null;
                            String sql = "Select* from " + tabla + " where muestra_id = " + idMuestra;
                            rs = conSql.CargarSql2(sql, bd);

                            if (rs.getRowCount() != 0) {
                                //  System.out.println("si hay asignacion de muestras");
                                session.setAttribute("muestrasSeleccionadas", rs);
                                session.setAttribute("aux_asignarM", 1);

                            } else {
                                //f  System.out.println("no hay asignacion de muestras!!!!!");
                                session.setAttribute("aux_asignarM", 0);
                            }
                            session.setAttribute("aux_selectorAsignarM3", 1);
                        } else if (!idP.equals("--")) {
                            session.setAttribute("aux_selectorAsignarM3", 0);
                            //   sql2 = "Select " + tabla1 + ".id, persona.id, persona.nombre, persona.apellido from " + tabla1 + " inner join persona on " + tabla1
                            //  + ".persona_id = persona.id where " + tabla1
                            //    + ".programa_id = " + idP;
                            fil = 1;
                        } else if (!idS.equals("--")) {
                            session.setAttribute("aux_selectorAsignarM3", 0);
                            //sql2 = "Select " + tabla1 + ".id, persona.id, persona.nombre, persona.apellido from " + tabla1 + " inner join persona on " + tabla1 + ".persona_id = persona.id where " + tabla1 + ".semestre = " + idS;
                            fil = 1;
                        }

                    } else if (id == 2) {

                        tabla = "muestradocente";
                        tabla1 = "docente";
                    }

                    if (fil == 0) {
                        session.setAttribute("aux_selectorAsignarM3", 0);
                        /*
                         * sql2 = "Select " + tabla1 + ".id,persona.id,
                         * persona.nombre, persona.apellido from " + tabla1 + "
                         * inner join persona on " + tabla1 + ".persona_id =
                         * persona.id";
                         */
                    }


                    Result rs = conSql.CargarSql2("select `persona_id`, `estado` from encabezado where `proceso_id` = '" + proceso.getId() + "' and `fuente_id` = '" + id + "'", bd);
                    session.setAttribute("selectorAsignarM33", rs);

                } catch (Error ex) {
                    //  Logger.getLogger(fontController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (request.getParameter(
                    "action").equals("selectorAsignarMuestra2AI")) {

                HttpSession session = request.getSession();
                sqlController conSql = new sqlController();
                Proceso proceso = (Proceso) session.getAttribute("proceso");
                int idProceso = proceso.getId();
                String bd = (String) session.getAttribute("bd");

                String idFuente = request.getParameter("fuente");
                int id = Integer.valueOf(idFuente);
                int idMuestra = (Integer) session.getAttribute("idMuestra");

                String tabla = null;
                String tabla1 = null;

                //Estatico
                if (id == 1) {
                    tabla = "muestraestudiante";
                    tabla1 = "estudiante";
                } else if (id == 2) {
                    tabla = "muestradocente";
                    tabla1 = "docente";
                } else if (id == 3) {
                    tabla = "muestraadministrativo";
                    tabla1 = "administrativo";
                } else if (id == 4) {
                    tabla = "muestradirector";
                    tabla1 = "directorprograma";
                } else if (id == 5) {
                    tabla = "muestraegresado";
                    tabla1 = "egresado";
                } else if (id == 6) {
                    tabla = "muestraempleador";
                    tabla1 = "empleador";
                } else if (id == 7) {
                    tabla = "muestraagencia";
                    tabla1 = "agenciagubernamental";
                }

                String sql = null;


                String conglomerado = (String) session.getAttribute("conglomeradoFiltro");

                if (conglomerado.equals("programa")) {
                    if (id == 1) {
                        String idP = request.getParameter("programas");
                        String idS = request.getParameter("semestres");
                        if (!idP.equals("--") && !idS.equals("--")) {
                            sql = "select persona.id, persona.nombre, persona.apellido, persona.password, estudiante.semestre from muestraestudiante inner join estudiante on muestraestudiante.estudiante_id = estudiante.id inner join persona on estudiante.persona_id = persona.id where muestraestudiante.muestra_id = " + idMuestra + " and estudiante.programa_id = " + idP + " and estudiante.semestre = " + idS + " order by estudiante.id";
                        } else if (!idP.equals("--")) {
                            sql = "select persona.id, persona.nombre, persona.apellido, persona.password, estudiante.semestre from muestraestudiante inner join estudiante on muestraestudiante.estudiante_id = estudiante.id inner join persona on estudiante.persona_id = persona.id where muestraestudiante.muestra_id = " + idMuestra + " and estudiante.programa_id = " + idP + " order by estudiante.id";
                        } else if (!idS.equals("--")) {
                            sql = "select persona.id, persona.nombre, persona.apellido, persona.password, estudiante.semestre from muestraestudiante inner join estudiante on muestraestudiante.estudiante_id = estudiante.id inner join persona on estudiante.persona_id = persona.id where muestraestudiante.muestra_id = " + idMuestra + " and estudiante.semestre = " + idS + " order by estudiante.id";
                        } else if (idP.equals("--") && idS.equals("--")) {
                            //sql = "select persona.id, estudiante.id, persona.nombre, persona.apellido, estudiante.semestre from muestraestudiante inner join estudiante on muestraestudiante.estudiante_id = estudiante.id inner join persona on estudiante.persona_id = persona.id where muestraestudiante.muestra_id = " + idMuestra + " order by estudiante.id";
                        }

                    } else if (id == 2) {
                        String idP = request.getParameter("programas");

                        if (!idP.equals("--")) {
                            sql = "select persona.id, persona.nombre, persona.apellido, persona.password from muestradocente inner join docente on muestradocente.docente_id = docente.id inner join persona on docente.persona_id = persona.id where muestradocente.muestra_id = " + idMuestra + " and docente.programa_id = " + idP + " order by docente.id";
                        } else if (!idP.equals("--")) {
                            sql = "select persona.id, persona.nombre, persona.apellido, persona.password from muestradocente inner join docente on muestradocente.docente_id = docente.id inner join persona on docente.persona_id = persona.id where muestradocente.muestra_id = " + idMuestra + " and docente.programa_id = " + idP + " order by docente.id";
                        } else if (idP.equals("--")) {
                            //sql = "select persona.id, estudiante.id, persona.nombre, persona.apellido, estudiante.semestre from muestraestudiante inner join estudiante on muestraestudiante.estudiante_id = estudiante.id inner join persona on estudiante.persona_id = persona.id where muestraestudiante.muestra_id = " + idMuestra + " order by estudiante.id";
                        }
                    } else if (id == 5) {
                        String idP = request.getParameter("programas");

                        if (!idP.equals("--")) {
                            sql = "select persona.id, persona.nombre, persona.apellido, persona.password from muestraegresado inner join egresado on muestraegresado.egresado_id = egresado.id inner join persona on egresado.persona_id = persona.id where muestraegresado.muestra_id = " + idMuestra + " and egresado.programa_id = " + idP + " order by egresado.id";
                        } else if (!idP.equals("--")) {
                            sql = "select persona.id, persona.nombre, persona.apellido, persona.password from muestraegresado inner join egresado on muestraegresado.egresado_id = egresado.id inner join persona on egresado.persona_id = persona.id where muestraegresado.muestra_id = " + idMuestra + " and egresado.programa_id = " + idP + " order by egresado.id";
                        } else if (idP.equals("--")) {
                            //sql = "select persona.id, estudiante.id, persona.nombre, persona.apellido, estudiante.semestre from muestraestudiante inner join estudiante on muestraestudiante.estudiante_id = estudiante.id inner join persona on estudiante.persona_id = persona.id where muestraestudiante.muestra_id = " + idMuestra + " order by estudiante.id";
                        }
                    }

                } else if (conglomerado.equals("tipo")) {
                    String idP = request.getParameter("programas");
                    if (!idP.equals("--")) {
                        sql = "select persona.id, persona.nombre, persona.apellido, persona.password from muestradocente inner join docente on muestradocente.docente_id = docente.id inner join persona on docente.persona_id = persona.id where muestradocente.muestra_id = " + idMuestra + " and docente.tipo = '" + idP + "'order by muestradocente.id";
                    }

                } else if (conglomerado.equals("cargo")) {
                    String idP = request.getParameter("programas");
                    if (!idP.equals("--")) {
                        sql = "select persona.id, persona.nombre, persona.apellido, persona.password from muestraadministrativo inner join administrativo on muestraadministrativo.administrativo = administrativo.id inner join persona on administrativo.persona_id = persona.id where muestraadministrativo.muestra_id = " + idMuestra + " and administrativo.cargo = '" + idP + "'order by muestraadministrativo.id";
                    }

                } else if (conglomerado.equals("sectorempresarial")) {
                    String idP = request.getParameter("programas");
                    if (!idP.equals("--")) {
                        sql = "select persona.id, persona.nombre, persona.apellido, persona.password from muestraempleador inner join empleador on muestraempleador.empleador_id = empleador.id inner join persona on empleador.persona_id = persona.id where muestraempleador.muestra_id = " + idMuestra + " and empleador.sectorempresarial = '" + idP + "'order by muestraempleador.id";
                    }
                } else if (conglomerado.equals("nuevoCriterio")) {
                    String idP = request.getParameter("programas");
                    if (!idP.equals("--")) {
                        sql = "select persona.id, persona.nombre, persona.apellido, persona.password from muestracriterio inner join persona on muestracriterio.persona_id = persona.id where muestracriterio.muestra_id = " + idMuestra + " and muestracriterio.descripcioncriterio_id = '" + idP + "'order by persona.id";
                    }
                } else if (conglomerado.equals("ninguno")) {
                    String idP = request.getParameter("programas");
                    if (!idP.equals("--")) {
                        sql = "select persona.id, persona.nombre, persona.apellido, persona.password from " + tabla + " inner join " + tabla1 + " on " + tabla + "." + tabla1 + "_id = " + tabla1 + ".id inner join persona on " + tabla1 + ".persona_id = persona.id where " + tabla + ".muestra_id = " + idMuestra + " order by persona.id";
                    }
                }


                session.setAttribute("idFuenteMuestra", id);
                Result rs = conSql.CargarSql2(sql, bd);
                session.setAttribute("selectorAsignarM2", rs);

                rs = conSql.CargarSql2("select `persona_id`, `estado` from encabezado where `proceso_id` = '" + proceso.getId() + "' and `fuente_id` = '" + id + "'", bd);
                session.setAttribute("selectorAsignarM22", rs);

            } else if (request.getParameter(
                    "action").equals("generarMuestra")) {

                HttpSession session = request.getSession();

                session.setAttribute("selectorAsignarM2", null);
                Proceso proceso = (Proceso) session.getAttribute("proceso");

                sqlController conSql = new sqlController();

                String idFormula = request.getParameter("formula");
                String idFuente = request.getParameter("fuente");
                int id = Integer.parseInt(idFuente);
                FuenteJpaController fj = new FuenteJpaController();
                Fuente f = fj.findFuente(id);
                String bd = (String) session.getAttribute("bd");

                int idMuestra = (Integer) session.getAttribute("idMuestra");

                String tabla = null;
                String tabla1 = null;

                //Estatico
                if (id == 1) {
                    tabla = "muestraestudiante";
                    tabla1 = "estudiante";
                } else if (id == 2) {
                    tabla = "muestradocente";
                    tabla1 = "docente";
                } else if (id == 3) {
                    tabla = "muestraadministrativo";
                    tabla1 = "administrativo";
                } else if (id == 4) {
                    tabla = "muestradirector";
                    tabla1 = "directorprograma";
                } else if (id == 5) {
                    tabla = "muestraegresado";
                    tabla1 = "egresado";
                } else if (id == 6) {
                    tabla = "muestraempleador";
                    tabla1 = "empleador";
                } else if (id == 7) {
                    tabla = "muestraagencia";
                    tabla1 = "agenciagubernamental";
                }

                String aux = "aleatorio" + tabla1 + "";

                String conglomerado = request.getParameter("conglomerado");

                session.setAttribute("conglomerado", conglomerado);
                session.setAttribute("conglomeradoFiltro", conglomerado);

                String metodo = request.getParameter("metodo");

                ArrayList rs = (ArrayList) session.getAttribute("muestraCalculada2");

                if (rs != null) {

                    Iterator i = rs.iterator();

                    try {
                        conSql.UpdateSql("TRUNCATE TABLE `" + tabla + "`", bd);

                        String sql = "DELETE " + tabla1 + " from " + tabla1 + " inner join persona on " + tabla1 + ".persona_id = persona.id where persona.nombre = '" + f.getNombre() + "' and persona.apellido = 'Usuario aleatorio'";
                        conSql.UpdateSql(sql, bd);
                        conSql.UpdateSql(sql, "autoevaluacion");

                        sql = "DELETE muestracriterio from muestracriterio inner join persona on muestracriterio.persona_id = persona.id where persona.nombre = '" + f.getNombre() + "' and persona.apellido = 'Usuario aleatorio'";
                        conSql.UpdateSql(sql, bd);

                        sql = "DELETE from persona where persona.nombre = '" + f.getNombre() + "' and persona.apellido = 'Usuario aleatorio'";
                        conSql.UpdateSql(sql, bd);
                        conSql.UpdateSql(sql, "autoevaluacion");

                    } catch (Error e) {
                        System.out.println(e);
                    }

                    while (i.hasNext()) {

                        String s1 = (String) i.next();
                        String[] campos = s1.split("/");

                        String muestra = campos[campos.length - 1];
                        String programa = campos[campos.length - 2];

                        if (conglomerado.equals("programa")) {
                            if (metodo.equals("normal")) {
                                String sql = "SELECT * FROM " + tabla1 + " where " + tabla1 + ".programa_id = " + programa + " ORDER BY Rand() LIMIT " + muestra;
                                ResultSet rs1 = conSql.CargarSql(sql, bd);
                                if (rs1 != null) {
                                    try {
                                        while (rs1.next()) {
                                            String sql2 = "insert into " + tabla + " values (null, '" + idMuestra + "'  , '" + rs1.getString(1) + "', 'programa', 'normal')";
                                            conSql.UpdateSql(sql2, bd);
                                        }
                                    } catch (SQLException ex) {
                                        Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    System.out.println("nnull");
                                }
                            } else if (metodo.equals("aleatorio")) {
                                int d = (int) Math.floor(Double.parseDouble(muestra));
                                for (int j = 0; j < d; j++) {

                                    String pass = PasswordGenerator.getPassword(
                                            PasswordGenerator.MAYUSCULAS
                                            + PasswordGenerator.NUMEROS, 6);

                                    String sql2 = "insert into persona values ('" + proceso.getId() + id + programa + "-" + j + "', '" + f.getNombre() + "'  , 'Usuario aleatorio', '" + pass + "', '--')";
                                    conSql.UpdateSql(sql2, bd);
                                    conSql.UpdateSql(sql2, "autoevaluacion");

                                    if (id == 1) {
                                        sql2 = "insert into " + tabla1 + " values (NULL, '--', '--', '--', '" + proceso.getId() + id + programa + "-" + j + "', '" + id + "', '" + programa + "')";
                                        conSql.UpdateSql(sql2, bd);
                                        conSql.UpdateSql(sql2, "autoevaluacion");
                                    }
                                    if (id == 2) {
                                        sql2 = "insert into " + tabla1 + " values (NULL, '--', '" + proceso.getId() + id + programa + "-" + j + "', '" + id + "', '" + programa + "')";
                                        conSql.UpdateSql(sql2, bd);
                                        conSql.UpdateSql(sql2, "autoevaluacion");
                                       
                                    }
                                    if (id == 5) {
                                        sql2 = "insert into " + tabla1 + " values (NULL, '" + proceso.getId() + id + programa + "-" + j + "', '" + id + "', '" + programa + "')";
                                        conSql.UpdateSql(sql2, bd);
                                        conSql.UpdateSql(sql2, "autoevaluacion");
                                    }
                                    
                                    sql2 = "insert into " + tabla + " values (null, '" + idMuestra + "', (SELECT id from " + tabla1 + " where persona_id = '" + proceso.getId() + id + programa + "-" + j + "' LIMIT 1), 'programa', 'aleatorio')";
                                    conSql.UpdateSql(sql2, bd);
                                }
                            }
                        } else if (conglomerado.equals("nuevoCriterio")) {
                            int d = (int) Math.floor(Double.parseDouble(muestra));
                            for (int j = 0; j < d; j++) {

                                String pass = PasswordGenerator.getPassword(
                                        PasswordGenerator.MAYUSCULAS
                                        + PasswordGenerator.NUMEROS, 6);


                                String sql2 = "insert into persona values ('" + proceso.getId() + id + programa + "-" + j + "', '" + f.getNombre() + "'  , 'Usuario aleatorio', '" + pass + "', 'nuevoConglomerado')";
                                conSql.UpdateSql(sql2, bd);
                                conSql.UpdateSql(sql2, "autoevaluacion");
                                if (id == 1) {
                                    sql2 = "insert into " + tabla1 + " values (NULL, '--', '--', '--', '" + proceso.getId() + id + programa + "-" + j + "', '" + id + "', '1')";
                                    conSql.UpdateSql(sql2, bd);
                                    conSql.UpdateSql(sql2, "autoevaluacion");

                                }
                                if (id == 2) {
                                    sql2 = "insert into " + tabla1 + " values (NULL, '--', '" + proceso.getId() + id + programa + "-" + j + "', '1')";
                                    conSql.UpdateSql(sql2, bd);
                                    conSql.UpdateSql(sql2, "autoevaluacion");

                                }
                                if (id == 3) {
                                    sql2 = "insert into " + tabla1 + " values (NULL, '" + proceso.getId() + id + programa + "-" + j + "', '" + id + "'  , '" + 1 + "', NULL)";
                                    conSql.UpdateSql(sql2, bd);
                                    conSql.UpdateSql(sql2, "autoevaluacion");

                                }
                                if (id == 4 || id == 5) {
                                    sql2 = "insert into " + tabla1 + " values (null, '" + proceso.getId() + id + programa + "-" + j + "', '" + id + "'  , '" + 1 + "')";
                                    conSql.UpdateSql(sql2, bd);
                                    conSql.UpdateSql(sql2, "autoevaluacion");

                                }
                                if (id == 6) {
                                    sql2 = "insert into " + tabla1 + " values (null, null, '" + proceso.getId() + id + programa + "-" + j + "', '" + id + "'  , '" + 1 + "')";
                                    conSql.UpdateSql(sql2, bd);
                                    conSql.UpdateSql(sql2, "autoevaluacion");

                                }
                                if (id == 7) {
                                    sql2 = "insert into " + tabla1 + " values (null, null, '" + proceso.getId() + id + programa + "-" + j + "', '" + id + "')";
                                    conSql.UpdateSql(sql2, bd);
                                    conSql.UpdateSql(sql2, "autoevaluacion");

                                }
                                sql2 = "insert into " + tabla + " values (null, '" + idMuestra + "', (SELECT id from " + tabla1 + " where persona_id = '" + proceso.getId() + id + programa + "-" + j + "' LIMIT 1), 'nuevoCriterio', 'aleatorio')";
                                conSql.UpdateSql(sql2, bd);


                                sql2 = "insert into muestracriterio values (null, '" + idMuestra + "', '" + proceso.getId() + id + programa + "-" + j + "')";
                                conSql.UpdateSql(sql2, bd);

                            }
                        } else if (conglomerado.equals("ninguno")) {
                            if (metodo.equals("normal")) {
                                String sql = "SELECT * FROM " + tabla1 + " ORDER BY Rand() LIMIT " + muestra;
                                ResultSet rs1 = conSql.CargarSql(sql, bd);
                                if (rs1 != null) {
                                    try {
                                        while (rs1.next()) {
                                            String sql2 = "insert into " + tabla + " values (null, '" + idMuestra + "'  , '" + rs1.getString(1) + "', 'ninguno', 'normal')";
                                            conSql.UpdateSql(sql2, bd);

                                        }
                                    } catch (SQLException ex) {
                                        Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    System.out.println("nnull");
                                }
                            } else if (metodo.equals("aleatorio")) {
                                int d = (int) Math.floor(Double.parseDouble(muestra));
                                String x = "00";
                                for (int j = 0; j < d; j++) {

                                    String pass = PasswordGenerator.getPassword(
                                            PasswordGenerator.MAYUSCULAS
                                            + PasswordGenerator.NUMEROS, 6);


                                    String sql2 = "insert into persona values ('" + proceso.getId() + id + programa + "-" + j + "', '" + f.getNombre() + "'  , 'Usuario aleatorio', '" + pass + "', '--')";
                                    conSql.UpdateSql(sql2, bd);
                                    conSql.UpdateSql(sql2, "autoevaluacion");

                                    if (id == 3) {
                                        sql2 = "insert into " + tabla1 + " values (null, '" + proceso.getId() + id + programa + "-" + j + "', '" + id + "'  , '" + 1 + "', NULL)";
                                        conSql.UpdateSql(sql2, bd);
                                        conSql.UpdateSql(sql2, "autoevaluacion");

                                    }
                                    if (id == 4 || id == 5) {
                                        sql2 = "insert into " + tabla1 + " values (null, '" + proceso.getId() + id + programa + "-" + j + "', '" + id + "'  , '" + 1 + "')";
                                        conSql.UpdateSql(sql2, bd);
                                        conSql.UpdateSql(sql2, "autoevaluacion");

                                    }
                                    if (id == 6) {
                                        sql2 = "insert into " + tabla1 + " values (null, null, '" + proceso.getId() + id + programa + "-" + j + "', '" + id + "'  , '" + 1 + "')";
                                        conSql.UpdateSql(sql2, bd);
                                        conSql.UpdateSql(sql2, "autoevaluacion");

                                    }
                                    if (id == 7) {
                                        sql2 = "insert into " + tabla1 + " values (null, null, '" + proceso.getId() + id + programa + "-" + j + "', '" + id + "')";
                                        conSql.UpdateSql(sql2, bd);
                                        conSql.UpdateSql(sql2, "autoevaluacion");

                                    }
                                    sql2 = "insert into " + tabla + " values (null, '" + idMuestra + "', (SELECT id from " + tabla1 + " where persona_id = '" + proceso.getId() + id + programa + "-" + j + "' LIMIT 1), 'ninguno', 'aleatorio')";
                                    conSql.UpdateSql(sql2, bd);

                                }
                            } else {
                                if (metodo.equals("normal")) {
                                    String sql = "SELECT * FROM " + tabla1 + " where " + tabla1 + "." + conglomerado + " = " + programa + " ORDER BY Rand() LIMIT " + muestra;
                                    ResultSet rs1 = conSql.CargarSql(sql, bd);
                                    if (rs1 != null) {
                                        try {
                                            while (rs1.next()) {
                                                String sql2 = "insert into " + tabla + " values (null, '" + idMuestra + "'  , '" + rs1.getString(1) + "', '" + conglomerado + "', 'normal')";
                                                conSql.UpdateSql(sql2, bd);

                                            }
                                        } catch (SQLException ex) {
                                            Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    } else {
                                        System.out.println("nnull");
                                    }
                                } else if (metodo.equals("aleatorio")) {
                                    int d = (int) Math.floor(Double.parseDouble(muestra));
                                    for (int j = 0; j < d; j++) {

                                        String pass = PasswordGenerator.getPassword(
                                                PasswordGenerator.MAYUSCULAS
                                                + PasswordGenerator.NUMEROS, 6);

                                        String sql2 = "insert into persona values ('" + proceso.getId() + id + programa + "-" + j + "', '" + f.getNombre() + "'  , 'Usuario aleatorio', '" + pass + "', '--')";
                                        conSql.UpdateSql(sql2, bd);
                                        conSql.UpdateSql(sql2, "autoevaluacion");

                                        if (id == 2) {
                                            sql2 = "insert into " + tabla1 + " values (NULL, '" + programa + "', '" + proceso.getId() + id + programa + "-" + j + "', '" + id + "', '1')";
                                            conSql.UpdateSql(sql2, bd);
                                            conSql.UpdateSql(sql2, "autoevaluacion");

                                        }
                                        if (id == 3) {
                                            sql2 = "insert into " + tabla1 + " values (null, '" + proceso.getId() + id + programa + "-" + j + "'', '" + id + "'  , '" + 1 + "', '" + programa + "')";
                                            conSql.UpdateSql(sql2, bd);
                                            conSql.UpdateSql(sql2, "autoevaluacion");

                                        }
                                        if (id == 4 || id == 5) {
                                            sql2 = "insert into " + tabla1 + " values (null, '" + proceso.getId() + id + programa + "-" + j + "', '" + id + "'  , '" + 1 + "')";
                                            conSql.UpdateSql(sql2, bd);
                                            conSql.UpdateSql(sql2, "autoevaluacion");

                                        }
                                        if (id == 6) {
                                            sql2 = "insert into " + tabla1 + " values (null, null, '" + proceso.getId() + id + programa + "-" + j + "', '" + id + "'  , '" + 1 + "')";
                                            conSql.UpdateSql(sql2, bd);
                                            conSql.UpdateSql(sql2, "autoevaluacion");

                                        }
                                        if (id == 7) {
                                            sql2 = "insert into " + tabla1 + " values (null, null, '" + proceso.getId() + id + programa + "-" + j + "', '" + id + "', '" + programa + "')";
                                            conSql.UpdateSql(sql2, bd);
                                            conSql.UpdateSql(sql2, "autoevaluacion");

                                        }
                                        sql2 = "insert into " + tabla + " values (null, '" + idMuestra + "', (SELECT id from " + tabla1 + " where persona_id = '" + proceso.getId() + id + programa + "-" + j + "' LIMIT 1), '" + conglomerado + "', 'aleatorio')";
                                        conSql.UpdateSql(sql2, bd);

                                    }
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("nulo");
                }
            } else if (request.getParameter(
                    "action").equals("calcularMuestraAI")) {


                HttpSession session = request.getSession();
                Proceso proceso = (Proceso) session.getAttribute("proceso");

                session.setAttribute("muestraIndividual", null);
                session.setAttribute("muestraCalculada3", null);


                session.setAttribute("muestraCalculada", null);


                sqlController conSql = new sqlController();

                String idFormula = request.getParameter("formula");
                String idFuente = request.getParameter("fuente");
                String conglomerado = request.getParameter("conglomerado");

                int id = Integer.parseInt(idFuente);

                String bd = (String) session.getAttribute("bd");

                int idMuestra = (Integer) session.getAttribute("idMuestra");

                String tabla = null;
                String tabla1 = null;

                //Estatico
                if (id == 1) {
                    tabla = "muestraestudiante";
                    tabla1 = "estudiante";
                } else if (id == 2) {
                    tabla = "muestradocente";
                    tabla1 = "docente";
                } else if (id == 3) {
                    tabla = "muestraadministrativo";
                    tabla1 = "administrativo";
                } else if (id == 4) {
                    tabla = "muestradirector";
                    tabla1 = "directorprograma";
                } else if (id == 5) {
                    tabla = "muestraegresado";
                    tabla1 = "egresado";
                } else if (id == 6) {
                    tabla = "muestraempleador";
                    tabla1 = "empleador";
                } else if (id == 7) {
                    tabla = "muestraagencia";
                    tabla1 = "agenciagubernamental";
                }

                ResultSet rs = null;
                String sql = "";


                // rs = conSql.CargarSql(sql, bd);

                if (idFormula.equals("1")) {
                    session.setAttribute("muestraCalculada2", null);
                    session.setAttribute("muestraCalculada", null);
                    session.setAttribute("muestraIndividual", null);

                    //Tamaño muestra
                    double n;
                    //Nivel de confianza
                    double z = 1.96;
                    //Probabilidad Ocurrencia
                    double p = 0.5;
                    //Nivel de tolerancia 
                    double e = 0.04;
                    //Porbabilidad de no ocurrencia
                    double q = 0.5;
                    //Tamaño de la población
                    double N = 0.0;

                    try {

                        String inputAux;
                        if (request.getParameter("inputAux") != null) {
                            inputAux = request.getParameter("inputAux");
                        } else {
                            inputAux = "automatic";
                        }

                        if (inputAux.equals("automatic")) {
                            sql = "Select count(*) from " + tabla1 + " inner join persona on " + tabla1 + ".persona_id = persona.id where persona.apellido <> 'aleatorio" + tabla1 + "'";
                            rs = conSql.CargarSql(sql, bd);
                            while (rs.next()) {
                                N = Double.parseDouble(rs.getString(1));
                            }
                        } else {
                            if (session.getAttribute("conglomerado").equals("ninguno")) {
                                N = Double.valueOf(request.getParameter("tamanioPobla"));
                            } else if (session.getAttribute("conglomerado").equals("programa")) {
                                rs = conSql.CargarSql("Select id from programa", bd);
                                while (rs.next()) {
                                    if (!"".equals(request.getParameter("tamanioPobla" + rs.getString(1)))) {
                                        double aux = Double.valueOf(request.getParameter("tamanioPobla" + rs.getString(1)));
                                        N = N + aux;
                                    }
                                }
                            } else if (session.getAttribute("conglomerado").equals("nuevoCriterio")) {
                                String criterio = (String) session.getAttribute("criterio");

                                rs = conSql.CargarSql("Select id from descripcioncriterio where criterio_id = '" + criterio + "'", bd);
                                while (rs.next()) {
                                    if (!"".equals(request.getParameter("tamanioPobla" + rs.getString(1)))) {
                                        double aux = Double.valueOf(request.getParameter("tamanioPoblaCriterio" + rs.getString(1)));
                                        N = N + aux;
                                    }
                                }
                            }
                        }


                        if (N != 0.0) {

                            n = (N * p * q * (z * z)) / ((N - 1) * (e * e) + p * q * (z * z));


                            String sql2 = null;
                            int var1 = 0;

                            if (conglomerado.equals("ninguno")) {
                                String s;
                                ArrayList l = new ArrayList();


                                int d = (int) Math.floor(n);
                                s = "Muestra/" + d;
                                l.add(s);
                                session.setAttribute("muestraIndividual", d);
                                session.setAttribute("muestraCalculada", null);
                                session.setAttribute("muestraCalculada3", null);

                                session.setAttribute("muestraCalculada2", l);
                            } else if (conglomerado.equals("programa")) {
                                double cociente = n / N;
                                Result result = null;
                                if (inputAux.equals("automatic")) {
                                    ArrayList l = new ArrayList();
                                    String s;
                                    sql2 = "Select programa.nombre, ROUND((count(*)*" + cociente + ")*1.2,0), programa.id from " + tabla1 + " inner join programa on " + tabla1 + ".programa_id = programa.id group by " + tabla1 + ".programa_id order by programa.nombre";
                                    result = conSql.CargarSql2(sql2, bd);
                                    session.setAttribute("muestraCalculada", result);
                                    session.setAttribute("muestraIndividual", null);
                                    session.setAttribute("muestraCalculada3", null);
                                    var1 = 1;

                                    ResultSet rs1 = conSql.CargarSql(sql2, bd);

                                    while (rs1.next()) {

                                        if (rs1.getString(2).equals("0")) {
                                            s = rs1.getString(3) + "/" + "1";
                                        } else {
                                            s = rs1.getString(3) + "/" + rs1.getString(2);
                                        }
                                        l.add(s);
                                    }
                                    session.setAttribute("muestraCalculada2", l);

                                } else {
                                    ArrayList<Bean1> l2 = new ArrayList<Bean1>();
                                    ArrayList l = new ArrayList();
                                    String s;
                                    rs = conSql.CargarSql("Select id, nombre from programa", bd);
                                    while (rs.next()) {
                                        if (!"".equals(request.getParameter("tamanioPobla" + rs.getString(1)))) {
                                            Bean1 bean = new Bean1();
                                            bean.setPrograma(rs.getString(2));
                                            double tam = Double.valueOf(request.getParameter("tamanioPobla" + rs.getString(1)));
                                            int d = (int) Math.floor(tam * cociente);
                                            bean.setTamanio(String.valueOf(d));
                                            l2.add(bean);

                                            if (request.getParameter("tamanioPobla" + rs.getString(1)).equals("0")) {
                                                s = rs.getString(1) + "/" + "1";
                                            } else {
                                                s = rs.getString(1) + "/" + d;
                                            }
                                            l.add(s);
                                        }
                                    }
                                    session.setAttribute("muestraCalculada", null);
                                    session.setAttribute("muestraIndividual", null);
                                    session.setAttribute("muestraCalculada3", l2);

                                    session.setAttribute("muestraCalculada2", l);
                                }
                            } else if (conglomerado.equals("nuevoCriterio")) {
                                ArrayList<Bean1> l2 = new ArrayList<Bean1>();
                                ArrayList l = new ArrayList();
                                String s;
                                String criterio = (String) session.getAttribute("criterio");
                                double cociente = n / N;

                                rs = conSql.CargarSql("Select id, nombre from descripcioncriterio where criterio_id = '" + criterio + "'", bd);
                                while (rs.next()) {
                                    if (!"".equals(request.getParameter("tamanioPoblaCriterio" + rs.getString(1)))) {
                                        Bean1 bean = new Bean1();
                                        bean.setPrograma(rs.getString(2));
                                        double tam = Double.valueOf(request.getParameter("tamanioPoblaCriterio" + rs.getString(1)));
                                        int d = (int) Math.floor(tam * cociente);
                                        bean.setTamanio(String.valueOf(d));
                                        l2.add(bean);

                                        if (request.getParameter("tamanioPoblaCriterio" + rs.getString(1)).equals("0")) {
                                            s = rs.getString(1) + "/" + "1";
                                        } else {
                                            s = rs.getString(1) + "/" + d;
                                        }
                                        l.add(s);
                                    }
                                }
                                session.setAttribute("muestraCalculada", null);
                                session.setAttribute("muestraIndividual", null);
                                session.setAttribute("muestraCalculada3", l2);

                                session.setAttribute("muestraCalculada2", l);

                            } else {
                                ArrayList l = new ArrayList();
                                String s;
                                double cociente = n / N;
                                sql2 = "Select distinct " + tabla1 + "." + conglomerado + ", ROUND((count(*)*" + cociente + ")*1.2,0) from " + tabla1 + " where " + tabla1 + "." + conglomerado + " <> null group by " + tabla1 + "." + conglomerado + " order by " + conglomerado + "";
                                Result result = conSql.CargarSql2(sql2, bd);
                                if (result.getRowCount() > 0) {
                                    session.setAttribute("muestraCalculada", result);
                                } else {
                                    session.setAttribute("muestraCalculada", null);

                                }
                                session.setAttribute("muestraIndividual", null);
                                session.setAttribute("muestraCalculada3", null);

                                ResultSet rs1 = conSql.CargarSql(sql2, bd);

                                while (rs1.next()) {

                                    if (rs1.getString(2).equals("0")) {
                                        s = rs1.getString(1) + "/" + "1";
                                    } else {
                                        s = rs1.getString(1) + "/" + rs1.getString(2);
                                    }
                                    l.add(s);
                                }
                                session.setAttribute("muestraCalculada2", l);

                            }

                        } else {
                            System.out.println("No existe población");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (request.getParameter(
                    "action").equals("crearProcesoAIp")) {

                HttpSession session = request.getSession();
                sqlController conSql = new sqlController();
                Programa programa = new Programa();
                Proceso proceso = new Proceso();
                ProcesoJpaController conProceso = new ProcesoJpaController();


                if (session.getAttribute("aux_index2").equals(1)) {
                    proceso = (Proceso) session.getAttribute("proceso");
                    int idProceso = proceso.getId();
                    String bd = (String) session.getAttribute("bd");
                    proceso.setDescripcion(request.getParameter("descripcion"));
                    try {
                        conSql.UpdateSql("UPDATE `proceso` SET `descripcion` = '" + request.getParameter("descripcion") + "' WHERE `proceso`.`id` = " + idProceso, bd);
                        conProceso.edit(proceso);
                    } catch (entity.controller.exceptions.NonexistentEntityException ex) {
                        Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    session.setAttribute("proceso", proceso);
                } else {
                    proceso.setFechainicio("Proceso en Configuración.");
                    proceso.setDescripcion(request.getParameter("descripcion"));

                    programa = (Programa) session.getAttribute("programa");
                    proceso.setProgramaId(programa);

                    conProceso.create(proceso);

                    session.setAttribute("aux_IniciarP", 0);
                    session.setAttribute("proceso", proceso);
                    session.setAttribute("aux_index2", 1);
                    session.setAttribute("aux2_index2", 1);
                    session.setAttribute("estadoProceso", 0);

                    try {
                        String str = request.getSession().getServletContext().getRealPath("/scriptsSql/script.sql");
                        String str2 = request.getSession().getServletContext().getRealPath("/scriptsSql/script2.sql");
                        conSql.newDb(proceso, str, str2);

                    } catch (SQLException ex) {
                        // Logger.getLogger(fontController.class.getName()).log(Level.SEVERE, null, ex);
                    }


                    String nombreBd = programa.getNombre() + proceso.getId();

                    session.setAttribute("proceso", proceso);
                    session.setAttribute("bd", nombreBd);
                    session.setAttribute("proActivo", 1);


                    conSql.UpdateSql("INSERT INTO `autoevaluacion`.`muestra` (`id`, `formula`, `proceso_id`) VALUES (NULL, NULL, '" + proceso.getId() + "')", nombreBd);


                    String sql2 = "Select id from muestra where proceso_id = " + proceso.getId();
                    ResultSet rs3 = conSql.CargarSql(sql2, "autoevaluacion");


                    try {
                        while (rs3.next()) {

                            int idMuestra = Integer.parseInt(rs3.getString(1));
                            session.setAttribute("idMuestra", idMuestra);
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    conSql.UpdateSql("INSERT INTO `" + nombreBd + "`.`muestra` (`id`, `formula`, `proceso_id`) VALUES (" + session.getAttribute("idMuestra") + ", NULL, '" + proceso.getId() + "')", nombreBd);


                }



            } else if (request.getParameter(
                    "action").equals("CerrarProcesoAI")) {

                HttpSession session = request.getSession();
                Proceso proceso = (Proceso) session.getAttribute("proceso");
                int idProceso = proceso.getId();
                String bd = (String) session.getAttribute("bd");
                sqlController conSql = new sqlController();

                Result rs1 = conSql.CargarSql2("Select indicador.id, indicador.codigo from indicador inner join instrumentohasindicador on indicador.id = instrumentohasindicador.indicador_id where instrumentohasindicador.instrumento_id = 2 order by indicador.id", bd);
                int indis = rs1.getRowCount();
                int doc = 0;
                Result rs4 = null;
                String sql = "select indicador.id, indicador.nombre, numericadocumental.documento, numericadocumental.responsable, numericadocumental.medio, numericadocumental.lugar, numericadocumental.evaluacion, numericadocumental.accion from numericadocumental inner join indicador on numericadocumental.indicador_id = indicador.id inner join instrumentohasindicador on indicador.id = instrumentohasindicador.indicador_id where instrumentohasindicador.instrumento_id = 2 and numericadocumental.proceso_id = '" + idProceso + "'";
                rs4 = conSql.CargarSql2(sql, bd);
                if (((indis == 0) || (rs4.getRowCount() == indis))) {
                    doc = 1;
                    System.out.println("si hay info documental");
                } else {
                    System.out.println("no hay info documental");
                }


                rs1 = conSql.CargarSql2("Select indicador.id, indicador.codigo from indicador inner join instrumentohasindicador on indicador.id = instrumentohasindicador.indicador_id where instrumentohasindicador.instrumento_id = 3 order by indicador.id", bd);
                indis = rs1.getRowCount();
                int num = 0;
                rs4 = null;
                sql = "select indicador.id, indicador.nombre, numericadocumental.documento, numericadocumental.responsable, numericadocumental.medio, numericadocumental.lugar, numericadocumental.evaluacion, numericadocumental.accion from numericadocumental inner join indicador on numericadocumental.indicador_id = indicador.id inner join instrumentohasindicador on indicador.id = instrumentohasindicador.indicador_id where instrumentohasindicador.instrumento_id = 1 and numericadocumental.proceso_id = '" + idProceso + "'";
                rs4 = conSql.CargarSql2(sql, bd);
                if ((indis == 0) || (rs4.getRowCount() == indis)) {
                    num = 1;
                    System.out.println("si hay info numerica");
                } else {
                    System.out.println("no hay info numerica");
                }


                if (doc != 0 && num != 0) {
                    Date d = new Date();
                    String date = String.valueOf(d);
                    conSql.UpdateSql("UPDATE `proceso` SET `fechacierre` = '" + date + "' WHERE `proceso`.`id` = " + idProceso, bd);
                    proceso.setFechacierre(date);
                    session.setAttribute("proceso", proceso);
                    session.setAttribute("proActivo", 0);
                    session.setAttribute("estadoProceso", 2);
                    session.setAttribute("aux_IniciarP", 2);
                    session.setAttribute("aux_index2", 3);
                    conSql.UpdateSql("UPDATE `proceso` SET `fechacierre` = '" + date + "' WHERE `proceso`.`id` = " + idProceso, "autoevaluacion");
                    sql = "DELETE from persona where persona.nombre = 'Fuente'";
                    conSql.UpdateSql(sql, "autoevaluacion");
                }

            } else if (request.getParameter(
                    "action").equals("verProceso")) {
                String idPro = request.getParameter(
                        "idPro");
                ProcesoJpaController pc = new ProcesoJpaController();
                HttpSession session = request.getSession();

                Proceso p = pc.findProceso(Integer.valueOf(idPro));
                Programa pro = (Programa) session.getAttribute("programa");

                if (p.getFechacierre() == null && p.getProgramaId().getId() == pro.getId()) {
                    if (p.getFechainicio().equals("Proceso en Configuración.")) {
                        session.setAttribute("aux_index2", 1);
                        session.setAttribute("aux_IniciarP", 0);
                        System.out.println("Proceso en configuración");
                    } else {
                        session.setAttribute("aux_index2", 2);
                        session.setAttribute("aux_IniciarP", 1);
                        System.out.println("Proceso en ejecución");
                    }
                } else {
                    session.setAttribute("aux_IniciarP", 2);
                    session.setAttribute("aux_index2", 3);
                }

                session.setAttribute("proceso", p);
                String nombreBd = pro.getNombre() + p.getId();
                session.setAttribute("bd", nombreBd);

            } else if (request.getParameter(
                    "action").equals("IniciarProcesoAI")) {


                HttpSession session = request.getSession();
                String bd = (String) session.getAttribute("bd");
                sqlController conSql = new sqlController();
                Proceso p = (Proceso) session.getAttribute("proceso");
                int idProceso = p.getId();
                Date d = new Date();
                String date = String.valueOf(d);
                p.setFechainicio(date);

                // valida ponderacion factores
                int f = 0;
                Result rs3 = null;
                String sql = "Select factor.id, ponderacion, justificacion, proceso_id, factor_id, nombre from ponderacionfactor inner join factor on ponderacionfactor.factor_id = factor.id where proceso_id = " + idProceso + " order by factor.id";
                rs3 = conSql.CargarSql2(sql, bd);
                if (rs3.getRowCount() > 0) {
                    System.out.println("si hay ponderacion de factores");
                    f = 1;
                } else {
                    System.out.println("Debe asignar la ponderacion de factores");
                    session.setAttribute("aux_IniciarP", 0);
                }

                // valida ponderacion caracteristicas
                int c = 0;
                Result rs4 = null;
                sql = "Select caracteristica.id, ponderacion, justificacion, proceso_id, caracteristica_id, nombre, nivelimportancia from ponderacioncaracteristica inner join caracteristica on ponderacioncaracteristica.caracteristica_id = caracteristica.id where proceso_id = " + idProceso + " order by caracteristica.id";
                rs4 = conSql.CargarSql2(sql, bd);

                if (rs4.getRowCount() > 0) {
                    System.out.println("si hay ponderacion de caracteristicas");
                    c = 1;
                } else {
                    System.out.println("Debe asignar la ponderacion de caracteristicas");
                    session.setAttribute("aux_IniciarP", 0);
                }

                //  valida asig encuesta
                int count = 0;
                int count2 = 0;

                ResultSet rs2 = conSql.CargarSql("Select id from fuente", bd);
                try {
                    while (rs2.next()) {
                        count2++;
                        Result rs = null;
                        sql = "Select* from asignacionencuesta where proceso_id = " + idProceso + " and fuente_id = "
                                + rs2.getString(1);
                        rs = conSql.CargarSql2(sql, bd);
                        if (rs.getRowCount() != 0) {
                            count++;
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(formController.class.getName()).log(Level.SEVERE,
                            null, ex);
                }

                int e = 0;
                if (count != 0) {

                    e = 1;
                } else {
                    System.out.println("Debe asignar las Encuestas.");
                    session.setAttribute("aux_IniciarP", 0);
                }

                //valida asig muestra

                int count3 = 0;
                int count4 = 0;
                int count5 = 0;
                int laid = 0;

                int m = 0;

                ResultSet rs6 = conSql.CargarSql("Select id from muestra where proceso_id = '" + idProceso + "'", bd);
                try {
                    while (rs6.next()) {
                        count5 = 1;
                        laid = Integer.parseInt(rs6.getString(1));
                        ResultSet rs5 = conSql.CargarSql("Select id from fuente", bd);
                        try {
                            while (rs5.next()) {
                                int id = Integer.parseInt(rs5.getString(1));
                                String tabla = "";
                                String tabla1 = "";

                                //Estatico
                                if (id == 1) {
                                    tabla = "muestraestudiante";
                                    tabla1 = "estudiante";
                                } else if (id == 2) {
                                    tabla = "muestradocente";
                                    tabla1 = "docente";
                                } else if (id == 3) {
                                    tabla = "muestraadministrativo";
                                    tabla1 = "administrativo";
                                } else if (id == 4) {
                                    tabla = "muestradirector";
                                    tabla1 = "directorprograma";
                                } else if (id == 5) {
                                    tabla = "muestraegresado";
                                    tabla1 = "egresado";
                                } else if (id == 6) {
                                    tabla = "muestraempleador";
                                    tabla1 = "empleador";
                                } else if (id == 7) {
                                    tabla = "muestraagencia";
                                    tabla1 = "agenciagubernamental";
                                }

                                Result rs = null;

                                sql = "Select* from " + tabla + " where muestra_id = " + laid;
                                rs = conSql.CargarSql2(sql, bd);

                                if (rs.getRowCount() != 0) {
                                    count3++;
                                }
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(formController.class.getName()).log(Level.SEVERE,
                                    null, ex);
                        }

                        if (count3 != 0) {
                            m = 1;
                            System.out.println("Si hay asignacion de Muestra.");

                        } else {
                            System.out.println("Debe asignar las Muestra.");
                            session.setAttribute("aux_IniciarP", 0);
                        }

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (count5 == 0) {
                    System.out.println("Debe asignar las Muestra.");
                    session.setAttribute("aux_IniciarP", 0);
                }

                if (f != 0 && c != 0 && e != 0 && m != 0) {
                    try {
                        conSql.UpdateSql("UPDATE `proceso` SET `fechainicio` = '" + date + "' WHERE `proceso`.`id` = " + idProceso, bd);

                        System.out.println("UPDATE `autoevaluacion`.`proceso` SET `fechainicio` = '" + date + "' WHERE `proceso`.`id` = " + idProceso);
                        session.setAttribute("proceso", p);
                        session.setAttribute("aux_index2", 2);
                        session.setAttribute("aux_IniciarP", 1);
                        session.setAttribute("estadoProceso", 1);
                        conSql.UpdateSql("UPDATE `autoevaluacion`.`proceso` SET `fechainicio` = '" + date + "' WHERE `proceso`.`id` = " + idProceso, "autoevaluacion");
                    } catch (Error ee) {
                        System.out.println("error: " + ee);
                    }
                }
            } else if (request.getParameter(
                    "action").equals("evaluarInfoDocumentalAI")) {

                HttpSession session = request.getSession();
                sqlController conSql = new sqlController();

                int numRows = Integer.parseInt(request.getParameter("count"));
                ResultSet rs = null;
                String bd = (String) session.getAttribute("bd");
                Proceso p = (Proceso) session.getAttribute("proceso");
                int idProceso = p.getId();

                if (session.getAttribute("auxInfoDocumental").equals(0)) {


                    rs = conSql.CargarSql("Select* from indicador inner join instrumentohasindicador on indicador.id = instrumentohasindicador.indicador_id where instrumentohasindicador.instrumento_id = 2 order by indicador.id", bd);
                    try {
                        while (rs.next()) {
                            int i = Integer.parseInt(rs.getString(1));
                            String id = request.getParameter("idIndicadorDoc" + i);
                            String nombreDoc = request.getParameter("nombreDocumento" + i);
                            String responsable = request.getParameter("responsableDocumento" + i);
                            String medio = request.getParameter("medioDocumento" + i);
                            String lugar = request.getParameter("lugarDocumento" + i);
                            String evaluacion = request.getParameter("evaluacionDoc" + i);
                            String accion = request.getParameter("accionDocumento" + i);

                            conSql.UpdateSql("INSERT INTO `numericadocumental` (`id`, `documento`, `responsable`, `medio`, `lugar`, `evaluacion`, `accion`, `indicador_id`, `proceso_id`) VALUES (NULL, '" + nombreDoc + "', '" + responsable + "', '" + medio + "', '" + lugar + "', '" + evaluacion + "', '" + accion + "','" + id + "', '" + idProceso + "')", bd);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    session.setAttribute("auxInfoDocumental", 1);

                } else {

                    rs = conSql.CargarSql("Select* from indicador inner join instrumentohasindicador on indicador.id = instrumentohasindicador.indicador_id where instrumentohasindicador.instrumento_id = 2 order by indicador.id", bd);
                    try {
                        while (rs.next()) {
                            int i = Integer.parseInt(rs.getString(1));
                            String id = request.getParameter("idIndicadorDoc" + i);
                            String nombreDoc = request.getParameter("nombreDocumento" + i);
                            String responsable = request.getParameter("responsableDocumento" + i);
                            String medio = request.getParameter("medioDocumento" + i);
                            String lugar = request.getParameter("lugarDocumento" + i);
                            String evaluacion = request.getParameter("evaluacionDoc" + i);
                            String accion = request.getParameter("accionDocumento" + i);
                            int idNumDoc = 0;

                            ResultSet rs2 = conSql.CargarSql("Select id from numericadocumental where numericadocumental.proceso_id = '" + idProceso + "' and numericadocumental.indicador_id = '" + id + "'", bd);
                            try {
                                while (rs2.next()) {
                                    idNumDoc = Integer.parseInt(rs2.getString(1));
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            conSql.UpdateSql("UPDATE `numericadocumental` SET `evaluacion` = '" + evaluacion + "',`documento` = '" + nombreDoc + "',`accion` = '" + accion + "',`responsable` = '" + responsable + "', `medio` = '" + medio + "', `lugar` = '" + lugar + "' WHERE `numericadocumental`.`id` ='" + idNumDoc + "'", bd);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (request.getParameter(
                    "action").equals("evaluarInfoNumericaAI")) {

                HttpSession session = request.getSession();
                sqlController conSql = new sqlController();

                int numRows = Integer.parseInt(request.getParameter("count"));
                ResultSet rs = null;
                String bd = (String) session.getAttribute("bd");
                Proceso p = (Proceso) session.getAttribute("proceso");
                int idProceso = p.getId();

                if (session.getAttribute("auxInfoNumerica").equals(0)) {

                    rs = conSql.CargarSql("Select* from indicador inner join instrumentohasindicador on indicador.id = instrumentohasindicador.indicador_id where instrumentohasindicador.instrumento_id = 2 order by indicador.id", bd);
                    try {
                        while (rs.next()) {
                            int i = Integer.parseInt(rs.getString(1));

                            String id = request.getParameter("idIndicadorDoc" + i);
                            String nombreDoc = request.getParameter("nombreDocumento" + i);
                            String responsable = request.getParameter("responsableDocumento" + i);
                            String medio = request.getParameter("medioDocumento" + i);
                            String lugar = request.getParameter("lugarDocumento" + i);
                            String evaluacion = request.getParameter("evaluacionDoc" + i);
                            String accion = request.getParameter("accionDocumento" + i);

                            conSql.UpdateSql("INSERT INTO `numericadocumental` (`id`, `documento`, `responsable`, `medio`, `lugar`, `evaluacion`, `accion`, `indicador_id`, `proceso_id`) VALUES (NULL, '" + nombreDoc + "', '" + responsable + "', '" + medio + "', '" + lugar + "', '" + evaluacion + "', '" + accion + "','" + id + "', '" + idProceso + "')", bd);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    session.setAttribute("auxInfoDocumental", 1);

                } else {

                    rs = conSql.CargarSql("Select* from indicador inner join instrumentohasindicador on indicador.id = instrumentohasindicador.indicador_id where instrumentohasindicador.instrumento_id = 2 order by indicador.id", bd);
                    try {
                        while (rs.next()) {
                            int i = Integer.parseInt(rs.getString(1));
                            String id = request.getParameter("idIndicadorDoc" + i);
                            String nombreDoc = request.getParameter("nombreDocumento" + i);
                            String responsable = request.getParameter("responsableDocumento" + i);
                            String medio = request.getParameter("medioDocumento" + i);
                            String lugar = request.getParameter("lugarDocumento" + i);
                            String evaluacion = request.getParameter("evaluacionDoc" + i);
                            String accion = request.getParameter("accionDocumento" + i);
                            int idNumDoc = 0;

                            ResultSet rs2 = conSql.CargarSql("Select id from numericadocumental where numericadocumental.proceso_id = '" + idProceso + "' and numericadocumental.indicador_id = '" + id + "'", bd);
                            try {
                                while (rs2.next()) {
                                    idNumDoc = Integer.parseInt(rs2.getString(1));
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            conSql.UpdateSql("UPDATE `numericadocumental` SET `evaluacion` = '" + evaluacion + "',`documento` = '" + nombreDoc + "',`accion` = '" + accion + "',`responsable` = '" + responsable + "', `medio` = '" + medio + "', `lugar` = '" + lugar + "' WHERE `numericadocumental`.`id` ='" + idNumDoc + "'", bd);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (Error ex) {
            Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
        /*
         * if
         * (request.getParameter("action").equals("asignarPonderacionFactorAIp"))
         * { HttpSession session = request.getSession(); sqlController conSql =
         * new sqlController();
         *
         * int numRows = Integer.parseInt(request.getParameter("count")); int
         * idproceso = 0; ResultSet rs = null; String bd = (String)
         * session.getAttribute("bd");
         *
         * Proceso proceso = (Proceso) session.getAttribute("proceso"); int
         * idProceso = proceso.getId();
         *
         * session.setAttribute("idproceso", idproceso);
         *
         * for (int i = 1; i <= numRows; i++) { String id =
         * request.getParameter("id" + i); String ponderacion =
         * request.getParameter("ponderacion" + i); String justificacion =
         * request.getParameter("justificacion" + i);
         *
         *
         * conSql.UpdateSql("INSERT INTO `ponderacionfactor` (`id`,
         * `ponderacion`, `justificacion`, `proceso_id`, `factor_id`) VALUES
         * (NULL, '" + ponderacion + "', '" + justificacion + "', '" + idProceso
         * + "', '" + id + "');", bd); }
         *
         *
         * }
         */
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
