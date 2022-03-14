package com.paymybuddy.connection.connectionDTO;

import java.util.Objects;

import com.paymybuddy.user.models.User;

public class CurrentConnectionDTO {

	private String firstNameConnection;
	private String lastNameConnection;
	private String mailAddress;
	private int id;

	public CurrentConnectionDTO(User user) {
		this.firstNameConnection = user.getFirstName();
		this.lastNameConnection = user.getLastName();
		this.mailAddress = user.getMailAddress();
		this.id = user.getId();
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public int getId() {
		return id;
	}

	public String getFirstNameConnection() {
		return firstNameConnection;
	}

	public void setFirstNameConnection(String firstNameConnection) {
		this.firstNameConnection = firstNameConnection;
	}

	public String getLastNameConnection() {
		return lastNameConnection;
	}

	public void setLastNameConnection(String lastNameConnection) {
		this.lastNameConnection = lastNameConnection;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstNameConnection, lastNameConnection);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurrentConnectionDTO other = (CurrentConnectionDTO) obj;
		return Objects.equals(firstNameConnection, other.firstNameConnection)
				&& Objects.equals(lastNameConnection, other.lastNameConnection);
	}

}
