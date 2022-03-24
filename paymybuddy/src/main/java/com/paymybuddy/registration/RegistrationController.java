package com.paymybuddy.registration;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.paymybuddy.security.service.SecurityService;
import com.paymybuddy.validation.ValidationService;

@RestController
@RequestMapping(path = "/newUser")
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private ValidationService validationService;

	@Autowired
	private SecurityService securityService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid RegistrationRequest registrationRequest,
			HttpServletRequest request, Errors errors) {

		if (validationService.isEmailValid(registrationRequest.getMailAddress())
				&& validationService.isNameValid(registrationRequest.getFirstName())
				&& validationService.isNameValid(registrationRequest.getLastName())
				&& validationService.isPasswordValide(registrationRequest.getPassword())) {

			registrationRequest.setPassword(securityService.getEncryptedPassword(registrationRequest.getPassword()));

			registrationService.registerUser(registrationRequest);
			return new ModelAndView("log", "user", registrationRequest);
		}
		return new ModelAndView("signup");

	}
}
