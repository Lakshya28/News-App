package com.example.roundedbutton.Fragment;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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
import com.example.roundedbutton.Utils.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class LocationFragment extends Fragment {

    private static final String TAG = "LocationFragment";
    View v;
    private RecyclerView recyclerView;
    InterestRecyclerViewAdapter recyclerViewAdapter;
    private List<UserChoiceClass> userChoiceList;

    public LocationFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userChoiceList = new ArrayList<>();
        setUpUserChoiceList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_location, container, false);
        recyclerView = v.findViewById(R.id.interestRecyclerView);
        recyclerViewAdapter = new InterestRecyclerViewAdapter(getContext(), userChoiceList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }

    private void setUpUserChoiceList() {
        Log.d("LakLocLen ", " eee ");
        userChoiceList.add(new UserChoiceClass("India", "India", "", getDrawableImage("india"), TAG));

        String json;
        try {
            InputStream is = getContext().getAssets().open("cities.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String id = object.get("name").toString();
                String title = object.get("name").toString() + ", " + object.get("state").toString();
                String mDrawableName = object.get("state").toString().replaceAll("\\s", "").toLowerCase();

                userChoiceList.add(new UserChoiceClass(id, title, "", getDrawableImage(mDrawableName), TAG));
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            Log.d("LakStack ", e.toString());
        }
    }

    private Drawable getDrawableImage(String mDrawableName) {
        Resources res = getResources();
        int resID = res.getIdentifier(mDrawableName, "drawable", getContext().getPackageName());
        return res.getDrawable(resID);
    }

    public void loadSearch(String searchQuery) {
        List<UserChoiceClass> mUserList = userChoiceList;

        if (!searchQuery.isEmpty()) {
            mUserList = utils.search(searchQuery, mUserList);
        }
        recyclerViewAdapter.updateAdapter(mUserList);

    }
}