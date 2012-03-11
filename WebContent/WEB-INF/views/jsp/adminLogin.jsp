<html>
<script type="text/javascript">


function ValForm(){  
   
    var username = document.getElementById('username').value
    var password = document.getElementById('password').value
    
    if(username == ''){  
    	alert('please enter your username');  
    	return false;           
    	}
    
    if(password == ''){  
    	alert('please enter your password');  
    	return false;           
    	}
  
    
    
    else return true;  

  }  


	

</script>


<body>
<font face="verdana,arial" size=-1>
	<center>
		<table cellpadding='2' cellspacing='0' border='0' id='ap_table'>
		<tr>
		<td>
		<img  src="images/kioskAdmin.bmp" alt="" />
		<td>
		</tr>
			<tr>
				<td bgcolor="blue"><table cellpadding='0' cellspacing='0'
						border='0' width='100%'>
						<tr>
							<td bgcolor="blue" align=center
								style="padding: 2; padding-bottom: 4"><font size=-1
									color="white" face="verdana,arial"><b>Enter your
											username and password</b>
								</font>
							
						</tr>
						<tr>
							<td bgcolor="white" style="padding: 5"><br>
								<form name="form" action="admin.htm?type=login"
									 onsubmit="return ValForm()" method="post" >
									
									<center>
										<table>
											<tr>
												<td><font face="verdana,arial" size=-1>Username:</font>
												</td>
												<td><input type="text" name="username" id = "username"> 
												</td>
											</tr>
											<tr>
												<td><font face="verdana,arial" size=-1>Password:</font>
												</td>
												<td><input type="password" name="password" id = "password">
												</td>
											</tr>
											<tr>
												<td>&nbsp;
												</td>
												<td><font face="verdana,arial" size=-1><input
														type="submit" value="Enter"></font>
												</td>
											</tr>
											<tr>
												<td colspan=2>&nbsp;
												</td>
											</tr>
										
										
										</table>
									</center></form>
								</td>
						</tr>
					</table>
				</td>
			</tr>		
		</table>
			</center>
		</font>
		</body>
		</html>