package com.paymybuddy.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByMailAddress(String mailAddress);
}
