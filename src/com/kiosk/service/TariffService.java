package com.kiosk.service;

/**
 * Author: Sam Cox
 * Date: 06/01/2012
 * TariffService.Java: Java interface
 */
public interface TariffService {


	public boolean deleteTariff(int tariffID);

	public boolean updateTariff(int ID, Double price, String Level);

	public boolean addTariff(String level, Double price);

}
