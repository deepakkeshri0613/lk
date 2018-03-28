package com.deepak.lk;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import adapters.TopicMenuAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements TopicMenuAdapter.ClickListener{

    private View containerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private RecyclerView recyclerView;
    private TopicMenuAdapter adapter;
    public NavigationDrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        adapter=new TopicMenuAdapter(getActivity());
        adapter.setOnClickListener(this);
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

    public void setUp( DrawerLayout drawerLayout) {
        mDrawerLayout=drawerLayout;
        mDrawerToggle=new ActionBarDrawerToggle(getActivity(),drawerLayout,R.string.drawer_open
        ,R.string.drawer_close)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }


            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };

    }

    public DrawerLayout getmDrawerLayout()
    {
        return mDrawerLayout;
    }

    @Override
    public void itemClick(View view, int position) {
        Toast.makeText(getActivity(),"cllose",Toast.LENGTH_SHORT).show();
        getmDrawerLayout().closeDrawer(Gravity.END);
    }
}
