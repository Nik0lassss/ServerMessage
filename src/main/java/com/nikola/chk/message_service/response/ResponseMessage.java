package com.nikola.chk.message_service.response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by User on 08.10.2015.
 */
@XmlRootElement
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ResponseMessage implements java.io.Serializable{
    private int code;
    private String message;

    public ResponseMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
