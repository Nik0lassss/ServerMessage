package com.nikola.chk.message_service.entity;

/**
 * Created by Nikola on 9/4/2015.
 */

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {


    private Integer user_id;
    private String user_login;
    private String first_name;
    private String last_name;
    private String photo_avatar;


    public User(Integer user_id, String user_login, String first_name, String last_name, String photo_avatar) {
        this.user_id = user_id;
        this.user_login = user_login;
        this.first_name = first_name;
        this.last_name = last_name;
        this.photo_avatar = photo_avatar;
    }

    public User() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
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

    public String getPhoto_avatar() {
        return photo_avatar;
    }

    public void setPhoto_avatar(String photo_avatar) {
        this.photo_avatar = photo_avatar;
    }
}
