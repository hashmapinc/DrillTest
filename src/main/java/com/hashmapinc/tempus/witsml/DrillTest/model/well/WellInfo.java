
package com.hashmapinc.tempus.witsml.DrillTest.model.well;

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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "links",
    "value"
})
public class WellInfo {

    @JsonProperty("links")
    private WellLinks links;
    @JsonProperty("value")
    private List<WellValue> value = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("links")
    public WellLinks getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(WellLinks links) {
        this.links = links;
    }

    @JsonProperty("value")
    public List<WellValue> getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(List<WellValue> value) {
        this.value = value;
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
        return new ToStringBuilder(this).append("links", links).append("value", value).append("additionalProperties", additionalProperties).toString();
    }

}
