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
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/jQuery/dragDrop/fcbklistselection.css" />
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
        <script type="text/javascript" src="<%=request.getContextPath()%>/jQuery/dragDrop/fcbklistselection.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.validate.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.metadata.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.pagination.js"></script>

        <style type="text/css">

            .page_loading {
                background-attachment: scroll;
                background-clip: border-box;
                background-color: #8F8F8F;
                background-image: none;
                background-origin: padding-box;
                background-position: 0 0;
                background-repeat: repeat;
                background-size: auto auto;
                border-bottom-left-radius: 5px;
                border-bottom-right-radius: 5px;
                border-top-left-radius: 5px;
                border-top-right-radius: 5px;
                color: #FFFFFF;
                height: 32px;
                left: 45%;
                line-height: 32px;
                margin-bottom: 0;
                margin-left: -62px;
                margin-right: 0;
                margin-top: -16px;
                padding-bottom: 0;
                padding-left: 10px;
                padding-right: 10px;
                padding-top: 0;
                position: absolute;
                top: 30%;
            }
            .page_loading span {
                color: #FFFFFF;
                font-size: 18px;
                font-weight: bold;
                line-height: 32px;
                font-family: Helvetica,Arial,sans-serif;
            }

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

            .ui-layout-pane-west{
                border-right: 1px solid #BBBBBB; 
            }

        </style>

        <script type="text/javascript">
           
            var myLayout, innerLayout, middleLayout;
            $(document).ready( function() {
                location ="/AutoevaluacionInstitucional/#inicio";
                
                $('.about').click(function(){
                    
                    $('#myModalAbout').modal();
                });


                $("div.ui-layout-center").ajaxStart(function(){
                    $("div.ui-layout-center").append("<div id='contenido'></div>");
                    $("#contenido").hide();
                    $("div.ui-layout-center").append("<div class='page_loading'>"
                        +"<span>Cargando</span>"
                        +"<img src='css/images/loading.gif' style='margin-left:6px;'>"
                        +"</div>");
           
                });
                
                myLayout = $('body').layout({
                    //	enable showOverflow on west-pane so CSS popups will overlap north pane
                    west__size:			270
                    ,   center__paneSelector:  ".ui-layout-center"
                    ,   north__paneClass:    "ui-layout-pane2"
                    //	reference only - these options are NOT required because 'true' is the default
                    ,	closable:				true	// pane can open & close
                    ,	resizable:				false	// when open, pane can be resized 
                    ,	slidable:				false	// when closed, pane can 'slide' open over other panes - closes on mouse-out
                    ,   north__size:            41
                    ,   north__closable:        false
                    ,   north__maxSize:         41    
                    ,   north__slidable:		false	// OVERRIDE the pane-default of 'slidable=true'
                    ,	north__spacing_open:	0		// no resizer-bar when open (zero height)
                    ,	south__resizable:		false	// OVERRIDE the pane-default of 'resizable=true'
                    ,	south__closable:		false	
                    ,	south__spacing_open:	0		// no resizer-bar when open (zero height)
                    ,	west__spacing_open:	0
                    ,	west__spacing_closed:			20
                    ,	west__togglerLength_closed:	35
                    ,	west__togglerAlign_closed:	"top"
                    ,	west__togglerContent_closed:	"<button id='west-open' class='close' style='float:left;margin-left:4px;opacity:1;margin-top:-10px;'>&raquo;</button>"
                    ,	west__togglerTip_closed:	"Mostrar menú"
                    ,	west__togglerTip_open:	        "Ocultar menú"
                    ,   west__onclose_end: function(){ $("#conte").removeClass("span10").addClass("span12")}
                    ,   west__onopen_end: function(){$("#conte").removeClass("span12").addClass("span10")}
                    ,   south__paneClass:               "ui-layout-pane"
                    ,	west__togglerContent_open:      ""	
                    ,	west__minSize:			200
                    ,	west__maxSize:			350
                                                
                     
                });
                myLayout.allowOverflow('north'); 
                // setTimeout( myLayout.resizeAll, 1000 ); /* allow time for browser to re-render with new theme */
                // save selector strings to vars so we don't have to repeat it
                // must prefix paneClass with "body > " to target ONLY the outerLayout panes
                myLayout.addCloseBtn("#west-closer", "west");
		
               
               
               
                
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
                
                    if(hash.indexOf("#editar")!=-1){
                        $(".nav li").removeClass("active");
                        $("ul.nav-list li a").children("i").removeClass("icon-white");
                    }
                    if(hash=="#CerrarSesion"){//CerrarSesion
                        $.ajax({ 
                            type: "POST", 
                            url: "<%=request.getContextPath()%>/ControllerAI?action=CerrarSesion", 
                            beforeSend:function(){
                                $("div.ui-layout-center").hide();  
                            },
                            success: function() 
                            { location = "<%=request.getContextPath()%>/";
                            }
                        });
                                  
                    }//fin CerrarSesion
                    
                    if(hash=="#inicio"){//inicio
                        $("div.ui-layout-center").empty();
                        $("div.ui-layout-center").append(
                        "<div id='conte' class='span10' style='text-align: justify'>"
                            +"<div class='hero-unit'>"
                            +"<h1>Autoevaluaci&oacute;n Institucional!</h1>"
                            +"<p style='font-size: 18px; font-weight: 200; line-height: 27px;'>El consejo Academico aprob&oacute; el Modelo de Autoevaluaci&oacute;n con fines de Acreditaci&oacute;n Institucional de la Universidad de Cartagena; Instrumento de gesti&oacute;n que permite la revision sistematica de los procesos acad&eacute;micos y administrativos para  la elaboracion y puesta en marcha de planes de mejoramiento y de mantenimiento que den respuesta a su politica de calidad.</p>"
                            +"<p><a class='btn btn-primary btn-large' target='_blank' href='http://autoevaluacioninstitucional.unicartagena.edu.co/index.php/autoevaluacion-institucional'>Leer M&aacute;s »</a></p>"
                            +"</div>"
                            +"<div class='row-fluid'>"
                            +"<div class='span4'>"
                            +"<h2>Definici&oacute;n</h2>"
                            +"<p>Para la Institución, la autoevaluación es un instrumento de gestión, que le permite una revisión sistemática de los procesos académicos y administrativos. De esta manera, se propone optimizar y abrir espacios para la elaboración y puesta en marcha de planes de mejoramiento y de mantenimiento que, por supuesto, den respuesta a su política de calidad.</p>"
                            +"<p><a target='_blank' href='http://autoevaluacioninstitucional.unicartagena.edu.co/index.php/autoevaluacion-institucional/definicion' class='btn'>Ver detalles »</a></p>"
                            +"</div><!--/span-->"
                            +"<div class='span4'>"
                            +"<h2>Modelo</h2>"
                            +"<p>El Modelo de Autoevaluación de la Universidad de Cartagena se diseñó siguiendo los lineamientos establecidos por el CNA y  fue articulado a la política de mejoramiento de la calidad de la Institución. De acuerdo a ello, lo constituyen un conjunto de factores, características e indicadores que sirven como instrumento analítico en la valoración de los diversos elementos que intervienen en la operación comprensiva de la calidad de la Institución.</p>"
                            +"<p><a href='http://autoevaluacioninstitucional.unicartagena.edu.co/images/pdf/modelo_de_autoevaluacin_institucional.pdf'>Descargar Modelo de Autoevaluación Institucional</a></p></p>"
                            +"<p><a target='_blank' href='http://autoevaluacioninstitucional.unicartagena.edu.co/index.php/autoevaluacion-institucional/modelo-de-autoevaluacion' class='btn'>Ver detalles »</a></p>"
                            +"</div>"
                            +"<div class='span4'>"
                            +"<h2>Marco Normativo</h2>"
                            +"<p>Los Lineamientos de Autoevaluación para la Acreditación Institucional, el Sistema de Aseguramiento de la Calidad en Colombia, y El Modelo de Autoevaluación Institucional con fines de acreditación de la Universidad de Cartagena, se fundamentan en normas externas y normas internas. </p>"
                            +"<p><a target='_blank' href='http://autoevaluacioninstitucional.unicartagena.edu.co/index.php/autoevaluacion-institucional/marco-normativo' class='btn'>Ver detalles »</a></p>"
                            +"</div><!--/span-->"
                            +"</div><!--/row-->"
                            +"</div>");
                        $("ul.nav-list li").removeClass("active");
                        $(".nav-collapse .nav >li:eq(0)").addClass("active");
                        $("ul.nav-list li").siblings().children("a").children("i").removeClass("icon-white");
                        return false;
                    } //fin inicio
                    
                    
                    if(hash.indexOf("#editarFactor")!=-1 || hash.indexOf("#editarEncuesta")!=-1 || hash.indexOf("#editarPregunta")!=-1
                        || hash.indexOf("#editarCaracteristica")!=-1 || hash.indexOf("#editarIndicador")!=-1){
                        var cual = hash.split("&");
                        var url3 = "<%=request.getContextPath()%>/ControllerCC?action=";
                        url3 = url3.concat(cual[0].substring(1),"CC&id=",cual[1]);
                        $("div.ui-layout-center").empty();
                        $.ajax({ 
                            type: "POST", 
                            url: url3, 
                            success: function(data) 
                            {
                                $("#contenido").append(data);
                                $("#contenido").show(200, function(){
                                    $(".page_loading").hide();
                                })     
                                
                            } //fin success
                        }); //fin del $.ajax
                            
                            
                    }
                   
                    if(hash == "#crearFactor" || hash == "#crearCaracteristica" || hash == "#crearIndicador"
                        || hash == "#crearEncuesta" || hash == "#listarFactores" || hash == "#listarPreguntas"
                        || hash == "#listarEncuestas" || hash == "#listarIndicadores" || hash == "#listarCaracteristicas"
                        || hash == "#crearPregunta" ){
                            
                        var url3 = "<%=request.getContextPath()%>/"+hash;
                        url3 = url3.replace('#', "ControllerCC?action=")+"CC";
                          
                        $("div.ui-layout-center").empty();
                        $.ajax({ 
                            type: "POST", 
                            url: url3, 
                            success: function(data) 
                            {
                                $("#contenido").append(data);
                                $("#contenido").show(200, function(){
                                    $(".page_loading").hide();
                                })     
                                
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
                        <a class="brand" style="padding-bottom: 3px; padding-top: 6px" href="#"><img src="css/images/logoSIA.png"></img>&nbsp;&nbsp;&nbsp; Gesti&oacute;n del modelo</a>

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
                                <li><a target="_blank" href="http://autoevaluacioninstitucional.unicartagena.edu.co/index.php/contacto">Contacto</a></li>
                            </ul>
                        </div><!-- /.nav-collapse -->

                    </div>
                </div><!-- /navbar-inner -->
            </div><!-- /navbar -->

        </div><!--North-->

        <div class="ui-layout-south ui-widget-content"> 
            <div class="contenedor_footer fondo_footer" id="footer">
                <div class="links">
                    <a style="font-weight: normal"><span class="muted">&copy; Universidad de Cartagena 2012</span></a>

                    <a href="#" class="about"><span>Acerca de</span></a>

                    <a target="_blank" href="http://autoevaluacioninstitucional.unicartagena.edu.co/index.php/contacto"><span>Contacto</span></a>

                    <a target="_blank" href="http://autoevaluacioninstitucional.unicartagena.edu.co/index.php/contacto"><span>Ayuda</span></a>
                </div>
            </div>
        </div><!--South-->

        <div class="ui-layout-center">
            <div id="conte" class="span10" style="text-align: justify">
                <div class="hero-unit">
                    <h1>Autoevaluaci&oacute;n Institucional!</h1>
                    <p style='font-size: 18px; font-weight: 200; line-height: 27px;'>El consejo Academico aprob&oacute; el Modelo de Autoevaluaci&oacute;n con fines de Acreditaci&oacute;n Institucional de la Universidad de Cartagena; Instrumento de gesti&oacute;n que permite la revision sistematica de los procesos acad&eacute;micos y administrativos para  la elaboracion y puesta en marcha de planes de mejoramiento y de mantenimiento que den respuesta a su politica de calidad.</p>
                    <p><a class="btn btn-primary btn-large" target="_blank" href="http://autoevaluacioninstitucional.unicartagena.edu.co/index.php/autoevaluacion-institucional">Leer M&aacute;s »</a></p>
                </div>
                <div class="row-fluid">
                    <div class="span4">
                        <h2>Definición</h2>
                        <p>Para la Institución, la autoevaluación es un instrumento de gestión, que le permite una revisión sistemática de los procesos académicos y administrativos. De esta manera, se propone optimizar y abrir espacios para la elaboración y puesta en marcha de planes de mejoramiento y de mantenimiento que, por supuesto, den respuesta a su política de calidad.</p>
                        <p><a target="_blank" href="http://autoevaluacioninstitucional.unicartagena.edu.co/index.php/autoevaluacion-institucional/definicion" class="btn">Ver detalles »</a></p>
                    </div>
                    <div class="span4">
                        <h2>Modelo</h2>
                        <p>El Modelo de Autoevaluación de la Universidad de Cartagena se diseñó siguiendo los lineamientos establecidos por el CNA y  fue articulado a la política de mejoramiento de la calidad de la Institución. De acuerdo a ello, lo constituyen un conjunto de factores, características e indicadores que sirven como instrumento analítico en la valoración de los diversos elementos que intervienen en la operación comprensiva de la calidad de la Institución.</p>
                        <p><a href="http://autoevaluacioninstitucional.unicartagena.edu.co/images/pdf/modelo_de_autoevaluacin_institucional.pdf">Descargar Modelo de Autoevaluación Institucional</a></p>
                        <p><a target="_blank" href="http://autoevaluacioninstitucional.unicartagena.edu.co/index.php/autoevaluacion-institucional/modelo-de-autoevaluacion" class="btn">Ver detalles »</a></p>
                    </div><!--/span-->
                    <div class="span4">
                        <h2>Marco Normativo</h2>
                        <p>Los Lineamientos de Autoevaluación para la Acreditación Institucional, el Sistema de Aseguramiento de la Calidad en Colombia, y El Modelo de Autoevaluación Institucional con fines de acreditación de la Universidad de Cartagena, se fundamentan en normas externas y normas internas. </p>
                        <p><a target="_blank" href="http://autoevaluacioninstitucional.unicartagena.edu.co/index.php/autoevaluacion-institucional/marco-normativo" class="btn">Ver detalles »</a></p>
                    </div><!--/span-->
                </div><!--/row-->
            </div> 
        </div><!--/Center-->

        <div id="ui-layout-west" class="ui-layout-west">
            <div class="ui-layout-content">
                <div id="menu" style="padding: 8px 0pt;" class="well">
                    <ul class="nav nav-list">  
                        <button id="west-closer" class="close">&laquo;</button>
                        <li class="nav-header">Factores</li>
                        <li><a href="#crearFactor"><i class="icon-plus"></i> Crear Factor</a></li>
                        <li><a href="#listarFactores"><i class="icon-th-list"></i> listar Factores</a></li>
                        <li class="divider"></li>
                        <li class="nav-header">Caracteristicas</li>
                        <li><a href="#crearCaracteristica"><i class="icon-plus"></i> Crear Caracteristica</a></li>
                        <li><a href="#listarCaracteristicas"><i class="icon-th-list"></i> listar Caracteristicas</a></li>
                        <li class="divider"></li>
                        <li class="nav-header">Indicadores</li>
                        <li><a href="#crearIndicador"><i class="icon-plus"></i> Crear Indicador</a></li>
                        <li><a href="#listarIndicadores"><i class="icon-th-list"></i> listar Indicadores</a></li>
                        <li class="divider"></li>
                        <li class="nav-header">Preguntas</li>
                        <li><a href="#crearPregunta"><i class="icon-plus"></i> Crear Pregunta</a></li>
                        <li><a href="#listarPreguntas"><i class="icon-th-list"></i> listar Preguntas</a></li>
                        <li class="divider"></li>
                        <li class="nav-header">Encuestas</li>
                        <li><a href="#crearEncuesta"><i class="icon-plus"></i> Crear Encuesta</a></li>
                        <li><a href="#listarEncuestas"><i class="icon-th-list"></i> listar Encuestas</a></li>
                    </ul>
                </div>
            </div>
            <div align="center">
                <%--    <img src="<%=request.getContextPath()%>/css/Captura.png" style="width: 224px; height: 80px"></img>
                  <a title="Esoluciones" href="http://www.iesoluciones.com/esoluciones/index.php" target="_blank"><img src="<%=request.getContextPath()%>/css/images/esoluciones.PNG" style="width: 118px;"></img></a>
                <a title="Universidad de Cartagena" href="http://www.unicartagena.edu.co/" target="_blank"><img src="<%=request.getContextPath()%>/css/LogoUdeC.png" style="width: 236px;"></img></a>
                --%>
                <a title="Autoevaluación Institucional" href="http://autoevaluacioninstitucional.unicartagena.edu.co/" target="_blank"><img src="<%=request.getContextPath()%>/css/selloAcreditacion.png" style="width: 200px;"></img></a>
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
