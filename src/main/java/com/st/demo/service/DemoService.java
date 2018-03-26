package com.st.demo.service;

import com.st.demo.annotation.ReadOnly;
import com.st.demo.mapper.UserMapper;
import com.st.demo.model.DemoUser;
import com.st.util.TraceIdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DemoService implements IDemoService {

    Logger LOG = LoggerFactory.getLogger(DemoService.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public String insertTest() {
        LOG.info("DemoService [test]");
        int rows = userMapper.insert("wkf","pwd",23);
        System.out.println("rows:"+rows);
        return null;
    }

    @ReadOnly
    @Override
    public List<DemoUser> queryTest(){
        LOG.info("DemoService [queryTest] begin");
        List<DemoUser> demoUsers = userMapper.queryUsersByName("wkf");
        if(demoUsers!=null && demoUsers.size()>0) {
            for (int i = 0; i < demoUsers.size(); i++) {
                LOG.info(demoUsers.get(i).toString());
            }
        }
        LOG.info("DemoService [queryTest] end");
        return demoUsers;
    }
}
