package ru.alikhano.cyberdisplay.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ru.alikhano.cyberdisplay.mapper.ProductDisplayMapper;
import ru.alikhano.cyberdisplay.model.DisplayProduct;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Stateless
public class ManageTopProductsService {
	
	@Inject
	GetTopProductsService getTopProductsService;
	
	private boolean needsUpdate = false;
	
	public List<DisplayProduct> displayTopProducts() {
		return ProductDisplayMapper.convertProductForDisplay(getTopProductsService.getTopProducts());
				
	}
	
	/**
	 * sets needsUpdate flag to true to enable the update of the list of top 10 products
	 */
	public void getReadyforUpdate() {
		changeUpdateState();
	}
	
	/**
	 *  sets needsUpdate flag to true
	 */
	public void changeUpdateState() {
		needsUpdate = true;
	}

	/**
	 * @return the needsUpdate flag (true/false)
	 */
	public boolean isNeedsUpdate() {
		return needsUpdate;
	}

	/**
	 * resets the flag back to false after update
	 */
	public void resetNeedsUpdate() {
		needsUpdate = false;
	}

}
