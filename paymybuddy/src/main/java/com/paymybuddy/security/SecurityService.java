package com.paymybuddy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.paymybuddy.user.models.User;
import com.paymybuddy.user.repository.UserRepository;

@Service
public class SecurityService {

	@Autowired
	UserRepository userRepository;

	public static final String USER_NOT_FOUND = "not Found";

	public static String getCurrentUserMailAddress() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			return authentication.getName();
		}
		return USER_NOT_FOUND;
	}

	public User getCurrentUserByUserMailAddress() {
		String currentUserName = SecurityService.getCurrentUserMailAddress();
		User currentUser = userRepository.findByMailAddress(currentUserName).get();
		return currentUser;
	}

	public String getEncryptedPassword(String password) {
		PasswordEncoder cryptedPassword = new BCryptPasswordEncoder();
		return cryptedPassword.encode(password);
	}

	public boolean matches(String rawPassword, String encodedPassword) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(rawPassword, encodedPassword);
	}
}
