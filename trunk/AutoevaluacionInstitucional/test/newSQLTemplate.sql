
SELECT pregunta.id, count( CASE WHEN respuesta = '0' THEN 1 ELSE null end),
count( CASE WHEN respuesta = '1' THEN 1 ELSE null end),
count( CASE WHEN respuesta = '2' THEN 1 ELSE null end),
count( CASE WHEN respuesta = '3' THEN 1 ELSE null end),
count( CASE WHEN respuesta = '4' THEN 1 ELSE null end)
 FROM resultadoevaluacion
inner join pregunta on pregunta.id=resultadoevaluacion.pregunta_id
where pregunta.tipo="elegir 1-5"
group by pregunta.id 

/*ultimo*/
SELECT indicador.id, indicador.nombre AS ino, pregunta.id AS pi, pregunta.pregunta,
format((sum( case when respuesta='1'  THEN 1 ELSE null end)+
sum( case when respuesta='2'  THEN 2 ELSE null end)+
sum( case when respuesta='3'  THEN 3 ELSE null end)+
sum( case when respuesta='4'  THEN 4 ELSE null end)+
sum( case when respuesta='5'  THEN 5 ELSE null end))/
(count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end)),2), 
caracteristica.id, pregunta.codigo, indicador.codigo,
count( CASE WHEN respuesta = '0' THEN 1 ELSE null end) as "0",
count( CASE WHEN respuesta = '1' THEN 1 ELSE null end) as "1",
count( CASE WHEN respuesta = '2' THEN 1 ELSE null end) as "2",
count( CASE WHEN respuesta = '3' THEN 1 ELSE null end) as "3",
count( CASE WHEN respuesta = '4' THEN 1 ELSE null end) as "4",
count( CASE WHEN respuesta = '5' THEN 1 ELSE null end) as "5"
FROM resultadoevaluacion
INNER JOIN pregunta ON pregunta.id=resultadoevaluacion.pregunta_id
INNER JOIN indicador ON indicador.id=pregunta.indicador_id
INNER JOIN caracteristica ON caracteristica.id = indicador.caracteristica_id
WHERE pregunta.tipo = 'elegir 1-5' 
AND indicador.id =268
GROUP BY pregunta.id



SELECT indicador.id, indicador.nombre AS ino, pregunta.id AS pi, pregunta.pregunta, format(avg(respuesta),2), caracteristica.id, pregunta.codigo, indicador.codigo"
                + " FROM Indicador"
                + " INNER JOIN caracteristica ON indicador.caracteristica_id = caracteristica.id"
                + " INNER JOIN pregunta ON pregunta.indicador_id = indicador.id"
                + " INNER JOIN resultadoevaluacion ON resultadoevaluacion.pregunta_id = pregunta.id"
                + " WHERE pregunta.tipo = 'elegir 1-5' and resultadoevaluacion.respuesta != 0"
                + " AND indicador.id =" + idI
                + " GROUP BY pregunta.id





SELECT encabezado.id
 FROM resultadoevaluacion
                 INNER JOIN pregunta ON pregunta.id = resultadoevaluacion.pregunta_id
                 INNER JOIN indicador on indicador.id = pregunta.indicador_id
                 INNER JOIN encabezado on encabezado.id=resultadoevaluacion.encabezado_id
                 where pregunta.tipo="elegir 1-5" AND respuesta != '0' and 
                         respuesta != '1' and 
                         respuesta != '2' and 
                         respuesta != '3' and 
                         respuesta != '4' and 
                         respuesta != '5' and encabezado.estado="terminado"


SELECT pregunta.id, pregunta.pregunta, pregunta.tipo, 
count( CASE WHEN respuesta = 'Si' THEN 1 ELSE null end) AS 'Si', 
count( CASE WHEN respuesta = 'No' THEN 1 ELSE null end) AS 'No', 
count( CASE WHEN respuesta = '0' THEN 1 ELSE null end) AS '0', 
count( CASE WHEN respuesta = '1' THEN 1 ELSE null end) AS '1', 
count( CASE WHEN respuesta = '2' THEN 1 ELSE null end) AS '2', 
count( CASE WHEN respuesta = '3' THEN 1 ELSE null end) AS '3', 
count( CASE WHEN respuesta = '4' THEN 1 ELSE null end) AS '4', 
count( CASE WHEN respuesta = '5' THEN 1 ELSE null end) AS '5'
FROM `encuesta`
INNER JOIN encabezado ON encuesta.id = encabezado.encuesta_id
INNER JOIN resultadoevaluacion ON encabezado.id = resultadoevaluacion.encabezado_id
INNER JOIN pregunta ON resultadoevaluacion.pregunta_id = pregunta.id
WHERE encuesta.id =1
GROUP BY encabezado.id









select * from (
                (SELECT COUNT(CASE WHEN encabezado.fuente_id ="1" THEN 1 END ) as totalEstu
                FROM muestraestudiante 
                INNER JOIN estudiante ON muestraestudiante.estudiante_id = estudiante.id 
                INNER JOIN programa ON estudiante.programa_id = programa.id 
                INNER JOIN facultad ON programa.facultad_id = facultad.id 
                LEFT JOIN encabezado ON encabezado.persona_id = estudiante.persona_id 
                WHERE muestraestudiante.muestra_id = 2 
                GROUP BY facultad.nombre, programa.nombre 
                WITH ROLLUP) as ja,
                (SELECT COUNT(CASE WHEN encabezado.fuente_id =1  THEN 1 END ) as totalEstu2
                FROM estudiante 
                INNER JOIN programa ON estudiante.programa_id = programa.id 
                INNER JOIN facultad ON programa.facultad_id = facultad.id 
                LEFT JOIN encabezado ON encabezado.persona_id = estudiante.persona_id 
                GROUP BY facultad.nombre, programa.nombre 
                WITH ROLLUP) as ju
                )


SELECT facultad.nombre, programa.id, programa.nombre, Count( * ) , COUNT(CASE WHEN encabezado.fuente_id =1 AND encabezado.estado = 'terminado' THEN 1 END ), facultad.id 
FROM muestraadministrativo
                INNER JOIN administrativo ON muestraadministrativo.administrativo_id = administrativo.id 
                
                LEFT JOIN encabezado ON encabezado.persona_id = administrativo.persona_id 
                WHERE muestraadministrativo.muestra_id = "2"
                GROUP BY facultad.nombre 
                WITH ROLLUP

Select descripcioncriterio.id, descripcioncriterio.nombre 
from descripcioncriterio 
where criterio_id = 1


select criterio.nombre, descripcioncriterio.nombre, Count( * ), COUNT(CASE WHEN encabezado.fuente_id =3 AND encabezado.estado = 'terminado' THEN 1 END ) 
from muestraadministrativo 
inner join administrativo on muestraadministrativo.administrativo_id =administrativo.id
inner join muestracriterio on administrativo.persona_id=muestracriterio.persona_id
inner join descripcioncriterio on muestracriterio.descripcioncriterio_id=descripcioncriterio.id
inner join criterio  on descripcioncriterio.criterio_id=criterio.id
left join encabezado on encabezado.persona_id = administrativo.persona_id
where muestraadministrativo.muestra_id="2" 
group by descripcioncriterio.nombre
