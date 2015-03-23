package com.streakapi.crm.queryStreak.resources.streakObjects;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Stages {

	@JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private Map<String, Stage> allStages = new HashMap<String, Stage>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
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
        this.additionalProperties.put(name, value);
        return this;
    }
}
