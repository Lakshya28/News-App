package com.example.roundedbutton.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.roundedbutton.Adapter.InterestViewPagerAdapter;
import com.example.roundedbutton.Fragment.LocationFragment;
import com.example.roundedbutton.Fragment.SourceFragment;
import com.example.roundedbutton.Fragment.TopicFragment;
import com.example.roundedbutton.Fragment.UserChoiceBaseFragment;
import com.example.roundedbutton.R;

import java.util.ArrayList;
import java.util.List;

public class UserChoiceActivity extends AppCompatActivity {

    ViewPager viewPager;
    InterestViewPagerAdapter viewPagerAdapter;
    private List<String> interestTitle = new ArrayList<>();
    TextView continueButton;
    EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_choice);

        interestTitle.add("Pick Your Interest");
        interestTitle.add("Pick Your Sources");
        interestTitle.add("Select your city for local news");

        Toolbar toolbar = findViewById(R.id.choicetoolbar);
        continueButton = findViewById(R.id.continueButton);
        toolbar.setTitle("Pick Your Interest");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        searchBar = findViewById(R.id.search_bar);

        viewPager = findViewById(R.id.intrestViewPager);
        viewPagerAdapter = new InterestViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new TopicFragment());
        viewPagerAdapter.addFragment(new SourceFragment());
        viewPagerAdapter.addFragment(new LocationFragment());


        viewPager.setAdapter(viewPagerAdapter);

        continueButton.setOnClickListener(view -> {
            if (viewPager.getCurrentItem() + 1 == 3) {
                Intent intent = new Intent(UserChoiceActivity.this, HomeActivity.class);
                startActivity(intent);
            } else {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                toolbar.setTitle(interestTitle.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                loadSearchResults(editable.toString());
            }
        });
    }

    private void loadSearchResults(String searchQuery) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.intrestViewPager + ":" + viewPager.getCurrentItem());

        if (fragment instanceof TopicFragment) {
            TopicFragment frg = (TopicFragment) fragment;
            frg.loadSearch(searchQuery);

        } else if (fragment instanceof SourceFragment) {
            SourceFragment frg = (SourceFragment) fragment;
            frg.loadSearch(searchQuery);
        } else if (fragment instanceof LocationFragment) {
            LocationFragment frg = (LocationFragment) fragment;
            frg.loadSearch(searchQuery);
        } else {
            Log.d("LakSearchempty", "Empty");
        }
    }
}