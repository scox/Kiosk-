package com.kiosk.model;

public class Tariff {

	private int tariffID;
	private String level;
	private Double price;

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
