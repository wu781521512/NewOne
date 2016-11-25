package com.example.mrwuchao.newone.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 网络请求的工具类
 */
public class HttpUtil {
    RequestQueue requestQueue = null;
    Context context;
    public HttpUtil(Context context){
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    /**
     *
     * @param path       网络请求的地址
     *
     */
    public void jsonRequest(String path, final Handler handler,final int back){
        //正确响应回调
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject object) {
                try {
                    Log.i("scro","json响应");
                    Log.i("scro","json" + object.getInt("result_code"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //实现响应的逻辑
                if (object == null) {
                    handler.sendEmptyMessage(HandlerBack.ERRORNET);
                }else{
                    Message message = Message.obtain();
                    message.what = back;
                    message.obj = object;
                    handler.sendMessage(message);
                }

            }
        };
        //错误响应回调
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //实现逻辑
                handler.sendEmptyMessage(HandlerBack.ERRORNET);
            }
        };
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,path,null,listener,errorListener);
        requestQueue.add(jsonObjectRequest);
    }
}
