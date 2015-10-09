package com.nikola.chk.message_service.response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by User on 02.10.2015.
 */
@XmlRootElement
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ResponseList implements java.io.Serializable{
    private int code;
    private List<Object> responseList;

    public ResponseList(int code, List<Object> responseList) {
        this.code = code;
        this.responseList = responseList;
    }

    public ResponseList(int code) {
        this.code = code;
    }

    public ResponseList(List<Object> responseList) {
        this.responseList = responseList;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Object> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<Object> responseList) {
        this.responseList = responseList;
    }

}
