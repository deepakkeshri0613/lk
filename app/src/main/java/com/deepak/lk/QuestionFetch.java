package com.deepak.lk;
import android.content.Context;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.deepak.lk.network.RequestTransation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapters.QuestionSwipeAdapter;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by dsk on 13-Mar-18.
 */

public class QuestionFetch implements RequestTransation.NetworkResponseListener{
    QuestionSwipeAdapter questionSwipeAdapter;
    List<Question> questionList=new ArrayList<>();
    Context context;

    UiLoadListener uiLoadListener;
    public List<Question> getQuestionList() {
        return questionList;
    }

 public void setUiLoadListener(UiLoadListener uiLoadListener)
 {
     this.uiLoadListener=uiLoadListener;
 }
    public QuestionFetch() {

        this.context=context;
        String serverUrl="http://dev.neolen.com/api/test/interface?test_id=1";
        RequestTransation requestTransation=new RequestTransation();
        StringRequest stringRequest=requestTransation.sendRequest(1,serverUrl);
        requestTransation.setNetworkResponseListener(this);
        SingletonRequest.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);






    }

    @Override
    public void parseJsonObjectOnResponse(JSONArray jsonArray) {
        try {

            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String question = jsonObject.getString("en_value");
                String option[] = new String[4];
                Question item = new Question();
                item.setQuestion(question);
                item.setCorrectOptionIndex(i % 4);
                item.setMarks(jsonObject.getString("marks"));
                item.setNegativeMarks(jsonObject.getString("neg_marks"));
                //item.setSolution(jsonObject.getString("en_sol"));
                item.setSolution("deepak is name set by mom" + i);
                item.setTypeOfQuestion(jsonObject.getString("type"));
                option[0]=jsonObject.getString("en_a");
                option[1]=jsonObject.getString("en_b");
                option[2]=jsonObject.getString("en_c");
                option[3]=jsonObject.getString("en_d");
                item.setOptions(option);
                questionList.add(item);


            }
            if(uiLoadListener!=null)
            {
                uiLoadListener.UiLoad();
            }
        }
        catch (JSONException e)
        {

        }
    }

    @Override
    public void parseJsonObjectOnError(VolleyError error) {

    }

    public interface UiLoadListener{
        void UiLoad();
    }
}

