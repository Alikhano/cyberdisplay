package ru.alikhano.cyberdisplay.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import ru.alikhano.cyberdisplay.model.DisplayProduct;
import ru.alikhano.cyberdisplay.model.ProductJson;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Stateless
public class ProductDisplayMapper {
	
	
	
	/** converts a list of ProductJson objects to DisplayProduct objects that are used to be displayed on a xhtml page
	 * @param topProducts
	 * @return list of top 10 products that is ready to be displayed on a xhtml page
	 */
	public static List<DisplayProduct> convertProductForDisplay(List<ProductJson> topProducts) {
		List<DisplayProduct> displayList = new ArrayList<>();
		for (ProductJson productJson : topProducts) {
			DisplayProduct displayProduct = new DisplayProduct();
			displayProduct.setProductId(productJson.getProductId());
			displayProduct.setModel(productJson.getModel());
			displayProduct.setPrice(productJson.getPrice());
			displayList.add(displayProduct);
		}
		
		return displayList;
	
	}

}
