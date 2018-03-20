package com.st.demo.util.parser;

import com.st.demo.annotation.DBSource;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class DBAnnotationParser implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Map<String,Object> beanMap = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(DBSource.class);
        Set<String> sets = beanMap.keySet();
        for (String name: sets) {
            System.out.println(name);
        }
    }
}
