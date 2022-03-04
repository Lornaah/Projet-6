package com.paymybuddy.transfer.transferDTO;

import java.util.Date;
import java.util.Objects;

import com.paymybuddy.transfer.model.Transfer;

public class CurrentUserTransferDTO {

	private String firstNameSender;
	private String lastNameSender;
	private String firstNameReceiver;
	private String lastNameReceiver;
	private Date date;
	private float amount;

	public CurrentUserTransferDTO(Transfer transfer) {
		this.firstNameSender = transfer.getUserSend().getFirstName();
		this.lastNameSender = transfer.getUserSend().getLastName();
		this.firstNameReceiver = transfer.getUserReceive().getFirstName();
		this.lastNameReceiver = transfer.getUserReceive().getLastName();
		this.date = transfer.getDate();
		this.amount = transfer.getAmount();
	}

	public String getFirstNameSender() {
		return firstNameSender;
	}

	public void setFirstNameSender(String firstNameSender) {
		this.firstNameSender = firstNameSender;
	}

	public String getLastNameSender() {
		return lastNameSender;
	}

	public void setLastNameSender(String lastNameSender) {
		this.lastNameSender = lastNameSender;
	}

	public String getFirstNameReceiver() {
		return firstNameReceiver;
	}

	public void setFirstNameReceiver(String firstNameReceiver) {
		this.firstNameReceiver = firstNameReceiver;
	}

	public String getLastNameReceiver() {
		return lastNameReceiver;
	}

	public void setLastNameReceiver(String lastNameReceiver) {
		this.lastNameReceiver = lastNameReceiver;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, date, firstNameReceiver, firstNameSender, lastNameReceiver, lastNameSender);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurrentUserTransferDTO other = (CurrentUserTransferDTO) obj;
		return Float.floatToIntBits(amount) == Float.floatToIntBits(other.amount) && Objects.equals(date, other.date)
				&& Objects.equals(firstNameReceiver, other.firstNameReceiver)
				&& Objects.equals(firstNameSender, other.firstNameSender)
				&& Objects.equals(lastNameReceiver, other.lastNameReceiver)
				&& Objects.equals(lastNameSender, other.lastNameSender);
	}

}
