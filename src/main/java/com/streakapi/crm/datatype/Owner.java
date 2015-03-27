package com.streakapi.crm.datatype;

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
    "email",
    "isOwner"
})
public class Owner {

    @JsonProperty("email")
    private String email;
    @JsonProperty("isOwner")
    private Boolean isOwner;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The email
     */
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The email
     */
    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    public Owner withEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * 
     * @return
     *     The isOwner
     */
    @JsonProperty("isOwner")
    public Boolean getIsOwner() {
        return isOwner;
    }

    /**
     * 
     * @param isOwner
     *     The isOwner
     */
    @JsonProperty("isOwner")
    public void setIsOwner(Boolean isOwner) {
        this.isOwner = isOwner;
    }

    public Owner withIsOwner(Boolean isOwner) {
        this.isOwner = isOwner;
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

    public Owner withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
