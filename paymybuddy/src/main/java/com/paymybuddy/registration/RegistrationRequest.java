package com.paymybuddy.registration;

import java.util.Objects;

public class RegistrationRequest {

	private String mailAddress;
	private String password;

	public RegistrationRequest(String mailAddress, String password) {
		this.mailAddress = mailAddress;
		this.password = password;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "RegistrationRequest [mailAddress=" + mailAddress + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(mailAddress, password);
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
		return Objects.equals(mailAddress, other.mailAddress) && Objects.equals(password, other.password);
	}
}
