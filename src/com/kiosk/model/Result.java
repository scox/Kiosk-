package com.kiosk.model;

public class Result {

	public boolean result;
	public int pin;
	public int memberPin;
	public String customerType;

	
	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public int getMemberPin() {
		return memberPin;
	}

	public void setMemberPin(int memberPin) {
		this.memberPin = memberPin;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

}
