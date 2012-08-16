<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.metadata.js"></script>
<script type="text/javascript">
    var itemsxpagina=10;
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
            prev_text:"&larr; Anterior",
            next_text:"Siguiente &rarr;",
            prev_show_always:false,
            next_show_always:false
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
        
        
               
        var validator = $("#formResponderE").bind("invalid-form.validate", function() {
            alert("usted ha dejado de contestar " + validator.numberOfInvalids() + " preguntas, por favor contestelas todas.");
        })
        .validate({
            ignore: "", 
                
            submitHandler: function(){
                $.ajax({
                    type: 'POST', 
                    url: "<%=request.getContextPath()%>/formController3?action=responderE",
                    data: $("#formResponderE").serialize(),
                    beforeSend: function(){
                        $("div.ui-layout-center").append("<div class='page_loading'>"
                            +"<span>Enviando</span>"
                            +"<img src='css/images/loading.gif' style='margin-left:6px;'>"
                            +"</div>");
                    },
                    success: function(){
                        $(".page_loading").hide();
                        $("#myModalGracias").modal();
                        $('#myModalGracias').on('hidden', function () {
                            location = "<%=request.getContextPath()%>/#inicio"
                        })
                        
                    } //fin success
                }); //fin $.ajax    
            }
        });
        
        $("button[rel=popover1]")
        .popover({placement:'left'})
        .click(function(e){
            e.preventDefault();
    
            
            $(this).popover('hide');
            $(this).button('loading');
                
            $.ajax({
                type: 'POST', 
                url: "<%=request.getContextPath()%>/formController3?action=guardarE",
                data: $("#formResponderE").serialize(),
                success: function(){
                    $("button[rel=popover1]").button('reset');
                } //fin success
            })
            
        });
        
        $("button[rel=popover2]")
        .popover({placement:'left'});
    });
</script>
<style type="text/css">
    label.error{
        color:#B94A48;
    }
