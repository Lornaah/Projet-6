package com.paymybuddy.connection.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.paymybuddy.user.models.User;

@Entity
@Table(name = "Connection")
public class Connection {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne
	private User user1;
	@OneToOne
	private User user2;

	public Connection(User user1, User user2) {
		this.user1 = user1;
		this.user2 = user2;
	}

	public Connection() {
	}

	public int getId() {
		return id;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, user1, user2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connection other = (Connection) obj;
		return id == other.id && Objects.equals(user1, other.user1) && Objects.equals(user2, other.user2);
	}

}
