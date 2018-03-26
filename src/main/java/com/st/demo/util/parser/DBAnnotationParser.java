package com.st.demo.util.parser;

import com.st.demo.dataSource.ReadWriteDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Aspect
@Order(-10)
@Component
public class DBAnnotationParser {

    @Resource
    ReadWriteDataSource readWriteDataSource;

    private Logger LOG = LoggerFactory.getLogger(DBAnnotationParser.class);

    @Pointcut(value = "@annotation(com.st.demo.annotation.ReadOnly)")
    public void onPointCut(){

    }
    @Pointcut(value = "execution(* com.st.demo..*.*(..))")
    public void onPointCunt2(){

    }

    @Before(value = "onPointCut() && onPointCunt2()")
    public void handleAnnotation(JoinPoint joinPoint){
        LOG.info("DBAnnotationParser [handleAnnotation]");
        readWriteDataSource.setReadOnly(true);
    }

    @After(value = "onPointCut()&&onPointCunt2()")
    public void clearDB(){
        LOG.info("DBAnnotationParser [clearDB]");
        readWriteDataSource.clear();
    }
}
