<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" 
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

    <%
        HttpSession session1 = request.getSession();
        String aux = (String) session1.getAttribute("tipoLogin");
        System.out.println("aux: " + session1.getAttribute("tipoLogin"));
        if (aux == null || aux.equals("")) {
        } else {
            if (aux.equals("autoevaluacionInstitucional")) {
                RequestDispatcher rd = request.getRequestDispatcher("/ControllerAI?action=indexAI");
                rd.forward(request, response);
            } else {
                if (aux.equals("Comite Central")) {
                    RequestDispatcher rd = request.getRequestDispatcher("/ControllerCC?action=indexCC");
                    rd.forward(request, response);
                } else {
                    if (aux.equals("Fuente")) {
                        RequestDispatcher rd = request.getRequestDispatcher("/ControllerF?action=indexF");
                        rd.forward(request, response);
                    }
                }
            }

        }

    %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta name="language" content="en" />
        <title>Autoevaluacion Institucional</title>
        <link rel="stylesheet" type="text/css" href="css/layout2.css" />
        <link rel="stylesheet" type="text/css" href="css/footer.css" />
        <link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap3.css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-responsive3.css" rel="stylesheet"/>



        <script src="<%=request.getContextPath()%>/bootstrap/js/jquery.js"></script>
        <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-alert.js"></script>
        <script type="text/javascript"src="script/jquery.validate.js"></script>
        <script type="text/javascript" src="script/jquery.metadata.js"></script>
        <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-dropdown.js"></script>
        <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-collapse.js"></script>
        <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-transition.js"></script>

        <script type="text/javascript" src="script/jquery-layout.js"></script>




        <style type="text/css">
            label {
                display: block;
                margin: 10px 0 2px;
                font-family: Arial,sans-serif;
                font-size: 14px;

            }
            div.dropdown {
                border: 1px solid #BFC3C7;
                padding: 4px;
                margin: 0;
                width: 240px;
                line-height: 19px;
                background-clip: padding-box;
                border-radius: 4px;
                position: relative;
                background-color: #F5F6F9;
                background-image: -moz-linear-gradient(center top , #F5F6F9, #F9FBFD);
                box-shadow: 0 1px 0 white;
            }

            div.dropdown a {
                line-height: 19px;
                padding: 0 20px 0 7px;

            }

            div.dropdown ul {
                background: none repeat scroll 0 0 padding-box rgba(255, 255, 255, 0.98);
                border-radius: 4px 4px 4px 4px;
                box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.15), 0 0 1px rgba(0, 0, 0, 0.3);
                display: none;
                padding: 3px 0;
                position: absolute;
                top: 100%;
                white-space: nowrap;
                z-index: 52;
                list-style: none outside none;
            }
            div.dropdown li {
                line-height: 22px;
                width: 208px;
                background-position: 0 50%;
                border-bottom: 1px solid transparent;
                border-top: 1px solid transparent;
                color: #3190D3;
                cursor: default;
                padding: 0 21px;
                position: relative;
            }

            .dropdown li:hover {
                -moz-border-bottom-colors: none;
                -moz-border-image: none;
                -moz-border-left-colors: none;
                -moz-border-right-colors: none;
                -moz-border-top-colors: none;
                background-color: #F2F2F3;
                border-color: #E7E8E9;
                border-style: solid;
                border-width: 1px 0;
                color: #D85724;
            }


        </style>



        <style type="text/css">

            /* remove padding and scrolling from elements that contain an Accordion OR a content-div */


            .ui-layout-center ,	/* has content-div */
            .ui-layout-west ,	/* has Accordion */
            .ui-layout-east ,	/* has content-div ... */
            .ui-layout-east .ui-layout-content { /* content-div has Accordion */
                padding: 0px;
                overflow: hidden;
            }
            .ui-layout-center P.ui-layout-content {
                line-height:	1.4em;
                margin:			0; /* remove top/bottom margins from <P> used as content-div */
            }
            /*h3, h4 { /* Headers & Footer in Center & East panes 
                font-size:		1.1em;
                background:		#EEF;
                border:			1px solid #BBB;
                border-width:	0 0 1px;
                padding:		7px 10px;
                margin:			0;
            }*/
            .ui-layout-west h4 { /* Footer in East-pane */
                font-size:		0.9em;
                font-weight:	normal;
                border-width:	1px 0 0;
                overflow: hidden;
            }

            .ui-layout-north {
                /* Drop-Down */
                bottom:		auto;
                margin:		0;
                margin-top:	1.45em;
            }

            .inner-layout-north {
                /* Drop-Down */
                bottom:		auto;
                margin:		0;
                margin-top:	1.45em;
            }
            .ui-layout-pane2 { /* solo header */
                background:	#FFF; 
                /* <<<< ojo comentado por mi >>>>> */
                /*border: 1px solid #BBB; */


                /* DO NOT add scrolling (or padding) to 'panes' that have a content-div,
                   otherwise you may get double-scrollbars - on the pane AND on the content-div
                */

                padding: 0;
                overflow:	auto;
            }

            .ui-layout-pane3 { /* all 'panes' */
                background:	#FFF; 
                /*border:		1px solid #BBB;*/
                /* DO NOT add scrolling (or padding) to 'panes' that have a content-div,
                   otherwise you may get double-scrollbars - on the pane AND on the content-div
                */
                padding:	10px; 
                overflow:	hidden; /* modificado x mi */
            }



            .middle-center, .inner-center{
                padding: 0px;   

            }


        </style>
        <script type="text/javascript">
            $().ready(function() {
                $(".progress").ajaxStart(function(){
                    $(this).show(); 
                    $(".bar").css("width","5%");
                });
                $(".progress").ajaxStop(function(){
                    $(".bar").css("width","100%");
                    setTimeout(function(){
                        $(".progress").hide();
                    },400);
                    
                });
                
                $('.dropdown-menu').find('form').click(function (e) {
                    var target = $(e.target);
                    if(!target.is("a"))
                        e.stopPropagation();
                });
                $("#formulario_login").validate({
                    errorLabelContainer:".alert-error",
                    submitHandler: function() {
                        this.timer = setTimeout(function () {
                            $.ajax({
                                url: '/AutoevaluacionInstitucional/loginController',
                                data: 'un='+ $('#codigo').val() +'&pw=' + $('#pass').val() +'&tp=' + $('#tipo :selected').val(),
                                type: 'post',
                                success: function(msg){
                                   
                                    if(msg == 0) // Message Sent, check and redirect
                                    {                // and direct to the success page
	 
	
                                        //redirect to secure page
                                        document.location='/AutoevaluacionInstitucional/';
	
	 
                                    }
                                    else if(msg == 1)
                                    { 
                                        $(".alert-error").html("<a data-dismiss='alert' class='close'>×</a>"  
                                            +"<strong>Error!</strong>"
                                            +"<label for='pass' generated='true' class='error'> Usuario No Posee Permisos Para Ingresas bajo ese perfil.</label>");  
                                        $(".alert-error").show();
                                      
                                    }
                                    else if(msg == 2 || msg == 3)
                                    { 
                                        $(".alert-error").html("<a data-dismiss='alert' class='close'>×</a>"  
                                            +"<strong>Error!</strong>"
                                            +"<label for='pass' generated='true' class='error'> C&oacutedigo y/o Contraseña Incorrecto(s).</label>");  
                                        $(".alert-error").show();
                                      
                                      
                                        
                                    }
                                    
                                    else if(msg == 4)
                                    { 
                                        $(".alert-error").html("<a data-dismiss='alert' class='close'>×</a>"  
                                            +"<strong>Error!</strong>"
                                            +"<label for='pass' generated='true' class='error'> Formato de Código Incorrecto.</label>");  
                                        $(".alert-error").show();
                                      
                                        
                                       
                                    }
                                }
                              
                            });
                        
                           

                        }, 400);
                            
                    }               
                });
		
                
                
                
                
        
            });
            var myLayout, innerLayout, middleLayout;
            $(document).ready( function() {

                myLayout = $('body').layout({
                    
                         
                    west__size:			197
                    ,   center__paneSelector:   ".ui-layout-center"
                    ,   north__paneClass:       "ui-layout-pane2"
                    ,   north__size:            1
                    ,   north__maxSize:         1
                    ,   closable:		true	// pane can open & close
                    ,	resizable:		true	// when open, pane can be resized 
                    ,	slidable:		false	// when closed, pane can 'slide' open over other panes - closes on mouse-out

                    ,   north__slidable:	false	// OVERRIDE the pane-default of 'slidable=true'
                    ,	north__spacing_open:	0		// no resizer-bar when open (zero height)
                            
                    ,	south__resizable:	false	// OVERRIDE the pane-default of 'resizable=true'
                    ,	south__spacing_open:	0		// no resizer-bar when open (zero height)
                    ,   south__paneClass: "ui-layout-pane"
                    //	some resizing/toggling settings
                    
                
                });
                myLayout.allowOverflow('north'); 
                 		
                // ACCORDION - in the East pane - in a 'content-div'
		

                // THEME SWITCHER
                //addThemeSwitcher('.ui-layout-north',{ top: '42px', right: '750px' });
                
                
                // if a new theme is applied, it could change the height of some content,
                // so call resizeAll to 'correct' any header/footer heights affected
                // NOTE: this is only necessary because we are changing CSS *AFTER LOADING* using themeSwitcher
                setTimeout( myLayout.resizeAll, 1000 ); /* allow time for browser to re-render with new theme */
         
            });
         
        </script>
    </head>

    <body>
        <div class="ui-layout-north ui-widget-content">
            <div class="navbar navbar-fixed-top">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </a>
                        <a class="brand" style="padding-bottom: 3px; padding-top: 6px" href="#"><img src="css/images/SIA UDEC - LOGO letras solas - copia.png"></img></a>
                        <div class="btn-group pull-right">
                            <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                                <i class="icon-user"></i> Iniciar sesi&oacute;n
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu" style="padding-top: 0px;">
                                <form  id="formulario_login" class="well"  action="" method="post" style="background-color: #FFFFFF; margin-bottom: 0px; border:0">
                                    <div class="progress progress-warning progress-striped active" style="display: none;">
                                        <div class="bar"
                                             style="width: 5%;"></div>
                                    </div>

                                    <div class='alert alert-error' style="display: none">
                                        <a data-dismiss='alert' class='close'>×</a>  
                                        <strong>Error!</strong>
                                    </div>

                                    <label>Usuario</label>
                                    <input id="codigo" type="text" name="codigo" class="span3 {required:true,number:true,maxlength:10,messages:{number:'Por favor ingrese un c&oacutedigo v&aacutelido',required:'El campo c&oacutedigo es requerido',maxlength:'El campo c&oacutedigo no admite m&aacutes de 10 numeros'}}"/>
                                    <label>Contrase&ntilde;a</label>
                                    <input id="pass" type="password" name="pass" class="span3 {required:true,messages:{required:'El campo contrase&ntildea es requerido'}}"/>
                                    <label><a href="#" style="color: #F77F00;margin-top: -20px;margin-right:50%; padding-left: 0px;font-size: 12px">¿Olvidó su contrase&ntilde;a?</a></label>

                                    <label style="margin-top: 15px;">Perfil de ingreso</label>
                                    <select id="tipo" name="tipo" class="span3">
                                        <option>Autoevaluacion Institucional</option>
                                        <option>Comite Central</option>
                                        <optgroup label="Fuentes">
                                            <option>Estudiantes</option>
                                            <option>Docentes</option>
                                            <option>Administrativos</option>
                                        </optgroup>
                                    </select>
                                    <button type="submit" class="btn" name="submit_login" style="font-weight:700;margin-top: 15px;">Iniciar sesi&oacute;n</button>
                                </form>
                            </ul>
                        </div>



                        <div class="nav-collapse">
                            <ul class="nav">
                                <li class="active"><a href="#">Inicio</a></li>
                                <li><a href="#">Contacto</a></li>
                            </ul>
                        </div><!-- /.nav-collapse -->
                    </div>
                </div><!-- /navbar-inner -->
            </div><!-- /navbar -->
            <!--div que cierra-->
        </div>
        <div class="ui-layout-south ui-widget-content">
            <div class="contenedor_footer fondo_footer" id="footer">
                <div class="links">
                    <a href="#">
                        <span>Acerca de</span>
                    </a>

                    <a href="#">
                        <span>Derechos de Autor</span>
                    </a>

                    <a href="#">
                        <span>Ayuda</span>
                    </a>
                </div>
            </div>
        </div>
        <div class="ui-layout-center">
            <div style="height: 68%; background-color: #f77f00;">
                <IMG SRC="css/images/torre.png" border=0 style="z-index: 5;height: 95%; position: relative; left: 3%; top: 5%"/>    
                <IMG SRC="css/images/SIA UDEC - LOGO_1.png" border=0 style="z-index: 5;height: 23%; position: relative;right:15%;top: 13%;float: right"/>    
                <div style="z-index: 5;float: left;position:relative;top:25%;left: 4%;width: 26%;color: #FAF4E3;"><h2>La Universidad de Cartagena cuenta con el Sistema Institucional de Autoevaluaci&oacute;n  SIA-UdeC que sirve de Apoyo a los procesos de autoevaluación con fines de ACREDITACIÓN INSTITUCIONAL.</h2></div>     
            </div>
            <div style="height: 32%;" class="offset2">
                <ul class="thumbnails" style="margin-top: 10px;">
                    <li class="span3">
                        <div class="thumbnail" style="box-shadow:none;border: 0;">
                            <a href="#" style="text-decoration:none;">
                                <img width="28%" height="28%" src="css/images/diseno.png">
                            </a><!-- THEME: Grooveshark Explore Thumb  -->
                            <a href="#" style="text-decoration:none;">
                                <img width="28%" height="28%" src="css/images/ponderacion.png">
                            </a><!-- THEME: Grooveshark Explore Thumb  -->
                            <a  href="#" style="text-decoration:none;">
                                <img width="28%" height="28%" src="css/images/proceso.png">
                            </a>
                            <div class="caption">
                                <h3 style="color: #F77F00;">Construcci&oacute;n del Modelo</span></h3>
                                <p>Realice la ponderación de factores y características, el diseño de instrumentos y m&aacute;s.</p>
                                <a href="#" class="btn js-btn" style="text-decoration: none;">Explorar &raquo;</a>
                            </div>
                        </div>

                    </li>

                    <li class="span3">
                        <div class="thumbnail" style="box-shadow:none;border: 0;">
                            <a href="#" style="text-decoration:none;">
                                <img width="28%" height="28%" src="css/images/encuesta.png">
                            </a><!-- THEME: Grooveshark Explore Thumb  -->
                            <a href="#" style="text-decoration:none;">
                                <img width="28%" height="28%"  src="css/images/documental.png">
                            </a><!-- THEME: Grooveshark Explore Thumb  -->
                            <a  href="#" style="text-decoration:none;">
                                <img width="28%" height="28%" src="css/images/recoleccion.png">
                            </a>
                            <div class="caption">
                                <h3 style="color: #F77F00;">Recopile Informaci&oacute;n</h3>
                                <p>A traves de encuestas en linea, informaci&oacute;n documental y m&aacute;s.</p>
                                <a href="#" class="btn js-btn" style="text-decoration: none;">Explorar &raquo;</a>
                            </div>
                        </div>

                    </li>
                    <li class="span3">
                        <div class="thumbnail" style="box-shadow:none;border: 0;">
                            <a href="#" style="text-decoration:none;">
                                <img width="28%" height="28%" src="css/images/pie.png">
                            </a><!-- THEME: Grooveshark Explore Thumb  -->
                            <a href="#" style="text-decoration:none;">
                                <img width="28%" height="28%"  src="css/images/bar.png">
                            </a><!-- THEME: Grooveshark Explore Thumb  -->
                            <a  href="#" style="text-decoration:none;">
                                <img width="28%" height="28%" src="css/images/line.png">
                            </a>
                            <div class="caption">
                                <h3 style="color: #F77F00;">Analice Resultados</h3>
                                <p>Realiza el procesamiento y sistematizaci&oacute;n de la informaci&oacute;n recolectada, mostrando informes en tiempo real.</p>
                                <a href="#" class="btn js-btn" style="text-decoration: none;">Explorar &raquo;</a>
                            </div>
                        </div>

                    </li>
                </ul>
            </div>
        </div>


    </body>
</html>
