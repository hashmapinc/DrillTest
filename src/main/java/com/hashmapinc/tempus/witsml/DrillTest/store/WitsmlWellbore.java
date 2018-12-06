package com.hashmapinc.tempus.witsml.DrillTest.store;

import javax.persistence.*;

@Entity(name = "WitsmlWellbore")
@NamedQuery(name = "WitsmlWellbore.findByUid", query = "SELECT w FROM WitsmlWellbore w WHERE w.uid = ?1")
public class WitsmlWellbore {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="welluid")
    private String wellUid;

    private String uid;
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

    public String getWellUid() {
        return wellUid;
    }

    public void setWellUid(String wellUid) {
        this.wellUid = wellUid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
