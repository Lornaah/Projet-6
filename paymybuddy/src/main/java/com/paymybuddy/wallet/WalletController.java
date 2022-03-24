package com.paymybuddy.wallet;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.security.service.SecurityService;
import com.paymybuddy.user.models.User;
import com.paymybuddy.user.service.UserService;
import com.paymybuddy.wallet.service.WalletService;

@RestController
public class WalletController {

	@Autowired
	private WalletService walletService;
	@Autowired
	private UserService userService;

	@GetMapping("/myWallet")
	@ResponseStatus(code = HttpStatus.OK)
	public float getFounds(@RequestBody Integer userID) {
		return walletService.getFounds(userID);
	}

	@PostMapping("/addFounds")
	@ResponseStatus(code = HttpStatus.OK)
	public void addFounds(@ModelAttribute("money") @Valid ManageFoundsDTO manageFoundsDTO, HttpServletRequest request,
			Errors errors, HttpServletResponse response) throws IOException {

		String currentUserName = SecurityService.getCurrentUserMailAddress();
		Optional<User> user = userService.getUserByUserName(currentUserName);
		manageFoundsDTO.setUserID(user.get().getId());
		walletService.addFounds(manageFoundsDTO);

		response.sendRedirect("/transfer");
	}

}
