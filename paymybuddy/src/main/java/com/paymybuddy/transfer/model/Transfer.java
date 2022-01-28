package com.paymybuddy.transfer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.paymybuddy.user.models.User;

@Entity
@Table(name = "Transfer")
public class Transfer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne
	private User userSend;
	@OneToOne
	private User userReveive;
	private float amount;
	private float taxedAmount;

	public Transfer() {
	}

	public Transfer(User userSend, User userReveive, float amount) {
		this.userSend = userSend;
		this.userReveive = userReveive;
		this.amount = amount;
		computeTaxAmount();
	}

	public int getId() {
		return id;
	}

	public User getUserSend() {
		return userSend;
	}

	public void setUserSend(User userSend) {
		this.userSend = userSend;
	}

	public User getUserReveive() {
		return userReveive;
	}

	public void setUserReveive(User userReveive) {
		this.userReveive = userReveive;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getTaxedAmount() {
		return taxedAmount;
	}

	public void setTaxedAmount(float taxedAmount) {
		this.taxedAmount = taxedAmount;
	}

	public void execute() {
		deposit();
		receive();
	}

	private void computeTaxAmount() {
		taxedAmount = amount * 1.05f;
	}

	private void deposit() {
		float founds = userSend.getWallet().getFounds();

		if (amount > founds) {
			throw new IllegalStateException("The user doesn't have enough founds");
		}
		userSend.getWallet().setFounds(founds - amount);
	}

	private void receive() {
		float founds = userReveive.getWallet().getFounds();

		userReveive.getWallet().setFounds(founds + amount);
	}
}
