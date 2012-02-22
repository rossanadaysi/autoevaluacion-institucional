/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.sqlConnection;
import entity.Proceso;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

/**
 *
 * @author Arturo González
 */
public class sqlController {
    
    private sqlConnection linkDB = null;
    private Connection con = null;

    //////////////////////////////////////////////////////////////////////
    public void setConexion(sqlConnection conex) {
        linkDB = conex;
        con = linkDB.getConnection();
        System.out.println("[CONEXION RECIBIDA]");
    }

    ///////////////////////////////////////////////////////////////////////
    public boolean isConected() {
        boolean result = false;
        if ((linkDB != null) && (linkDB.isConnect())) {
            result = true;
        }
        return result;
    }

    /*
     * public ResultSet CargarSql(String sql) {
     *
     * ResultSet rs = null; Connection con = null; sqlConnection sqlCon = new
     * sqlConnection();
     *
     * try {
     *
     * con = (Connection) sqlCon.getConecction();
     *
     * Statement st = (Statement) con.createStatement(); System.out.println("Se
     * ha realizado con exito la conexiÃƒÂ³n a MySQL"); //el resultSet es el
     * encargado de traer los datos de la consulta rs = st.executeQuery(sql);
     *
     *
     * } catch (SQLException ex) { System.out.println(ex); } finally { try {
     * con.close(); System.out.println("sqlConnection Cerrada con Exito..."); }
     * catch (SQLException ex) { System.out.println(ex); } } return rs; }
     */
    public void newDb2(Proceso proceso) throws IOException, SQLException, FileNotFoundException {
        
        String nombre = proceso.getProgramaId().getNombre();
        int id = proceso.getId();
        System.out.println("el id : " + id);
        
        sqlConnection conSql = new sqlConnection();
        conSql.conectarMySQL("autoevaluacion");
        
        this.setConexion(conSql);
        
        String name1 = "`" + nombre + id + "`;";
        Statement stmt = con.createStatement();
        stmt.executeUpdate("create database " + name1);
        
        String script = "C:\\Users\\Usuario\\Desktop\\21 febrero\\AutoevaluacionInstitucional\\web\\scriptsSql\\script.sql";
        
        String nameBd = nombre + id;
        
        this.cargarScript(nameBd, script);
        
        
        script = "C:\\Users\\Usuario\\Desktop\\21 febrero\\AutoevaluacionInstitucional\\web\\scriptsSql\\script2.sql";
        
        this.cargarScript(nameBd, script);
        //  this.resetDB_gtbio(stmt);
        //stmt.close();
    }
    
