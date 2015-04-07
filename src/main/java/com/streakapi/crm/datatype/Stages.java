package com.streakapi.crm.datatype;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * The Stages object contains the following information:  
 * <li>allStages (Contains a Map of Stage names with Stage information.) </li>
 * <li>otherItemsMap (Contains a Map of other items from Streak API that are not listed above) </li>
 * @see <a href="https://www.streak.com/api/">https://www.streak.com/api/</a>
 * @see Stage
 * @author dineshkp
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Stages {

	@JsonIgnore
    private Map<String, Object> otherItemsMap = new HashMap<String, Object>();
    private Map<String, Stage> allStages = new HashMap<String, Stage>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.otherItemsMap;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Stage stage) {
        this.allStages.put(name, stage);
    }

    @JsonProperty
    public void setAllStages(String name, Stage stage) {
        this.allStages.put(name, stage);
    }

    @JsonProperty
    public Map<String, Stage> getAllStages() {
        return this.allStages;
    }
    
    public Stages withAdditionalProperty(String name, Object value) {
        this.otherItemsMap.put(name, value);
        return this;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Stages [");
		if (otherItemsMap != null) {
			builder.append("otherItemsMap=");
			builder.append(otherItemsMap);
			builder.append(", ");
		}
		if (allStages != null) {
			builder.append("allStages=");
			builder.append(allStages);
		}
		builder.append("]");
		return builder.toString();
	}
}
