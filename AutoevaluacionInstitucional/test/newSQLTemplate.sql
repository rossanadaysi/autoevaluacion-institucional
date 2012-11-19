select c2.fid2, c2.fno2, c2.fpo2, format(sum(c2.cumpC*c2.pondC)/sum(c2.pondC),1), format((sum(c2.cumpC*c2.pondC)/sum(c2.pondC))*c2.fpo2,1), format((sum(c2.cumpC*c2.pondC)/sum(c2.pondC))*5,1), format((sum(c2.cumpC*c2.pondC)/sum(c2.pondC))*20,1) from (select c1.fid as fid2, c1.fno as fno2, c1.fpo as fpo2, c1.cara, c1.nombre, c1.nivel, c1.ponderacionCara as pondC, avg(c1.cumplimiento) as cumpC, avg(cumplimiento)*c1.ponderacionCara, 5*c1.ponderacionCara, avg(cumplimiento)*20    from(

SELECT factor.id AS fid,  factor.nombre AS fno, ponderacionfactor.ponderacion AS fpo, caracteristica.id as cara, 
                   caracteristica.nombre as nombre, ponderacioncaracteristica.nivelimportancia as nivel, ponderacioncaracteristica.ponderacion as ponderacionCara, 
                   case 
           when ( (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+
                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+
                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+
                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+
                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/
                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end))) is null 
           then  avg (numericadocumental.evaluacion ) 
           when avg (numericadocumental.evaluacion )is null 
           then (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+
                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+
                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+
                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+
                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/
                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end))
           else (( (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+
                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+
                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+
                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+
                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/
                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end)))+avg (numericadocumental.evaluacion))/2 
       end  as cumplimiento
                   FROM factor
                   INNER JOIN caracteristica ON caracteristica.factor_id = factor.id
                   INNER JOIN ponderacionfactor ON ponderacionfactor.factor_id = factor.id
                   INNER JOIN ponderacioncaracteristica ON ponderacioncaracteristica.caracteristica_id = caracteristica.id
                   INNER JOIN indicador ON indicador.caracteristica_id = caracteristica.id
                   LEFT JOIN numericadocumental ON numericadocumental.indicador_id = indicador.id
                   LEFT JOIN pregunta ON pregunta.indicador_id = indicador.id
                   LEFT JOIN resultadoevaluacion ON resultadoevaluacion.pregunta_id = pregunta.id
                   group by indicador.id

) as c1

group by c1.cara) as c2
group by c2.fid2








Select * from numericadocumental
where numericadocumental.proceso_id = '1' 
and numericadocumental.instrumento_id = '3'



