package com.kiosk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.dao.db.AdminDBDao;
import com.kiosk.model.Transaction;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDBDao adminDBDao;

	@Override
	public List<Transaction> getTransactions() {
		// TODO Auto-generated method stub
		return adminDBDao.getTransactions();
	}

}