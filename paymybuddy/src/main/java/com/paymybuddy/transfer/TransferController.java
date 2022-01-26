package com.paymybuddy.transfer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.transfer.service.TransferService;

@RestController
public class TransferController {

	@Autowired
	private TransferService transferService;

	@PostMapping("/createTransfer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String create(@RequestBody TransferRequest transfer) {
		return transferService.createTransfer(transfer);
	}

	@GetMapping("/myTransfer")
	@ResponseStatus(code = HttpStatus.OK)
	public Optional<TransferResponseDTO> getTransfer(@RequestBody Integer ID) {
		return transferService.getTransfer(ID);
	}
}