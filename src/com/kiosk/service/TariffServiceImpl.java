package com.kiosk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.dao.db.TariffDBDao;
import com.kiosk.model.Tariff;

@Service
public class TariffServiceImpl implements TariffService {

	@Autowired
	private TariffDBDao tariffDBDao;

	@Override
	public List<Tariff> getTariffs() {
		return tariffDBDao.getTariff();
	}

	@Override
	public boolean deleteTariff(int tariffID) {
		// TODO Auto-generated method stub

		int i = tariffDBDao.deleteTariff(tariffID);

		if (i > 0) {
			return true;
		}

		else {
			return false;
		}
	}

	@Override
	public Tariff getIndividualTariffs(int tariffID) {
		// TODO Auto-generated method stub
		return tariffDBDao.getIndividualTariff(tariffID);
	}

	@Override
	public boolean updateTariff(int id, Double price, String level) {
		// TODO Auto-generated method stub

		int i = tariffDBDao.updateTariff(id, price, level);

		if (i > 0) {
			return true;
		} else {
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