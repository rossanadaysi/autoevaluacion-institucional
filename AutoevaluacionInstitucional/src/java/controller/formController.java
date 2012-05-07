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

                System.out.println("Entra: " + id);
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
                    String idP = request.getParameter("programas4");
                    if (!idP.equals("--")) {
                        sql = "select persona.nombre, persona.apellido, persona.password from " + tabla + " inner join " + tabla1 + " on " + tabla + "." + tabla1 + "_id = " + tabla1 + ".id inner join persona on " + tabla1 + ".persona_id = persona.id where " + tabla + ".muestra_id = " + idMuestra + " order by " + tabla + ".id";
                        System.out.println("sql8: " + sql);
                    }
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

                            String sql = "DELETE from administrativo where persona_id in (select id from persona WHERE mail='aleatorioAdministrativo')";
                            conSql.UpdateSql(sql, bd);

                            sql = "DELETE from persona where persona.mail = 'aleatorioAdministrativo'";
                            conSql.UpdateSql(sql, bd);

                        }
                        if (id == 4) {

                            String sql = "DELETE from directorprograma where persona_id in = (Select id from persona where persona.mail = 'aleatorioDirectivo')";
                            conSql.UpdateSql(sql, bd);

                            sql = "DELETE from persona where persona.mail = 'aleatorioDirectivo'";
                            conSql.UpdateSql(sql, bd);

                        }
                        if (id == 5) {

                            String sql = "DELETE from egresado where persona_id in = (Select id from persona where persona.mail = 'aleatorioEgresado')";
                            conSql.UpdateSql(sql, bd);

                            sql = "DELETE from persona where persona.mail = 'aleatorioEgresado'";
                            conSql.UpdateSql(sql, bd);

                        }
                        if (id == 6) {

                            String sql = "DELETE from empleador where persona_id in = (Select id from persona where persona.mail = 'aleatorioEmpleador')";
                            conSql.UpdateSql(sql, bd);

                            sql = "DELETE from persona where persona.mail = 'aleatorioEmpleador'";
                            conSql.UpdateSql(sql, bd);

                        }
                        if (id == 7) {

                            String sql = "DELETE from agenciagubernamental where persona_id in = (Select id from persona where persona.mail = 'aleatorioAgencia')";
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
                                    conSql.UpdateSql(sql2, bd);
                                    sql2 = "insert into docente values (null, 'aleatorioDocente', '" + proceso.getId() + "" + id + "" + programa + "" + contador + "', '" + id + "'  , '" + programa + "')";
                                    conSql.UpdateSql(sql2, bd);
                                    sql2 = "insert into muestradocente values (null, '" + idMuestra + "', (SELECT id from docente where persona_id = " + proceso.getId() + "" + id + "" + programa + "" + contador + " LIMIT 1))";
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
                                        + PasswordGenerator.NUMEROS
                                        + PasswordGenerator.ESPECIALES, 6);

                                String sql2 = "insert into persona values ('" + proceso.getId() + "" + id + "" + j + "', 'Fuente'  , '" + tabla1 + "', '" + pass + "', '" + aux + "')";
                                conSql.UpdateSql(sql2, bd);
                                if (id == 3 || id == 4 || id == 5) {
                                    sql2 = "insert into " + tabla1 + " values (null, '" + proceso.getId() + "" + id + "" + j + "', '" + id + "'  , '" + 1 + "')";
                                    conSql.UpdateSql(sql2, bd);
                                }
                                if (id == 6) {
                                    sql2 = "insert into " + tabla1 + " values (null, null, '" + proceso.getId() + "" + id + "" + j + "', '" + id + "'  , '" + 1 + "')";
                                    conSql.UpdateSql(sql2, bd);
                                }
                                if (id == 7) {
                                    sql2 = "insert into " + tabla1 + " values (null, null, '" + proceso.getId() + "" + id + "" + j + "', '" + id + "')";
                                    conSql.UpdateSql(sql2, bd);
                                }
                                sql2 = "insert into " + tabla + " values (null, '" + idMuestra + "', (SELECT id from " + tabla1 + " where persona_id = " + proceso.getId() + "" + id + "" + j + " LIMIT 1))";
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

                        String manual = request.getParameter("tamanioPobla");

                        if (!"".equals(manual)) {
                            N = Integer.parseInt(manual);
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
                                Result result = conSql.CargarSql2(sql2, bd);
                                session.setAttribute("muestraCalculada", result);
                            } else if (idFuente.equals("2")) {
                                sql2 = "Select programa.nombre, ROUND((count(*)*0.2),0), programa.id from docente inner join programa on docente.programa_id = programa.id inner join persona on docente.persona_id = persona.id  where docente.tipo <> 'aleatorioDocente' group by docente.programa_id order by programa.nombre";
                                Result result = conSql.CargarSql2(sql2, bd);
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
                            if (idFuente.equals("1") || idFuente.equals("2")) {
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
                session.setAttribute("aux_IniciarP", 2);


            } else if (request.getParameter(
                    "action").equals("IniciarProcesoAI")) {


                HttpSession session = request.getSession();
                String bd = (String) session.getAttribute("bd");
                sqlController conSql = new sqlController();
                Proceso p = (Proceso) session.getAttribute("proceso");
                int idProceso = p.getId();
                ProcesoJpaController pc = new ProcesoJpaController();
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
                        pc.edit(p);
                    } catch (entity.controller.exceptions.NonexistentEntityException ex) {
                        Logger.getLogger(formController.class.getName()).log(Level.SEVERE,
                                null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(formController.class.getName()).log(Level.SEVERE,
                                null, ex);
                    }
                    session.setAttribute("aux_IniciarP", 1);
                    session.setAttribute("aux2_index2", 0);
                    session.setAttribute("estadoProceso", 1);
                }


            } else if (request.getParameter(
                    "action").equals("evaluarInfoDocumentalAI")) {

                HttpSession session = request.getSession();
                sqlController conSql = new sqlController();

                int numRows = Integer.parseInt(request.getParameter("count"));
                ResultSet rs = null;
                String bd = (String) session.getAttribute("bd");
                int idProceso = (Integer) session.getAttribute("idproceso");

                if (session.getAttribute("auxInfoDocumental").equals(0)) {

                    for (int i = 1; i <= numRows; i++) {
                        String id = request.getParameter("idIndicadorDoc" + i);
                        String evaluacion = request.getParameter("evaluacionDoc" + i);
                        String nombreDoc = request.getParameter("nombreDocumento" + i);
                        String accion = request.getParameter("accionDocumento" + i);
                        String responsable = request.getParameter("responsableDocumento" + i);

                        conSql.UpdateSql("INSERT INTO `institucional9`.`numericadocumental` (`id`, `evaluacion`, `nombre`, `accion`, `responsable`, `indicador_id`, `proceso_id`) VALUES (NULL, '" + evaluacion + "', '" + nombreDoc + "', '" + accion + "', '" + responsable + "', '" + id + "', '" + idProceso + "')", bd);
                    }

                    session.setAttribute("auxInfoDocumental", 1);

                } else {

                    for (int i = 1; i <= numRows; i++) {
                        String id = request.getParameter("idIndicadorDoc" + i);
                        String evaluacion = request.getParameter("evaluacionDoc" + i);
                        String nombreDoc = request.getParameter("nombreDocumento" + i);
                        String accion = request.getParameter("accionDocumento" + i);
                        String responsable = request.getParameter("responsableDocumento" + i);
                        int idNumDoc = 0;

                        rs = conSql.CargarSql("Select id from numericadocumental where numericadocumental.proceso_id = '" + idProceso + "' and numericadocumental.indicador_id = '" + id + "'", bd);
                        try {
                            while (rs.next()) {
                                idNumDoc = Integer.parseInt(rs.getString(1));
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(formController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        conSql.UpdateSql("UPDATE `numericadocumental` SET `evaluacion` = '" + evaluacion + "',`nombre` = '" + nombreDoc + "',`accion` = '" + accion + "',`responsable` = '" + responsable + "' WHERE `numericadocumental`.`id` ='" + idNumDoc + "'", bd);

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
