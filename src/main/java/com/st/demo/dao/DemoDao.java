package com.st.demo.dao;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;


@Repository
public class DemoDao implements IDemoDao{


    @Bean
    public DruidDataSource getDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }


    @Override
    public int insert() {
        return 0;
    }
}
