package com.example.roundedbutton.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.roundedbutton.Adapter.NewsRecyclerViewAdapter;
import com.example.roundedbutton.Adapter.SwipeCardAdapter;
import com.example.roundedbutton.R;
import com.example.roundedbutton.Utils.Constants;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class BriefsFragment extends Fragment {

    FusedLocationProviderClient fusedLocationProviderClient;
    String locality = "";
    private RecyclerView recyclerView;
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    List<Article> articleList;
    List<Article> test = new ArrayList<>();
    private ViewPager2 viewPager2;
    SwipeCardAdapter swipeCardAdapter;
    SharedPreferences sharedPreferences;

    public BriefsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        articleList = new ArrayList<>();
        sharedPreferences = getContext().getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);
        locality = sharedPreferences.getString(Constants.LOCATION, "India");
        getAllArticles(locality);
        Log.d("Laklistsize", "size: " + articleList.size());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_briefs, container, false);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        viewPager2 = view.findViewById(R.id.viewPagerSlider);

        swipeCardAdapter = new SwipeCardAdapter(getContext(), articleList, viewPager2);
        viewPager2.setAdapter(swipeCardAdapter);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);

        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        return view;
    }

    private void getAllArticles(String locality) {
        Log.d("LakshyaLocationArticle", "Lakshya " + locality);
        NewsApiClient newsApiClient = new NewsApiClient(Constants.API_KEY);
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .language("en")
                        .q(locality)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse articleResponse) {
                        test = articleResponse.getArticles();
                        //articleList.addAll(test);
                        Log.d("LakLissttttttttSize", "Size: " + articleList.size());
                        swipeCardAdapter.addAll(test);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.d("LakError", throwable.getMessage());
                    }
                }
        );
    }

    /*@SuppressLint("MissingPermission")
    void getLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {
            Location location = task.getResult();
            if(location==null)Log.d("LakshyaLocationNuLL","yes");
            if (location != null) {
                try {
                    Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                    List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    locality = addressList.get(0).getLocality();
                    getAllArticles(locality);
                    Log.d("LakshyaLocationInside","Lakshya "+locality);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }*/


}