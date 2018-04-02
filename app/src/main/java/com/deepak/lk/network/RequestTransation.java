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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dsk on 24-Mar-18.
 */

public class RequestTransation {

    public NetworkResponseListener networkResponseListener;
    String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjUyODc1ODE0MzkyNTNiNzllMzY4YzVhNmUwODc4MmI5NzEwNWMzYWMyNGUwMWMxNjQ1OGRmYzZiZjlmMzk2OWQ1MjY2ZTlmZDVlNGYwZWM1In0.eyJhdWQiOiIxIiwianRpIjoiNTI4NzU4MTQzOTI1M2I3OWUzNjhjNWE2ZTA4NzgyYjk3MTA1YzNhYzI0ZTAxYzE2NDU4ZGZjNmJmOWYzOTY5ZDUyNjZlOWZkNWU0ZjBlYzUiLCJpYXQiOjE1MTg5NTEyOTgsIm5iZiI6MTUxODk1MTI5OCwiZXhwIjoxNTUwNDg3Mjk4LCJzdWIiOiI1Iiwic2NvcGVzIjpbXX0.G6LHOiVHp_LD5liJeQsTNLLKwflUYWVyL6qjzXb9AGQVesDcBrfOPEAdARXFrwr_DrpTc5h0TQU3jYnOQFOxeSOm8d9Izq2GiZqmnC1-VfjMRvDvkiH4KRcLRzVgjIsb_w8g0U24tepXuizzR7yqbM3m1AbCm3Ik1gJiw0lX54by9LGssuUnS4o-58Jg1f3i-UkTruaHi62hScRP38-GSN4tzfO-F5wRPLDrvRU7h5KcgRGv_LWuhSme8zPu2Cs30FQ2ffQVzDGYURVGRpN0fL5qh8kdBPC5hcDAGX-yAJwALLEWCUB0KI4RhiTN1l3d8HAwNjqWsClarae0blnNxhqdF7Ov2CDKd-wm0R-JLQ9pa52eET-dpq6tqZ5owDU4W6qlC-ZUl0s0WUdAWjwyAX1ssIhAWSQryo7FlSzfK52fot0nQr-GolqfHC7b1LDAS13axAcUotIBGg79vV_isOy47Wiw8LZPcEOt_rr_1PhHyiJorc_vtQK1SGBfu76VDKta2jR3MX6XoLo0GMU6r-xam2LtLxLtj0NPHV5liAuqjcsYuzkBDKFtTIsFTebQNgTie1tu2_5TVGF4-2YhiVE7Xl1LAqXT6hoax8np55zxaqLsiQt3cVF1Xu8ZGSY5o69hNs3FWY8ePnM0PQZPM5IWHVqyDplGo2LJO2Caqm4";

    StringRequest stringRequest;

    public StringRequest sendRequest(int requestType,String serverUrl)
    {


                    stringRequest= new StringRequest(Request.Method.POST, serverUrl,
                    new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                           // if(networkResponseListener!=null)
                            {
                                networkResponseListener.parseJsonObjectOnResponse(jsonObject);
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            networkResponseListener.parseJsonObjectOnResponse(new JSONObject());
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
                            return headers;
                        }
                        @Override
                        protected Map<String, String> getParams() {

                            Map<String,String> parms=new HashMap<String, String>();
                            parms.put("email","sumit@neolen.com");
                            parms.put("password","Sumit@123");
                            return parms;
                        }
        };

        return stringRequest;
    }






    public interface NetworkResponseListener{
        void parseJsonObjectOnResponse(JSONObject jsonObject);
        void parseJsonObjectOnError(VolleyError error);

    }

    public void setNetworkResponseListener(NetworkResponseListener responseListener)
    {
        this.networkResponseListener=responseListener;
    }

}
