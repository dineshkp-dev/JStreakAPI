package com.streakapi.crm.datatype;
/**
 * @author dineshkp
 *
 */
public class AclEntry {
	private String fullName;
	private String email;
	private boolean isOwner;
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the isOwner
	 */
	public boolean isOwner() {
		return isOwner;
	}
	/**
	 * @param isOwner the isOwner to set
	 */
	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AclEntry [");
		if (fullName != null) {
			builder.append("fullName=");
			builder.append(fullName);
			builder.append(", ");
		}
		if (email != null) {
			builder.append("email=");
			builder.append(email);
			builder.append(", ");
		}
		builder.append("isOwner=");
		builder.append(isOwner);
		builder.append("]");
		return builder.toString();
	}
}
