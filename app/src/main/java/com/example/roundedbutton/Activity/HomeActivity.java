package com.example.roundedbutton.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.roundedbutton.Adapter.InterestViewPagerAdapter;
import com.example.roundedbutton.Fragment.BriefsFragment;
import com.example.roundedbutton.Fragment.ForYouNewsFragment;
import com.example.roundedbutton.Fragment.LiveTvFragment;
import com.example.roundedbutton.Fragment.LocationFragment;
import com.example.roundedbutton.Fragment.SourceFragment;
import com.example.roundedbutton.Fragment.TopStoriesFragment;
import com.example.roundedbutton.Fragment.TopicFragment;
import com.example.roundedbutton.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TabLayout tabLayout;
    ViewPager viewPager;
    NavigationView navigationView;
    InterestViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setHomeAsUpIndicator(R.drawable.ic_toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        tabLayout = findViewById(R.id.home_sliding_tabs);
        viewPager = findViewById(R.id.homeviewpager);
        viewPager.setOffscreenPageLimit(3);

        tabLayout.addTab(tabLayout.newTab().setText("For You"));
        tabLayout.addTab(tabLayout.newTab().setText("Top Stories"));
        tabLayout.addTab(tabLayout.newTab().setText("Briefs"));
        tabLayout.addTab(tabLayout.newTab().setText("Live News"));

        //tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(Color.GRAY, Color.BLACK);

        int tabCount = tabLayout.getTabCount();
        int betweenSpace = 40;
        for (int i = 0; i < tabCount; i++) {
            View tabView = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            tabView.requestLayout();
            ViewCompat.setBackground(tabView, setImageButtonStateNew(this));
            //ViewCompat.setPaddingRelative(tabView, tabView.getPaddingStart(), tabView.getPaddingTop(), tabView.getPaddingEnd(), tabView.getPaddingEnd());
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) tabView.getLayoutParams();
            params.rightMargin = betweenSpace;
        }


        viewPagerAdapter = new InterestViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ForYouNewsFragment());
        viewPagerAdapter.addFragment(new TopStoriesFragment());
        viewPagerAdapter.addFragment(new BriefsFragment());
        viewPagerAdapter.addFragment(new LiveTvFragment());


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        String type = "query", topic = "bitcoin";
        //navigationView.setCheckedItem(id);

        if (id == R.id.nav_world) {

        } else if (id == R.id.nav_science) {
            type = "category";
            topic = "science";
        } else if (id == R.id.nav_technology) {
            type = "category";
            topic = "technology";

        } else if (id == R.id.nav_sports) {
            type = "category";
            topic = "sports";

        } else if (id == R.id.nav_entertainment) {
            type = "category";
            topic = "entertainment";

        } else if (id == R.id.nav_health) {
            type = "category";
            topic = "health";
        } else if (id == R.id.nav_startup) {
            type = "query";
            topic = "startup";
        } else if (id == R.id.nav_travel) {
            type = "query";
            topic = "travel";

        } else if (id == R.id.nav_food) {
            type = "query";
            topic = "food";
        } else if (id == R.id.nav_settings) {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_save_later) {
            type = "saved";
            topic = "saved";
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        Intent intent = new Intent(HomeActivity.this, TopicNewsActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("topic", topic);
        startActivity(intent);
        return true;

    }

    Drawable setImageButtonStateNew(Context mContext) {
        StateListDrawable states = new StateListDrawable();

        states.addState(new int[]{android.R.attr.state_selected}, ContextCompat.getDrawable(mContext, R.drawable.tab_bg_normal_white));
        states.addState(new int[]{-android.R.attr.state_selected}, ContextCompat.getDrawable(mContext, R.drawable.tab_bg_normal_white));
        return states;
    }


}