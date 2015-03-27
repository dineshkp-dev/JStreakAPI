package com.streakapi.crm.datatype;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * The Stage object contains the following information:  
 * <li>name</li>
 * <li>key</li>
 * <li>otherItemsMap (Contains a Map of other items from Streak API that are not listed above) </li>
 * @see <a href="https://www.streak.com/api/">https://www.streak.com/api/</a>
 * @author dineshkp
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"name",
"key"
})
public class Stage {
	private String name;
	private String key;
	@JsonIgnore
	private Map<String, Object> otherItemsMap = new HashMap<String, Object>();
	/**
	* 
	* @return
	* The name
	*/
	@JsonProperty("name")
	public String getName() {
	return name;
	}

	/**
	* 
	* @param name
	* The name
	*/
	@JsonProperty("name")
	public void setName(String name) {
	this.name = name;
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
	private void setKey(String key) {
	this.key = key;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
	return this.otherItemsMap;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
	this.otherItemsMap.put(name, value);
	}
	

}
