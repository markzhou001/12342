package com.code.config;

public class GlobalConfig {

    public static JDBCDTO getmy_db(String env){
        JDBCDTO data=new JDBCDTO();
        if(env=="stg")
        {
            data.setJdbcUrl("");
            data.setUser("");
            data.setPwd("");
        }
        else if(env=="pre")
        {
            data.setJdbcUrl("");
            data.setUser("");
            data.setPwd("");
        }
        else
        {
            data.setJdbcUrl("jdbc:mysql://baidu.cn:3306/db?zeroDateTimeBehavior=convertToNull");
            data.setUser("");
            data.setPwd("");
        }
        return data;
    }


    public static REDISDTO getRedisCoreSrv(String env){
        REDISDTO data = new REDISDTO();
        if(env.equals("stg"))
        {
            data.setAddr("redis-stg.cn");
            data.setPort(6000);
            data.setServiceName("core_srv_stg");
            data.setPassWord("");

        }
        else if(env.equals("pre"))
        {
            data.setAddr("redis-pre.cn");
            data.setPort(6000);
            data.setServiceName("core_srv_pre");
            data.setPassWord("");
        }

        return data;
    }

}
