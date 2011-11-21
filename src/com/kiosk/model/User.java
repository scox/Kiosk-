package com.kiosk.model;

public class User {

	public int userID;
	public String username;
	public String password;
	public int accessRight;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccessRight() {
		return accessRight;
	}

	public void setAccessRight(int accessRight) {
		this.accessRight = accessRight;
	}

}
