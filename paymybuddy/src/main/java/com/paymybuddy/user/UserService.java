package com.paymybuddy.user;

import com.paymybuddy.models.User;

public interface UserService {

	public String createUser(User user);

	public boolean alreadyRegistered(User user);
}