ResultSet result;
cont1 = 0;
cont2 = 0;
cont3 = 0;
bd.conectarMySQL();
maneja.setConexion(bd);

sql = "Select factor.`NOMBRE` as 'Factor',"
+ " caracteristica.`NOMBRE` as 'Caracteristica',"
+ " ponderacion.`PONDERACION` as 'Ponderacion (1 â€“10)',"
+ " Format(((((Select AVG(respuesta.respuesta))*100)/5)*((5)/100)),2) as 'Grado de Cumplimiento (1 - 5)',"
+ " ponderacion.`PONDERACION` * Format(((((Select AVG(respuesta.respuesta))*100)/5)*((5)/100)),2) as 'Evaluacion Teniendo en Cuenta Ponderacion',"
+ " 5 * ponderacion.`PONDERACION` as 'Logro Ideal',"
+ " Format(((ponderacion.`PONDERACION` * ((((Select AVG(respuesta.respuesta))*100)/5)*((5)/100))) / (5 * ponderacion.`PONDERACION`))*100,2) as 'RelaciÃ³n con el logro ideal',"
+ " caracteristica.`ID`"
+ " from resultadoevaluacion"
+ " inner join proyecto on resultadoevaluacion.`IDPROYECTO_ID` = proyecto.`ID`"
+ " inner join respuesta on resultadoevaluacion.`IDRESPUESTA_ID` = respuesta.`ID`"
+ " inner join encuesta on resultadoevaluacion.`IDENCUESTA_ID` = encuesta.`ID`"
+ " inner join pregunta on resultadoevaluacion.`IDPREGUNTA_ID` = pregunta.`ID`"
+ " inner join indicador on pregunta.`INDICADOR_ID` = indicador.`ID`"
+ " inner join caracteristica on indicador.`CARACTERISTICA_ID` = caracteristica.`ID`"
+ " inner join factor on caracteristica.`FACTOR_ID` = factor.`ID`"
+ " inner join ponderacion on caracteristica.`ID` = ponderacion.`IDCARATERISTICA_ID`"
+ " where resultadoevaluacion.IDPROYECTO_ID = " + proyecto.getId() + ""
+ " and respuesta.`RESPUESTA` != 0"
+ " and pregunta.`DTYPE` = 'PreguntaCerrada'"
+ " and ponderacion.`IDPROYECTO_ID` = " + proyecto.getId() + ""
+ " group by caracteristica.`NOMBRE`"
+ " order by factor.`ID`";


