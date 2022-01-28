package com.paymybuddy.transfer.transferDTO;

import com.paymybuddy.transfer.model.Transfer;

public class TransferResponseDTO {

	private int ID;
	private TransferUserDTO userSend;
	private TransferUserDTO userReceive;
	private float amount;
	private float taxedAmount;

	public TransferResponseDTO(Transfer transfer) {
		this.userSend = new TransferUserDTO(transfer.getUserSend());
		this.userReceive = new TransferUserDTO(transfer.getUserReveive());
		this.amount = transfer.getAmount();
		this.taxedAmount = transfer.getTaxedAmount();
		this.ID = transfer.getId();
	}

	public int getID() {
		return ID;
	}

	public TransferUserDTO getUserSend() {
		return userSend;
	}

	public TransferUserDTO getUserReceive() {
		return userReceive;
	}

	public float getAmount() {
		return amount;
	}

	public float getTaxedAmount() {
		return taxedAmount;
	}

}
