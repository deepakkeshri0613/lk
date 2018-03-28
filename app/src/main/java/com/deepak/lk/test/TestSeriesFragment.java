package com.deepak.lk.test;

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

import com.deepak.lk.MyAdapter;
import com.deepak.lk.R;
import com.deepak.lk.TestActivity;
import com.deepak.lk.home.HomePageFragment;

/**
 * Created by dsk on 03-Mar-18.
 */

public class TestSeriesFragment extends Fragment implements MyAdapter.ClickListener {

    RecyclerView recyclerView;
    MyAdapter myAdapter;

    public static Fragment createFragment(){
        TestSeriesFragment fragment = new TestSeriesFragment();


        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.test_series_fragment, container, false);


    }

    //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        recyclerView=view.findViewById(R.id.recyclerView);
        myAdapter=new MyAdapter(getActivity());
        myAdapter.setOnClickListener(this);
        recyclerView.setAdapter(myAdapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

    }


    @Override
    public void ItemClick(View view, int position) {
        Toast.makeText(getActivity(),"item clicked"+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void testStartButtonClick(View view, int position) {
        Intent intent=new Intent(getContext(),TestActivity.class);
        startActivity(intent);
    }
}
