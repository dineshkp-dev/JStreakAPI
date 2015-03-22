/**
 * 
 */
package main.com.koreinfo.crm.streakapi.queryStreak.resources.streakObjects;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author dineshkp
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"name",
"key"
})
public class Stage {
	private String name;
	private String key;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
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
	return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	}
	

}
