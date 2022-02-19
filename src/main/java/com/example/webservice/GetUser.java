package com.example.webservice;

import com.example.webservice.model.User;

import java.util.Date;

public class GetUser {
    private String id;
    private String username;
    private String first_name;
    private String last_name;
    public Date account_created=new Date();
    public Date account_updated=new Date();

    public static GetUser saveToNewUser(User user){

        GetUser user1=new GetUser();
        user1.setAccount_updated(user.getUpdatedAt());
        user1.setUsername(user.getEmailAddress());
        user1.setLast_name(user.getLastname());
        user1.setFirst_name(user.getFirstname());

        user1.setAccount_created(user.getCreatedAt());
        user1.setId(user.getId());
        return user1;

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getAccount_created() {
        return account_created;
    }

    public void setAccount_created(Date account_created) {
        this.account_created = account_created;
    }

    public Date getAccount_updated() {
        return account_updated;
    }

    public void setAccount_updated(Date account_updated) {
        this.account_updated = account_updated;
    }
}
