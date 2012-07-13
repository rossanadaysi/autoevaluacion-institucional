<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" 
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
        <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-transition.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-dropdown.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-scrollspy.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-alert.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-modal.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-tooltip.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-popover.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-collapse.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-button.js"></script>

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
            .middle-center, .inner-center{
                padding: 0px;   

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


        </style>

        <script type="text/javascript">
           
            var myLayout, innerLayout, middleLayout;
            $(document).ready( function() {
                location ="/AutoevaluacionInstitucional/#inicio";
                                    
                myLayout = $('body').layout({
                    //	enable showOverflow on west-pane so CSS popups will overlap north pane
                    center__paneSelector:  ".ui-layout-center"
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
                    
                                                
                     
                });
                
                myLayout.allowOverflow('north'); 
                // setTimeout( myLayout.resizeAll, 1000 ); /* allow time for browser to re-render with new theme */
               
               
               
               
                
            }); //fin de document.ready
                
           
        </script>
        <script type="text/javascript">
            $(function(){
                location ="/AutoevaluacionInstitucional/#inicio"; 
                
                $(window).hashchange(function(){
                    
                    var hash = location.hash;
                
                    if(hash=="#CerrarSesion"){
                        $.post('<%=request.getContextPath()%>/ControllerCC?action=CerrarSesion', function(data) {
                            location = "<%=request.getContextPath()%>/";
                                 
                        });//fin post
                                                        
                    }
                    
                    if(hash=="#inicio"){
                        $("div.ui-layout-center").empty();
                        $.ajax({ 
                            type: "POST", 
                            url: "<%=request.getContextPath()%>/ControllerF?action=inicio", 
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
                    
                    if(hash.indexOf("#responderEncuesta")!=-1){
                        var url3 = "<%=request.getContextPath()%>/"+hash;
                        url3 = url3.replace('#responderEncuesta', "ControllerF?action=responderEnc");
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
                        <a class="brand" style="padding-bottom: 3px; padding-top: 6px" href="#"><img src="css/images/logoSIA.png"></img>&nbsp;&nbsp;&nbsp; Recolecci&oacute;n de informaci&oacute;n</a>

                        <div class="btn-group pull-right">
                            <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                                <i class="icon-user"></i> ${persona.nombre}
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
            <div class="container">  
                <div class="row">
                    <div class="span8">
                        <br/>
                        <h2>Listado de  Encuestas Disponibles</h2>
                        <c:choose>
                            <c:when test="${listaEncuestasDisponibles.getRowCount()>0}">

                                <table class="table table-striped table-bordered table-condensed">
                                    <thead>
                                        <th>Encuesta</th>
                                        <th>Programa</th>
                                        <th></th>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listaEncuestasDisponibles.rowsByIndex}" var="item" varStatus="iter">
                                            <tr>    
                                                <td>   
                                                    <c:out value="${item[1]}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${proceso.programaId.nombre}"/>
                                                </td>
                                                <td class="action">
                                                    <a title="Responder Encuesta" href="#responderEncuesta&${item[0]}">Responder encuesta</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>
                                No Existen Encuestas Disponibles.
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div><!--/Center-->
    </body>
</html> 
