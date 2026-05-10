package com.example.microservice.controller;

import com.example.microservice.dto.HelloResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello")
	public HelloResponse hello() {
		return new HelloResponse("Hello microservice");
	}

	@GetMapping("/hello/{name}")
	public HelloResponse helloName(@PathVariable String name) {
		return new HelloResponse("Hello " + name);
	}
}
