package com.paymybuddy.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/newUser")
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public String create(@RequestBody RegistrationRequest request) {
		return registrationService.registerUser(request);
	}

}
