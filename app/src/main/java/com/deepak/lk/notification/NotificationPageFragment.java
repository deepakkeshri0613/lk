package com.deepak.lk.notification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deepak.lk.R;

import adapters.ChatPageAdapter;

/**
 * Created by dsk on 22-Mar-18.
 */

public class NotificationPageFragment extends Fragment implements ChatPageAdapter.ClickListener {


    RecyclerView recyclerView;
    ChatPageAdapter myAdapter;

    public static Fragment createFragment(){
        NotificationPageFragment fragment = new NotificationPageFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.recycler_view_layout, container, false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView=view.findViewById(R.id.recyclerView);
        myAdapter=new ChatPageAdapter(getActivity());
        myAdapter.setOnClickListener(this);
        recyclerView.setAdapter(myAdapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

    }


    @Override
    public void itemClick(View view, int position) {

    }


}
