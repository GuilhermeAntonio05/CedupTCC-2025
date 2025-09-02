package com.virtualgym.dev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(name = "/")
@RestController
public class MainController {

	@GetMapping
	public String hello() {
		return "Hello World ";
	}

	@PostMapping
	public String teste() {
		return "funcionou";
	}
}
