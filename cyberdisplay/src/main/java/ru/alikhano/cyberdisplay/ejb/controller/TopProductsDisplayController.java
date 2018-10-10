package ru.alikhano.cyberdisplay.ejb.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ru.alikhano.cyberdisplay.ejb.service.TopProductsEjbService;
import ru.alikhano.cyberdisplay.model.DisplayProduct;

@Named("topManagerBean")
@ViewScoped
public class TopProductsDisplayController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	TopProductsEjbService topProductsEjbService;
	
	public List<DisplayProduct> getTopProducts() {
		return topProductsEjbService.getProductsToDisplay();
	}
	
	public void update() {
		topProductsEjbService.update();
	}

}
