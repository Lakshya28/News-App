package com.example.roundedbutton.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.example.roundedbutton.R;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    String articleUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent intent = getIntent();
        articleUrl = intent.getStringExtra("articleUrl");
        webView = findViewById(R.id.webview);

        Log.d("Lakshyaurl", articleUrl);
        if (articleUrl.charAt(4) != 's') articleUrl = articleUrl.replaceFirst("^http", "https");
        Log.d("Lakshyaurlnew", articleUrl);
        webView.loadUrl(articleUrl);


    }
}