<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" 
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="language" content="en" />
        <title>Autoevaluacion Institucional</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/layout2.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/footer.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui-1.8.5.custom.css" type="text/css" media="screen" charset="utf-8" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/jquery.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery-layout.js"></script>
        <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <!-- Le styles -->
        <link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap3.css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-responsive3.css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/bootstrap/css/docs2.css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/bootstrap/js/google-code-prettify/prettify.css" rel="stylesheet"/>



        <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery-ui.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery-layout.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.ba-hashchange.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/google-code-prettify/prettify.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-dropdown.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-scrollspy.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-alert.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-modal.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-tooltip.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-popover.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-collapse.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-button.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-transition.js"></script>

        <style type="text/css">
            .ui-layout-north {
                /* Drop-Down */
                bottom:		auto;
                margin:		0;
                padding-bottom: 30px;
            }

            .ui-layout-center{
                overflow: auto;
            }
            .ui-layout-west{
                padding-right: 0;
            }
            .inner-layout-north {
                /* Drop-Down */
                bottom:		auto;
                margin:		0;

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

            .middle-center, .inner-center{
                padding: 0px;   

            }


        </style>

        <script type="text/javascript">
           
            var myLayout, innerLayout, middleLayout;
            $(document).ready( function() {
                location ="/AutoevaluacionInstitucional/#inicio";
                                    
                myLayout = $('body').layout({
                    //	enable showOverflow on west-pane so CSS popups will overlap north pane
                    west__size:			270
                    ,   center__paneSelector:  ".ui-layout-center"
                    ,   north__paneClass:    "ui-layout-pane2"
                    ,   closable:				true	// pane can open & close


                    //	reference only - these options are NOT required because 'true' is the default
                    ,	closable:				true	// pane can open & close
                    ,	resizable:				false	// when open, pane can be resized 
                    ,	slidable:				false	// when closed, pane can 'slide' open over other panes - closes on mouse-out
                    ,   north__size:            41
                    ,   north__maxSize:         41    
                    ,   north__slidable:		false	// OVERRIDE the pane-default of 'slidable=true'
                    ,	north__spacing_open:	0		// no resizer-bar when open (zero height)
                    ,	south__resizable:		false	// OVERRIDE the pane-default of 'resizable=true'
                    ,	south__spacing_open:	0		// no resizer-bar when open (zero height)
                    ,	west__spacing_open:	1		// no resizer-bar when open (zero height)
                    ,       south__paneClass:               "ui-layout-pane"
                  	
                    ,	west__minSize:			200
                    ,	west__maxSize:			350
                                                
                     
                });
                
                myLayout.allowOverflow('north'); 
                // setTimeout( myLayout.resizeAll, 1000 ); /* allow time for browser to re-render with new theme */
               
               
               
               
                
            }); //fin de document.ready
                
           
        </script>
        <script type="text/javascript">
            $(function(){
                
                 $("ul.nav-list li a").click(function(event){
                    $(".nav li").removeClass("active");
                    $("ul.nav-list li a").children("i").removeClass("icon-white");
                    $(this).parent().addClass("active");
                    $(this).children("i").addClass("icon-white");
                    location = $(this).attr("href");
                })
            
                $(window).hashchange(function(){
                    var hash = location.hash;
                
                    if(hash=="#CerrarSesion"){
                        $.post('<%=request.getContextPath()%>/ControllerCC?action=CerrarSesion', function(data) {
                            location = "<%=request.getContextPath()%>/";
                                 
                        });//fin post
                                                        
                    }
                    
                    if(hash=="#inicio"){//inicio
                            $("div.ui-layout-center").empty();
                            $("div.ui-layout-center").append(
                            "<div class='span10' style='text-align: justify'>"
                                +"<div class='hero-unit'>"
                                +"<h1>Autoevaluaci&oacute;n Institucional!</h1>"
                                +"<p>El consejo Academico aprob&oacute; el Modelo de Autoevaluaci&oacute;n con fines de Acreditaci&oacute;n Institucional de la Universidad de Cartagena; Instrumento de gesti&oacute;n que permite la revision sistematica de los procesos acad&eacute;micos y administrativos para  la elaboracion y puesta en marcha de planes de mejoramiento y de mantenimiento que den respuesta a su politica de calidad.</p>"
                                +"<p><a class='btn btn-primary btn-large'>Leer M&aacute;s »</a></p>"
                                +"</div>"
                                +"<div class='row-fluid'>"
                                +"<div class='span4'>"
                                +"<h2>Marco Normativo</h2>"
                                +"<p>los lineamientos de Autoevaluaci&oacute;n para la Acreditaci&oacute;n Institucional, el Sistema de Aseguramiento de la Calidad en Colombia, y el Modelo de Autoevaluacion Institucional con fines de acreditacion de la  Universidad de Cartagena se fundamentan en.. . </p>"
                                +"<p><a href='#' class='btn'>Ver detalles »</a></p>"
                                +"</div><!--/span-->"
                                +"<div class='span4'>"
                                +"<h2>Definiciones</h2>"
                                +"<p>lorem ipsum id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>"
                                +"<p><a href='#' class='btn'>Ver detalles »</a></p>"
                                +"</div>"
                                +"<div class='span4'>"
                                +"<h2>Objetivos</h2>"
                                +"<p>Mantener informado a la comunidad academica sobre el proceso de autoevaluacion institucional. </p>"
                                +"<p><a href='#' class='btn'>Ver detalles »</a></p>"
                                +"</div><!--/span-->"
                                +"</div><!--/row-->"
                                +"</div>");
                            $("ul.nav-list li").removeClass("active");
                            $(".nav-collapse .nav >li:eq(0)").addClass("active");
                            $("ul.nav-list li").siblings().children("a").children("i").removeClass("icon-white");
                            return false;
                        } //fin inicio
                    
                    if(hash == "#crearFactor"){
                            
                        var url3 = "<%=request.getContextPath()%>/"+hash;
                        url3 = url3.replace('#', "ControllerCC?action=")+"CC";
                          
                        $("div.ui-layout-center").empty();
                        $.ajax({ 
                            type: "POST", 
                            url: url3, 
                            beforeSend :function(){
                                $("div.ui-layout-center").hide();
                            },
                            success: function(data) 
                            {
                                $("div.ui-layout-center").append(data);
                                $("div.ui-layout-center").show(100);
                                        
                            } //fin success
                        }); //fin del $.ajax
                         
                    }
                    if(hash.indexOf("#editarFactor")!=-1){
                        var url3 = "<%=request.getContextPath()%>/"+hash;
                        url3 = url3.replace('#editarFactor', "ControllerCC?action=editarFactorCC");
                        url3 = url3.replace('&', "&idF=");
                            
                        $("div.ui-layout-center").empty();
                        $.ajax({ 
                            type: "POST", 
                            url: url3, 
                            beforeSend :function(){
                                $("div.ui-layout-center").hide();
                            },
                            success: function(data) 
                            {
                                $("div.ui-layout-center").append(data);
                                $("div.ui-layout-center").show(100);
                                        
                            } //fin success
                        }); //fin del $.ajax
                            
                            
                    }
                   
                   
                   
                    if(hash.indexOf("#editarEncuesta")!=-1){
                        var url3 = "<%=request.getContextPath()%>/"+hash;
                        url3 = url3.replace('#editarEncuesta', "ControllerCC?action=editarEncuestaCC");
                        url3 = url3.replace('&', "&idE=");
                            
                        $("div.ui-layout-center").empty();
                        $.ajax({ 
                            type: "POST", 
                            url: url3, 
                            beforeSend :function(){
                                $("div.ui-layout-center").hide();
                            },
                            success: function(data) 
                            {
                                $("div.ui-layout-center").append(data);
                                $("div.ui-layout-center").show(100);
                                        
                            } //fin success
                        }); //fin del $.ajax
                            
                            
                    }
                   
                   
                    if(hash.indexOf("#editarPregunta")!=-1){
                        var url3 = "<%=request.getContextPath()%>/"+hash;
                        url3 = url3.replace('#editarPregunta', "ControllerCC?action=editarPreguntaCC");
                        url3 = url3.replace('&', "&idP=");
                            
                        $("div.ui-layout-center").empty();
                        $.ajax({ 
                            type: "POST", 
                            url: url3, 
                            beforeSend :function(){
                                $("div.ui-layout-center").hide();
                            },
                            success: function(data) 
                            {
                                $("div.ui-layout-center").append(data);
                                $("div.ui-layout-center").show(100);
                                        
                            } //fin success
                        }); //fin del $.ajax
                            
                            
                    }
                   
                                      
                    if(hash == "#crearCaracteristica"){
                            
                        var url3 = "<%=request.getContextPath()%>/"+hash;
                        url3 = url3.replace('#', "ControllerCC?action=")+"CC";
                        $("div.ui-layout-center").empty();
                        $.ajax({ 
                            type: "POST", 
                            url: url3, 
                            beforeSend :function(){
                                $("div.ui-layout-center").hide();
                            },
                            success: function(data) 
                            {
                                $("div.ui-layout-center").append(data);
                                $("div.ui-layout-center").show(100);
                                        
                            } //fin success
                        }); //fin del $.ajax
                         
                    }
                    if(hash.indexOf("#editarCaracteristica")!=-1){
                        var url3 = "<%=request.getContextPath()%>/"+hash;
                        url3 = url3.replace('#editarCaracteristica', "ControllerCC?action=editarCaracteristicaCC");
                        url3 = url3.replace('&', "&idC=");
                            
                        $("div.ui-layout-center").empty();
                        $.ajax({ 
                            type: "POST", 
                            url: url3, 
                            beforeSend :function(){
                                $("div.ui-layout-center").hide();
                            },
                            success: function(data) 
                            {
                                $("div.ui-layout-center").append(data);
                                $("div.ui-layout-center").show(100);
                                        
                            } //fin success
                        }); //fin del $.ajax
                            
                    }
                    
                    if(hash.indexOf("#editarIndicador")!=-1){
                        var url3 = "<%=request.getContextPath()%>/"+hash;
                        url3 = url3.replace('#editarIndicador', "ControllerCC?action=editarIndicadorCC");
                        url3 = url3.replace('&', "&idI=");
                            
                        $("div.ui-layout-center").empty();
                        $.ajax({ 
                            type: "POST", 
                            url: url3, 
                            beforeSend :function(){
                                $("div.ui-layout-center").hide();
                            },
                            success: function(data) 
                            {
                                $("div.ui-layout-center").append(data);
                                $("div.ui-layout-center").show(100);
                                        
                            } //fin success
                        }); //fin del $.ajax
                            
                    }
                    
                    if(hash == "#crearIndicador"){
                            
                        var url3 = "<%=request.getContextPath()%>/"+hash;
                        url3 = url3.replace('#', "ControllerCC?action=")+"CC";
                        $("div.ui-layout-center").empty();
                        $.ajax({ 
                            type: "POST", 
                            url: url3, 
                            success: function(data) 
                            {
                                $("div.ui-layout-center").append(data);
                                        
                            } //fin success
                        }); //fin del $.ajax
                         
                    }
                    if(hash == "#crearEncuesta"){
                            
                        var url3 = "<%=request.getContextPath()%>/"+hash;
                        url3 = url3.replace('#', "ControllerCC?action=")+"CC";
                        $("div.ui-layout-center").empty();
                        $.ajax({ 
                            type: "POST", 
                            url: url3, 
                            success: function(data) 
                            {
                                $("div.ui-layout-center").append(data);
                                        
                            } //fin success
                        }); //fin del $.ajax
                         
                    }
                    if(hash == "#listarFactores"){
                            
                        var url3 = "<%=request.getContextPath()%>/"+hash;
                        url3 = url3.replace('#', "ControllerCC?action=")+"CC";
                        $("div.ui-layout-center").empty();
                        $.ajax({ 
                            type: "POST", 
                            url: url3, 
                            success: function(data) 
                            {
                                $("div.ui-layout-center").html(data);
                                        
                            } //fin success
                        }); //fin del $.ajax
                         
                    }
                    if(hash == "#listarPreguntas"){
                            
                        var url3 = "<%=request.getContextPath()%>/"+hash;
                        url3 = url3.replace('#', "ControllerCC?action=")+"CC";
                        $("div.ui-layout-center").empty();
                        $.ajax({ 
                            type: "POST", 
                            url: url3, 
                            success: function(data) 
                            {
                                $("div.ui-layout-center").append(data);
                                        
                            } //fin success
                        }); //fin del $.ajax
                         
                    }
                    if(hash == "#listarEncuestas"){
                            
                        var url3 = "<%=request.getContextPath()%>/"+hash;
                        url3 = url3.replace('#', "ControllerCC?action=")+"CC";
                        $("div.ui-layout-center").empty();
                        $.ajax({ 
                            type: "POST", 
                            url: url3, 
                            success: function(data) 
                            {
                                $("div.ui-layout-center").append(data);
                                        
                            } //fin success
                        }); //fin del $.ajax
                         
                    }
                    if(hash == "#listarIndicadores"){
                            
                        var url3 = "<%=request.getContextPath()%>/"+hash;
                        url3 = url3.replace('#', "ControllerCC?action=")+"CC";
                        $("div.ui-layout-center").empty();
                        $.ajax({ 
                            type: "POST", 
                            url: url3, 
                            success: function(data) 
                            {
                                $("div.ui-layout-center").append(data);
                                        
                            } //fin success
                        }); //fin del $.ajax
                         
                    }
                    if(hash == "#listarCaracteristicas"){
                            
                        var url3 = "<%=request.getContextPath()%>/"+hash;
                        url3 = url3.replace('#', "ControllerCC?action=")+"CC";
                        $("div.ui-layout-center").empty();
                        $.ajax({ 
                            type: "POST", 
                            url: url3, 
                            success: function(data) 
                            {
                                $("div.ui-layout-center").append(data);
                                        
                            } //fin success
                        }); //fin del $.ajax
                         
                    }
                    if(hash == "#crearPregunta"){
                            
                        var url3 = "<%=request.getContextPath()%>/"+hash;
                        url3 = url3.replace('#', "ControllerCC?action=")+"CC";
                        $("div.ui-layout-center").empty();
                        $.ajax({ 
                            type: "POST", 
                            url: url3, 
                            success: function(data) 
                            {
                                $("div.ui-layout-center").append(data);
                                        
                            } //fin success
                        }); //fin del $.ajax
                         
                    }
                
                });//fin hashchange
            });//fin function
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
                                <i class="icon-user"></i> ${representante.personaId.nombre}
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Perfil</a></li>
                                <li><a href="#">Cambiar Contrase&ntilde;a</a></li>
                                <li class="divider"></li>
                                <li><a href="<%=request.getContextPath()%>/#CerrarSesion">Cerrar Sesion</a></li>
                            </ul>
                        </div>
                        <div class="nav-collapse">
                            <ul class="nav">
                                <li class="active"><a href="<%=request.getContextPath()%>/#inicio">Inicio</a></li>
                                <li><a href="#">Contacto</a></li>
                            </ul>
                        </div><!-- /.nav-collapse -->

                    </div>
                </div><!-- /navbar-inner -->
            </div><!-- /navbar -->

        </div><!--North-->

        <div class="ui-layout-south ui-widget-content"> 
            <div class="contenedor_footer fondo_footer" id="footer">
                <div class="links">
                    <a href="#"><span>Acerca de</span></a>

                    <a href="#"><span>Derechos de Autor</span></a>

                    <a href="#"><span>Ayuda</span></a>
                </div>
            </div>
        </div><!--South-->

        <div class="ui-layout-center">


        </div><!--/Center-->

        <div id="ui-layout-west" class="ui-layout-west">
            <div class="ui-layout-content">
                <div id="menu" style="padding: 8px 0pt;" class="well">
                    <ul class="nav nav-list">  
                        <li class="nav-header">Modelo de Autoevaluaci&oacute;n</li>

                        <div id="accordion1" class="accordion">
                            <div class="accordion-group">
                                <div class="accordion-heading">
                                    <a href="#collapseOne" data-parent="#accordion1" data-toggle="collapse" class="accordion-toggle">
                                        Factores
                                    </a>
                                </div>
                                <div class="accordion-body collapse" id="collapseOne" style="height: 0px;">
                                    <div class="accordion-inner">
                                        <li><a href="#crearFactor"><i class="icon-plus"></i> Crear Factor</a></li>
                                        <li><a href="#listarFactores"><i class="icon-th-list"></i> listar Factores</a></li>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="accordion2" class="accordion">
                            <div class="accordion-group">
                                <div class="accordion-heading">
                                    <a href="#collapseCaracteristicas" data-parent="#accordion2" data-toggle="collapse" class="accordion-toggle">
                                        Caracteristicas
                                    </a>
                                </div>
                                <div class="accordion-body collapse" id="collapseCaracteristicas" style="height: 0px;">
                                    <div class="accordion-inner">
                                        <li><a href="#crearCaracteristica"><i class="icon-plus"></i> Crear Caracteristica</a></li>
                                        <li><a href="#listarCaracteristicas"><i class="icon-th-list"></i> listar Caracteristicas</a></li>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="accordion3" class="accordion">
                            <div class="accordion-group">
                                <div class="accordion-heading">
                                    <a href="#collapseIndicadores" data-parent="#accordion3" data-toggle="collapse" class="accordion-toggle">
                                        Indicadores
                                    </a>
                                </div>
                                <div class="accordion-body collapse" id="collapseIndicadores" style="height: 0px;">
                                    <div class="accordion-inner">
                                        <li><a href="#crearIndicador"><i class="icon-plus"></i> Crear Indicador</a></li>
                                        <li><a href="#listarIndicadores"><i class="icon-th-list"></i> listar Indicadores</a></li>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="accordion4" class="accordion">
                            <div class="accordion-group">
                                <div class="accordion-heading">
                                    <a href="#collapsePreguntas" data-parent="#accordion4" data-toggle="collapse" class="accordion-toggle">
                                        Preguntas
                                    </a>
                                </div>
                                <div class="accordion-body collapse" id="collapsePreguntas" style="height: 0px;">
                                    <div class="accordion-inner">
                                        <li><a href="#crearPregunta"><i class="icon-plus"></i> Crear Pregunta</a></li>
                                        <li><a href="#listarPreguntas"><i class="icon-th-list"></i> listar Preguntas</a></li>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="accordion5" class="accordion">
                            <div class="accordion-group">
                                <div class="accordion-heading">
                                    <a href="#collapseEncuestas" data-parent="#accordion5" data-toggle="collapse" class="accordion-toggle">
                                        Encuestas
                                    </a>
                                </div>
                                <div class="accordion-body collapse" id="collapseEncuestas" style="height: 0px;">
                                    <div class="accordion-inner">
                                        <li><a href="#crearEncuesta"><i class="icon-plus"></i> Crear Encuesta</a></li>
                                        <li><a href="#listarEncuestas"><i class="icon-th-list"></i> listar Encuestas</a></li>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </ul>
                </div>
            </div>
        </div><!--/West-->

        <div class="modal hide fade" id="myModalElimF">
            <div class="modal-body">
                ¿Esta usted seguro que desea eliminar este factor?
            </div>
            <div class="modal-footer">
                <a class="btn btn-primary" data-dismiss="modal" href="#eliminarFactor">Si</a>
                <a class="btn" data-dismiss="modal" href="#">Cancelar</a>
            </div>
        </div><!--/ModalElimF-->
        <div class="modal hide fade" id="myModalElimC">
            <div class="modal-body">
                ¿Esta usted seguro que desea eliminar esta caracteristica?
            </div>
            <div class="modal-footer">
                <a class="btn btn-primary" data-dismiss="modal" href="#eliminarCaracteristica">Si</a>
                <a class="btn" data-dismiss="modal" href="#">Cancelar</a>
            </div>
        </div><!--/ModalElimC-->
        <div class="modal hide fade" id="myModalElimI">
            <div class="modal-body">
                ¿Esta usted seguro que desea eliminar este indicador?
            </div>
            <div class="modal-footer">
                <a class="btn btn-primary" data-dismiss="modal" href="#eliminarIndicador">Si</a>
                <a class="btn" data-dismiss="modal" href="#">Cancelar</a>
            </div>
        </div><!--/ModalElimI-->
        <div class="modal hide fade" id="myModalElimP">
            <div class="modal-body">
                ¿Esta usted seguro que desea eliminar esta pregunta?
            </div>
            <div class="modal-footer">
                <a class="btn btn-primary" data-dismiss="modal" href="#eliminarPregunta">Si</a>
                <a class="btn" data-dismiss="modal" href="#">Cancelar</a>
            </div>
        </div><!--/ModalElimP-->
    </body>
</html> 