select indicador.id, indicador.nombre, numericadocumental.documento, numericadocumental.responsable, numericadocumental.medio, numericadocumental.lugar, numericadocumental.evaluacion, numericadocumental.accion 
from numericadocumental 
inner join indicador on numericadocumental.indicador_id = indicador.id 
inner join instrumentohasindicador on indicador.id = instrumentohasindicador.indicador_id 
where numericadocumental.instrumento_id = '2' and instrumentohasindicador.instrumento_id = '2' and numericadocumental.proceso_id = 1
group by indicador.id
order by count(instrumentohasindicador.instrumento_id) desc
/*
componiendo error
DELETE FROM `autoevaluacion`.`empleador` WHERE `empleador`.`id` = 296;
DELETE FROM `autoevaluacion`.`empleador` WHERE `empleador`.`id` = 297;
DELETE FROM `autoevaluacion`.`empleador` WHERE `empleador`.`id` = 298;
DELETE FROM `autoevaluacion`.`empleador` WHERE `empleador`.`id` = 299;
DELETE FROM `autoevaluacion`.`empleador` WHERE `empleador`.`id` = 300;

DELETE FROM `autoevaluacion`.`persona` WHERE `persona`.`id` = '1615-10';
DELETE FROM `autoevaluacion`.`persona` WHERE `persona`.`id` = '1615-11';
DELETE FROM `autoevaluacion`.`persona` WHERE `persona`.`id` = '1615-12';
DELETE FROM `autoevaluacion`.`persona` WHERE `persona`.`id` = '1615-13';
DELETE FROM `autoevaluacion`.`persona` WHERE `persona`.`id` = '1615-14';


DELETE FROM `institucional1`.`muestraempleador` WHERE `muestraempleador`.`id` = 216;
DELETE FROM `institucional1`.`muestraempleador` WHERE `muestraempleador`.`id` = 217;
DELETE FROM `institucional1`.`muestraempleador` WHERE `muestraempleador`.`id` = 218;
DELETE FROM `institucional1`.`muestraempleador` WHERE `muestraempleador`.`id` = 219;
DELETE FROM `institucional1`.`muestraempleador` WHERE `muestraempleador`.`id` = 220;

DELETE FROM `institucional1`.`empleador` WHERE `empleador`.`id` = 296;
DELETE FROM `institucional1`.`empleador` WHERE `empleador`.`id` = 297;
DELETE FROM `institucional1`.`empleador` WHERE `empleador`.`id` = 298;
DELETE FROM `institucional1`.`empleador` WHERE `empleador`.`id` = 299;
DELETE FROM `institucional1`.`empleador` WHERE `empleador`.`id` = 300;

UPDATE `institucional1`.`empleador` SET `persona_id` = '1615-0' WHERE `empleador`.`id` =301;
UPDATE `institucional1`.`empleador` SET `persona_id` = '1615-1' WHERE `empleador`.`id` =302;
UPDATE `institucional1`.`empleador` SET `persona_id` = '1615-2' WHERE `empleador`.`id` =303;
UPDATE `institucional1`.`empleador` SET `persona_id` = '1615-3' WHERE `empleador`.`id` =304;
UPDATE `institucional1`.`empleador` SET `persona_id` = '1615-4' WHERE `empleador`.`id` =305;
UPDATE `institucional1`.`empleador` SET `persona_id` = '1615-5' WHERE `empleador`.`id` =306;
UPDATE `institucional1`.`empleador` SET `persona_id` = '1615-6' WHERE `empleador`.`id` =307;
UPDATE `institucional1`.`empleador` SET `persona_id` = '1615-7' WHERE `empleador`.`id` =308;
UPDATE `institucional1`.`empleador` SET `persona_id` = '1615-8' WHERE `empleador`.`id` =309;
UPDATE `institucional1`.`empleador` SET `persona_id` = '1615-9' WHERE `empleador`.`id` =310;

DELETE FROM `institucional1`.`muestracriterio` WHERE `muestracriterio`.`id` = 753;
DELETE FROM `institucional1`.`muestracriterio` WHERE `muestracriterio`.`id` = 754;
DELETE FROM `institucional1`.`muestracriterio` WHERE `muestracriterio`.`id` = 755;
DELETE FROM `institucional1`.`muestracriterio` WHERE `muestracriterio`.`id` = 756;
DELETE FROM `institucional1`.`muestracriterio` WHERE `muestracriterio`.`id` = 757;

DELETE FROM `institucional1`.`persona` WHERE `persona`.`id` = '1615-10';
DELETE FROM `institucional1`.`persona` WHERE `persona`.`id` = '1615-11';
DELETE FROM `institucional1`.`persona` WHERE `persona`.`id` = '1615-12';
DELETE FROM `institucional1`.`persona` WHERE `persona`.`id` = '1615-13';
DELETE FROM `institucional1`.`persona` WHERE `persona`.`id` = '1615-14';

DELETE FROM `institucional1`.`muestraempleador` WHERE `muestraempleador`.`id` = 211;
DELETE FROM `institucional1`.`muestraempleador` WHERE `muestraempleador`.`id` = 212;
DELETE FROM `institucional1`.`muestraempleador` WHERE `muestraempleador`.`id` = 213;
DELETE FROM `institucional1`.`muestraempleador` WHERE `muestraempleador`.`id` = 214;
DELETE FROM `institucional1`.`muestraempleador` WHERE `muestraempleador`.`id` = 215;

*/




