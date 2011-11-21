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

@Service
public class KioskServiceImpl implements KioskService {

	@Autowired
	private KioskDBDao kioskDBDao;

	@Override
	public Result uploadTransaction(Transaction t) {
		// TODO Auto-generated method stub

		boolean pinExists = true;

		Result result = new Result();
		result.setResult(false);
		t.generatePin();

		while (pinExists == true) {

			if (kioskDBDao.checkPinExists(t.getPin()) == 0) {
				System.out.println("unique pin");
				pinExists = false;
			} else {
				System.out.println("pin exists");
				t.generatePin();

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