package com.paymybuddy.transfer;

import java.util.Objects;

public class TransferRequest {

	private int userSendID;
	private int userReceiveID;
	private float amount;

	public TransferRequest(int userSendID, int userReceiveID, float amount) {
		this.userSendID = userSendID;
		this.userReceiveID = userReceiveID;
		this.amount = amount;
	}

	public int getUserSendID() {
		return userSendID;
	}

	public void setUserSendID(int userSendID) {
		this.userSendID = userSendID;
	}

	public int getUserReveiveID() {
		return userReceiveID;
	}

	public void setUserReveiveID(int userReceiveID) {
		this.userReceiveID = userReceiveID;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, userReceiveID, userSendID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransferRequest other = (TransferRequest) obj;
		return Float.floatToIntBits(amount) == Float.floatToIntBits(other.amount)
				&& Objects.equals(userReceiveID, other.userReceiveID) && Objects.equals(userSendID, other.userSendID);
	}

	@Override
	public String toString() {
		return "TransferRequest [userSendID=" + userSendID + ", userReceiveID=" + userReceiveID + ", amount=" + amount
				+ "]";
	}

}
