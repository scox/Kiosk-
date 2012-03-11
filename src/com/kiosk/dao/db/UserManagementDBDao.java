package com.kiosk.dao.db;

import java.util.List;

import com.kiosk.model.User;

/**
 * Author: Sam Cox Date: 06/01/2012 UserManagementDBDao.Java: User management
 * interface to group related methods
 */

public interface UserManagementDBDao {

	public int addUser(String username, String password, int access);

	public List<User> getUsers();

	public int deleteUser(int userID);

	public int editAccess(int userID, int access);

	public int changePassword(int userID, String newPassword);

}