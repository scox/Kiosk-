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

<link rel="stylesheet" href="css/jmesa.css" />
 <script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="js/jmesa.js"></script>
<script type="text/javascript" src="js/jquery.jmesa.js"></script>

 <script type="text/javascript">


function ValData(){  
  
    var exNo = document.getElementById('exhibitNumber').value
    var tr = document.getElementById('trackInfo').value
    var level = document.getElementById('level').value
    var language = document.getElementById('language').value
    var file = document.getElementById('audio').value
   
    
    if(file == ''){  
    	alert('please choose a file');  
    	return false;           
    	}
    
   
if(exNo == ''){  
alert('please enter an exhibit number');  
return false;           
}
    
    if(tr == ''){  
    	alert('please enter the track information');  
    	return false;           
    	}
    
    if(level == '--SELECT--'){  
    	alert('Please enter the level');  
    	return false;           
    	}

    if(language == '--SELECT--'){  
    	alert('Please enter a language');  
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

	function roomlengthRestriction(elem, min, max){
		var uInput = elem.value;
		if(uInput.length >= min && uInput.length <= max){
			return true;
		}else{
			alert("Please enter a valid room number (00)");
			elem.focus();
			return false;
		}
	}
	
	
	
	 

	function validateFileExtension(fld) {

	      if(!/(\.mp3)$/i.test(fld.value)) {

	            alert("Invalid file type. mp3's only");

	            fld.form.reset();

	            fld.focus();

	            return false;

	      }

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
<%@ include file="/css/head.jsp" %>
			<%@ include file="/css/menu.jsp" %>

<table cellpadding="0" cellspacing="0" border="0">
				<tr valign="top">
					<td id="navCell">
						<div  id="nav2">
							
							<ul class="breadcrumb">
								
								<li><a href="#">Options</a></li>
							</ul>
							<ul class="navitemList">
							
					<li class="selected"><a href="manageAudios.htm?type=getAdd"">Add Audio</a></li>
					
					
					
							</ul>
							
								
							
							
							
							
						</div>
					</td>
					<td id="mainCell">
					<br></br>
							<h2>Audio Manager</h2>
							
				
						<div id="mainBody" class="grid_2-3_1-3">		
							Audios <br></br>
				
				<c:if test="${(type) == 'getpage'}">
							<script type="text/javascript"> 

function onInvokeAction(id) {   
	setExportToLimit(id, '');  
	var type = '<%=request.getAttribute("type").toString()%>'; 
	var parameterString = createParameterStringForLimit(id);   
	location.href = '${pageContext.request.contextPath}/manageAudios.htm?type='+type +'&' + parameterString;

	
	}
</script>
 
<form name="audioResults" action="${pageContext.request.contextPath}/manageAudios.htm"> 
    ${output} </form>
    </c:if>
    
    
    <c:if test="${(type) == 'getAdd'}">
    
    
    <form:form ModelAttribute="uploadAudio" onsubmit="return (ValData()&&roomlengthRestriction(document.getElementById('roomNo'), 1, 2) && validateFileExtension(this.file))" action = "addAudio.htm" enctype="multipart/form-data" method ="post">
	<input type = "hidden" name = "type" value="getAdd" />
	<input type = "hidden" name = "subtype" value="doAdd" />
<table>
<tr>
<td>Exhibit Number</td>

<td><form:input path="exhibitNumber" id="exhibitNumber" type="text"/></td>
</tr>
<tr>
<td>Track Information</td>		
<td><form:input path="trackInfo" id="trackInfo" type="text"/></td>	

</tr>
<tr>
<td>Level (DD)</td>		
<td>
<form:select
										path="level" id="level">
										<form:option value="--SELECT--" />
										<c:forEach items="${level}" var="level" varStatus="status">
											<form:option value="${level.level}" />

										</c:forEach>

									</form:select>
</td>	

</tr>
<tr>
<td>Language(DD)</td>		
<td>
<form:select
										path="language" id="language">
										<form:option value="--SELECT--" />
										<form:option value="English" />
										<form:option value="French" />
										<form:option value="German" />
										<form:option value="Spanish" />
											
									</form:select>
								</td>


</tr>

<tr>
<td>Room Number</td>		
<td><form:input path="roomNo" id="roomNo" maxlength = "2" onkeypress="return isValidNumbers(event)" /></td>	

</tr>

<tr>
<td>Audio</td>		
<td><form:input path="audio" id="audio" name = "file" type="file" onchange="return validateFileExtension(this)"/></td>	

</tr>

</table>
	<input type="submit" name="add" value="add"/>
			</form:form>
	
	<c:if test="${(subtype) == 'doAdd'}">	
	
	<c:choose>
		<c:when test="${(added) == true}">		
		Successfully added audio. 
		</c:when>
		<c:otherwise>
		Failed to add try again. 
		</c:otherwise>
		</c:choose>
	
	</c:if>
    
    
    </c:if>
			
											
			</div>
			</td>
			</tr>
			
</table>



										</div>
							


</body>
</html>

