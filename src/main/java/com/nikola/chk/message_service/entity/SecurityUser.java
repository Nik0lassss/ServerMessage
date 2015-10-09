package com.nikola.chk.message_service.entity;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Nikolas on 20.09.2015.
 */
@XmlRootElement
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SecurityUser {
    private Integer user_id;
    private String userPassword;

    public SecurityUser(Integer user_id, String userPassword) {
        this.user_id = user_id;
        this.userPassword = userPassword;
    }

    public SecurityUser() {
    }


    @JsonIgnore
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
