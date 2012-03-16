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
	function ValAddData() {

		var un = document.getElementById('username').value
		var pw = document.getElementById('password').value
		var access = document.getElementById('access').value

		if (un == '') {
			alert('please enter a username');
			return false;
		}

		if (pw == '') {
			alert('please enter a password');
			return false;
		}

		if (access == '--SELECT--') {
			alert('Please enter access right');
			return false;
		}

		else
			return true;

	}

	function ValDelData() {

		var user = document.getElementById('userID').value

		if (userID == '--SELECT--') {
			alert('Please choose a user');
			return false;
		}

		else
			return true;

	}

	function ValEditAccess() {

		var userID = document.getElementById('userID').value
		var access = document.getElementById('access').value

		if (userID == '--SELECT--') {
			alert('please enter a user');
			return false;
		}

		if (access == '--SELECT--') {
			alert('Please enter access right');
			return false;
		}

		else
			return true;

	}

	function ValChangePW() {

		var dbPW = document.getElementById('dbOldPW').value
		var oldPW = document.getElementById('oldPW').value
		var newPW = document.getElementById('newPW').value
		var confirmPW = document.getElementById('confirmPW').value

		if (dbPW != oldPW) {
			alert('Old password does not match');
			return false;
		}

		if (newPW == '') {
			alert('New password empty');
			return false;
		}

		if (newPW != confirmPW) {
			alert('New password mismatch');
			return false;
		}

		else
			return true;

	}
</script>

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

							<li class="selected"><a
								href="userManagement.htm?option=getAddUser">Add User</a>
							</li>
							<li class="selected"><a
								href="userManagement.htm?option=getEditAccess">Edit Access</a>
							</li>
							<li class="selected"><a
								href="userManagement.htm?option=getDeleteUser">Delete User</a>
							</li>
							<li class="selected"><a
								href="userManagement.htm?option=getChangePassword">Change
									Password</a>
							</li>


						</ul>
					</div></td>
				<td id="mainCell"><br></br>
					<h2>User Management</h2>


					<div id="mainBody" class="grid_2-3_1-3">


						<c:if
							test="${(option) == 'getAddUser' || (option) == 'doAddUser' }">

							<form action="userManagement.htm" name="addForm"
								onsubmit="return (ValAddData())">
								<input type="hidden" name="option" value="doAddUser" />

								<table>
									<tr>
										<td>Username</td>
										<td><input type="text" id="username" name="username"
											maxlength="20" />
										</td>
									</tr>
									<tr>
										<td>Password</td>
										<td><input type="text" id="password" name="password"
											maxlength="20" />
										</td>

									</tr>

									<tr>
										<td>Access Right</td>
										<td><select id="access" name="access">
												<option value="--SELECT--">--SELECT--</option>

												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>

										</select>
										</td>

									</tr>

								</table>
								<input type="submit" name="add" value="add" />
							</form>



							<c:if test="${(option) == 'doAddUser' }">

								<c:choose>
									<c:when test="${(added) == true }">
						
						Successfully added user
					
						</c:when>

									<c:otherwise>
						Failed to add user. Please try again.
						</c:otherwise>
								</c:choose>

							</c:if>

						</c:if>

						<c:if
							test="${(option) == 'getDeleteUser' || (option) == 'doDeleteUser' }">

							<form action="userManagement.htm" name="addForm"
								onsubmit="return (ValDelData())">
								<input type="hidden" name="option" value="doDeleteUser" />

								<table>
									<tr>
										<td>Select User</td>
										<td><select name="userID">
												<option value="--SELECT--">--SELECT--</option>

												<c:forEach items="${users}" var="users" varStatus="status">
													<option value="<c:out value="${users.userID}" />">
														<c:out value="${users.username}" />
													</option>

												</c:forEach>
										</select>
										</td>
									</tr>


								</table>
								<input type="submit" name="delete" value="delete" />
							</form>



							<c:if test="${(option) == 'doDeleteUser' }">

								<c:choose>
									<c:when test="${(deleteResult) == true }">
						
						Successfully deleted user
					
						</c:when>

									<c:otherwise>
						Failed to delete user. Please try again.
						</c:otherwise>
								</c:choose>

							</c:if>

						</c:if>



						<c:if
							test="${(option) == 'doEditAccess' || (option) == 'getEditAccess' }">

							<form action="userManagement.htm" name="editForm"
								onsubmit="return (ValEditAccess())">
								<input type="hidden" name="option" value="doEditAccess" />

								<table>
									<tr>
										<td>Select User</td>
										<td><select name="userID">
												<option value="SelectUser">Select User</option>

												<c:forEach items="${users}" var="users" varStatus="status">
													<option value="<c:out value="${users.userID}" />">
														<c:out value="${users.username}" />
														(
														<c:out value="${users.accessRight}" />
														)
													</option>

												</c:forEach>
										</select>
										</td>
										<td>New Access</td>
										<td><select name="access">
												<option value="SelectAccess">Select Access</option>

												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>

										</select>
										</td>
									</tr>


								</table>
								<input type="submit" name="edit" value="Edit" />
							</form>



							<c:if test="${(option) == 'doEditAccess' }">

								<c:choose>
									<c:when test="${(editResult) == true }">
						
						Successfully edited access level.
					
						</c:when>

									<c:otherwise>
						Failed to edit access level.
						</c:otherwise>
								</c:choose>

							</c:if>

						</c:if>





						<c:if
							test="${(option) == 'doChangePassword' || (option) == 'getChangePassword' }">

							<form action="userManagement.htm" name="editForm"
								onsubmit="return (ValChangePW())">
								<input type="hidden" name="option" value="doChangePassword" />
								<input type="hidden" name="userID"
									value="<c:out value="${user.userID}" />" /> <input
									type="hidden" name="dbOldPW"
									value="<c:out value="${user.password}" />" />
								<table>
									<tr>
										<td>Old Password</td>
										<td><input type="text" name="oldPW" />
										</td>
									</tr>

									<tr>
										<td>New Password</td>
										<td><input type="text" name="newPW" />
										</td>
									</tr>

									<tr>
										<td>Confirm New Password</td>
										<td><input type="text" name="confirmPW" />
										</td>
									</tr>

								</table>
								<input type="submit" name="edit" value="Edit" />
							</form>



							<c:if test="${(option) == 'doChangePassword' }">


								<c:choose>
									<c:when test="${(updated) == true }">
						
						Successfully changed password
						</c:when>

									<c:otherwise>
						Failed to change password.  Contact the systems administrator.
						</c:otherwise>
								</c:choose>


							</c:if>

						</c:if>


					</div></td>
			</tr>

		</table>



	</div>



</body>
</html>

