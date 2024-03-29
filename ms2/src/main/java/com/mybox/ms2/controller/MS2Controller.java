package com.mybox.ms2.controller;

import com.mybox.ms2.services.Greeting;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ms2")
public class MS2Controller {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		System.out.println("Greeting name - " + name);
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	@RequestMapping(method = RequestMethod.GET, value = "/data")
	public String getData() {
		System.out.println("Returning data from MS 2 data method");
		return "Hello from MS 2 TWO -  data method";
	}

	@RequestMapping(method = RequestMethod.POST, value="/sendmsg")
	@ResponseBody
	public String sendMsg(@RequestBody String msg) {
		System.out.println("Publish message to kafka - " + msg);
	
        return msg;
	}
}//end of class MS2Controller