package com.paymybuddy.user.updateDTO;

import java.util.Objects;

public class UpdateProfileDTO {
	private String newFirstName;
	private String newLastName;

	public UpdateProfileDTO(String newFirstName, String newLastName) {
		this.newFirstName = newFirstName;
		this.newLastName = newLastName;
	}

	public UpdateProfileDTO() {
	}

	public String getNewFirstName() {
		return newFirstName;
	}

	public void setNewFirstName(String newFirstName) {
		this.newFirstName = newFirstName;
	}

	public String getNewLastName() {
		return newLastName;
	}

	public void setNewLastName(String newLastName) {
		this.newLastName = newLastName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(newFirstName, newLastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateProfileDTO other = (UpdateProfileDTO) obj;
		return Objects.equals(newFirstName, other.newFirstName) && Objects.equals(newLastName, other.newLastName);
	}

}
