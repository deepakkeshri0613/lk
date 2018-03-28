package com.deepak.lk;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//Pol8WfFD268/O3SIimXg8l+LuuI=
/**
 * Created by dsk on 05-Jan-18.
 */

public class LoginActivity extends AppCompatActivity {
    public static final String EXTRA_NAME="USER_NAME";
    public static final String EXTRA_ID="USER_ID";
    public static final String EXTRA_URL="USER_URL";

    private String serverUrl,manualAccessToken;
    private String name,id,url,userEmail,userPassword;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private SignInButton mgooglesignInButton;
    private GoogleSignInOptions gso;
    private GoogleApiClient mGoogleApiClient;
    private int SIGN_IN = 30;


    GoogleSignInClient mGoogleSignInClient;


   LoginButton loginButton;
    EditText emailEditTextView,passwordEditTextView;
    TextView textView;
    String s;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        serverUrl="https://www.neolen.com/api/android/auth/login";
        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor=preferences.edit();
        FacebookSdk.getSdkVersion();
        setContentView(R.layout.login_activity);


        emailEditTextView=findViewById(R.id.email_mob);
        passwordEditTextView=findViewById(R.id.password);
        loginButton=(LoginButton)findViewById(R.id.fb_login_but);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));

        AccessToken accessToken=AccessToken.getCurrentAccessToken();

        if(accessToken!=null)
        {
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);



            name=preferences.getString("USER_NAME",null);
            id=preferences.getString("USER_ID",null);
            url=preferences.getString("USER_URL",null);


            intent.putExtra(EXTRA_NAME,name);
            intent.putExtra(EXTRA_ID,id);
            intent.putExtra(EXTRA_URL,url);
            startActivity(intent);
            finish();
        }


        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null)
        {
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            name=preferences.getString("USER_NAME",null);
            id=preferences.getString("USER_ID",null);
            url=preferences.getString("USER_URL",null);
            intent.putExtra(EXTRA_NAME,name);
            intent.putExtra(EXTRA_ID,id);
            intent.putExtra(EXTRA_URL,url);
            startActivity(intent);
            finish();
        }

        manualAccessToken=preferences.getString("MANUAL_TOKEN",null);

        if(manualAccessToken!=null) {

            Intent intent=new Intent(LoginActivity.this,MainActivity.class);

           name=manualAccessToken;

          //  name=preferences.getString("USER_NAME",manualAccessToken);
            id=preferences.getString("USER_ID",null);
            url=preferences.getString("USER_URL",null);

            intent.putExtra(EXTRA_NAME,name);
            intent.putExtra(EXTRA_ID,id);
            intent.putExtra(EXTRA_URL,url);
            startActivity(intent);
            finish();

        }


        callbackManager=CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {





                // Facebook Email address
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {


                                try {
                                     name = object.getString("name");

                                    String FEmail = object.getString("email");

                                    String Gender= object.getString(  "gender");
                                    String dob=object.getString("birthday");
                                    id=object.getString("id");
                                    url=object.getJSONObject("picture").getJSONObject("data").getString("url");


//                                    s=Name+"\n"+FEmail+"\n"+Gender+"\n"+dob+"\n"+id+"\n"+picture;

                                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);

                                    editor.putString("USER_NAME",name);
                                    editor.putString("USER_ID",id);
                                    editor.putString("USER_URL",url);
                                    editor.commit();
                                    intent.putExtra(EXTRA_NAME,name);
                                    intent.putExtra(EXTRA_ID,id);
                                    intent.putExtra(EXTRA_URL,url);
                                    startActivity(intent);
                                    finish();



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });



/*                Button button=(Button)findViewById(R.id.facebookloginButton);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LoginManager.getInstance().logInWithReadPermissions(getParent(), Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));
                    }
                });*/
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday,picture");
                request.setParameters(parameters);
                GraphRequestAsyncTask graphRequestAsyncTask = request.executeAsync();

            }

            @Override
            public void onCancel() {

                textView.setText("cancled");
            }

            @Override
            public void onError(FacebookException error) {

            }

        });


        //    google Authentication


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient=GoogleSignIn.getClient(this, gso);
        mgooglesignInButton=(SignInButton) findViewById(R.id.google_sign_in_button);
        mgooglesignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.google_sign_in_button:
                        ProgressDialog progressDialog=null;

                        progressDialog=new ProgressDialog(view.getContext());
                        progressDialog.setCancelable(true);
                        progressDialog.setMessage("Featching Data");
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        signIn();
                        progressDialog.dismiss();
                        break;
                }


            }
        });








    }// on create close


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private static final int RC_SIGN_IN = 9001;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        // fb callback manager
        callbackManager.onActivityResult(requestCode,resultCode,data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
           // Toast.makeText(this,account.getDisplayName()+account.getEmail()+account.getId(),Toast.LENGTH_LONG).show();

            name=account.getDisplayName();
            id=account.getId();
            url=account.getPhotoUrl().toString();


            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            editor.putString("USER_NAME",name);
            editor.putString("USER_ID",id);
            editor.putString("USER_URL",url);
            editor.commit();
            intent.putExtra(EXTRA_NAME,name);
            intent.putExtra(EXTRA_ID,id);
            intent.putExtra(EXTRA_URL,url);
            startActivity(intent);
            finish();



        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("google handle sign", "signInResult:failed code=" + e.getStatusCode());

        }
    }



    public void signupTextClick(View view) {
        this.finish();
        Intent intent=new Intent(this,SignupActivity.class);
        startActivity(intent);
    }

    public void onLoginClick(View view) {



        userEmail=emailEditTextView.getText().toString();
        userPassword=passwordEditTextView.getText().toString();


      /*  Intent intent=new Intent(LoginActivity.this,MainActivity.class);

        editor.putString("USER_NAME",name);
        editor.putString("USER_ID",id);
        editor.putString("USER_URL",url);
        editor.putString("MANUAL_TOKEN",manualAccessToken);
        editor.commit();
        intent.putExtra(EXTRA_NAME,name);
        intent.putExtra(EXTRA_ID,id);
        intent.putExtra(EXTRA_URL,url);
        startActivity(intent);
        finish();

        */






                StringRequest stringRequest= new StringRequest(Request.Method.POST, serverUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                  /*      Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        LoginActivity.this.finish();

                   */


                  String token="";


                        try {
                            JSONObject jsonObject=new JSONObject(response);
                             token=jsonObject.getString("token");

                             editor.putString("MANUAL_TOKEN",token);
                             editor.commit();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage(token);
                        builder.setTitle("Server_Response_Success");


                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
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
                AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
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
                return headers;
            }
            @Override
            protected Map<String, String> getParams() {

                Map<String,String> parms=new HashMap<String, String>();
                parms.put("email",userEmail);
                parms.put("password",userPassword);
                return parms;
            }
        };


        SingletonRequest.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);











    }
}
