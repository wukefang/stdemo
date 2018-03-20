package com.st.demo.util.parser;

import com.st.demo.annotation.DBSource;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareAnnotation;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.SourceFilteringListener;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;
@Aspect
@Component
public class DBAnnotationParser {

    @Pointcut(value = "@annotation(com.st.demo.annotation.DBSource)")
    public void onPointCut(){

    }
    @Pointcut(value = "execution(* com.st.demo.dao.*.*(..))")
    public void onPointCunt2(){

    }

    @Before(value = "onPointCut() && onPointCunt2()")
    public void handleAnnotation(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Annotation[] annotations = methodSignature.getMethod().getAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            if(annotations[i] instanceof DBSource){

            }
        }
    }
}
