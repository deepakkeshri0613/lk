package adapters;

import android.content.Context;
import android.opengl.Visibility;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.deepak.lk.Question;
import com.deepak.lk.R;

import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by dsk on 12-Mar-18.
 */

public class QuestionSwipeAdapter extends PagerAdapter implements View.OnClickListener{
    List<Question> data= Collections.emptyList();
    private Context context;
    private LayoutInflater layoutInflater;
    int currentIndex=0;

    String style="<style>img{display: inline; height: auto; max-width: 100%;}</style>";

    ClickListener clickListener;
    String tag="QuestionSwipeAdapter";

    public QuestionSwipeAdapter(Context context,List<Question> data)
    {this.context=context;
        layoutInflater= LayoutInflater.from(context);
        this.data=data;
    }

    public void setOnClickListener(ClickListener clickListener)
    {
        this.clickListener=clickListener;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        Log.d(tag,"getItemPosition called");
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        Log.d(tag,"getCount Called");
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        Log.d(tag,"isViewFromObject called");
        return (view==object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        Log.d(tag,"instantiateItemCalled called"+position);
        View itemView =layoutInflater.inflate(R.layout.test_question_layout,container,false);
        TextView questionNumber=itemView.findViewById(R.id.question_number);
        WebView questionData=itemView.findViewById(R.id.question_data);
        questionNumber.setText("Q "+(position+1));
        Question question= data.get(position);
        questionData.loadData(style+question.getQuestion(),"text/html","UTF-8");

        int optionHolderid[]={R.id.question_option1_holder,R.id.question_option2_holder,
                R.id.question_option3_holder,R.id.question_option4_holder};
        int id[]={R.id.question_option1,R.id.question_option2,R.id.question_option3,R.id.question_option4};

        TextView negativeMarks=itemView.findViewById(R.id.negative_mark);
        negativeMarks.setText(question.getNegativeMarks());
        for(int i=0;i<4;i++)
        {
            LinearLayout optionHolder[] = new LinearLayout[4];
            optionHolder[i]=itemView.findViewById(optionHolderid[i]);
            TextView option=itemView.findViewById(id[i]);
            optionHolder[i].setOnClickListener(this);
            option.setText(question.getOptions()[i]);
        }
        View solutionHolder=itemView.findViewById(R.id.question_solution_holder);
        solutionHolder.setTag("solutionHolder"+position);
        TextView solution=itemView.findViewById(R.id.question_solution);
        solution.setText(question.getSolution());

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
        currentIndex=data.get(position).getCorrectOptionIndex();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ScrollView)object);
    }



    @Override
    public void onClick(View view) {
        if(clickListener!=null)
        {
            clickListener.optionItemClick(view,currentIndex);
        }



    }

    public interface ClickListener{
        void optionItemClick(View view,int currentIndex);
    }




}
























