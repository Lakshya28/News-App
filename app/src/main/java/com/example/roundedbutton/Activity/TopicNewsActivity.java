package com.example.roundedbutton.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

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

    String topic, type;
    List<Article> articleList;
    List<Article> test = new ArrayList<>();
    private RecyclerView recyclerView;
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;

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

        recyclerView = findViewById(R.id.topicNewsRecyclerView);
        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(this, articleList, topic);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(newsRecyclerViewAdapter);

        if (("category").equals(type)) {
            getAllArticleCategory(topic);
        } else if (("country").equals(type)) {
            getAllArticleCountry(topic);
        } else {
            getAllArticleQuery(topic);
        }

    }

    public void getAllArticleCategory(String category) {
        NewsApiClient newsApiClient = new NewsApiClient(Constants.API_KEY);
        newsApiClient.getTopHeadlines(new TopHeadlinesRequest.Builder().language("en")
                        .category(category)
                        .language("en")
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

    public void getAllArticleCountry(String country) {
        NewsApiClient newsApiClient = new NewsApiClient(Constants.API_KEY);
        newsApiClient.getTopHeadlines(new TopHeadlinesRequest.Builder().language("en")
                        .country(country)
                        .language("en")
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

    public void getAllArticleQuery(String query) {
        NewsApiClient newsApiClient = new NewsApiClient(Constants.API_KEY);
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .language("en")
                        .q(query)
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}