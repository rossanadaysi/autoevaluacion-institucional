INSERT INTO factor (id, nombre) SELECT id, nombre FROM autoevaluacion.factor;
INSERT INTO caracteristica (id, nombre, factor_id)  SELECT id, nombre, factor_id FROM autoevaluacion.caracteristica; 
INSERT INTO indicador (id, nombre, codigo, caracteristica_id)  SELECT id, nombre, codigo, caracteristica_id FROM autoevaluacion.indicador; 
INSERT INTO sede (id, nombre, descripcion, direccion)  SELECT id, nombre, descripcion, direccion FROM autoevaluacion.sede;
INSERT INTO facultad (id, nombre, descripcion)  SELECT id, nombre, descripcion FROM autoevaluacion.facultad;
INSERT INTO programa (id, nombre, descripcion, facultad_id, sede_id) SELECT id, nombre, descripcion, facultad_id, sede_id FROM autoevaluacion.programa; 
INSERT INTO proceso (id, fechainicio, fechacierre, descripcion, programa_id) SELECT id, fechainicio, fechacierre, descripcion, programa_id FROM autoevaluacion.proceso; 
INSERT INTO encuesta (id, nombre, descripcion, instrucciones, mensaje, fecha) SELECT id, nombre, descripcion, instrucciones, mensaje, fecha FROM autoevaluacion.encuesta;
INSERT INTO fuente (id, nombre, descripcion) SELECT id, nombre, descripcion FROM autoevaluacion.fuente;
INSERT INTO persona (`id` ,`nombre` ,`apellido` ,`password` ,`mail`) SELECT `id` ,`nombre` ,`apellido` ,`password` ,`mail` FROM autoevaluacion.persona;
INSERT INTO estudiante (`id`, `semestre`, `periodo`, `anio`, `persona_id`, `fuente_id`, `programa_id`) SELECT `id`, `semestre`, `periodo`, `anio`, `persona_id`, `fuente_id`, `programa_id` FROM autoevaluacion.estudiante;
INSERT INTO docente (`id`, `tipo`, `persona_id`, `fuente_id`, `programa_id`) SELECT `id`, `tipo`, `persona_id`, `fuente_id`, `programa_id` FROM autoevaluacion.docente;
INSERT INTO pregunta (`id`, `pregunta`, `tipo`, `codigo`, `indicador_id`) SELECT `id`, `pregunta`, `tipo`, `codigo`, `indicador_id` FROM autoevaluacion.pregunta;
INSERT INTO encuestahaspregunta (`encuesta_id`, `pregunta_id`) SELECT `encuesta_id`, `pregunta_id` FROM autoevaluacion.encuestahaspregunta;
INSERT INTO representante (`id`, `rol`, `persona_id`, `programa_id`) SELECT `id`, `rol`, `persona_id`, `programa_id` FROM autoevaluacion.representante;
INSERT INTO instrumento (`id`, `nombre`, `descripcion`) SELECT `id`, `nombre`, `descripcion` FROM autoevaluacion.instrumento;
INSERT INTO instrumentohasindicador (`instrumento_id`, `indicador_id`) SELECT `instrumento_id`, `indicador_id` FROM autoevaluacion.instrumentohasindicador;

