package com.kiosk.dao.db;

import java.util.List;

import com.kiosk.model.Transaction;

/**
 * Author: Sam Cox Date: 06/01/2012 AdminDBDao.Java: Admin interface to group
 * related methods
 */

public interface AdminDBDao {

	public List<Transaction> getTransactions();

}
