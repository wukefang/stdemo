package com.st.demo.controller;

import com.st.demo.service.DemoService;
import com.st.demo.service.IDemoService;
import com.st.util.TraceIdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/demo")
public class DemoController {

    Logger LOG = LoggerFactory.getLogger(DemoController.class);

    @Resource
    private IDemoService iDemoService;

    @RequestMapping(value = "/insertTest")
    public String insertTest(HttpServletRequest request, HttpServletResponse response){
        LOG.info("DemoController [insertTest]");
        iDemoService.insertTest();
        return "insertTest";
    }


    @RequestMapping(value = "/queryTest")
    public String queryTest(HttpServletRequest request, HttpServletResponse response){
        LOG.info("DemoController [queryTest]");
        iDemoService.queryTest();
        return "queryTest";
    }
}
