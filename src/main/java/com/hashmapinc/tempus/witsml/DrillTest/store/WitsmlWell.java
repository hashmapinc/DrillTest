package com.hashmapinc.tempus.witsml.DrillTest.store;

import javax.persistence.*;

@Entity(name = "WitsmlWell")
@NamedQuery(name = "WitsmlWell.findByUid", query = "SELECT w FROM WitsmlWell w WHERE w.uid = ?1")
public class WitsmlWell {

    @Id
    @GeneratedValue
    private Long id;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

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
}
