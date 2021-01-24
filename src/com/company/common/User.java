package com.company.common;

import java.io.Serializable;

public class User implements Serializable {
    private final Integer id;
    private final String name;
    private final String lastName;
    private final String email;

    public User(Integer id, String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }
}
