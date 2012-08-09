<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" 
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

    <%
        HttpSession session1 = request.getSession();
        String aux = (String) session1.getAttribute("tipoLogin");
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
        <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-carousel.js"></script>
        <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-transition.js"></script>
        <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-modal.js"></script>

        <script type="text/javascript" src="script/jquery-layout.js"></script>




        <style type="text/css">
            #home-carousel {
                border: 2px solid #2C2C2C;
                -webkit-border-radius: 6px;
                -moz-border-radius: 6px;
                border-radius: 6px;
            }


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
                
                $('.about').click(function(){
                    
                    $('#myModalAbout').modal();
                });


                $("#home-carousel").carousel({
                    interval: 15000
                });
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
                        <a class="brand" style="padding-bottom: 3px; padding-top: 6px" href="#"><img src="css/images/logoSIA.png"></img></a>
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
                                    <input id="codigo" type="text" name="codigo" class="span3 {required:true,maxlength:10,messages:{required:'El campo c&oacutedigo es requerido',maxlength:'El campo c&oacutedigo no admite m&aacutes de 10 caracteres'}}"/>
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
                                            <option>Egresados</option>
                                            <option>Directivos</option>
                                            <option>Agencias Gubernamentales</option>
                                            <option>Empleadores</option>
                                        </optgroup>
                                    </select>
                                    <button type="submit" class="btn" name="submit_login" style="font-weight:700;margin-top: 15px;">Iniciar sesi&oacute;n</button>
                                </form>
                            </ul>
                        </div>



                        <div class="nav-collapse">
                            <ul class="nav">
                                <li class="active"><a href="#">Inicio</a></li>
                                <li><a target="_blank" href="http://autoevaluacioninstitucional.unicartagena.edu.co/index.php/contacto">Contacto</a></li>
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
                    <a href="#" class="about"><span>Acerca de</span></a>

                    <a href="#"><span>Derechos de Autor</span></a>

                    <a target="_blank" href="http://autoevaluacioninstitucional.unicartagena.edu.co/index.php/contacto"><span>Ayuda</span></a>
                </div>
            </div>
        </div>
        <div class="ui-layout-center">
            <div style="height: 60%; background-color: #f77f00;">
                <IMG SRC="css/images/torre.png" border=0 style="z-index: 5;height: 95%; position: relative; left: 20%; top: 5%"/>    
                <IMG SRC="css/images/SIA UDEC - LOGO_1.png" border=0 style="z-index: 5;height: 23%; position: relative;right:15%;top: 13%;float: right"/>    
            </div>
            <div class="container-fluid">
                <div class="hero-unit">
                    <div class="row-fluid">
                        <div class="span7">
                            <h2>Autoevaluaci&oacute;n Institucional</h2>
                            <p style="color: inherit;font-size: 18px;font-weight: 200;line-height: 27px;margin-bottom: -8px;text-align: justify;">La Universidad de Cartagena cuenta con el Sistema Institucional de Autoevaluación SIA-UdeC, el cual apoya los procesos de autoevaluación con fines de acreditación institucional.</p>     
                            <div class="span4" style="margin-top: 10px;">
                                <a title="Universidad de Cartagena" href="http://www.unicartagena.edu.co/" target="_blank" ><img src="<%=request.getContextPath()%>/css/LogoUdeC.png"></img></a>
                            </div>
                            <div class="span3">
                                <a title="Autoevaluación Institucional" href="http://autoevaluacioninstitucional.unicartagena.edu.co/" target="_blank" ><img src="<%=request.getContextPath()%>/css/selloAcreditacion.png"></img></a>
                            </div>
                            <div class="span4">
                                <a title="Esoluciones" href="http://www.iesoluciones.com/esoluciones/index.php" target="_blank"><img src="<%=request.getContextPath()%>/css/images/esoluciones.PNG"></img></a>
                            </div>
                        </div>

                        <div class="span5">
                            <div class="carousel" id="home-carousel">
                                <div class="carousel-inner">
                                    <div class="item">
                                        <img alt="Construcci&oacute;n del Modelo" src="<%=request.getContextPath()%>/css/images/modelo.png">
                                            <div class="carousel-caption">
                                                <h4>Construcci&oacute;n del Modelo</h4>
                                                <p>Realice la ponderación de factores y características, el diseño de instrumentos y m&aacute;s.</p>
                                            </div>
                                    </div>
                                    <div class="item">
                                        <img alt="Informes" src="<%=request.getContextPath()%>/css/images/informe.png">
                                            <div class="carousel-caption">
                                                <h4>Analice Resultados</h4>
                                                <p>Realiza el procesamiento y sistematizaci&oacute;n de la informaci&oacute;n recolectada, mostrando informes en tiempo real.</p>
                                            </div>
                                    </div>
                                    <div class="item active">
                                        <img alt="Recolecci&oacute;n" src="<%=request.getContextPath()%>/css/images/recoleccion.png">
                                            <div class="carousel-caption">
                                                <h4>Recopile Informaci&oacute;n</h4>
                                                <p>A traves de encuestas en linea, informaci&oacute;n documental y m&aacute;s</p>
                                            </div>
                                    </div>
                                </div>
                                <a class="carousel-control left" data-slide="prev" href="#home-carousel">&lsaquo;</a>
                                <a class="carousel-control right" data-slide="next" href="#home-carousel">&rsaquo;</a>
                            </div>
                        </div>
                    </div> 



                </div>
            </div>

        </div>
        <div class="modal hide fade" id="myModalAbout">
            <div class="modal-header" style="padding-bottom: 19px; background-color: #282728">
                <a data-dismiss="modal" style="margin-top: 0px; padding: 0px" class="close">×</a>
                <h3></h3>
            </div>
            <div class="modal-body" style="background-color: #282728" align="center">
                <img src="<%=request.getContextPath()%>/css/images/SIA UDEC - LOGO_1.png"></img>
            </div>
            <div style="background-color: #282728;border-top: 0px;border-radius:0;" class="modal-footer">
                <%-- <a class="btn btn-primary" data-dismiss="modal" href="#">Cerrar</a>--%>
                <p style="color: #FFFFFF; text-align: justify">El sistema de autoevaluación institucional es un apoyo

                    fundamental para los procesos de acreditación que debe realizar la Universidad, con

                    el propósito de aportar en forma representativa al aumento en la calidad de la

                    Educación Superior, contribuyendo así al desarrollo del país.</p>
            </div>
        </div>
    </body>
</html>
