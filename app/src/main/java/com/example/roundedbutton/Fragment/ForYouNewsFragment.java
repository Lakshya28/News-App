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
import com.example.roundedbutton.Adapter.NewsRecyclerViewAdapter;
import com.example.roundedbutton.R;
import com.example.roundedbutton.Utils.Constants;
import com.example.roundedbutton.Utils.utils;
import com.google.gson.Gson;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.Source;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.kwabenaberko.newsapilib.network.APIClient;

import java.util.ArrayList;
import java.util.List;


public class ForYouNewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    List<Article> articleList;
    List<Article> test = new ArrayList<>();
    String s;

    public ForYouNewsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        articleList = new ArrayList<>();
        getAllArticles();


        Log.d("LakArticlesize", " " + articleList.size() + s + " " + test.size());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_for_you_news, container, false);
        recyclerView = v.findViewById(R.id.forYouNewsRecyclerView);
        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(getContext(), articleList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(newsRecyclerViewAdapter);
        return v;
    }

    private void getAllArticles() {
        NewsApiClient newsApiClient = new NewsApiClient(Constants.API_KEY);
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .language("en")
                        .q("bitcoin")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse articleResponse) {
                        test = articleResponse.getArticles();
                        articleList.addAll(test);
                        newsRecyclerViewAdapter.addAll(test);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.d("LakError", throwable.getMessage());
                    }
                }
        );
    }
}