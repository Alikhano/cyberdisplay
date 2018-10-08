package ru.alikhano.cyberdisplay.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.alikhano.cyberdisplay.mapper.ProductDisplayMapper;
import ru.alikhano.cyberdisplay.model.DisplayProduct;

@Stateless
public class ManageTopProductsService {
	
	@Inject
	GetTopProductsService getTopProductsService;
	
	
	private static final Logger logger = LogManager.getLogger(ManageTopProductsService.class);
	
	public List<DisplayProduct> displayTopProducts() {
		List<DisplayProduct> list = ProductDisplayMapper.convertProductForDisplay(getTopProductsService.getTopProducts());
		return list;
	}
	
	

}
