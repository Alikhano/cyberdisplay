package ru.alikhano.cyberdisplay.ejb.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;

import ru.alikhano.cyberdisplay.model.DisplayProduct;
import ru.alikhano.cyberdisplay.service.ManageTopProductsService;

@Singleton
public class TopProductsEjbService {
	
	@Inject 
	ManageTopProductsService manageTopProductsService;
	
	private List<DisplayProduct> productsToDisplay;

	public List<DisplayProduct> getProductsToDisplay() {
		return productsToDisplay;
	}

	public void setProductsToDisplay(List<DisplayProduct> productsToDisplay) {
		this.productsToDisplay = productsToDisplay;
	}
	
	public void loadDisplay() {
		productsToDisplay = manageTopProductsService.displayTopProducts();
	}
	
	@PostConstruct
	public void init() {
		loadDisplay();
	}


}
