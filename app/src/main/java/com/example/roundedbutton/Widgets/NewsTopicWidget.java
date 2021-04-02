package com.example.roundedbutton.Widgets;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roundedbutton.Activity.TopicNewsActivity;
import com.example.roundedbutton.Adapter.NewsRecyclerViewAdapter;
import com.example.roundedbutton.Adapter.SmallNewsCardAdapter;
import com.example.roundedbutton.R;
import com.example.roundedbutton.Utils.Constants;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

public class NewsTopicWidget extends FrameLayout {

    String topic, type;
    TextView tvTopic;
    ImageView ivMoreNews;
    RecyclerView recyclerView;
    List<Article> articleList;
    List<Article> test = new ArrayList<>();
    private SmallNewsCardAdapter newsRecyclerViewAdapter;


    public NewsTopicWidget(Context context) {
        this(context, null);
    }

    public NewsTopicWidget(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NewsTopicWidget(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.widget_news_topic, this, true);

        tvTopic = findViewById(R.id.tvNewsTopic);
        ivMoreNews = findViewById(R.id.ivMoreNews);
        recyclerView = findViewById(R.id.newsWidgetRecyclerView);
        articleList = new ArrayList<>();
        newsRecyclerViewAdapter = new SmallNewsCardAdapter(getContext(), articleList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(newsRecyclerViewAdapter);


        ivMoreNews.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), TopicNewsActivity.class);
            intent.putExtra("type", type);
            intent.putExtra("topic", topic);
            getContext().startActivity(intent);
        });

    }

    public void setTypeandTopic(String type, String topic) {
        this.type = type;
        this.topic = topic;
    }

    public void setText(String s) {
        tvTopic.setText(s);
    }

    public void load(String s) {
        NewsApiClient newsApiClient = new NewsApiClient(Constants.API_KEY);
        newsApiClient.getTopHeadlines(new TopHeadlinesRequest.Builder().language("en")
                        .category(s)
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

    public void loadc(String country) {
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

    public void loadq(String query) {
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


}
