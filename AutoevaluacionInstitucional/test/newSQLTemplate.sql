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
