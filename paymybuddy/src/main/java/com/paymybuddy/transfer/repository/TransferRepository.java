package com.paymybuddy.transfer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.transfer.model.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

	List<Transfer> findByUserReceive_idOrUserSend_id(int receiveID, int sendID);
}
