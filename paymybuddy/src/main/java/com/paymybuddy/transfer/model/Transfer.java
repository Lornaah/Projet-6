package com.paymybuddy.transfer.model;

import java.util.Date;

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
	private User userReceive;
	private float amount;
	private float taxedAmount;
	private Date date;

	public Transfer() {
	}

	public Transfer(User userSend, User userReceive, float amount, Date date) {
		this.userSend = userSend;
		this.userReceive = userReceive;
		this.amount = amount;
		this.date = date;
		computeTaxAmount();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public User getUserReceive() {
		return userReceive;
	}

	public void setUserReceive(User userReceive) {
		this.userReceive = userReceive;
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
		float founds = userReceive.getWallet().getFounds();

		userReceive.getWallet().setFounds(founds + amount);
	}
}
