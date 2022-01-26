package com.paymybuddy.transfer;

import com.paymybuddy.user.models.User;

public class TransferUserDTO {

	private int id;
	private String mailAddress;

	public TransferUserDTO(User user) {
		this.id = user.getId();
		this.mailAddress = user.getMailAddress();
	}

	public int getId() {
		return id;
	}

	public String getMailAddress() {
		return mailAddress;
	}

}
