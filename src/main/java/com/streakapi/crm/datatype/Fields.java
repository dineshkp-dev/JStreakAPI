package com.streakapi.crm.datatype;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Fields {
    @JsonIgnore
    private Map<String, Object> allFields = new HashMap<String, Object>();

    public Fields withAllFields(String name, Object value) {
        this.allFields.put(name, value);
        return this;
    }
    
    @JsonAnyGetter
    public Map<String, Object> getAllFields() {
        return this.allFields;
    }

    @JsonAnySetter
    public void setAllFields(String name, Object value) {
        this.allFields.put(name, value);
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fields [");
		if (allFields != null) {
			builder.append("allFields=");
			builder.append(allFields);
		}
		builder.append("]");
		return builder.toString();
	}

}
