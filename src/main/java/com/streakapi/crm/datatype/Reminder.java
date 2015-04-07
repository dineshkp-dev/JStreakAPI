package com.streakapi.crm.datatype;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * The Box object contains the following information about a Box in Streak.  
 * <li>creatorKey</li>
 * <li>creationDate (in dd-MM-yyyy, HH:00:00 format) </li>
 * <li>remindDate (in dd-MM-yyyy, HH:00:00 format) </li>
 * <li>message</li>
 * <li>remindFollowers</li>
 * <li>boxKey</li>
 * <li>pipelineKey</li>
 * <li>status</li>
 * <li>reminderKey</li>
 * <li>creatorName</li>
 * <li>key</li>
 * <li>otherItemsMap (Contains a Map of other items from Streak API that are not listed above) </li>
 * @see <a href="https://www.streak.com/api/">https://www.streak.com/api/</a>
 * @author dineshkp
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
	"creatorKey",
	"creationDate",
	"remindDate",
	"message",
	"remindFollowers",
	"boxKey",
	"pipelineKey",
	"status",
	"reminderKey",
	"creatorName",
	"key"
})
public class Reminder {

	private String creatorKey;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy,HH:00:00")
	private Long creationDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy,HH:00:00")
	private Long remindDate;
	private String message;
	private Boolean remindFollowers;
	private String boxKey;
	private String pipelineKey;
	private String status;
	private String reminderKey;
	private String creatorName;
	private String key;
	@JsonIgnore
	private Map<String, Object> otherItemsMap = new HashMap<String, Object>();

	/**
	 * 
	 * @return
	 * The creatorKey
	 */
	@JsonIgnore
	public String getCreatorKey() {
		return creatorKey;
	}

	/**
	 * 
	 * @param creatorKey
	 * The creatorKey
	 */
	@JsonProperty("creatorKey")
	public void setCreatorKey(String creatorKey) {
		this.creatorKey = creatorKey;
	}

	/**
	 * 
	 * @return
	 * The creationDate
	 */
	@JsonIgnore
	public Long getCreationDate() {
		return creationDate;
	}

	/**
	 * 
	 * @param creationDate
	 * The creationDate
	 */
	@JsonProperty("creationDate")
	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * 
	 * @return
	 * The remindDate
	 */
	@JsonIgnore
	public Long getRemindDate() {
		return remindDate;
	}

	/**
	 * 
	 * @param remindDate
	 * The remindDate
	 */
	@JsonProperty("remindDate")
	public void setRemindDate(Long remindDate) {
		this.remindDate = remindDate;
	}

	/**
	 * 
	 * @return
	 * The message
	 */
	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	/**
	 * 
	 * @param message
	 * The message
	 */
	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 
	 * @return
	 * The remindFollowers
	 */
	@JsonProperty("remindFollowers")
	public Boolean getRemindFollowers() {
		return remindFollowers;
	}

	/**
	 * 
	 * @param remindFollowers
	 * The remindFollowers
	 */
	@JsonProperty("remindFollowers")
	public void setRemindFollowers(Boolean remindFollowers) {
		this.remindFollowers = remindFollowers;
	}

	/**
	 * 
	 * @return
	 * The boxKey
	 */
	@JsonIgnore
	public String getBoxKey() {
		return boxKey;
	}

	/**
	 * 
	 * @param boxKey
	 * The boxKey
	 */
	@JsonProperty("boxKey")
	public void setBoxKey(String boxKey) {
		this.boxKey = boxKey;
	}

	/**
	 * 
	 * @return
	 * The pipelineKey
	 */
	@JsonIgnore
	public String getPipelineKey() {
		return pipelineKey;
	}

	/**
	 * 
	 * @param pipelineKey
	 * The pipelineKey
	 */
	@JsonProperty("pipelineKey")
	public void setPipelineKey(String pipelineKey) {
		this.pipelineKey = pipelineKey;
	}

	/**
	 * 
	 * @return
	 * The status
	 */
	@JsonIgnore
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 * The status
	 */
	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return
	 * The reminderKey
	 */
	@JsonIgnore
	public String getReminderKey() {
		return reminderKey;
	}

	/**
	 * 
	 * @param reminderKey
	 * The reminderKey
	 */
	@JsonProperty("reminderKey")
	public void setReminderKey(String reminderKey) {
		this.reminderKey = reminderKey;
	}

	/**
	 * 
	 * @return
	 * The creatorName
	 */
	@JsonIgnore
	public String getCreatorName() {
		return creatorName;
	}

	/**
	 * 
	 * @param creatorName
	 * The creatorName
	 */
	@JsonProperty("creatorName")
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	/**
	 * 
	 * @return
	 * The key
	 */
	@JsonIgnore
	public String getKey() {
		return key;
	}

	/**
	 * 
	 * @param key
	 * The key
	 */
	@JsonProperty("key")
	public void setKey(String key) {
		this.key = key;
	}

	@JsonAnyGetter
	public Map<String, Object> getOtherItemsMap() {
		return this.otherItemsMap;
	}

	@JsonAnySetter
	public void setOtherItemsMap(String name, Object value) {
		this.otherItemsMap.put(name, value);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Reminder [");
		if (creatorKey != null) {
			builder.append("creatorKey=");
			builder.append(creatorKey);
			builder.append(", ");
		}
		if (creationDate != null) {
			builder.append("creationDate=");
			builder.append(creationDate);
			builder.append(", ");
		}
		if (remindDate != null) {
			builder.append("remindDate=");
			builder.append(remindDate);
			builder.append(", ");
		}
		if (message != null) {
			builder.append("message=");
			builder.append(message);
			builder.append(", ");
		}
		if (remindFollowers != null) {
			builder.append("remindFollowers=");
			builder.append(remindFollowers);
			builder.append(", ");
		}
		if (boxKey != null) {
			builder.append("boxKey=");
			builder.append(boxKey);
			builder.append(", ");
		}
		if (pipelineKey != null) {
			builder.append("pipelineKey=");
			builder.append(pipelineKey);
			builder.append(", ");
		}
		if (status != null) {
			builder.append("status=");
			builder.append(status);
			builder.append(", ");
		}
		if (reminderKey != null) {
			builder.append("reminderKey=");
			builder.append(reminderKey);
			builder.append(", ");
		}
		if (creatorName != null) {
			builder.append("creatorName=");
			builder.append(creatorName);
			builder.append(", ");
		}
		if (key != null) {
			builder.append("key=");
			builder.append(key);
			builder.append(", ");
		}
		if (otherItemsMap != null) {
			builder.append("otherItemsMap=");
			builder.append(otherItemsMap);
		}
		builder.append("]");
		return builder.toString();
	}

}