package com.paymybuddy.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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
}
