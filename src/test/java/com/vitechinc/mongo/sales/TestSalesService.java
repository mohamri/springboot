package com.vitechinc.mongo.sales;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;

import com.vitechinc.mongo.sales.model.Address;
import com.vitechinc.mongo.sales.model.Image;
import com.vitechinc.mongo.sales.model.Product;
import com.vitechinc.mongo.sales.model.Review;
import com.vitechinc.mongo.sales.repository.SalesRepository;
import com.vitechinc.mongo.sales.services.SalesService;

@RunWith(MockitoJUnitRunner.class)
public class TestSalesService {
	
	
    private final String imagesPath = "C:\\\\projects\\\\mongo\\\\svn\\\\ecommerce\\\\src\\\\main\\\\resources\\\\images\\"; 

	
	@Mock
	SalesRepository salesRepository;
	
	@InjectMocks
	SalesService salesService;
	
	/**
	 * 
	 * @throws IOException
	 */
	@Test
	public void testAddReviewToProduct() throws IOException {
		
		List<Review> reviews = new ArrayList<Review>(); 
		reviews.add( new Review("John", 8, false));
		reviews.add( new Review("Mary", 7, true));
		
		Product marriot = new Product(
                "Marriot",
                "description",
                130,
                new Binary(BsonBinarySubType.BINARY, IOUtils.toByteArray(new FileInputStream(imagesPath +  "ball.png"))),
                "ball.png",
                new Address("Paris", "France"),
                reviews,
                Arrays.asList(new Image("images/beachhouse.jpg"), 
            			  new Image("images/boy.jpg"),
            			  new Image("images/boy2.jpg"),
            			  new Image("images/flower.jpg"),
            			  new Image("images/flower2.jpg")));
		Optional<Product> oproduct = Optional.of(marriot);
		Mockito.when(salesRepository.findById(Mockito.any(String.class))).thenReturn(oproduct);
		salesService.addReviewToProduct("01",  new Review("Amri", 10, true));
		Assert.assertEquals(3, oproduct.get().getReviews().size());
		Mockito.verify(salesRepository, Mockito.times(1)).findById("01");
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	@Test(expected=RuntimeException.class)
	public void testUpdateProductPriceThrowExceptionIfProductDoesnotExist () throws IOException {
		List<Review> reviews = new ArrayList<Review>(); 
		reviews.add( new Review("John", 8, false));
		reviews.add( new Review("Mary", 7, true));
		
		Product marriot = new Product(
                "Marriot",
                "description",
                130,
                new Binary(BsonBinarySubType.BINARY, IOUtils.toByteArray(new FileInputStream(imagesPath +  "ball.png"))),
                "ball.png",
                new Address("Paris", "France"),
                reviews,
                Arrays.asList(new Image("images/beachhouse.jpg"), 
            			  new Image("images/boy.jpg"),
            			  new Image("images/boy2.jpg"),
            			  new Image("images/flower.jpg"),
            			  new Image("images/flower2.jpg")));
		Mockito.when(salesRepository.findById(Mockito.any(String.class))).thenReturn(Optional.ofNullable(null));
		salesService.updateProductPrice("01", 150);
		verify(salesRepository).findById(any(String.class));
		
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	
	@Test
	public void testUpdateProductPriceDoesUpdate () throws IOException {
		List<Review> reviews = new ArrayList<Review>(); 
		reviews.add( new Review("John", 8, false));
		reviews.add( new Review("Mary", 7, true));
		
		Product marriot = new Product(
                "Marriot",
                "description",
                130,
                new Binary(BsonBinarySubType.BINARY, IOUtils.toByteArray(new FileInputStream(imagesPath +  "ball.png"))),
                "ball.png",
                new Address("Paris", "France"),
                reviews,
                Arrays.asList(new Image("images/beachhouse.jpg"), 
            			  new Image("images/boy.jpg"),
            			  new Image("images/boy2.jpg"),
            			  new Image("images/flower.jpg"),
            			  new Image("images/flower2.jpg")));
		Mockito.when(salesRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(marriot));
		salesService.updateProductPrice("01", 150);
		Assert.assertEquals(Integer.valueOf(150), Integer.valueOf(marriot.getPrice()));
		Mockito.verify(salesRepository, times(1)).findById("01");
	}
	
	/*@Test
	public void testFindRecommendedHotelsReturnsCorrectRecommendedHotels() throws Exception {
		Product marriot = new Product(
                "Marriot",
                130,
                new Binary(BsonBinarySubType.BINARY, IOUtils.toByteArray(new FileInputStream(imagesPath +  "ball.png"))),
                new Address("Paris", "France"),
                Arrays.asList(
                        new Review("John", 8, false),
                        new Review("Mary", 7, true)
                )
        );

        Product ibis = new Product(
                "Ibis",
                90,
                new Binary(BsonBinarySubType.BINARY, IOUtils.toByteArray(new FileInputStream(imagesPath +  "ball.png"))),
                new Address("Bucharest", "Romania"),
                Arrays.asList(
                        new Review("Teddy", 9, true)
                )
        );

        Product sofitel = new Product(
                "Sofitel",
                70,
                new Binary(BsonBinarySubType.BINARY, IOUtils.toByteArray(new FileInputStream(imagesPath +  "canon.jpg"))),
                new Address("Rome", "Italy"),
                Arrays.asList(
                        new Review("Teddy", 9, true)
                )
        );
        
        List<Product> products = new ArrayList<Product>();
        products.add(marriot);
        products.add(ibis);
        products.add(sofitel);
        
        when(salesRepository.findAll()).thenReturn(products);
        List<Product> returnedProducts = salesService.findRecommendedProducts();
        Assert.assertEquals(Integer.valueOf(2), Integer.valueOf(returnedProducts.size()));
        verify(salesRepository.findAll(), times(1));
        
	}*/
}