/*
Eliminar indicador 1.1.2 = 2
DELETE FROM `autoevaluacion`.`instrumentohasindicador` WHERE `instrumentohasindicador`.`instrumento_id` = 3 AND `instrumentohasindicador`.`indicador_id` = 2
DELETE FROM `autoevaluacion`.`indicador` WHERE `indicador`.`id` = 2
DELETE FROM `institucional1`.`numericadocumental` WHERE `numericadocumental`.`id` = 280
DELETE FROM `institucional1`.`instrumentohasindicador` WHERE `instrumentohasindicador`.`instrumento_id` = 3 AND `instrumentohasindicador`.`indicador_id` = 2
DELETE FROM `institucional1`.`indicador` WHERE `indicador`.`id` = 2


Eliminar indicador 10.32.7 = 410
DELETE FROM `autoevaluacion`.`instrumentohasindicador` WHERE `instrumentohasindicador`.`instrumento_id` = 2 AND `instrumentohasindicador`.`indicador_id` = 410
DELETE FROM `autoevaluacion`.`indicador` WHERE `indicador`.`id` = 410
DELETE FROM `institucional1`.`numericadocumental` WHERE `numericadocumental`.`id` = 269
DELETE FROM `institucional1`.`instrumentohasindicador` WHERE `instrumentohasindicador`.`instrumento_id` = 2 AND `instrumentohasindicador`.`indicador_id` = 410
DELETE FROM `institucional1`.`indicador` WHERE `indicador`.`id` = 410


Eliminar indicador 10.32.8 = 411
DELETE FROM `autoevaluacion`.`instrumentohasindicador` WHERE `instrumentohasindicador`.`instrumento_id` = 2 AND `instrumentohasindicador`.`indicador_id` = 411
DELETE FROM `autoevaluacion`.`indicador` WHERE `indicador`.`id` = 411
DELETE FROM `institucional1`.`numericadocumental` WHERE `numericadocumental`.`id` = 270
DELETE FROM `institucional1`.`instrumentohasindicador` WHERE `instrumentohasindicador`.`instrumento_id` = 2 AND `instrumentohasindicador`.`indicador_id` = 411
DELETE FROM `institucional1`.`indicador` WHERE `indicador`.`id` = 411


Eliminar indicador 10.33.2 = 415
DELETE FROM `autoevaluacion`.`instrumentohasindicador` WHERE `instrumentohasindicador`.`instrumento_id` = 2 AND `instrumentohasindicador`.`indicador_id` = 415
DELETE FROM `autoevaluacion`.`indicador` WHERE `indicador`.`id` = 415
DELETE FROM `institucional1`.`numericadocumental` WHERE `numericadocumental`.`id` = 273
DELETE FROM `institucional1`.`instrumentohasindicador` WHERE `instrumentohasindicador`.`instrumento_id` = 2 AND `instrumentohasindicador`.`indicador_id` = 415
DELETE FROM `institucional1`.`indicador` WHERE `indicador`.`id` = 415

Eliminar indicador 9.30.8 = 384
DELETE FROM `autoevaluacion`.`instrumentohasindicador` WHERE `instrumentohasindicador`.`instrumento_id` = 2 AND `instrumentohasindicador`.`indicador_id` = 384
DELETE FROM `autoevaluacion`.`indicador` WHERE `indicador`.`id` = 384
DELETE FROM `institucional1`.`numericadocumental` WHERE `numericadocumental`.`id` = 245
DELETE FROM `institucional1`.`instrumentohasindicador` WHERE `instrumentohasindicador`.`instrumento_id` = 2 AND `instrumentohasindicador`.`indicador_id` = 384
DELETE FROM `institucional1`.`indicador` WHERE `indicador`.`id` = 384

*/




