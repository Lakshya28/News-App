package com.example.roundedbutton.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.roundedbutton.Adapter.LoginAdapter;
import com.example.roundedbutton.R;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Button submitButton, agreeButton;
    TextView skipButton, termsAndCondition;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        submitButton = findViewById(R.id.submitButton);
        skipButton = findViewById(R.id.skipButton);
        agreeButton = findViewById(R.id.agreeButton);
        termsAndCondition = findViewById(R.id.tvtermsandcondition);

        dialog = new Dialog(LoginActivity.this);
        dialog.setContentView(R.layout.termsandconditiondialog);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.tab_bg_normal_white));
        }
        agreeButton = dialog.findViewById(R.id.agreeButton);

        termsAndCondition.setOnClickListener(view -> dialog.show());
        agreeButton.setOnClickListener(view -> dialog.dismiss());

        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Signup"));
        //tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(Color.WHITE, Color.BLACK);

        int tabCount = tabLayout.getTabCount();

        for (int i = 0; i < tabCount; i++) {
            View tabView = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            tabView.requestLayout();
            ViewCompat.setBackground(tabView, setImageButtonStateNew(this));
            //ViewCompat.setPaddingRelative(tabView, tabView.getPaddingStart(), tabView.getPaddingTop(), tabView.getPaddingEnd(), tabView.getPaddingEnd());
        }

        //tabLayout.setupWithViewPager(viewPager);

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


        final LoginAdapter loginAdapter = new LoginAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(loginAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, UserChoiceActivity.class);
                startActivity(intent);
            }
        });

    }

    Drawable setImageButtonStateNew(Context mContext) {
        StateListDrawable states = new StateListDrawable();

        states.addState(new int[]{android.R.attr.state_selected}, ContextCompat.getDrawable(mContext, R.drawable.tab_bg_normal_white));
        states.addState(new int[]{-android.R.attr.state_selected}, ContextCompat.getDrawable(mContext, R.drawable.tab_bg_normal));
        return states;
    }
}