
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:caps.properties")
@ConfigurationProperties(prefix = "dtcap.capability")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "serverType",
    "version",
    "capabilities"
})
public class ServerCapability {

    @JsonProperty("serverType")
    private String serverType;
    @JsonProperty("version")
    private String version;

    @JsonProperty("capabilities")
    private Capabilities capabilities;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("serverType")
    public String getServerType() {
        return serverType;
    }

    @JsonProperty("serverType")
    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("capabilities")
    public Capabilities getCapabilities() {
        return capabilities;
    }

    @Autowired
    @JsonProperty("capabilities")
    public void setCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities;
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
        return new ToStringBuilder(this).append("serverType", serverType).append("version", version).append("capabilities", capabilities).append("additionalProperties", additionalProperties).toString();
    }

}
