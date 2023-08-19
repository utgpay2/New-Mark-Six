package com.xxl.job.executor.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionURL {
    public static String Connet(String address){
        HttpURLConnection conn = null;
        URL url = null;
        InputStream in = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder = null;
        try {
            url = new URL(address);
            conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setDoInput(true);
            conn.connect();
            in = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in));
            stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line+'\n');
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(conn!=null)conn.disconnect();;
            try {
                if(in!=null)in.close();
                if(reader!=null)reader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(stringBuilder!=null)return  stringBuilder.toString();
        return "";
    }
}
