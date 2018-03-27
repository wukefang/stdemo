package com.st.service;

import com.st.model.DemoUser;

import java.util.List;

public interface IDemoService {

    String insertTest(Integer type);

    List<DemoUser> queryTest();
}
