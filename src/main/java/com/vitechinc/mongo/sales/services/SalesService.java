package com.vitechinc.mongo.sales.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.vitechinc.mongo.sales.model.Product;
import com.vitechinc.mongo.sales.model.QProduct;
import com.vitechinc.mongo.sales.model.Review;
import com.vitechinc.mongo.sales.repository.SalesRepository;

@Service
public class SalesService {
	
	@Autowired
	SalesRepository salesRepository;
	
	/**
	 * Returns the list of all products
	 * @return
	 */
	@Cacheable("products")
	public List<Product> findAllProducts() {
		return salesRepository.findAll();
	}
	
	/**
	 * Saves a product.
	 * @param product
	 */
	public void saveProduct(Product product) {
		salesRepository.save(product);
	}
	
	/**
	 * 
	 * @param productId
	 */
	public Product deleteProduct(final String productId) {
		Optional<Product> product = salesRepository.findById(productId);
		if(product.get() != null) {
			salesRepository.delete(product.get());
		}	
		return product.get();
	}
	
	/**
	 * 
	 * @param price
	 * @return
	 */
	public List<Product> findHotelsByPrice(final int price) {
		List<Product> retval = salesRepository.findByPriceLessThan(price);
		return retval;
	}
	
	/**
	 * 
	 * @param city
	 * @return
	 */
	public List<Product> findProductsByCity(String city ) {
		List<Product> retval = salesRepository.findByCity(city);
		return retval;
	}
	
	/**
	 * 
	 * @param country
	 * @return
	 */
	public List<Product> findHotelsByCountry(final String country) {
		QProduct qProduct = new QProduct("product");
		BooleanExpression boolExp = qProduct.address.country.eq(country);
		List<Product> retval = (List<Product>)salesRepository.findAll(boolExp);
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Product> findRecommendedHotels() {
		QProduct qProduct = new QProduct("product");
		int maxPrice = 100;
		int rating = 7;
		
		BooleanExpression exp1 = qProduct.price.lt(maxPrice);
		BooleanExpression exp2 = qProduct.reviews.any().rating.gt(rating);
		List<Product> retval = (List<Product>) salesRepository.findAll(exp1.and(exp2));
		return retval;
	}
	
	/**
	 * 
	 */
	public void addReviewToProduct(String producId, Review review) {
		Optional oproduct = salesRepository.findById(producId);
		if(oproduct.get() != null) {
			Product product = (Product)oproduct.get();
			product.addReview(review);
			salesRepository.save(product);
		}
	}
	
	/**
	 * 
	 */
	public void updateProductPrice(String productId, int price) {
		Optional oproduct =  salesRepository.findById(productId);
		if(oproduct.isPresent() ==  false) 
			throw new RuntimeException();
		Product product = (Product)oproduct.get();
		product.setPrice(price);
		salesRepository.save(product);
	}
	
	public List<Product> findRecommendedProducts() {
		QProduct qProduct= new QProduct("product");
		int maxPrice = 100;
		int minRate = 6;
				
		BooleanExpression exp1 = qProduct.price.lt(maxPrice);
		BooleanExpression exp2 = qProduct.reviews.any().rating.gt(minRate);
		List<Product> retval = (List<Product>)salesRepository.findAll(exp1.and(exp2));
		return retval;
	}
}
