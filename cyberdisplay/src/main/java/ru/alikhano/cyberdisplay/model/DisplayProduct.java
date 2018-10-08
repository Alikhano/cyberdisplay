package ru.alikhano.cyberdisplay.model;

import java.io.Serializable;

public class DisplayProduct implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String model;
	private double price;
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "DisplayProduct [model=" + model + ", price=" + price + "]";
	}

}
