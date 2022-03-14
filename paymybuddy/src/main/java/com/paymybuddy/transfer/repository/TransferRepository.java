package com.paymybuddy.transfer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.transfer.model.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

	Page<Transfer> findByUserReceive_idOrUserSend_id(int receiveID, int sendID, Pageable pageable);
}
