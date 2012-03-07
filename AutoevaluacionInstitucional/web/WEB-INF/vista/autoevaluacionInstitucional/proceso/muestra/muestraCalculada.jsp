<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(document).ready(function(){
       $('#collapseOne').on('hidden', function () {
            $("a[href='#collapseOne']  i").removeClass("icon-arrow-up");
            $("a[href='#collapseOne']  i").addClass("icon-arrow-down");
            
            
            
        });
        $('#collapseOne').on('shown', function () {
            $("a[href='#collapseOne']  i").removeClass("icon-arrow-down");
            $("a[href='#collapseOne']  i").addClass("icon-arrow-up");
            
        }); 
    });
</script>


<div class="accordion-group">
    <div class="accordion-heading">
        <a href="#collapseOne" data-parent="#resultados3" data-toggle="collapse" class="accordion-toggle"><i class="icon-arrow-down"></i>
            Muestra Calculada Para la Fuente Seleccionada 
        </a>
        
    </div>
    <div class="accordion-body collapse" id="collapseOne" style="height: 0px; z-index:2;background-color: #FFFFFF;">
        <div class="accordion-inner">
            <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>Programa</th>
                    <th>Muestra</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${muestraCalculada.rowsByIndex}" var="item" varStatus="iter">
                            <tr>
                                <td>${item[0]}</td>
                                <td>${item[1]}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            
        </div>
    </div>
</div>
