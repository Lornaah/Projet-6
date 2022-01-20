package com.paymybuddy.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paymybuddy.models.User;
import com.paymybuddy.security.model.UserDetailsImpl;
import com.paymybuddy.security.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByMailAddress(userName);

		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

		return user.map(UserDetailsImpl::new).get();
	}

	@Override
	public String createUser(User user) {
		userRepository.save(user);
		return "User created";
	}

	public boolean alreadyRegistered(User user) {
		if (userRepository.findByMailAddress(user.getMailAddress()).isPresent()) {
			return true;
		}
		return false;
	}
}
