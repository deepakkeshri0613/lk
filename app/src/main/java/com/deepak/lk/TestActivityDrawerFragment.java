package com.deepak.lk;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import adapters.QuestionMenuAdapter;
import adapters.QuestionNumAdapter;
import adapters.TopicMenuAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestActivityDrawerFragment extends Fragment implements QuestionMenuAdapter.ClickListener {
    private DrawerLayout mDrawerLayout;
    private RecyclerView recyclerView;
    TestActivityDrawerClosedListener testActivityDrawerClosedListener;
    private QuestionMenuAdapter adapter;
    private ActionBarDrawerToggle mDrawerToggle;
    QuestionFetch questionFetch;
    public TestActivityDrawerFragment() {
        // Required empty public constructor
    }

    public void setDrawerClosedListener(TestActivityDrawerClosedListener testActivityDrawerClosedListener)
    {
        this.testActivityDrawerClosedListener=testActivityDrawerClosedListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(questionFetch==null)
             questionFetch=new QuestionFetch();
        adapter=new QuestionMenuAdapter(getActivity(),questionFetch.getQuestionList());
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

    public void setUp( DrawerLayout drawerLayout,QuestionFetch questionFetch) {

        this.questionFetch=questionFetch;
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
    public void menuItemClick(View view, int position) {
        recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.question_number_view_id)
                .setBackgroundColor(getActivity().getResources().getColor(R.color.goodgrey));
        getmDrawerLayout().closeDrawer(Gravity.END);
        if(testActivityDrawerClosedListener!=null)
        {
            testActivityDrawerClosedListener.onDrawerClosed(view,position);
        }

    }

    public interface TestActivityDrawerClosedListener
    {
        void onDrawerClosed(View view,int position);
    }
}
