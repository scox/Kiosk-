package com.kiosk.dao.db;

import com.kiosk.model.User;

public interface LoginDBDao {

	public User getAuthenticateUser(String usr, String pw);

}
 