/*
Eliminar Indicador 4.15.36 = 192
Eliminar Indicador 6.20.9 = 245
DELETE FROM `institucional1`.`numericadocumental` WHERE `numericadocumental`.`id` = 157
DELETE FROM `institucional1`.`numericadocumental` WHERE `numericadocumental`.`id` = 117;
DELETE FROM `institucional1`.`numericadocumental` WHERE `numericadocumental`.`id` = 342;
DELETE FROM `autoevaluacion`.`instrumentohasindicador` WHERE `instrumentohasindicador`.`instrumento_id` = 2 AND `instrumentohasindicador`.`indicador_id` = 192
DELETE FROM `autoevaluacion`.`instrumentohasindicador` WHERE `instrumentohasindicador`.`instrumento_id` = 3 AND `instrumentohasindicador`.`indicador_id` = 192
DELETE FROM `institucional1`.`instrumentohasindicador` WHERE `instrumentohasindicador`.`instrumento_id` = 2 AND `instrumentohasindicador`.`indicador_id` = 192
DELETE FROM `institucional1`.`instrumentohasindicador` WHERE `instrumentohasindicador`.`instrumento_id` = 3 AND `instrumentohasindicador`.`indicador_id` = 192

DELETE FROM `autoevaluacion`.`instrumentohasindicador` WHERE `instrumentohasindicador`.`instrumento_id` = 2 AND `instrumentohasindicador`.`indicador_id` = 245
DELETE FROM `institucional1`.`instrumentohasindicador` WHERE `instrumentohasindicador`.`instrumento_id` = 2 AND `instrumentohasindicador`.`indicador_id` = 245

DELETE FROM `autoevaluacion`.`indicador` WHERE `indicador`.`id` = 245
DELETE FROM `institucional1`.`indicador` WHERE `indicador`.`id` = 245
DELETE FROM `autoevaluacion`.`indicador` WHERE `indicador`.`id` = 192
DELETE FROM `institucional1`.`indicador` WHERE `indicador`.`id` = 192

*/
/*
PASAR ESTA CONSULTA!!!!! para quitar el indicador de el instrumento informacion numerica 2.4.10
DELETE FROM `autoevaluacion`.`instrumentohasindicador` WHERE `instrumentohasindicador`.`instrumento_id` = 3 AND `instrumentohasindicador`.`indicador_id` = 28
DELETE FROM `institucional1`.`instrumentohasindicador` WHERE `instrumentohasindicador`.`instrumento_id` = 3 AND `instrumentohasindicador`.`indicador_id` = 28
*/


SELECT numericadocumental.documento, numericadocumental.responsable,
numericadocumental.medio, numericadocumental.lugar, numericadocumental.evaluacion,numericadocumental.accion, indicador.nombre,indicador.codigo
from numericadocumental
inner join indicador on numericadocumental.indicador_id=indicador.id 
where numericadocumental.indicador_id=146 and numericadocumental.instrumento_id=2


SELECT caracteristica.id AS cid, caracteristica.nombre AS cno, indicador.id, indicador.nombre, 
format(case 
           when ( (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+
                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+
                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+
                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+
                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/
                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end))) is null 
           then  avg (numericadocumental.evaluacion ) 
           when avg (numericadocumental.evaluacion )is null 
           then (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+
                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+
                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+
                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+
                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/
                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end))
           else (( (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+
                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+
                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+
                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+
                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/
                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end)))+avg (numericadocumental.evaluacion))/2 
       end ,2) as cumplimiento, 
factor.id, indicador.codigo, factor.nombre
FROM Caracteristica
INNER JOIN factor ON caracteristica.factor_id = factor.id
INNER JOIN ponderacioncaracteristica ON ponderacioncaracteristica.caracteristica_id = caracteristica.id
INNER JOIN indicador ON indicador.caracteristica_id = caracteristica.id
LEFT JOIN numericadocumental ON numericadocumental.indicador_id = indicador.id
LEFT JOIN pregunta ON pregunta.indicador_id = indicador.id
LEFT JOIN resultadoevaluacion ON resultadoevaluacion.pregunta_id = pregunta.id
where caracteristica.id ="15"
GROUP BY indicador.id


