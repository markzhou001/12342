package com.code.core.lib;

import com.code.core.util.ErrMsg;
import com.code.core.util.KafkaData;
import com.code.core.util.Log;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;


public class HttpClientManager {

    public static  String get_request_all(String url){
        HttpClient httpClient =HttpClientBuilder.create().build();

        HttpGet httpGet = null;
        try {
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();
            String encodeStr="args=";
            Integer start=url.indexOf(encodeStr);
            Integer end=url.indexOf("&",start);
            try
            {
                if(start==-1)//说明没有需要加密的
                {
                }
                else
                {
                    start=start+encodeStr.length();
                    if(end==-1)//说明args后面没内容
                    {
                        url=url.substring(0,start)+ URLEncoder.encode(url.substring(start),"UTF-8");
                    }
                    else
                    {
                        url=url.substring(0,start)+URLEncoder.encode(url.substring(start,end),"UTF-8")+url.substring(end);

                    }
                }

            }
            catch (Exception e)
            {

            }

            System.out.println(url);
            System.out.println("---------------");
            httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            String rContent=EntityUtils.toString(httpEntity,"utf-8");
            System.out.println("发送请求*****："+httpGet.getURI());
            System.out.println("请求返回主*****："+rContent);
            return rContent;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally{
            try {
                if(httpGet!=null){
                    httpGet.releaseConnection();
                }
                if(httpClient!=null){
                    ((CloseableHttpClient) httpClient).close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static  String get_request_uniqueword(String url, Map<String, String> params){
        HttpClient httpClient =HttpClientBuilder.create().build();

        HttpGet httpGet = null;
        try {
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();
            String ps = "";
            for (String pKey : params.keySet()) {
                if(!"Cookie".equalsIgnoreCase(pKey)){
                    if(!"".equals(ps)){
                        ps = ps + "&";
                    }
                    ps += pKey+"="+params.get(pKey);
                }
            }
            if(!"".equals(ps)){
                url = url + "?" + ps;
            }
            System.out.println(url);
            URL url_obj = new URL(url);
            URI uri = new URI(url_obj.getProtocol(), url_obj.getHost(), url_obj.getPath(), url_obj.getQuery(), null);

            httpGet = new HttpGet(uri);
            if(params.get("Cookie")!=null){
                httpGet.setHeader("Cookie","COFFEE_TOKEN="+params.get("Cookie"));
            }
            httpGet.setConfig(requestConfig);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            String rContent=EntityUtils.toString(httpEntity,"utf-8");
            System.out.println("发送请求*****："+httpGet.getURI());
            System.out.println("发送请求参数*****："+params.toString());
            System.out.println("docode参数*****："+ URLDecoder.decode(params.toString(), "UTF-8"));
            System.out.println("请求返回主*****："+rContent);
            return rContent;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        } finally{
            try {
                if(httpGet!=null){
                    httpGet.releaseConnection();
                }
                if(httpClient!=null){
                    ((CloseableHttpClient) httpClient).close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static  String get_request(String url, Map<String, String> params){
        //  CloseableHttpClient httpClient = null;
        HttpClient httpClient =HttpClientBuilder.create().build();

        HttpGet httpGet = null;
        try {
            //httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();
            String ps = "";
            for (String pKey : params.keySet()) {
                if(!"Cookie".equalsIgnoreCase(pKey)){
                    if(!"".equals(ps)){
                        ps = ps + "&";
                    }
                    ps += pKey+"="+params.get(pKey);
                }
            }
            if(!"".equals(ps)){
                url = url + "?" + ps;
            }
            System.out.println(url);
            httpGet = new HttpGet(url);
            if(params.get("Cookie")!=null){
                httpGet.setHeader("Cookie","COFFEE_TOKEN="+params.get("Cookie"));
            }
            httpGet.setConfig(requestConfig);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            String rContent=EntityUtils.toString(httpEntity,"utf-8");
            System.out.println("发送请求*****："+httpGet.getURI());
            System.out.println("发送请求参数*****："+params.toString());
            System.out.println("docode参数*****："+ URLDecoder.decode(params.toString(), "UTF-8"));
            System.out.println("请求返回主*****："+rContent);
            return rContent;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally{
            try {
                if(httpGet!=null){
                    httpGet.releaseConnection();
                }
                if(httpClient!=null){
                    ((CloseableHttpClient) httpClient).close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static  String get_request_withHead(String url, Map<String, String> params,Map<String,String> headMap){
        // CloseableHttpClient httpClient = null;
        HttpClient httpClient =HttpClientBuilder.create().build();

        HttpGet httpGet = null;
        try {
            //   httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();
            String ps = "";
            if(params!=null){
                for (String pKey : params.keySet()) {
                    if(!"Cookie".equalsIgnoreCase(pKey)){
                        if(!"".equals(ps)){
                            ps = ps + "&";
                        }
                        ps += pKey+"="+params.get(pKey);
                    }
                }
                if(params.get("Cookie")!=null){
                    httpGet.setHeader("Cookie","COFFEE_TOKEN="+params.get("Cookie"));
                }
            }

            if(!"".equals(ps)){
                url = url + "?" + ps;
            }
            httpGet = new HttpGet(url);

            if(headMap!=null){
                headMap.forEach(httpGet::setHeader);
               /* for(Map.Entry<String,String> entry:headMap.entrySet()){
                    httpGet.setHeader(entry.getKey(),entry.getValue());
                }*/
            }
            httpGet.setConfig(requestConfig);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
//            System.out.printf("response:\n%s\n",EntityUtils.toString(httpEntity,"utf-8"));
            // System.out.println(EntityUtils.toString(httpEntity,"utf-8"));
            return EntityUtils.toString(httpEntity,"utf-8");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally{
            try {
                if(httpGet!=null){
                    httpGet.releaseConnection();
                }
                if(httpClient!=null){
                    ((CloseableHttpClient) httpClient).close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String  httpPostWithContext(HttpPost httppost, List<NameValuePair> nameValuePairList, HttpClientContext context ) throws IOException {
        // 创建默认的httpClient实例.
        // DefaultHttpClient  client = new DefaultHttpClient();

        HttpClient client =HttpClientBuilder.create().build();

        UrlEncodedFormEntity uefEntity = null;
        try {
            uefEntity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httppost.setEntity(uefEntity);
        System.out.println("发送请求----------：" + httppost.getURI());
        System.out.println("请求参数--------：" +nameValuePairList.toString());
        HttpResponse response = null;
        try {
            response = client.execute(httppost,context);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String tmp;
        HttpEntity entity = response.getEntity();
        tmp=EntityUtils.toString(entity, "UTF-8");
        client.getConnectionManager().shutdown();
        System.out.println("请求返回结果--------：" +tmp);
        return tmp;

    }

    public static String  httpPostForm(String url,List<NameValuePair> nameValuePairList) throws IOException {
        // 创建默认的httpClient实例.
        // DefaultHttpClient  client = new DefaultHttpClient();

        HttpClient client =HttpClientBuilder.create().build();

        HttpPost httppost = new HttpPost(url);

        UrlEncodedFormEntity uefEntity = null;
        try {
            uefEntity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httppost.setEntity(uefEntity);
        System.out.println("发送请求----------：" + httppost.getURI());
        System.out.println("请求参数--------：" +nameValuePairList.toString());
        HttpResponse response = null;
        try {
            response = client.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String tmp;
        HttpEntity entity = response.getEntity();
        tmp=EntityUtils.toString(entity, "UTF-8");
        client.getConnectionManager().shutdown();
        System.out.println("请求返回结果--------：" +tmp);
        return tmp;

    }

    public static HttpResponse httpPostFormWithMoved (String url,List<NameValuePair> nameValuePairList) throws IOException {
        // 创建默认的httpClient实例.
        // DefaultHttpClient  client = new DefaultHttpClient();

        HttpClient client =HttpClientBuilder.create().build();

        HttpPost httppost = new HttpPost(url);

        UrlEncodedFormEntity uefEntity = null;
        try {
            uefEntity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httppost.setEntity(uefEntity);
        System.out.println("发送请求----------：" + httppost.getURI());
        System.out.println("请求参数--------：" +nameValuePairList.toString());
        HttpResponse response = null;
        HttpEntity entity;
        try {
            response = client.execute(httppost);
            httppost.abort();
            if (response.getStatusLine().getStatusCode() == 302) {
                String locationUrl = response.getLastHeader("Location").getValue();
                response = httpPostFormWithMoved(locationUrl, nameValuePairList);
                return response;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String tmp;
//        entity = response.getEntity();
//        tmp=EntityUtils.toString(entity, StandardCharsets.UTF_8);
//        client.getConnectionManager().shutdown();
//        System.out.println("请求返回结果--------：" +tmp);
        return response;
    }

    public static String  httpPostForm(String url,List<NameValuePair> nameValuePairList,String host,Integer port) throws IOException {
        // 创建默认的httpClient实例.
        // DefaultHttpClient  client = new DefaultHttpClient();

        HttpClient client =HttpClientBuilder.create().build();

        HttpPost httppost = new HttpPost(url);

        if(!host.equals(""))
        {
            if(port==0) port=80;

            HttpHost proxyHost = new HttpHost(host,port);
            RequestConfig reqConfig = RequestConfig.custom().setProxy(proxyHost).build();
            httppost.setConfig(reqConfig);
        }
        UrlEncodedFormEntity uefEntity = null;
        try {
            uefEntity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httppost.setEntity(uefEntity);
        System.out.println("========executing request " + httppost.getURI());
        HttpResponse response = null;
        try {
            response = client.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String tmp;
        HttpEntity entity = response.getEntity();
        tmp=EntityUtils.toString(entity, "UTF-8");
        client.getConnectionManager().shutdown();
        return tmp;

    }

    public static String  httpPostFormWithHeader(String url,List<NameValuePair> nameValuePairList,Map<String,String> headMap) throws IOException {

        HttpClient client =HttpClientBuilder.create().build();
        HttpPost httppost = new HttpPost(url);

        UrlEncodedFormEntity uefEntity = null;
        if(nameValuePairList!=null){
            try {
                uefEntity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        httppost.setEntity(uefEntity);
        if(headMap!=null){
            headMap.forEach(httppost::setHeader);
        }
        HttpResponse response = null;
        System.out.println("发送请求----------：" + httppost.getURI());
        System.out.println("请求参数--------：" +nameValuePairList.toString());
        System.out.println("header--------：" +headMap.toString());
        try {
            response = client.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String tmp;
        HttpEntity entity = response.getEntity();
        tmp=EntityUtils.toString(entity, "UTF-8");
        client.getConnectionManager().shutdown();
        return tmp;
    }

    public static String  httpPostFormWithHeaderAndMoved(String url,List<NameValuePair> nameValuePairList,Map<String,String> headMap) throws IOException {

        HttpClient client =HttpClientBuilder.create().build();
        HttpPost httppost = new HttpPost(url);

        UrlEncodedFormEntity uefEntity = null;
        if(nameValuePairList!=null){
            try {
                uefEntity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        httppost.setEntity(uefEntity);
        if(headMap!=null){
            headMap.forEach(httppost::setHeader);
        }
        HttpResponse response = null;
        HttpEntity entity;
        String tmp = "";
        try {
            response = client.execute(httppost);
            httppost.abort();
            if (response.getStatusLine().getStatusCode() == 302) {
                String locationUrl = response.getLastHeader("Location").getValue();
                tmp = httpPostFormWithHeaderAndMoved(locationUrl, nameValuePairList, headMap);
                return tmp;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        entity = response.getEntity();
        tmp=EntityUtils.toString(entity, "UTF-8");
        client.getConnectionManager().shutdown();
        return tmp;
    }


    public static String  httpPostFormByHttpPost(HttpPost httppost) throws IOException {

        HttpClient client =HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            response = client.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String tmp;
        HttpEntity entity = response.getEntity();
        tmp=EntityUtils.toString(entity, "UTF-8");
        client.getConnectionManager().shutdown();
        return tmp;
    }


    public static String doJsonPost(String url, String json) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        String result = null;
        CloseableHttpResponse httpResponse = null;
        CloseableHttpClient httpClient = null;
        System.out.println("========url " + url);
        System.out.println("========参数 " + json);
        try {
            httpClient = HttpClients.createDefault();
            StringEntity postingString = new StringEntity(json, Consts.UTF_8);
            postingString.setContentEncoding("UTF-8");
            postingString.setContentType("application/json");
            HttpPost httpPost = new HttpPost(url);
            // 为httpPost实例设置配置
            httpPost.setConfig(buildRequestConfig());
            httpPost.setEntity(postingString);
            // 3.执行post请求并返回结果
            httpResponse = httpClient.execute(httpPost);
            // 4.处理结果，这里将结果返回为字符串
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, Charset.forName("UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.data(new KafkaData(ErrMsg.NET_REQ_EX)).error("HttpClientException|doJsonPost|url:{},json:{}",url,json);
        }  finally {
            // 关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private static RequestConfig buildRequestConfig() {
        // 配置请求参数实例
        return RequestConfig.custom().setConnectTimeout(5000)// 设置连接主机服务超时时间
                .setConnectionRequestTimeout(5000)// 设置连接请求超时时间
                .setSocketTimeout(60000)// 设置读取数据连接超时时间
                .build();
    }

    public static  String  postJsonRequest(String url, String body,Map<String,String> headermap) throws IOException{
        HttpClient httpClient =HttpClientBuilder.create().build();
        HttpPost httpPost = null;
        String my_response="";

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();
        httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        try {
            httpPost.setEntity(new StringEntity(body,"UTF-8"));

            if(headermap!=null){
                headermap.forEach(httpPost::setHeader);
            }
            if(!headermap.keySet().contains("Content-Type"))
            {

                httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
            }



            HttpResponse response = httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode() ==200) {
                HttpEntity httpEntity = response.getEntity();
                my_response = EntityUtils.toString(httpEntity, "utf-8");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return my_response;
    }

    public static  String  patchJsonRequest(String url, String body,Map<String,String> headermap) throws IOException{
        HttpClient httpClient =HttpClientBuilder.create().build();
        String my_response="";

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();
        HttpPatch httpPatch = new HttpPatch(url);
        httpPatch.setConfig(requestConfig);
        try {
            httpPatch.setEntity(new StringEntity(body,"UTF-8"));

            if(headermap!=null){
                headermap.forEach(httpPatch::setHeader);
            }
            httpPatch.setHeader("Content-Type", "application/json; charset=UTF-8");
            HttpResponse response = httpClient.execute(httpPatch);
            HttpEntity httpEntity = response.getEntity();
            my_response=EntityUtils.toString(httpEntity,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return my_response;
    }

    public static  String  putJsonRequest(String url, String body,Map<String,String> headermap) throws IOException{
        // CloseableHttpClient httpClient = null;
        HttpClient httpClient =HttpClientBuilder.create().build();

        HttpPut httpPut = null;
        String my_response="";

        httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();
        httpPut = new HttpPut(url);
        httpPut.setConfig(requestConfig);
        try {
            httpPut.setEntity(new StringEntity(body,"UTF-8"));
            if(headermap!=null){

                headermap.forEach(httpPut::setHeader);
/*
                for(Map.Entry<String,String> mapEentry:headermap.entrySet()){
                    httpPut.setHeader(mapEentry.getKey(),mapEentry.getValue());
                }*/
            }
            httpPut.setHeader("Content-Type", "application/json; charset=UTF-8");
            HttpResponse response = httpClient.execute(httpPut);
            HttpEntity httpEntity = response.getEntity();
            my_response=EntityUtils.toString(httpEntity,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return my_response;
    }

    public static  String delete_request(String url, Map<String, String> params){
        // CloseableHttpClient httpClient = null;
        HttpClient httpClient =HttpClientBuilder.create().build();

        HttpDelete httpDelete = null;
        try {
            httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();
            String ps = "";
            for (String pKey : params.keySet()) {
                if(!"Cookie".equalsIgnoreCase(pKey)){
                    if(!"".equals(ps)){
                        ps = ps + "&";
                    }
                    ps += pKey+"="+params.get(pKey);
                }
            }
            if(!"".equals(ps)){
                url = url + "?" + ps;
            }
            httpDelete = new HttpDelete(url);
            if(params.get("Cookie")!=null){
                httpDelete.setHeader("Cookie","COFFEE_TOKEN="+params.get("Cookie"));
            }
            httpDelete.setConfig(requestConfig);
            HttpResponse response = httpClient.execute( httpDelete);
            HttpEntity httpEntity = response.getEntity();
            // System.out.println(EntityUtils.toString(httpEntity,"utf-8"));
            return EntityUtils.toString(httpEntity,"utf-8");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally{
            try {
                if(httpDelete!=null){
                    httpDelete.releaseConnection();
                }
                if(httpClient!=null){
                    ((CloseableHttpClient) httpClient).close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String  httpPatchFormWithHeader(String url,List<NameValuePair> nameValuePairList,Map<String,String> headMap) throws IOException {

        HttpClient client =HttpClientBuilder.create().build();
        HttpPatch httpPatch = new HttpPatch(url);

        UrlEncodedFormEntity uefEntity = null;
        if(nameValuePairList!=null){
            try {
                uefEntity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        httpPatch.setEntity(uefEntity);
        if(headMap!=null){
            headMap.forEach(httpPatch::setHeader);
        }
        HttpResponse response = null;
        try {
            response = client.execute(httpPatch);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String tmp;
        HttpEntity entity = response.getEntity();
        tmp=EntityUtils.toString(entity, "UTF-8");
        client.getConnectionManager().shutdown();
        return tmp;
    }

    public static String httpPostFormWithMovedReturnString(String url, List<NameValuePair> nameValuePairList) {

        HttpClient client = HttpClientBuilder.create().build();

        HttpPost httppost = new HttpPost(url);

        UrlEncodedFormEntity uefEntity = null;
        try {
            uefEntity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httppost.setEntity(uefEntity);
        System.out.println("发送请求----------：" + httppost.getURI());
        System.out.println("请求参数--------：" +nameValuePairList.toString());
        HttpResponse response = null;
        HttpEntity entity;
        try {
            response = client.execute(httppost);
            String result = "";
            if (response.getStatusLine().getStatusCode() == 302) {
                String locationUrl = response.getLastHeader("Location").getValue();
                result = httpPostFormWithMovedReturnString(locationUrl, nameValuePairList);
            } else if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getRequestLocation(String url){
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response;
        int moved = 302;

        HttpGet httpGet = null;
        try {
            httpClient = HttpClients.createDefault();
            httpGet = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000)
                    .setConnectTimeout(50000).setRedirectsEnabled(false).build();

            httpGet.setConfig(requestConfig);
            response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == moved) {
                return response.getLastHeader("Location").getValue();
            }
            return "";
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                if(httpGet!=null){
                    httpGet.releaseConnection();
                }
                if(httpClient!=null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * 发送 get 请求，并获取 cookie
     *
     * @param url 请求地址
     * @return 返回响应用于外部解析 set-cookie
     * @author jade.fan(樊诗呈)
     * @date 2021/2/18
     */
    public static HttpResponse getRequestCookies(String url){
        CloseableHttpClient httpClient = null;
        HttpResponse response = null;

        HttpGet httpGet = null;
        try {
            httpClient = HttpClients.createDefault();
            httpGet = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000)
                    .setConnectTimeout(50000).setRedirectsEnabled(false).build();
            httpGet.setConfig(requestConfig);
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                if(httpGet!=null){
                    httpGet.releaseConnection();
                }
                if(httpClient!=null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

}
