
package com.hashmapinc.tempus.witsml.DrillTest.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:caps.properties")
@ConfigurationProperties(prefix = "dtcap.capability.caps.function.dataobject")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "maxDataNodes",
    "maxDataPoints"
})
public class DataObject {

    @JsonProperty("name")
    private String name;
    @JsonProperty("maxDataNodes")
    private String maxDataNodes;
    @JsonProperty("maxDataPoints")
    private String maxDataPoints;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("maxDataNodes")
    public String getMaxDataNodes() {
        return maxDataNodes;
    }

    @JsonProperty("maxDataNodes")
    public void setMaxDataNodes(String maxDataNodes) {
        this.maxDataNodes = maxDataNodes;
    }

    @JsonProperty("maxDataPoints")
    public String getMaxDataPoints() {
        return maxDataPoints;
    }

    @JsonProperty("maxDataPoints")
    public void setMaxDataPoints(String maxDataPoints) {
        this.maxDataPoints = maxDataPoints;
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
        return new ToStringBuilder(this).append("name", name).append("maxDataNodes", maxDataNodes).append("maxDataPoints", maxDataPoints).append("additionalProperties", additionalProperties).toString();
    }

}
