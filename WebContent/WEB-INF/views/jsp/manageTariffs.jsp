<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<link rel="stylesheet" href="css/jmesa.css" />
<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="js/jmesa.js"></script>
<script type="text/javascript" src="js/jquery.jmesa.js"></script>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Kiosk Admin</title>
<style type="text/css">
@import url(css/styles-30Jun05.css);
</style>



<script type="text/javascript">
	function ValData() {
		var regEx = /^\d+$|^\d+\.\d{0,2}$/;

		price = document.tariffForm.price.value.match(regEx);
		var level = document.getElementById('level').value

		if (!price) {
			alert('incorrect format for price');
			return false;
		}

		if (level == '') {
			alert('please enter a level');
			return false;
		}

		else
			return true;

	}
</script>

<style type="text/css">
#stats {
	width: 100%;
	border-collapse: collapse;
}

#stats td,#stats th {
	font-size: 1.5em;
	border: 1px solid #000000;
	padding: 3px 7px 2px 7px;
}

#stats th {
	font-size: 1.5em;
	text-align: left;
	padding-top: 5px;
	padding-bottom: 4px;
	background-color: #6666FF;
	color: #ffffff;
}
</style>

<link rel="stylesheet" href="css/simple-30Jun05.css" />
</head>

<body>
	<div id="document">
		<%@ include file="/css/head.jsp"%>
		<%@ include file="/css/menu.jsp"%>

		<table cellpadding="0" cellspacing="0" border="0">
			<tr valign="top">
				<td id="navCell">
					<div id="nav2">

						<ul class="breadcrumb">

							<li><a href="#">Options</a>
							</li>
						</ul>
						<ul class="navitemList">

							<li class="selected"><a href="manageTariffs.htm?type=getAdd"">Add
									Tariff</a>
							</li>



						</ul>






					</div></td>
				<td id="mainCell"><br></br>
					<h2>Tariff Manager</h2>


					<div id="mainBody" class="grid_2-3_1-3">

						<c:if test="${(type) == 'getpage'}">
							Tariffs <br></br>

							<script type="text/javascript">
								function onInvokeAction(id) {
									setExportToLimit(id, '');
									var parameterString = createParameterStringForLimit(id);
									location.href = '${pageContext.request.contextPath}/manageAudios.htm?type=getpage&'
											+ parameterString;

								}
							</script>



							<form name="allTariffs"
								action="${pageContext.request.contextPath}/manageTariffs.htm">
								${output}</form>
						</c:if>

						<c:if test="${(type) == 'getAdd'}">
							<form action="manageTariffs.htm" name="tariffForm"
								onsubmit="return (ValData())">
								<input type="hidden" name="type" value="getAdd" /> <input
									type="hidden" name="subtype" value="doAdd" />
								<table>
									<tr>
										<td>Level</td>
										<td><input type="text" name="level" />
										</td>
									</tr>
									<tr>
										<td>Price</td>
										<td><input type="text" name="price" />
										</td>

									</tr>

								</table>
								<input type="submit" name="add" value="add" />
							</form>

							<c:if test="${(subtype) == 'doAdd'}">
								<table>
									<tr>
										<td><c:choose>
												<c:when test="${(result) == true}">
Successfully added!
</c:when>
												<c:otherwise>
Failed to add record. Please contact your Systems administrator.
</c:otherwise>
											</c:choose></td>
									</tr>
								</table>
							</c:if>
						</c:if>


					</div></td>
			</tr>

		</table>



	</div>



</body>
</html>

