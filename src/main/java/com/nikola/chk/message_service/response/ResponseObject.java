package com.nikola.chk.message_service.response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by User on 02.10.2015.
 */
@XmlRootElement
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ResponseObject implements java.io.Serializable{
    private int code;
    private Object responseObject;

    public ResponseObject(int code, Object responseObject) {
        this.code = code;
        this.responseObject = responseObject;
    }

    public Object getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(Object responseObject) {
        this.responseObject = responseObject;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
