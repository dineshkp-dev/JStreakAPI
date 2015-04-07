package com.streakapi.crm.datatype;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * The Box object contains the following information about a Box in Streak.  
 * <li>pipelineKey</li>
 * <li>creatorKey</li>
 * <li>creationTimestamp (in dd-MM-yyyy, HH:00:00 format) </li>
 * <li>lastUpdatedTimestamp (in dd-MM-yyyy, HH:00:00 format) </li>
 * <li>name</li>
 * <li>notes</li>
 * <li>stageKey</li>
 * <li>fields</li>
 * <li>followerKeys</li>
 * <li>followerCount</li>
 * <li>commentCount</li>
 * <li>reminderCount</li>
 * <li>gmailThreadCount</li>
 * <li>fileCount</li>
 * <li>boxKey</li>
 * <li>key</li>
 * <li>otherItemsMap (Contains a Map of other items from Streak API that are not listed above) </li>
 * @see <a href="https://www.streak.com/api/">https://www.streak.com/api/</a>
 * @author dineshkp
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pipelineKey",
    "creatorKey",
    "creationTimestamp",
    "lastUpdatedTimestamp",
    "name",
    "notes",
    "stageKey",
    "fields",
    "followerKeys",
    "followerCount",
    "commentCount",
    "reminderCount",
    "gmailThreadCount",
    "fileCount",
    "boxKey",
    "key"
})
public class Box {

    private String pipelineKey;
    private String creatorKey;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy,HH:00:00")
    private Calendar creationTimestamp;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy,HH:00:00")
    private Calendar lastUpdatedTimestamp;
    private String name;
    private String notes;
    private String stageKey;
    private Fields fields;
    private List<String> followerKeys = new ArrayList<String>();
    private Long followerCount;
    private Long commentCount;
    private Long reminderCount;
    private Long gmailThreadCount;
    private Long fileCount;
    private String boxKey;
    private String key;
    @JsonIgnore
    private Map<String, Object> otherItemsMap = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The pipelineKey
     */
//    @JsonProperty("pipelineKey")
    @JsonIgnore
    public String getPipelineKey() {
        return pipelineKey;
    }

    /**
     * 
     * @param pipelineKey
     *     The pipelineKey
     */
    @JsonProperty("pipelineKey")
    public void setPipelineKey(String pipelineKey) {
        this.pipelineKey = pipelineKey;
    }

    public Box withPipelineKey(String pipelineKey) {
        this.pipelineKey = pipelineKey;
        return this;
    }

    /**
     * 
     * @return
     *     The creatorKey
     */
    @JsonIgnore
    @JsonProperty("creatorKey")
    public String getCreatorKey() {
        return creatorKey;
    }

    /**
     * 
     * @param creatorKey
     *     The creatorKey
     */
    @JsonProperty("creatorKey")
    public void setCreatorKey(String creatorKey) {
        this.creatorKey = creatorKey;
    }

    public Box withCreatorKey(String creatorKey) {
        this.creatorKey = creatorKey;
        return this;
    }

    /**
     * 
     * @return
     *     The creationTimestamp
     */
//    @JsonProperty("creationTimestamp")
    @JsonIgnore
    public Calendar getCreationTimestamp() {
        return creationTimestamp;
    }

    /**
     * 
     * @param creationTimestamp
     *     The creationTimestamp
     */
    @JsonProperty("creationTimestamp")
    public void setCreationTimestamp(Calendar creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public Box withCreationTimestamp(Calendar creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
        return this;
    }

    /**
     * 
     * @return
     *     The lastUpdatedTimestamp
     */
//    @JsonProperty("lastUpdatedTimestamp")
    @JsonIgnore
    public Calendar getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }

    /**
     * 
     * @param lastUpdatedTimestamp
     *     The lastUpdatedTimestamp
     */
    @JsonProperty("lastUpdatedTimestamp")
    public void setLastUpdatedTimestamp(Calendar lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }

    public Box withLastUpdatedTimestamp(Calendar lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
        return this;
    }

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Box withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 
     * @return
     *     The notes
     */
    @JsonProperty("notes")
    public String getNotes() {
        return notes;
    }

    /**
     * 
     * @param notes
     *     The notes
     */
    @JsonProperty("notes")
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Box withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    /**
     * 
     * @return
     *     The stageKey
     */
    @JsonProperty("stageKey")
    public String getStageKey() {
        return stageKey;
    }

    /**
     * 
     * @param stageKey
     *     The stageKey
     */
    @JsonProperty("stageKey")
    public void setStageKey(String stageKey) {
        this.stageKey = stageKey;
    }

    public Box withStageKey(String stageKey) {
        this.stageKey = stageKey;
        return this;
    }

    /**
     * 
     * @return
     *     The fields
     */
    @JsonProperty("fields")
    public Fields getFields() {
        return fields;
    }

    /**
     * 
     * @param fields
     *     The fields
     */
    @JsonProperty("fields")
    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public Box withFields(Fields fields) {
        this.fields = fields;
        return this;
    }

    /**
     * 
     * @return
     *     The followerKeys
     */
//    @JsonProperty("followerKeys")
    @JsonIgnore
    public List<String> getFollowerKeys() {
        return followerKeys;
    }

    /**
     * 
     * @param followerKeys
     *     The followerKeys
     */
    @JsonProperty("followerKeys")
    public void setFollowerKeys(List<String> followerKeys) {
        this.followerKeys = followerKeys;
    }

