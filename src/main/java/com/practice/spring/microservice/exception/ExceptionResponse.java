package com.practice.spring.microservice.exception;

import java.util.Date;

public class ExceptionResponse {

    private Date time;
    private String message;
    private String details;

    public ExceptionResponse(Date time, String message, String details) {
        this.time = time;
        this.message = message;
        this.details = details;
    }

    public Date getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
