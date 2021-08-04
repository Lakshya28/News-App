package com.example.roundedbutton.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.roundedbutton.R;

import me.angrybyte.goose.Article;
import me.angrybyte.goose.Configuration;
import me.angrybyte.goose.ContentExtractor;

public class NewsViewActivity extends AppCompatActivity {

    String title, description, imageUrl, articleUrl, source, publishTime;
    TextView tvTitle, tvDescription, publisher;
    ImageView ivImage, ivWebView, ivShare, ivSave;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        description = intent.getStringExtra("description");
        imageUrl = intent.getStringExtra("imageUrl");
        articleUrl = intent.getStringExtra("articleUrl");
        source = intent.getStringExtra("source");
        publishTime = intent.getStringExtra("publishTime");


        tvTitle = findViewById(R.id.articleHeadline);
        tvDescription = findViewById(R.id.articleDescription);
        ivImage = findViewById(R.id.articleImage);
        ivSave = findViewById(R.id.articleSave);
        ivShare = findViewById(R.id.articleShare);
        ivWebView = findViewById(R.id.articleWebView);
        toolbar = findViewById(R.id.toolbar);
        publisher = findViewById(R.id.articlePublisher);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        description = description + "<br><br>" + "<b>" + "Click on WebView to view more from " + source + "</b>";
        tvTitle.setText(title);
        tvDescription.setText(Html.fromHtml(description));
        String published = source + " | " + publishTime;
        publisher.setText(published);
        Glide.with(ivImage.getContext()).load(imageUrl).into(ivImage);

        ivWebView.setOnClickListener(view -> {
            Intent intent1 = new Intent(NewsViewActivity.this, WebViewActivity.class);
            intent1.putExtra("articleUrl", articleUrl);
            startActivity(intent1);
        });

        ivShare.setOnClickListener(view -> {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, source + " - " + title + " : " + articleUrl);
            this.startActivity(Intent.createChooser(sharingIntent, this.getString(R.string.share_article)));
        });

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