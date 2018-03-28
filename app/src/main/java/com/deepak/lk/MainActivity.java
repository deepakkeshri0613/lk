package com.deepak.lk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.deepak.lk.bottomNavBar.AHBottomNavigation;
import com.deepak.lk.bottomNavBar.AHBottomNavigationItem;
import com.deepak.lk.chats.ChatPageFragment;
import com.deepak.lk.fragment.CenteredTextFragment;
import com.deepak.lk.home.HomePageFragment;
import com.deepak.lk.home.HomePageListFragment;
import com.deepak.lk.notification.NotificationPageFragment;
import com.deepak.lk.practice.PracticePageFragment;
import com.deepak.lk.profile.UserProfileFragment;
import com.deepak.lk.slidingrootnav.SlidingRootNav;
import com.deepak.lk.slidingrootnav.SlidingRootNavBuilder;
import com.deepak.lk.menu.DrawerAdapter;
import com.deepak.lk.menu.DrawerItem;
import com.deepak.lk.menu.SimpleItem;
import com.deepak.lk.test.TestSeriesFragment;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener,GoogleApiClient.OnConnectionFailedListener{

    private static final int POS_HOME = 0;
    private static final int POS_COURSES = 1;
    private static final int POS_PLAN = 2;
    private static final int POS_BLOG = 3;
    private static final int POS_SETTING = 4;
    private static final int POS_LOGOUT = 5;
    String previousTag=null;

    private FrameLayout fragmentContainer;
    private AccessToken accessToken;


    private String[] screenTitles;
    private Drawable[] screenIcons;

    private String name;
    public  String id;
    private String url;
    private SlidingRootNav slidingRootNav;
    GoogleApiClient mGoogleApiClient;

    private Toolbar toolbar;
    private Window window;


    private Fragment[] fragment={HomePageFragment.createFragment("Deepak keshri","",""),
            PracticePageFragment.createFragment(), NotificationPageFragment.createFragment(), ChatPageFragment.createFragment(),
            CenteredTextFragment.createFor("deepak")};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        window=getWindow();
       mGoogleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();


        FacebookSdk.getSdkVersion();

        setContentView(R.layout.activity_main);


        fragmentContainer=findViewById(R.id.fragment_container);
          //fragmentContainer.setBackgroundColor(getResources().getColor(R.color.goodgreybackground));
/*
        Bundle bundle=getIntent().getExtras();
        name=bundle.getString("USER_NAME","");
        id=bundle.getString("USER_ID","");
        url=bundle.getString("USER_URL","");
        Toast.makeText(this,name,Toast.LENGTH_LONG).show();
*/
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();
        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_HOME).setChecked(true),
                createItemFor(POS_COURSES),
                createItemFor(POS_PLAN),
                createItemFor(POS_BLOG),
                createItemFor(POS_SETTING),
                createItemFor(POS_LOGOUT)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

         adapter.setSelected(POS_HOME);



        final AHBottomNavigation bottomNavigation = findViewById(R.id.bottom_navigation);
// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_home, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_practice, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_notification, R.color.colorPrimary);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_4, R.drawable.ic_chat, R.color.colorPrimary);
// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);

// Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
// Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(true);

// Enable the translation of the FloatingActionButton
        //bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);

// Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));

// Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);


        bottomNavigation.setTranslucentNavigationEnabled(true);

// Manage titles
       bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);


// Use colored navigation with circle reveal effect
        bottomNavigation.setColored(true);


// Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...


                if(position==0)
                {
                    showFragment(fragment[position],"HomePageFragment");
                    previousTag="HomePageFragment";
                }
                else if(position==1)
                {

                 //  fragmentContainer.setBackgroundColor(getResources().getColor(R.color.color_tab_2_backgrognd));


                    showFragment(fragment[position],"PracticePageFragment");
                    previousTag="PracticePageFragment";
                   // toolbar.setBackgroundColor(getResources().getColor(R.color.color_tab_2));
                 /*   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        window.setStatusBarColor(getResources().getColor(R.color.color_tab_2_dark));
                    }*/

                }
                else if(position==2)
                {
                    showFragment(fragment[position],"NotificationPageFragment");
                    previousTag="NotificationPageFragment";
                }
                else if(position==3)
                {
                    showFragment(fragment[position],"ChatPageFragment");
                    previousTag="ChatPageFragment";
                }
                return true;
            }
        });
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override public void onPositionChange(int y) {
                // Manage the new y position

            }
        });


    }  // onCreate method close


    @Override
    public void onStart()
    {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult)
    {

    }

    @Override
    public void onItemSelected(int position) {
        if (position == POS_LOGOUT) {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Really Want To Logout ?");
           // builder.setTitle("");
            builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    LoginManager.getInstance().logOut();
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {

                        }
                    });
                    SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("MANUAL_TOKEN",null);
                    editor.commit();
                    editor.clear();
                    Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }
        else if(position==POS_HOME)
        {


            showFragment(fragment[position],"HomePageFragment");
            previousTag="HomePageFragment";
        }
       else if(position==POS_COURSES)
        {


            slidingRootNav.closeMenu();
            Intent intent=new Intent(this,TestActivity.class);
            startActivity(intent);

          //  showFragment(CenteredTextFragment.createFor(screenTitles[position]),"CenterTextFragment");
        }
        else if(position==POS_PLAN)
        {
            showFragment(fragment[4],"CenterPageFragment");
            previousTag="CenterPageFragment";
        }
        else if(position==POS_BLOG)
        {
            //Fragment fragment=CenteredTextFragment.createFor(screenTitles[position]);
            //showFragment(fragment);
        }
        else if(position==POS_SETTING)
        {   //Fragment fragment=CenteredTextFragment.createFor(screenTitles[position]);
            //showFragment(fragment);
        }

        slidingRootNav.closeMenu();
    }






    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.textColorSecondary))
                .withTextTint(color(R.color.textColorPrimary))
                .withSelectedIconTint(color(R.color.colorAccent))
                .withSelectedTextTint(color(R.color.colorAccent));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }





    public void aboutUs(View view) {
        slidingRootNav.closeMenu();
      //  Fragment fragment= AboutUsFragment.createFor();
    //    showFragment(fragment,"AboutUsFragment");

    }

    private void showFragment(Fragment fragment,String tag) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        if(getSupportFragmentManager().findFragmentByTag(tag)==null)
        {
            transaction.add(R.id.fragment_container,fragment,tag);
        }
        if(previousTag!=null)
            transaction.hide(getSupportFragmentManager().findFragmentByTag(previousTag));
        transaction.show(fragment);
        transaction.commit();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
