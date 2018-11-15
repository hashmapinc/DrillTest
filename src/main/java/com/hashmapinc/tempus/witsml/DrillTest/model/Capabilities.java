
package com.hashmapinc.tempus.witsml.DrillTest.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:caps.properties")
@ConfigurationProperties(prefix = "dtcap.capability.caps")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "maxRequestLatestValues",
    "cascadedDelete",
    "function"
})
public class Capabilities {

    @JsonProperty("maxRequestLatestValues")
    private String maxRequestLatestValues;
    @JsonProperty("cascadedDelete")
    private String cascadedDelete;

    @JsonProperty("function")
    private List<Function> function;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("maxRequestLatestValues")
    public String getMaxRequestLatestValues() {
        return maxRequestLatestValues;
    }

    @JsonProperty("maxRequestLatestValues")
    public void setMaxRequestLatestValues(String maxRequestLatestValues) {
        this.maxRequestLatestValues = maxRequestLatestValues;
    }

    @JsonProperty("cascadedDelete")
    public String getCascadedDelete() {
        return cascadedDelete;
    }

    @JsonProperty("cascadedDelete")
    public void setCascadedDelete(String cascadedDelete) {
        this.cascadedDelete = cascadedDelete;
    }

    @JsonProperty("function")
    public List<Function> getFunction() {
        return function;
    }

    @Autowired
    @JsonProperty("function")
    public void setFunction(List<Function> function) {
        this.function = function;
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
        return new ToStringBuilder(this).append("maxRequestLatestValues", maxRequestLatestValues).append("cascadedDelete", cascadedDelete).append("function", function).append("additionalProperties", additionalProperties).toString();
    }

}
