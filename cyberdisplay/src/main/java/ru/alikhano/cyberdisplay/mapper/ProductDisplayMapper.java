package ru.alikhano.cyberdisplay.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import ru.alikhano.cyberdisplay.model.DisplayProduct;
import ru.alikhano.cyberdisplay.model.ProductJson;

@Stateless
public class ProductDisplayMapper {
	
	public static List<DisplayProduct> convertProductForDisplay(List<ProductJson> topProducts) {
		List<DisplayProduct> displayList = new ArrayList<>();
		for (ProductJson productJson : topProducts) {
			DisplayProduct displayProduct = new DisplayProduct();
			displayProduct.setModel(productJson.getModel());
			displayProduct.setPrice(productJson.getPrice());
			displayList.add(displayProduct);
		}
		
		return displayList;
	
	}

}
