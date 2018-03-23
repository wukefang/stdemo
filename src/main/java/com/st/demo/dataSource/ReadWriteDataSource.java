package com.st.demo.dataSource;

import org.springframework.stereotype.Service;


@Service
public class ReadWriteDataSource  {

    public ThreadLocal<Boolean> readOnly = new ThreadLocal<>();

    public Boolean isReadOnly(){
        if(readOnly.get()==null){
            return false;
        }
        return readOnly.get();
    }


    public void setReadOnly(Boolean b){
        readOnly.set(b);
    }

    public void clear(){
        readOnly.remove();
    }
}
