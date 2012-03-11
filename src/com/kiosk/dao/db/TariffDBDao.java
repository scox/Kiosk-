package com.kiosk.dao.db;

import java.util.List;

import com.kiosk.model.Tariff;

/**
 * Author: Sam Cox Date: 06/01/2012 TariffDBDao.Java: Tariff interface to group
 * related methods
 */

public interface TariffDBDao {

	public List<Tariff> getTariffs();

	public int deleteTariff(int tariffID);

	public Tariff getIndividualTariff(int tariffID);

	public int updateTariff(int id, Double price, String level);

	public int addTariff(String level, Double price);

}
