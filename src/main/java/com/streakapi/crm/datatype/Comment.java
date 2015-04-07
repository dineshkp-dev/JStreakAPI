package com.streakapi.crm.datatype;
/**
 * @author dineshkp
 *
 */
public class Comment {
	private String message;
	private String timestamp;
	private String creatorKey;
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the creatorKey
	 */
	public String getCreatorKey() {
		return creatorKey;
	}
	/**
	 * @param creatorKey the creatorKey to set
	 */
	public void setCreatorKey(String creatorKey) {
		this.creatorKey = creatorKey;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Comment [");
		if (message != null) {
			builder.append("message=");
			builder.append(message);
			builder.append(", ");
		}
		if (timestamp != null) {
			builder.append("timestamp=");
			builder.append(timestamp);
			builder.append(", ");
		}
		if (creatorKey != null) {
			builder.append("creatorKey=");
			builder.append(creatorKey);
		}
		builder.append("]");
		return builder.toString();
	}
	
}
