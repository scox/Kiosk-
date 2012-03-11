package com.kiosk.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.dao.db.KioskDBDao;
import com.kiosk.model.Tariff;
import com.kiosk.model.Result;
import com.kiosk.model.Transaction;

/**
 * Author: Sam Cox
 * Date: 06/01/2012
 * Constants.Java:  This class contains the business logic for the kiosk.  All logic required to purchase a 
 * playback unit.
 */
@Service
public class KioskServiceImpl implements KioskService {

	@Autowired
	private KioskDBDao kioskDBDao;

	@Override
	public Result uploadTransaction(Transaction t) {
	
		boolean pinExists = true;

		Result result = new Result();
		result.setResult(false);
		result.setCustomerType(t.getCustomerType());
		t.setPin(t.generatePin());

		while (pinExists == true) {

			if (kioskDBDao.checkPinExists(t.getPin()) == 0) {
				//Unique pin number created
				pinExists = false;

			}
			
			//Pin exists. Generate new pin
			else {
				
				t.setPin(t.generatePin());

			}

		}

		if (t.getCustomerType().equalsIgnoreCase("GROUP")) {
			pinExists = true;

			t.setMemberPin(t.generatePin());

			while (pinExists == true) {

				if (kioskDBDao.checkMemberPinExists(t.getMemberPin()) == 0) {
					//Unique pin
					result.setMemberPin(t.getMemberPin());
					pinExists = false;

				} else {
					//Group Pin Exists. Retry
					t.setMemberPin(t.generatePin());

				}

			}

		}

		result.setPin(t.getPin());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int p = kioskDBDao.uploadPaymentRecord(t);

		int tt = kioskDBDao.uplaodTransactionRecord(t,
				kioskDBDao.getLastPaymentID(),
				sdf.format(Calendar.getInstance().getTime()));

		if (tt > 0 && p > 0) {

			result.setResult(true);
		}

		return result;

	}

	@Override
	public List<String> getLanguages() {

		return kioskDBDao.getLanguages();
	}

	@Override
	public List<Tariff> getLevels() {

		return kioskDBDao.getLevels();
	}

}