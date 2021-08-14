package com.mybox.gateway.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value="/my_gateway")
public class MyGatewayController {
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public String getData() {
		System.out.println("Returning data from MY GATEWAY Service data method");
		return "Hello from MY GATEWAY data method";
	}
	
	@RequestMapping(value = "/ms1data", method = RequestMethod.GET)
	public String getMsData() {
		System.out.println("Got inside GATEWAY - ms1data method");
		try {
			String msEndpoint = env.getProperty("endpoint.ms1-service");
			System.out.println("MS 1 Endpoint name : [" + msEndpoint + "]");
			
			return restTemplate.getForObject(new URI(msEndpoint), String.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "Exception occurred.. so, returning default data";
	}
}
