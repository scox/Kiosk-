<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Kiosk Admin</title>
<style type="text/css">
@import url(css/styles-30Jun05.css);
</style>
<link rel="stylesheet" href="css/simple-30Jun05.css" />
</head>

<body>
<div id="document">

			<%@ include file="/css/head.jsp" %>
			<%@ include file="/css/menu.jsp" %>
<table cellpadding="0" cellspacing="0" border="0">
	<tr valign="top">
		<td id="navCell"></td>
		<td>

		<table cellspacing="0" cellpadding="0" border="0" width = "770">
			
				<tr>	
				<td align = "center">
				<br/>
			<form>
<input type="button" value="Emergency Stop" onclick="window.location.href='emergencyStop.htm'"/> 
</form>
			</td>
			</tr>
			<tr>
	

				<td align = "center">
				
				<c:if test="${type == 'home'}">
				
				<img src="images/home.png"/>
				
				</c:if>
				
				<c:if test="${type == 'help'}">
				
				<img src="images/help.png"/>
				
				</c:if>
				
				</td>
				
			</tr>
			
				
					</table>
					
					
					</td>
				<td width="220">&nbsp;</td>
			</tr>


		</table>


</div>
</body>
</html>

