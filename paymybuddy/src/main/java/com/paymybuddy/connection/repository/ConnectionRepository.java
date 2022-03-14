package com.paymybuddy.connection.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.connection.model.Connection;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Integer> {

	List<Connection> findByUser1_idOrUser2_id(int user1ID, int user2ID);
}
