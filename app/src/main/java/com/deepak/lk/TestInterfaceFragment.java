package com.deepak.lk;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by dsk on 25-Mar-18.
 */

public class TestInterfaceFragment extends Fragment implements View.OnClickListener{

    static int position=0;
    static List<Question> data= Collections.emptyList();

    Question question;

    public static Fragment createFragment(List<Question> datas){
        data=datas;
        TestInterfaceFragment fragment = new TestInterfaceFragment();
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.test_question_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View itemView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(itemView, savedInstanceState);


        TextView questionNumber=itemView.findViewById(R.id.question_number);
        TextView questionData=itemView.findViewById(R.id.question_data);

        int questionNumberIndex=position+1;
        questionNumber.setText("Q "+questionNumberIndex);
        question= data.get(position);
        questionData.setText(question.getQuestion());

        int optionHolderid[]={R.id.question_option1_holder,R.id.question_option2_holder,
                R.id.question_option3_holder,R.id.question_option4_holder};
        int id[]={R.id.question_option1,R.id.question_option2,R.id.question_option3,R.id.question_option4};

        for(int i=0;i<4;i++)
        {
            LinearLayout optionHolder[] = new LinearLayout[4];
            optionHolder[i]=itemView.findViewById(optionHolderid[i]);
            TextView option=itemView.findViewById(id[i]);
            optionHolder[i].setOnClickListener(this);
            option.setText(question.getOptions()[i]);
        }

        //     View solutionHolder=itemView.findViewById(R.id.question_solution_holder);
        TextView solution=itemView.findViewById(R.id.question_solution);
        solution.setText(question.getSolution());

        itemView.findViewById(R.id.question_solution_holder).setVisibility(View.GONE);



    }

    @Override
    public void onClick(View view) {


        switch (view.getId())
        {



            case R.id.question_option1_holder :
                if(question.getCorrectOptionIndex()==0)
                {view.setBackgroundColor(getResources().getColor(R.color.blue));}
                else
                {view.setBackgroundColor(getResources().getColor(R.color.redDark));
                    view.getRootView().findViewById(R.id.question_solution_holder).setVisibility(View.VISIBLE);
                }
                break;
            case R.id.question_option2_holder:
                if(question.getCorrectOptionIndex()==1)
                {view.setBackgroundColor(getResources().getColor(R.color.blue));
                }
                else
                {view.setBackgroundColor(getResources().getColor(R.color.redDark));
                    view.getRootView().findViewById(R.id.question_solution_holder).setVisibility(View.VISIBLE);
                }
                break;
            case R.id.question_option3_holder:
                if(question.getCorrectOptionIndex()==2)
                {view.setBackgroundColor(getResources().getColor(R.color.blue));

                }
                else
                {view.setBackgroundColor(getResources().getColor(R.color.redDark));
                    view.getRootView().findViewById(R.id.question_solution_holder).setVisibility(View.VISIBLE);}
                break;
            case R.id.question_option4_holder:
                if(question.getCorrectOptionIndex()==3)
                {
                    view.setBackgroundColor(getResources().getColor(R.color.blue));}
                else
                {view.setBackgroundColor(getResources().getColor(R.color.redDark));
                    view.getRootView().findViewById(R.id.question_solution_holder).setVisibility(View.VISIBLE);}
                break;
        }
    }
}
