/**
 * 
 */
package main.com.koreinfo.crm.streakapi.queryStreak.resources.streakObjects;

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

}
