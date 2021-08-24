package com.mybox.ms2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ms2")
public class MS2Controller {
	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public String getData() {
		System.out.println("Returning data from MS 2 data method");
		return "Hello from MS 2 TWO -  data method";
	}
}//end of class MS1Controller