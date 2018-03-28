package com.deepak.lk.profile;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.deepak.lk.MainActivity;
import com.deepak.lk.R;
import com.deepak.lk.aboutus.AboutUsFragment;
import com.facebook.login.widget.ProfilePictureView;

/**
 * Created by dsk on 27-Jan-18.
 */

public class UserProfileFragment extends Fragment {
    SharedPreferences sharedPreferences;
    private ProfilePictureView profilePictureView;
    String id;
    TextView textView;
    public static UserProfileFragment createFor() {
        UserProfileFragment fragment = new UserProfileFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        id=sharedPreferences.getString("USER_ID",null);
        return inflater.inflate(R.layout.user_profile_fragmant, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        profilePictureView=view.findViewById(R.id.fb_profile_pic);
        profilePictureView.setProfileId(id);

        textView=view.findViewById(R.id.fb_user_name);
        textView.setText(sharedPreferences.getString("USER_NAME",null));

    }


}
