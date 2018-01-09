package com.example.vera.coolweathers.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Vera on 2018/1/9/0009.
 */

public class HttpUtil {
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }


}
