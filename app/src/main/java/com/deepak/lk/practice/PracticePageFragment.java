package com.deepak.lk.practice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.deepak.lk.R;
import com.deepak.lk.SubTopicActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import adapters.PracticePageAdapter;
import datastore.ModuleDetail;

/**
 * Created by dsk on 17-Mar-18.
 */

public class PracticePageFragment extends Fragment implements PracticePageAdapter.ClickListener {


    List<ModuleDetail> data=new ArrayList<>();
    String module[]={"General knowledge","Apptitude and Reasoning","Mathematics","English"};
    RecyclerView recyclerView;
    PracticePageAdapter myAdapter;
    public static Fragment createFragment(){
        PracticePageFragment fragment = new PracticePageFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_view_layout, container, false);
    }

    //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView=view.findViewById(R.id.recyclerView);



        for(int i=0;i<4;++i)
        {
            ModuleDetail moduleDetail=new ModuleDetail();
            moduleDetail.setModuleName(module[i]);
            moduleDetail.setOverView(getResources().getString(R.string.dummy_text));
            moduleDetail.setDueDate("25-3-2018");
            moduleDetail.setProgress(25+i);
            data.add(moduleDetail);
        }




        myAdapter=new PracticePageAdapter(getActivity(),data);
        myAdapter.setOnClickListener(this);
        recyclerView.setAdapter(myAdapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

    }


    @Override
    public void itemClick(View view, int position) {
        Intent intent=new Intent(getContext(), SubTopicActivity.class);
        startActivity(intent);
    }
}
