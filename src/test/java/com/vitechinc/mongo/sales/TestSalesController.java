package com.vitechinc.mongo.sales;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.net.URI;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.vitechinc.mongo.sales.model.Address;
import com.vitechinc.mongo.sales.model.Image;
import com.vitechinc.mongo.sales.model.Product;
import com.vitechinc.mongo.sales.model.Review;
import com.vitechinc.mongo.sales.services.SalesService;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TestSalesController {
	 private final String imagesPath = "C:\\\\projects\\\\mongo\\\\svn\\\\ecommerce\\\\src\\\\main\\\\resources\\\\images\\"; 
	
	@LocalServerPort
	int randomServerPort;
	
	@Mock
	SalesService salesService;
	
	@Test
	public void testGetAllProducts() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity entity = new HttpEntity(httpHeaders);
		String url = "http://localhost:" + randomServerPort + "/sales/all"; 
		URI uri = new URI(url);
		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		response.getBody();
		
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddProduct() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		String url = "http://localhost:" + randomServerPort + "/sales/addProduct";
		URI uri = new URI(url);
		Product product = new Product(
                "Marriot",
                "description",
                130,
                null,
                "ball.png",
                new Address("Paris", "France"),
                Arrays.asList(
                        new Review("Teddy", 9, true)
                ),
                Arrays.asList(new Image("images/beachhouse.jpg"), 
            			  new Image("images/boy.jpg"),
            			  new Image("images/boy2.jpg"),
            			  new Image("images/flower.jpg"),
            			  new Image("images/flower2.jpg")));
		HttpEntity entity = new HttpEntity(product, httpHeaders);
		
		ResponseEntity response = restTemplate.postForEntity(url, entity, String.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	
}
