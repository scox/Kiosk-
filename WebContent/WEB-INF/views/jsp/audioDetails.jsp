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

        <script type="text/javascript" language="javascript">
        function confirmDelete()
        {return confirm('Are you sure you want to delete this?');}
        </script>


</head>
<body>
	<table>
		<tr>
			<td><img src="images/kioskAdmin.bmp" name="image" /></td>
		</tr>
	</table>

	<c:choose>
		<c:when test="${(type) == 'delete'}">

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
					<th>Exhibit Number</th>
					<th>Track Info</th>
					<th>Level</th>
					<th>Language</th>
					<th>Room No</th>
					<th>Audio</th>


				</tr>



				
	
					<tr>



						<td><c:out value="${audio.audioID}" />
						</td>
						<td><c:out value="${audio.exhibitNumber}"/>
						</td>
						<td><c:out value="${audio.trackInfo}" />
						</td>

						<td><c:out value="${audio.level}" />

						</td>


						<td><c:out value="${audio.language}" />
						</td>

						<td><c:out value="${audio.roomNo}" />
						</td>
						<td><a
			href="playAudio.htm?audioID=<c:out value="${audio.audioID}" />">Play</a>
						</td>
						

					</tr>
			</table>
			<table border="0">
				<tr>
					<td>
					
					
						<form action="audioDetails.htm" onsubmit='return confirmDelete()'>
							<input type="hidden" name="type" value="delete" /> <input
								type="hidden" name="id" value="<c:out value="${id}" />" /> <input
								type="submit" name="delete" value="Delete"/>
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