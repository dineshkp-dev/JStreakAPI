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
 * The Field object contains the following information:  
 * <li>name(Field-Name)</li>
 * <li>key(Field-Key)</li>
 * <li>TYPE(ENUM Supported types: 'TEXT_INPUT', 'DATE', 'PERSON')</li>
 * <li>otherItemsMap (Contains a Map of other items from Streak API that are not listed above) </li>
 * @see <a href="https://www.streak.com/api/">https://www.streak.com/api/</a>
 * @author dineshkp
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "key",
    "type"
})
public class Field {

	public enum TYPE {TEXT_INPUT, DATE, PERSON, DROPDOWN};
	
    private String name;
    private String key;
    private TYPE type;
    
    @JsonIgnore
    private Map<String, Object> otherItemsMap = new HashMap<String, Object>();

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
        return this.otherItemsMap;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.otherItemsMap.put(name, value);
    }

    public Field withAdditionalProperty(String name, Object value) {
        this.otherItemsMap.put(name, value);
        return this;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Field [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (key != null) {
			builder.append("key=");
			builder.append(key);
			builder.append(", ");
		}
		if (type != null) {
			builder.append("type=");
			builder.append(type);
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
