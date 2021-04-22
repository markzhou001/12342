package com.code.ordertools;

import com.code.config.GlobalConfig;
import com.code.config.REDISDTO;
import com.code.core.lib.RedisUtil;

//redis连接示例
public class RedisDemo {

    public void redisClientDemo(){

        REDISDTO redisData =  new GlobalConfig().getRedisCoreSrv("stg");
        RedisUtil my_rd = new RedisUtil();
        my_rd.getConnection(redisData.getAddr(),redisData.getPort(),redisData.getPassWord());
        String re_v= my_rd.getForString("order_call_time");

    }
}
