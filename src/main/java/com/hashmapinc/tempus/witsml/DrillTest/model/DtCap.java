
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
@ConfigurationProperties(prefix = "dtcap")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "contact",
    "description",
    "name",
    "vendor",
    "serverCapabilities"
})
public class DtCap {

    @JsonProperty("contact")
    private Contact contact;
    @JsonProperty("description")
    private String description;
    @JsonProperty("name")
    private String name;
    @JsonProperty("vendor")
    private String vendor;

    @JsonProperty("serverCapabilities")
    private List<ServerCapability> serverCapabilities = null;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("contact")
    public Contact getContact() {
        return contact;
    }

    @Autowired
    @JsonProperty("contact")
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("vendor")
    public String getVendor() {
        return vendor;
    }

    @JsonProperty("vendor")
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @JsonProperty("serverCapabilities")
    public List<ServerCapability> getServerCapabilities() {
        return serverCapabilities;
    }

    @Autowired
    @JsonProperty("serverCapabilities")
    public void setServerCapabilities(List<ServerCapability> serverCapabilities) {
        this.serverCapabilities = serverCapabilities;
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
        return new ToStringBuilder(this).append("contact", contact).append("description", description).append("name", name).append("vendor", vendor).append("serverCapabilities", serverCapabilities).append("additionalProperties", additionalProperties).toString();
    }

}
