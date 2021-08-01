package com.example.roundedbutton.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.roundedbutton.Activity.WebViewActivity;
import com.example.roundedbutton.R;
import com.kwabenaberko.newsapilib.models.Article;

import java.util.List;

public class SmallNewsCardAdapter extends RecyclerView.Adapter<SmallNewsCardAdapter.viewholder> {


    List<Article> articleList;
    Context mContext;

    public SmallNewsCardAdapter(Context context, List<Article> articleList) {
        this.mContext = context;
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public SmallNewsCardAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.news_card_small, parent, false);
        return new SmallNewsCardAdapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SmallNewsCardAdapter.viewholder holder, int position) {
        Article article = articleList.get(position);
        holder.articleHeadline.setText(article.getTitle());
        Glide.with(holder.articleImage.getContext()).load(article.getUrlToImage()).into(holder.articleImage);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, WebViewActivity.class);
            intent.putExtra("articleUrl", article.getUrl());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public void clearAll() {
        articleList.clear();
        notifyDataSetChanged();
    }


    public void addAll(List<Article> articleList) {
        Log.d("LakAddAll", " " + articleList.size());
        articleList.addAll(articleList);
        notifyDataSetChanged();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView articleImage;
        TextView articleHeadline;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            articleImage = itemView.findViewById(R.id.smallArticleImage);
            articleHeadline = itemView.findViewById(R.id.smallArticleHeadline);
        }
    }


}
