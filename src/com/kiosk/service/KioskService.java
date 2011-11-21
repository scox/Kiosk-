package com.kiosk.service;

import java.util.List;

import com.kiosk.model.Tariff;
import com.kiosk.model.Result;
import com.kiosk.model.Transaction;

public interface KioskService {

	public Result uploadTransaction(Transaction t);

	public List<String> getLanguages();

	public List<Tariff> getLevels();

}
