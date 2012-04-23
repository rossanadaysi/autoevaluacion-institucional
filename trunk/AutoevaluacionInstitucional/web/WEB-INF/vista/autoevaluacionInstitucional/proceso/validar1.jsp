<script type="text/javascript">
    $(document).ready(function() {
        if(${aux_IniciarP} == 0){
            $('#myModalIP').modal(); 
            location = "<%=request.getContextPath()%>/#detalleProceso"; 
        }else{
            $("#menu").load("<%=request.getContextPath()%>/ControllerAI?action=menuAI");
            location = "<%=request.getContextPath()%>/#detalleProceso";    
        }
    })            
</script>