    public void newDb(Proceso proceso) throws IOException, SQLException, FileNotFoundException {
        
    /*    String nombre = proceso.getProgramaId().getNombre();
        int id = proceso.getId();
        System.out.println("el id : " + id);
        
        sqlConnection conSql = new sqlConnection();
        conSql.conectarMySQL("autoevaluacion");
        
        this.setConexion(conSql);
        
        String name1 = "`" + nombre + id + "`;";
        Statement stmt = con.createStatement();
        stmt.executeUpdate("create database " + name1);
        
        
        String nameBd = nombre + id;
        
        Result rs2 = null;
        String sql2 = " create database `sisa`;"
                + " use `sisa`;"
                + " SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';"
                + " SET time_zone = '+00:00';"
                + " CREATE TABLE IF NOT EXISTS `administrativo` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `persona_id` int(11) NOT NULL,"
                + " `fuente_id` int(11) NOT NULL,"
                + " `programa_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_administrativo_persona1` (`persona_id`),"
                + " KEY `fk_administrativo_fuente1` (`fuente_id`),"
                + " KEY `fk_administrativo_programa1` (`programa_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `agenciagubernamental` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `descripcion` varchar(200) DEFAULT NULL,"
                + " `persona_id` int(11) NOT NULL,"
                + " `fuente_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_agenciagubernamental_persona1` (`persona_id`),"
                + " KEY `fk_agenciagubernamental_fuente1` (`fuente_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `asignacionencuesta` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `proceso_id` int(11) NOT NULL,"
                + " `fuente_id` int(11) NOT NULL,"
                + " `encuesta_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_asignacionencuesta_proceso1` (`proceso_id`),"
                + " KEY `fk_asignacionencuesta_fuente1` (`fuente_id`),"
                + " KEY `fk_asignacionencuesta_encuesta1` (`encuesta_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `caracteristica` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `nombre` varchar(200) NOT NULL,"
                + " `descripcion` varchar(200) DEFAULT NULL,"
                + " `factor_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_caracteristica_factor1` (`factor_id`)"
                + " ) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=35 ;"
                + " CREATE TABLE IF NOT EXISTS `directorprograma` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `persona_id` int(11) NOT NULL,"
                + " `fuente_id` int(11) NOT NULL,"
                + " `programa_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_directorprograma_fuente1` (`fuente_id`),"
                + " KEY `fk_directorprograma_programa1` (`programa_id`),"
                + " KEY `fk_directorprograma_persona1` (`persona_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `docente` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `tipo` varchar(45) NOT NULL,"
                + " `persona_id` int(11) NOT NULL,"
                + " `fuente_id` int(11) NOT NULL,"
                + " `programa_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_docente_fuente1` (`fuente_id`),"
                + " KEY `fk_docente_persona1` (`persona_id`),"
                + " KEY `fk_docente_programa1` (`programa_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `egresado` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `persona_id` int(11) NOT NULL,"
                + " `fuente_id` int(11) NOT NULL,"
                + " `programa_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_egresado_persona1` (`persona_id`),"
                + " KEY `fk_egresado_fuente1` (`fuente_id`),"
                + " KEY `fk_egresado_programa1` (`programa_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `empleador` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `descripcion` varchar(200) DEFAULT NULL,"
                + " `persona_id` int(11) NOT NULL,"
                + " `fuente_id` int(11) NOT NULL,"
                + " `sectorempresarial_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_empleador_persona1` (`persona_id`),"
                + " KEY `fk_empleador_fuente1` (`fuente_id`),"
                + " KEY `fk_empleador_sectorempresarial1` (`sectorempresarial_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `encuesta` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `nombre` varchar(45) NOT NULL,"
                + " `descripcion` varchar(200) DEFAULT NULL,"
                + " `instrucciones` varchar(200) DEFAULT NULL,"
                + " `mensaje` varchar(200) DEFAULT NULL,"
                + " `fecha` datetime NOT NULL,"
                + " PRIMARY KEY (`id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `encuestahaspregunta` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `encuesta_id` int(11) NOT NULL,"
                + " `pregunta_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_encuestahaspregunta_encuesta1` (`encuesta_id`),"
                + " KEY `fk_encuestahaspregunta_pregunta1` (`pregunta_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `estudiante` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `semestre` varchar(45) NOT NULL,"
                + " `periodo` varchar(45) NOT NULL,"
                + " `aÃ±o` varchar(45) NOT NULL,"
                + " `persona_id` int(11) NOT NULL,"
                + " `fuente_id` int(11) NOT NULL,"
                + " `programa_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_estudiante_persona1` (`persona_id`),"
                + " KEY `fk_estudiante_fuente1` (`fuente_id`),"
                + " KEY `fk_estudiante_programa1` (`programa_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `factor` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `nombre` varchar(200) NOT NULL,"
                + " `descripcion` varchar(200) DEFAULT NULL,"
                + " PRIMARY KEY (`id`)"
                + " ) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;"
                + " CREATE TABLE IF NOT EXISTS `facultad` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `nombre` varchar(45) NOT NULL,"
                + " `descripcion` varchar(200) DEFAULT NULL,"
                + " PRIMARY KEY (`id`),"
                + " UNIQUE KEY `nombre_UNIQUE` (`nombre`),"
                + " UNIQUE KEY `idFacultad_UNIQUE` (`id`)"
                + " ) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;"
                + " CREATE TABLE IF NOT EXISTS `fuente` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `nombre` varchar(45) NOT NULL,"
                + " `descripcion` varchar(200) DEFAULT NULL,"
                + " PRIMARY KEY (`id`),"
                + " UNIQUE KEY `nombre_UNIQUE` (`nombre`),"
                + " UNIQUE KEY `idFuente_UNIQUE` (`id`)"
                + " ) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;"
                + " CREATE TABLE IF NOT EXISTS `indicador` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `nombre` varchar(200) NOT NULL,"
                + " `descripcion` varchar(200) DEFAULT NULL,"
                + " `caracteristica_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_indicador_caracteristica1` (`caracteristica_id`)"
                + " ) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=76 ;"
                + " CREATE TABLE IF NOT EXISTS `muestra` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `formula` varchar(500) DEFAULT NULL,"
                + " `proceso_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_muestra_proceso1` (`proceso_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `muestraadministrativo` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `muestra_id` int(11) NOT NULL,"
                + " `administrativo_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_muestraadministrativo_muestra1` (`muestra_id`),"
                + " KEY `fk_muestraadministrativo_administrativo1` (`administrativo_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `muestraagencia` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `muestra_id` int(11) NOT NULL,"
                + " `agenciagubernamental_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_muestraagencia_agenciagubernamental1` (`agenciagubernamental_id`),"
                + " KEY `fk_muestraagencia_muestra1` (`muestra_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `muestradirector` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `muestra_id` int(11) NOT NULL,"
                + " `directorprograma_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_muestradirector_muestra1` (`muestra_id`),"
                + " KEY `fk_muestradirector_directorprograma1` (`directorprograma_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `muestradocente` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `muestra_id` int(11) NOT NULL,"
                + " `docente_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_muestraprofesor_muestra1` (`muestra_id`),"
                + " KEY `fk_muestraprofesor_docente1` (`docente_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `muestraegresado` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `muestra_id` int(11) NOT NULL,"
                + " `egresado_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_muestraegresado_muestra1` (`muestra_id`),"
                + " KEY `fk_muestraegresado_egresado1` (`egresado_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `muestraempleador` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `muestra_id` int(11) NOT NULL,"
                + " `empleador_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_muestraempleador_muestra1` (`muestra_id`),"
                + " KEY `fk_muestraempleador_empleador1` (`empleador_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `muestraestudiante` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `muestra_id` int(11) NOT NULL,"
                + " `estudiante_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_muestraestudiante_muestra1` (`muestra_id`),"
                + " KEY `fk_muestraestudiante_estudiante1` (`estudiante_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `persona` ("
                + " `id` int(11) NOT NULL,"
                + " `nombre` varchar(45) NOT NULL,"
                + " `apellido` varchar(45) NOT NULL,"
                + " `password` varchar(45) NOT NULL,"
                + " `mail` varchar(45) DEFAULT NULL,"
                + " PRIMARY KEY (`id`),"
                + " UNIQUE KEY `idPersona_UNIQUE` (`id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8;"
                + " CREATE TABLE IF NOT EXISTS `ponderacioncaracteristica` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `ponderacion` int(11) NOT NULL,"
                + " `justificacion` varchar(500) NOT NULL,"
                + " `proceso_id` int(11) NOT NULL,"
                + " `caracteristica_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_ponderacioncaracteristica_proceso1` (`proceso_id`),"
                + " KEY `fk_ponderacioncaracteristica_caracteristica1` (`caracteristica_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `ponderacionfactor` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `ponderacion` int(11) NOT NULL,"
                + " `justificacion` varchar(500) NOT NULL,"
                + " `proceso_id` int(11) NOT NULL,"
                + " `factor_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_ponderacionfactor_proceso1` (`proceso_id`),"
                + " KEY `fk_ponderacionfactor_factor1` (`factor_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `pregunta` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `pregunta` varchar(500) NOT NULL,"
                + " `tipo` varchar(45) NOT NULL,"
                + " `indicador_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_pregunta_indicador1` (`indicador_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `privilegio` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `nombre` varchar(45) NOT NULL,"
                + " `descripcion` varchar(500) DEFAULT NULL,"
                + " PRIMARY KEY (`id`)"
                + " ) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;"
                + " CREATE TABLE IF NOT EXISTS `proceso` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `fechainicio` varchar(30) NOT NULL,"
                + " `fechacierre` varchar(30) DEFAULT NULL,"
                + " `descripcion` varchar(200) DEFAULT NULL,"
                + " `programa_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " UNIQUE KEY `idProceso_UNIQUE` (`id`),"
                + " KEY `fk_proceso_programa1` (`programa_id`)"
                + " ) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;"
                + " CREATE TABLE IF NOT EXISTS `programa` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `nombre` varchar(45) NOT NULL,"
                + " `descripcion` varchar(200) DEFAULT NULL,"
                + " `facultad_id` int(11) DEFAULT NULL,"
                + " `sede_id` int(11) DEFAULT NULL,"
                + " PRIMARY KEY (`id`),"
                + " UNIQUE KEY `nombre_UNIQUE` (`nombre`),"
                + " UNIQUE KEY `idPrograma_UNIQUE` (`id`),"
                + " KEY `fk_programa_facultad1` (`facultad_id`),"
                + " KEY `fk_programa_sede1` (`sede_id`)"
                + " ) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;"
                + " CREATE TABLE IF NOT EXISTS `propuestacaracteristica` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `ponderacion` int(11) NOT NULL,"
                + " `justificacion` varchar(500) DEFAULT NULL,"
                + " `solicitudponderacion_id` int(11) NOT NULL,"
                + " `caracteristica_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_propuestacaracteristica_solicitudponderacion1` (`solicitudponderacion_id`),"
                + " KEY `fk_propuestacaracteristica_caracteristica1` (`caracteristica_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `propuestafactor` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `ponderacion` int(11) NOT NULL,"
                + " `justificacion` varchar(500) DEFAULT NULL,"
                + " `solicitudponderacion_id` int(11) NOT NULL,"
                + " `factor_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_propuestafactor_solicitudponderacion1` (`solicitudponderacion_id`),"
                + " KEY `fk_propuestafactor_factor1` (`factor_id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `representante` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `rol` varchar(45) NOT NULL,"
                + " `persona_id` int(11) NOT NULL,"
                + " `programa_id` int(11) DEFAULT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_representante_persona1` (`persona_id`),"
                + " KEY `fk_representante_programa1` (`programa_id`)"
                + " ) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;"
                + " CREATE TABLE IF NOT EXISTS `representantehasprivilegio` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `representante_id` int(11) NOT NULL,"
                + " `privilegio_id` int(11) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " KEY `fk_representantehasprivilegio_representante1` (`representante_id`),"
                + " KEY `fk_representantehasprivilegio_privilegio1` (`privilegio_id`)"
                + " ) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;"
                + " CREATE TABLE IF NOT EXISTS `sectorempresarial` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `nombre` varchar(45) NOT NULL,"
                + " `descripcion` varchar(200) DEFAULT NULL,"
                + " PRIMARY KEY (`id`),"
                + " UNIQUE KEY `idSectores Empresariales_UNIQUE` (`id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " CREATE TABLE IF NOT EXISTS `sede` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `nombre` varchar(45) NOT NULL,"
                + " `descripcion` varchar(200) DEFAULT NULL,"
                + " `direccion` varchar(45) DEFAULT NULL,"
                + " PRIMARY KEY (`id`),"
                + " UNIQUE KEY `idSedes_UNIQUE` (`id`),"
                + " UNIQUE KEY `nombre_UNIQUE` (`nombre`)"
                + " ) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;"
                + " CREATE TABLE IF NOT EXISTS `solicitudponderacion` ("
                + " `id` int(11) NOT NULL AUTO_INCREMENT,"
                + " `estado` varchar(45) NOT NULL,"
                + " `Representante_Persona_idPersona` int(11) NOT NULL,"
                + " `tipo` varchar(45) NOT NULL,"
                + " PRIMARY KEY (`id`)"
                + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;"
                + " ALTER TABLE `administrativo`"
                + " ADD CONSTRAINT `fk_administrativo_fuente1` FOREIGN KEY (`fuente_id`) REFERENCES `fuente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_administrativo_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_administrativo_programa1` FOREIGN KEY (`programa_id`) REFERENCES `programa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `agenciagubernamental`"
                + " ADD CONSTRAINT `fk_agenciagubernamental_fuente1` FOREIGN KEY (`fuente_id`) REFERENCES `fuente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_agenciagubernamental_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `asignacionencuesta`"
                + " ADD CONSTRAINT `fk_asignacionencuesta_encuesta1` FOREIGN KEY (`encuesta_id`) REFERENCES `encuesta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_asignacionencuesta_fuente1` FOREIGN KEY (`fuente_id`) REFERENCES `fuente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_asignacionencuesta_proceso1` FOREIGN KEY (`proceso_id`) REFERENCES `proceso` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `caracteristica`"
                + " ADD CONSTRAINT `fk_caracteristica_factor1` FOREIGN KEY (`factor_id`) REFERENCES `factor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `directorprograma`"
                + " ADD CONSTRAINT `fk_directorprograma_fuente1` FOREIGN KEY (`fuente_id`) REFERENCES `fuente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_directorprograma_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_directorprograma_programa1` FOREIGN KEY (`programa_id`) REFERENCES `programa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `docente`"
                + " ADD CONSTRAINT `fk_docente_fuente1` FOREIGN KEY (`fuente_id`) REFERENCES `fuente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_docente_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_docente_programa1` FOREIGN KEY (`programa_id`) REFERENCES `programa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `egresado`"
                + " ADD CONSTRAINT `fk_egresado_fuente1` FOREIGN KEY (`fuente_id`) REFERENCES `fuente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_egresado_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_egresado_programa1` FOREIGN KEY (`programa_id`) REFERENCES `programa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `empleador`"
                + " ADD CONSTRAINT `fk_empleador_fuente1` FOREIGN KEY (`fuente_id`) REFERENCES `fuente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_empleador_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_empleador_sectorempresarial1` FOREIGN KEY (`sectorempresarial_id`) REFERENCES `sectorempresarial` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `encuestahaspregunta`"
                + " ADD CONSTRAINT `fk_encuestahaspregunta_encuesta1` FOREIGN KEY (`encuesta_id`) REFERENCES `encuesta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_encuestahaspregunta_pregunta1` FOREIGN KEY (`pregunta_id`) REFERENCES `pregunta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `estudiante`"
                + " ADD CONSTRAINT `fk_estudiante_fuente1` FOREIGN KEY (`fuente_id`) REFERENCES `fuente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_estudiante_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_estudiante_programa1` FOREIGN KEY (`programa_id`) REFERENCES `programa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `indicador`"
                + " ADD CONSTRAINT `fk_indicador_caracteristica1` FOREIGN KEY (`caracteristica_id`) REFERENCES `caracteristica` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `muestra`"
                + " ADD CONSTRAINT `fk_muestra_proceso1` FOREIGN KEY (`proceso_id`) REFERENCES `proceso` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `muestraadministrativo`"
                + " ADD CONSTRAINT `fk_muestraadministrativo_administrativo1` FOREIGN KEY (`administrativo_id`) REFERENCES `administrativo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_muestraadministrativo_muestra1` FOREIGN KEY (`muestra_id`) REFERENCES `muestra` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `muestraagencia`"
                + " ADD CONSTRAINT `fk_muestraagencia_agenciagubernamental1` FOREIGN KEY (`agenciagubernamental_id`) REFERENCES `agenciagubernamental` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_muestraagencia_muestra1` FOREIGN KEY (`muestra_id`) REFERENCES `muestra` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `muestradirector`"
                + " ADD CONSTRAINT `fk_muestradirector_directorprograma1` FOREIGN KEY (`directorprograma_id`) REFERENCES `directorprograma` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_muestradirector_muestra1` FOREIGN KEY (`muestra_id`) REFERENCES `muestra` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `muestradocente`"
                + " ADD CONSTRAINT `fk_muestraprofesor_docente1` FOREIGN KEY (`docente_id`) REFERENCES `docente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_muestraprofesor_muestra1` FOREIGN KEY (`muestra_id`) REFERENCES `muestra` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `muestraegresado`"
                + " ADD CONSTRAINT `fk_muestraegresado_egresado1` FOREIGN KEY (`egresado_id`) REFERENCES `egresado` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_muestraegresado_muestra1` FOREIGN KEY (`muestra_id`) REFERENCES `muestra` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `muestraempleador`"
                + " ADD CONSTRAINT `fk_muestraempleador_empleador1` FOREIGN KEY (`empleador_id`) REFERENCES `empleador` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_muestraempleador_muestra1` FOREIGN KEY (`muestra_id`) REFERENCES `muestra` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `muestraestudiante`"
                + " ADD CONSTRAINT `fk_muestraestudiante_estudiante1` FOREIGN KEY (`estudiante_id`) REFERENCES `estudiante` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_muestraestudiante_muestra1` FOREIGN KEY (`muestra_id`) REFERENCES `muestra` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `ponderacioncaracteristica`"
                + " ADD CONSTRAINT `fk_ponderacioncaracteristica_caracteristica1` FOREIGN KEY (`caracteristica_id`) REFERENCES `caracteristica` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_ponderacioncaracteristica_proceso1` FOREIGN KEY (`proceso_id`) REFERENCES `proceso` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `ponderacionfactor`"
                + " ADD CONSTRAINT `fk_ponderacionfactor_factor1` FOREIGN KEY (`factor_id`) REFERENCES `factor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_ponderacionfactor_proceso1` FOREIGN KEY (`proceso_id`) REFERENCES `proceso` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `pregunta`"
                + " ADD CONSTRAINT `fk_pregunta_indicador1` FOREIGN KEY (`indicador_id`) REFERENCES `indicador` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `proceso`"
                + " ADD CONSTRAINT `fk_proceso_programa1` FOREIGN KEY (`programa_id`) REFERENCES `programa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `programa`"
                + " ADD CONSTRAINT `fk_programa_facultad1` FOREIGN KEY (`facultad_id`) REFERENCES `facultad` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_programa_sede1` FOREIGN KEY (`sede_id`) REFERENCES `sede` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `propuestacaracteristica`"
                + " ADD CONSTRAINT `fk_propuestacaracteristica_caracteristica1` FOREIGN KEY (`caracteristica_id`) REFERENCES `caracteristica` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_propuestacaracteristica_solicitudponderacion1` FOREIGN KEY (`solicitudponderacion_id`) REFERENCES `solicitudponderacion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `propuestafactor`"
                + " ADD CONSTRAINT `fk_propuestafactor_factor1` FOREIGN KEY (`factor_id`) REFERENCES `factor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_propuestafactor_solicitudponderacion1` FOREIGN KEY (`solicitudponderacion_id`) REFERENCES `solicitudponderacion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `representante`"
                + " ADD CONSTRAINT `fk_representante_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_representante_programa1` FOREIGN KEY (`programa_id`) REFERENCES `programa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;"
                + " ALTER TABLE `representantehasprivilegio`"
                + " ADD CONSTRAINT `fk_representantehasprivilegio_privilegio1` FOREIGN KEY (`privilegio_id`) REFERENCES `privilegio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"
                + " ADD CONSTRAINT `fk_representantehasprivilegio_representante1` FOREIGN KEY (`representante_id`) REFERENCES `representante` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;";
        
        
        
        rs2 = conSql.CargarSql2(sql2, bd);
        
        
        
        
        
        conSql.cerrarConexion();*/
        
    }
    
