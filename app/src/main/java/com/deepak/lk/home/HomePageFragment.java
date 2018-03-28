package com.deepak.lk.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.deepak.lk.R;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dsk on 03-Jan-18.
 */


public class HomePageFragment extends Fragment {
    TextView textView;
    private static HomePageFragment mInstance;
    private static String name;
    private static String id;
    private static String url;
    private Context mContext;
    private LinearLayout linearLayout;






    public static Fragment createFragment(String name1, String id1, String url1){

        Fragment fragment=new HomePageFragment();
        name=name1;
        id=id1;
        url=url1;
        return fragment;
    }

    int[][] yData={{5,10,20},{5,10,20,25,6,8},{5,10,20,3},{5,10}};
    String[][] xData={{"Complete","Ongoing","Unseen"},{"Com","Ong","Uns","pi","alpha","keshri"},
            {"Complete","Ongoing","Unseen","seen"},{"Ongoing","Unseen"}};

    String label[]={"Test Analysis","Question Analysis","Accuracy Analysis","Progress Analysis"};



  /*  private void showFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.homePageRecyclerContainer, fragment)
                .commit();
    }*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_page_fragment, container, false);


    }

    //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    linearLayout=(LinearLayout)view.findViewById(R.id.homepage_innerLayout);
 /*   linearLayout.setDividerDrawable(getResources().getDrawable(R.drawable.divider));
    linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);*/



  /*  LinearLayout.LayoutParams cardparams=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 600);
    LinearLayout.LayoutParams params2=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 200);
        RelativeLayout.LayoutParams articalTitleparams=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams articalContentparams=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams dividerParams=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,4);
*/
  Context mContext=getActivity();

LayoutInflater inflater=(LayoutInflater)mContext.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

String info[]={"Score","Question Attempted","Speed","Rank"};
//int color[]=context.getResources().getDrawable().getIntArray(R.array.dashboard_img);

        int id[]={R.layout.card1,R.layout.card2,R.layout.dashboard_test_analysis_pie_chart
        ,R.layout.dashboard_question_analysis_chart,R.layout.dashboard_accuracy_chart,R.layout.dashboard_progress_chart};

        int pieChartId[]={R.id.piechart,R.id.question_analysis_piechart,R.id.accuracy_analysis_piechart,
                R.id.progress_analysis_piechart};

        for(int i=0;i<6;++i)
        {
            View v= inflater.inflate(id[i], linearLayout,false);
            if(i>=2) {
                PieChart pieChart = v.findViewById(pieChartId[i-2]);
                setPieChartData(pieChart, label[i-2], xData[i-2], yData[i-2]);
                pieChart.invalidate();
            }
            linearLayout.addView(v);
        }

        textView=view.findViewById(R.id.homepage_user_name);

        if(name==null)
            name="";
    textView.setText("Hello "+name+"\n");

    } //  onViewCreated close

    private void setPieChartData(PieChart pieChart,String label,String[] xData,int[] yData ) {


        Description description=new Description();
        description.setText("Value shown is in Percentages");
        pieChart.setUsePercentValues(true);
        pieChart.setDragDecelerationFrictionCoef(0.99f);
        pieChart.setDescription(description);
        pieChart.setExtraOffsets(10,10,10,10);

        List<PieEntry> pieEntryList=new ArrayList<>();
        for(int i=0;i<xData.length;++i){

            pieEntryList.add(new PieEntry(yData[i],xData[i]));
        }

        PieDataSet pieDataSet=new PieDataSet(pieEntryList,label);
        pieDataSet.setSliceSpace(1f);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
    }


    int getRandomColor(){
        Random random=new Random();
        return Color.argb(255,random.nextInt(256),random.nextInt(256),random.nextInt(256));
    }

}
