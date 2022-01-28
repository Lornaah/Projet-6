package com.paymybuddy.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.wallet.service.WalletService;

@RestController
public class WalletController {

	@Autowired
	private WalletService walletService;

	@GetMapping("/myWallet")
	@ResponseStatus(code = HttpStatus.OK)
	public float getFounds(@RequestBody Integer userID) {
		return walletService.getFounds(userID);
	}

	@PutMapping("/addFounds")
	@ResponseStatus(code = HttpStatus.OK)
	public float addFounds(@RequestBody ManageFoundsDTO manageFoundsDTO) {
		return walletService.addFounds(manageFoundsDTO);
	}

	@PutMapping("/removeFounds")
	@ResponseStatus(code = HttpStatus.OK)
	public float removeFounds(@RequestBody ManageFoundsDTO manageFoundsDTO) {
		return walletService.removeFounds(manageFoundsDTO);
	}
}
