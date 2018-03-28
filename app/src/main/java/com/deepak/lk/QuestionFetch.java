package com.deepak.lk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dsk on 13-Mar-18.
 */

public class QuestionFetch {
    List<Question> questionList=new ArrayList<>();

    public List<Question> getQuestionList() {
        return questionList;
    }


    public QuestionFetch()
    {

        for(int i=0;i<15;++i)
        {

            String option[]=new String[4];
            Question item=new Question();
            item.setQuestion("What is my name"+i);
            item.setCorrectOptionIndex(i%4);
            item.setNegativeMarks("1/2");
            item.setSolution("deepak is name set by mom"+i);
            item.setTypeOfQuestion("four");
            for(int j=0;j<4;++j)
                option[j]="deepak"+(i+j);
            item.setOptions(option);
            questionList.add(item);

        }
    }




}

