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




        <script src="<%=request.getContextPath()%>/bootstrap/js/jquery.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery-layout.js"></script>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/slick.grid.css" type="text/css" media="screen" charset="utf-8" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/slick.pager.css" type="text/css" media="screen" charset="utf-8" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui-1.8.5.custom.css" type="text/css" media="screen" charset="utf-8" />
        <!--<link rel="stylesheet" href="<%=request.getContextPath()%>/css/examples.css" type="text/css" media="screen" charset="utf-8" />-->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/slick.columnpicker.css" type="text/css" media="screen" charset="utf-8" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/slick-default-theme.css" type="text/css" media="screen" charset="utf-8" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styl.css" type="text/css" media="screen" charset="utf-8" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css" media="screen" charset="utf-8" />


        <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <!-- Le styles -->
        <link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css" rel="stylesheet">
            <link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
                <link href="<%=request.getContextPath()%>/bootstrap/css/docs.css" rel="stylesheet">
                    <link href="<%=request.getContextPath()%>/bootstrap/js/google-code-prettify/prettify.css" rel="stylesheet">

                        <!-- Le fav and touch icons -->
                        <link rel="apple-touch-icon" href="<%=request.getContextPath()%>/bootstrap/ico/apple-touch-icon.png">
                            <link rel="apple-touch-icon" sizes="72x72" href="<%=request.getContextPath()%>/bootstrap/ico/apple-touch-icon-72x72.png">
                                <link rel="apple-touch-icon" sizes="114x114" href="<%=request.getContextPath()%>/bootstrap/ico/apple-touch-icon-114x114.png">


                                    <script language="JavaScript" src="<%=request.getContextPath()%>/script/slick.model.js"></script>

                                    <script language="JavaScript" src="<%=request.getContextPath()%>/script/jquery.event.drag-2.0.min.js"></script>
                                    <script language="JavaScript" src="<%=request.getContextPath()%>/script/slick.editors.js"></script>
                                    <script language="JavaScript" src="<%=request.getContextPath()%>/script/slick.grid.js"></script>

                                    <script language="JavaScript" src="<%=request.getContextPath()%>/script/slick.pager.js"></script>
                                    <script language="JavaScript" src="<%=request.getContextPath()%>/script/slick.columnpicker.js"></script>
                                    <script language="JavaScript" src="<%=request.getContextPath()%>/script/slick.remotemodel.js"></script>
                                    <script language="JavaScript" src="<%=request.getContextPath()%>/script/jjmenu.js"></script>
                                    <script language="JavaScript" src="<%=request.getContextPath()%>/script/pubsub.js"></script>
                                    <script language="JavaScript" src="<%=request.getContextPath()%>/script/jquery.ba-hashchange.js"></script>
                                    <script language="JavaScript" src="<%=request.getContextPath()%>/script/jquery-ui.js"></script>
                                    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery-layout.js"></script>
                                    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.hotkeys.js"></script>
                                    <script src="<%=request.getContextPath()%>/bootstrap/js/google-code-prettify/prettify.js"></script>
                                    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-transition.js"></script>
                                    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-dropdown.js"></script>
                                    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-scrollspy.js"></script>
                                    <style type="text/css">

                                         .slick-cell .options a {
                                            cursor: pointer;
                                            display: block;
                                            float: left;
                                            height: 18px;
                                            width: 18px;
                                        }

                                        .options {
                                            background: none repeat scroll 0 0 transparent;
                                            float: right;
                                            height: 18px;
                                            margin: 2px;
                                            position: relative;
                                            width: auto !important;
                                            z-index: 1;
                                        }

                                        a.more {
                                            visibility: hidden;
                                        }

                                        .slick-row:hover a.more {
                                            visibility: visible;
                                            background-position: 0 -54px;
                                        }   
                                        .slick-row:hover a.more:hover {
                                            background-position: -18px -54px;
                                        }

                                        div.selected:hover a.more, div.selected.hover a.more {
                                            background-position: 0 -198px;
                                            visibility: visible;
                                        }
                                        .grid-canvas div.selected .slick-cell a.more:hover, .grid-canvas div.selected .slick-cell a.hover  {
                                            background-position: -36px -198px;
                                        }


                                        .grid-canvas .slick-cell .rowOption {
                                            background: url("/AutoevaluacionInstitucional/css/images/songRow_18px.png") repeat scroll 0 0 transparent;
                                        }




                                        #page_header .meta h3 .gridCount {
                                            color: #999999;
                                            font-size: 12px;
                                            letter-spacing: -0.5px;
                                            line-height: 22px;
                                            margin-left: 5px;
                                        }
                                        #page_header .meta {
                                            background: none repeat scroll 0 0 transparent;
                                            float: left;
                                            margin: 10px 15px 10px 10px;
                                        }
                                        #page_header .meta h3 {
                                            color: #333333;
                                            font-size: 16px;
                                            font-weight: bold;
                                            letter-spacing: -0.5px;
                                            line-height: 22px;
                                            margin: 0;
                                            max-width: 320px;
                                        }

                                        #page_header {
                                            background: url("css/images/page_header_bg.png") repeat-x scroll left bottom #E9E9E9;
                                            position: relative;
                                            width: 100%;
                                            height: 100%;

                                        }
                                        #page_search {
                                            background: none repeat scroll 0 0 #FFFFFF;
                                            border: 1px solid #CCCCCC;
                                            border-radius: 20px 20px 20px 20px;
                                            display: block;
                                            height: 22px;
                                            margin: 0;
                                            position: relative;
                                            width: 150px;
                                            z-index: 9999;
                                        }

                                        .page_options {
                                            float: right;
                                            height: 22px;
                                            padding: 9px 10px 10px 0;
                                            text-align: right;
                                        }

                                        #page_search input {
                                            background: none repeat scroll 0 0 transparent;
                                            border: medium none;
                                            color: #999999;
                                            font-size: 11px;
                                            left: 9px;
                                            line-height: 14px;
                                            margin: 0;
                                            position: absolute;
                                            top: 3px;
                                            width: 116px;
                                        }

                                        #page_search a.remove {
                                            background: url("css/images/iconos.png") no-repeat scroll center center transparent;
                                            background-position: -81px 0;
                                            height: 19px;
                                            width: 22px;
                                            position: absolute;
                                            right: 3px;
                                            top: 1px;
                                        }



                                        .cell-effort-driven {
                                            text-align: center;

                                        }

                                        .cell-selection {
                                            border-right-color: silver;
                                            border-right-style: solid;
                                            background: #f5f5f5;
                                            color: gray;
                                            text-align: right;
                                            font-size: 10px;
                                        }

                                        .slick-row.selected .cell-selection {
                                            background-color: transparent; /* show default selected row background */
                                        }
                                    </style>
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


                                    </style>
                                    <script type="text/javascript">
                    
            
            
                                        $(document).ready(function() {
               
                                            $("ul.nav-list li a").click(function(event){
                                                $(this).parent().siblings().removeClass("active");
                                                $(this).parent().siblings().children("a").children("i").removeClass("icon-white");
            
            
                                                $(this).parent().addClass("active");
                                                $(this).children("i").addClass("icon-white");
            
                                            })
                                        
                                            $(".ui-layout-center").scroll(function(){
                                                // If has not activated (has no attribute "data-top"
                                                if (!$('.subnav').attr('data-top')) {
                                                    // If already fixed, then do nothing
                                                    if ($('.subnav').hasClass('subnav-fixed')) return;
                                                    // Remember top position
                                                    var offset = $('.subnav').offset()
                                                    $('.subnav').attr('data-top', offset.top);
                                                }

                                                if ($('.subnav').attr('data-top') - $('.subnav').outerHeight() <= $(this).scrollTop())
                                                    $('.subnav').addClass('subnav-fixed');
                                                else
                                                    $('.subnav').removeClass('subnav-fixed');
                                            });
            
                                        });
                
                                    </script>
                                    <script type="text/javascript">
           
                                        var myLayout, innerLayout, middleLayout;
                                        $(document).ready( function() {
                                    
                                            myLayout = $('body').layout({
                                                //	enable showOverflow on west-pane so CSS popups will overlap north pane
                                                west__size:			270
                                                ,   center__paneSelector:  ".ui-layout-center"
                                                ,   north__paneClass:    "ui-layout-pane"
                                                ,   closable:				true	// pane can open & close


                                                //	reference only - these options are NOT required because 'true' is the default
                                                ,	closable:				true	// pane can open & close
                                                ,	resizable:				false	// when open, pane can be resized 
                                                ,	slidable:				false	// when closed, pane can 'slide' open over other panes - closes on mouse-out
                        
                                                ,       north__slidable:		false	// OVERRIDE the pane-default of 'slidable=true'
                                                ,	north__spacing_open:	0		// no resizer-bar when open (zero height)
                                                ,	south__resizable:		false	// OVERRIDE the pane-default of 'resizable=true'
                                                ,	south__spacing_open:	0		// no resizer-bar when open (zero height)
                                                ,	west__spacing_open:	1		// no resizer-bar when open (zero height)
                                                ,       south__paneClass:               "ui-layout-pane"
                  	
                                                ,	west__minSize:			200
                                                ,	west__maxSize:			350
                                                
                                                ,       center__onresize: function (name, el, state, opts, Layout) { 
                                                    $.publish("set_grid_height", [state.innerHeight]);
                                                    $.publish("set_grid_width", [state.innerWidth]);
                                                }
			
                     
                                            });
                
                                            myLayout.allowOverflow('north'); 
                                            // setTimeout( myLayout.resizeAll, 1000 ); /* allow time for browser to re-render with new theme */
               
               
               
               
                
                                        }); //fin de document.ready
                
           

            
                                        $(function()
                                        {
                                            $(window).hashchange(function(){
                      
                                                var hash = location.hash;
                    
                                                
                                                if(hash=="#cara0"){
                                                    console.log("ok")
                                                }else{
                                                    if(grid){
                                                        grid.destroy(); 
                        
                                                    }
                                               
           
                                                    if(hash != "#detalleProceso" && hash !="#listarPonderacionFactor" && hash !="#listarPonderacionCaracteristica" && hash !="#listarProcesos")
                                                    {
                        
                                                        $.unsubscribe("set_grid_width");
                                                        $.unsubscribe("set_grid_height");    
                                                        if(middleLayout){
                                                      
                                                            middleLayout.destroy();
                                                            window[ "middleLayout" ] = null;
        
                                                        } 
                                                        if(innerLayout){
                                                            innerLayout.destroy();
                                                            window[ "innerLayout" ] = null;    
                                                        }
                        
                        
                        
                                                        selectedRowIds = [];
                        
                
                                                        $("div.ui-layout-center").empty();
                                                        var url3 = "<%=request.getContextPath()%>/"+hash;
                        
                                                        if(hash == "#PonderacionFactor"){
                                                            url3 = url3.replace('#', "ControllerAI?action=")+"AI";
                          
                      
                                                            $("div.ui-layout-center").empty();
                                                            $.ajax({ 
                                                                type: "POST", 
                                                                url: url3, 
                                                                success: function(data) 
                                                                {
                                                                    if(${auxAsignarF} == 1){
                                                                        alert("La Ponderación de Factores ya ha sido Asignada.");
                                                                    }
                                       
                                                                    $("div.ui-layout-center").append(data);
                                    
                                                                    $("#formPondeFa").submit(function(event){
                                                                        event.preventDefault();
                                             
                                                                        $.ajax({
                                                                            type: 'POST',
                                                                            url: "<%=request.getContextPath()%>/formController?action=asignarPonderacionFactorAIp",
                                                                            data: $("#formPondeFa").serialize(),
                                                                            success: function(){
                                                                          
                                                                                if(${auxAsignarF} == 1){
                                                                                    alert("Ponderación de Factores Actualizada con Exito!");
                                                                                    location = "<%=request.getContextPath()%>/#PonderacionFactor";      
                                                                                }else{
                                                                                    alert("Ponderación de Factores Asignada con Exito!");
                                                                                    location = "<%=request.getContextPath()%>/#PonderacionFactor";       
                                                                                }
                                             
                                                                            } //fin success
                                            
                                                                        }); //fin $.ajax
                                                                    }); //fin submit
                                        
                                     
                             
                                                                } //fin success
                                                            }); //fin del $.ajax
                         
                                                       
                       
                                                        }
                                                        else if(hash == "#PonderacionCaracteristica"){
                                                            url3 = url3.replace('#', "ControllerAI?action=")+"AI";
                          
                      
                                                        
                                                            $("div.ui-layout-center").empty();
                                                            $.ajax({ 
                                                                type: "POST", 
                                                                url: url3, 
                                                                success: function(data) 
                                                                {
                                                                    if(${auxAsignarC} == 1){
                                                                        alert("La Ponderación de Caracteristicas ya ha sido Asignada.");
                                                                    }
                                       
                                       
                                       
                                                                    $("div.ui-layout-center").append(data);
                                                                    setTimeout(function(){
                                                                        $("div.ui-layout-center").scrollspy();   
                                                                    }, 500);
                                    
                                                                    $("#formPondeCara").submit(function(event){
                                                                        event.preventDefault();
                                             
                                                                        $.ajax({
                                                                            type: 'POST',
                                                                            url: "<%=request.getContextPath()%>/formController?action=asignarPonderacionCaracteristicaAIp",
                                                                            data: $("#formPondeCara").serialize(),
                                                                            success: function(){
                                                                                if(${auxAsignarC} == 1){
                                                                                    alert("Ponderación de Caracteristicas Actualizada con Exito!");
                                                                                    location = "<%=request.getContextPath()%>/#PonderacionCaracteristica";      
                                                                                }else{
                                                                                    alert("Ponderación de Caracteristicas Asignada con Exito!");
                                                                                    location = "<%=request.getContextPath()%>/#PonderacionCaracteristica";       
                                                                                }
                                             
                                                                            } //fin success
                                            
                                                                        }); //fin $.ajax
                                                                    }); //fin submit
                                        
                                     
                             
                                                                } //fin success
                                                            }); //fin del $.ajax
                         
                                                        
                                                        }
                                                        else if(hash == "#AsignacionEncuestas"){
                                                            url3 = url3.replace('#', "ControllerAI?action=")+"AI";
                          
                      
                                                            var jaja34 = function(){
                                                                $("div.ui-layout-center").empty();
                                                                $.ajax({ 
                                                                    type: "POST", 
                                                                    url: url3, 
                                                                    success: function(data) 
                                                                    {
                                       
                                                                        $("div.ui-layout-center").append(data);
                                    
                                                                        $("#formAsigEnc").submit(function(event){
                                                                            event.preventDefault();
                                             
                                                                            $.ajax({
                                                                                type: 'POST',
                                                                                url: "<%=request.getContextPath()%>/formController?action=asignarEncuestasAIp",
                                                                                data: $("#formAsigEnc").serialize(),
                                                                                success: function(){
                                                                                    // $("#asignarEncuesta").attr("href", "<%=request.getContextPath()%>/#listarAsignacionEncuestas");  
                                                                                    //location = "<%=request.getContextPath()%>/#listarAsignacionEncuestas";  
                                                                                    alert("encuestas asignada con exito!")
                                                                                } //fin success
                                            
                                                                            }); //fin $.ajax
                                                                        }); //fin submit
                                        
                                     
                             
                                                                    } //fin success
                                                                }); //fin del $.ajax
                         
                                                            } //fin jaja34  
                                                            jaja34(); 
                       
                                                        }
                                                        else if(hash == "#CrearProceso"){
                                                            url3 = url3.replace('#', "ControllerAI?action=")+"AI";
                                                            $("div.ui-layout-center").empty();
                                                            $.ajax({ 
                                                                type: "POST", 
                                                                url: url3, 
                                                                success: function(data) 
                                                                {
                                                                    if(${aux_index2} == 1){
                                                                        alert("El Proceso ya Fue Creado.");
                                                                    }
                                                                    $("div.ui-layout-center").append(data);
                                                                    $("#formCrearProc").submit(function(event){
                                                                        event.preventDefault();
                                                                        $.ajax({
                                                                            type: 'POST',
                                                                            url: "<%=request.getContextPath()%>/formController?action=crearProcesoAIp",
                                                                            data: $("#formCrearProc").serialize(),
                                                                            success: function(){
                                                                                if(${aux_index2} == 1){
                                                                                    alert("Proceso Actualizado con Exito!");
                                                                                    $("#menu").load("<%=request.getContextPath()%>/ControllerAI?action=menuAI");
                                                                                    location = "<%=request.getContextPath()%>/#CrearProceso";      
                                                                                }else{
                                                                                    $("#menu").load("<%=request.getContextPath()%>/ControllerAI?action=menuAI");
                                                                                    location = '/AutoevaluacionInstitucional/';      
                                                                                }
                                                                                                         
                                                                            } //fin success
                                                                        }); //fin $.ajax
                                                                    }); //fin submit
                                                                } //fin success
                                                            }); //fin del $.ajax
                                                        }
                                                        else if(hash == "#IniciarProceso"){
                                                            url3 = url3.replace('#', "formController?action=")+"AI";
                                                            $("div.ui-layout-center").empty();
                                                            $.ajax({ 
                                                                type: "POST", 
                                                                url: url3, 
                                                                success: function(data) 
                                                                {
                                                                    $("#menu").load("<%=request.getContextPath()%>/ControllerAI?action=menuAI");
                                                                            
                                                                    location = "<%=request.getContextPath()%>/#listarProcesos";                                
                                                             
                                                                } //fin success
                                                            }); //fin del $.ajax
                          
                                                      
                       
                                                        }
                                                        else if(hash == "#CerrarProceso"){
                                                            url3 = url3.replace('#', "formController?action=")+"AI";
                                                            $("div.ui-layout-center").empty();
                                                            $.ajax({ 
                                                                type: "POST", 
                                                                url: url3, 
                                                                success: function(data) 
                                                                {
                                                                    $("#menu").load("<%=request.getContextPath()%>/ControllerAI?action=menuAI");
                                                                            
                                                                    location = "<%=request.getContextPath()%>/#detalleProceso";                                
                                                             
                                                                } //fin success
                                                            }); //fin del $.ajax
                          
                                                      
                       
                                                        }
                                                        else {
                                                            url3 = url3.replace('#', "ControllerAI?action=")+"AI";
                                                            $("div.ui-layout-center").load(url3);
                                                        }
                            
                            
                        
                       
                           
                      
                       
                       
                                                    }else{
                        
                                                        if(hash == "#detalleProceso")
                                                        {
                        
                         
                                                            $.unsubscribe("set_grid_width");
                        
                                                            if(middleLayout){
                                                                middleLayout.destroy();
                            
                                                            } 
                                                            if(innerLayout){
                                                                innerLayout.destroy();
                            
                                                            }
                        
                        
                        
                                                            selectedRowIds = [];
                        
                
                                                            $("div.ui-layout-center").empty();
                        
                                                            var dataView;
                                                            var grid;
                                                            //  var pager;
                                                            //var columnpicker;
                                                            var grid_opts={};
                                                            var data = [];
                                                            var selectedRowIds = [];





                                                            var storyTitleFormatter = function(row, cell, value, columnDef, dataContext) {
                                                                return "<div class='options'><a title='Option' class='rowOption more option'></a></div>"+
                                                                    ""+dataContext["programa"]+"";


                                                            };
            
            
                                                            var columns = [
                                                                {id:"programa", name:"Nombre", field:"programa", minWidth:100,  sortable:true, formatter:storyTitleFormatter },	
                                                                {id:"fechaInicio", name:"Fecha de Inicio", field:"fechaInicio", minWidth:100, sortable:true },
                                                                {id:"fechaCierre", name:"Fecha de Cierre", field:"fechaCierre", minWidth:100, sortable:true},
                                                                {id:"descripcion", name:"Descripcin", field:"descripcion", width:150, sortable:true}
                
                                                            ];

                                                            var options = {
                                                                editable: false,
                                                                autoEdit: false,
                                                                enableAddRow: false,
                                                                enableCellNavigation: true,
                                                                asyncEditorLoading: false,
                                                                enableColumnReorder: false,
                                                                forceFitColumns: true
                        
                                                            };

                                                            var sortcol = "Nombre";
                                                            var sortdir = 1;
                                                            //var percentCompleteThreshold = 0;
                                                            var searchString = "";

                                                            function myFilter(item) {
               
                                                                if (searchString != "" && item["programa"].toLowerCase().indexOf(searchString.toLowerCase())==-1 )
                                                                    return false;

                                                                return true;
                                                            }

               
                                                            $(".grid-header .ui-icon")
                                                            .addClass("ui-state-default ui-corner-all")
                                                            .mouseover(function(e) {
                                                                $(e.target).addClass("ui-state-hover")
                                                            })
                                                            .mouseout(function(e) {
                                                                $(e.target).removeClass("ui-state-hover")
                                                            });


                    
                                                            $.ajax({ 
                                                                type: "POST", 
                                                                url: "/AutoevaluacionInstitucional/jsonController?ejecucion=indexAI", 
                                                                dataType: 'json', 
                            
                                                                success: function(json) 
                                                                {
                                                                    $(".ui-layout-center").append("<div class='middle-north'>"
                                                                        +"<div class='row wellMio'>"
                                                                        +"<div class='span5'>"
                                                                        +"<h3>${msjLogIn1}</h3>"
                                                                        +"<h4>${msjLogIn2}</h4>"
                                                                        +"</div>"
                                                                        +"<div class='pull-right'>"
                                                                        +"<form class='form-search'>"
                                                                        +"<input type='text' id='txtSearch' class='input-medium search-query'>"
                                                                        +"<button class='btn' type='submit'>Buscar</button>"
                                                                        +"</form>"
                                                                        +"</div>"
                                                                        +"</div>"
                                                                        +"</div>"   
                                                                        +"<div class='middle-center'>"
                                                                        +"<div class='inner-center' style='width:100%;float:left;'>"
                                                                        +"<div id='myGrid'></div></div></div>");
                                                                    if(json.length > 0) 
                                                                    { for (var i = 0; i < json.length; i++) 
                                                                        {
                                                                            data[i] = { 
                                                                                id: json[i].id, 
                                                                                fechaInicio: json[i].fechaInicio, 
                                                                                fechaCierre: json[i].fechaCierre, 
                                                                                descripcion: json[i].descripcion,
                                                                                programa: json[i].programa
                                                                            }; 
                                                                        } 
                                                                        dataView = new Slick.Data.DataView();
                          
                                                                        grid = new Slick.Grid($("#myGrid"), dataView.rows, columns, options);
                                                                        var fil=[];
                                                                        grid.setSelectedRows(fil); 
                                                                        // pager = new Slick.Controls.Pager(dataView, grid, $("#pager"));
                                                                        //columnpicker = new Slick.Controls.ColumnPicker(columns, grid, options);
                
                
                
                                                                        $.subscribe("set_grid_height", function (new_height) {
                                                                            grid_opts.height = new_height;
                                                                            $("#myGrid").css('height', grid_opts.height);
                                                                            grid.resizeCanvas();
                                        
                                                                        });
            
                                                                        $.subscribe("set_grid_width", function (new_width) {
                                                                            grid_opts.width = new_width;
                                                                            $("#myGrid").css('width', grid_opts.width );
                                                                            grid.autosizeColumns();
                                                                            //grid.resizeCanvas();
                                                                        });
                                                                    

            

                                                                        /*   al cambiar el contenido de una celda
                                                                         *   grid.onCellChange = function(row,col,item) {
                                         dataView.updateItem(item.id,item);    
                                     };

                                                 grid.onAddNewRow = addItem;
                                                                         */  //al presionar teclas
                                                                        grid.onKeyDown = function(e) {
                                    
                                                                            var rows = [];
                                                                            selectedRowIds = [];
                                    
                                                                            // select all rows on ctrl-a
                                                                            if (e.which == 65 && e.ctrlKey)
                                                                            {
                                                                                for (var i = 0; i < dataView.rows.length; i++) {
                                                                                    rows.push(i);
                                                                                    selectedRowIds.push(dataView.rows[i].id);
                                                                                }

                                                                                grid.setSelectedRows(rows);

                                                                                return true;
                                                                            }  
                                                                            if(e.which == 27){
                                                                                grid.setSelectedRows(rows);
                                                                                return true;
                                                                            }
                                                                            return false;
                                    

                                   
                                                                        };

                                                                        grid.onSelectedRowsChanged = function() {
                                                                            selectedRowIds = [];
                                                                            var rows = grid.getSelectedRows();
                                                                            for (var i = 0, l = rows.length; i < l; i++) {
                                                                                var item = dataView.rows[rows[i]];
                                                                                if (item) selectedRowIds.push(item.id);
                                                                            }
                                                                        };

                
                
                                                                        grid.onSort = function(sortCol, sortAsc) {
                                                                            sortdir = sortAsc ? 1 : -1;
                                                                            sortcol = sortCol.field;
                                         
                
                
                
                
                                                                        };

                                                                        // wire up model events to drive the grid
                                                                        dataView.onRowCountChanged.subscribe(function(args) {
                                                                            grid.updateRowCount();
                                                                            grid.render();
                                                                        });

                                                                        dataView.onRowsChanged.subscribe(function(rows) {
                                                                            grid.removeRows(rows);
                                                                            grid.render();

                                                                            if (selectedRowIds.length > 0)
                                                                            {
                                                                                // since how the original data maps onto rows has changed,
                                                                                // the selected rows in the grid need to be updated
                                                                                var selRows = [];
                                                                                for (var i = 0; i < selectedRowIds.length; i++)
                                                                                {
                                                                                    var idx = dataView.getRowById(selectedRowIds[i]);
                                                                                    if (idx != undefined)
                                                                                        selRows.push(idx);
                                                                                }

                                                                                grid.setSelectedRows(selRows);
                                                                            }
                                                                        });

                                                                        dataView.onPagingInfoChanged.subscribe(function(pagingInfo) {
                    
                                                                            grid.setOptions({enableAddRow:options.enableAddRow});
                                                                        });
                
                
                                                                        $("#txtSearch").keyup(function(e) {
                                                                            Slick.GlobalEditorLock.cancelCurrentEdit();

                                                                            // clear on Esc
                                                                            if (e.which == 27)
                                                                                this.value = "";

                                                                            searchString = this.value;
                                                                            dataView.refresh();
                                                                        });


                                                                        dataView.beginUpdate();
                                                                        dataView.setItems(data);
                                                                        dataView.setFilter(myFilter);
                                                                        dataView.endUpdate();
                        

                                                                        grid.onContextMenu = function (e, row, cell)
                                                                        {
                                                                            //al dar click derecho sobre el la tabla
                                                                            if (!Slick.GlobalEditorLock.commitCurrentEdit()) { return; }
                                   
                                                                            if(selectedRowIds.length <= 1){
                                                                                var rows = [];
                                                                                selectedRowIds = [];
                                                                                rows.push(row);
                                                                                selectedRowIds.push(""+rows[0]);
                                                                                grid.setSelectedRows(rows);
                                                                            }
                                                                            return true;
                                                                        }; 
                                
                                                                        grid.onClick = function (e, row, cell)
                                                                        {
                                                                            //al dar click derecho sobre el la tabla
                                                                            if (!Slick.GlobalEditorLock.commitCurrentEdit()) { return; }
                                   
                                                                            if(selectedRowIds.length <= 1){
                                                                                var rows = [];
                                                                                selectedRowIds = [];
                                                                                rows.push(row);
                                                                                selectedRowIds.push(""+rows[0]);
                                                                                grid.setSelectedRows(rows);
                                                                            }
                                                                            var ancla = $(".rowOption");
                                                                            if(e.target == ancla[0]){
                                                              
                                                                                $(".slick-cell .options a").jjmenu("click", 
                                                                                // menu items:
                                                                                [ {getByFunction:function(myData) {
                                                                                            return [{title:"Asignar Ponderacin ", action:{type:"fn",callback:"(function(){ $('#asignarPonderacion').trigger('click'); })"}},
                                                                                                {title:"Asignar Muestra", action:{type:"fn",callback:"(function(){ $('#asignarMuestra').trigger('click'); })"} },
                                                                                                {title:"Asignar Encuestas" , action:{type:"fn",callback:"(function(){ $('#asignarEncuesta').trigger('click'); })"} },
                                                                                                {title:"Eliminar", action:{type:"fn",callback:"(function(){ alert('Esperando implementacion'); })"}}
                                                                                            ];
                                                                                        }
                                                                                    } 
                                                                                ], 
                                                                                // myReplaces / userData:
                                                                                {   "tbRow":function(){
                       
                        
                                                                                        var fila = jQuery(triggerElement);
                                                                                        var celdas = [];
                                                                                        jQuery(fila).parents(".slick-row").children().each(function() {
                                                                                            celdas[celdas.length] = jQuery(this).html();
                                                                                       
                                                                                        });
                                                                                        if(selectedRowIds.length > 1){
                                                                                            celdas[0] ="seleccionados"; 
                                                                                        }
                                                                                        return celdas;    
                                                                                    }
                    
                                          
                                                                                }, 
                                                                                // effects:
                                                                                {show:"default", xposition:"left", yposition:"auto"
                
                                                                                });
                                               
                                                                                setTimeout(function () { 
                                                                                    $(".slick-cell .options a").addClass("hover");
                                                                                    $(".slick-cell .options a").parents("div").addClass("hover");
                                                                                    $(".slick-cell .options a").trigger("click");
                                                                                }, 200);
                                                                                setTimeout(function () { $(".slick-cell .options a").unbind("click"); }, 2000);
                                               
                                                                            }else{
                                                                                var filadelaTabla =  $(".slick-row");
                                                                                var rowsx =[];
                                                                                if(!$.contains(filadelaTabla[0],e.target)){
                                                                                    grid.setSelectedRows(rowsx);
                                                
                                                                                }
                                                                            }
                                        
                                                                        }; 
                                
                                
                                
                                                                        $("#myGrid").bind("draginit", function(e,dd) {
                                                                            var cell = grid.getCellFromEvent(e);
                                                                            if (!cell)
                                                                                return false;

                                                                            dd.row = cell.row;
                                                                            if (!data[dd.row])
                                                                                return false;

                                                                            if (Slick.GlobalEditorLock.isActive() && !Slick.GlobalEditorLock.cancelCurrentEdit())
                                                                                return false;
                                                                        });

                            
                                                                        // jjmen(); 
                  
                                                                    } //fin del if
                       
                                                                }//fin del success
                                                            }); //fin del .ajax
                
                    
                    
                                                        }
                                                        if(hash == "#listarProcesos")
                                                        {
                                                 
                                                            //  $.unsubscribe("set_grid_width");
                        
                                                            if(middleLayout){
                                                                middleLayout.destroy();
                            
                                                            } 
                                                            if(innerLayout){
                                                                innerLayout.destroy();
                            
                                                            }
                        
                        
                        
                                                            selectedRowIds = [];
                        
                
                                                            $("div.ui-layout-center").empty();
                        
                                                            var dataView;
                                                            var grid;
                                                            //  var pager;
                                                            //var columnpicker;
                                                            var grid_opts={};
                                                            var data = [];
                                                            var selectedRowIds = [];





                                                            var storyTitleFormatter = function(row, cell, value, columnDef, dataContext) {
                                                                return "<div class='options'><a title='Option' class='rowOption more option'></a></div>"+
                                                                    ""+dataContext["programa"]+"";


                                                            };
            
            
                                                            var columns = [
                                                                {id:"programa", name:"Nombre", field:"programa", minWidth:100,  sortable:true, formatter:storyTitleFormatter },	
                                                                {id:"fechaInicio", name:"Fecha de Inicio", field:"fechaInicio", minWidth:100, sortable:true },
                                                                {id:"fechaCierre", name:"Fecha de Cierre", field:"fechaCierre", minWidth:100, sortable:true},
                                                                {id:"descripcion", name:"Descripcin", field:"descripcion", width:150, sortable:true}
                
                                                            ];

                                                            var options = {
                                                                editable: false,
                                                                autoEdit: false,
                                                                enableAddRow: false,
                                                                enableCellNavigation: true,
                                                                asyncEditorLoading: false,
                                                                enableColumnReorder: false,
                                                                forceFitColumns: true
                        
                                                            };

                                                            var sortcol = "Fecha de Inicio";
                                                            var sortdir = 1;
                                                            //var percentCompleteThreshold = 0;
                                                            var searchString = "";

                                                            function myFilter(item) {
               
                                                                if (searchString != "" && item["Fecha de Inicio"].toLowerCase().indexOf(searchString.toLowerCase())==-1 )
                                                                    return false;

                                                                return true;
                                                            }

               
                                                            $(".grid-header .ui-icon")
                                                            .addClass("ui-state-default ui-corner-all")
                                                            .mouseover(function(e) {
                                                                $(e.target).addClass("ui-state-hover")
                                                            })
                                                            .mouseout(function(e) {
                                                                $(e.target).removeClass("ui-state-hover")
                                                            });


                    
                                                            $.ajax({ 
                                                                type: "POST", 
                                                                url: "/AutoevaluacionInstitucional/jsonController?ejecucion=listarProcesos", 
                                                                dataType: 'json', 
                            
                                                                success: function(json) 
                                                                {
                                                                    $(".ui-layout-center").append("<div class='middle-north'>"
                                                                        +"<div class='row wellMio'>"
                                                                        +"<div class='span5'>"
                                                                        +"<h3>Procesos Realizados</h3>"
                                                                        +"<h4>Detalle de Procesos Ejecutados.</h4>"
                                                                        +"</div>"
                                                                        +"<div class='pull-right'>"
                                                                        +"<form class='form-search'>"
                                                                        +"<input type='text' class='input-medium search-query'>"
                                                                        +"<button class='btn' type='submit'>Buscar</button>"
                                                                        +"</form>"
                                                                        +"</div>"
                                                                        +"</div>"
                                                                        +"</div>"   
                                                                        +"<div class='middle-center'>"
                                                                        +"<div class='inner-center' style='width:100%;float:left;'>"
                                                                        +"<div id='myGrid'></div></div></div>");
            
                                                               
                            
                                                                    if(json.length > 0) 
                                                                    { for (var i = 0; i < json.length; i++) 
                                                                        {
                                                                            data[i] = { 
                                                                                id: json[i].id, 
                                                                                fechaInicio: json[i].fechaInicio, 
                                                                                fechaCierre: json[i].fechaCierre, 
                                                                                descripcion: json[i].descripcion,
                                                                                programa: json[i].programa
                                                                            }; 
                                                                        } 
                                                                        dataView = new Slick.Data.DataView();
                          
                                                                        grid = new Slick.Grid($("#myGrid"), dataView.rows, columns, options);
                                                                        var fil=[];
                                                                        grid.setSelectedRows(fil); 
                                                                        // pager = new Slick.Controls.Pager(dataView, grid, $("#pager"));
                                                                        //columnpicker = new Slick.Controls.ColumnPicker(columns, grid, options);
                
                
                
                                                                        $.subscribe("set_grid_height", function (new_height) {
                                                                            grid_opts.height = new_height;
                                                                            $("#myGrid").css('height', grid_opts.height);
                                                                            grid.resizeCanvas();
                                                                 
                                                                        });

                                                                        $.subscribe("set_grid_width", function (new_width) {
                                                                            grid_opts.width = new_width;
                                                                            $("#myGrid").css('width', grid_opts.width );
                                                                            grid.autosizeColumns();
                                                                            //grid.resizeCanvas();
                                                                        });

            

                                                                        /*   al cambiar el contenido de una celda
                                                                         *   grid.onCellChange = function(row,col,item) {
                                         dataView.updateItem(item.id,item);    
                                     };

                                                 grid.onAddNewRow = addItem;
                                                                         */  //al presionar teclas
                                                                        grid.onKeyDown = function(e) {
                                    
                                                                            var rows = [];
                                                                            selectedRowIds = [];
                                    
                                                                            // select all rows on ctrl-a
                                                                            if (e.which == 65 && e.ctrlKey)
                                                                            {
                                                                                for (var i = 0; i < dataView.rows.length; i++) {
                                                                                    rows.push(i);
                                                                                    selectedRowIds.push(dataView.rows[i].id);
                                                                                }

                                                                                grid.setSelectedRows(rows);

                                                                                return true;
                                                                            }  
                                                                            if(e.which == 27){
                                                                                grid.setSelectedRows(rows);
                                                                                return true;
                                                                            }
                                                                            return false;
                                    

                                   
                                                                        };

                                                                        grid.onSelectedRowsChanged = function() {
                                                                            selectedRowIds = [];
                                                                            var rows = grid.getSelectedRows();
                                                                            for (var i = 0, l = rows.length; i < l; i++) {
                                                                                var item = dataView.rows[rows[i]];
                                                                                if (item) selectedRowIds.push(item.id);
                                                                            }
                                                                        };

                
                
                                                                        grid.onSort = function(sortCol, sortAsc) {
                                                                            sortdir = sortAsc ? 1 : -1;
                                                                            sortcol = sortCol.field;
                                         
                
                
                
                
                                                                        };

                                                                        // wire up model events to drive the grid
                                                                        dataView.onRowCountChanged.subscribe(function(args) {
                                                                            grid.updateRowCount();
                                                                            grid.render();
                                                                        });

                                                                        dataView.onRowsChanged.subscribe(function(rows) {
                                                                            grid.removeRows(rows);
                                                                            grid.render();

                                                                            if (selectedRowIds.length > 0)
                                                                            {
                                                                                // since how the original data maps onto rows has changed,
                                                                                // the selected rows in the grid need to be updated
                                                                                var selRows = [];
                                                                                for (var i = 0; i < selectedRowIds.length; i++)
                                                                                {
                                                                                    var idx = dataView.getRowById(selectedRowIds[i]);
                                                                                    if (idx != undefined)
                                                                                        selRows.push(idx);
                                                                                }

                                                                                grid.setSelectedRows(selRows);
                                                                            }
                                                                        });

                                                                        dataView.onPagingInfoChanged.subscribe(function(pagingInfo) {
                    
                                                                            grid.setOptions({enableAddRow:options.enableAddRow});
                                                                        });
                
                
                                                                        $("#txtSearch").keyup(function(e) {
                                                                            Slick.GlobalEditorLock.cancelCurrentEdit();

                                                                            // clear on Esc
                                                                            if (e.which == 27)
                                                                                this.value = "";

                                                                            searchString = this.value;
                                                                            dataView.refresh();
                                                                        });


                                                                        dataView.beginUpdate();
                                                                        dataView.setItems(data);
                                                                        dataView.setFilter(myFilter);
                                                                        dataView.endUpdate();
                        

                                                                        grid.onContextMenu = function (e, row, cell)
                                                                        {
                                                                            //al dar click derecho sobre el la tabla
                                                                            if (!Slick.GlobalEditorLock.commitCurrentEdit()) { return; }
                                   
                                                                            if(selectedRowIds.length <= 1){
                                                                                var rows = [];
                                                                                selectedRowIds = [];
                                                                                rows.push(row);
                                                                                selectedRowIds.push(""+rows[0]);
                                                                                grid.setSelectedRows(rows);
                                                                            }
                                                                            return true;
                                                                        }; 
                                
                                                                        grid.onClick = function (e, row, cell)
                                                                        {
                                                                            //al dar click derecho sobre el la tabla
                                                                            if (!Slick.GlobalEditorLock.commitCurrentEdit()) { return; }
                                   
                                                                            if(selectedRowIds.length <= 1){
                                                                                var rows = [];
                                                                                selectedRowIds = [];
                                                                                rows.push(row);
                                                                                selectedRowIds.push(""+rows[0]);
                                                                                grid.setSelectedRows(rows);
                                                                            }
                                                                            var ancla = $(".rowOption");
                                                                            if(e.target == ancla[0]){
                                                              
                                                                                $(".slick-cell .options a").jjmenu("click", 
                                                                                // menu items:
                                                                                [ {getByFunction:function(myData) {
                                                                                            return [{title:"Asignar Ponderacin ", action:{type:"fn",callback:"(function(){ $('#asignarPonderacion').trigger('click'); })"}},
                                                                                                {title:"Asignar Muestra", action:{type:"fn",callback:"(function(){ $('#asignarMuestra').trigger('click'); })"} },
                                                                                                {title:"Asignar Encuestas" , action:{type:"fn",callback:"(function(){ $('#asignarEncuesta').trigger('click'); })"} },
                                                                                                {title:"Eliminar", action:{type:"fn",callback:"(function(){ alert('Esperando implementacion'); })"}}
                                                                                            ];
                                                                                        }
                                                                                    } 
                                                                                ], 
                                                                                // myReplaces / userData:
                                                                                {   "tbRow":function(){
                       
                        
                                                                                        var fila = jQuery(triggerElement);
                                                                                        var celdas = [];
                                                                                        jQuery(fila).parents(".slick-row").children().each(function() {
                                                                                            celdas[celdas.length] = jQuery(this).html();
                                                                                       
                                                                                        });
                                                                                        if(selectedRowIds.length > 1){
                                                                                            celdas[0] ="seleccionados"; 
                                                                                        }
                                                                                        return celdas;    
                                                                                    }
                    
                                          
                                                                                }, 
                                                                                // effects:
                                                                                {show:"default", xposition:"left", yposition:"auto"
                
                                                                                });
                                               
                                                                                setTimeout(function () { 
                                                                                    $(".slick-cell .options a").addClass("hover");
                                                                                    $(".slick-cell .options a").parents("div").addClass("hover");
                                                                                    $(".slick-cell .options a").trigger("click");
                                                                                }, 200);
                                                                                setTimeout(function () { $(".slick-cell .options a").unbind("click"); }, 2000);
                                               
                                                                            }else{
                                                                                var filadelaTabla =  $(".slick-row");
                                                                                var rowsx =[];
                                                                                if(!$.contains(filadelaTabla[0],e.target)){
                                                                                    grid.setSelectedRows(rowsx);
                                                
                                                                                }
                                                                            }
                                        
                                                                        }; 
                                
                                
                                
                                                                        $("#myGrid").bind("draginit", function(e,dd) {
                                                                            var cell = grid.getCellFromEvent(e);
                                                                            if (!cell)
                                                                                return false;

                                                                            dd.row = cell.row;
                                                                            if (!data[dd.row])
                                                                                return false;

                                                                            if (Slick.GlobalEditorLock.isActive() && !Slick.GlobalEditorLock.cancelCurrentEdit())
                                                                                return false;
                                                                        });

                            
                                                                        // jjmen(); 
                  
                                                                    } //fin del if
                       
                                                                }//fin del success
                                                            }); //fin del .ajax
                
                    
                    
                                                        }
                        
                                                        if(hash == "#listarPonderacionFactor"){
                 
                                                            $.unsubscribe("set_grid_width");
                        
                                                            if(middleLayout){
                                                                middleLayout.destroy();
                            
                                                            } 
                                                            if(innerLayout){
                                                                innerLayout.destroy();
                            
                                                            }
                        
                        
                        
                                                            selectedRowIds = [];
                        
                
                                                            $("div.ui-layout-center").empty();
                        
                                                            var dataView;
                                                            var grid;
                                                            //  var pager;
                                                            //var columnpicker;
                                                            var grid_opts={};
                                                            var data = [];
                                                            var selectedRowIds = [];





                                                            var storyTitleFormatter = function(row, cell, value, columnDef, dataContext) {
                                                                return "<div class='options'><a title='Option' class='rowOption more option'></a></div>"+
                                                                    ""+dataContext["programa"]+"";


                                                            };
            
            
                                                            var columns = [
                                                                {id:"factor", name:"Factor", field:"factor", minWidth:100,  sortable:true },	
                                                                {id:"ponderacion", name:"Ponderacion", field:"ponderacion", minWidth:100, sortable:true },
                                                                {id:"justificacion", name:"Justificacin", field:"justificacion", width:150, sortable:true}
                
                                                            ];

                                                            var options = {
                                                                editable: false,
                                                                autoEdit: false,
                                                                enableAddRow: false,
                                                                enableCellNavigation: true,
                                                                asyncEditorLoading: false,
                                                                enableColumnReorder: false,
                                                                forceFitColumns: true
                        
                                                            };

                                                            var sortcol = "factor";
                                                            var sortdir = 1;
                                                            //var percentCompleteThreshold = 0;
                                                            var searchString = "";

                                                            function myFilter(item) {
               
                                                                if (searchString != "" && item["factor"].toLowerCase().indexOf(searchString.toLowerCase())==-1 )
                                                                    return false;

                                                                return true;
                                                            }

               
                                                            $(".grid-header .ui-icon")
                                                            .addClass("ui-state-default ui-corner-all")
                                                            .mouseover(function(e) {
                                                                $(e.target).addClass("ui-state-hover")
                                                            })
                                                            .mouseout(function(e) {
                                                                $(e.target).removeClass("ui-state-hover")
                                                            });


                    
                                                            $.ajax({ 
                                                                type: "POST", 
                                                                url: "/AutoevaluacionInstitucional/jsonController?ejecucion=listarPonderacionFactor", 
                                                                dataType: 'json', 
                            
                                                                success: function(json) 
                                                                {
                                                                    $(".ui-layout-center").append("<div class='middle-north'>"
                                                                        +"<div class='row wellMio'>"
                                                                        +"<div class='span5'>"
                                                                        +"<h3>Ponderación de Factores</h3>"
                                                                        +"<h4>Detalle de Ponderación.</h4>"
                                                                        +"</div>"
                                                                        +"<div class='pull-right'>"
                                                                        +"<form class='form-search'>"
                                                                        +"<input type='text' class='input-medium search-query'>"
                                                                        +"<button class='btn' type='submit'>Buscar</button>"
                                                                        +"</form>"
                                                                        +"</div>"
                                                                        +"</div>"
                                                                        +"</div>"   
                                                                        +"<div class='middle-center'>"
                                                                        +"<div class='inner-center' style='width:100%;float:left;'>"
                                                                        +"<div id='myGrid'></div></div></div>");
            
                                                                    middleLayout = $('div.ui-layout-center').layout({ 
                                                                        north__paneSelector:    ".middle-north"
                                                                        ,   north__paneClass:    "ui-layout-pane"
                                                                        ,   center__paneSelector:    ".middle-center"
                                                                        ,	spacing_open:			8  // ALL panes
                                                                        ,	spacing_closed:			8  // ALL panes
                   
                                                                    });
                
                                                                    innerLayout = $('div.middle-center').layout({ 
                       
                                                                        center__paneSelector:    ".inner-center"
                                                                        ,	spacing_open:			8  // ALL panes
                                                                        ,	spacing_closed:			8  // ALL panes
                    
                                                                        ,   center__onresize: function (name, el, state, opts, Layout) { 
                                                                            $.publish("set_grid_height", [state.innerHeight]);
                                                                            $.publish("set_grid_width", [state.innerWidth]);
                                                                        }
                 
                                                                    });
                
                        
                
                    
                
                                                                    $.publish("set_grid_height", [middleLayout.state.center.innerHeight]);
                                                                    $.publish("set_grid_width", [middleLayout.state.center.innerWidth]);
		
		
                                                                    setTimeout( myLayout.resizeAll, 1000 ); /* allow time for browser to re-render with new theme */
         
                            
                                                                    if(json.length > 0) 
                                                                    { for (var i = 0; i < json.length; i++) 
                                                                        {
                                                                            data[i] = { 
                                                                                id: json[i].id, 
                                                                                factor: json[i].factor, 
                                                                                ponderacion: json[i].ponderacion, 
                                                                                justificacion: json[i].justificacion
                                            
                                                                            }; 
                                                                        } 
                                                                        dataView = new Slick.Data.DataView();
                          
                                                                        grid = new Slick.Grid($("#myGrid"), dataView.rows, columns, options);
                                                                        var fil=[];
                                                                        grid.setSelectedRows(fil); 
                                                                        // pager = new Slick.Controls.Pager(dataView, grid, $("#pager"));
                                                                        //columnpicker = new Slick.Controls.ColumnPicker(columns, grid, options);
                
                
                
                                                                        $.subscribe("set_grid_height", function (new_height) {
                                                                            grid_opts.height = new_height;
                                                                            $("#myGrid").css('height', grid_opts.height);
                                                                            grid.resizeCanvas();
                                        
                                                                        });
            
                                                                        $.subscribe("set_grid_width", function (new_width) {
                                                                            grid_opts.width = new_width;
                                                                            $("#myGrid").css('width', grid_opts.width );
                                                                            grid.autosizeColumns();
                                                                            //grid.resizeCanvas();
                                                                        });


            

                                                                        /*   al cambiar el contenido de una celda
                                                                         *   grid.onCellChange = function(row,col,item) {
                                         dataView.updateItem(item.id,item);    
                                     };

                                                 grid.onAddNewRow = addItem;
                                                                         */  //al presionar teclas
                                                                        grid.onKeyDown = function(e) {
                                    
                                                                            var rows = [];
                                                                            selectedRowIds = [];
                                    
                                                                            // select all rows on ctrl-a
                                                                            if (e.which == 65 && e.ctrlKey)
                                                                            {
                                                                                for (var i = 0; i < dataView.rows.length; i++) {
                                                                                    rows.push(i);
                                                                                    selectedRowIds.push(dataView.rows[i].id);
                                                                                }

                                                                                grid.setSelectedRows(rows);

                                                                                return true;
                                                                            }  
                                                                            if(e.which == 27){
                                                                                grid.setSelectedRows(rows);
                                                                                return true;
                                                                            }
                                                                            return false;
                                    

                                   
                                                                        };

                                                                        grid.onSelectedRowsChanged = function() {
                                                                            selectedRowIds = [];
                                                                            var rows = grid.getSelectedRows();
                                                                            for (var i = 0, l = rows.length; i < l; i++) {
                                                                                var item = dataView.rows[rows[i]];
                                                                                if (item) selectedRowIds.push(item.id);
                                                                            }
                                                                        };

                
                
                                                                        grid.onSort = function(sortCol, sortAsc) {
                                                                            sortdir = sortAsc ? 1 : -1;
                                                                            sortcol = sortCol.field;
                                         
                
                
                
                
                                                                        };

                                                                        // wire up model events to drive the grid
                                                                        dataView.onRowCountChanged.subscribe(function(args) {
                                                                            grid.updateRowCount();
                                                                            grid.render();
                                                                        });

                                                                        dataView.onRowsChanged.subscribe(function(rows) {
                                                                            grid.removeRows(rows);
                                                                            grid.render();

                                                                            if (selectedRowIds.length > 0)
                                                                            {
                                                                                // since how the original data maps onto rows has changed,
                                                                                // the selected rows in the grid need to be updated
                                                                                var selRows = [];
                                                                                for (var i = 0; i < selectedRowIds.length; i++)
                                                                                {
                                                                                    var idx = dataView.getRowById(selectedRowIds[i]);
                                                                                    if (idx != undefined)
                                                                                        selRows.push(idx);
                                                                                }

                                                                                grid.setSelectedRows(selRows);
                                                                            }
                                                                        });

                                                                        dataView.onPagingInfoChanged.subscribe(function(pagingInfo) {
                    
                                                                            grid.setOptions({enableAddRow:options.enableAddRow});
                                                                        });
                
                
                                                                        $("#txtSearch").keyup(function(e) {
                                                                            Slick.GlobalEditorLock.cancelCurrentEdit();

                                                                            // clear on Esc
                                                                            if (e.which == 27)
                                                                                this.value = "";

                                                                            searchString = this.value;
                                                                            dataView.refresh();
                                                                        });


                                                                        dataView.beginUpdate();
                                                                        dataView.setItems(data);
                                                                        dataView.setFilter(myFilter);
                                                                        dataView.endUpdate();
                        

                                                                        grid.onContextMenu = function (e, row, cell)
                                                                        {
                                                                            //al dar click derecho sobre el la tabla
                                                                            if (!Slick.GlobalEditorLock.commitCurrentEdit()) { return; }
                                   
                                                                            if(selectedRowIds.length <= 1){
                                                                                var rows = [];
                                                                                selectedRowIds = [];
                                                                                rows.push(row);
                                                                                selectedRowIds.push(""+rows[0]);
                                                                                grid.setSelectedRows(rows);
                                                                            }
                                                                            return true;
                                                                        }; 
                                
                                                                        grid.onClick = function (e, row, cell)
                                                                        {
                                                                            //al dar click derecho sobre el la tabla
                                                                            if (!Slick.GlobalEditorLock.commitCurrentEdit()) { return; }
                                   
                                                                            if(selectedRowIds.length <= 1){
                                                                                var rows = [];
                                                                                selectedRowIds = [];
                                                                                rows.push(row);
                                                                                selectedRowIds.push(""+rows[0]);
                                                                                grid.setSelectedRows(rows);
                                                                            }
                                                                            var ancla = $(".rowOption");
                                                                            if(e.target == ancla[0]){
                                                              
                                                                                $(".slick-cell .options a").jjmenu("click", 
                                                                                // menu items:
                                                                                [ {getByFunction:function(myData) {
                                                                                            return [{title:"Asignar Ponderacin ", action:{type:"fn",callback:"(function(){ $('#asignarPonderacion').trigger('click'); })"}},
                                                                                                {title:"Asignar Muestra", action:{type:"fn",callback:"(function(){ $('#asignarMuestra').trigger('click'); })"} },
                                                                                                {title:"Asignar Encuestas" , action:{type:"fn",callback:"(function(){ $('#asignarEncuesta').trigger('click'); })"} },
                                                                                                {title:"Eliminar", action:{type:"fn",callback:"(function(){ alert('Esperando implementacion'); })"}}
                                                                                            ];
                                                                                        }
                                                                                    } 
                                                                                ], 
                                                                                // myReplaces / userData:
                                                                                {   "tbRow":function(){
                       
                        
                                                                                        var fila = jQuery(triggerElement);
                                                                                        var celdas = [];
                                                                                        jQuery(fila).parents(".slick-row").children().each(function() {
                                                                                            celdas[celdas.length] = jQuery(this).html();
                                                                                       
                                                                                        });
                                                                                        if(selectedRowIds.length > 1){
                                                                                            celdas[0] ="seleccionados"; 
                                                                                        }
                                                                                        return celdas;    
                                                                                    }
                    
                                          
                                                                                }, 
                                                                                // effects:
                                                                                {show:"default", xposition:"left", yposition:"auto"
                
                                                                                });
                                               
                                                                                setTimeout(function () { 
                                                                                    $(".slick-cell .options a").addClass("hover");
                                                                                    $(".slick-cell .options a").parents("div").addClass("hover");
                                                                                    $(".slick-cell .options a").trigger("click");
                                                                                }, 200);
                                                                                setTimeout(function () { $(".slick-cell .options a").unbind("click"); }, 2000);
                                               
                                                                            }else{
                                                                                var filadelaTabla =  $(".slick-row");
                                                                                var rowsx =[];
                                                                                if(!$.contains(filadelaTabla[0],e.target)){
                                                                                    grid.setSelectedRows(rowsx);
                                                
                                                                                }
                                                                            }
                                        
                                                                        }; 
                                
                                
                                
                                                                        $("#myGrid").bind("draginit", function(e,dd) {
                                                                            var cell = grid.getCellFromEvent(e);
                                                                            if (!cell)
                                                                                return false;

                                                                            dd.row = cell.row;
                                                                            if (!data[dd.row])
                                                                                return false;

                                                                            if (Slick.GlobalEditorLock.isActive() && !Slick.GlobalEditorLock.cancelCurrentEdit())
                                                                                return false;
                                                                        });

                            
                                                                        // jjmen(); 
                  
                                                                    } //fin del if
                       
                                                                }//fin del success
                                                            }); //fin del .ajax
                        
                        
                        
                        
                                                        }
                                                        if(hash == "#listarPonderacionCaracteristica"){
                 
                                                            $.unsubscribe("set_grid_width");
                        
                                                            if(middleLayout){
                                                                middleLayout.destroy();
                            
                                                            } 
                                                            if(innerLayout){
                                                                innerLayout.destroy();
                            
                                                            }
                        
                        
                        
                                                            selectedRowIds = [];
                        
                
                                                            $("div.ui-layout-center").empty();
                        
                                                            var dataView;
                                                            var grid;
                                                            //  var pager;
                                                            //var columnpicker;
                                                            var grid_opts={};
                                                            var data = [];
                                                            var selectedRowIds = [];





                                                            var storyTitleFormatter = function(row, cell, value, columnDef, dataContext) {
                                                                return "<div class='options'><a title='Option' class='rowOption more option'></a></div>"+
                                                                    ""+dataContext["programa"]+"";


                                                            };
            
            
                                                            var columns = [
                                                                {id:"caracteristica", name:"Caracteristica", field:"caracteristica", minWidth:100,  sortable:true },	
                                                                {id:"ponderacion", name:"Ponderacion", field:"ponderacion", minWidth:100, sortable:true },
                                                                {id:"justificacion", name:"Justificacin", field:"justificacion", width:150, sortable:true}
                
                                                            ];

                                                            var options = {
                                                                editable: false,
                                                                autoEdit: false,
                                                                enableAddRow: false,
                                                                enableCellNavigation: true,
                                                                asyncEditorLoading: false,
                                                                enableColumnReorder: false,
                                                                forceFitColumns: true
                        
                                                            };

                                                            var sortcol = "caracteristica";
                                                            var sortdir = 1;
                                                            //var percentCompleteThreshold = 0;
                                                            var searchString = "";

                                                            function myFilter(item) {
               
                                                                if (searchString != "" && item["caracteristica"].toLowerCase().indexOf(searchString.toLowerCase())==-1 )
                                                                    return false;

                                                                return true;
                                                            }

               
                                                            $(".grid-header .ui-icon")
                                                            .addClass("ui-state-default ui-corner-all")
                                                            .mouseover(function(e) {
                                                                $(e.target).addClass("ui-state-hover")
                                                            })
                                                            .mouseout(function(e) {
                                                                $(e.target).removeClass("ui-state-hover")
                                                            });


                    
                                                            $.ajax({ 
                                                                type: "POST", 
                                                                url: "/AutoevaluacionInstitucional/jsonController?ejecucion=listarPonderacionCaracteristica", 
                                                                dataType: 'json', 
                            
                                                                success: function(json) 
                                                                {
                                                                    $(".ui-layout-center").append("<div class='middle-north'>"
                                                                        +"<div class='row wellMio'>"
                                                                        +"<div class='span5'>"
                                                                        +"<h3>${msjLogIn1}</h3>"
                                                                        +"<h4>${msjLogIn2}</h4>"
                                                                        +"</div>"
                                                                        +"<div class='pull-right'>"
                                                                        +"<form class='form-search'>"
                                                                        +"<input type='text' class='input-medium search-query'>"
                                                                        +"<button class='btn' type='submit'>Buscar</button>"
                                                                        +"</form>"
                                                                        +"</div>"
                                                                        +"</div>"
                                                                        +"</div>"   
                                                                        +"<div class='middle-center'>"
                                                                        +"<div class='inner-center' style='width:100%;float:left;'>"
                                                                        +"<div id='myGrid'></div></div></div>");
            
                                                                    middleLayout = $('div.ui-layout-center').layout({ 
                                                                        north__paneSelector:    ".middle-north"
                                                                        ,   north__paneClass:    "ui-layout-pane"
                                                                        ,   center__paneSelector:    ".middle-center"
                                                                        ,	spacing_open:			8  // ALL panes
                                                                        ,	spacing_closed:			8  // ALL panes
                   
                                                                    });
                
                                                                    innerLayout = $('div.middle-center').layout({ 
                       
                                                                        center__paneSelector:    ".inner-center"
                                                                        ,	spacing_open:			8  // ALL panes
                                                                        ,	spacing_closed:			8  // ALL panes
                    
                                                                        ,   center__onresize: function (name, el, state, opts, Layout) { 
                                                                            $.publish("set_grid_height", [state.innerHeight]);
                                                                            $.publish("set_grid_width", [state.innerWidth]);
                                                                        }
                 
                                                                    });
                
                        
                
                    
                
                                                                    $.publish("set_grid_height", [middleLayout.state.center.innerHeight]);
                                                                    $.publish("set_grid_width", [middleLayout.state.center.innerWidth]);
		
		
                                                                    setTimeout( myLayout.resizeAll, 1000 ); /* allow time for browser to re-render with new theme */
         
                            
                                                                    if(json.length > 0) 
                                                                    { for (var i = 0; i < json.length; i++) 
                                                                        {
                                                                            data[i] = { 
                                                                                id: json[i].id, 
                                                                                caracteristica: json[i].caracteristica, 
                                                                                ponderacion: json[i].ponderacion, 
                                                                                justificacion: json[i].justificacion
                                            
                                                                            }; 
                                                                        } 
                                                                        dataView = new Slick.Data.DataView();
                          
                                                                        grid = new Slick.Grid($("#myGrid"), dataView.rows, columns, options);
                                                                        var fil=[];
                                                                        grid.setSelectedRows(fil); 
                                                                        // pager = new Slick.Controls.Pager(dataView, grid, $("#pager"));
                                                                        //columnpicker = new Slick.Controls.ColumnPicker(columns, grid, options);
                
                
                
                                                                        $.subscribe("set_grid_height", function (new_height) {
                                                                            grid_opts.height = new_height;
                                                                            $("#myGrid").css('height', grid_opts.height);
                                                                            grid.resizeCanvas();
                                        
                                                                        });
            
                                                                        $.subscribe("set_grid_width", function (new_width) {
                                                                            grid_opts.width = new_width;
                                                                            $("#myGrid").css('width', grid_opts.width );
                                                                            grid.autosizeColumns();
                                                                            //grid.resizeCanvas();
                                                                        });


            

                                                                        /*   al cambiar el contenido de una celda
                                                                         *   grid.onCellChange = function(row,col,item) {
                                         dataView.updateItem(item.id,item);    
                                     };

                                                 grid.onAddNewRow = addItem;
                                                                         */  //al presionar teclas
                                                                        grid.onKeyDown = function(e) {
                                    
                                                                            var rows = [];
                                                                            selectedRowIds = [];
                                    
                                                                            // select all rows on ctrl-a
                                                                            if (e.which == 65 && e.ctrlKey)
                                                                            {
                                                                                for (var i = 0; i < dataView.rows.length; i++) {
                                                                                    rows.push(i);
                                                                                    selectedRowIds.push(dataView.rows[i].id);
                                                                                }

                                                                                grid.setSelectedRows(rows);

                                                                                return true;
                                                                            }  
                                                                            if(e.which == 27){
                                                                                grid.setSelectedRows(rows);
                                                                                return true;
                                                                            }
                                                                            return false;
                                    

                                   
                                                                        };

                                                                        grid.onSelectedRowsChanged = function() {
                                                                            selectedRowIds = [];
                                                                            var rows = grid.getSelectedRows();
                                                                            for (var i = 0, l = rows.length; i < l; i++) {
                                                                                var item = dataView.rows[rows[i]];
                                                                                if (item) selectedRowIds.push(item.id);
                                                                            }
                                                                        };

                
                
                                                                        grid.onSort = function(sortCol, sortAsc) {
                                                                            sortdir = sortAsc ? 1 : -1;
                                                                            sortcol = sortCol.field;
                                         
                
                
                
                
                                                                        };

                                                                        // wire up model events to drive the grid
                                                                        dataView.onRowCountChanged.subscribe(function(args) {
                                                                            grid.updateRowCount();
                                                                            grid.render();
                                                                        });

                                                                        dataView.onRowsChanged.subscribe(function(rows) {
                                                                            grid.removeRows(rows);
                                                                            grid.render();

                                                                            if (selectedRowIds.length > 0)
                                                                            {
                                                                                // since how the original data maps onto rows has changed,
                                                                                // the selected rows in the grid need to be updated
                                                                                var selRows = [];
                                                                                for (var i = 0; i < selectedRowIds.length; i++)
                                                                                {
                                                                                    var idx = dataView.getRowById(selectedRowIds[i]);
                                                                                    if (idx != undefined)
                                                                                        selRows.push(idx);
                                                                                }

                                                                                grid.setSelectedRows(selRows);
                                                                            }
                                                                        });

                                                                        dataView.onPagingInfoChanged.subscribe(function(pagingInfo) {
                    
                                                                            grid.setOptions({enableAddRow:options.enableAddRow});
                                                                        });
                
                
                                                                        $("#txtSearch").keyup(function(e) {
                                                                            Slick.GlobalEditorLock.cancelCurrentEdit();

                                                                            // clear on Esc
                                                                            if (e.which == 27)
                                                                                this.value = "";

                                                                            searchString = this.value;
                                                                            dataView.refresh();
                                                                        });


                                                                        dataView.beginUpdate();
                                                                        dataView.setItems(data);
                                                                        dataView.setFilter(myFilter);
                                                                        dataView.endUpdate();
                        

                                                                        grid.onContextMenu = function (e, row, cell)
                                                                        {
                                                                            //al dar click derecho sobre el la tabla
                                                                            if (!Slick.GlobalEditorLock.commitCurrentEdit()) { return; }
                                   
                                                                            if(selectedRowIds.length <= 1){
                                                                                var rows = [];
                                                                                selectedRowIds = [];
                                                                                rows.push(row);
                                                                                selectedRowIds.push(""+rows[0]);
                                                                                grid.setSelectedRows(rows);
                                                                            }
                                                                            return true;
                                                                        }; 
                                
                                                                        grid.onClick = function (e, row, cell)
                                                                        {
                                                                            //al dar click derecho sobre el la tabla
                                                                            if (!Slick.GlobalEditorLock.commitCurrentEdit()) { return; }
                                   
                                                                            if(selectedRowIds.length <= 1){
                                                                                var rows = [];
                                                                                selectedRowIds = [];
                                                                                rows.push(row);
                                                                                selectedRowIds.push(""+rows[0]);
                                                                                grid.setSelectedRows(rows);
                                                                            }
                                                                            var ancla = $(".rowOption");
                                                                            if(e.target == ancla[0]){
                                                              
                                                                                $(".slick-cell .options a").jjmenu("click", 
                                                                                // menu items:
                                                                                [ {getByFunction:function(myData) {
                                                                                            return [{title:"Asignar Ponderacin ", action:{type:"fn",callback:"(function(){ $('#asignarPonderacion').trigger('click'); })"}},
                                                                                                {title:"Asignar Muestra", action:{type:"fn",callback:"(function(){ $('#asignarMuestra').trigger('click'); })"} },
                                                                                                {title:"Asignar Encuestas" , action:{type:"fn",callback:"(function(){ $('#asignarEncuesta').trigger('click'); })"} },
                                                                                                {title:"Eliminar", action:{type:"fn",callback:"(function(){ alert('Esperando implementacion'); })"}}
                                                                                            ];
                                                                                        }
                                                                                    } 
                                                                                ], 
                                                                                // myReplaces / userData:
                                                                                {   "tbRow":function(){
                       
                        
                                                                                        var fila = jQuery(triggerElement);
                                                                                        var celdas = [];
                                                                                        jQuery(fila).parents(".slick-row").children().each(function() {
                                                                                            celdas[celdas.length] = jQuery(this).html();
                                                                                       
                                                                                        });
                                                                                        if(selectedRowIds.length > 1){
                                                                                            celdas[0] ="seleccionados"; 
                                                                                        }
                                                                                        return celdas;    
                                                                                    }
                    
                                          
                                                                                }, 
                                                                                // effects:
                                                                                {show:"default", xposition:"left", yposition:"auto"
                
                                                                                });
                                               
                                                                                setTimeout(function () { 
                                                                                    $(".slick-cell .options a").addClass("hover");
                                                                                    $(".slick-cell .options a").parents("div").addClass("hover");
                                                                                    $(".slick-cell .options a").trigger("click");
                                                                                }, 200);
                                                                                setTimeout(function () { $(".slick-cell .options a").unbind("click"); }, 2000);
                                               
                                                                            }else{
                                                                                var filadelaTabla =  $(".slick-row");
                                                                                var rowsx =[];
                                                                                if(!$.contains(filadelaTabla[0],e.target)){
                                                                                    grid.setSelectedRows(rowsx);
                                                
                                                                                }
                                                                            }
                                        
                                                                        }; 
                                
                                
                                
                                                                        $("#myGrid").bind("draginit", function(e,dd) {
                                                                            var cell = grid.getCellFromEvent(e);
                                                                            if (!cell)
                                                                                return false;

                                                                            dd.row = cell.row;
                                                                            if (!data[dd.row])
                                                                                return false;

                                                                            if (Slick.GlobalEditorLock.isActive() && !Slick.GlobalEditorLock.cancelCurrentEdit())
                                                                                return false;
                                                                        });

                            
                                                                        // jjmen(); 
                  
                                                                    } //fin del if
                       
                                                                }//fin del success
                                                            }); //fin del .ajax
                        
                        
                        
                        
                                                        }
                    
                    
                                                    } //fin del else
                
                      
                      
                      
                                           
                
                                                }
                                                
                                                
                
                     
                    
                                            });        
                                        });  //fin del function   
                    
                                        //setTimeout(function () { $.jstree._focused().select_node("#detalle"); }, 1000);
          
                                    </script> 
                                    </head>
                                    <body>
                                        <div class="ui-layout-north ui-widget-content">
                                            <div class="navbar navbar-fixed-top">
                                                <div class="navbar-inner">
                                                    <div class="container-fluid" style="width: auto;">
                                                        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                                                            <span class="icon-bar"></span>
                                                            <span class="icon-bar"></span>
                                                            <span class="icon-bar"></span>
                                                        </a>
                                                        <a class="brand" href="#">Autoevaluacion Institucional</a>
                                                        <div class="nav-collapse">
                                                            <ul class="nav">
                                                                <li class="active"><a href="#">Inicio</a></li>
                                                                <li><a href="#">Contacto</a></li>
                                                            </ul>

                                                            <ul class="nav pull-right">
                                                                <li class="divider-vertical"></li>
                                                                <li class="dropdown">
                                                                    <a class="dropdown-toggle" data-toggle="dropdown">${representante.personaId.nombre}<b class="caret"></b></a>
                                                                    <ul class="dropdown-menu">
                                                                        <li><a href="#">Action</a></li>
                                                                        <li><a href="#">Another action</a></li>
                                                                        <li><a href="#">Something else here</a></li>
                                                                        <li class="divider"></li>
                                                                        <li><a href="#">Cerrar Sesion</a></li>
                                                                    </ul>
                                                                </li>
                                                            </ul>
                                                        </div><!-- /.nav-collapse -->
                                                    </div>
                                                </div><!-- /navbar-inner -->
                                            </div><!-- /navbar -->

                                        </div>
                                        <div class="ui-layout-south ui-widget-content"> 
                                            <div class="contenedor_footer fondo_footer" id="footer">
                                                <div class="links">
                                                    <a href="#"><span>Acerca de</span></a>

                                                    <a href="#"><span>Derechos de Autor</span></a>

                                                    <a href="#"><span>Ayuda</span></a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="ui-layout-center">



                                        </div>


                                        <div id="ui-layout-west" class="ui-layout-west">
                                            <div class="ui-layout-content">
                                                <div id="menu" style="padding: 8px 0pt;" class="well">
                                                    <ul class="nav nav-list">  
                                                        <c:choose>
                                                            <c:when test="${aux_index2 == 1}">
                                                                <li class="nav-header">Proceso en Ejecución</li>
                                                                <c:choose>
                                                                    <c:when test="${aux2_index2 == 1}">
                                                                        <li class="active"><a href="<%=request.getContextPath()%>/#CrearProceso"><i class="icon-white icon-th"></i> Detalle Proceso</a></li>
                                                                        <li><a id="ponderacionFact" href="<%=request.getContextPath()%>/#PonderacionFactor"><i class="icon-tag"></i> Ponderacion Factores</a></li>
                                                                        <li><a id="ponderacionCara" href="<%=request.getContextPath()%>/#PonderacionCaracteristica"><i class="icon-tags"></i> Ponderacion Caracteristicas</a></li>
                                                                        <li><a  id="asignarMuestra"  href="<%=request.getContextPath()%>/#asignarMuestra"><i class="icon-glass"></i> Asignar Muestra</a></li>
                                                                        <li><a id="asignarEncuesta"  href="<%=request.getContextPath()%>/#AsignacionEncuestas"><i class="icon-question-sign"></i> Asignacin Encuestas</a></li>
                                                                        <li><a href="#IniciarProceso"><i class="icon-play"></i> Iniciar Proceso</a></li> 
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <li class="active"><a id="detalle" href="<%=request.getContextPath()%>/#detalleProceso"><i class="icon-white icon-th"></i> Detalle Proceso</a></li>
                                                                        <li><a id="ponderacionFact" href="<%=request.getContextPath()%>/#PonderacionFactor"><i class="icon-tag"></i> Ponderacion Factores</a></li>
                                                                        <li><a id="ponderacionCara" href="<%=request.getContextPath()%>/#PonderacionCaracteristica"><i class="icon-tags"></i> Ponderacion Caracteristicas</a></li>
                                                                        <li><a  id="asignarMuestra"  href="<%=request.getContextPath()%>/#asignarMuestra"><i class="icon-glass"></i> Asignar Muestra</a></li>
                                                                        <li><a id="asignarEncuesta"  href="<%=request.getContextPath()%>/#AsignacionEncuestas"><i class="icon-question-sign"></i> Asignacin Encuestas</a></li>
                                                                        <li><a href="#CerrarProceso"><i class="icon-trash"></i> Finalizar Proceso</a></li>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                                <li class="nav-header">Procesos Anteriores</li>
                                                                <li><a id="listarProcesos" href="<%=request.getContextPath()%>/#listarProcesos"><i class="icon-th-list"></i> Listar Procesos</a></li>        
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li class="nav-header">Procesos</li>
                                                                <li><a href="#CrearProceso"><i class="icon-plus"></i>Proceso Nuevo</a></li>
                                                                <li><a id="listarProcesos" href="<%=request.getContextPath()%>/#listarProcesos"><i class="icon-th-list"></i> Listar Procesos</a></li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </body>
                                    </html> 
