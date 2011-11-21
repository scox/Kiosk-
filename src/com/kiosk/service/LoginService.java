package com.kiosk.service;

import com.kiosk.model.User;

public interface LoginService {

	public User getAuthenticateUser(String username, String password);
}
