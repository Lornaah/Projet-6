package com.paymybuddy.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.paymybuddy.message.model.Message;
import com.paymybuddy.message.service.MessageService;

@Controller
public class MessageController {

	@Autowired
	private MessageService messageService;

	@PostMapping("/sendMessage")
	public String sendMessage(@ModelAttribute("message") Message message) {
		messageService.addMessage(message);
		return "redirect:/contact";
	}
}
