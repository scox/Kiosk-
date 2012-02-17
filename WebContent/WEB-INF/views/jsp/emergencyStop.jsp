<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:choose>
<c:when test="${triggerStatus == true}">
Emergency Alert Triggered!! <a
			href="admin.htm?type=home">Home</a>
</c:when>
<c:otherwise>

Failed to trigger alert!! RUN <a
			href="admin.htm?type=home">Home</a>

</c:otherwise>
</c:choose>



</body>
</html>