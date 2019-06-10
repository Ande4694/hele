package com.example.demo.Model;

import javax.validation.constraints.NotEmpty;


// POJO MED CONSTRUCTORS, kan ikke være "tom"
public class SearchCriteria {

    @NotEmpty(message = "username can't be empty 2")
    String username;

    public SearchCriteria() {
    }

    public SearchCriteria(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
