package com.kiosk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.dao.db.TariffDBDao;
/**
 * Author: Sam Cox
 * Date: 06/01/2012
 * TariffServiceImpl.Java:  This class contains the business logic related to tariff management component.
 */
@Service
public class TariffServiceImpl implements TariffService {

	@Autowired
	private TariffDBDao tariffDBDao;


	@Override
	public boolean deleteTariff(int tariffID) {

		int i = tariffDBDao.deleteTariff(tariffID);

		if (i > 0) {
			return true;
		}

		else {
			return false;
		}
	}

	@Override
	public boolean updateTariff(int id, Double price, String level) {

		int i = tariffDBDao.updateTariff(id, price, level);
		if (i > 0) {
			return true;
		} 
		else {
			return false;
		}
	}

	@Override
	public boolean addTariff(String level, Double price) {

		int i = tariffDBDao.addTariff(level, price);

		if (i > 0) {
			return true;
		}
		else {
			return false;
		}
	}

}