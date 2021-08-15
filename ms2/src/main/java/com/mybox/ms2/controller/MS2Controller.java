package com.mybox.ms2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/my_ms2")
public class MS2Controller {
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	@RequestMapping(value = "/ms2data", method = RequestMethod.GET)
	public String getData() {
		System.out.println("Returning data from MS 2 data method");
		return "Yello from MS 2 data method";
	}
}