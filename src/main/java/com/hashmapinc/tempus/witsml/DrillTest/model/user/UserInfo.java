package com.hashmapinc.tempus.witsml.DrillTest.model.user;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "account",
        "password"
})
public class UserInfo {

    @JsonProperty("account")
    @ApiModelProperty(notes = "The User Name")
    private String account;
    @JsonProperty("password")
    @ApiModelProperty(notes = "Password")
    private String password;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("account")
    @ApiModelProperty(notes = "The User Name")
    public String getAccount() {
        return account;
    }

    @JsonProperty("account")
    @ApiModelProperty(notes = "The User Name")
    public void setAccount(String account) {
        this.account = account;
    }

    @JsonProperty("password")
    @ApiModelProperty(notes = "Password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    @ApiModelProperty(notes = "Password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("account", account).append("password", password).append("additionalProperties", additionalProperties).toString();
    }

}
