package com.paymybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeResource {

	@GetMapping("/transfer")
	public String transfer() {
		return "transfer";
	}

	@GetMapping("/")
	public String home() {
		return "transfer";
	}

	@GetMapping("/log")
	public String user() {
		return "log";
	}

	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}

}
