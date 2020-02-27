package com.vitechinc.mongo.sales.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.vitechinc.mongo.sales.model.Address;
import com.vitechinc.mongo.sales.model.Image;
import com.vitechinc.mongo.sales.model.Product;
import com.vitechinc.mongo.sales.model.Review;
import com.vitechinc.mongo.sales.repository.SalesRepository;

@Component
public class DbSeeder implements CommandLineRunner{
	private SalesRepository salesRepository;
	
	@Value("${images.root.path}")
    private String imagesPath;

	
	public DbSeeder (SalesRepository salesRepository) {
		this.salesRepository = salesRepository;
	}
	
	public void run(String...strings ) throws IOException {
		
		FileInputStream inputStream = new FileInputStream(imagesPath +  "ball.png");
		final String description = " It is a good platform to learn programming. It is an \r\n" + 
				"                    educational website. Prepare for the Recruitment drive  \r\n" + 
				"                    of product based companies like Microsoft, Amazon,  \r\n" + 
				"                    Adobe etc with a free online placement preparation \r\n" + 
				"                    course. The course focuses on various MCQ's & Coding  \r\n" + 
				"                    question likely to be asked in the interviews & make \r\n" + 
				"                    your upcoming placement season efficient and successful. ";
		Product marriot = new Product(
                "Marriot",
                description,
                130,
                new Binary(BsonBinarySubType.BINARY, IOUtils.toByteArray(new FileInputStream(imagesPath +  "ball.png"))),
                "images/ball.png",
                new Address("Paris", "France"),
                Arrays.asList(
                        new Review("John", 8, false),
                        new Review("Mary", 7, true)),
                Arrays.asList(new Image("images/beachhouse.jpg"), 
                			  new Image("images/boy.jpg"),
                			  new Image("images/boy2.jpg"),
                			  new Image("images/flower.jpg"),
                			  new Image("images/flower2.jpg"))
       );

        Product ibis = new Product(
                "Ibis",
                description,
                90,
                new Binary(BsonBinarySubType.BINARY, IOUtils.toByteArray(new FileInputStream(imagesPath +  "ball.png"))),
                "images/ball.png",
                new Address("Bucharest", "Romania"),
                Arrays.asList(
                        new Review("Teddy", 9, true)
                ),
                Arrays.asList(new Image("images/beachhouse.jpg"), 
          			  new Image("images/boy.jpg"),
          			  new Image("images/boy2.jpg"),
          			  new Image("images/flower.jpg"),
          			  new Image("images/flower2.jpg"))
        );

        Product sofitel = new Product(
                "Sofitel",
                description,
                200,
                new Binary(BsonBinarySubType.BINARY, IOUtils.toByteArray(new FileInputStream(imagesPath +  "canon.jpg"))),
                "images/ball.png",
                new Address("Rome", "Italy"),
                new ArrayList<>(),
                Arrays.asList(new Image("images/beachhouse.jpg"), 
          			  new Image("images/boy.jpg"),
          			  new Image("images/boy2.jpg"),
          			  new Image("images/flower.jpg"),
          			  new Image("images/flower2.jpg"))
        );
        Product marriot2 = new Product(
                "Marriot",
                description,
                130,
                new Binary(BsonBinarySubType.BINARY, IOUtils.toByteArray(new FileInputStream(imagesPath +  "ball.png"))),
                "images/ball.png",
                new Address("Paris", "France"),
                Arrays.asList(
                        new Review("John", 8, false),
                        new Review("Mary", 7, true)
                ),
                Arrays.asList(new Image("images/beachhouse.jpg"), 
          			  new Image("images/boy.jpg"),
          			  new Image("images/boy2.jpg"),
          			  new Image("images/flower.jpg"),
          			  new Image("images/flower2.jpg"))
        );

        Product ibis2 = new Product(
                "Ibis",
                description,
                90,
                new Binary(BsonBinarySubType.BINARY, IOUtils.toByteArray(new FileInputStream(imagesPath +  "ball.png"))),
                "images/ball.png",
                new Address("Bucharest", "Romania"),
                Arrays.asList(
                        new Review("Teddy", 9, true)
                ),
                Arrays.asList(new Image("images/beachhouse.jpg"), 
          			  new Image("images/boy.jpg"),
          			  new Image("images/boy2.jpg"),
          			  new Image("images/flower.jpg"),
          			  new Image("images/flower2.jpg"))
        );

        Product sofitel2 = new Product(
                "Sofitel",
                description,
                200,
                new Binary(BsonBinarySubType.BINARY, IOUtils.toByteArray(new FileInputStream(imagesPath +  "canon.jpg"))),
                "images/ball.png",
                new Address("Rome", "Italy"),
                new ArrayList<>(),
                Arrays.asList(new Image("images/beachhouse.jpg"), 
          			  new Image("images/boy.jpg"),
          			  new Image("images/boy2.jpg"),
          			  new Image("images/flower.jpg"),
          			  new Image("images/flower2.jpg"))
        );

        // drop all hotels
        this.salesRepository.deleteAll();
        salesRepository.deleteAll();
        //add our hotels to the database
        List<Product> hotels = Arrays.asList(marriot, ibis, sofitel, marriot2, ibis2, sofitel2);
        for(Product hotel : hotels) {
        	try {
        		salesRepository.save(hotel);
        	} catch(Exception exc) {
        		exc.printStackTrace();
        	}
        	
        }
        salesRepository.findAll();
	}

}
