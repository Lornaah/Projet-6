package com.paymybuddy.transfer.service;

import java.util.Optional;

import com.paymybuddy.transfer.TransferRequest;
import com.paymybuddy.transfer.TransferResponseDTO;

public interface TransferService {

	public String createTransfer(TransferRequest transfer);

	public Optional<TransferResponseDTO> getTransfer(int ID);
}
