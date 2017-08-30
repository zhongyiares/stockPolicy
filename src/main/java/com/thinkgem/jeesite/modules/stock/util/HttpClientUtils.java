package com.thinkgem.jeesite.modules.stock.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: KunLiang Cao
 * Email: caokunliang@sgrcr.com
 * Date: 2016/11/8
 */
public class HttpClientUtils {
    static CloseableHttpClient httpclient = HttpClients.createDefault();

    public static String doGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response1 = null;
        try {
            response1 = httpclient.execute(httpGet);
            int status = response1.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response1.getEntity();
                String s = entity != null ? EntityUtils.toString(entity) : null;
                return s;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String doPost(String url, Map<String,Object> map) throws Exception{
        CollectionUtils.removeEmptyKeyOfMap(map);

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (Map.Entry<String,Object> entry : map.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
        }

        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(100000)
                .setConnectTimeout(100000)
                .build();

        httpPost.setConfig(requestConfig);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        CloseableHttpResponse response2 = null;
        try {
            response2 = httpclient.execute(httpPost);

            int status = response2.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response2.getEntity();
                if (entity == null) {
                    return null;
                }
                String s = EntityUtils.toString(entity);
                return s;
            }
        } finally {
            if(response2 != null) {
                response2.close();
            }
        }
        return null;
    }


    //TODO  不使用类变量
    public static String doPostDiffHttp(String url, Map<String,Object> map) throws Exception{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CollectionUtils.removeEmptyKeyOfMap(map);

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (Map.Entry<String,Object> entry : map.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
        }

        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(10000)
                .setConnectTimeout(10000)
                .build();

        httpPost.setConfig(requestConfig);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        CloseableHttpResponse response2 = null;
        try {
            response2 = httpclient.execute(httpPost);

            int status = response2.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response2.getEntity();
                if (entity == null) {
                    return null;
                }
                String s = EntityUtils.toString(entity);
                return s;
            }
        } finally {
            if(response2 != null) {
                response2.close();
            }
        }
        return null;
    }
}
