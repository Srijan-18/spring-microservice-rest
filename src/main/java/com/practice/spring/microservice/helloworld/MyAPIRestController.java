package com.practice.spring.microservice.helloworld;

import org.springframework.web.bind.annotation.*;

@RestController
public class MyAPIRestController {

    @RequestMapping(method = RequestMethod.GET, path = "/request-hello")
    public String HelloString(){
        return "My Hello String From Request Mapping";
    }

    @GetMapping( path = "/hello")
    public String getHelloString(){
        return "My Hello String From Get Mapping";
    }

    @GetMapping( path = "/hello-bean")
    public MyCustomBean getHelloBean(){
        return new MyCustomBean();
    }

    /**
     *  Get Mapping with path Variable
     * @param studentName : name of student
     * @return myCustomBean :  custom bean with name as given in path variable
     */
    @GetMapping( path = "/hello-bean/{studentName}")
    public MyCustomBean getHelloBean(@PathVariable String studentName){
        return new MyCustomBean(studentName);
    }
}
