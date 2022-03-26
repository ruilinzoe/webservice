package com.example.webservice.Model;

import java.util.Date;

public class UserDetail {
    private User user;
    private String uuid;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private Date account_created;
    private Date account_updated;

    public UserDetail(User user) {
        this.user = user;
        uuid = user.getUuid();
        first_name = user.getFirst_name();
        last_name = user.getLast_name();
        username = user.getUsername();
        password = user.getPassword();
        account_created = user.getAccount_created();
        account_updated = user.getAccount_updated();
    }

    public String getUuid() {
        return uuid;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getUsername() {
        return username;
    }

    public Date getAccount_created() {
        return account_created;
    }

    public Date getAccount_updated() {
        return account_updated;
    }
}
