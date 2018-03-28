package com.deepak.lk;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import adapters.QuestionMenuAdapter;
import adapters.QuestionNumAdapter;

import static android.graphics.Color.*;


/**
 * Created by dsk on 12-Mar-18.
 */

public class TestActivity extends AppCompatActivity implements QuestionNumAdapter.ClickListener,QuestionSwipeAdapter.ClickListener,
        QuestionMenuAdapter.ClickListener,TestActivityDrawerFragment.TestActivityDrawerClosedListener{

    int previousPosition=0;
    QuestionFetch questionFetch;
    ViewPager viewPager;
    QuestionSwipeAdapter adapter;
    Resources resources;
    QuestionNumAdapter questionNumAdapter;

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

        questionFetch=new QuestionFetch();
        drawerFragment=(TestActivityDrawerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.test_navigation_drawer_fragment);
        drawerFragment.setUp((DrawerLayout) findViewById(R.id.test_drawer_layout),this.questionFetch);
        drawerFragment.setDrawerClosedListener(this);

        resources=getResources();
        final RecyclerView recyclerView=findViewById(R.id.question_number_recycler_view);
        questionNumAdapter=new QuestionNumAdapter(this);
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

            @SuppressLint("ResourceAsColor")
            @Override
            public void onPageSelected(int position) {


                if (position >=previousPosition) {
                    recyclerView.smoothScrollToPosition(position+1);
                } else {
                    if(position>0)
                    recyclerView.smoothScrollToPosition(position-1);
                }
                previousPosition = position;

                RecyclerView.ViewHolder viewHolder=recyclerView.findViewHolderForAdapterPosition(position);

                if(viewHolder!=null)
                      viewHolder.itemView.setBackgroundColor(R.color.lightGrey);
                else
                {
                    recyclerView.smoothScrollToPosition(position);

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
        switch (view.getId())
        {
            case R.id.question_option1_holder :
                if(currentIndex==0)
                {view.setBackgroundColor(getResources().getColor(R.color.blue));}
                else
                {view.setBackgroundColor(getResources().getColor(R.color.redDark));
                    solutionHolder.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.question_option2_holder:
                if(currentIndex==1)
                {view.setBackgroundColor(getResources().getColor(R.color.blue));
                }
                else
                {view.setBackgroundColor(getResources().getColor(R.color.redDark));
                    solutionHolder.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.question_option3_holder:
                if(currentIndex==2)
                {view.setBackgroundColor(getResources().getColor(R.color.blue));

                }
                else
                {view.setBackgroundColor(getResources().getColor(R.color.redDark));
                    solutionHolder.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.question_option4_holder:
                if(currentIndex==3)
                {
                    view.setBackgroundColor(getResources().getColor(R.color.blue));
                }
                else
                {view.setBackgroundColor(getResources().getColor(R.color.redDark));
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
    public void onDrawerClosed(int position) {
        viewPager.setCurrentItem(position);
    }
}
