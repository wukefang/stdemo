package com.st.demo.controller;

import com.st.demo.annotation.DBSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/demo")
public class DemoController {


    @RequestMapping(value = "/demoController")
    @DBSource
    public String demoController(HttpServletRequest request, HttpServletResponse response){
        System.out.println("aaa");
        return "helloWorld";
    }
}
