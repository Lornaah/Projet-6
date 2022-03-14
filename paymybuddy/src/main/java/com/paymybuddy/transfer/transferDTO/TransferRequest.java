package com.paymybuddy.transfer.transferDTO;

import java.util.Date;
import java.util.Objects;

public class TransferRequest {

	private int userReceiveID;
	private float amount;
	private Date date;

	public TransferRequest(int userReceiveID, float amount) {
		this(userReceiveID, amount, new Date());
	}

	public TransferRequest(int userReceiveID, float amount, Date date) {
		this.userReceiveID = userReceiveID;
		this.amount = amount;
		this.date = date;
	}

	public TransferRequest() {

	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getUserReceiveID() {
		return userReceiveID;
	}

	public void setUserReceiveID(int userReceiveID) {
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
		return Objects.hash(amount, date, userReceiveID);
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
		return Float.floatToIntBits(amount) == Float.floatToIntBits(other.amount) && Objects.equals(date, other.date)
				&& userReceiveID == other.userReceiveID;
	}

	@Override
	public String toString() {
		return "TransferRequest [userReceiveID=" + userReceiveID + ", amount=" + amount + ", date=" + date + "]";
	}

}
