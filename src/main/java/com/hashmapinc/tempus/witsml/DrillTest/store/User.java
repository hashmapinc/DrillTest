package com.hashmapinc.tempus.witsml.DrillTest.store;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "well.findByName", query = "SELECT u FROM Users u WHERE u.name = ?1")
public class User {

    @Id
    @GeneratedValue
    private long Id;

    private String userName;

    private String password;

    private String token;

}
