package com.kiosk.dao.db;

import java.util.List;

import com.kiosk.model.Transaction;

public interface AdminDBDao {

	public List<Transaction> getTransactions();

}
