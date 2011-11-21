package com.kiosk.service;

import java.util.List;

import com.kiosk.model.Tariff;

public interface TariffService {



	public List<Tariff> getTariffs();
	public boolean deleteTariff(int tariffID);
	public Tariff getIndividualTariffs(int tariffID);
	public boolean updateTariff(int ID, Double price, String Level);
	public boolean addTariff(String level, Double price);

}
