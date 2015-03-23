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
    "name",
    "key",
    "type"
})
public class Field {

	public enum TYPE {TEXT_INPUT, DATE, PERSON};
	
    private String name;
    private String key;
    private TYPE type;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    public Field withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 
     * @return
     *     The key
     */
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
    private void setKey(String key) {
        this.key = key;
    }

    public Field withKey(String key) {
        this.key = key;
        return this;
    }

    /**
     * 
     * @return
     *     The type
     */
    @JsonIgnore
    public TYPE getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    @JsonProperty("type")
    private void setType(TYPE type) {
        this.type = type;
    }

    public Field withType(TYPE type) {
        this.type = type;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Field withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
