package com.vitechinc.mongo.sales.controller;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.vitechinc.mongo.sales.model.Product;
import com.vitechinc.mongo.sales.model.QProduct;
//import com.vitechinc.mongo.sales.model.QProduct;
import com.vitechinc.mongo.sales.repository.SalesRepository;
import com.vitechinc.mongo.sales.services.SalesService;


@RestController
@RequestMapping("/sales")
public class SalesController {
	@Autowired
	SalesService salesService;
	

	@GetMapping("/all")
	public ResponseEntity getAllProducts() {
		List<Product> retval = salesService.findAllProducts();
		return ResponseEntity.ok().body(retval);
	}
	
	@PostMapping("/addProduct")
	public ResponseEntity<Void> addProduct(@RequestBody Product product) {
		salesService.saveProduct(product);
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(product.getId()).toUri();
		 return ResponseEntity.created(location).build();
		 
	}
	
	@DeleteMapping("/deleteProduct/{productId}")
	public ResponseEntity<Product> deleteHotel(@PathVariable String productId) {
		Product product = salesService.deleteProduct(productId);
		return ResponseEntity.ok().body(product);
	}
	
	@GetMapping("/lessthan/{price}")
	public List<Product> hotelByPrice(@PathVariable int price) {
		List<Product> retval = salesService.findHotelsByPrice(price);
		return retval;
	}
	
	@GetMapping("productsByCity/{city}")
	public List<Product> productsByCityVariable(@PathVariable String city ) {
		List<Product> retval = salesService.findProductsByCity(city);
		return retval;
	}
	
	@GetMapping("productsByCountry/{country}")
	public List<Product> hotelsByCountry(@PathVariable String country) {
		
		List<Product> retval = (List<Product>)salesService.findHotelsByCountry(country);
		return retval;
	}

	@GetMapping("/recommended")
	public List<Product> recommendedHotels() {
		List<Product> retval = (List<Product>)salesService.findRecommendedHotels();
		return retval;
	};
}
