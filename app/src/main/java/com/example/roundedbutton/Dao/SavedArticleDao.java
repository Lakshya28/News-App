package com.example.roundedbutton.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.roundedbutton.Entities.SavedArticle;

import java.util.List;

@Dao
public interface SavedArticleDao {

    @Query("SELECT * FROM savedArticle")
    List<SavedArticle> getAll();

    @Insert
    void insert(SavedArticle savedArticle);

    @Query("DELETE from savedArticle where url = :url")
    void delete(String url);

    @Query("SELECT count(*) from savedArticle where url = :url")
    int search(String url);

}
