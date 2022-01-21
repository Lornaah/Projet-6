package com.paymybuddy.user;

import java.util.Objects;

public class UpdateRequest {

	private String mailAddress;
	private String password;
	private boolean active;

	public UpdateRequest(String mailAddress, String password, boolean active) {
		this.mailAddress = mailAddress;
		this.password = password;
		this.active = active;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public boolean isActive() {
		return active;
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, mailAddress, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateRequest other = (UpdateRequest) obj;
		return active == other.active && Objects.equals(mailAddress, other.mailAddress)
				&& Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "Account : [mailAddress=" + mailAddress + ", password=" + password + ", active=" + active + "]";
	}

}
