package com.mybox.ms1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ms1")
public class MS1Controller {
	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public String getData() {
		System.out.println("Returning data from MS 1 data method");
		return "Hello from MS 1 data method";
	}
}//end of class MS1Controller