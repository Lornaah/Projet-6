package com.paymybuddy.transfer.service;

import java.util.List;
import java.util.Optional;

import com.paymybuddy.transfer.transferDTO.CurrentUserTransferDTO;
import com.paymybuddy.transfer.transferDTO.TransferRequest;
import com.paymybuddy.transfer.transferDTO.TransferResponseDTO;

public interface TransferService {

	public TransferResponseDTO createTransfer(TransferRequest transfer);

	public Optional<TransferResponseDTO> getTransfer(int ID);

	public List<CurrentUserTransferDTO> getAllTransfers(String currentUserName);
}
