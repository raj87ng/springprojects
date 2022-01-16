package com.rajat.springparallelwebclients.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class User {

    private int id;

    @JsonCreator
    public User(@JsonProperty("id") int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }
}
