package com.paymybuddy.transfer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Wallet")
public class Wallet {
	private float founds;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	public Wallet() {
	}

	public float getFounds() {
		return founds;
	}

	public void setFounds(float founds) {
		this.founds = founds;
	}
}
