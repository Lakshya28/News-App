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
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.roundedbutton.Activity.WebViewActivity;
import com.example.roundedbutton.R;
import com.example.roundedbutton.Utils.utils;
import com.kwabenaberko.newsapilib.models.Article;

import java.util.List;

public class SwipeCardAdapter extends RecyclerView.Adapter<SwipeCardAdapter.SwipeViewHolder> {

    private List<Article> articleList;
    private ViewPager2 viewPager2;
    Context mContext;

    public SwipeCardAdapter(Context context, List<Article> articleList, ViewPager2 viewPager2) {
        this.articleList = articleList;
        this.viewPager2 = viewPager2;
        this.mContext = context;
    }

    @NonNull
    @Override
    public SwipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.swipe_card_detail, parent, false);
        return new SwipeCardAdapter.SwipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SwipeViewHolder holder, int position) {
        holder.setArticle(articleList.get(position));

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
        Log.d("LakAddAllSwipe", " " + articleList1.size());
        articleList.addAll(articleList1);
        notifyDataSetChanged();
    }

    class SwipeViewHolder extends RecyclerView.ViewHolder {

        ImageView articleImage, articleSave, goLeft, goRight, articleWebView, articleShare;
        TextView articleHeadline, articleTopic, articlePublisher, articleDescription;

        public SwipeViewHolder(@NonNull View itemView) {
            super(itemView);
            articleImage = itemView.findViewById(R.id.articleImage);
            articleSave = itemView.findViewById(R.id.articleSave);
            articleWebView = itemView.findViewById(R.id.articleWebView);
            articleShare = itemView.findViewById(R.id.articleShare);
            articleHeadline = itemView.findViewById(R.id.articleHeadline);
            articleDescription = itemView.findViewById(R.id.articleDescription);
            articleTopic = itemView.findViewById(R.id.articleTopic);
            articlePublisher = itemView.findViewById(R.id.articlePublisher);
            goLeft = itemView.findViewById(R.id.goLeft);
            goRight = itemView.findViewById(R.id.goRight);
        }

        void setArticle(Article article) {
            articleHeadline.setText(article.getTitle());
            articleDescription.setText(article.getDescription());
            Glide.with(articleImage.getContext()).load(article.getUrlToImage()).into(articleImage);

            String published = article.getSource().getName() + " | " + utils.getTimeDifference(utils.formatDate(article.getPublishedAt()));
            articlePublisher.setText(published);

            goLeft.setOnClickListener(view -> {
                if (viewPager2.getCurrentItem() > 0) {
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
                }
            });

            goRight.setOnClickListener(view -> {
                if (viewPager2.getCurrentItem() < getItemCount() - 1) {
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                }
            });

            articleWebView.setOnClickListener(view -> {
                Intent intent1 = new Intent(mContext, WebViewActivity.class);
                intent1.putExtra("articleUrl", article.getUrl());
                mContext.startActivity(intent1);
            });

            articleShare.setOnClickListener(view -> {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, article.getSource().getName() + " - " + article.getTitle() + " : " + article.getUrl());
                mContext.startActivity(Intent.createChooser(sharingIntent, mContext.getString(R.string.share_article)));
            });
        }
    }
}

