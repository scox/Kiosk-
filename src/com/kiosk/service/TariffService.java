package com.kiosk.service;


public interface TariffService {


	public boolean deleteTariff(int tariffID);

	public boolean updateTariff(int ID, Double price, String Level);

	public boolean addTariff(String level, Double price);

}
