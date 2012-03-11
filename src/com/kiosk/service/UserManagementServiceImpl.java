package com.kiosk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.dao.db.UserManagementDBDao;
import com.kiosk.model.User;
/**
 * Author: Sam Cox
 * Date: 06/01/2012
 * UserManagementServiceImpl.Java:  This class contains the business logic for the user management component
 */
@Service
public class UserManagementServiceImpl implements UserManagementService {

	@Autowired
	private UserManagementDBDao userManagementDao;

	@Override
	public boolean addUser(String username, String password, int access) {

		int i = userManagementDao.addUser(username, password, access);

		if (i > 0) {
			return true;
		}

		else {
			return false;
		}

	}

	@Override
	public List<User> getUser() {

		return userManagementDao.getUsers();
	}

	@Override
	public boolean deleteUser(int userID) {
		int i = userManagementDao.deleteUser(userID);

		if (i > 0) {
			return true;
		}

		else {
			return false;
		}

	}

	@Override
	public boolean editAccess(int userID, int access) {

		int i = userManagementDao.editAccess(userID, access);

		if (i > 0) {
			return true;
		}

		else {
			return false;
		}

	}

	@Override
	public boolean changePassword(int userID, String newPassword) {
		int i = userManagementDao.changePassword(userID, newPassword);

		if (i > 0) {
			return true;
		}

		else {
			return false;
		}
	}

}
