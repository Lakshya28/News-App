package com.example.roundedbutton.Adapter;

import android.content.Context;
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
import com.example.roundedbutton.R;
import com.example.roundedbutton.Utils.utils;
import com.kwabenaberko.newsapilib.models.Article;

import java.util.List;

import static com.example.roundedbutton.Utils.utils.getTopicColour;
import static com.example.roundedbutton.Utils.utils.setFirstCapital;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.viewholder> {


    List<Article> articleList;
    Context mContext;
    String topic = "";

    public NewsRecyclerViewAdapter(Context context, List<Article> articleList) {
        this.mContext = context;
        this.articleList = articleList;
    }

    public NewsRecyclerViewAdapter(Context context, List<Article> articleList, String topic) {
        this.mContext = context;
        this.articleList = articleList;
        this.topic = topic;
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
