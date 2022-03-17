package com.paymybuddy.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.paymybuddy.connection.connectionDTO.ConnectionDTO;
import com.paymybuddy.connection.service.ConnectionService;
import com.paymybuddy.security.SecurityService;
import com.paymybuddy.transfer.service.TransferService;
import com.paymybuddy.transfer.transferDTO.CurrentUserTransferDTO;
import com.paymybuddy.transfer.transferDTO.TransferRequest;
import com.paymybuddy.user.models.User;
import com.paymybuddy.user.service.UserService;
import com.paymybuddy.user.updateDTO.UpdatePasswordDTO;
import com.paymybuddy.user.updateDTO.UpdateProfileDTO;

@Controller
public class ViewController {

	@Autowired
	TransferService transferService;
	@Autowired
	UserService userService;
	@Autowired
	ConnectionService connectionService;

	@GetMapping("/transfer")
	public String transfer(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

		return findPaginated(1, model, request, response);
	}

	private void fillTransferModel(Model model) {
		String currentUserName = SecurityService.getCurrentUserName();
		model.addAttribute("wallet", userService.getUserByUserName(currentUserName).get().getWallet().getFounds());
		model.addAttribute("connectionMail", new ConnectionDTO());
		model.addAttribute("newTransfer", new TransferRequest());
		model.addAttribute("connectionList", connectionService.getAllConnection());
	}

	@GetMapping("/")
	public String home() {
		return "redirect:/transfer";
	}

	@GetMapping("/log")
	public String user() {
		return "log";
	}

	@GetMapping("/profile")
	public String profile(Model model) {
		String currentUserName = SecurityService.getCurrentUserName();
		User user = userService.getUserByUserName(currentUserName).get();
		UpdateProfileDTO updateProfileDTO = new UpdateProfileDTO(user.getFirstName(), user.getLastName());
		model.addAttribute("nameInfos", updateProfileDTO);
		UpdatePasswordDTO updatePasswordDTO = new UpdatePasswordDTO("password", "password");
		model.addAttribute("updatePasswordDTO", updatePasswordDTO);
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

	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}

	@GetMapping("/addFounds")
	public String addFounds() {
		return "transfer";
	}

	@GetMapping("/transfer/{pageNum}")
	public String findPaginated(@PathVariable(value = "pageNum") int pageNum, Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int pageSize = 6;

		Page<CurrentUserTransferDTO> page = transferService.getTransfersPaginated(pageNum, pageSize);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());

		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listTransfer", page.getContent());

		fillTransferModel(model);

		return "transfer";
	}

}
