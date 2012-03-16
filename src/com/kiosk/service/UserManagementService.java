package com.kiosk.service;

/**
 * Author: Sam Cox
 * Date: 06/01/2012
 * UserManagementService.Java: Java interface
 */
import java.util.List;

import com.kiosk.model.User;

public interface UserManagementService {

	public boolean addUser(String username, String password, int access);

	public List<User> getUser();

	public boolean deleteUser(int userID);

	public boolean editAccess(int userID, int access);

	public boolean changePassword(int userID, String newPassword);

}
