package com.hashmapinc.tempus.witsml.DrillTest.store;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Well.findByUUid", query = "SELECT w FROM Well w WHERE w.uuid = ?1")
@NamedQuery(name = "well.findByName", query = "SELECT w FROM Well w WHERE w.name = ?1")
public class Well {

    @Id
    @GeneratedValue
    private Long id;

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
