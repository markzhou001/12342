package com.code.core.lib;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RedisUtil {

    private JedisPool jedisPool = null;
    private JedisSentinelPool jedisPoolForMaster = null;
    private Jedis jedis=null;

    //失败返回1，成功返回0
    public int getConnection(String addr,int port, String auth)
    {
        try {

            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(1024);
            config.setMaxIdle(200);
            config.setMaxWaitMillis(10000);
            config.setTestOnBorrow(true);
            if(auth.isEmpty())
            {
                jedisPool = new JedisPool(config, addr, port, 10000);
            }
            else
            {
                jedisPool = new JedisPool(config, addr, port, 10000, auth);
            }

            jedis=jedisPool.getResource();
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }
    //失败返回1，成功返回0
    public int getConnectionByMaster(String MasterName,String addr,int port, String auth)
    {
        try {

            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(1024);
            config.setMaxIdle(200);
            config.setMaxWaitMillis(10000);
            config.setTestOnBorrow(true);


            Set<String> sentinels = new HashSet<>(Arrays.asList(addr+":"+port));
            jedisPoolForMaster = new JedisSentinelPool(MasterName,sentinels, config, auth);

            // 创建连接池
            //JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels,jedisPoolConfig,"123456");


            jedis=jedisPoolForMaster.getResource();
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }
    //string操作
    public String getForString(String key)
    {
        return jedis.get(key);

    }
    public void setForString(String key,String value)
    {
        jedis.set(key,value);

    }
    public void delForString(String key)
    {
        jedis.del(key);

    }
    //hash操作 todo
    public String hGetForString(String key,String field)
    {
        return jedis.hget(key,field);

    }
    public String hGetAllForString(String key)
    {
        return jedis.hgetAll(key).toString();
    }
    public void hDelForString(String key)
    {
        jedis.hdel(key);

    }
    public void hDelForString(String key,String field)
    {
        jedis.hdel(key,field);

    }

    public void hSetForString(String key,String field,String value)
    {
        jedis.hset(key,field,value);

    }
    //list
    public String lRangeForList(String key,int start,int end)
    {
        return jedis.lrange(key, start ,end).toString();

    }

    //set

    public String SmembersForSet(String key)
    {
        return jedis.smembers(key).toString();

    }
    public String SremForSet(String key,String member)
    {
        return jedis.srem(key,member).toString();

    }
    public String SaddForSet(String key,String member)
    {
        return jedis.sadd(key,member).toString();

    }
    public String zaddForAdd(String key,double score,String member)
    {
        return jedis.zadd(key,score,member).toString();


    }
    public void setExpireTime(String key,Integer seconds)
    {
        jedis.expire(key,seconds);

    }
    public String getKeyType(String key)
    {
        return jedis.type(key);

    }


    public  void returnResource() {
        if(jedis != null) {
            jedisPool.returnResource(jedis);
        }

    }
    public static void main(String[] args) throws Exception {
//        RedisUtil test = new RedisUtil();
//        System.out.println(test.getJedis());

    }


}
