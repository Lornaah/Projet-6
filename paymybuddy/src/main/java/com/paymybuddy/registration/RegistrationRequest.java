package com.paymybuddy.registration;

import java.util.Objects;

public class RegistrationRequest {

	private String mailAddress;
	private String password;
	private String firstName;
	private String lastName;

	public RegistrationRequest(String mailAddress, String password, String firstName, String lastName) {
		this.mailAddress = mailAddress;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;

	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "RegistrationRequest [mailAddress=" + mailAddress + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, mailAddress, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistrationRequest other = (RegistrationRequest) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(mailAddress, other.mailAddress) && Objects.equals(password, other.password);
	}
}
