<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" 
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="language" content="en" />
        <title>Autoevaluacion Institucional</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/layout2.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/header.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/footer.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/menu.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/login.css" />


        <script type="text/javascript" src="<%=request.getContextPath()%>/jQuery/jquery.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery-layout.js"></script>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/slick.grid.css" type="text/css" media="screen" charset="utf-8" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/slick.pager.css" type="text/css" media="screen" charset="utf-8" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui-1.8.5.custom.css" type="text/css" media="screen" charset="utf-8" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/examples.css" type="text/css" media="screen" charset="utf-8" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/slick.columnpicker.css" type="text/css" media="screen" charset="utf-8" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/slick-default-theme.css" type="text/css" media="screen" charset="utf-8" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styl.css" type="text/css" media="screen" charset="utf-8" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css" media="screen" charset="utf-8" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/production-uri.css" />


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
        <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery-layout.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.jstree.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.hotkeys.js"></script>
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
                    
            
            
            $(document).ready(function() {
               
                var activeClass = 'dropdown-active';
                var showingDropdown, showingMenu, showingParent;
                var dropdown = $('#iniciarSesion');
                var menu = $(".main_login");
                var parent = $("#main_login2");
						
                var hideMenu = function() {
							
                    if(showingDropdown) {
								
                        showingDropdown.removeClass(activeClass);
                        showingMenu.fadeOut("slow");
                        $("#iniciarSesion span").addClass("hoverInactive");					
                              
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
            });
                
        </script>
        <script type="text/javascript">
           
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
                    ,	west__minSize:			100
                    ,	west__maxSize:			300
                   
                    
		
                    
	
                
                });
                
                myLayout.allowOverflow('north'); 
                // setTimeout( myLayout.resizeAll, 1000 ); /* allow time for browser to re-render with new theme */
               
               
               
               
                
            }); //fin de document.ready
                
           

            
            $(function()
            {
                $("#nodo1").click(function () { 
                    $("#tree").jstree("toggle_node","#nodo1");
                
                });
                $("#nodo2").click(function () { 
                    $("#tree").jstree("toggle_node","#nodo2");
                });
                $("#nodo3").click(function () { 
                    $("#tree").jstree("toggle_node","#nodo3");
                });
        
                
                $("#tree").jstree({ 
            
                    "types" : {
                        "valid_children" : [ "root","create","edit","delete","show"],
                        "types" : {
                            "root" : {
                                "icon" : { 
                                    "image" : "css/images/iconos2.png",
                                    "position": "0 -32px"
                                },
                                "valid_children" : [ "default" ],
                                "hover_node" : true,
                                "select_node" : function () {return false;}
                            },
                            "default" : {
                                "valid_children" : [ "default" ]
                            },
                                
                            "create" : {
                                    
                                "icon" : { 
                                    "image" : "css/images/iconos2.png",
                                    "position": "0 -48px"
                                    
                                },
                                "valid_children" : [ "default" ]
                            },
                            "edit" : {
                                    
                                "icon" : { 
                                    "image" : "css/images/iconos2.png",
                                    "position": "0 -64px"
                                },
                                "valid_children" : [ "default" ]
                            }
                            ,
                            "delete" : {
                                    
                                "icon" : { 
                                    "image" : "css/images/iconos2.png",
                                    "position": "0 -80px"
                                },
                                "valid_children" : [ "default" ]
                            }
                            ,
                            "show" : {
                                    
                                "icon" : { 
                                    "image" : "css/images/iconos2.png",
                                    "position": "0 -112px"
                                },
                                "valid_children" : [ "default" ]
                            }
                                
                        }
                        
                        
                        
                        
                    },
            
            
                    "ui" : {
                        "select_limit" : 1,
                        "selected_parent_close" : "select_parent",
                        "selected_parent_open": true
			
                    }
                    , "themes" : {
                        "theme" : "default",
                        "dots" : false,
                        "icons" : true,
                        "url": "/AutoevaluacionInstitucional/css/style.css"
                    },
                    "core" : { "initially_open" : [ "nodo1", "nodo2","nodo3" ] }
                    , "plugins" : [ "themes", "html_data","ui","hotkeys","types"]
              
                })
                
             

                .delegate("a", "click", function (event, data2) { event.preventDefault(); 
                   
                    location = $(this).attr("href");
                
                })
                
                $(window).hashchange(function(){
                      
                    var hash = location.hash;
                    
                                     
                    
                    if(grid){
                        grid.destroy(); 
                        
                    }
                    
           
                    if(hash != "#detalleProceso" && hash !="#listarPonderacionFactor" && hash !="#listarPonderacionCaracteristica" && hash !="#listarProcesos")
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
                        var url3 = "<%=request.getContextPath()%>/"+hash;
                        
                        if(hash == "#PonderacionFactor"){
                            url3 = url3.replace('#', "ControllerAI?action=")+"AI";
                          
                      
                            var jaja34 = function(){
                                $("div.ui-layout-center").empty();
                                $.ajax({ 
                                    type: "POST", 
                                    url: url3, 
                                    success: function(data) 
                                    {
                                       
                                        $("div.ui-layout-center").append(data);
                                    
                                        $("#formPondeFa").submit(function(event){
                                            event.preventDefault();
                                             
                                            $.ajax({
                                                type: 'POST',
                                                url: "<%=request.getContextPath()%>/formController?action=asignarPonderacionFactorAIp",
                                                data: $("#formPondeFa").serialize(),
                                                success: function(){
                                                    $("#ponderacionFact").attr("href", "<%=request.getContextPath()%>/#listarPonderacionFactor");  
                                                    location = "<%=request.getContextPath()%>/#listarPonderacionFactor";
                                                    
                                                   
                                             
                                                } //fin success
                                            
                                            }); //fin $.ajax
                                        }); //fin submit
                                        
                                     
                             
                                    } //fin success
                                }); //fin del $.ajax
                         
                            } //fin jaja34  
                            jaja34(); 
                       
                        }
                        else if(hash == "#PonderacionCaracteristica"){
                            url3 = url3.replace('#', "ControllerAI?action=")+"AI";
                          
                      
                            var jaja34 = function(){
                                $("div.ui-layout-center").empty();
                                $.ajax({ 
                                    type: "POST", 
                                    url: url3, 
                                    success: function(data) 
                                    {
                                       
                                        $("div.ui-layout-center").append(data);
                                    
                                        $("#formPondeCara").submit(function(event){
                                            event.preventDefault();
                                             
                                            $.ajax({
                                                type: 'POST',
                                                url: "<%=request.getContextPath()%>/formController?action=asignarPonderacionCaracteristicaAIp",
                                                data: $("#formPondeCara").serialize(),
                                                success: function(){
                                                    $("#ponderacionCara").attr("href", "<%=request.getContextPath()%>/#listarPonderacionCaracteristica");  
                                                    location = "<%=request.getContextPath()%>/#listarPonderacionCaracteristica";
                                                    
                                                   
                                             
                                                } //fin success
                                            
                                            }); //fin $.ajax
                                        }); //fin submit
                                        
                                     
                             
                                    } //fin success
                                }); //fin del $.ajax
                         
                            } //fin jaja34  
                            jaja34(); 
                       
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
                                {id:"descripcion", name:"Descripción", field:"descripcion", width:150, sortable:true}
                
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
                                        +"<div id='page_header'>"
                                        +"<div class='meta'>"
                                        +"<h3><span>"
                                        +"${msjLogIn1}"
                                        +"</span>"
                                        +"<span class='gridCount' id='gridNumItems'>"
                                        +"(${msjLogIn2})"
                                        +"</span></h3>"
                                        +"</div> <div class='page_options'><form class='searchbar right' id='page_search'>"
                                        +"<input type='text' value='' id='txtSearch' class='search'><a class='remove'></a></form>"
                                        +"</div></div></div>"    
                                        +"<div class='middle-center'>"
                                        +"<div class='inner-center' style='width:100%;float:left;'>"
                                        +"<div id='myGrid'></div></div></div>");
            
                                    middleLayout = $('div.ui-layout-center').layout({ 
                                        north__paneSelector:    ".middle-north"
                                        ,   north__paneClass:    "ui-layout-pane2"
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
                                                            return [{title:"Asignar Ponderación ", action:{type:"fn",callback:"(function(){ $('#asignarPonderacion').trigger('click'); })"}},
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
                                {id:"descripcion", name:"Descripción", field:"descripcion", width:150, sortable:true}
                
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
                                        +"<div id='page_header'>"
                                        +"<div class='meta'>"
                                        +"<h3><span>"
                                        +"Procesos Realizados"
                                        +"</span>"
                                        +"<span class='gridCount' id='gridNumItems'>"
                                        +"(Detalle de Procesos.)"
                                        +"</span></h3>"
                                        +"</div> <div class='page_options'><form class='searchbar right' id='page_search'>"
                                        +"<input type='text' value='' id='txtSearch' class='search'><a class='remove'></a></form>"
                                        +"</div></div></div>"    
                                        +"<div class='middle-center'>"
                                        +"<div class='inner-center' style='width:100%;float:left;'>"
                                        +"<div id='myGrid'></div></div></div>");
            
                                    middleLayout = $('div.ui-layout-center').layout({ 
                                        north__paneSelector:    ".middle-north"
                                        ,   north__paneClass:    "ui-layout-pane2"
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
                                                            return [{title:"Asignar Ponderación ", action:{type:"fn",callback:"(function(){ $('#asignarPonderacion').trigger('click'); })"}},
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
                                {id:"justificacion", name:"Justificación", field:"justificacion", width:150, sortable:true}
                
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
                                        +"<div id='page_header'>"
                                        +"<div class='meta'>"
                                        +"<h3><span>"
                                        +"Lista de Factores"
                                        +"</span>"
                                        +"<span class='gridCount' id='gridNumItems'>"
                                        +"(detalle)"
                                        +"</span></h3>"
                                        +"</div> <div class='page_options'><form class='searchbar right' id='page_search'>"
                                        +"<input type='text' value='' id='txtSearch' class='search'><a class='remove'></a></form>"
                                        +"</div></div></div>"    
                                        +"<div class='middle-center'>"
                                        +"<div class='inner-center' style='width:100%;float:left;'>"
                                        +"<div id='myGrid'></div></div></div>");
            
                                    middleLayout = $('div.ui-layout-center').layout({ 
                                        north__paneSelector:    ".middle-north"
                                        ,   north__paneClass:    "ui-layout-pane2"
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
                                                            return [{title:"Asignar Ponderación ", action:{type:"fn",callback:"(function(){ $('#asignarPonderacion').trigger('click'); })"}},
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
                                {id:"justificacion", name:"Justificación", field:"justificacion", width:150, sortable:true}
                
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
                                        +"<div id='page_header'>"
                                        +"<div class='meta'>"
                                        +"<h3><span>"
                                        +"Lista de Caracteristicas"
                                        +"</span>"
                                        +"<span class='gridCount' id='gridNumItems'>"
                                        +"(detalle)"
                                        +"</span></h3>"
                                        +"</div> <div class='page_options'><form class='searchbar right' id='page_search'>"
                                        +"<input type='text' value='' id='txtSearch' class='search'><a class='remove'></a></form>"
                                        +"</div></div></div>"    
                                        +"<div class='middle-center'>"
                                        +"<div class='inner-center' style='width:100%;float:left;'>"
                                        +"<div id='myGrid'></div></div></div>");
            
                                    middleLayout = $('div.ui-layout-center').layout({ 
                                        north__paneSelector:    ".middle-north"
                                        ,   north__paneClass:    "ui-layout-pane2"
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
                                                            return [{title:"Asignar Ponderación ", action:{type:"fn",callback:"(function(){ $('#asignarPonderacion').trigger('click'); })"}},
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
                
                      
                      
                      
                });
                
                
                     
                    
                    
            });  //fin del function   
                    
            //setTimeout(function () { $.jstree._focused().select_node("#detalle"); }, 1000);
          
        </script> 
    </head>
    <body>
        <div class="ui-layout-north ui-widget-content" style="display: none;">
            <div class="fondoHeader" id="header">
                <h1>
                    <a id="logo" href="/#/">
                    </a>
                </h1>
                <div id="Navigation">
                    <a class="active" >
                        ${mensaje}
                    </a>
                    <a  href="#/">
                        Inicio
                    </a>
                    <a href="#">
                        Contacto
                    </a>
                </div>
                <!--ojo-->
                <div class="right" id="header_Options">
                    <%--   <div class="header_Option">
                           <a href="#">
                               <span class="header hoverInactive">Idioma</span>
                           </a>
                       </div>--%>
                    <div class="header_Option">
                        <a href="#" id="iniciarSesion">
                            <span class="hoverInactive header">${representante.personaId.nombre}</span>
                        </a>
                        <div class="main_login" name="main_login" id="main_login2">
                            <a id="loginReplace" href="#">
                                <span class="label">Iniciar Sesion</span>
                                <span class="arrow icon_arrow"></span>
                            </a>
                            <ul class="dropdownOptions">
                                <li class="option"><a class="profile" href="#/user/pepe+cortez/7037960"><span data-translate-text="PROFILE">Profile</span></a></li>
                                <li class="option"><a class="settings" href="#/settings"><span data-translate-text="SETTINGS">Settings</span></a></li>
                                <li class="option"><a class="extras" href="#/features"><span data-translate-text="FEATURES_TITLE">Features</span></a></li>
                                <li class="option"><a class="invite"><span data-translate-text="INVITE_FRIENDS">Invitar Amigos</span></a></li>

                                <li class="option"><a href="#/settings/subscriptions"><span data-translate-text="UPGRADE">Upgrade</span></a></li>


                                <li class="option">
                                    <a href="#/surveys">
                                        <span data-translate-text="SURVEYS" class="label">Encuestas</span>
                                    </a>
                                </li>

                                <li class="option"><a class="help" target="_blank" href="http://help.grooveshark.com"><span data-translate-text="HELP">Help</span></a></li>
                                <li class="last option"><a class="logout" href="<%=request.getContextPath()%>/ControllerAI?action=CerrarSesion"><span data-translate-text="LOGOUT">Cerrar Sesión</span></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--ojo-->
            </div>
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
        <div class="ui-layout-west" style="display: none;">
            <div class="ui-layout-content">
                <div id="tree">
                    <ul>
                        <c:choose>

                            <c:when test="${aux_index2 == 1}">
                                <li rel="root">
                                    <a id="nodo1" >Proceso en Ejecución</a>
                                    <ul> 
                                        <li id="detalle" rel="create">
                                            <a id="detalle" href="<%=request.getContextPath()%>/#detalleProceso">Detalle Proceso</a>
                                        </li>
                                        <li rel="edit">  
                                            <a id="ponderacionFact" href="<%=request.getContextPath()%>/#PonderacionFactor">Ponderación Factores</a>
                                        </li>
                                        <li rel="edit">
                                            <a  id="ponderacionCara" href="<%=request.getContextPath()%>/#PonderacionCaracteristica">Ponderación Características</a>
                                        </li>    
                                        <li id="unid" rel="edit">
                                            <a  id="asignarMuestra"  href="<%=request.getContextPath()%>/#asignarMuestra">Asignar Muestra</a>
                                        </li>
                                        <li rel="edit">
                                            <a id="asignarEncuesta"  href="<%=request.getContextPath()%>/#asignarEncuesta">Asignar Encuestas</a>
                                        </li>
                                        <c:choose>
                                            <c:when test="${aux2_index2 == 1}">
                                                <li rel="edit">
                                                    <a href="<%=request.getContextPath()%>/ControllerAI?action=iniciarProcesoAI">Iniciar Proceso</a>
                                                </li>   
                                            </c:when>
                                            <c:otherwise>
                                                <li rel="edit">
                                                    <a href="<%=request.getContextPath()%>/ControllerAI?action=cerrarProcesoAI">Finalizar Proceso</a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </ul>
                                </li>
                                <li rel="root">   
                                    <a id="nodo2" >Procesos Antiguos</a>
                                    <ul> 
                                        <li rel="create">
                                            <a id="listarProcesos" href="<%=request.getContextPath()%>/#listarProcesos">Listar Procesos</a>
                                        </li>
                                    </ul>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li rel="root">
                                    <a id="nodo1" >Proceso Nuevo</a>
                                    <ul> 
                                        <li rel="create">
                                            <a href="<%=request.getContextPath()%>/ControllerAI?action=crearProcesoAI">Crear Proceso Nuevo</a>
                                        </li>
                                    </ul>
                                    <li>
                                        <li rel="root">   
                                            <a id="nodo2" >Procesos Antiguos</a>
                                            <ul> 
                                                <li rel="create">
                                                    <a id="listarProcesos" href="<%=request.getContextPath()%>/#listarProcesos">Listar Procesos</a>
                                                </li>
                                            </ul>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                                </ul>
                                </div>
                                </div>
                                </div>
                                </body>
                                </html> 