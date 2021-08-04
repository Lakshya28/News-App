package com.example.roundedbutton.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
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


public class SourceFragment extends Fragment {

    private static final String TAG = "SourceFragment";
    View v;
    private RecyclerView recyclerView;
    InterestRecyclerViewAdapter recyclerViewAdapter;
    private List<UserChoiceClass> userChoiceList;
    private RequestQueue requestQueue;
    public String url = "https://newsapi.org/v2/sources?apiKey=7fed359922a045fc8b73231117cbf6f7";
    public String API_KEY = "7fed359922a045fc8b73231117cbf6f7";

    public SourceFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userChoiceList = new ArrayList<>();
        setUpUserChoiceList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_source, container, false);
        recyclerView = v.findViewById(R.id.interestRecyclerView);
        recyclerViewAdapter = new InterestRecyclerViewAdapter(getContext(), userChoiceList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }

    private void setUpUserChoiceList() {
        String json;
        try {
            InputStream is = getContext().getAssets().open("sources.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String id = object.get("id").toString();
                String title = object.get("name").toString();
                String imageurl = "https://logo.clearbit.com/" + object.get("url").toString();
                if (id.equals("the-times-of-india"))
                    imageurl = "https://images.squarespace-cdn.com/content/v1/57e37b4eebbd1ac57c4eacca/1557915219271-X0XCP9SGDK7Y2ARGRKLE/ke17ZwdGBToddI8pDm48kPfFKSUBdevgsXB8RmU2OdN7gQa3H78H3Y0txjaiv_0fDoOvxcdMmMKkDsyUqMSsMWxHk725yiiHCCLfrh8O1z5QHyNOqBUUEtDDsRWrJLTmHDGpy8WtDauOYtbFeKvOOosDZiZvrLzfXzqB3yAuiuDMbqQMmkc-MxwgUjkh5J1x/Final+TOI.png?format=500w";
                userChoiceList.add(new UserChoiceClass(id, title, imageurl, null, TAG));
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            Log.d("LakStack ", e.toString());
        }
        userChoiceList.add(new UserChoiceClass());
        userChoiceList.add(new UserChoiceClass());
    }

    public void loadSearch(String searchQuery) {
        List<UserChoiceClass> mUserList = userChoiceList;

        if (!searchQuery.isEmpty() && mUserList != null) {
            mUserList = utils.search(searchQuery, mUserList);
        }
        if (mUserList != null) recyclerViewAdapter.updateAdapter(mUserList);
    }
}


