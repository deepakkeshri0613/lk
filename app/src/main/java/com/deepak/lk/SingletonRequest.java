package com.deepak.lk;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by dsk on 03-Feb-18.
 */

public class SingletonRequest {


    private static SingletonRequest mInstance;
    private RequestQueue requestQueue;
    private static Context mContext;


    private SingletonRequest(Context context)
    {
        mContext=context;
        requestQueue=getRequestQueue();
    }

    public RequestQueue getRequestQueue()
    {
        if(requestQueue==null)
        {
            requestQueue= Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }


   public static synchronized SingletonRequest getInstance(Context context)
    {
        if(mInstance==null)
        {
            mInstance=new SingletonRequest(context);
        }
        return mInstance;
    }


    public<T> void addToRequestQueue(Request<T> request)
    {
        requestQueue.add(request);
    }

}
