package com.streakapi.crm.datatype;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.streakapi.crm.impl.StreakAPIImpl;

/**
 * 
 * The User object contains the following information about a User.  
 * <li>email</li>
 * <li>lowercaseEmail</li>
 * <li>creationTimestamp (in dd-MM-yyyy, HH:00:00 format) </li>
 * <li>lastUpdatedTimestamp (in dd-MM-yyyy, HH:00:00 format) </li>
 * <li>lastSeenTimestamp (in dd-MM-yyyy, HH:00:00 format) </li>
 * <li>isOauthComplete</li>
 * <li>userKey</li>
 * <li>displayName</li>
 * <li>key</li>
 * <li>otherItemsMap (Contains a Map of other items from Streak API that are not listed above)</li>
 * 
 * @see <a href="https://www.streak.com/api/">https://www.streak.com/api/</a>
 * @author dineshkp
 */
public class User{

	private String email;
	private String lowercaseEmail;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy,HH:00:00")
	private String creationTimestamp;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy,HH:00:00")
	private Calendar lastUpdatedTimestamp;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy,HH:00:00")
	private Calendar lastSeenTimestamp;
	@JsonProperty("isOauthComplete")
	private boolean isOauthComplete; 
	private String userKey;
	private String displayName;
	private String key;
	Map<String, Object> otherItemsMap = new HashMap<String, Object>();

	/**
	 * @return the email
	 */
	@JsonGetter
	public String getEmail() {
		return email;
	}

	/**
	 * @return the lowercaseEmail
	 */
	@JsonGetter
	public String getLowercaseEmail() {
		return lowercaseEmail;
	}

	/**
	 * @return the creationTimestamp
	 */
	@JsonGetter
	public String getCreationTimestamp() {
		return creationTimestamp;
	}

	/**
	 * @return the lastUpdatedTimestamp
	 */
	@JsonGetter
	public Calendar getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}

	/**
	 * @return the lastSeenTimestamp
	 */
	@JsonGetter
	public Calendar getLastSeenTimestamp() {
		return lastSeenTimestamp;
	}

	/**
	 * @return the isOauthComplete
	 */
	@JsonGetter
	@JsonProperty("isOauthComplete")
	public boolean getOauthComplete() {
		return isOauthComplete;
	}

	/**
	 * @return the userKey
	 */
	@JsonGetter
	public String getUserKey() {
		return userKey;
	}

	/**
	 * @return the displayName
	 */
	@JsonGetter
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @return the key
	 */
	@JsonGetter
	public String getKey() {
		return key;
	}

	/**
	 * @param email the email to set
	 */
	@JsonSetter
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param lowercaseEmail the lowercaseEmail to set
	 */
	@JsonSetter
	public void setLowercaseEmail(String lowercaseEmail) {
		this.lowercaseEmail = lowercaseEmail;
	}

	/**
	 * @param creationTimestamp the creationTimestamp to set
	 */
	@JsonSetter
	public void setCreationTimestamp(String creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	/**
	 * @param lastUpdatedTimestamp the lastUpdatedTimestamp to set
	 */
	@JsonSetter
	public void setLastUpdatedTimestamp(Calendar lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}

	/**
	 * @param lastSeenTimestamp the lastSeenTimestamp to set
	 */
	@JsonSetter
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy,HH:00:00")
	public void setLastSeenTimestamp(Calendar lastSeenTimestamp) {
		this.lastSeenTimestamp = lastSeenTimestamp;
	}

	/**
	 * @param isOauthComplete the isOauthComplete to set
	 */
	@JsonSetter
	@JsonProperty("isOauthComplete")
	public void setOauthComplete(boolean isOauthComplete) {
		this.isOauthComplete = isOauthComplete;
	}

	/**
	 * @param userKey the userKey to set
	 */
	@JsonSetter
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	/**
	 * @param displayName the displayName to set
	 */
	@JsonSetter
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @param key the key to set
	 */
	@JsonSetter
	public void setKey(String key) {
		this.key = key;
	}

	@JsonAnySetter
	private void unknownItemHandler(String key, Object value) {
		otherItemsMap.put(key, value);
		System.out.println("Unmapped item found: " + key + ":" + value);
	}

	public Map<String, Object> getotherItemsMap() {
		return otherItemsMap;
	}



}
