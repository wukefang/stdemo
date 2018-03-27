package com.st.dataSource;

public enum DBType {

    READ("read"),
    WRITE("write");
    private String type;

    DBType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
