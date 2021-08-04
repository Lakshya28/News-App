package com.example.roundedbutton;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsVideoItem implements Parcelable {
    private String channelName;
    private String description;
    private String youtubeUrl;
    private String websiteUrl;
    private String videoId;
    private String imageUrl;

    public NewsVideoItem() {
    }

    public NewsVideoItem(String name, String websiteUrl, String videoId) {
        this.channelName = name;
        this.websiteUrl = websiteUrl;
        this.videoId = videoId;
        this.imageUrl = "https://logo.clearbit.com/" + websiteUrl;
    }

    public NewsVideoItem(String name, String description, String youtubeUrl, String websiteUrl, String videoId) {
        this.channelName = name;
        this.description = description;
        this.youtubeUrl = youtubeUrl;
        this.websiteUrl = websiteUrl;
        this.videoId = videoId;
        this.imageUrl = "https://logo.clearbit.com/" + websiteUrl;
    }

    protected NewsVideoItem(Parcel in) {
        channelName = in.readString();
        description = in.readString();
        youtubeUrl = in.readString();
        websiteUrl = in.readString();
        videoId = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<NewsVideoItem> CREATOR = new Creator<NewsVideoItem>() {
        @Override
        public NewsVideoItem createFromParcel(Parcel in) {
            return new NewsVideoItem(in);
        }

        @Override
        public NewsVideoItem[] newArray(int size) {
            return new NewsVideoItem[size];
        }
    };

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(channelName);
        parcel.writeString(description);
        parcel.writeString(youtubeUrl);
        parcel.writeString(websiteUrl);
        parcel.writeString(videoId);
        parcel.writeString(imageUrl);
    }


}
