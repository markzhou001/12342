package com.code.baseapi;

import java.io.Serializable;

public class apiReModule implements Serializable {
    private Integer ret;
    private String msg;
    private String su;
    private Object data;

    public apiReModule() {
    }

    public apiReModule(Object data) {
        this.data = data;
    }

    public apiReModule(int ret, String msg) {
        this.ret = ret;
        this.msg = msg;
    }


    public void setRet(Integer ret) {
        this.ret = ret;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSu(String su) {
        this.su = su;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getRet() {
        return ret;
    }

    public String getMsg() {
        return msg;
    }

    public String getSu() {
        return su;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "apiReModule{" +
                "ret=" + ret +
                ", msg='" + msg + '\'' +
                ", su='" + su + '\'' +
                ", data=" + data +
                '}';
    }
}
