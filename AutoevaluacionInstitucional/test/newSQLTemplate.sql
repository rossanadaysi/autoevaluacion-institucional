/*numero de personas en la muestra*/
select (
(select count(*) from muestraestudiante where muestraestudiante.muestra_id=1)+(select count(*) from muestradocente where muestradocente.muestra_id=1) +
(select count(*) from muestraadministrativo where muestraadministrativo.muestra_id=1)+
(select count(*) from muestraegresado where muestraegresado.muestra_id=1)+
(select count(*) from muestradirector where muestradirector.muestra_id=1)+
(select count(*) from muestraagencia where muestraagencia.muestra_id=1)+
(select count(*) from muestraempleador where muestraempleador.muestra_id=1)) as total;



select fuente.nombre, count(muestraestudiante.id), count(encabezado.id), count(muestraestudiante.id) - count(encabezado.id) from estudiante inner join fuente on estudiante.fuente_id = fuente.id inner join muestraestudiante on estudiante.id = muestraestudiante.estudiante_id left join encabezado on  estudiante.persona_id = encabezado.persona_id