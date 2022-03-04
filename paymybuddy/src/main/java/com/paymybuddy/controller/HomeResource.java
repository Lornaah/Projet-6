package com.paymybuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.paymybuddy.security.SecurityService;
import com.paymybuddy.transfer.service.TransferService;
import com.paymybuddy.user.service.UserService;

@Controller
public class HomeResource {

	@Autowired
	TransferService transferService;
	@Autowired
	UserService userService;

	@GetMapping("/transfer")
	public String transfer(Model model) {
		String currentUserName = SecurityService.getCurrentUserName();
		model.addAttribute("transferList", transferService.getAllTransfers(currentUserName));
		model.addAttribute("wallet", userService.getUserByUserName(currentUserName).get().getWallet().getFounds());
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

	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}

	@GetMapping("/disconnected")
	public String disconnected() {
		return "disconnected";
	}

	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

	@GetMapping("/addFounds")
	public String addFounds() {
		return "transfer";
	}

}
