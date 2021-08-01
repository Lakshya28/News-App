package com.example.roundedbutton.Fragment;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roundedbutton.Activity.UserChoiceClass;
import com.example.roundedbutton.Adapter.InterestRecyclerViewAdapter;
import com.example.roundedbutton.R;
import com.example.roundedbutton.Utils.Constants;
import com.example.roundedbutton.Utils.utils;

import java.util.ArrayList;
import java.util.List;


public class TopicFragment extends Fragment {

    private static final String TAG = "TopicFragment";
    View v;
    private RecyclerView recyclerView;
    List<UserChoiceClass> userChoiceList;
    InterestRecyclerViewAdapter recyclerViewAdapter;

    public TopicFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userChoiceList = new ArrayList<>();
        setUpUserChoiceList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_topic, container, false);
        recyclerView = v.findViewById(R.id.interestRecyclerView);
        recyclerViewAdapter = new InterestRecyclerViewAdapter(getContext(), userChoiceList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }

    private void setUpUserChoiceList() {
        userChoiceList.add(new UserChoiceClass(Constants.Category.WORLD,
                Constants.Category.WORLD, "", getResources().getDrawable(R.drawable.ic_world), TAG));

        userChoiceList.add(new UserChoiceClass(Constants.Category.SCIENCE,
                Constants.Category.SCIENCE, "", getResources().getDrawable(R.drawable.ic_science), TAG));

        userChoiceList.add(new UserChoiceClass(Constants.Category.TECHNOLOGY,
                Constants.Category.TECHNOLOGY, "", getResources().getDrawable(R.drawable.ic_technology), TAG));

        userChoiceList.add(new UserChoiceClass(Constants.Category.SPORTS,
                Constants.Category.SPORTS, "", getResources().getDrawable(R.drawable.ic_sport), TAG));

        userChoiceList.add(new UserChoiceClass(Constants.Category.BUSINESS,
                Constants.Category.BUSINESS, "", getResources().getDrawable(R.drawable.ic_business), TAG));

        userChoiceList.add(new UserChoiceClass(Constants.Category.ENTERTAINMENT,
                Constants.Category.ENTERTAINMENT, "", getResources().getDrawable(R.drawable.ic_entertainment), TAG));

        userChoiceList.add(new UserChoiceClass(Constants.Category.HEALTH,
                Constants.Category.HEALTH, "", getResources().getDrawable(R.drawable.ic_add_icon), TAG));

        userChoiceList.add(new UserChoiceClass(Constants.CustomCategory.STARTUP,
                Constants.CustomCategory.STARTUP, "", getResources().getDrawable(R.drawable.ic_startup), TAG));

        userChoiceList.add(new UserChoiceClass(Constants.CustomCategory.TRAVEL,
                Constants.CustomCategory.TRAVEL, "", getResources().getDrawable(R.drawable.ic_travel), TAG));

        userChoiceList.add(new UserChoiceClass(Constants.CustomCategory.FOOD,
                Constants.CustomCategory.FOOD, "", getResources().getDrawable(R.drawable.ic_food), TAG));

        userChoiceList.add(new UserChoiceClass());
        userChoiceList.add(new UserChoiceClass());

    }

    public void loadSearch(String searchQuery) {
        List<UserChoiceClass> mUserList = userChoiceList;
        Log.d("LakSearch", searchQuery);
        if (!searchQuery.isEmpty() && mUserList != null) {
            mUserList = utils.search(searchQuery, mUserList);
        }
        Log.d("lakshya", "lakshyaerror");
        if (mUserList != null) recyclerViewAdapter.updateAdapter(mUserList);

    }
}