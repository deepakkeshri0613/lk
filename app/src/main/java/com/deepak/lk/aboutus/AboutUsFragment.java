package com.deepak.lk.aboutus;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.deepak.lk.R;


/**
 * Created by dsk on 16-Jan-18.
 */

public class AboutUsFragment extends Fragment {
    public static AboutUsFragment createFor() {
        AboutUsFragment fragment = new AboutUsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.about_page_fragment, container, false);
    }








}
