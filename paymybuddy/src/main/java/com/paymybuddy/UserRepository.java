package com.paymybuddy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymybuddy.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByMailAddress(String userName);
}
