package ru.alikhano.cyberdisplay.ejb.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.inject.Inject;

import ru.alikhano.cyberdisplay.model.DisplayProduct;
import ru.alikhano.cyberdisplay.service.ManageTopProductsService;

@Singleton
public class TopProductsEjbService {
	
	@Inject 
	ManageTopProductsService manageTopProductsService;
	
	@Inject
	Listener listener;

	
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
	
	public void update() {
		
		if (manageTopProductsService.isNeedsUpdate()) {
			loadDisplay();
			manageTopProductsService.resetNeedsUpdate();
		}
		
	}
	
	@PostConstruct
	public void init() throws IOException, TimeoutException {
		listener.start();
		loadDisplay();
	}
	
	@PreDestroy
	private void stopListener() throws IOException, TimeoutException {
		listener.close();
	}


}
