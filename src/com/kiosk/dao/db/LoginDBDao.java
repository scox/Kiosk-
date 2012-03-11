package com.kiosk.dao.db;

import com.kiosk.model.User;

/**
 * Author: Sam Cox Date: 06/01/2012 LoginDBDao.Java: Login interface to group
 * related methods
 */

public interface LoginDBDao {

	public User getAuthenticateUser(String usr, String pw);

}
