package com.streakapi.crm.queryStreak.resources.streakObjects;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"key",
	"value"
})
public class BoxField {

	private String key;
	private String value;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return
	 * The key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 
	 * @param key
	 * The key
	 */
	@JsonProperty("key")
	private void setKey(String key) {
		this.key = key;
	}

	/**
	 * 
	 * @return
	 * The value
	 */
	@JsonProperty("value")
	public String getValue() {
		return value;
	}

	/**
	 * 
	 * @param value
	 * The value
	 */
	@JsonProperty("value")
	public void setValue(String value) {
		this.value = value;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}