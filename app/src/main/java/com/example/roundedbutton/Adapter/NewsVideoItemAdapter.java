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
import com.example.roundedbutton.Activity.LiveNewsDetailActivity;
import com.example.roundedbutton.NewsVideoItem;
import com.example.roundedbutton.R;

import java.util.List;

public class NewsVideoItemAdapter extends RecyclerView.Adapter<NewsVideoItemAdapter.viewholder> {

    List<NewsVideoItem> mNewsVideoItems;
    Context mContext;

    public NewsVideoItemAdapter(Context context, List<NewsVideoItem> newsVideoItems) {
        this.mContext = context;
        this.mNewsVideoItems = newsVideoItems;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.news_video_item, parent, false);
        return new NewsVideoItemAdapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        NewsVideoItem newsVideoItem = mNewsVideoItems.get(position);
        holder.setVideoItem(newsVideoItem);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, LiveNewsDetailActivity.class);
            intent.putExtra("newsVideoItem", newsVideoItem);
            mContext.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return mNewsVideoItems.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView channelIcon;
        TextView newsChannelName;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            newsChannelName = itemView.findViewById(R.id.newsChannelName);
            channelIcon = itemView.findViewById(R.id.channelIcon);
        }

        void setVideoItem(NewsVideoItem newsVideoItem) {
            Glide.with(mContext).load(newsVideoItem.getImageUrl()).into(channelIcon);
            newsChannelName.setText(newsVideoItem.getChannelName());
        }
    }


}
