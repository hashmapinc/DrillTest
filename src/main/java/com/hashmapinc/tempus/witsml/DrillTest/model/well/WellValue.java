
package com.hashmapinc.tempus.witsml.DrillTest.model.well;

import java.util.HashMap;
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
    "id",
    "name",
    "company",
    "timeZone",
    "creationTimeUtc",
    "lastUpdateTimeUtc",
    "data"
})
public class WellValue {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("company")
    private String company;
    @JsonProperty("contentType")
    private String contentType;
    @JsonProperty("timeZone")
    private String timeZone;
    @JsonProperty("creationTimeUtc")
    private String creationTimeUtc;
    @JsonProperty("lastUpdateTimeUtc")
    private String lastUpdateTimeUtc;
    @JsonProperty("streamingState")
    private String streamingState;
    @JsonProperty("data")
    private String data;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("company")
    public String getCompany() {
        return company;
    }

    @JsonProperty("company")
    public void setCompany(String company) {
        this.company = company;
    }

    @JsonProperty("timeZone")
    public String getTimeZone() {
        return timeZone;
    }

    @JsonProperty("timeZone")
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @JsonProperty("creationTimeUtc")
    public String getCreationTimeUtc() {
        return creationTimeUtc;
    }

    @JsonProperty("creationTimeUtc")
    public void setCreationTimeUtc(String creationTimeUtc) {
        this.creationTimeUtc = creationTimeUtc;
    }

    @JsonProperty("lastUpdateTimeUtc")
    public String getLastUpdateTimeUtc() {
        return lastUpdateTimeUtc;
    }

    @JsonProperty("lastUpdateTimeUtc")
    public void setLastUpdateTimeUtc(String lastUpdateTimeUtc) {
        this.lastUpdateTimeUtc = lastUpdateTimeUtc;
    }

    @JsonProperty("data")
    public String getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(String data) {
        this.data = data;
    }

    @JsonProperty("contentType")
    public String getContentType() {
        return contentType;
    }

    @JsonProperty("contentType")
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @JsonProperty("streamingState")
    public String getStreamingState() {
        return streamingState;
    }

    @JsonProperty("streamingState")
    public void setStreamingState(String streamingState) {
        this.streamingState = streamingState;
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
        return new ToStringBuilder(this).append("id", id).append("name", name).append("company", company).append("timeZone", timeZone).append("creationTimeUtc", creationTimeUtc).append("lastUpdateTimeUtc", lastUpdateTimeUtc).append("data", data).append("additionalProperties", additionalProperties).toString();
    }
}
