SELECT persona.id
FROM encuesta
INNER JOIN asignacionencuesta ON asignacionencuesta.ENCUESTA_ID = encuesta.ID
INNER JOIN proceso ON asignacionencuesta.PROCESO_ID = proceso.ID
INNER JOIN muestra ON asignacionencuesta.PROCESO_ID = muestra.PROCESO_ID
INNER JOIN muestradocente ON muestra.ID = muestradocente.MUESTRA_ID
INNER JOIN docente ON muestradocente.DOCENTE_ID = docente.ID
INNER JOIN persona ON docente.PERSONA_ID = persona.ID
WHERE persona.id = "1222-0"
AND proceso.`FECHACIERRE` IS NULL
AND proceso.fechainicio !='Proceso en Configuración.'
AND asignacionencuesta.fuente_id=2
AND (asignacionencuesta.PROCESO_ID, persona.id, asignacionencuesta.ENCUESTA_ID, asignacionencuesta.FUENTE_ID) NOT IN 
(select encabezado.PROCESO_ID, encabezado.PERSONA_ID, encabezado.ENCUESTA_ID, encabezado.FUENTE_ID from encabezado where encabezado.estado ='terminado')



select c1.total, c1.terminados, format((c1.terminados*100/c1.total),2) as porcentaje,(c1.total-c1.terminados) as faltantes, 100-format((c1.terminados*100/c1.total),2) as porFal 
from(
select (
(select count(*) from muestraestudiante where muestraestudiante.muestra_id=1)+
(select count(*) from muestradocente where muestradocente.muestra_id=1) +
(select count(*) from muestraadministrativo where muestraadministrativo.muestra_id=1)+
(select count(*) from muestraegresado where muestraegresado.muestra_id=1)+
(select count(*) from muestradirector where muestradirector.muestra_id=1)+
(select count(*) from muestraagencia where muestraagencia.muestra_id=1)+
(select count(*) from muestraempleador where muestraempleador.muestra_id=1)) AS total 
,(select count(`persona_id`) as terminados from encabezado where
 estado='terminado') as terminados
) AS c1




SELECT encuesta.id , encuesta.nombre
FROM encuesta
INNER JOIN asignacionencuesta ON asignacionencuesta.ENCUESTA_ID = encuesta.ID
INNER JOIN proceso ON asignacionencuesta.PROCESO_ID = proceso.ID
INNER JOIN muestra ON asignacionencuesta.PROCESO_ID = muestra.PROCESO_ID
INNER JOIN muestraestudiante ON muestra.ID = muestraestudiante.MUESTRA_ID
INNER JOIN estudiante ON muestraestudiante.ESTUDIANTE_ID = estudiante.ID
INNER JOIN persona ON estudiante.PERSONA_ID = persona.ID
WHERE persona.id = 1044925822
AND proceso.`FECHACIERRE` IS NULL
AND proceso.fechainicio !='Proceso en Configuración.'
AND asignacionencuesta.fuente_id=1 
AND (asignacionencuesta.PROCESO_ID, persona.id, asignacionencuesta.ENCUESTA_ID, asignacionencuesta.FUENTE_ID) NOT IN 
(select encabezado.PROCESO_ID, encabezado.PERSONA_ID, encabezado.ENCUESTA_ID, encabezado.FUENTE_ID from encabezado where encabezado.estado ='terminado') 
