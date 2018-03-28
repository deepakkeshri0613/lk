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
        String serverUrl="https://www.neolen.com/api/android/students/dashboard";


        final String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjUyODc1ODE0MzkyNTNiNzllMzY4YzVhNmUwODc4MmI5NzEwNWMzYWMyNGUwMWMxNjQ1OGRmYzZiZjlmMzk2OWQ1MjY2ZTlmZDVlNGYwZWM1In0.eyJhdWQiOiIxIiwianRpIjoiNTI4NzU4MTQzOTI1M2I3OWUzNjhjNWE2ZTA4NzgyYjk3MTA1YzNhYzI0ZTAxYzE2NDU4ZGZjNmJmOWYzOTY5ZDUyNjZlOWZkNWU0ZjBlYzUiLCJpYXQiOjE1MTg5NTEyOTgsIm5iZiI6MTUxODk1MTI5OCwiZXhwIjoxNTUwNDg3Mjk4LCJzdWIiOiI1Iiwic2NvcGVzIjpbXX0.G6LHOiVHp_LD5liJeQsTNLLKwflUYWVyL6qjzXb9AGQVesDcBrfOPEAdARXFrwr_DrpTc5h0TQU3jYnOQFOxeSOm8d9Izq2GiZqmnC1-VfjMRvDvkiH4KRcLRzVgjIsb_w8g0U24tepXuizzR7yqbM3m1AbCm3Ik1gJiw0lX54by9LGssuUnS4o-58Jg1f3i-UkTruaHi62hScRP38-GSN4tzfO-F5wRPLDrvRU7h5KcgRGv_LWuhSme8zPu2Cs30FQ2ffQVzDGYURVGRpN0fL5qh8kdBPC5hcDAGX-yAJwALLEWCUB0KI4RhiTN1l3d8HAwNjqWsClarae0blnNxhqdF7Ov2CDKd-wm0R-JLQ9pa52eET-dpq6tqZ5owDU4W6qlC-ZUl0s0WUdAWjwyAX1ssIhAWSQryo7FlSzfK52fot0nQr-GolqfHC7b1LDAS13axAcUotIBGg79vV_isOy47Wiw8LZPcEOt_rr_1PhHyiJorc_vtQK1SGBfu76VDKta2jR3MX6XoLo0GMU6r-xam2LtLxLtj0NPHV5liAuqjcsYuzkBDKFtTIsFTebQNgTie1tu2_5TVGF4-2YhiVE7Xl1LAqXT6hoax8np55zxaqLsiQt3cVF1Xu8ZGSY5o69hNs3FWY8ePnM0PQZPM5IWHVqyDplGo2LJO2Caqm4";



        RequestTransation requestTransation=new RequestTransation();
        StringRequest stringRequest=requestTransation.sendRequest(1,serverUrl);
        requestTransation.setNetworkResponseListener(this);
        SingletonRequest.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        textView.setText("deepak");

    }

    @Override
    public void parseJsonObjectOnResponse(JSONObject jsonObject) {
        try {

            JSONObject user_analysis=jsonObject.getJSONObject("user_analysis");

            userAnalysis.setId(user_analysis.getInt("id"));
            userAnalysis.setEmailId(user_analysis.getString("email"));
            userAnalysis.setCourseId(user_analysis.getString("course_id"));
            userAnalysis.setScore(user_analysis.getInt("score"));
            userAnalysis.setQuestionAttempeted(user_analysis.getInt("ques_attempted"));
            userAnalysis.setSpeed(user_analysis.getInt("speed"));
            userAnalysis.setRank(user_analysis.getInt("rank"));
            userAnalysis.setTotalTest(user_analysis.getInt("no_tests"));
            userAnalysis.setOngoingTest(user_analysis.getInt("ongoing_tests"));
            userAnalysis.setAttemptedTest(user_analysis.getInt("attempted_tests"));
            userAnalysis.setTotalQuestion(user_analysis.getInt("no_que"));
            userAnalysis.setCorrectQuestion(user_analysis.getInt("correct_que"));
            userAnalysis.setWrongQuestion(user_analysis.getInt("wrong_ques"));
            userAnalysis.setAccuracy(user_analysis.getInt("accuracy"));
            userAnalysis.setProgress(user_analysis.getInt("progress"));
            result=userAnalysis.getCourseId()+"\n"+userAnalysis.getEmailId()+
                    "\n"+userAnalysis.getAccuracy()+
                    "\n"+userAnalysis.getCorrectQuestion()+"\n"+userAnalysis.getQuestionAttempeted();

            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"deepak",Toast.LENGTH_LONG).show();
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
