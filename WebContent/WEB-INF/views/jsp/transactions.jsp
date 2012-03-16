<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/jmesa.css" />
<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="js/jmesa.js"></script>
<script type="text/javascript" src="js/jquery.jmesa.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transaction Results</title>

</head>
<body>
	<table>
		<tr>
			<td><img src="images/kioskAdmin.bmp" name="image" /></td>
		</tr>
	</table>

	<script type="text/javascript"> function onInvokeExportAction(id) {  
	var parameterString = createParameterStringForLimit(id);   
	location.href = '${pageContext.request.contextPath}/transactions.htm?' + parameterString;
	
	} 
	
	


</script>



	<script type="text/javascript"> 

function onInvokeAction(id) {   
	setExportToLimit(id, '');  
	var parameterString = createParameterStringForLimit(id);   
	location.href = '${pageContext.request.contextPath}/transactions.htm?'+ parameterString;

	
	}
</script>

	<form name="transactionResults"
		action="${pageContext.request.contextPath}/transactions.htm">
		${output}</form>



</body>
</html>