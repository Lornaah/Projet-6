package com.paymybuddy.user;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.paymybuddy.registration.RegistrationRequest;
import com.paymybuddy.security.SecurityService;
import com.paymybuddy.user.service.UserService;
import com.paymybuddy.user.updateDTO.UpdatePasswordDTO;
import com.paymybuddy.user.updateDTO.UpdateProfileDTO;
import com.paymybuddy.user.updateDTO.UpdateRequest;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@DeleteMapping("/deleteAccount")
	@ResponseStatus(code = HttpStatus.OK)
	public String delete(@RequestBody RegistrationRequest request) {
		return userService.deleteUser(request);
	}

	@GetMapping("/myAccount")
	@ResponseStatus(code = HttpStatus.OK)
	public String getAccount(@RequestBody UpdateRequest request) {

		if (userService.getUser(request).isPresent()) {
			return (userService.getUser(request).get().toString());
		}
		return "User doesn't exist or password is incorrect";
	}

	@PostMapping("/changeName")
	public void changeName(@ModelAttribute("nameInfos") @Valid UpdateProfileDTO updateProfileDTO,
			HttpServletRequest request, HttpServletResponse response, Model model, Errors errors) throws IOException {
		String currentUserName = SecurityService.getCurrentUserName();
		model.addAttribute("newProfile", userService.updateProfileNamesByUserName(currentUserName, updateProfileDTO));
		response.sendRedirect("/profile");
	}

	@PostMapping("/changePassword")
	public void changePassword(@ModelAttribute("updatePasswordDTO") @Valid UpdatePasswordDTO updatePasswordDTO,
			HttpServletRequest request, HttpServletResponse response, Model model, Errors errors) throws IOException {
		String currentUserName = SecurityService.getCurrentUserName();
		model.addAttribute("newPassword", userService.updatePasswordByUserName(currentUserName, updatePasswordDTO));
		response.sendRedirect("/profile");
	}

	@GetMapping("/disableAccount")
	public String disableAccount() {
		String currentUserName = SecurityService.getCurrentUserName();
		userService.disableAccountByUserName(currentUserName);
		return "disconnected";
	}
}
