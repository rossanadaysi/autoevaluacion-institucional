<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="hero-unit">
    <div class="row">
        <div class="span10">
            <br/>
            <fieldset>
                <legend>Estado del proceso en ejecución</legend>
                <p>Detalle:</p>
                <br>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>Descripción</th>
                    <th>Fecha Inicio</th>
                    <th>Fecha Cierre</th>
                    <th>Programa</th>
                    <th>Informes</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${detailProceso2}" var="row" varStatus="iter">
                            <tr>  
                                <td>   
                                    <c:out value="${row.descripcion}"/>
                                </td>
                                <td>   
                                    <c:out value="${row.fechainicio}"/>
                                </td>
                                <td>   
                                    <c:out value="${row.fechacierre}"/>
                                </td>
                                <td>   
                                    <c:out value="${row.programaId.nombre}"/>
                                </td>
                                <td>   
                                    link
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table> 
                <p>Estado general del proceso:</p>
                <br>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>Número total de muestra</th>
                    <th>Número de personas que han evaluado las encuestas</th>
                    <th>Procentaje de personas que han evaluado las encuestas</th>
                    <th>Número de personas que faltan por evaluar las encuestas</th>
                    <th>Porcentaje de personas que faltan por evaluar las encuestas</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${tabla1.rowsByIndex}" var="row" varStatus="iter">
                            <tr>  
                                <td>   
                                    <c:out value="${row[0]}"/>
                                </td>
                                <td>   
                                    <c:out value="${row[1]}"/>
                                </td>
                                <td>   
                                    <c:out value="${row[2]}%"/>
                                </td>
                                <td>   
                                    <c:out value="${row[3]}"/>
                                </td>
                                <td>   
                                    <c:out value="${row[4]}%"/>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>



                <p>Estado por fuente del proceso:</p>
                <br>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>Fuente</th>
                    <th>Número total de muestra</th>
                    <th>Número de personas que han evaluado las encuestas</th>
                    <th>Procentaje de personas que han evaluado las encuestas</th>
                    <th>Número de personas que faltan por evaluar las encuestas</th>
                    <th>Porcentaje de personas que faltan por evaluar las encuestas</th>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </fieldset>
        </div>
    </div>
</div>    
