package com.practice.spring.microservice.helloworld;

import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

public class MyCustomBean {
    private final String name;
    private final int rollNumber;
    private String uuid;


    public MyCustomBean() {
        this("N/A");
    }

    public MyCustomBean(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.uuid = UUID.randomUUID().toString();
    }

    public MyCustomBean(String name) {
        this(name, Integer.MAX_VALUE);
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return String.format("New Student with Name = %s , Roll Number = %d, uuid = %s", name, rollNumber, uuid);
    }
}

