package com.st.demo.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.Resource;


public class DynamicDataSource extends AbstractRoutingDataSource {

    @Resource
    private ReadWriteDataSource readWriteDataSource;

    @Override
    protected Object determineCurrentLookupKey() {
        System.out.println("DynamicDataSource [determineCurrentLookupKey]");
        if(readWriteDataSource.isReadOnly()){
            return DBType.READ.getType();
        }
        return DBType.WRITE.getType();
    }

}
