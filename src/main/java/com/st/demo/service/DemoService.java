package com.st.demo.service;

import com.st.demo.annotation.ReadOnly;
import com.st.demo.mapper.UserMapper;
import com.st.demo.model.DemoUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class DemoService implements IDemoService {

    Logger LOG = LoggerFactory.getLogger(DemoService.class);

    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public String insertTest(Integer type) {
        LOG.info("DemoService [test]");
        int rows = 0;
        rows = userMapper.insert("wkf", "pwd", 23);
        if(type==1) {
        }else if(type==2){
            throw new RuntimeException("rollback");
        }else{
            try {
                throw new Exception("file not found");
            } catch (Exception e) {
                e.printStackTrace();
                //throw new RuntimeException("file not found");
            }
        }
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
