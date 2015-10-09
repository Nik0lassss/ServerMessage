package com.nikola.chk.message_service.entity;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Nikola on 9/9/2015.
 */
@XmlRootElement
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Message implements java.io.Serializable{
    private Integer id;
    private String message;

    private User user_from;
    private User user_to;
//    private Integer from_id;
//    private Integer to_id;


    public Message() {
    }


    public Message( Integer id, String message, User user_from, User user_to) {
        this.user_to = user_to;
        this.id = id;
        this.message = message;
        this.user_from = user_from;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser_from() {
        return user_from;
    }

    public void setUser_from(User user_from) {
        this.user_from = user_from;
    }

    public User getUser_to() {
        return user_to;
    }

    public void setUser_to(User user_to) {
        this.user_to = user_to;
    }
}
