package com.nikola.chk.message_service.entity;

/**
 * Created by Nikola on 9/4/2015.
 */
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {



    private Integer id;
    private String first_name;
    private String last_name;
    private SecurityUser securityUser;
    private String photo_avatar;


    public User(Integer id, String first_name, String last_name, SecurityUser securityUser, String photo_avatar) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.securityUser = securityUser;
        this.photo_avatar = photo_avatar;
    }

    public String getPhoto_avatar() {
        return photo_avatar;
    }

    public void setPhoto_avatar(String photo_avatar) {
        this.photo_avatar = photo_avatar;
    }


    public User() {
    }




    @JsonIgnore
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public SecurityUser getSecurityUser() {
        return securityUser;
    }

    public void setSecurityUser(SecurityUser securityUser) {
        this.securityUser = securityUser;
    }


}
