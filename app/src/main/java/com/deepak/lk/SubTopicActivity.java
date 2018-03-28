package com.deepak.lk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapters.SubTopicAdapter;
import datastore.SubTopicDetail;

public class SubTopicActivity extends AppCompatActivity implements SubTopicAdapter.ClickListener {
    RecyclerView recyclerView;
    SubTopicAdapter myAdapter;

    List<SubTopicDetail> data=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("SubTopicActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_sub_topic);


        for(int i=0;i<15;++i)
        {
            SubTopicDetail detail=new SubTopicDetail();
            detail.setPageNumbers(4+i);
            detail.setChapterNumber(i+1);
            detail.setChapterTopicTitle("Number System And Reasoning");
            detail.setTimeInMin(25+(i*2));
            data.add(detail);
        }

        recyclerView=findViewById(R.id.recyclerView);
        myAdapter=new SubTopicAdapter(this,data);
        myAdapter.setOnClickListener(this);
        recyclerView.setAdapter(myAdapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    public void itemClick(View view, int position) {
        Intent intent=new Intent(this,ChapterActivity.class);
        startActivity(intent);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home: //NavUtils.navigateUpFromSameTask(this);
                //finish();

                onBackPressed();
                Toast.makeText(this," up button clicked",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
