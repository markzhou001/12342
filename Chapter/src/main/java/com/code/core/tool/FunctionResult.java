package com.code.core.tool;


public class FunctionResult {
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;

    }

    private Integer code;
    private String msg;
    public  FunctionResult(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public void checkCodeByThrow() throws QaException {
        if(this.code!=0){
            throw new QaException(this.msg);
        }
    }

}

