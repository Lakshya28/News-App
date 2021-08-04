package com.example.roundedbutton.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roundedbutton.Adapter.InterestRecyclerViewAdapter;
import com.example.roundedbutton.Adapter.NewsVideoItemAdapter;
import com.example.roundedbutton.NewsVideoItem;
import com.example.roundedbutton.R;

import java.util.ArrayList;
import java.util.List;


public class LiveTvFragment extends Fragment {

    private RecyclerView recyclerView;
    List<NewsVideoItem> newsVideoItemList;
    NewsVideoItemAdapter newsVideoItemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsVideoItemList = new ArrayList<>();
        setupNewsVideoItemList();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_live_tv, container, false);

        recyclerView = v.findViewById(R.id.videoRecyclerView);
        newsVideoItemAdapter = new NewsVideoItemAdapter(getContext(), newsVideoItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(newsVideoItemAdapter);
        return v;
    }

    void setupNewsVideoItemList() {

        newsVideoItemList.add(new NewsVideoItem("India TV", "https://indiatvnews.com", "LZcMzgx8c0Y"));
        newsVideoItemList.add(new NewsVideoItem("NDTV India", "https://ndtv.com", "MN8p-Vrn6G0"));
        newsVideoItemList.add(new NewsVideoItem("Aaj Tak", "https://aajtak.in", "cnX9fQEq59A"));
        newsVideoItemList.add(new NewsVideoItem("ABP News", "https://news.abplive.com/", "odmHZVWb7ws"));
        newsVideoItemList.add(new NewsVideoItem("Republic World", "https://www.republicworld.com/", "4QDUnBTrphQ"));
        newsVideoItemList.add(new NewsVideoItem("India Today", "https://www.indiatoday.in/", "heFq-5rmUTY"));
        newsVideoItemList.add(new NewsVideoItem("DD News", "http://ddnews.gov.in/", "YTYL9VyMvC8"));
        newsVideoItemList.add(new NewsVideoItem("CNBC News", "https://www.cnbc.com", "Kxwrqig5UV4"));
        newsVideoItemList.add(new NewsVideoItem("News 18", "https://www.news18.com/", "GCIUMx73QlQ"));
        newsVideoItemList.add(new NewsVideoItem("DD National", "https://prasarbharati.gov.in", "oEu-PmkVBBE"));

    }

}