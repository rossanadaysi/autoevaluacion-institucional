delete t1 from `" + tabla + "` t1 inner join estudiante on t1.estudiante_id = estudiante.id  where `muestra_id` = " + idMuestra + " and estudiante.programa_id = " + idP + " and estudiante.semestre = " + idS
