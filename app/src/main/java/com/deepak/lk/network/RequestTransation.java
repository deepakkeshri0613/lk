package com.deepak.lk.network;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dsk on 24-Mar-18.
 */

public class RequestTransation {

    public NetworkResponseListener networkResponseListener;
    String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImFjNjlhODk4YTE2OTJkZDU0MWIxN2FlOWMyYmQ5OWQ0NzI2ZTQzYTU1NmEwMGE1ZmQ5N2U1OGI1YzQ0NGUxMmU1MWZmMWUwZGQ1ZjBkNTk3In0.eyJhdWQiOiIxIiwianRpIjoiYWM2OWE4OThhMTY5MmRkNTQxYjE3YWU5YzJiZDk5ZDQ3MjZlNDNhNTU2YTAwYTVmZDk3ZTU4YjVjNDQ0ZTEyZTUxZmYxZTBkZDVmMGQ1OTciLCJpYXQiOjE1MjI2OTQyMjQsIm5iZiI6MTUyMjY5NDIyNCwiZXhwIjoxNTU0MjMwMjI0LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.h5o7ksiFJWZHbn0uuXOJC8R9zj74pNyB8ejuTEXAKjQF5_Yf9pFK0DUcZpRiqj4wIVM8X_D-vNaugIU6xbfP21ubRG37xNaalOqWcufBNhy35RbC3sBtho93DOwn01QOPsy9mNryO0iTwtCWWA9G-GfFh3pWEJeT7QWgzqqK5kyflDm5QmaYXUp4Z4g644miRbAjp5W0VGFpxn938ywafyna1dgyWQNYEkZHEhx5O7UUMOKbNs4KbSh-eCBlhXo80Flhq91UnHkQ8atq4ZjJc3DjSoLcPdNUFWbvh9ew7NQTYHyASzm8pNCEiAR3kiZJAbw2w20wWLfcRQe2WLl9avUUX5NWz1KsxArwTIbv28nlYejvB-6Fv4Lk4Yeo77ZjaJibKpYiCQtD88hKFKQAYuPZOtubq0_t4KDIyelPRYLT02eW3QAZ8dhiz3kWSZEmfwHXkhsHBKBqYmiOw9JH8PREwVDbPftdjKpSpIV17UxfCwRoJO1Bb7mc-Vj-yotPKnzjUcKGJY10uc9wqgqjrKtB1nLib37Btb0MOECIVB8r3EJNHIsWCWpAB-o-VmDS6fksshtwPT4zG41f-ehyTC6vSwKHMIMLCxcdRshI5Rf9bcHYzOKYKQSPjwPgszXzcam5dqrjdJi3wrIvqICdZ3h25k8tbd5OJGMr8hsLrYE";
    StringRequest stringRequest;

    public StringRequest sendRequest(int requestType,String serverUrl)
    {


                    stringRequest= new StringRequest(Request.Method.GET, serverUrl,
                    new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray=new JSONArray(response);
                           // if(networkResponseListener!=null)
                            {
                                networkResponseListener.parseJsonObjectOnResponse(jsonArray);
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            networkResponseListener.parseJsonObjectOnResponse(new JSONArray());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                networkResponseListener.parseJsonObjectOnError(error);
            }
        }){
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String,String> headers = new HashMap<String, String>();
                            headers.put("Accept","application/json");
                            headers.put("Authorization",token);
                            return headers;
                        }

        };

        return stringRequest;
    }






    public interface NetworkResponseListener{
        void parseJsonObjectOnResponse(JSONArray jsonArray);
        void parseJsonObjectOnError(VolleyError error);

    }

    public void setNetworkResponseListener(NetworkResponseListener responseListener)
    {
        this.networkResponseListener=responseListener;
    }

}
