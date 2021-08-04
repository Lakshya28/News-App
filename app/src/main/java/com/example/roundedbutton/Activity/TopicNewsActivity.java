package com.example.roundedbutton.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.roundedbutton.Adapter.NewsRecyclerViewAdapter;
import com.example.roundedbutton.R;
import com.example.roundedbutton.Utils.Constants;
import com.example.roundedbutton.Utils.utils;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

import static com.example.roundedbutton.Utils.utils.setFirstCapital;

public class TopicNewsActivity extends AppCompatActivity {

    private static final String TAG = "TopicNewsActivity";
    String topic, type;
    List<Article> articleList;
    List<Article> test = new ArrayList<>();
    private RecyclerView recyclerView;
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    private int page = 1;
    private View mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_news);

        articleList = new ArrayList<>();
        topic = getIntent().getStringExtra("topic");
        type = getIntent().getStringExtra("type");

        Toolbar toolbar = findViewById(R.id.topicNewstoolbar);
        toolbar.setTitle(utils.setFirstCapital(topic));
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mLoadingIndicator = findViewById(R.id.loading_indicator);
        recyclerView = findViewById(R.id.topicNewsRecyclerView);
        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(this, articleList, topic);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(newsRecyclerViewAdapter);
        recyclerView.addOnScrollListener(OnScrollListener);

        APICall(page, type);
    }

    private void APICall(int page, String type) {
        Log.d("LakshyaPageNewsAPICall", "page :" + page);
        if (("category").equals(type)) {
            getAllArticleCategory(page, topic);
        } else if (("country").equals(type)) {
            getAllArticleCountry(page, topic);
        } else {
            getAllArticleQuery(page, topic);
        }

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
                Log.d("LakshyaPageTopicNews", "page :" + page);
                page++;
                APICall(page, type);
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

    public void getAllArticleCategory(int page, String category) {
        NewsApiClient newsApiClient = new NewsApiClient(Constants.API_KEY);
        newsApiClient.getTopHeadlines(new TopHeadlinesRequest.Builder().language("en")
                        .category(category)
                        .page(page)
                        .language("en")
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
                        Log.d(TAG + "LakErr", throwable.getMessage());
                    }
                }
        );
    }

    public void getAllArticleCountry(int page, String country) {
        NewsApiClient newsApiClient = new NewsApiClient(Constants.API_KEY);
        newsApiClient.getTopHeadlines(new TopHeadlinesRequest.Builder().language("en")
                        .country(country)
                        .page(page)
                        .language("en")
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
                        Log.d(TAG + "LakErr", throwable.getMessage());
                    }
                }
        );
    }

    public void getAllArticleQuery(int page, String query) {
        NewsApiClient newsApiClient = new NewsApiClient(Constants.API_KEY);
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .language("en")
                        .page(page)
                        .q(query)
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
                        Log.d(TAG + "LakErr", throwable.getMessage());
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}