<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.kiosk.web.KioskController"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Transaction Form</title>

<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<!-- CSS -->
<link rel="stylesheet" href="css/structure.css" type="text/css" />
<link rel="stylesheet" href="css/form.css" type="text/css" />
<script type="text/javascript">


function ValNumber(){  
    //var regEx = /^\d{1,2}\.\d{1,2}$/;  
    var regEx = /^\d+$|^\d+\.\d{0,2}$/;  
    var custname = document.getElementById('custname').value
    var address = document.getElementById('address').value
    var postcode = document.getElementById('postcode').value
    var language = document.getElementById('language').value
    var level = document.getElementById('level').value
    var cardtype = document.getElementById('cardtype').value

    
   
if(custname == ''){  
alert('please enter an cust name');  
return false;           
}

    if(address == ''){  
    	alert('please enter your address');  
    	return false;           
    	}
    if(postcode == ''){  
    	alert('please enter your postcode');  
    	return false;           
    	}
    if(language == '--SELECT--'){  
    	alert('please enter your language');  
    	return false;           
    	}
    if(level == '--SELECT--'){  
    	alert('please enter your level');  
    	return false;           
    	}
    if(cardtype == '--SELECT--'){  
    	alert('please enter your card type');  
    	return false;           
    	}
    
    
    else return true;  

  }  


function isValidNumbers(evt) {
	  if (evt.which != 0) {
	    var charCode = (evt.which) ? evt.which : event.keyCode
		if (charCode > 31 && (charCode < 48 || charCode > 57)){
	      return false;
	    } else {
	      return true;
	    }
	  }
	} 

	function cardnumberlengthRestriction(elem, min, max){
		var uInput = elem.value;
		if(uInput.length >= min && uInput.length <= max){
			return true;
		}else{
			alert("Please enter 16 digit card number");
			elem.focus();
			return false;
		}
	}
	

	function monthlengthRestriction(elem, min, max){
		var uInput = elem.value;
		if(uInput.length >= min && uInput.length <= max){
			return true;
		}else{
			alert("Please enter month in the format MM");
			elem.focus();
			return false;
		}
	}
	

	function yearlengthRestriction(elem, min, max){
		var uInput = elem.value;
		if(uInput.length >= min && uInput.length <= max){
			return true;
		}else{
			alert("Please enter year in the format YYYY");
			elem.focus();
			return false;
		}
	}
	
	function seccodelengthRestriction(elem, min, max){
		var uInput = elem.value;
		if(uInput.length >= min && uInput.length <= max){
			return true;
		}else{
			alert("Please enter valid security code (xxx)");
			elem.focus();
			return false;
		}
	}
	

</script>
</head>

<body id="public">
	<div id="container" class="ltr">

		<h1 id="logo">
			<br></br>
		</h1>



		<header id="header" class="info">
		<h2>
			<img src="images/logo.bmp" alt="" /> <br></br> &nbsp; &nbsp; Audio
			Transaction Form <br></br>
		</h2>
		<div></div>
		</header>




		<table>
			<tr>
				<td width="25"></td>
				<td>
				 <form:form name="transForm"
						onsubmit="return (ValNumber()&&
						cardnumberlengthRestriction(document.getElementById('cardno'), 16, 16)&&
						monthlengthRestriction(document.getElementById('month'), 1, 2)&&
						yearlengthRestriction(document.getElementById('year'), 4, 4)&&
						seccodelengthRestriction(document.getElementById('seccode'), 3, 3))"
						method="post" action="confirm.htm">
						<table align = "center">
							
							<tr>
							<td colspan = "4" align = "center">
							<b>Customer Details</b>
							
							</td>
							
							</tr>
							
							<tr>

								<td><label class="desc"> Name </label> <span> <form:input
											path="name" id="custname" size="8" tabindex="1" /> </span>
											
											
								</td>
								<td><label class="desc"> Address </label> <span> <form:input
											path="address" id="address" type="text" size="8" tabindex="1" />
								</span>
								</td>
							</tr>

							<tr>
								<td><label class="desc"> Post Code </label> <span> <form:input
											path="postCode" id="postcode" type="text" size="8"
											tabindex="1" /> </span>
								</td>

								<td><label class="desc"> Tel No </label> <span> <form:input
											path="telNo" id="tel" type="text" size="8" tabindex="1" /> </span>
								</td>
							</tr>

							<tr>
								<td><label class="desc"> Level </label> <form:select
										path="level" id="level">
										<form:option value="--SELECT--" />
										<c:forEach items="${level}" var="level" varStatus="status">
											<form:option value="${level.level}(£${level.price})" />

										</c:forEach>

									</form:select>
								</td>
								<td><label class="desc"> Language </label> 
								
								<form:select
										path="language" id="language">
										<form:option value="--SELECT--" />
										<c:forEach items="${language}" var="language" varStatus="status">
											<form:option value="${language}" />
											</c:forEach>
											</form:select>
								</td>

							</tr>
							
							
							<tr>
								<td><label class="desc"> Email </label> <span> <form:input
											path="email" id="email" type="text" size="18"
											tabindex="1" /> </span>
								</td>

							</tr>

							<tr>
								<td colspan="4" align ="center"><br></br>
									<hr></hr><b> Payment Details </b></td>
							</tr>
							<tr>
								<td><label class="desc"> Card Type </label>
									<div>
										<form:select path="cardType" id="cardtype">
											<form:option value="--SELECT--" />

											<form:option value="Visa" />
											<form:option value="MasterCard" />
											<form:option value="Maestro" />
										</form:select>
									</div>
								</td>
								<td></td>
								<td colspan="2"><label class="desc"> Card Number </label> <span>
										<form:input path="cardNo" id="cardno" name="cardno"
											maxlength="16" type="text" size="28" tabindex="1"
											onkeypress="return isValidNumbers(event)" /> </span>
								</td>
								<td></td>
							</tr>


							<tr>
								<td><label class="desc"> Security Code </label> <span>
										<form:input path="secCode" id="seccode" name="secCode"
											type="text" maxlength="3" size="8" tabindex="1" onkeypress="return isValidNumbers(event)"/> </span>
								</td>
								<td></td>
								<td><label class="desc"> MM </label> <span> <form:input
											path="month" id="month" maxlength="2" name="month"
											type="text" size="8" tabindex="1"
											onkeypress="return isValidNumbers(event)" /> </span>
								</td>
								<td><label class="desc"> YYYY </label> <span> <form:input
											path="year" id="year" name="year" maxlength="4" type="text"
											value="" size="8" tabindex="1"
											onkeypress="return isValidNumbers(event)" /> </span>
								</td>
							</tr>
							<tr>
								<td colspan = "4" align = "center"><input id="purchase" name="purchase" type="submit"
									value="Purchase" />
								</td>
							</tr>
						</table>
					</form:form>
				</td>
			</tr>
		</table>

	</div>
	<!--container-->
	
</body>
</html>