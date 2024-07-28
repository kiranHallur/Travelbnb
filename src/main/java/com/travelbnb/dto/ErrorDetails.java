package com.travelbnb.dto;

import java.util.Date;

public class ErrorDetails {
    private String webRequest;
    private String errorMessage;
    private Date date;

    public ErrorDetails(String webRequest, String errorMessage, Date date) {
        this.webRequest = webRequest;
        this.errorMessage = errorMessage;
        this.date = date;
    }

    public String getWebRequest() {
        return webRequest;
    }

    public void setWebRequest(String webRequest) {
        this.webRequest = webRequest;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
