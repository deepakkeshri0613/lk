package com.deepak.lk;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by dsk on 05-Jan-18.
 */


public class SignupActivity extends AppCompatActivity {

    private String userName,courseId,confirmPassword;
    private String password;
    private String mobileNumber;
    private String email;
    private String serverResponceText;
    EditText userNameEditTextView,passwordEditTextView,mobileNumberEditTextView,emailEditTextView,courseIdEditTextView,confirmPasswordEditTextView;
    RequestQueue requestQueue;

    private String serverUrl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        serverUrl="http://dev.neolen.com/api/android/auth/register";
        userNameEditTextView=findViewById(R.id.sign_up_name);
        mobileNumberEditTextView=findViewById(R.id.sign_up_mob);
        passwordEditTextView=findViewById(R.id.sign_up_password);
        emailEditTextView=findViewById(R.id.sign_up_email);

        confirmPasswordEditTextView=findViewById(R.id.sign_up_confirm_password);
        courseIdEditTextView=findViewById(R.id.sign_up_course_id);






        userName="deepak";
        password="deepak123";
        confirmPassword="deepak123";
        courseId="p1";
        mobileNumber="8505996168";
        email="deepakkk@gmail.com";






    }


    public void loginTextClick(View view) {
        this.finish();
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);

    }





    public void signUp(View view) {

        userName=userNameEditTextView.getText().toString();
        password=passwordEditTextView.getText().toString();
        mobileNumber=mobileNumberEditTextView.getText().toString();
        email=emailEditTextView.getText().toString();
        confirmPassword=confirmPasswordEditTextView.getText().toString();
        courseId=courseIdEditTextView.getText().toString();
    /*    if(userName.equals("")||courseId.equals("")||password.equals("")||mobileNumber.equals("")||email.equals(""))
        {

        }
        else
        {
            if(!password.equals(confirmPassword))
            {
                //  show erroe
            }
        }

*/
        StringRequest stringRequest= new StringRequest(Request.Method.POST, serverUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            JSONObject jsonObject=jsonArray.getJSONObject(0);

                            String code=jsonObject.getString("code");
                            String message=jsonObject.getString("message");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        AlertDialog.Builder builder=new AlertDialog.Builder(SignupActivity.this);
                        builder.setMessage(response);
                        builder.setTitle("Server_Response_Success");


                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();

                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        AlertDialog alertDialog=builder.create();
                        alertDialog.show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder=new AlertDialog.Builder(SignupActivity.this);
                builder.setTitle("Error....");
                if (error instanceof TimeoutError){
                    builder.setMessage("TimeOut");
                }
                else if (error instanceof NoConnectionError)
                {
                    builder.setMessage("Please make Sure Internet Connection is Activie");
                }
                else if (error instanceof AuthFailureError) {

                    builder.setMessage("Authantication Failuer...");
                }else if (error instanceof NetworkError) {

                    builder.setMessage("NetworkError...");
                } else if (error instanceof ServerError) {


                    NetworkResponse networkResponse=error.networkResponse;
                    String json;
                    if(networkResponse!=null)
                    {
                        switch (networkResponse.statusCode)
                        {

                            case 500:
                            case 422:
                                json=new String(networkResponse.data);
                                if(json!=null)
                                {
                                    try {
                                        JSONObject jsonObject=new JSONObject(json);
                                        String message="";
                                        builder.setTitle(jsonObject.getString("message"));
                                        JSONObject errors=jsonObject.getJSONObject("errors");

                                        Iterator<?> keys=errors.keys();
                                        while(keys.hasNext())
                                        {
                                            String key=(String)keys.next();
                                            JSONArray array=errors.getJSONArray(key);
                                            for(int i=0;i<array.length();++i)
                                            {

                                                message=message+" -> "+array.getString(i)+"\n";
                                            }
                                        }
                                        builder.setMessage(message);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }

                        }
                    }

                   // builder.setMessage("Validation error");
                } else if (error instanceof ParseError) {
                    builder.setMessage("ParsserError");
                }





                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                });

                AlertDialog alertDialog=builder.create();
                alertDialog.show();


            }
        }){


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<String, String>();
                headers.put("Accept","application/json");
                headers.put("Authorization","null");
                return headers;
            }
            @Override
            protected Map<String, String> getParams() {

                Map<String,String> parms=new HashMap<String, String>();
                parms.put("name",userName);
                parms.put("lname","keshri");
                parms.put("number",mobileNumber);
                parms.put("email",email);
                parms.put("password",password);
                parms.put("password_confirmation",confirmPassword);
                return parms;
            }
        };


         SingletonRequest.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);



    }



}
