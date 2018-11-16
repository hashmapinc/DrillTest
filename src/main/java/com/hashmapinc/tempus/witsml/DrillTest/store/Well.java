package com.hashmapinc.tempus.witsml.DrillTest.store;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Well.findByUUid", query = "SELECT w FROM Well w WHERE w.uuid = ?1")
@NamedQuery(name = "well.findByName", query = "SELECT w FROM Well w WHERE w.name = ?1")
public class Well {

    @Id
    @GeneratedValue
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setStreamingState(String streamingState) {
        this.streamingState = streamingState;
    }

    public void setLiveState(String liveState) {
        this.liveState = liveState;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String uuid;

    private String name;

    private String company;

    @Column(name = "streaming_state")
    private String streamingState;

    @Column(name = "live_state")
    private String liveState;

    @Column(name = "time_zone")
    private String timeZone;

    @Lob
    @Column(columnDefinition = "CLOB NOT NULL")
    private String data;

    public String getData() {
        return data;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public String getCompany() {
        return company;
    }

    public String getStreamingState() {
        return streamingState;
    }

    public String getLiveState() {
        return liveState;
    }

    public String getTimeZone() {
        return timeZone;
    }
}