</style>
<a class="span10" style="text-align: right" id="printEnlace"><i class="icon-print"></i> Imprimir</a>  
<div class="hero-unit">
    <div style="margin-left: -30px;">
        <div id="conte" class="span10" style="text-align: justify">
            <div class="row">
                <table class="table table-bordered table-striped" style="font-weight: bold;">
                    <tbody>
                        <tr>
                            <td rowspan="3" style="width: 25%; text-align: center;"><img style="width: 224px; height: 80px" src="/AutoevaluacionInstitucional/css/LogoUdeC.png"></td>
                            <td style="width: 50%; text-align: center;">UNIVERSIDAD DE CARTAGENA</td>
                            <td style="width: 25%;">CÓDIGO: FO-DO/AA-002</td>
                        </tr>
                        <tr>
                            <td style="width: 50%; text-align: center;">AUTOEVALUACIÓN Y ACREDITACIÓN</td>
                            <td>VERSIÓN:00</td>
                        </tr>
                        <tr>
                            <td style="width: 50%; text-align: center;">${encuesta.rowsByIndex[0][0]}</td>
                            <td>FECHA:18/04/2012</td>
                        </tr>
                    </tbody>
                </table>
                <h3>Objetivo:</h3>
                <p style="text-align: justify;">${encuesta.rowsByIndex[0][1]}</p>
                <h3>Instrucciones:</h3>
                <textarea id="ins" style="display: none;" rows="9" class="span8">${encuesta.rowsByIndex[0][2]}</textarea>
                <p id="insp" style="text-align: justify;"></p>
            </div>


            <!--como se va poner-->
            <c:forEach items="${respuestas.rowsByIndex}" var="respuesta" varStatus="status">
                <c:choose>
                    <c:when test="${status.count%2==1}">
                        <div class="row printDiv">
                            <c:choose>
                                <c:when test="${respuesta[2] != 'Elegir 1-5'}">
                                    <div class="span5">
                                        <p>${status.count} ${respuesta[1]}</p>
                                        <c:choose>
                                            <c:when test="${respuesta[3]=='Si'}">
                                                <label class="radio"><input type="radio" checked="">Si</label>        
                                                </c:when>
                                                <c:otherwise>
                                                <label class="radio"><input type="radio">Si</label>        
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${respuesta[3]=='No'}">
                                                <label class="radio"><input type="radio" checked="">No</label>        
                                                </c:when>
                                                <c:otherwise>
                                                <label class="radio"><input type="radio">No</label>        
                                                </c:otherwise>
                                            </c:choose>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="span5">
                                        <p>${status.count} ${respuesta[1]}</p>
                                        <c:choose>
                                            <c:when test="${respuesta[3]=='5'}">
                                                <label class="radio"><input type="radio" checked="">5 Completamente deacuerdo</label>        
                                                </c:when>
                                                <c:otherwise>
                                                <label class="radio"><input type="radio">5 Completamente deacuerdo</label>        
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${respuesta[3]=='4'}">
                                                <label class="radio"><input type="radio" checked="">4 De acuerdo</label>        
                                                </c:when>
                                                <c:otherwise>
                                                <label class="radio"><input type="radio">4 De acuerdo</label>        
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${respuesta[3]=='3'}">
                                                <label class="radio"><input type="radio" checked="">3 Parcialmente de acuerdo</label>        
                                                </c:when>
                                                <c:otherwise>
                                                <label class="radio"><input type="radio">3 Parcialmente de acuerdo</label>        
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${respuesta[3]=='2'}">
                                                <label class="radio"><input type="radio" checked="">2 En desacuerdo</label>        
                                                </c:when>
                                                <c:otherwise>
                                                <label class="radio"><input type="radio">2 En desacuerdo</label>        
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${respuesta[3]=='1'}">
                                                <label class="radio"><input type="radio" checked="">1 Completamente en desacuerdo</label>        
                                                </c:when>
                                                <c:otherwise>
                                                <label class="radio"><input type="radio">1 Completamente en desacuerdo</label>        
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${respuesta[3]=='0'}">
                                                <label class="radio"><input type="radio" checked="" disabled="">0 No sabe</label>        
                                                </c:when>
                                                <c:otherwise>
                                                <label class="radio"><input type="radio" disabled="">0 No sabe</label>        
                                                </c:otherwise>
                                            </c:choose>
                                    </div>
                                </c:otherwise>    
                            </c:choose>
                        </c:when>

                        <c:otherwise>

                            <c:choose>
                                <c:when test="${respuesta[2] != 'Elegir 1-5'}">
                                    <div class="span5">
                                        <p>${status.count} ${respuesta[1]}</p>
                                        <c:choose>
                                            <c:when test="${respuesta[3]=='Si'}">
                                                <label class="radio"><input type="radio" checked="" disabled="">Si</label>        
                                                </c:when>
                                                <c:otherwise>
                                                <label class="radio"><input type="radio" disabled="">Si</label>        
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${respuesta[3]=='No'}">
                                                <label class="radio"><input type="radio" checked="" disabled="">No</label>        
                                                </c:when>
                                                <c:otherwise>
                                                <label class="radio"><input type="radio" disabled="">No</label>        
                                                </c:otherwise>
                                            </c:choose>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="span5">
                                        <p>${status.count} ${respuesta[1]}</p>
                                        <c:choose>
                                            <c:when test="${respuesta[3]=='5'}">
                                                <label class="radio"><input type="radio" checked="" disabled="">5 Completamente deacuerdo</label>        
                                                </c:when>
                                                <c:otherwise>
                                                <label class="radio"><input type="radio" disabled="">5 Completamente deacuerdo</label>        
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${respuesta[3]=='4'}">
                                                <label class="radio"><input type="radio" checked="" disabled="">4 De acuerdo</label>        
                                                </c:when>
                                                <c:otherwise>
                                                <label class="radio"><input type="radio" disabled="">4 De acuerdo</label>        
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${respuesta[3]=='3'}">
                                                <label class="radio"><input type="radio" checked="" disabled="">3 Parcialmente de acuerdo</label>        
                                                </c:when>
                                                <c:otherwise>
                                                <label class="radio"><input type="radio" disabled="">3 Parcialmente de acuerdo</label>        
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${respuesta[3]=='2'}">
                                                <label class="radio"><input type="radio" checked="" disabled="">2 En desacuerdo</label>        
                                                </c:when>
                                                <c:otherwise>
                                                <label class="radio"><input type="radio" disabled="">2 En desacuerdo</label>        
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${respuesta[3]=='1'}">
                                                <label class="radio"><input type="radio" checked="" disabled="">1 Completamente en desacuerdo</label>        
                                                </c:when>
                                                <c:otherwise>
                                                <label class="radio"><input type="radio" disabled="">1 Completamente en desacuerdo</label>        
                                                </c:otherwise>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${respuesta[3]=='0'}">
                                                <label class="radio" ><input type="radio" disabled="" checked="">0 No sabe</label>        
                                                </c:when>
                                                <c:otherwise>
                                                <label class="radio" ><input type="radio" disabled="">0 No sabe</label>        
                                                </c:otherwise>
                                            </c:choose>
                                    </div>
                                </c:otherwise>    
                            </c:choose>
                        </div><!--jaja-->
                    </c:otherwise>
                </c:choose>
            </c:forEach>               




        </div>
    </div>
</div>
