package com.xxl.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /**
     * swagger url: http://localhost:9090/swagger-ui.html#/
     * url : http://localhost:9090/hello
     */


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello, Spring Boot!";
    }
}
