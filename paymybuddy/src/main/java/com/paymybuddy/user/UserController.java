package com.paymybuddy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.registration.RegistrationRequest;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@DeleteMapping("/deleteAccount")
	@ResponseStatus(code = HttpStatus.OK)
	public String delete(@RequestBody RegistrationRequest request) {
		return userService.deleteUser(request);
	}

	@PutMapping("/updateAccount")
	@ResponseStatus(code = HttpStatus.OK)
	public String update(@RequestBody UpdateRequest request) {
		return userService.updateUser(request);
	}

	@GetMapping("/myAccount")
	@ResponseStatus(code = HttpStatus.OK)
	public String getAccount(@RequestBody UpdateRequest request) {

		if (userService.getUser(request).isPresent()) {
			return (userService.getUser(request).get().toString());
		}
		return "User doesn't exist or password is incorrect";
	}
}
