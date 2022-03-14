package com.paymybuddy.connection.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.paymybuddy.connection.connectionDTO.ConnectionDTO;
import com.paymybuddy.connection.service.ConnectionService;

@Controller
public class ConnectionController {

	@Autowired
	private ConnectionService connectionService;

	@PostMapping("/newConnection")
	public void newConnection(@ModelAttribute("connectionMail") @Valid ConnectionDTO connectionMail,
			HttpServletRequest request, HttpServletResponse response, Model model, Errors errors) throws IOException {
		connectionService.newConnection(connectionMail);
		response.sendRedirect("/transfer");
	}

}
