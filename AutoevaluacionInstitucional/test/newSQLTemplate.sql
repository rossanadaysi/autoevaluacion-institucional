/*numero de personas en la muestra*/
select c1.total, c1.terminados, format((c1.terminados*100/c1.total),2) as porcentaje,(c1.total-c1.terminados) as faltantes, 100-format((c1.terminados*100/c1.total),2) as porFal 
from(
select (
(select count(*) from muestraestudiante where muestraestudiante.muestra_id=1)+
(select count(*) from muestradocente where muestradocente.muestra_id=1) +
(select count(*) from muestraadministrativo where muestraadministrativo.muestra_id=1)+
(select count(*) from muestraegresado where muestraegresado.muestra_id=1)+
(select count(*) from muestradirector where muestradirector.muestra_id=1)+
(select count(*) from muestraagencia where muestraagencia.muestra_id=1)+
(select count(*) from muestraempleador where muestraempleador.muestra_id=1)+
(select count(*) from muestracriterio where muestracriterio.muestra_id=1)) AS total 
, (select count(`persona_id`) as terminados from encabezado where 
`fuente_id` = 1 and estado="terminado") as terminados
) AS c1




select * from(
(select count(muestraestudiante.estudiante_id) as totalEstu from muestraestudiante where muestraestudiante.muestra_id=1) as totalEst,
(select count(muestraestudiante.estudiante_id) as estudiantes  from muestraestudiante 
inner join estudiante on muestraestudiante.estudiante_id =estudiante.id
inner join encabezado on encabezado.persona_id = estudiante.persona_id
where muestraestudiante.muestra_id=1 and encabezado.fuente_id=1) as est,
(select count(muestradocente.docente_id) as totalDoce from muestradocente where muestradocente.muestra_id=1) as totalDoc,
(select count(muestradocente.docente_id) as docentes from muestradocente 
inner join docente on muestradocente.docente_id =docente.id
inner join encabezado on encabezado.persona_id = docente.persona_id
where muestradocente.muestra_id=1 and encabezado.fuente_id=2) as doc,
(select count(muestraadministrativo.administrativo_id) as totalAdmi from muestraadministrativo where muestraadministrativo.muestra_id=1) as totalAdm,
(select count(muestraadministrativo.administrativo_id) as administrativos from muestraadministrativo 
inner join administrativo on muestraadministrativo.administrativo_id =administrativo.id
inner join encabezado on encabezado.persona_id = administrativo.persona_id
where muestraadministrativo.muestra_id=1 and encabezado.fuente_id=3) as adm,
(select count(muestradirector.directorprograma_id) as totalDire from muestradirector where muestradirector.muestra_id=1) as totalDir,
(select count(muestradirector.directorprograma_id) as directores from muestradirector 
inner join directorprograma on muestradirector.directorprograma_id=directorprograma.id
inner join encabezado on encabezado.persona_id = directorprograma.persona_id
where muestradirector.muestra_id=1 and encabezado.fuente_id=4) as dir,
(select count(muestraegresado.egresado_id) as totalEgre from muestraegresado where muestraegresado.muestra_id=1) as totalEgr,
(select count(muestraegresado.egresado_id) as egresados from muestraegresado 
inner join egresado on muestraegresado.egresado_id=egresado.id
inner join encabezado on encabezado.persona_id = egresado.persona_id
where muestraegresado.muestra_id=1 and encabezado.fuente_id=5) as egr,
(select count(muestraempleador.empleador_id) as totalEmpl from muestraempleador where muestraempleador.muestra_id=1) as totalEmp, 
(select count(muestraempleador.empleador_id) as empleadores  from muestraempleador 
inner join empleador on muestraempleador.empleador_id=empleador.id
inner join encabezado on encabezado.persona_id = empleador.persona_id
where muestraempleador.muestra_id=1 and encabezado.fuente_id=6) as emp,
(select count(muestraagencia.agenciagubernamental_id) as totalAgen from muestraagencia where muestraagencia.muestra_id=1) as totalAge, 
(select count(muestraagencia.agenciagubernamental_id) as agencias from muestraagencia 
inner join agenciagubernamental on muestraagencia.agenciagubernamental_id=agenciagubernamental.id
inner join encabezado on encabezado.persona_id = agenciagubernamental.persona_id
where muestraagencia.muestra_id=1 and encabezado.fuente_id=7 ) as age
)

select nombre from fuente 


select `persona_id` from encabezado where 
`fuente_id` = 1 and estado="terminado"




select fuente.nombre, count(muestraestudiante.id), count(encabezado.id), count(muestraestudiante.id) - count(encabezado.id) from estudiante inner join fuente on estudiante.fuente_id = fuente.id inner join muestraestudiante on estudiante.id = muestraestudiante.estudiante_id left join encabezado on  estudiante.persona_id = encabezado.persona_id