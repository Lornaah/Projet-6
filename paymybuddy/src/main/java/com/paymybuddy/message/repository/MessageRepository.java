package com.paymybuddy.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.message.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
