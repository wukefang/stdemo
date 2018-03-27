package com.st.demo.service;

import com.st.demo.model.DemoUser;

import java.util.List;

public interface IDemoService {

    String insertTest(Integer type);

    List<DemoUser> queryTest();
}
