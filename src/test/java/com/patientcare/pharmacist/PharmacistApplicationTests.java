package com.patientcare.pharmacist;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import com.patientcare.pharmacist.controller.PharmacistController;
import com.patientcare.pharmacist.entity.Pillbox;

@SpringBootTest
class PharmacistApplicationTests {
	
	@Autowired
	PharmacistController pharmacistController;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void getAllPillboxesTest() throws Exception {
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(pharmacistController.restTemplate);
		
		mockServer
	    .expect(MockRestRequestMatchers.requestTo(new URI("http://localhost:8081/getAllPillboxes")))
	    .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
	    .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
	        .contentType(MediaType.APPLICATION_XML)
	        .body("success"));
		
		pharmacistController.getAllPillboxes();
		
	}
	
	@Test
	public void addPillboxTest() throws Exception {
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(pharmacistController.restTemplate);
		
		mockServer
	    .expect(MockRestRequestMatchers.requestTo(new URI("http://localhost:8081/addPillbox")))
	    .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
	    .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
	        .contentType(MediaType.APPLICATION_XML)
	        .body("success"));
		
		Pillbox pillbox = new Pillbox(1, 1, "completed", 0, 10, "daily", "8:00");
		pharmacistController.addPillbox(pillbox);
		
	}

}
