package com.code.ordertools;

import com.alibaba.fastjson.JSONObject;
import com.code.baseapi.apiReModule;
import com.code.config.GlobalConfig;
import com.code.config.JDBCDTO;
import com.code.core.lib.HttpClientManager;
import com.code.core.lib.JdbcClient;
import com.code.core.tool.DataMap;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


import java.util.ArrayList;
import java.util.List;

public class Login {

    public apiReModule login(){
        apiReModule rev = new apiReModule();
        String url ="https://baidu.cn/index.php?_m=login";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("phone_no","10041000011"));
        params.add(new BasicNameValuePair("password","a123456"));

        try {
            String reValue = null;
            reValue = HttpClientManager.httpPostForm(url,params);
            JSONObject jsonObject = JSONObject.parseObject(reValue);
            rev.setRet(jsonObject.getInteger("ret"));
            rev.setMsg(jsonObject.getString("msg"));
            rev.setData(jsonObject.getString("data"));
            //取接口返回的token

        }catch (Exception e){
            e.printStackTrace();
            rev.setRet(1);
            rev.setMsg("发送请求失败！");
            rev.setData("");
        }

        return null;
    }

    public void sqlDemo(){
        JDBCDTO mydb = new GlobalConfig().getmy_db("stg");
        JdbcClient jdbc = new JdbcClient(mydb.getJdbcUrl(), mydb.getUser(),mydb.getPwd());
        try {
            List<DataMap> list = jdbc.fetchRows("select * from db where id =1");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        apiReModule rev = new apiReModule();
        rev = new Login().login();
        System.out.println(rev);
    }
}
