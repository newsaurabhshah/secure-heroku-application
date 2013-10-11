<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main Logged In Page</title>
<link rel="stylesheet" type="text/css" href="css/base.css"/>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css"/>
<link rel="stylesheet" type="text/css" href="css/ui.jqgrid.css"/>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="js/parsley.js"></script>
<script type="text/javascript" src="js/formToJson.js"></script>
</head>
<body>
	<div class="ui-state-highlight" style="height:31px;">
		<div style="float:left; padding: 8px 0px;">
			<span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em; "></span>
			Welcome ${userDTO.username} 
		</div>
		<input type="button" id="login" style="float:right" onclick="window.location='logout'" value="Logout">
	</div>
	<div class="container">
		<div style="float:left;">
			<form id="contactForm" data-validate="parsley">
				<table>
					<tr>
						<td>Name:</td>
						<td><input name="name" data-required="true" data-maxlength="20"/></td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><input name="email" data-required="true" data-remote="/validateEmail.do" data-maxlength="50"/></td>
					</tr>
					<tr>
						<td>Mobile:</td>
						<td><input name="mobile" data-required="true" data-type="phone" data-maxlength="15"/></td>
					</tr>
					<tr>
						<td>Message:</td>
						<td><textarea name="message" data-required="true" data-maxlength="200"></textarea></td>
					</tr>
				</table>
				<button id="submitForm" type="submit">Add</button>
				<p class="loader">&nbsp;</p>
			</form>
		</div>
		<div style="float:right">
			<table id="contactGrid"></table>
			<br/>
			<input type="button" id="export" style="float:right" onclick="window.open('exportToPDF.do','_blank')" value="Export To PDF"/>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$("button, input[type=button]").button();	
			$('#contactForm').submit(function(e) { 
		        e.preventDefault();
		        if ( $(this).parsley('validate') ) {
		        	$( "button" ).button({ disabled: true });
		        	$(".loader").show();
		        	$.ajax({
		                url: "userFormSubmit.do",
		                type: "POST",
		                dataType:"json",
		                data: $('#contactForm').serializeObject()
				        }).done(function(data){
				        		$( "button" ).button({ disabled: false });
				        		$(".loader").hide();
				                if(data===true){
				                	$("#contactGrid").jqGrid().trigger("reloadGrid");
				                }
				                else{
				                	alert("Server Validation Failed");            		    
				        		}
				        }).fail(function(){
				        	$( "button" ).button({ disabled: false });
				        	$(".loader").hide();
				    		alert("Server Error");
				        });
		        }
		    });
		});
		$("#contactGrid").jqGrid({
			url:"contactHistory.do",
			datatype: "json",
		   	colNames:['ID','Name', 'Email', 'Mobile','Message'],
		   	colModel:[
		   		{name:'id',index:'id', width:10, sortable:false},
		   		{name:'name',index:'name', width:30, sortable:false},
		   		{name:'email',index:'email', width:60, sortable:false},
		   		{name:'mobile',index:'mobile', width:45, sortable:false},		
		   		{name:'message',index:'message', width:80, sortable:false}	
		   	],
		   	rowNum: '',
		   	width: 600,
		    viewrecords: true,
		    height:'100%',
		    caption:"Contacts History"
		});
	</script>
</body>
</html>