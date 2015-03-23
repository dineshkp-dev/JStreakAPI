package main.com.koreinfo.crm.streakapi.queryStreak.resources.streakObjects;

public class Reminder {
	private String creatorKey;
	private String creationDate;
	private String remindDate;
	private String message;
	private ReminderStatus status;
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
	/**
	 * @return the creationDate
	 */
	public String getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the remindDate
	 */
	public String getRemindDate() {
		return remindDate;
	}
	/**
	 * @param remindDate the remindDate to set
	 */
	public void setRemindDate(String remindDate) {
		this.remindDate = remindDate;
	}
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
	 * @return the status
	 */
	public ReminderStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(ReminderStatus status) {
		this.status = status;
	}
	

}