    public void cargarScript(String nameBd, String url) throws FileNotFoundException, IOException, SQLException {
        try {
            
            String s = new String();
            
            Statement stmt = con.createStatement();
            
            StringBuffer sb = new StringBuffer();
            FileReader fr = new FileReader(new File(url));
            // be sure to not have line starting with "--" or "/*" or any 
            // other non aplhabetical character
            BufferedReader br = new BufferedReader(fr);
            String name = "USE `" + nameBd + "`;";
            sb.append(name);
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            br.close();
            // here is our splitter ! We use ";" as a delimiter for each request
            // then we are sure to have well formed statements
            String[] inst = sb.toString().split(";");
            for (int i = 0; i < inst.length; i++) {
                // we ensure that there is no spaces before or after the request 
                // string in order to not execute empty statements
                if (!inst[i].trim().equals("")) {
                    stmt.executeUpdate(inst[i] + ";");
                }
            }
            
            stmt.close();
        } catch (java.sql.SQLException er) {
            System.out.println("No se pudo realizar la operaciÃ³n");
            System.out.println("SQLException: " + er.getMessage());
            System.out.println("SQLState: " + er.getSQLState());
            System.out.println("VendorError: " + er.getErrorCode());
        }
        
    }
    
    public void cerrarConexion() {
        
        if (this.isConected()) {
            linkDB.cerrarConexion();
        }
    }
    
