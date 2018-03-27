package com.st.demo.controller;

import com.st.demo.eunm.ResultEnum;
import com.st.demo.model.DemoUser;
import com.st.demo.service.IDemoService;
import com.st.demo.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/demo")
public class DemoController {

    Logger LOG = LoggerFactory.getLogger(DemoController.class);

    @Resource
    private IDemoService iDemoService;

    @RequestMapping(value = "/insertTest/{type}")
    public String insertTest(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer type){
        LOG.info("DemoController [insertTest]");
        iDemoService.insertTest(type);
        return "insertTest";
    }


    @RequestMapping(value = "/queryTest")
    @ResponseBody
    public ResponseVo queryTest(HttpServletRequest request, HttpServletResponse response){
        LOG.info("DemoController [queryTest]");
        List<DemoUser> userList = iDemoService.queryTest();
        return new ResponseVo(ResultEnum.SUCCESS,userList);
    }
}
