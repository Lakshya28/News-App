package com.example.roundedbutton.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.kwabenaberko.newsapilib.models.Article;


@Entity(tableName = "savedArticle")
public class SavedArticle {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private String sourceName;
    private String category;
    private String url;
    private String urlToImage;
    private String publishedAt;

    public SavedArticle(String title, String description, String sourceName, String category, String url, String urlToImage, String publishedAt) {
        this.title = title;
        this.description = description;
        this.sourceName = sourceName;
        this.category = category;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
