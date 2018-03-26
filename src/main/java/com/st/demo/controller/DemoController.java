package com.st.demo.controller;

import com.st.demo.service.IDemoService;
import com.st.util.TraceIdUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/demo")
public class DemoController {

    @Resource
    private IDemoService iDemoService;

    @RequestMapping(value = "/insertTest")
    public String insertTest(HttpServletRequest request, HttpServletResponse response){
        System.out.println("DemoController [insertTest]");
        iDemoService.insertTest();
        return "insertTest";
    }


    @RequestMapping(value = "/queryTest")
    public String queryTest(HttpServletRequest request, HttpServletResponse response){
        System.out.println("DemoController [queryTest]");
        System.out.println(TraceIdUtils.get());
        iDemoService.queryTest();
        return "queryTest";
    }
}
