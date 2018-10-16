package ru.alikhano.cyberdisplay.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.alikhano.cyberdisplay.model.ProductJson;


@Stateless
public class GetTopProductsService {
	
	private static final String URI = "http://localhost:8080/cyberlife/topProducts";
	private ObjectMapper objectMapper = new ObjectMapper();
	private Client client = ClientBuilder.newClient();
	
	private static final Logger logger = LogManager.getLogger(GetTopProductsService.class);
	
	private String getTopProductsResponse() {
		WebTarget target = client.target(URI);
		return client.target(URI).request(MediaType.APPLICATION_JSON).get(String.class);
	}
	
	public List<ProductJson> getTopProducts() {
		String response = getTopProductsResponse();
		List<ProductJson> productsJson = new ArrayList<>();
		
		try {
			productsJson = objectMapper.readValue(response, new TypeReference<List<ProductJson>>(){});
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
		return productsJson;
	}
	
	
	
	
	

}
