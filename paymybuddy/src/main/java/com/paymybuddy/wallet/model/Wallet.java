package com.paymybuddy.wallet.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.paymybuddy.user.models.User;

@Entity
@Table(name = "Wallet")
public class Wallet {

	@Id
	private int id;
	@OneToOne
	@MapsId
	private User user;

	private float founds;

	public Wallet() {
	}

	public Wallet(int id, float founds) {
		this.id = id;
		this.founds = founds;
	}

	public float getFounds() {
		return founds;
	}

	public void setFounds(float founds) {
		this.founds = founds;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
