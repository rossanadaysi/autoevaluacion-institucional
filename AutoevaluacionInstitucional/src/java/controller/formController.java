/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Asignacionencuesta;
import entity.Proceso;
import entity.Programa;
import entity.controller.ProcesoJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;
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
                    System.out.println("Distinto de 100");
                    session.setAttribute("auxAsignarF1", 0);
                } else {
                    System.out.println("igual a 100");
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

                            rs = conSql.CargarSql("Select id from ponderacionfactor where ponderacionfactor.proceso_id = '" + idProceso + "' and ponderacionfactor.factor_id = '" + id + "'", bd);
                            try {
                                while (rs.next()) {
                                    idPonderacion = Integer.parseInt(rs.getString(1));
                                    System.out.println(idPonderacion);
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                            }


                            conSql.UpdateSql("UPDATE `ponderacionfactor` SET `ponderacion` = '" + ponderacion + "',`justificacion` = '" + justificacion + "' WHERE `ponderacionfactor`.`id` ='" + idPonderacion + "'", bd);
                        }

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
                    System.out.println("count " + numRows);
                    for (int i = 1; i <= numRows; i++) {
                        String id = request.getParameter("id" + i);
                        String ponderacion = request.getParameter("ponderacion" + i);
                        String justificacion = request.getParameter("justificacion" + i);
                        int ponde = Integer.parseInt(ponderacion);



                        conSql.UpdateSql("INSERT INTO `ponderacioncaracteristica` (`id`, `nivelimportancia`, `ponderacion`, `justificacion`, `proceso_id`, `caracteristica_id`) VALUES (NULL, '" + ponderacion + "', '" + 0 + "', '" + justificacion + "', '" + idProceso + "', '" + id + "');", bd);



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
                                System.out.println("suma: " + s);
                                suma = Double.parseDouble(s);
                                String p = rs1.getString(2);
                                System.out.println("ponderacionF:" + p);
                                ponfa = Double.parseDouble(p);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        double a = (100 * ponde) / suma;
                        System.out.println("a> " + a);
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

                System.out.println(id);
                System.out.println(ponde);
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
                System.out.println("a> " + a);
                double b = ((ponfa * a) / 100);

                double r;


                int decimalPlaces = 2;
                BigDecimal bde = new BigDecimal(b);

// setScale is immutable
                bde = bde.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
                r = bde.doubleValue();
                System.out.println("r: " + r);
                session.setAttribute("pondec", r);
                session.setAttribute("idc", id);
                //  conSql.UpdateSql("UPDATE `ponderacioncaracteristica` SET `ponderacion` = '" + r + "' WHERE `ponderacioncaracteristica`.`proceso_id` = '" + idProceso + "' and `ponderacioncaracteristica`.`caracteristica_id` = '" + id + "'", bd);


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
                System.out.println("hey");
                rs = conSql.CargarSql(sql, bd);
                try {
                    while (rs.next()) {

                        if (request.getParameter(rs.getString(2)).equals("1")) {
                            System.out.println("hey2");
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
                    System.out.println("id: " + id);
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
                    "action").equals("asignarMuestraAIp")) {

                System.out.println("entro");
                HttpSession session = request.getSession();
                Proceso proceso = (Proceso) session.getAttribute("proceso");
                Asignacionencuesta ae = new Asignacionencuesta();

                sqlController conSql = new sqlController();


                String idFuente = request.getParameter("fuente");

                System.out.println("idfuentexxx : " + idFuente);

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
                System.out.println("Delete: " + sql2);
                conSql.UpdateSql(sql2, bd);


                ResultSet rs = null;
                String sql = "Select* from " + tabla1 + " where estudiante.programa_id = " + idP + " and estudiante.semestre = " + idS;

                System.out.println("Select: " + sql);

                rs = conSql.CargarSql(sql, bd);
                try {
                    System.out.println("insertando0");
                    while (rs.next()) {
                        if (request.getParameter(rs.getString(1)).equals("1")) {
                            System.out.println("insertando" + rs.getString(1));
                            sql2 = "INSERT INTO `" + tabla + "` (`id`, `muestra_id`, `" + tabla1 + "_id`) VALUES (NULL, '" + idMuestra + "', '" + rs.getString(1) + "')";
                            conSql.UpdateSql(sql2, bd);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("termino esa monda");

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
                        //  System.out.println("si hay asignacion de muestras");
                        session.setAttribute("muestrasSeleccionadas", rs);
                        session.setAttribute("aux_asignarM", 1);

                    } else {
                        //f  System.out.println("no hay asignacion de muestras!!!!!");
                        session.setAttribute("aux_asignarM", 0);
                    }


                } catch (Error ex) {
                    //  Logger.getLogger(fontController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (request.getParameter(
                    "action").equals("selectorAsignarMuestra3AI")) {

                System.out.println("selectorAsignarMuestra3AI");
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
                            System.out.println(sql2);
                            rs2 = conSql.CargarSql2(sql2, bd);
                            if (rs2 != null) {
                                session.setAttribute("muestras", rs2);
                                System.out.println("result: " + rs2.getRowCount());
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
                            System.out.println(sql2);
                            fil = 1;
                        } else if (!idS.equals("--")) {
                            session.setAttribute("aux_selectorAsignarM3", 0);
                            //sql2 = "Select " + tabla1 + ".id, persona.id, persona.nombre, persona.apellido from " + tabla1 + " inner join persona on " + tabla1 + ".persona_id = persona.id where " + tabla1 + ".semestre = " + idS;
                            System.out.println(sql2);
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

                //Estatico
                if (id == 1) {


                    String idP = request.getParameter("programas");
                    String idS = request.getParameter("semestres");
                    if (!idP.equals("--") && !idS.equals("--")) {
                        sql = "select persona.id, estudiante.id, persona.nombre, persona.apellido, estudiante.semestre from muestraestudiante inner join estudiante on muestraestudiante.estudiante_id = estudiante.id inner join persona on estudiante.persona_id = persona.id where muestraestudiante.muestra_id = " + idMuestra + " and estudiante.programa_id = " + idP + " and estudiante.semestre = " + idS + " order by estudiante.id";
                    } else if (!idP.equals("--")) {
                        sql = "select persona.id, estudiante.id, persona.nombre, persona.apellido, estudiante.semestre from muestraestudiante inner join estudiante on muestraestudiante.estudiante_id = estudiante.id inner join persona on estudiante.persona_id = persona.id where muestraestudiante.muestra_id = " + idMuestra + " and estudiante.programa_id = " + idP + " order by estudiante.id";
                    } else if (!idS.equals("--")) {
                        sql = "select persona.id, estudiante.id, persona.nombre, persona.apellido, estudiante.semestre from muestraestudiante inner join estudiante on muestraestudiante.estudiante_id = estudiante.id inner join persona on estudiante.persona_id = persona.id where muestraestudiante.muestra_id = " + idMuestra + " and estudiante.semestre = " + idS + " order by estudiante.id";
                    } else if (idP.equals("--") && idS.equals("--")) {
                        //sql = "select persona.id, estudiante.id, persona.nombre, persona.apellido, estudiante.semestre from muestraestudiante inner join estudiante on muestraestudiante.estudiante_id = estudiante.id inner join persona on estudiante.persona_id = persona.id where muestraestudiante.muestra_id = " + idMuestra + " order by estudiante.id";
                    }


                } else if (id == 2) {

                    String idP = request.getParameter("programas3");

                    if (!idP.equals("--")) {
                        sql = "select persona.id, persona.nombre, persona.apellido, persona.password from muestradocente inner join docente on muestradocente.docente_id = docente.id inner join persona on docente.persona_id = persona.id where muestradocente.muestra_id = " + idMuestra + " and docente.programa_id = " + idP + " order by docente.id";
                    } else if (!idP.equals("--")) {
                        sql = "select persona.id, persona.nombre, persona.apellido, persona.password from muestradocente inner join docente on muestradocente.docente_id = docente.id inner join persona on docente.persona_id = persona.id where muestradocente.muestra_id = " + idMuestra + " and docente.programa_id = " + idP + " order by docente.id";
                    } else if (idP.equals("--")) {
                        //sql = "select persona.id, estudiante.id, persona.nombre, persona.apellido, estudiante.semestre from muestraestudiante inner join estudiante on muestraestudiante.estudiante_id = estudiante.id inner join persona on estudiante.persona_id = persona.id where muestraestudiante.muestra_id = " + idMuestra + " order by estudiante.id";
                    }

                } else {
                    sql = "select persona.nombre, persona.apellido, persona.password from " + tabla1 + " inner join " + tabla + " on " + tabla1 + "." + tabla + "_id = " + tabla + ".id inner join persona on " + tabla + ".persona_id = persona.id where " + tabla1 + ".muestra_id = " + idMuestra + " order by " + tabla + ".id";
                    System.out.println("sql8: " + sql);
                }

                session.setAttribute("idFuenteMuestra", id);

                Result rs = conSql.CargarSql2(sql, bd);
                session.setAttribute("selectorAsignarM2", rs);
            } else if (request.getParameter(
                    "action").equals("generarMuestra")) {

                HttpSession session = request.getSession();

                session.setAttribute("selectorAsignarM2", null);
                Proceso proceso = (Proceso) session.getAttribute("proceso");

                sqlController conSql = new sqlController();

                String idFormula = request.getParameter("formula");
                String idFuente = request.getParameter("fuente");
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
                String aux = null;


                ArrayList rs = (ArrayList) session.getAttribute("muestraCalculada2");

                if (rs != null) {

                    Iterator i = rs.iterator();

                    try {

                        conSql.UpdateSql("TRUNCATE TABLE `" + tabla + "` ", bd);

                        if (id == 2) {

                            String sql = "DELETE from docente where docente.tipo = 'aleatorioDocente'";
                            conSql.UpdateSql(sql, bd);

                            sql = "DELETE from persona where persona.mail = 'aleatorioDocente'";
                            conSql.UpdateSql(sql, bd);

                        }
                        if (id == 3) {

                            String sql = "DELETE from administrativo where administrativo.persona_id = (Select id from persona where persona.mail = 'aleatorioAdministrativo')";
                            conSql.UpdateSql(sql, bd);

                            sql = "DELETE from persona where persona.mail = 'aleatorioAdministrativo'";
                            conSql.UpdateSql(sql, bd);

                        }
                        if (id == 4) {

                            String sql = "DELETE from directorprograma where directorprograma.persona_id = (Select id from persona where persona.mail = 'aleatorioDirectivo')";
                            conSql.UpdateSql(sql, bd);

                            sql = "DELETE from persona where persona.mail = 'aleatorioDirectivo'";
                            conSql.UpdateSql(sql, bd);

                        }
                        if (id == 5) {

                            String sql = "DELETE from egresado where egresado.persona_id = (Select id from persona where persona.mail = 'aleatorioEgresado')";
                            conSql.UpdateSql(sql, bd);

                            sql = "DELETE from persona where persona.mail = 'aleatorioEgresado'";
                            conSql.UpdateSql(sql, bd);

                        }
                        if (id == 6) {

                            String sql = "DELETE from empleador where empleador.persona_id = (Select id from persona where persona.mail = 'aleatorioEmpleador')";
                            conSql.UpdateSql(sql, bd);

                            sql = "DELETE from persona where persona.mail = 'aleatorioEmpleador'";
                            conSql.UpdateSql(sql, bd);

                        }
                        if (id == 7) {

                            String sql = "DELETE from agenciagubernamental where agenciagubernamental.persona_id = (Select id from persona where persona.mail = 'aleatorioAgencia')";
                            conSql.UpdateSql(sql, bd);

                            sql = "DELETE from persona where persona.mail = 'aleatorioAgencia'";
                            conSql.UpdateSql(sql, bd);

                        }


                        if (id == 3) {
                            aux = "aleatorioAdministrativo";
                            tabla1 = "administrativo";
                        } else if (id == 4) {
                            aux = "aleatorioDirectivo";
                            tabla1 = "directorprograma";
                        } else if (id == 5) {
                            aux = "aleatorioEgresado";
                            tabla1 = "egresado";
                        } else if (id == 6) {
                            aux = "aleatorioEmpleador";
                            tabla1 = "empleador";
                        } else if (id == 7) {
                            aux = "aleatorioAgencia";
                            tabla1 = "agenciagubernamental";
                        }






                    } catch (Error e) {
                        System.out.println(e);
                    }
                    while (i.hasNext()) {

                        String s1 = (String) i.next();
                        String[] campos = s1.split("/");

                        String muestra = campos[campos.length - 1];
                        String programa = campos[campos.length - 2];

                        if (id == 1) {
                            String sql = "SELECT * FROM estudiante where estudiante.programa_id = " + programa + " and estudiante.semestre != 01 and  estudiante.semestre != 02 ORDER BY Rand() LIMIT " + muestra;
                            ResultSet rs1 = conSql.CargarSql(sql, bd);
                            try {
                                while (rs1.next()) {
                                    String sql2 = "insert into muestraestudiante values (null, '" + idMuestra + "'  , '" + rs1.getString(1) + "')";
                                    conSql.UpdateSql(sql2, bd);
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (id == 2) {

                            String sql;
                            int contador = 0;
                            sql = "SELECT id, CONV(FLOOR(RAND() * 99999999999999), 10, 36) from docente ORDER BY Rand() LIMIT " + muestra;
                            System.out.println("sql1: " + sql);
                            ResultSet rs1 = conSql.CargarSql(sql, bd);
                            try {
                                while (rs1.next()) {
                                    String sql2 = "insert into persona values ('" + proceso.getId() + "" + id + "" + programa + "" + contador + "', 'Fuente'  , 'Docente', '" + rs1.getString(2) + "', 'aleatorioDocente')";
                                    System.out.println("sql2: " + sql2);
                                    conSql.UpdateSql(sql2, bd);
                                    sql2 = "insert into docente values (null, 'aleatorioDocente', '" + proceso.getId() + "" + id + "" + programa + "" + contador + "', '" + id + "'  , '" + programa + "')";
                                    System.out.println("sql3: " + sql2);
                                    conSql.UpdateSql(sql2, bd);
                                    sql2 = "insert into muestradocente values (null, '" + idMuestra + "', (SELECT id from docente where persona_id = " + proceso.getId() + "" + id + "" + programa + "" + contador + " LIMIT 1))";
                                    System.out.println("sql4: " + sql2);
                                    conSql.UpdateSql(sql2, bd);
                                    contador++;
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {

                            int d = (int) Math.floor(Double.parseDouble(muestra));

                            for (int j = 0; j < d; j++) {

                                String pass = PasswordGenerator.getPassword(
                                        PasswordGenerator.MINUSCULAS
                                        + PasswordGenerator.MAYUSCULAS
                                        + PasswordGenerator.ESPECIALES, 6);

                                String sql2 = "insert into persona values ('" + proceso.getId() + "" + id + "" + j + "', 'Fuente'  , '" + tabla1 + "', '" + pass + "', '" + aux + "')";
                                System.out.println("sql2: " + sql2);
                                conSql.UpdateSql(sql2, bd);
                                if (id == 3 || id == 4 || id == 5) {
                                    sql2 = "insert into " + tabla1 + " values (null, '" + proceso.getId() + "" + id + "" + j + "', '" + id + "'  , '" + 1 + "')";
                                    System.out.println("sql3: " + sql2);
                                    conSql.UpdateSql(sql2, bd);
                                }
                                if (id == 6) {
                                    sql2 = "insert into " + tabla1 + " values (null, null, '" + proceso.getId() + "" + id + "" + j + "', '" + id + "'  , '" + 1 + "')";
                                    System.out.println("sql3: " + sql2);
                                    conSql.UpdateSql(sql2, bd);
                                }
                                if (id == 7) {
                                    sql2 = "insert into " + tabla1 + " values (null, null, '" + proceso.getId() + "" + id + "" + j + "', '" + id + "')";
                                    System.out.println("sql3: " + sql2);
                                    conSql.UpdateSql(sql2, bd);
                                }
                                sql2 = "insert into " + tabla + " values (null, '" + idMuestra + "', (SELECT id from " + tabla1 + " where persona_id = " + proceso.getId() + "" + id + "" + j + " LIMIT 1))";
                                System.out.println("sql4: " + sql2);
                                conSql.UpdateSql(sql2, bd);


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

                sqlController conSql = new sqlController();

                String idFormula = request.getParameter("formula");
                String idFuente = request.getParameter("fuente");
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

                        String aux = request.getParameter("tamanioPobla");
                        System.out.println("aux: " + aux);

                        if (aux != null) {
                            N = Integer.parseInt(aux);
                            System.out.println("Manual");
                        } else {
                            System.out.println("Bd");
                            sql = "Select count(*) from " + tabla1;
                            rs = conSql.CargarSql(sql, bd);
                            while (rs.next()) {
                                N = Double.parseDouble(rs.getString(1));
                            }
                        }


                        if (N != 0.0) {

                            n = (N * p * q * (z * z)) / ((N - 1) * (e * e) + p * q * (z * z));

                            ArrayList l = new ArrayList();
                            String s;
                            String sql2 = null;

                            if (idFuente.equals("1")) {
                                double cociente = n / N;
                                sql2 = "Select programa.nombre, ROUND((count(*)*" + cociente + ")*1.2,0), programa.id from estudiante inner join programa on estudiante.programa_id = programa.id group by estudiante.programa_id order by programa.nombre";
                                Result result = conSql.CargarSql2(sql, bd);
                                session.setAttribute("muestraCalculada", result);
                            } else if (idFuente.equals("2")) {

                                sql2 = "Select programa.nombre, ROUND((count(*)*0.2),0), programa.id from docente inner join programa on docente.programa_id = programa.id inner join persona on docente.persona_id = persona.id  where docente.tipo <> 'aleatorio' group by docente.programa_id order by programa.nombre";
                                Result result = conSql.CargarSql2(sql, bd);
                                session.setAttribute("muestraCalculada", result);
                            } else {
                                if (n != 0) {
                                    s = "Muestra/" + n;
                                    int d = (int) Math.floor(n);
                                    session.setAttribute("muestraIndividual", d);
                                } else {
                                    s = "Muestra/" + 1;
                                    session.setAttribute("muestraIndividual", 1);
                                }
                                l.add(s);
                                session.setAttribute("muestraCalculada", null);
                            }

                            if (sql2 != null) {
                                ResultSet rs1 = conSql.CargarSql(sql2, bd);

                                while (rs1.next()) {

                                    if (rs1.getString(2).equals("0")) {
                                        s = rs1.getString(3) + "/" + "1";
                                    } else {
                                        s = rs1.getString(3) + "/" + rs1.getString(2);
                                    }
                                    l.add(s);
                                }


                            }

                            session.setAttribute("muestraCalculada2", l);

                        } else {
                            System.out.println("null");
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
                    System.out.println("bd");
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

                    session.setAttribute("proceso", proceso);
                    session.setAttribute("aux_index2", 1);
                    session.setAttribute("aux2_index2", 1);
                    session.setAttribute("msjLogIn1", "Existe un Proceso en Ejecución!");
                    session.setAttribute("msjLogIn2", "Detalle del Proceso.");
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
                }



            } else if (request.getParameter(
                    "action").equals("CerrarProcesoAI")) {

                HttpSession session = request.getSession();
                Proceso p = (Proceso) session.getAttribute("proceso");
                ProcesoJpaController pc = new ProcesoJpaController();
                Date d = new Date();
                String date = String.valueOf(d);
                p.setFechacierre(date);
                try {
                    pc.edit(p);


                } catch (entity.controller.exceptions.NonexistentEntityException ex) {
                    Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                }

                session.setAttribute("aux_index2", 0);
                System.out.println("modificado");
                session.setAttribute("estadoProceso", 2);


            } else if (request.getParameter(
                    "action").equals("IniciarProcesoAI")) {

                HttpSession session = request.getSession();
                Proceso p = (Proceso) session.getAttribute("proceso");
                ProcesoJpaController pc = new ProcesoJpaController();
                Date d = new Date();
                String date = String.valueOf(d);
                p.setFechainicio(date);
                /*
                 * int f = (Integer) session.getAttribute("auxAsignarF");
                 *
                 * if (f == 0) { System.out.println("Debe asignar la podenracion
                 * de los factores."); session.setAttribute("aux_IniciarP", 0);
                 * } int c = (Integer) session.getAttribute("auxAsignarC");
                 * System.out.println("cccccccccccccccc" +c); if (c == 0) {
                 * System.out.println("Debe asignar la podenracion de los
                 * caracteristicas."); session.setAttribute("aux_IniciarP", 0);
                 * }
                 *
                 *
                 *
                 *
                 *
                 * //valida asig encuesta String bd = (String)
                 * session.getAttribute("bd"); sqlController conSql = new
                 * sqlController(); Proceso proceso = (Proceso)
                 * session.getAttribute("proceso"); int idProceso =
                 * proceso.getId(); int count = 0; int count2 = 0;
                 *
                 * ResultSet rs2 = conSql.CargarSql("Select id from fuente",
                 * bd); try { while (rs2.next()) { count2++; Result rs = null;
                 * String sql = "Select* from asignacionencuesta where
                 * proceso_id = " + idProceso + " and fuente_id = " +
                 * rs2.getString(1); rs = conSql.CargarSql2(sql, bd); if
                 * (rs.getRowCount() != 0) { count++; } } } catch (SQLException
                 * ex) {
                 * Logger.getLogger(formController.class.getName()).log(Level.SEVERE,
                 * null, ex); }
                 *
                 * int e = 0; System.out.println("()" + count + "-" + count2);
                 * if (count == count2) { e = 1;
                 *
                 * } else { System.out.println("Debe asignar las Encuestas.");
                 * session.setAttribute("aux_IniciarP", 0); }
                 *
                 *
                 *
                 *
                 * if (f != 0 && c != 0 && e != 0) {
                 */
                try {
                    pc.edit(p);


                } catch (entity.controller.exceptions.NonexistentEntityException ex) {
                    Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                }

                session.setAttribute("aux2_index2", 0);
                session.setAttribute("estadoProceso", 1);
            }
            //  }
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
