package com.paymybuddy.user.updateDTO;


import java.util.Objects;

public class UpdatePasswordDTO {

	private String oldPassword;
	private String newPassword;

	public UpdatePasswordDTO(String oldPassword, String newPassword) {
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public int hashCode() {
		return Objects.hash(newPassword, oldPassword);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdatePasswordDTO other = (UpdatePasswordDTO) obj;
		return Objects.equals(newPassword, other.newPassword) && Objects.equals(oldPassword, other.oldPassword);
	}

}
