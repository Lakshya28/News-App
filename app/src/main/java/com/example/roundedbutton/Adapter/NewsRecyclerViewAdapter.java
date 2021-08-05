package com.example.roundedbutton.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.roundedbutton.Activity.NewsViewActivity;
import com.example.roundedbutton.Database.AppDatabase;
import com.example.roundedbutton.Entities.SavedArticle;
import com.example.roundedbutton.R;
import com.example.roundedbutton.Utils.utils;
import com.kwabenaberko.newsapilib.models.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.roundedbutton.Utils.utils.getTopicColour;
import static com.example.roundedbutton.Utils.utils.setFirstCapital;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.viewholder> {


    List<Article> articleList;
    Context mContext;
    //String topic = "";
    AppDatabase appDatabase;

    public NewsRecyclerViewAdapter(Context context, List<Article> articleList) {
        this.mContext = context;
        this.articleList = articleList;
    }

    public NewsRecyclerViewAdapter(Context context, List<Article> articleList, String topic) {
        this.mContext = context;
        this.articleList = articleList;
        //this.topic = topic;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.news_card, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRecyclerViewAdapter.viewholder holder, int position) {
        Article article = articleList.get(position);
        holder.articleHeadline.setText(article.getTitle());
        Glide.with(holder.articleImage.getContext()).load(article.getUrlToImage()).into(holder.articleImage);
        holder.articleSource.setText(article.getSource().getName());
        holder.articleTime.setText(utils.getTimeDifference(utils.formatDate(article.getPublishedAt())));
        //topic = article.getSource().getCategory();
        Log.d("LakCategory", "Cate: " + article.getSource().getName() + " " + article.getSource().getCategory());
        String topic = "";
        List<String> topics = new ArrayList<>();
        topics.add("in");
        topics.add("general");
        topics.add("technology");
        topics.add("science");
        topics.add("sports");
        topics.add("entertainment");
        topics.add("health");
        topics.add("business");
        Random random = new Random();
        if (topic.isEmpty()) topic = topics.get(random.nextInt(topics.size()));
        if (!topic.isEmpty()) {
            holder.articleTopic.setText(setFirstCapital(topic));
            List<String> list = getTopicColour(topic, mContext);
            Log.d("LakColour", list.toString());
            String s = mContext.getResources().getString(0 + R.color.blue100);
            Log.d("LakColourHex", "#" + s.substring(3));
            if (list != null) {
                holder.articleTopic.getBackground().setTint(Color.parseColor(list.get(0)));
                holder.articleTopic.setTextColor(Color.parseColor(list.get(1)));
            }
        }
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, NewsViewActivity.class);
            intent.putExtra("title", article.getTitle());
            intent.putExtra("description", article.getDescription());
            intent.putExtra("imageUrl", article.getUrlToImage());
            intent.putExtra("articleUrl", article.getUrl());
            intent.putExtra("source", article.getSource().getName());
            intent.putExtra("publishTime", utils.getTimeDifference(utils.formatDate(article.getPublishedAt())));
            mContext.startActivity(intent);
        });

        appDatabase = AppDatabase.getAppDatabase(mContext);

        if (appDatabase.savedArticleDao().search(article.getUrl()) > 0) {
            holder.articleSave.setImageDrawable(mContext.getDrawable(R.drawable.ic_save_with_border));
        } else {
            holder.articleSave.setImageDrawable(mContext.getDrawable(R.drawable.ic_save_without_border));
        }

        holder.articleSave.setOnClickListener(view -> {
            if (appDatabase.savedArticleDao().search(article.getUrl()) > 0) {
                holder.articleSave.setImageDrawable(mContext.getDrawable(R.drawable.ic_save_without_border));
                appDatabase.savedArticleDao().delete(article.getUrl());
            } else {
                holder.articleSave.setImageDrawable(mContext.getDrawable(R.drawable.ic_save_with_border));
                SavedArticle savedArticle = new SavedArticle(
                        article.getTitle(), article.getDescription(), article.getSource().getName(),
                        article.getSource().getCategory(), article.getUrl(), article.getUrlToImage(), article.getPublishedAt());
                appDatabase.savedArticleDao().insert(savedArticle);
            }
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


    public void addAll(List<Article> articleList1) {
        Log.d("LakAddAll", " " + mContext.toString() + " : " + articleList1.size());
        articleList.addAll(articleList1);
        notifyDataSetChanged();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView articleImage, articleSave;
        TextView articleHeadline, articleTime, articleTopic, articleSource;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            articleImage = itemView.findViewById(R.id.articleImage);
            articleSave = itemView.findViewById(R.id.articleSave);
            articleHeadline = itemView.findViewById(R.id.articleHeadline);
            articleTopic = itemView.findViewById(R.id.articleTopic);
            articleSource = itemView.findViewById(R.id.articleSource);
            articleTime = itemView.findViewById(R.id.articleTime);
        }
    }

}
