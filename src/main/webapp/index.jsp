<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<META Http-Equiv="Cache-Control" Content="no-cache, no-store, must-revalidate">
<META Http-Equiv="Pragma" Content="no-cache">
<META Http-Equiv="Expires" Content="0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="css/base.css"/>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css"/>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/parsley.js"></script>
<script type="text/javascript" src="js/formToJson.js"></script>
</head>
<body>
	<div class="ui-state-highlight">
		<span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
		Welcome To User Demo System
	</div>
	<div class="container">
		<form id="loginForm" data-validate="parsley">
			<table>
				<tr>
					<td>Username:</td>
					<td><input id="txtUsername" name="username" type="text" data-required="true" data-maxlength="12"/></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input id="txtPassword" name="password" type="password" data-required="true"  data-maxlength="12"/></td>
				</tr>
				<tr>
					<td colspan="2"><button id="login" type="submit">Login</button></td>
				</tr>
			</table>
		</form>		
		
		<div>
			Features:
			<ul>
				<li>Spring Security</li>
				<li>Spring MVC</li>
				<li>JQuery UI</li>
				<li>JQ Grid</li>
				<li>Anti Samy Layer{With Spring AOP}</li>
				<li>BASE 64 Encryption</li>
				<li>Postgresql {Heroku Cloud}</li>
				<li>IText</li>
				<li>JQuery</li>
				<li>Parsley js</li>
				<li>My own Lib for converting Forms to JSON</li>
				<li>Java Mail with Spring Mail template</li>
				<li>Spring JDBC</li>
				<li>Dynamic Tomcat Instance invocation for Heroku</li>
			</ul>
		</div>
	</div>
	<p class="loader">&nbsp;</p>
	<div id="message" class="ui-state-error">
		<span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
		Your username or password is incorrect!!
	</div>
	<script type="text/javascript">
		$(function(){
			$("#message").hide();
			$("button").button();	
			$('#txtPassword').on("keypress", function(e) {
		        if (e.keyCode == 13) {
		        	$("#login").click();   
		            return false; // prevent the button click from happening
		        }
			});
			
			$('#loginForm').submit(function(e) { 
		        e.preventDefault();
		        if ( $(this).parsley('validate') ) {
		        	$(".loader").show();
		        	$( "button" ).button({ disabled: true });
					$("#message").hide();
					$.ajax({
		                url: "tryLogin",
		                type: "POST",
		                dataType:"json",
		                data: $('#loginForm').serializeObject()
				        }).done(function(data){
				        		$(".loader").hide();
				        		$( "button" ).button({ disabled: false });
				                if(data.success===true){
				                        window.location = data.url.substring(1,data.url.length);
				                }
				                else{
				                	makeLoginInvalid();                		    
				        		}
				        }).fail(function(){
				    		makeLoginInvalid();
				        	return false;
				        });   
		        }
		    });
		});	
		function makeLoginInvalid(){
			$(".loader").hide();
			$("#message").show();
			$( "button" ).button({ disabled: false });
		}
	</script>
</body>
</html>