<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<link rel="stylesheet" href="css/jmesa.css" />
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
<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="js/jmesa.js"></script>
<script type="text/javascript" src="js/jquery.jmesa.js"></script>

<title>Audio Results</title>

</head>
<body>
	<table>
		<tr>
			<td><img src="images/kioskAdmin.bmp" name="image" /></td>
		</tr>
	</table>

	<c:choose>
		<c:when test="${(type) == 'doEdit' || (type) == 'delete'}">

			<c:choose>
				<c:when test="${(result) == true}">
Command performed successfully. Return <a href="admin.htm?type=home">home</a>
				</c:when>
				<c:otherwise>
Command failed! <a
						href="audioDetails.htm?type=manage&id=<c:out value="${audio.audioID}" />">Try
						again.</a>
				</c:otherwise>
			</c:choose>



		</c:when>
		<c:otherwise>



			<table id="stats">
				<tr>

					<th>ID</th>
					<th>Title</th>
					<th>Description</th>
					<th>Level</th>
					<th>Language</th>
					<th>Room No</th>


				</tr>



				
				<form action = "editAudio" enctype="multipart/form-data" method="post">

					<input type="hidden" name="type" value="doEdit"/>
					<input type ="hidden" name="audioID" value="${audio.audioID}"/>
					<tr>



						<td><c:out value="${audio.audioID}" />
						</td>
						<td><input type ="text" name ="title" id="title" value="<c:out value="${audio.title}" />" />
						</td>
						<td><input type ="text" name ="description" id="description" value="<c:out value="${audio.description}" />" />
						</td>

						<td><select name ="level" id="level">
								<option value="<c:out value="${audio.level}" />" ><c:out value="${audio.level}" /></option>

								<c:forEach items="${level}" var="level" varStatus="status">
									<option value="<c:out value="${level.level}" />" ><c:out value="${level.level}" /> </option>

								</c:forEach>

							</select>
						</td>


						<td><select name ="language" id="language">
								<option value="<c:out value="${audio.language}" />" > <c:out value="${audio.language}" /></option>

								<option value="English">English</option>
								<option value="French" >French</option>
								<option value="German" >German</option>
								<option value="Spanish" >Spanish</option>
							</select>
						</td>

						<td><input type ="text" name="roomNo" id="roomNo" value="<c:out value="${audio.roomNo}" />" />
						</td>

					</tr>
			</table>
			<table border="0">
				<tr>
					<td><input type="submit" name="update" value="Update" /> </form>
					</td>
					<td>
						<form action="audioDetails.htm">
							<input type="hidden" name="type" value="delete" /> <input
								type="hidden" name="id" value="<c:out value="${id}" />" /> <input
								type="submit" name="delete" value="Delete" />
						</form>
					</td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>


	<script type="text/javascript"> 

function onInvokeAction(id) {   
	setExportToLimit(id, '');  
	var parameterString = createParameterStringForLimit(id);   
	location.href = '${pageContext.request.contextPath}/audios.htm?'+ parameterString;

	
	}
</script>

	<form name="transactionResults"
		action="${pageContext.request.contextPath}/audios.htm">
		${output}</form>



</body>
</html>