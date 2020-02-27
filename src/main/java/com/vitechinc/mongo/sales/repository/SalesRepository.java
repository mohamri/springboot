package com.vitechinc.mongo.sales.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.vitechinc.mongo.sales.model.Product;

@Repository
public interface SalesRepository extends MongoRepository<Product, String>,  QuerydslPredicateExecutor<Product> {
	public Optional<Product> findById(String id);
	List<Product> findByPriceLessThan(int price);

	@Query("{ 'address.city' : ?0 }")
	public List<Product> findByCity(String city);
}
