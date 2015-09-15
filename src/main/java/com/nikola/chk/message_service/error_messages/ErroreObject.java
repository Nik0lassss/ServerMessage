package com.nikola.chk.message_service.error_messages;

/**
 * Created by Nikola on 9/9/2015.
 */
public class ErroreObject {
    private String message;
    private int code;

    public ErroreObject(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public ErroreObject() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
