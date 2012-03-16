package com.kiosk.service;

/**
 * Author: Sam Cox
 * Date: 06/01/2012
 * KioskService.Java: Java interface
 */

import java.util.List;

import com.kiosk.model.Tariff;
import com.kiosk.model.Result;
import com.kiosk.model.Transaction;

public interface KioskService {

	public Result uploadTransaction(Transaction t);

	public List<String> getLanguages();

	public List<Tariff> getLevels();

}
