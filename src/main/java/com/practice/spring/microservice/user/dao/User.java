package com.practice.spring.microservice.user.dao;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

//@JsonFilter("UserFilter")
@ToString
@AllArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) // ignores all the null values in json response.
public class User {

    private Integer id;

    @Size(min = 3, message = "Name must have at least 3 characters")
    private String name;

    //    @JsonIgnore //Ignore this field in all responses.[Static Filtering]
    @Past(message = "Birth Date can not be greater than present")
    private Date birthDate;
}
