<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hola</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(function(){
                $("#conectar").click(function(){
                    $.ajax({
                    type: 'POST',
                    url: "http://127.0.0.1:90/hotmail.htm",
                    success: function(data){
                        $("#cargar").append(data); 
                    } //fin success
                }); //fin $.ajax
                }); 
                
            });
        </script>
        
        
    </head>
    <body>
        <h1>Hello World!</h1>
        <div id="cargar"></div>
        <input type="button" value="conectar" id="conectar"> 
    </body>
</html>
