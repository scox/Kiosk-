<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Insert title here</title>
<style type="text/css">
#stats {
	width: 50%;
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
</head>



<body>

	<c:choose>
		<c:when test="${(type) == 'getpage'}">

			<table id="stats">
				<tr>

					<th>ID</th>
					<th>Level</th>
					<th>Price</th>
				</tr>




				<form action="tariffDetails.htm">
					<input type="hidden" name="type" value="doEdit" /> <input
						type="hidden" name="id"
						value="<c:out value="${tariff.tariffID}" />" />
					<tr>



						<td><c:out value="${tariff.tariffID}" /></td>
						<td><input type="text" name="level"
							value="<c:out value="${tariff.level}" />" /></td>
						<td><input type="text" name="price"
							value="<c:out value="${tariff.price}" />" /></td>

					</tr>
			</table>
			<table border="0">
				<tr>
					<td><input type="submit" name="update" value="Update" />
						</form></td>
					<td>
						<form action="tariffDetails.htm">
							<input type="hidden" name="type" value="delete" /> <input
								type="hidden" name="id"
								value="<c:out value="${tariff.tariffID}" />" /> <input
								type="submit" name="delete" value="Delete" />
						</form></td>
				</tr>
			</table>

		</c:when>
		<c:otherwise>

			<c:if test="${(type) == 'doEdit'||(type) == 'delete'}">
				<c:choose>
					<c:when test="${(result) == true}">
Command performed successfully. Return <a href="admin.htm?type=home">home</a>
					</c:when>
					<c:otherwise>
Command failed! <a
							href="tariffDetails.htm?type=getpage&id=<c:out value="${id}" />">Try
							again.</a>
					</c:otherwise>
				</c:choose>
			</c:if>


		</c:otherwise>
	</c:choose>
</body>
</html>