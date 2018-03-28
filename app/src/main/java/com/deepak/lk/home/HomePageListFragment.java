package com.deepak.lk.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.deepak.lk.R;

import java.util.List;

/**
 * Created by dsk on 02-Jan-18.
 */

public class HomePageListFragment extends Fragment {

    private RecyclerView mReportrecyclerView;

    public static HomePageListFragment createFragment() {

        HomePageListFragment fragment = new HomePageListFragment();

        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanced ) {

        View v=inflater.inflate(R.layout.recycler_view_layout,container,false);
        mReportrecyclerView=(RecyclerView) v.findViewById(R.id.recyclerView);
        mReportrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mReportrecyclerView.setAdapter(new ContentAdapter());
        return v;
    }


    private class ContentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mReportTitle;
        private TextView mDateTextView;
        private CheckBox mCheckBox;
        private CardView mCardView;

        public ContentViewHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            mCardView=itemView.findViewById(R.id.card_container);

          /*  mReportTitle=(TextView) itemView.findViewById(R.id.list_report_titel_text);
            mDateTextView=(TextView) itemView.findViewById(R.id.list_report_date_textview);
            mCheckBox=(CheckBox) itemView.findViewById(R.id.list_report_checkbox);*/

        }





        @Override
        public void onClick(View v)
        {

          /*  Intent intent=ReportPagerActivity.newIntent(getActivity(),mReport.getId());
            startActivity(intent);*/
        }
        private void bindReport(String report)
        {




        }
    }

    private class ContentAdapter extends RecyclerView.Adapter<ContentViewHolder> {
        private List<String> mReports;



        @Override
        public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
          View view = layoutInflater.inflate(R.layout.module_card_view, parent, false);
            return new ContentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ContentViewHolder holder, int position) {


        }

        @Override
        public int getItemCount() {
            return 5;

        }


    }
    } // class close