/* DETALLE FACTOR OK
SELECT factor.id AS fid,  factor.nombre AS fno, ponderacionfactor.ponderacion AS fpo, caracteristica.id as cara, 
                   caracteristica.nombre, ponderacioncaracteristica.nivelimportancia, ponderacioncaracteristica.ponderacion as ponderacionCara, 
                   format(case when ( (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+
                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+
                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+
                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+
                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/
                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end))) is null then  avg (   numericadocumental.evaluacion ) 
                   else (( (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+
                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+
                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+
                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+
                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/
                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end)))+avg (numericadocumental.evaluacion))/2 end ,2) as cumplimiento,
                    format( ponderacioncaracteristica.ponderacion * case when ( (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+
                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+
                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+
                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+
                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/
                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end))) is null then  avg (   numericadocumental.evaluacion ) 
                   else (( (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+
                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+
                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+
                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+
                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/
                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end)))+avg (numericadocumental.evaluacion))/2 end , 2)as evaluacion,
                   format(5 * ponderacioncaracteristica.ponderacion ,2) as ideal,
                   format((ponderacioncaracteristica.ponderacion * case when ( (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+
                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+
                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+
                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+
                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/
                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end))) is null then  avg (   numericadocumental.evaluacion ) 
                   else (( (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+
                   sum( case when respuesta='2'  THEN 2 ELSE 0 end)+
                   sum( case when respuesta='3'  THEN 3 ELSE 0 end)+
                   sum( case when respuesta='4'  THEN 4 ELSE 0 end)+
                   sum( case when respuesta='5'  THEN 5 ELSE 0 end))/
                   (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end)))+avg (numericadocumental.evaluacion))/2 end / ( ponderacioncaracteristica.ponderacion))*20 ,2)as relacion
                   FROM factor
                   INNER JOIN caracteristica ON caracteristica.factor_id = factor.id
                   INNER JOIN ponderacionfactor ON ponderacionfactor.factor_id = factor.id
                   INNER JOIN ponderacioncaracteristica ON ponderacioncaracteristica.caracteristica_id = caracteristica.id
                   INNER JOIN indicador ON indicador.caracteristica_id = caracteristica.id
                   LEFT JOIN numericadocumental ON numericadocumental.indicador_id = indicador.id
                   LEFT JOIN pregunta ON pregunta.indicador_id = indicador.id
                   LEFT JOIN resultadoevaluacion ON resultadoevaluacion.pregunta_id = pregunta.id
                   LEFT JOIN encabezado ON encabezado.id = resultadoevaluacion.encabezado_id
                   WHERE factor.id=4 
                   GROUP BY caracteristica.id;



SELECT c1.fid, c1.fno, c1.ponderacionCara,
format( SUM( c1.ponderacionCara * (case when c1.cumplimientoCara IS null THEN c1.cump2 else ((c1.cumplimientoCara+c1.cump2)/2) end)) / SUM( c1.ponderacionCara ) , 2 ) AS cumplimientoFact, 
format( (SUM( c1.ponderacionCara * (case when c1.cumplimientoCara IS null THEN c1.cump2 else ((c1.cumplimientoCara+c1.cump2)/2) end) ) / SUM( c1.ponderacionCara )) * c1.fpo, 2 ) AS evaluacion,
c1.fpo *5 AS ideal, 
format( (SUM( c1.ponderacionCara * (case when c1.cumplimientoCara IS null THEN c1.cump2 else ((c1.cumplimientoCara+c1.cump2)/2) end)) / SUM( c1.ponderacionCara ))*20 , 2 ) AS relacion
FROM (
    SELECT factor.id AS fid,  factor.nombre AS fno, ponderacionfactor.ponderacion AS fpo, caracteristica.id as cara,
    ponderacioncaracteristica.ponderacion as ponderacionCara, 
    case when ( (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+
    sum( case when respuesta='2'  THEN 2 ELSE 0 end)+
    sum( case when respuesta='3'  THEN 3 ELSE 0 end)+
    sum( case when respuesta='4'  THEN 4 ELSE 0 end)+
    sum( case when respuesta='5'  THEN 5 ELSE 0 end))/
    (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end))) is null then  avg (   numericadocumental.evaluacion ) 
    else ( (sum( case when respuesta='1'  THEN 1 ELSE 0 end)+
    sum( case when respuesta='2'  THEN 2 ELSE 0 end)+
    sum( case when respuesta='3'  THEN 3 ELSE 0 end)+
    sum( case when respuesta='4'  THEN 4 ELSE 0 end)+
    sum( case when respuesta='5'  THEN 5 ELSE 0 end))/
    (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end)))*avg (numericadocumental.evaluacion )/2
    FROM factor
    INNER JOIN caracteristica ON caracteristica.factor_id = factor.id
    INNER JOIN ponderacionfactor ON ponderacionfactor.factor_id = factor.id
    INNER JOIN ponderacioncaracteristica ON ponderacioncaracteristica.caracteristica_id = caracteristica.id
    INNER JOIN indicador ON indicador.caracteristica_id = caracteristica.id
    LEFT JOIN numericadocumental ON numericadocumental.indicador_id = indicador.id
    LEFT JOIN pregunta ON pregunta.indicador_id = indicador.id
    LEFT JOIN resultadoevaluacion ON resultadoevaluacion.pregunta_id = pregunta.id
    LEFT JOIN encabezado ON encabezado.id = resultadoevaluacion.encabezado_id
    WHERE factor.id="1"
    GROUP BY caracteristica.id
    ) AS c1
    group by c1.cara



SELECT factor.id AS fid, factor.nombre AS fno, ponderacionfactor.ponderacion, caracteristica.id, caracteristica.nombre,
                 ponderacioncaracteristica.nivelimportancia as importancia, ponderacioncaracteristica.ponderacion as ponderacion, format(avg( respuesta ),2) AS cumplimiento,
                 format(ponderacioncaracteristica.ponderacion* avg(respuesta),2) AS evaluacion,
                 format(5 * ponderacioncaracteristica.ponderacion ,2) as ideal,
                 format((ponderacioncaracteristica.ponderacion * avg(respuesta) / (5 * ponderacioncaracteristica.ponderacion))*100,2) as Relacion
                 FROM factor
                 INNER JOIN caracteristica ON caracteristica.factor_id = factor.id
                 INNER JOIN ponderacionfactor ON ponderacionfactor.factor_id = factor.id
                 INNER JOIN ponderacioncaracteristica ON ponderacioncaracteristica.caracteristica_id = caracteristica.id
                 INNER JOIN indicador ON indicador.caracteristica_id = caracteristica.id
                 INNER JOIN pregunta ON pregunta.indicador_id = indicador.id
                 INNER JOIN resultadoevaluacion ON resultadoevaluacion.pregunta_id = pregunta.id
                 WHERE pregunta.tipo = 'elegir 1-5' and factor.id="+idF+" and resultadoevaluacion.respuesta!=0
                 GROUP BY caracteristica.id



                    

                 





/*

 SELECT factor.id AS fid, factor.nombre AS fno, ponderacionfactor.ponderacion AS fpo, ponderacioncaracteristica.ponderacion
 FROM factor
 INNER JOIN caracteristica ON caracteristica.factor_id = factor.id
 INNER JOIN ponderacionfactor ON ponderacionfactor.factor_id = factor.id
 INNER JOIN ponderacioncaracteristica ON ponderacioncaracteristica.caracteristica_id = caracteristica.id
 INNER JOIN indicador ON indicador.caracteristica_id = caracteristica.id
 LEFT JOIN pregunta ON indicador.id = PREGUNTA.indicador_id
 LEFT JOIN numericadocumental ON indicador.id = numericadocumental.indicador_id
 LEFT JOIN resultadoevaluacion ON resultadoevaluacion.pregunta_id = pregunta.id
 LEFT JOIN encabezado ON encabezado.id = resultadoevaluacion.encabezado_id
 group by caracteristica.id
 











SELECT c1.fid, c1.fno, c1.fpo, c1.cara, (c1.cumplimiento+ c1.cump2)/2 as prueba,
avg(c1.cumplimiento), avg(c1.cump2), c1.cumplimiento, c1.cump2,
 format( SUM( c1.ponde *  c1.cumplimiento) / SUM( c1.ponde ) , 2 ) AS cumplimiento,
format( SUM( c1.ponde * (case when c1.cumplimiento IS null THEN c1.cump2 else ((c1.cumplimiento+c1.cump2)/2) end) ) / SUM( c1.ponde ) * c1.fpo, 2 ) AS evaluacion,
c1.fpo *5 AS ideal, format( (case when c1.cumplimiento IS null THEN c1.cump2 else ((c1.cumplimiento+c1.cump2)/2) end) *20, 2 ) AS relacion,
format( SUM( c1.ponde * c1.cump2 ) / SUM( c1.ponde ) , 2 ) AS cumplimiento2
 FROM (
 SELECT factor.id AS fid,  factor.nombre AS fno, ponderacionfactor.ponderacion AS fpo, caracteristica.id as cara,
ponderacioncaracteristica.ponderacion as ponde, format(
(sum( case when respuesta='1'  THEN 1 ELSE 0 end)+
sum( case when respuesta='2'  THEN 2 ELSE 0 end)+
sum( case when respuesta='3'  THEN 3 ELSE 0 end)+
sum( case when respuesta='4'  THEN 4 ELSE 0 end)+
sum( case when respuesta='5'  THEN 5 ELSE 0 end))/
(count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end)),2) AS cumplimiento,
avg (   numericadocumental.evaluacion ) AS cump2
 FROM factor
 INNER JOIN caracteristica ON caracteristica.factor_id = factor.id
 INNER JOIN ponderacionfactor ON ponderacionfactor.factor_id = factor.id
 INNER JOIN ponderacioncaracteristica ON ponderacioncaracteristica.caracteristica_id = caracteristica.id
 INNER JOIN indicador ON indicador.caracteristica_id = caracteristica.id
 LEFT JOIN numericadocumental ON numericadocumental.indicador_id = indicador.id
 LEFT JOIN pregunta ON pregunta.indicador_id = indicador.id
 LEFT JOIN resultadoevaluacion ON resultadoevaluacion.pregunta_id = pregunta.id
 LEFT JOIN encabezado ON encabezado.id = resultadoevaluacion.encabezado_id
  GROUP BY indicador.id
 ) AS c1
 group by c1.cara

/*

SELECT count(
CASE WHEN instrumentohasindicador.`instrumento_id` <>1
THEN 1
ELSE NULL
END ) AS cantidad, indicador.nombre
FROM `instrumentohasindicador`
INNER JOIN indicador ON instrumentohasindicador.`indicador_id` = indicador.id
GROUP BY indicador.id
ORDER BY cantidad DESC

SELECT pregunta.pregunta, encuesta.nombre, 
format(
(sum( case when respuesta='1'  THEN 1 ELSE 0 end)+
sum( case when respuesta='2'  THEN 2 ELSE 0 end)+
sum( case when respuesta='3'  THEN 3 ELSE 0 end)+
sum( case when respuesta='4'  THEN 4 ELSE 0 end)+
sum( case when respuesta='5'  THEN 5 ELSE 0 end))/
(count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end)),2), 
pregunta.codigo, encuesta.id,
count( CASE WHEN respuesta = '0' THEN 1 ELSE null end) as "0",
count( CASE WHEN respuesta = '1' THEN 1 ELSE null end) as "1",
count( CASE WHEN respuesta = '2' THEN 1 ELSE null end) as "2",
count( CASE WHEN respuesta = '3' THEN 1 ELSE null end) as "3",
count( CASE WHEN respuesta = '4' THEN 1 ELSE null end) as "4",
count( CASE WHEN respuesta = '5' THEN 1 ELSE null end) as "5"
FROM `resultadoevaluacion`
                 INNER JOIN encabezado ON encabezado.id = resultadoevaluacion.encabezado_id
                 INNER JOIN encuesta ON encuesta.id = encabezado.encuesta_id
                 INNER JOIN pregunta ON pregunta.id = resultadoevaluacion.pregunta_id
                 WHERE pregunta.id ="129"
group by encuesta.id


SELECT pregunta.pregunta, encuesta.nombre, 
                format((sum( case when respuesta='1'  THEN 1 ELSE 0 end)+ 
                sum( case when respuesta='2'  THEN 2 ELSE 0 end)+ 
                sum( case when respuesta='3'  THEN 3 ELSE 0 end)+ 
                sum( case when respuesta='4'  THEN 4 ELSE 0 end)+ 
                sum( case when respuesta='5'  THEN 5 ELSE 0 end))/ 
                (count(case when (respuesta ='1' or respuesta='2' or respuesta='3' or respuesta='4' or respuesta='5') THEN 1 else null end)),2), 
                pregunta.codigo, encuesta.id, 
                count( CASE WHEN respuesta = '0' THEN 1 ELSE null end) , 
        count( CASE WHEN respuesta = '1' THEN 1 ELSE null end) , 
        count( CASE WHEN respuesta = '2' THEN 1 ELSE null end) , 
        count( CASE WHEN respuesta = '3' THEN 1 ELSE null end) , 
        count( CASE WHEN respuesta = '4' THEN 1 ELSE null end) , 
        count( CASE WHEN respuesta = '5' THEN 1 ELSE null end) 
        FROM `resultadoevaluacion`  
        INNER JOIN encabezado ON encabezado.id = resultadoevaluacion.encabezado_id 
        INNER JOIN encuesta ON encuesta.id = encabezado.encuesta_id 
        INNER JOIN pregunta ON pregunta.id = resultadoevaluacion.pregunta_id 
        WHERE pregunta.id =90
        group by encuesta.id




SELECT indicador.id, indicador.nombre AS ino, pregunta.id AS pi, pregunta.pregunta,
format(
(sum( case when respuesta='1'  THEN 1 ELSE 0 end)+
sum( case when respuesta='2'  THEN 2 ELSE 0 end)+
sum( case when respuesta='3'  THEN 3 ELSE 0 end)+
sum( case when respuesta='4'  THEN 4 ELSE 0 end)+
sum( case when respuesta='5'  THEN 5 ELSE 0 end))/
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
AND indicador.id =129
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
*/
