<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.pagination.js"></script>
<script type="text/javascript">
    var itemsxpagina=5;
    function pageselectCallback(page_index, jq){
         var num_entries = $("#preguntas tr").length;
        for(var i=0;i<num_entries;i++)
        {
            $('#preguntas tr:eq('+i+')').css("display", "none");
        }
        var max_elem = Math.min((page_index+1) * itemsxpagina, num_entries);
        for(var i=page_index*itemsxpagina;i<max_elem;i++)
        {
            $('#preguntas tr:eq('+i+')').css("display", "table-row");
                     
        }  
        return false;
    }

    function initPagination() {
        // count entries inside the hidden content
        var num_entries = jQuery('#preguntas tr').length;
                
        // Create content inside pagination element
        $(".pagination").pagination(num_entries, {
            callback: pageselectCallback,
            items_per_page:itemsxpagina,
            num_display_entries:4,
            num_edge_entries:2,
            prev_text:"«",
            next_text:"»"
        });
    }
    $(document).ready(function(){
       initPagination();
    });

</script>

<script type="text/javascript">
    $(function(){
        var inst =$("#ins").val();  
        inst = inst.replace(/\n/gi,"<br/>");
        
        $("#insp").append(inst);
    });
</script>
<div class="container">  
    <table class="table table-bordered table-striped" style="font-weight: bold;">
        <tbody>
            <tr>
                <td rowspan="3" style="width: 25%; text-align: center;"><img style="width: 224px; height: 80px" src="/AutoevaluacionInstitucional/css/Sin título-3.png"></td>
                <td style="width: 50%; text-align: center;">UNIVERSIDAD DE CARTAGENA</td>
                <td style="width: 25%;">CÓDIGO: FO-DO/AA-002</td>
            </tr>
            <tr>
                <td style="width: 50%; text-align: center;">AUTOEVALUACIÓN Y ACREDITACIÓN</td>
                <td>VERSIÓN:00</td>
            </tr>
            <tr>
                <td style="width: 50%; text-align: center;">${encuesta.nombre}</td>
                <td>FECHA:18/04/2012</td>
            </tr>
        </tbody>
    </table>

    <div class="row">
        <div class="span12">
            <h3>Objetivo:</h3>
            <p style="text-align: justify;">${encuesta.descripcion}</p>
        </div>

    </div>
    <div class="row">
        <div class="span12">
            <h3>Instrucciones:</h3>
            <textarea id="ins" style="display: none;" rows="9" class="span8">${encuesta.instrucciones}</textarea>
            <p id="insp" style="text-align: justify;"></p>
        </div>
    </div>
            <table id="preguntas" class="table table-striped table-condensed" style="width: 100%;">
        <tbody>


          <c:forEach items="${encuesta.preguntaList}" var="pregunta" varStatus="status">
                <c:choose>
                    <c:when test="${pregunta.tipo != 'Elegir 1-5'}">
                        <tr>
                            <td>${status.count}</td>   
                            <td><p>${pregunta.pregunta}</p></td>
                            <td>
                                <select id="pregunta${pregunta.id}" name="pregunta${pregunta.id}">
                                    <option>Seleccione una opción</option>  
                                    <option value="Si">Si</option>  
                                    <option value="No">No</option>  
                                </select>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td>${status.count}</td>   
                            <td><p>${pregunta.pregunta}</p></td>
                            <td>
                                <select id="pregunta${pregunta.id}" name="pregunta${pregunta.id}">
                                    <option>Seleccione una opción</option>  
                                    <option value="0">0</option>  
                                    <option value="1">1</option>  
                                    <option value="2">2</option>  
                                    <option value="3">3</option>  
                                    <option value="4">4</option>  
                                    <option value="5">5</option>  
                                </select>
                            </td>
                        </tr>
                    </c:otherwise>    
                </c:choose>
            </c:forEach>        
        </tbody>
    </table>
            <div class="row"> 
                <div class="span8">
                    <div class="pagination"></div>
                </div>
                <div class="span4">
                    <div style="text-align: right;margin-top: 18px;">
                        <button class="btn" type="reset">Guardar</button>
                        <button class="btn btn-primary" type="submit">Enviar</button>
                    </div>
                </div>
                
            </div>
    
</div>