package com.code.testDemo;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestExtendDemo {

    @Test
    public  void test1(){
        Assert.assertEquals(2,3);
        System.out.println("11111111111");
    }


    @Test
    public  void test2(){
        Assert.assertEquals(2,2);
        System.out.println("22222");
    }


    @Test
    public  void test3(){
        Assert.assertEquals("aaaa","bbbbb");
        System.out.println("333333");
    }

    @Test
    public  void test4(){
        Reporter.log("未执行方法（exclude）");
        Assert.assertEquals("aaaa","bbbbb");
        System.out.println("4444444");
    }


    @Test
    public  void logDemo(){
        Reporter.log("这是失败的测试用例");
        System.out.println("555555555");
        throw new RuntimeException("这是错误异常");
    }

}
