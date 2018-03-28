package com.deepak.lk;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class ChapterActivity extends AppCompatActivity {
    private Toolbar toolbar;
    NavigationDrawerFragment drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Chapter Activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_chapter);


        drawerFragment=(NavigationDrawerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.navigation_drawer_fragment);
        drawerFragment.setUp((DrawerLayout) findViewById(R.id.drawer_layout));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chapter_menu,menu);
        Drawable drawable=menu.getItem(0).getIcon();
        drawable.mutate();
        drawable.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home: NavUtils.navigateUpFromSameTask(this);
                finish();
                Toast.makeText(this," up button clicked",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.drawer_menu: if(drawerFragment.getmDrawerLayout().isDrawerOpen(Gravity.RIGHT))
                drawerFragment.getmDrawerLayout().closeDrawer(Gravity.END);
            else
                drawerFragment.getmDrawerLayout().openDrawer(Gravity.END);

        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
