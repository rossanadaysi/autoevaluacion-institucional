INSERT INTO factor (id, nombre, descripcion) SELECT id, nombre, descripcion FROM autoevaluacion.factor;
INSERT INTO caracteristica (id, nombre, descripcion, factor_id)  SELECT id, nombre, descripcion, factor_id FROM autoevaluacion.caracteristica; 
INSERT INTO sede (id, nombre, descripcion, direccion)  SELECT id, nombre, descripcion, direccion FROM autoevaluacion.sede;
INSERT INTO facultad (id, nombre, descripcion)  SELECT id, nombre, descripcion FROM autoevaluacion.facultad;
INSERT INTO programa (id, nombre, descripcion, facultad_id, sede_id) SELECT id, nombre, descripcion, facultad_id, sede_id FROM autoevaluacion.programa; 
INSERT INTO proceso (id, fechainicio, fechacierre, descripcion, programa_id) SELECT id, fechainicio, fechacierre, descripcion, programa_id FROM autoevaluacion.proceso; 
INSERT INTO encuesta (id, nombre, descripcion, instrucciones, mensaje, fecha) SELECT id, nombre, descripcion, instrucciones, mensaje, fecha FROM autoevaluacion.encuesta;
INSERT INTO fuente (id, nombre, descripcion) SELECT id, nombre, descripcion FROM autoevaluacion.fuente;