<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>

<title>Transaction Confirmation</title>



</head>

<body onload="redirTimer()">

	<script language="javascript">
		redirTime = "15000";
		redirURL = "index.jsp";
		function redirTimer() {
			self.setTimeout("self.location.href = redirURL;", redirTime);
		}
	//  End -->
	</script>

	<table width="1000px">
		<tr>
			<td width="25%"></td>
			<td width="50%" align="center"><img src="images/logo.bmp" alt="" />
			</td>
			<td width="25%"></td>
		</tr>
		<tr>
			<td></td>
			<td align="center"><c:choose>
					<c:when test="${(uploaded.result) == true}">

						<p>
							<font size="5" face="courier"> PIN = <c:out
									value="${uploaded.pin}" /> </font>
						</p>

						<c:if test="${(uploaded.customerType) == 'Group'}">
							<br />
							<p>
								<font size="5" face="courier"> Member PIN = <c:out
										value="${uploaded.memberPin}" /> </font>
							</p>
						</c:if>
						<br></br>
						<p>Please keep this for future reference.</p>
						<br></br>
						<p>
							return to <a href="index.jsp"><u>HOME</u> </a>
						</p>

					</c:when>
					<c:otherwise>
Unfortunately your transaction failed please <a href="index.jsp"><u>try
								again</u> </a>

					</c:otherwise>

				</c:choose>
			</td>
			<td></td>
		</tr>


	</table>

</body>
</html>