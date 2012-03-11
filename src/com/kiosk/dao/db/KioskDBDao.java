package com.kiosk.dao.db;

import java.util.List;

/**
 * Author: Sam Cox
 * Date: 06/01/2012
 *KioskDBDao.Java:  Kiosk interface to group related methods
 */
import com.kiosk.model.Tariff;
import com.kiosk.model.Transaction;

public interface KioskDBDao {

	public int uplaodTransactionRecord(Transaction t, int paymentID,
			String today);

	public int uploadPaymentRecord(Transaction t);

	public List<Tariff> getLevels();

	public List<String> getLanguages();

	public int getLastPaymentID();

	public int checkPinExists(int pin);

	public int checkMemberPinExists(int pin);
}
