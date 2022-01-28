package com.paymybuddy.transfer.service;

import java.util.Optional;

import com.paymybuddy.transfer.model.Transfer;
import com.paymybuddy.transfer.transferDTO.TransferRequest;
import com.paymybuddy.transfer.transferDTO.TransferResponseDTO;

public interface TransferService {

	public Transfer createTransfer(TransferRequest transfer);

	public Optional<TransferResponseDTO> getTransfer(int ID);
}
