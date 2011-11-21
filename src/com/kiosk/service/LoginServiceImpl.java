package com.kiosk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.dao.db.LoginDBDao;
import com.kiosk.model.User;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDBDao loginDBDao;

	@Override
	public User getAuthenticateUser(String username, String password) {
		// TODO Auto-generated method stub
		return loginDBDao.getAuthenticateUser(username, password);
	}

}