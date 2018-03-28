package com.deepak.lk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.libRG.CustomTextView;

import java.util.List;

/**
 * Created by dsk on 14-Mar-18.
 */

public class ViewPagerAdapter extends PagerAdapter {


    private  List<Question> data;
    private  Context context;
    private LayoutInflater layoutInflater;


    public ViewPagerAdapter(Context context)
    {
        this.context=context;
        layoutInflater= LayoutInflater.from(context);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==object);
    }
    @Override
    public int getCount() {
        return 15;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View itemView =layoutInflater.inflate(R.layout.question_number_view,container,false);

        CustomTextView customTextView=itemView.findViewById(R.id.question_number_view_id);
        customTextView.setText(""+(position+1));
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((HorizontalScrollView)object);
    }

}
