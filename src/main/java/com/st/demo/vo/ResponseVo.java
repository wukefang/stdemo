package com.st.demo.vo;

import com.st.demo.Eunm.ResultEnum;

public class ResponseVo {

    public ResponseVo(){}

    public ResponseVo(ResultEnum resultEnum){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public ResponseVo(ResultEnum resultEnum,Object data){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        this.data = data;
    }

    private Integer code;
    private String msg;

    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
