package com.nikola.chk.message_service.entity;

/**
 * Created by Nikolas on 20.09.2015.
 */
public class SecurityUser {
    private Integer id;
    private String userPassword, userLogin;
    private User user;


    public SecurityUser(Integer id, String userPassword, String userLogin, User user) {
        this.id = id;
        this.userPassword = userPassword;
        this.userLogin = userLogin;
        this.user = user;
    }



    public SecurityUser() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
