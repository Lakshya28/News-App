package com.example.roundedbutton.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    private int page = 1;
    private View mLoadingIndicator;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public ForYouNewsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        articleList = new ArrayList<>();
        getAllArticles(page);

        Log.d("LakArticlesize", " " + articleList.size() + s + " " + test.size());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_for_you_news, container, false);

        mSwipeRefreshLayout = v.findViewById(R.id.swipe_refresh);
        mLoadingIndicator = v.findViewById(R.id.loading_indicator);
        recyclerView = v.findViewById(R.id.forYouNewsRecyclerView);
        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(getContext(), articleList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(newsRecyclerViewAdapter);
        recyclerView.addOnScrollListener(OnScrollListener);

        mSwipeRefreshLayout.setOnRefreshListener(() -> new Handler().postDelayed(() -> {
            page = 1;
            newsRecyclerViewAdapter.clearAll();
            getAllArticles(page);
            mSwipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getActivity(), " Updated just now", Toast.LENGTH_SHORT).show();

        }, 1000));

        return v;
    }

    private RecyclerView.OnScrollListener OnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (isLastItemDisplaying(recyclerView)) {
                mLoadingIndicator.setVisibility(View.VISIBLE);
                page++;
                getAllArticles(page);
            }
        }
    };

    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisiblePosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            if (lastVisiblePosition != RecyclerView.NO_POSITION && lastVisiblePosition == recyclerView.getAdapter().getItemCount() - 1) {
                return true;
            }
        }
        return false;
    }

    private void getAllArticles(int page) {
        Log.d("LakPage", "Page : " + page);
        NewsApiClient newsApiClient = new NewsApiClient(Constants.API_KEY);
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .language("en")
                        .q("world")
                        .page(page)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse articleResponse) {
                        mLoadingIndicator.setVisibility(View.INVISIBLE);
                        test = articleResponse.getArticles();
                        //articleList.addAll(test);
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