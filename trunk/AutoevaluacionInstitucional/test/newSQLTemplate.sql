SELECT factor.id, factor.nombre, ponderacionfactor.ponderacion,caracteristica.id, caracteristica.nombre, nivelimportancia, ponderacioncaracteristica.ponderacion AS pc,
format(avg(respuesta),2) AS cumplimiento,
format(ponderacioncaracteristica.ponderacion* avg(respuesta),2) AS evaluacion,
format(5 * ponderacioncaracteristica.ponderacion ,2) as ideal,
format((ponderacioncaracteristica.ponderacion * avg(respuesta) / (5 * ponderacioncaracteristica.ponderacion))*100,2) as Relacion
FROM factor
INNER JOIN caracteristica ON caracteristica.factor_id=factor.id
INNER JOIN ponderacionfactor ON ponderacionfactor.factor_id = factor.id
INNER JOIN ponderacioncaracteristica ON ponderacioncaracteristica.caracteristica_id = caracteristica.id
INNER JOIN indicador on indicador.caracteristica_id = caracteristica.id
INNER JOIN pregunta on pregunta.indicador_id = indicador.id
INNER JOIN resultadoevaluacion on resultadoevaluacion.pregunta_id=pregunta.id
where pregunta.tipo='elegir 1-5'
group by CARACTERISTICA.id