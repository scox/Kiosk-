package com.kiosk.service;

import java.util.List;

import com.kiosk.model.User;

public interface UserManagementService {

	public boolean addUser(String username, String password, int access);

	public List<User> getUser();

	public boolean deleteUser(int userID);

	public boolean editAccess(int userID, int access);

	public boolean changePassword(int userID, String newPassword);

}
