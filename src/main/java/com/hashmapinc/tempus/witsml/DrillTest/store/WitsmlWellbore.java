package com.hashmapinc.tempus.witsml.DrillTest.store;

import javax.persistence.*;

@Entity(name = "WitsmlWellbore")
@NamedQuery(name = "WitsmlWellbore.findByUid", query = "SELECT w FROM WitsmlWellbore w WHERE w.uid = ?1 AND w.welluid = ?2")
public class WitsmlWellbore {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="welluid")
    private String welluid;

    private String uid;

    public Long getId() {
        return id;
    }

    @Lob
    @Column(columnDefinition = "CLOB NOT NULL")
    private String data;

    public void setId(Long id) {
        this.id = id;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public String getUuid() {
        return uid;
    }

    public String getWelluid() {
        return welluid;
    }

    public void setWelluid(String welluid) {
        this.welluid = welluid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
