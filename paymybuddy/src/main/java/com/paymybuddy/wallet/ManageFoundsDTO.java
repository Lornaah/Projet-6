package com.paymybuddy.wallet;

public class ManageFoundsDTO {

	private Integer userID;
	private float amount;

	public ManageFoundsDTO(Integer userID, float amount) {
		this.userID = userID;
		this.amount = amount;
	}

	public ManageFoundsDTO() {

	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}