    public ResultSet CargarSql(String sql, String bd) {
        
        
        ResultSet rs = null;
        Connection con = null;
        sqlConnection sqlCon = new sqlConnection();
        
        try {
            
            sqlCon.conectarMySQL(bd);
            con = (Connection) sqlCon.getConnection();
            
            Statement st = (Statement) con.createStatement();
            // System.out.println("Se ha realizado con exito la conexion a MySQL");
            //el resultSet es el encargado de traer los datos de la consulta
            rs = st.executeQuery(sql);
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (Error ex) {
            System.out.println(ex);
        }
        return rs;
    }
    
    public Result CargarSql2(String sql, String bd) {
        
        
        ResultSet rs = null;
        Result result = null;
        Connection con = null;
        sqlConnection sqlCon = new sqlConnection();
        
        try {
            
            sqlCon.conectarMySQL(bd);
            //   System.out.println("**" + bd);

            con = (Connection) sqlCon.getConnection();
            
            Statement st = (Statement) con.createStatement();
            //  System.out.println("Se ha realizado con exito la conexion a MySQL");
            //el resultSet es el encargado de traer los datos de la consulta
            //System.out.println(sql);
            rs = st.executeQuery(sql);
            result = ResultSupport.toResult(rs);
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (Error ex) {
            System.out.println(ex);
        }
        return result;
    }
    
    public void UpdateSql(String sql, String bd) {
        
        
        
        Connection con = null;
        sqlConnection sqlCon = new sqlConnection();
        
        try {
            
            
            sqlCon.conectarMySQL(bd);
            con = (Connection) sqlCon.getConnection();
            
            Statement st = (Statement) con.createStatement();
            // System.out.println("Se ha realizado con exito la conexion a MySQL");

            st.executeUpdate(sql);
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (Error ex) {
            System.out.println(ex);
        } finally {
            try {
                con.close();
                //System.out.println("sqlConnection Cerrada con Exito...");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        
    }
}
