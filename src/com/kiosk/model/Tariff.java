package com.kiosk.model;

public class Tariff {

	public int tariffID;
	public String level;
	public Double price;


	
	public int getTariffID() {
		return tariffID;
	}

	public void setTariffID(int tariffID) {
		this.tariffID = tariffID;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
