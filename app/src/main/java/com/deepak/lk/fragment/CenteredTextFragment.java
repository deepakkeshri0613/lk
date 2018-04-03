package com.deepak.lk.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
import com.deepak.lk.R;
import com.deepak.lk.SignupActivity;
import com.deepak.lk.SingletonRequest;
import com.deepak.lk.UserAnalysis;
import com.deepak.lk.network.RequestTransation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * Created by yarolegovich on 25.03.2017.
 */

public class CenteredTextFragment extends Fragment implements RequestTransation.NetworkResponseListener {

    final UserAnalysis userAnalysis=new UserAnalysis();
    private static final String EXTRA_TEXT = "text";
    String result="";
    String userMessage="";
    String dataMessage="";
    private ProgressBar progressBar;

    public static CenteredTextFragment createFor(String text) {
        CenteredTextFragment fragment = new CenteredTextFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_text, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final String text = getArguments().getString(EXTRA_TEXT);
        TextView textView = view.findViewById(R.id.text);
        String serverUrl="http://dev.neolen.com/api/test/interface?test_id=1";
        RequestTransation requestTransation=new RequestTransation();
        StringRequest stringRequest=requestTransation.sendRequest(1,serverUrl);
        requestTransation.setNetworkResponseListener(this);
        SingletonRequest.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        textView.setText("deepak");

    }

    @Override
    public void parseJsonObjectOnResponse(JSONArray jsonArray) {
        try {

            //JSONObject user_analysis=jsonObject.getJSONObject("token");
            for(int i=0;i<jsonArray.length();++i)
            {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                result=result+jsonObject.getString("en_value");
            }

            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),jsonArray.length()+"",Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"deep",Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void parseJsonObjectOnError(VolleyError error) {



        if (error instanceof TimeoutError){
            Toast.makeText(getApplicationContext(),"TimeoutError",Toast.LENGTH_LONG).show();

        }
        else if (error instanceof NoConnectionError)
        {
            Toast.makeText(getApplicationContext(),"NoConnectionError",Toast.LENGTH_LONG).show();
        }
        else if (error instanceof AuthFailureError) {
            Toast.makeText(getApplicationContext(),"AuthFailureError",Toast.LENGTH_LONG).show();
        }else if (error instanceof NetworkError) {
            Toast.makeText(getApplicationContext(),"NetworkError",Toast.LENGTH_LONG).show();
        } else if (error instanceof ServerError) {
            NetworkResponse networkResponse=error.networkResponse;
            String json;
            if(networkResponse!=null)
            {
                switch (networkResponse.statusCode)
                {
                    case 500:
                    case 422:
                    case 404:
                        json=new String(networkResponse.data);
                        if(json!=null)
                        {
                            Toast.makeText(getApplicationContext(),"ServerError",Toast.LENGTH_LONG).show();
                            try {
                                JSONObject jsonObject=new JSONObject(json);
                            } catch (JSONException e) {

                            }
                        }

                }
            }

        } else if (error instanceof ParseError) {
            Toast.makeText(getApplicationContext(),"ParseError",Toast.LENGTH_LONG).show();
        }



    }
}
