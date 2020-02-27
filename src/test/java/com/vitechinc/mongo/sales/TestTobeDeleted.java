package com.vitechinc.mongo.sales;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TestTobeDeleted {
	
	@LocalServerPort
	private int randomPort;
	
	@Test
	public void testFindAll() {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity httpEntity = new HttpEntity(null, new HttpHeaders());
		String url = "localhost:" + randomPort + "/sales/all";
		restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
	}
}
