package main.com.koreinfo.crm.streakapi.queryStreak.resources.streakObjects;
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
	
}
