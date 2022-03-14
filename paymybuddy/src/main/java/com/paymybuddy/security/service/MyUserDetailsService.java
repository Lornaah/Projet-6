package com.paymybuddy.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paymybuddy.security.model.MyUserPrincipal;
import com.paymybuddy.user.models.User;
import com.paymybuddy.user.repository.UserRepository;

@Service("MyUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String mail) {
		Optional<User> user = userRepository.findByMailAddress(mail);
		if (user.isEmpty()) {
			throw new UsernameNotFoundException(mail);
		}
		if (!user.get().isActive()) {
			throw new UsernameNotFoundException("This Account is not active. Please contact an admnistrator");
		}
		return new MyUserPrincipal(user.get());
	}
}
