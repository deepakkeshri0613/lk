package com.deepak.lk;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import adapters.QuestionMenuAdapter;
import adapters.QuestionNumAdapter;
import adapters.QuestionSwipeAdapter;


/**
 * Created by dsk on 12-Mar-18.
 */

public class TestActivity extends AppCompatActivity implements QuestionNumAdapter.ClickListener, QuestionSwipeAdapter.ClickListener,
        QuestionMenuAdapter.ClickListener,TestActivityDrawerFragment.TestActivityDrawerClosedListener,QuestionFetch.UiLoadListener{

    int currentPosition=0;
    int previousPosition=0;
    public QuestionFetch questionFetch;
    ViewPager viewPager;
    QuestionSwipeAdapter adapter;
    ProgressBar progressBar;
    QuestionNumAdapter questionNumAdapter;
    RecyclerView recyclerView;
    TestActivityDrawerFragment drawerFragment;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chapter_menu,menu);
        Drawable drawable=menu.getItem(0).getIcon();
        drawable.mutate();
        drawable.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        return true;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle("Test");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_question_interface);
        progressBar=findViewById(R.id.progress_bar);
        questionFetch=new QuestionFetch();

        questionFetch.setUiLoadListener(this);
    }

    @Override
    public void ItemClick(View view, int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home: onBackPressed();
            return true;
            case R.id.drawer_menu: if(drawerFragment.getmDrawerLayout().isDrawerOpen(Gravity.RIGHT))
                drawerFragment.getmDrawerLayout().closeDrawer(Gravity.END);
            else
                drawerFragment.getmDrawerLayout().openDrawer(Gravity.END);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    @Override
    public void optionItemClick(View view,int currentIndex) {
        View solutionHolder=viewPager.findViewWithTag("solutionHolder"+viewPager.getCurrentItem());
        Toast.makeText(this,""+currentIndex,Toast.LENGTH_SHORT).show();
        RecyclerView.ViewHolder questionNumViewHolder=recyclerView.findViewHolderForAdapterPosition(currentPosition);
        switch (view.getId())
        {
            case R.id.question_option1_holder :
                if(currentIndex==0)
                {view.setBackgroundColor(getResources().getColor(R.color.optionGreen));

                questionNumViewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.optionGreen));

                }
                else
                {view.setBackgroundColor(getResources().getColor(R.color.optionRed));
                    questionNumViewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.optionRed));
                    solutionHolder.setVisibility(View.VISIBLE);

                }
                break;
            case R.id.question_option2_holder:
                if(currentIndex==1)
                {view.setBackgroundColor(getResources().getColor(R.color.optionGreen));
                    questionNumViewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.optionGreen));

                }
                else
                {view.setBackgroundColor(getResources().getColor(R.color.optionRed));
                    questionNumViewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.optionRed));
                    solutionHolder.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.question_option3_holder:
                if(currentIndex==2)
                {view.setBackgroundColor(getResources().getColor(R.color.optionGreen));
                    questionNumViewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.optionGreen));
                }
                else
                {view.setBackgroundColor(getResources().getColor(R.color.optionRed));
                    questionNumViewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.optionRed));
                    solutionHolder.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.question_option4_holder:
                if(currentIndex==3)
                {
                    view.setBackgroundColor(getResources().getColor(R.color.optionGreen));
                    questionNumViewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.optionGreen));
                }
                else
                {view.setBackgroundColor(getResources().getColor(R.color.optionRed));
                    questionNumViewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.optionRed));
                    solutionHolder.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void menuItemClick(View view, int position) {
        drawerFragment.getmDrawerLayout().closeDrawer(Gravity.END);

    }

    @Override
    public void onDrawerClosed(View view,int position) {

        viewPager.setCurrentItem(position);
    }



    public void loadData()
    {
        progressBar.setVisibility(View.GONE);
        drawerFragment=(TestActivityDrawerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.test_navigation_drawer_fragment);
        drawerFragment.setUp((DrawerLayout) findViewById(R.id.test_drawer_layout),questionFetch.getQuestionList());
        drawerFragment.setDrawerClosedListener(this);
        recyclerView=findViewById(R.id.question_number_recycler_view);
        questionNumAdapter=new QuestionNumAdapter(this,questionFetch.getQuestionList().size());
        questionNumAdapter.setOnClickListener(this);
        recyclerView.setAdapter(questionNumAdapter);
        final RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        viewPager=findViewById(R.id.question_view_pager);
        adapter=new QuestionSwipeAdapter(this,questionFetch.getQuestionList());
        adapter.setOnClickListener(this);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


                if (position >=previousPosition) {
                    recyclerView.smoothScrollToPosition(position+1);
                } else {
                    if(position>0)
                        recyclerView.smoothScrollToPosition(position-1);
                }
                previousPosition = position;

                currentPosition=position;
                RecyclerView.ViewHolder viewHolder=recyclerView.findViewHolderForAdapterPosition(position);

                if(viewHolder!=null)
                    viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.goodgrey));
                else
                {
                    recyclerView.smoothScrollToPosition(position);
                    // viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.redDark));

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void UiLoad() {
        loadData();
    }
}
