package com.st.dataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.Resource;


public class DynamicDataSource extends AbstractRoutingDataSource {

    Logger LOG = LoggerFactory.getLogger(DynamicDataSource.class);

    @Resource
    private ReadWriteDataSource readWriteDataSource;

    @Override
    protected Object determineCurrentLookupKey() {
        LOG.info("DynamicDataSource [determineCurrentLookupKey]");
        if(readWriteDataSource.isReadOnly()){
            return DBType.READ.getType();
        }
        return DBType.WRITE.getType();
    }

}