    public Box withFollowerKeys(List<String> followerKeys) {
        this.followerKeys = followerKeys;
        return this;
    }

    /**
     * 
     * @return
     *     The followerCount
     */
//    @JsonProperty("followerCount")
    @JsonIgnore
    public Long getFollowerCount() {
        return followerCount;
    }

    /**
     * 
     * @param followerCount
     *     The followerCount
     */
    @JsonProperty("followerCount")
    public void setFollowerCount(Long followerCount) {
        this.followerCount = followerCount;
    }

    public Box withFollowerCount(Long followerCount) {
        this.followerCount = followerCount;
        return this;
    }

    /**
     * 
     * @return
     *     The commentCount
     */
//    @JsonProperty("commentCount")
    @JsonIgnore
    public Long getCommentCount() {
        return commentCount;
    }

    /**
     * 
     * @param commentCount
     *     The commentCount
     */
    @JsonProperty("commentCount")
    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Box withCommentCount(Long commentCount) {
        this.commentCount = commentCount;
        return this;
    }

    /**
     * 
     * @return
     *     The reminderCount
     */
//    @JsonProperty("reminderCount")
    @JsonIgnore
    public Long getReminderCount() {
        return reminderCount;
    }

    /**
     * 
     * @param reminderCount
     *     The reminderCount
     */
    @JsonProperty("reminderCount")
    public void setReminderCount(Long reminderCount) {
        this.reminderCount = reminderCount;
    }

    public Box withReminderCount(Long reminderCount) {
        this.reminderCount = reminderCount;
        return this;
    }

    /**
     * 
     * @return
     *     The gmailThreadCount
     */
//    @JsonProperty("gmailThreadCount")
    @JsonIgnore
    public Long getGmailThreadCount() {
        return gmailThreadCount;
    }

    /**
     * 
     * @param gmailThreadCount
     *     The gmailThreadCount
     */
    @JsonProperty("gmailThreadCount")
    public void setGmailThreadCount(Long gmailThreadCount) {
        this.gmailThreadCount = gmailThreadCount;
    }

    public Box withGmailThreadCount(Long gmailThreadCount) {
        this.gmailThreadCount = gmailThreadCount;
        return this;
    }

    /**
     * 
     * @return
     *     The fileCount
     */
//    @JsonProperty("fileCount")
    @JsonIgnore
    public Long getFileCount() {
        return fileCount;
    }

    /**
     * 
     * @param fileCount
     *     The fileCount
     */
    @JsonProperty("fileCount")
    public void setFileCount(Long fileCount) {
        this.fileCount = fileCount;
    }

    public Box withFileCount(Long fileCount) {
        this.fileCount = fileCount;
        return this;
    }

    /**
     * 
     * @return
     *     The boxKey
     */
//    @JsonProperty("boxKey")
    @JsonIgnore
    public String getBoxKey() {
        return boxKey;
    }

    /**
     * 
     * @param boxKey
     *     The boxKey
     */
    @JsonProperty("boxKey")
    public void setBoxKey(String boxKey) {
        this.boxKey = boxKey;
    }

    public Box withBoxKey(String boxKey) {
        this.boxKey = boxKey;
        return this;
    }

    /**
     * 
     * @return
     *     The key
     */
//    @JsonProperty("key")
    @JsonIgnore
    public String getKey() {
        return key;
    }

    /**
     * 
     * @param key
     *     The key
     */
    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    public Box withKey(String key) {
        this.key = key;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.otherItemsMap;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.otherItemsMap.put(name, value);
    }

    public Box withAdditionalProperty(String name, Object value) {
        this.otherItemsMap.put(name, value);
        return this;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Box [");
		if (pipelineKey != null) {
			builder.append("pipelineKey=");
			builder.append(pipelineKey);
			builder.append(", ");
		}
		if (creatorKey != null) {
			builder.append("creatorKey=");
			builder.append(creatorKey);
			builder.append(", ");
		}
		if (creationTimestamp != null) {
			builder.append("creationTimestamp=");
			builder.append(creationTimestamp);
			builder.append(", ");
		}
		if (lastUpdatedTimestamp != null) {
			builder.append("lastUpdatedTimestamp=");
			builder.append(lastUpdatedTimestamp);
			builder.append(", ");
		}
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (notes != null) {
			builder.append("notes=");
			builder.append(notes);
			builder.append(", ");
		}
		if (stageKey != null) {
			builder.append("stageKey=");
			builder.append(stageKey);
			builder.append(", ");
		}
		if (fields != null) {
			builder.append("fields=");
			builder.append(fields);
			builder.append(", ");
		}
		if (followerKeys != null) {
			builder.append("followerKeys=");
			builder.append(followerKeys);
			builder.append(", ");
		}
		if (followerCount != null) {
			builder.append("followerCount=");
			builder.append(followerCount);
			builder.append(", ");
		}
		if (commentCount != null) {
			builder.append("commentCount=");
			builder.append(commentCount);
			builder.append(", ");
		}
		if (reminderCount != null) {
			builder.append("reminderCount=");
			builder.append(reminderCount);
			builder.append(", ");
		}
		if (gmailThreadCount != null) {
			builder.append("gmailThreadCount=");
			builder.append(gmailThreadCount);
			builder.append(", ");
		}
		if (fileCount != null) {
			builder.append("fileCount=");
			builder.append(fileCount);
			builder.append(", ");
		}
		if (boxKey != null) {
			builder.append("boxKey=");
			builder.append(boxKey);
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
