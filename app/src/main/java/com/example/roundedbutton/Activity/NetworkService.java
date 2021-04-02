package com.example.roundedbutton.Activity;

import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetworkService {

    public static String key = "7fed359922a045fc8b73231117cbf6f7";

    @GET("/sources?apiKey=" + key)
    Call<Map<String, String>> getAllSources();


}
