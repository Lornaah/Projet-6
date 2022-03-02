package com.paymybuddy.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.user.service.UserService;

@Service
public class ValidationService {

	@Autowired
	UserService userService;
	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

	private static final String PASSWORD_PATTERN = "^((?=\\S*?[A-Z])(?=\\S*?[a-z])(?=\\S*?[0-9]).{8,})\\S$";

	private static final String NAME_PATTERN = "^[A-Z](([A-Za-z])*|(-|'|[Ä-ÿ]|\\s))*$";

	// Email
	public boolean isEmailValid(String email) {

		if (email == null || userService.alreadyRegistered(email) || !emailValidator(email)) {
			return false;
		}
		return true;
	}

	private boolean emailValidator(String email) {
		return validator(email, EMAIL_PATTERN);
	}

	// Password
	public boolean isPasswordValide(String password) {
		if (password == null || !passwordValidator(password))
			return false;
		return true;
	}

	private boolean passwordValidator(String password) {
		return validator(password, PASSWORD_PATTERN);
	}

	// Lastname FirstName
	public boolean isNameValid(String name) {
		if (name == null || !nameValidator(name))
			return false;
		return true;
	}

	private boolean nameValidator(String name) {
		return validator(name, NAME_PATTERN);
	}

	private boolean validator(String param, String patternName) {
		pattern = Pattern.compile(patternName);
		matcher = pattern.matcher(param);
		return matcher.matches();
	}
}
