package com.streakapi.crm.datatype;
import java.util.Map;

/**
 * @author dineshkp
 *
 */
public class Thread {
	private String subject;
	private Map<String, String> nameEmailAddress;
	private String lastEmailTimestamp;
	private String threadGmailId;
	private String fileAttachments;
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @return the nameEmailAddress
	 */
	public Map<String, String> getNameEmailAddress() {
		return nameEmailAddress;
	}
	/**
	 * @return the lastEmailTimestamp
	 */
	public String getLastEmailTimestamp() {
		return lastEmailTimestamp;
	}
	/**
	 * @return the threadGmailId
	 */
	public String getThreadGmailId() {
		return threadGmailId;
	}
	/**
	 * @return the fileAttachments
	 */
	public String getFileAttachments() {
		return fileAttachments;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Thread [");
		if (subject != null) {
			builder.append("subject=");
			builder.append(subject);
			builder.append(", ");
		}
		if (nameEmailAddress != null) {
			builder.append("nameEmailAddress=");
			builder.append(nameEmailAddress);
			builder.append(", ");
		}
		if (lastEmailTimestamp != null) {
			builder.append("lastEmailTimestamp=");
			builder.append(lastEmailTimestamp);
			builder.append(", ");
		}
		if (threadGmailId != null) {
			builder.append("threadGmailId=");
			builder.append(threadGmailId);
			builder.append(", ");
		}
		if (fileAttachments != null) {
			builder.append("fileAttachments=");
			builder.append(fileAttachments);
		}
		builder.append("]");
		return builder.toString();
	}

}
