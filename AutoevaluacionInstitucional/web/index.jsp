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
            RequestDispatcher rd = request.getRequestDispatcher("/ControllerAI?action=indexAI");
            rd.forward(request, response);

        }

    %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="language" content="en" />
        <title>Autoevaluacion Institucional</title>
        <link rel="stylesheet" type="text/css" href="css/layout2.css" />
        <link rel="stylesheet" type="text/css" href="css/header.css" />
        <link rel="stylesheet" type="text/css" href="css/footer.css" />
        <link rel="stylesheet" type="text/css" href="css/menu.css" />
        <link rel="stylesheet" type="text/css" href="css/login.css" />
        <script src="<%=request.getContextPath()%>/bootstrap/js/jquery.js"></script>
        <script type="text/javascript" src="script/jquery-layout.js"></script>
        <script type="text/javascript" src="script/jquery.metadata.js"></script>
        <script type="text/javascript"src="script/jquery.validate.js"></script>


        <style type="text/css">

            .icon_arrow {
                background-image: url("css/images/flecha.png");
            }

            #iniciarSesion {
                background-position: right -12px;
                background-repeat: no-repeat;
                padding-right: 17px;
            }
            #loginReplace{
                background-position: right -85px;
                background-repeat: no-repeat;
                padding-right: 17px;   
            }

            #iniciarSesion:hover{
                background-position: right -50px;

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
            div.active-class2 {


                background: none repeat scroll 0 0 white;
                box-shadow: 0 0 5px rgba(0, 148, 255, 0.7);
                color: black;

            }

            .indicator-down {
                background: url("http://cdn.mediafire.com/images/icons/dropdown-arrow.png") no-repeat scroll center center transparent;
                right: 7px;
                top: 10px;
                height: 8px;
                position: absolute;
                width: 8px;

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
    
       
    
                var validador = $("#form_login1").validate({
                    errorLabelContainer: $("#login_penalty_message"),
                
                    submitHandler: function() {
                        this.timer = setTimeout(function () {
                        
                                
                            $.ajax({
                                url: '/AutoevaluacionInstitucional/loginController',
                                data: 'un='+ $('#login_email').val() +'&pw=' + $('#login_pass').val() +'&tp=' + $('#actual').text(),
                                type: 'post',
                                beforeSend: function(){
                                    $("#login_form").hide();
                                    $("#login_spinner").show();
                                },
                                success: function(msg){
                                   
                                    if(msg == 0) // Message Sent, check and redirect
                                    {                // and direct to the success page
	 
	
                                        //redirect to secure page
                                        document.location='/AutoevaluacionInstitucional/';
	
	 
                                    }
                                    else if(msg == 1)
                                    { 
                                        setTimeout(function (){
                                            $("#login_form").show();
                                            $("#login_spinner").hide();
                                        },500);  
                                     
                                        $("#login_penalty_message").fadeTo(200,0.1,function() //start fading the messagebox
                                        {
                                            //add message and change the class of the box and start fading
                                            $(this).append("<label class='error'>Usuario No Posee Permisos Para Ingresas bajo ese perfil.</label>").fadeTo(900,1).delay(2000).slideUp('fast', function() {
    
                                                $(this).html(""); });
                                        });
	 
                                    }
                                    else if(msg == 2)
                                    { setTimeout(function (){
                                            $("#login_form").show();
                                            $("#login_spinner").hide();
                                        },500);
                                        $("#login_penalty_message").fadeTo(200,0.1,function() //start fading the messagebox
                                        {
                                            $(this).append("<label class='error'>Contraseña Incorrecta.</label>").fadeTo(900,1).delay(2000).slideUp('fast', function() {
    
                                                $(this).html(""); });
                                        });
	 
                                    }
                                    else if(msg == 3)
                                    {setTimeout(function (){
                                            $("#login_form").show();
                                            $("#login_spinner").hide();
                                        },500);
                                        $("#login_penalty_message").fadeTo(200,0.1,function() //start fading the messagebox
                                        {
                                            //add message and change the class of the box and start fading
                                        
                                        
                                            $(this).append("<label class='error'>Usuario No Existe.</label>").fadeTo(900,1).delay(2000).slideUp('fast', function() {
    
                                                $(this).html(""); });
                                        });
                                        
                                    
                                    
                                    
	 
                                    }
                                    else if(msg == 4)
                                    { setTimeout(function (){
                                            $("#login_form").show();
                                            $("#login_spinner").hide();
                                        },500);
                                        $("#login_penalty_message").fadeTo(200,0.1,function() //start fading the messagebox
                                        {
                                            //add message and change the class of the box and start fading
                                            $(this).append("<label class='error'>Formato de Código Incorrecto.</label>").fadeTo(900,1).delay(2000).slideUp('fast', function() {
    
                                                $(this).html(""); });
                                        });
	 
                                    }
                                }
                              
                            });
                        
                           
                            
                         
                                
                        
                          

                        }, 200);
                        return false;
                    }
               
                });
		
	
                $(".cancel").click(function() {
                    validador.resetForm();
                });
        
                $("#iniciarSesion").hover(function(){
                    $("#header_Options a span.header").addClass("hover");   
                },function(){
                    $("#header_Options a span.header").removeClass("hover");
                });
        
                var activeClass = 'dropdown-active';
                var showingDropdown, showingMenu, showingParent;
                var dropdown = $('#iniciarSesion');
                var menu = $(".main_login");
                var parent = $("#main_login");
						
                var hideMenu = function() {
							
                    if(showingDropdown) {
								
                        showingDropdown.removeClass(activeClass);
                        showingMenu.fadeOut("slow");
                        $("#iniciarSesion span").addClass("hoverInactive");					
                        $("#login_penalty_message").css("display","none").html(""); 
                        validador.resetForm();
                        $('#login_email').val("");
                        $('#login_pass').val("");
                     
                    }
                };
                var showMenu = function() {
                               
                    showingDropdown = dropdown.addClass(activeClass);
                    showingMenu = menu.fadeIn("slow");
                    showingParent = parent;
                    $("#iniciarSesion span").removeClass("hoverInactive");					
								
                };
						
						
						
                dropdown.click(function(e) {
                    if ( !dropdown.hasClass(activeClass) ){
                        if(e) e.stopPropagation();
                        if(e) e.preventDefault();
                        showMenu(); 	
                    }
							
						 
                });
                $("#loginReplace").click(function(e) {
                    if(e) e.stopPropagation();
                    if(e) e.preventDefault();
                    hideMenu(); 
						 
                });
								     
                              
                /* hide when clicked outside */
                
                $(document.body).click(function(e) {
							
                    if(showingParent) {
                        var parentElement = showingParent[0];
                        if(!$.contains(parentElement,e.target) || !parentElement == e.target) {
                            hideMenu();
									
                        }
                    }
                });
           
            
            
               
                var myLayout, innerLayout, middleLayout;
                $(document).ready( function() {

                    myLayout = $('body').layout({
                    
                         
                        west__size:			197
                        ,   center__paneSelector:  ".ui-layout-center"
                        ,   north__paneClass:    "ui-layout-pane2"
                        ,   closable:				true	// pane can open & close
                        ,	resizable:				true	// when open, pane can be resized 
                        ,	slidable:				false	// when closed, pane can 'slide' open over other panes - closes on mouse-out

                        ,   north__slidable:		false	// OVERRIDE the pane-default of 'slidable=true'
                        ,	north__spacing_open:	0		// no resizer-bar when open (zero height)
                            
                        ,	south__resizable:		false	// OVERRIDE the pane-default of 'resizable=true'
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
       
           
                      
                var activeClass2 = 'active-class2';
                var showingDropdown2, showingMenu2, showingParent2;
                var dropdown2 = $('#tipo');
                var menu2 = $("#menu2");
                var parent2 = $("#tipo");
						
                
                var showMenu2 = function() {
                               
                    showingDropdown2 = dropdown2.addClass(activeClass2);
                    showingMenu2 = menu2.fadeIn("slow");
                    showingParent2 = parent2;
                    					
								
                };
                var hideMenu2 = function() {
							
                    if(showingDropdown2) {
								
                        showingDropdown2.removeClass(activeClass2);
                        showingMenu2.fadeOut("slow");
                        					
                    }
                };				
						
               
                
                
                $(".option").click(function(e) {
                    var a = $(this).text() + "<span class='indicator-down' id='indicator-down'></span>";
                    $("#actual").html(a);			
						 
                });
                
                dropdown2.click(function(e) {
                    if ( !dropdown2.hasClass(activeClass2) ){
                        if(e) e.stopPropagation();
                        if(e) e.preventDefault();
                        showMenu2(); 	
                    }else{
                        hideMenu2();
                    }				
						 
                });
                
    
                
                 
               
                dropdown2.hover(function(){},function(){ 
							
                    if(showingParent2) {
                                             
                        hideMenu2();
									
                    }
                    
                });	
	

            });
        </script>
    </head>

    <body>
        <div class="ui-layout-north ui-widget-content">
            <div class="fondoHeader" id="header">
                <h1>
                    <a id="logo" href="/#/">
                    </a>
                </h1>
                <div id="Navigation">
                    <a  href="#/">
                        Inicio
                    </a>
                    <a href="#">
                        Contacto
                    </a>
                </div>
                <!--ojo-->
                <div class="right" id="header_Options">
                    <div class="header_Option">
                    </div>
                    <div class="header_Option">
                        <a href="#" id="iniciarSesion" class="icon_arrow">
                            <span class="hoverInactive header">Iniciar Sesion</span>
                        </a>
                        <div class="main_login" name="main_login" id="main_login">
                            <a id="loginReplace" class="icon_arrow" href="#">
                                <span class="label">Iniciar Sesion</span>

                            </a>
                            <div class="tabs">
                                <div id="login_mediafire" class="current" style="padding-left: 20px; padding-top: 10px; padding-bottom: 20px;">

                                    <div id="login_spinner" style="margin-left: -19px; padding: 82px 0pt; display: none;">
                                        <div > 
                                            <p style="margin:20px; text-align:center">
                                                <img src="http://cdn.mediafire.com/images/icons/ajax-loader-grey_round.gif"></p> 
                                            <p style="text-align:center">Iniciando Sesi&oacute;n ...</p> 
                                        </div> 
                                    </div>

                                    <div id="login_form">
                                        <p id="login_penalty_message" style="display: none; margin: 10px 20px 10px 0pt; padding: 5px;" class="soc_error"></p>
                                        <form  action="" method="post" id="form_login1" name="form_login1">
                                            <fieldset>
                                                <label>C&oacute;digo: </label>
                                                <input type="text" id="login_email" name="usuario" class="{required:true,number:true,maxlength:10,messages:{number:'Por favor ingrese un c&oacutedigo v&aacutelido',required:'El campo c&oacutedigo es requerido',maxlength:'El campo c&oacutedigo no admite m&aacutes de 10 numeros'}}"/>
                                                <label>Contrase&ntilde;a: </label>
                                                <input type="password" autocomplete="off" id="login_pass" name="password" class="{required:true,messages:{required:'El campo contrase&ntildea es requerido'}}"/>
                                                <a class="forgot-pwd" href="#">
                                                    olvid&oacute; su contrase&ntilde;a?
                                                </a>
                                                <label>Tipo: </label>
                                                <div class="dropdown" id="tipo">
                                                    <a href="#" id="actual" >Autoevaluacion Institucional<span class="indicator-down" id="indicator-down"></span></a>
                                                    <ul style="left:auto;right:0;" id="menu2">
                                                        <li class="option">Autoevaluacion Institucional</li> 
                                                        <li class="option">Comite Central</li> 
                                                        <li class="option">Otros</li> 
                                                    </ul>
                                                </div>


                                                <input type="submit" value="Ingresar" name="submit_login" id="submit_login"/>
                                                <div id="login_secure_header_link">
                                                </div>
                                            </fieldset>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--ojo-->

            </div>

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
            <DIV STYLE="visibility: visible; width: 100%; height: 100%; left: 0px; top: -231px; z-index:2">
                <IMG STYLE="visibility: visible; width: 100%; height: 100%; left: 0px; top: -231px; z-index:2" SRC="css/images/stage_background.jpg" border=0>
            </DIV> 
            <DIV STYLE=" position: absolute;
                 position: absolute;
                 left: 50%;
                 top: 45%;
                 height: 350px;
                 margin-top: -175px;    
                 width: 350px;
                 margin-left: -175px;    
                 visibility:visible;
                 z-index:2">
                <IMG SRC="css/images/seal.gif" border=0>
                    <p style="color: #000; font-weight: bolder; font-size: 18px; text-align:center;" >Sistema de Autoevaluación Institucional
                        Universidad de Cartagena</p>
            </DIV>  
        </div>
    </body>
</html>
