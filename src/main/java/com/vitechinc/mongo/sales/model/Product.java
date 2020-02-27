package com.vitechinc.mongo.sales.model;

import java.util.List;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="products")
public class Product {
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Id
	private String id;
	
	private String name;
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Indexed(direction = IndexDirection.ASCENDING)
	private int price;
	private Binary image;
	private String imageName;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	private Address address;
	List<Review> reviews;
	List<Image> allImages;
	public List<Image> getAllImages() {
		return allImages;
	}

	public void setAllImages(List<Image> allImages) {
		this.allImages = allImages;
	}

	public Product() {
		
	}
	public Product(String name, String description, 
			int price, Binary image, 
					String imageName, Address address, 
							List<Review> reviews,
									List<Image> allImages) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
		this.imageName = imageName;
		this.address = address;
		this.reviews = reviews;
		this.allImages = allImages;
	}
	
	public Binary getImage() {
		return image;
	}

	public void setImage(Binary image) {
		this.image = image;
	}
	
	public void addReview(Review review) {
		reviews.add(review);
	}
	
	
}
