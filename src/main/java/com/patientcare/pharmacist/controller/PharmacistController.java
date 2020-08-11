package com.patientcare.pharmacist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.patientcare.pharmacist.entity.Pillbox;


@Controller
public class PharmacistController {
	
	@Autowired
	public RestTemplate restTemplate;
	
	@Value("${PILLBOX_API}")
	private String PILLBOX_API;
	
	private String ADD_PILLBOX_PATH = "/addPillbox";
	private String GET_PILLBOX_PATH = "/getAllPillboxes";
	
	@RequestMapping(value = { "/getAllPillboxes" }, method = RequestMethod.GET)
	public ResponseEntity<String> getAllPillboxes() {
		String url = PILLBOX_API + GET_PILLBOX_PATH;
		
		String resp = restTemplate.getForObject(url, String.class);
		return new ResponseEntity<String>(resp, HttpStatus.OK);
				
	}
	
	@RequestMapping(value = { "/addPillbox" }, method = RequestMethod.POST)
	public ResponseEntity<String> addPillbox(@RequestBody Pillbox pillbox) {
		String url = PILLBOX_API + ADD_PILLBOX_PATH;
		
		String resp = restTemplate.postForObject(url, pillbox, String.class);
		return new ResponseEntity<String>(resp, HttpStatus.OK);
		
	}

}
