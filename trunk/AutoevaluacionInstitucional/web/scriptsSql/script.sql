SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
CREATE TABLE IF NOT EXISTS `administrativo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `persona_id` varchar(25) NOT NULL,
  `fuente_id` int(11) NOT NULL,
  `programa_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_administrativo_fuente1` (`fuente_id`),
  KEY `fk_administrativo_programa1` (`programa_id`),
  KEY `fk_administrativo_persona1` (`persona_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `agenciagubernamental` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(500) DEFAULT NULL,
  `persona_id` varchar(25) NOT NULL,
  `fuente_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_agenciagubernamental_fuente1` (`fuente_id`),
  KEY `fk_agenciagubernamental_persona1` (`persona_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `asignacionencuesta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proceso_id` int(11) NOT NULL,
  `fuente_id` int(11) NOT NULL,
  `encuesta_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_asignacionencuesta_proceso1` (`proceso_id`),
  KEY `fk_asignacionencuesta_fuente1` (`fuente_id`),
  KEY `fk_asignacionencuesta_encuesta1` (`encuesta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `caracteristica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(500) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `factor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_caracteristica_factor1` (`factor_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=35 ;
CREATE TABLE IF NOT EXISTS `directorprograma` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `persona_id` varchar(25) NOT NULL,
  `fuente_id` int(11) NOT NULL,
  `programa_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_directorprograma_fuente1` (`fuente_id`),
  KEY `fk_directorprograma_programa1` (`programa_id`),
  KEY `fk_directorprograma_persona1` (`persona_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `docente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) NOT NULL,
  `persona_id` varchar(25) NOT NULL,
  `fuente_id` int(11) NOT NULL,
  `programa_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_docente_fuente1` (`fuente_id`),
  KEY `fk_docente_programa1` (`programa_id`),
  KEY `fk_docente_persona1` (`persona_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=33158624 ;
CREATE TABLE IF NOT EXISTS `egresado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `persona_id` varchar(25) NOT NULL,
  `fuente_id` int(11) NOT NULL,
  `programa_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_egresado_fuente1` (`fuente_id`),
  KEY `fk_egresado_programa1` (`programa_id`),
  KEY `fk_egresado_persona1` (`persona_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `empleador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(500) DEFAULT NULL,
  `persona_id` varchar(25) NOT NULL,
  `fuente_id` int(11) NOT NULL,
  `sectorempresarial_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_empleador_fuente1` (`fuente_id`),
  KEY `fk_empleador_sectorempresarial1` (`sectorempresarial_id`),
  KEY `fk_empleador_persona1` (`persona_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `encabezado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL,
  `persona_id` varchar(25) NOT NULL,
  `proceso_id` int(11) NOT NULL,
  `encuesta_id` int(11) NOT NULL,
  `fuente_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_encabezado_persona1` (`persona_id`),
  KEY `fk_encabezado_proceso1` (`proceso_id`),
  KEY `fk_encabezado_encuesta1` (`encuesta_id`),
  KEY `fk_encabezado_fuente1` (`fuente_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `encuesta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `instrucciones` varchar(1000) DEFAULT NULL,
  `mensaje` varchar(500) DEFAULT NULL,
  `fecha` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;
CREATE TABLE IF NOT EXISTS `encuestahaspregunta` (
  `encuesta_id` int(11) NOT NULL,
  `pregunta_id` int(11) NOT NULL,
  PRIMARY KEY (`encuesta_id`,`pregunta_id`),
  KEY `fk_encuestahaspregunta_encuesta1` (`encuesta_id`),
  KEY `fk_encuestahaspregunta_pregunta1` (`pregunta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE IF NOT EXISTS `estudiante` (
  `id` varchar(25) NOT NULL,
  `semestre` varchar(45) NOT NULL,
  `periodo` varchar(45) NOT NULL,
  `anio` varchar(45) NOT NULL,
  `persona_id` varchar(25) NOT NULL,
  `fuente_id` int(11) NOT NULL,
  `programa_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_estudiante_fuente1` (`fuente_id`),
  KEY `fk_estudiante_programa1` (`programa_id`),
  KEY `fk_estudiante_persona1` (`persona_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE IF NOT EXISTS `factor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;
CREATE TABLE IF NOT EXISTS `facultad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=100 ;
CREATE TABLE IF NOT EXISTS `fuente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;
CREATE TABLE IF NOT EXISTS `indicador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `caracteristica_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_indicador_caracteristica1` (`caracteristica_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=76 ;
CREATE TABLE IF NOT EXISTS `instrumento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `instrumentohasindicador` (
  `instrumento_id` int(11) NOT NULL,
  `indicador_id` int(11) NOT NULL,
  PRIMARY KEY (`instrumento_id`,`indicador_id`),
  KEY `fk_instrumento_has_indicador_indicador1` (`indicador_id`),
  KEY `fk_instrumento_has_indicador_instrumento1` (`instrumento_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE IF NOT EXISTS `muestra` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `formula` varchar(500) DEFAULT NULL,
  `proceso_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_muestra_proceso1` (`proceso_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `muestraadministrativo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `muestra_id` int(11) NOT NULL,
  `administrativo_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_muestraadministrativo_muestra1` (`muestra_id`),
  KEY `fk_muestraadministrativo_administrativo1` (`administrativo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `muestraagencia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `muestra_id` int(11) NOT NULL,
  `agenciagubernamental_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_muestraagencia_agenciagubernamental1` (`agenciagubernamental_id`),
  KEY `fk_muestraagencia_muestra1` (`muestra_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `muestradirector` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `muestra_id` int(11) NOT NULL,
  `directorprograma_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_muestradirector_muestra1` (`muestra_id`),
  KEY `fk_muestradirector_directorprograma1` (`directorprograma_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `muestradocente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `muestra_id` int(11) NOT NULL,
  `docente_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_muestraprofesor_muestra1` (`muestra_id`),
  KEY `fk_muestraprofesor_docente1` (`docente_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `muestraegresado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `muestra_id` int(11) NOT NULL,
  `egresado_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_muestraegresado_muestra1` (`muestra_id`),
  KEY `fk_muestraegresado_egresado1` (`egresado_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `muestraempleador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `muestra_id` int(11) NOT NULL,
  `empleador_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_muestraempleador_muestra1` (`muestra_id`),
  KEY `fk_muestraempleador_empleador1` (`empleador_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `muestraestudiante` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `muestra_id` int(11) NOT NULL,
  `estudiante_id` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_muestraestudiante_muestra1` (`muestra_id`),
  KEY `fk_muestraestudiante_estudiante1` (`estudiante_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `numericadocumental` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `evaluacion` int(11) NOT NULL,
  `nombre` varchar(500) NOT NULL,
  `accion` varchar(500) NOT NULL,
  `responsable` varchar(500) NOT NULL,
  `indicador_id` int(11) NOT NULL,
  `proceso_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_numericadocumental_indicador1` (`indicador_id`),
  KEY `fk_numericadocumental_proceso1` (`proceso_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `persona` (
  `id` varchar(25) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `password` varchar(45) NOT NULL,
  `mail` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE IF NOT EXISTS `ponderacioncaracteristica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nivelimportancia` float NOT NULL,
  `ponderacion` double NOT NULL,
  `justificacion` varchar(500) NOT NULL,
  `proceso_id` int(11) NOT NULL,
  `caracteristica_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ponderacioncaracteristica_proceso1` (`proceso_id`),
  KEY `fk_ponderacioncaracteristica_caracteristica1` (`caracteristica_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `ponderacionfactor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ponderacion` double NOT NULL,
  `justificacion` varchar(500) NOT NULL,
  `proceso_id` int(11) NOT NULL,
  `factor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ponderacionfactor_proceso1` (`proceso_id`),
  KEY `fk_ponderacionfactor_factor1` (`factor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `pregunta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pregunta` varchar(500) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `indicador_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pregunta_indicador1` (`indicador_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=90 ;
CREATE TABLE IF NOT EXISTS `privilegio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `proceso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fechainicio` varchar(30) NOT NULL,
  `fechacierre` varchar(30) DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `programa_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_proceso_programa1` (`programa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `programa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `facultad_id` int(11) DEFAULT NULL,
  `sede_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_programa_facultad1` (`facultad_id`),
  KEY `fk_programa_sede1` (`sede_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1003 ;
CREATE TABLE IF NOT EXISTS `representante` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rol` varchar(45) NOT NULL,
  `persona_id` varchar(25) NOT NULL,
  `programa_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_representante_programa1` (`programa_id`),
  KEY `fk_representante_persona1` (`persona_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;
CREATE TABLE IF NOT EXISTS `representantehasprivilegio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `representante_id` int(11) NOT NULL,
  `privilegio_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_representantehasprivilegio_representante1` (`representante_id`),
  KEY `fk_representantehasprivilegio_privilegio1` (`privilegio_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `resultadoevaluacion` (
  `idResultadoEvaluacion` int(11) NOT NULL AUTO_INCREMENT,
  `respuesta` varchar(45) DEFAULT NULL,
  `encabezado_id` int(11) NOT NULL,
  `pregunta_id` int(11) NOT NULL,
  PRIMARY KEY (`idResultadoEvaluacion`),
  KEY `fk_ResultadoEvaluacion_encabezado1` (`encabezado_id`),
  KEY `fk_ResultadoEvaluacion_pregunta1` (`pregunta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `sectorempresarial` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idSectores Empresariales_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
CREATE TABLE IF NOT EXISTS `sede` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
ALTER TABLE `administrativo`
  ADD CONSTRAINT `fk_administrativo_fuente1` FOREIGN KEY (`fuente_id`) REFERENCES `fuente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_administrativo_programa1` FOREIGN KEY (`programa_id`) REFERENCES `programa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_administrativo_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `agenciagubernamental`
  ADD CONSTRAINT `fk_agenciagubernamental_fuente1` FOREIGN KEY (`fuente_id`) REFERENCES `fuente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_agenciagubernamental_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `asignacionencuesta`
  ADD CONSTRAINT `fk_asignacionencuesta_proceso1` FOREIGN KEY (`proceso_id`) REFERENCES `proceso` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_asignacionencuesta_fuente1` FOREIGN KEY (`fuente_id`) REFERENCES `fuente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_asignacionencuesta_encuesta1` FOREIGN KEY (`encuesta_id`) REFERENCES `encuesta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `caracteristica`
  ADD CONSTRAINT `fk_caracteristica_factor1` FOREIGN KEY (`factor_id`) REFERENCES `factor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `directorprograma`
  ADD CONSTRAINT `fk_directorprograma_fuente1` FOREIGN KEY (`fuente_id`) REFERENCES `fuente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_directorprograma_programa1` FOREIGN KEY (`programa_id`) REFERENCES `programa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_directorprograma_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `docente`
  ADD CONSTRAINT `fk_docente_fuente1` FOREIGN KEY (`fuente_id`) REFERENCES `fuente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_docente_programa1` FOREIGN KEY (`programa_id`) REFERENCES `programa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_docente_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `egresado`
  ADD CONSTRAINT `fk_egresado_fuente1` FOREIGN KEY (`fuente_id`) REFERENCES `fuente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_egresado_programa1` FOREIGN KEY (`programa_id`) REFERENCES `programa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_egresado_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `empleador`
  ADD CONSTRAINT `fk_empleador_fuente1` FOREIGN KEY (`fuente_id`) REFERENCES `fuente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_empleador_sectorempresarial1` FOREIGN KEY (`sectorempresarial_id`) REFERENCES `sectorempresarial` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_empleador_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `encabezado`
  ADD CONSTRAINT `fk_encabezado_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_encabezado_proceso1` FOREIGN KEY (`proceso_id`) REFERENCES `proceso` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_encabezado_encuesta1` FOREIGN KEY (`encuesta_id`) REFERENCES `encuesta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_encabezado_fuente1` FOREIGN KEY (`fuente_id`) REFERENCES `fuente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `encuestahaspregunta`
  ADD CONSTRAINT `fk_encuestahaspregunta_encuesta1` FOREIGN KEY (`encuesta_id`) REFERENCES `encuesta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_encuestahaspregunta_pregunta1` FOREIGN KEY (`pregunta_id`) REFERENCES `pregunta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `estudiante`
  ADD CONSTRAINT `fk_estudiante_fuente1` FOREIGN KEY (`fuente_id`) REFERENCES `fuente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_estudiante_programa1` FOREIGN KEY (`programa_id`) REFERENCES `programa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_estudiante_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `indicador`
  ADD CONSTRAINT `fk_indicador_caracteristica1` FOREIGN KEY (`caracteristica_id`) REFERENCES `caracteristica` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `instrumentohasindicador`
  ADD CONSTRAINT `fk_instrumento_has_indicador_instrumento1` FOREIGN KEY (`instrumento_id`) REFERENCES `instrumento` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_instrumento_has_indicador_indicador1` FOREIGN KEY (`indicador_id`) REFERENCES `indicador` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `muestra`
  ADD CONSTRAINT `fk_muestra_proceso1` FOREIGN KEY (`proceso_id`) REFERENCES `proceso` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `muestraadministrativo`
  ADD CONSTRAINT `fk_muestraadministrativo_muestra1` FOREIGN KEY (`muestra_id`) REFERENCES `muestra` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_muestraadministrativo_administrativo1` FOREIGN KEY (`administrativo_id`) REFERENCES `administrativo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `muestraagencia`
  ADD CONSTRAINT `fk_muestraagencia_agenciagubernamental1` FOREIGN KEY (`agenciagubernamental_id`) REFERENCES `agenciagubernamental` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_muestraagencia_muestra1` FOREIGN KEY (`muestra_id`) REFERENCES `muestra` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `muestradirector`
  ADD CONSTRAINT `fk_muestradirector_muestra1` FOREIGN KEY (`muestra_id`) REFERENCES `muestra` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_muestradirector_directorprograma1` FOREIGN KEY (`directorprograma_id`) REFERENCES `directorprograma` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `muestradocente`
  ADD CONSTRAINT `fk_muestraprofesor_muestra1` FOREIGN KEY (`muestra_id`) REFERENCES `muestra` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_muestraprofesor_docente1` FOREIGN KEY (`docente_id`) REFERENCES `docente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `muestraegresado`
  ADD CONSTRAINT `fk_muestraegresado_muestra1` FOREIGN KEY (`muestra_id`) REFERENCES `muestra` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_muestraegresado_egresado1` FOREIGN KEY (`egresado_id`) REFERENCES `egresado` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `muestraempleador`
  ADD CONSTRAINT `fk_muestraempleador_muestra1` FOREIGN KEY (`muestra_id`) REFERENCES `muestra` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_muestraempleador_empleador1` FOREIGN KEY (`empleador_id`) REFERENCES `empleador` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `muestraestudiante`
  ADD CONSTRAINT `fk_muestraestudiante_muestra1` FOREIGN KEY (`muestra_id`) REFERENCES `muestra` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_muestraestudiante_estudiante1` FOREIGN KEY (`estudiante_id`) REFERENCES `estudiante` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `numericadocumental`
  ADD CONSTRAINT `fk_numericadocumental_indicador1` FOREIGN KEY (`indicador_id`) REFERENCES `indicador` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_numericadocumental_proceso1` FOREIGN KEY (`proceso_id`) REFERENCES `proceso` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `ponderacioncaracteristica`
  ADD CONSTRAINT `fk_ponderacioncaracteristica_proceso1` FOREIGN KEY (`proceso_id`) REFERENCES `proceso` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_ponderacioncaracteristica_caracteristica1` FOREIGN KEY (`caracteristica_id`) REFERENCES `caracteristica` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `ponderacionfactor`
  ADD CONSTRAINT `fk_ponderacionfactor_proceso1` FOREIGN KEY (`proceso_id`) REFERENCES `proceso` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_ponderacionfactor_factor1` FOREIGN KEY (`factor_id`) REFERENCES `factor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `pregunta`
  ADD CONSTRAINT `fk_pregunta_indicador1` FOREIGN KEY (`indicador_id`) REFERENCES `indicador` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `proceso`
  ADD CONSTRAINT `fk_proceso_programa1` FOREIGN KEY (`programa_id`) REFERENCES `programa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `programa`
  ADD CONSTRAINT `fk_programa_facultad1` FOREIGN KEY (`facultad_id`) REFERENCES `facultad` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_programa_sede1` FOREIGN KEY (`sede_id`) REFERENCES `sede` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `representante`
  ADD CONSTRAINT `fk_representante_programa1` FOREIGN KEY (`programa_id`) REFERENCES `programa` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_representante_persona1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `representantehasprivilegio`
  ADD CONSTRAINT `fk_representantehasprivilegio_representante1` FOREIGN KEY (`representante_id`) REFERENCES `representante` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_representantehasprivilegio_privilegio1` FOREIGN KEY (`privilegio_id`) REFERENCES `privilegio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `resultadoevaluacion`
  ADD CONSTRAINT `fk_ResultadoEvaluacion_encabezado1` FOREIGN KEY (`encabezado_id`) REFERENCES `encabezado` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_ResultadoEvaluacion_pregunta1` FOREIGN KEY (`pregunta_id`) REFERENCES `pregunta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
