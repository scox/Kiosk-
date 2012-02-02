package com.kiosk.dao.db;

import java.util.List;

import com.kiosk.model.Tariff;

public interface TariffDBDao {

	public List<Tariff> getTariff();

	public int deleteTariff(int tariffID);

	public Tariff getIndividualTariff(int tariffID);

	public int updateTariff(int id, Double price, String level);

	public int addTariff(String level, Double price);

}
