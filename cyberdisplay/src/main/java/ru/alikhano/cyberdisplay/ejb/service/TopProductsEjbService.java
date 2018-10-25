package ru.alikhano.cyberdisplay.ejb.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.alikhano.cyberdisplay.model.DisplayProduct;
import ru.alikhano.cyberdisplay.service.ManageTopProductsService;

@Singleton
public class TopProductsEjbService {
	
	@Inject 
	ManageTopProductsService manageTopProductsService;
	
	@Inject
	Listener listener;
	
	private static final Logger logger = LogManager.getLogger(TopProductsEjbService.class);

	
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
	
	public boolean update() {
		
		if (manageTopProductsService.isNeedsUpdate()) {
			loadDisplay();
			logger.info("TABLE WAS UPDATED!");
			manageTopProductsService.resetNeedsUpdate();
			return true;
		}
		
		return false;
		
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
