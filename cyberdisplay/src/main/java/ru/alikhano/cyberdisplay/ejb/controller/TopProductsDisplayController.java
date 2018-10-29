package ru.alikhano.cyberdisplay.ejb.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.alikhano.cyberdisplay.ejb.service.TopProductsEjbService;
import ru.alikhano.cyberdisplay.model.DisplayProduct;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Named("topManagerBean")
@ViewScoped
public class TopProductsDisplayController implements Serializable {
	
	/**
	 * 
	 */
	private static final Logger logger = LogManager.getLogger(TopProductsDisplayController.class);
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	TopProductsEjbService topProductsEjbService;
	
	/**
	 * retrieves top 10 products from a web service
	 * @return list of top 10 products that are ready to be displayed 
	 */
	public List<DisplayProduct> getTopProducts() {
		logger.info("top products are displayed on start");
		return topProductsEjbService.getProductsToDisplay();
	}
	
	/**
	 * updates the list of top 10 products after modification on server side (update is performed only if the message is received from server)
	 * @return true/false depending on whether actual update has been performed
	 */
	public boolean update() {
		return topProductsEjbService.update();
		
	}
	


}
