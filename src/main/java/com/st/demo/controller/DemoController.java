package com.st.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/demo")
public class DemoController {


    @RequestMapping(value = "/demoController")
    public String demoCtroller(HttpServletRequest request, HttpServletResponse response){
        System.out.println("aaa");
        return "helloWorld";
    }
}
