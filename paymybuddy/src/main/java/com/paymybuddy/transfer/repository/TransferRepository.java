package com.paymybuddy.transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.transfer.model.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

}
