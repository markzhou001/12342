package com.code.core.util;



public enum ErrMsg implements ErrCtg {
    SYS_ERR(1000, "系统本身处理异常"),
    SYS_PARAM_ERR(1001, "调用方参数错误"),
    REQ_CONCURRENT_COUNT_LIMIT(1101, "并发总数超出限制"),
    REQ_FREQUENCY_LIMIT(1102, "访问频率限制"),
    NET_ERROR(2000, "网络错误"),
    NET_REQ_EX(2001, "网络请求异常"),
    NET_REQ_TIMEOUT(2002, "网络请求超时"),
    SERVER_500(500, "服务响应错误"),
    SERVER_TIMEOUT(2004, "服务响应超时"),
    SQL_ERR(3000, "数据库操作失败"),
    SQL_CONNECT_POOL_ERR(3001, "数据库连接池错误"),
    BAIDU_ERR(5001, "百度错误"),
    GAODE_ERR(5002, "高德错误"),
    THIRD_CALL_ERR(4000, "调用第三方服务错误"),
    THIRD_NET_ERR(4100, "调用第三方服务网络错误"),
    THIRD_REQ_LIMIT(4202, "调用第三方请求限制"),
    THIRD_BALANCE_LIMIT(4203, "调用第三方余额不足");

    private int code;
    private String name;

    private ErrMsg(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getCode() {
        return this.code;
    }
}
