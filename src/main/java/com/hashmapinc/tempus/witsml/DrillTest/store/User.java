package com.hashmapinc.tempus.witsml.DrillTest.store;

import javax.persistence.*;

@Entity
@NamedQuery(name = "user.findByName", query = "SELECT u FROM User u WHERE u.userName = ?1")
public class User {

    @Id
    @GeneratedValue
    private long Id;

    @Column(name = "name")
    private String userName;

    private String password;

    private String token;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
