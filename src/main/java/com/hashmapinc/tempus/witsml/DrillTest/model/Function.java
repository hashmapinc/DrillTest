
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
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:caps.properties")
@ConfigurationProperties(prefix = "dtcap.capability.caps.function")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "dataObject",
    "name"
})
public class Function {

    @JsonProperty("dataObject")
    private List<DataObject> dataObject;

    @JsonProperty("name")
    private String name;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("dataObject")
    public List<DataObject> getDataObject() {
        return dataObject;
    }

    @Autowired
    @JsonProperty("dataObject")
    public void setDataObject(List<DataObject> dataObject) {
        this.dataObject = dataObject;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
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
        return new ToStringBuilder(this).append("dataObject", dataObject).append("name", name).append("additionalProperties", additionalProperties).toString();
    }

}
