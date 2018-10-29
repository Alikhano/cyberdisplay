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

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Singleton
public class TopProductsEjbService {
	
	@Inject 
	ManageTopProductsService manageTopProductsService;
	
	@Inject
	Listener listener;
	
	private static final Logger logger = LogManager.getLogger(TopProductsEjbService.class);

	
	private List<DisplayProduct> productsToDisplay;

	/**
	 * @return list of top 10 products that are ready to be displayed
	 */
	public List<DisplayProduct> getProductsToDisplay() {
		return productsToDisplay;
	}

	public void setProductsToDisplay(List<DisplayProduct> productsToDisplay) {
		this.productsToDisplay = productsToDisplay;
	}
	
	/**
	 * fills in the list of DisplayProduct instances with top 10 products
	 */
	public void loadDisplay() {
		productsToDisplay = manageTopProductsService.displayTopProducts();
	}
	
	/**
	 * reload the display if any of top 10 products have been modified
	 * @return true/false depending on whether the actual update happened
	 */
	public boolean update() {
		
		if (manageTopProductsService.isNeedsUpdate()) {
			loadDisplay();
			logger.info("TABLE WAS UPDATED!");
			manageTopProductsService.resetNeedsUpdate();
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * starts the listener and loads the list of top 10 products on application start
	 * @throws IOException
	 * @throws TimeoutException
	 */
	@PostConstruct
	public void init() throws IOException, TimeoutException {
		listener.start();
		loadDisplay();
	}
	
	/**
	 * stops the listener on application shutdown
	 * @throws IOException
	 * @throws TimeoutException
	 */
	@PreDestroy
	private void stopListener() throws IOException, TimeoutException {
		listener.close();
	}


}
