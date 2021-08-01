package com.example.roundedbutton.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roundedbutton.Adapter.NewsRecyclerViewAdapter;
import com.example.roundedbutton.R;
import com.example.roundedbutton.Widgets.NewsTopicWidget;

public class TopStoriesFragment extends Fragment {
    NewsTopicWidget widgetLatestNews, widgetIndiaNews, widgetGeneralNews, widgetTechNews,
            widgetScienceNews, widgetSportsNews, widgetEntertainmentNews, widgetHealthNews, widgetBusinessNews;

    private NewsRecyclerViewAdapter latestNewsRVAdapter, indiaNewsRVAdapter,
            techNewsRVAdapter, scienceNewsRVAdapter, sportsNewsRVAdapter, entertainmentNewsRVAdapter, healthNewsRVAdapter;


    public TopStoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_stories, container, false);

        /*widgetLatestNews = view.findViewById(R.id.widgetLatestNews);
        widgetLatestNews.setText("Latest News");
        widgetLatestNews.setTypeandTopic("query","indore");
        widgetLatestNews.loadq("world");*/

        widgetIndiaNews = view.findViewById(R.id.widgetIndiaNews);
        widgetIndiaNews.setText("India");
        widgetIndiaNews.setTypeandTopic("country", "in");
        widgetIndiaNews.loadc("in");

        widgetGeneralNews = view.findViewById(R.id.widgetGeneralNews);
        widgetGeneralNews.setText("General");
        widgetGeneralNews.setTypeandTopic("category", "general");
        widgetGeneralNews.load("general");

        /*widgetTechNews = view.findViewById(R.id.widgetTechNews);
        widgetTechNews.setText("Tech");
        widgetTechNews.setTypeandTopic("category", "technology");
        widgetTechNews.load("technology");

        widgetScienceNews = view.findViewById(R.id.widgetScienceNews);
        widgetScienceNews.load("science");
        widgetScienceNews.setTypeandTopic("category","science");
        widgetScienceNews.setText("Science");

        widgetSportsNews = view.findViewById(R.id.widgetSportsNews);
        widgetSportsNews.setText("Sports");
        widgetSportsNews.setTypeandTopic("category","sports");
        widgetSportsNews.load("sports");

        widgetEntertainmentNews = view.findViewById(R.id.widgetEntertainmentNews);
        widgetEntertainmentNews.setText("Entertainment");
        widgetEntertainmentNews.setTypeandTopic("category","entertainment");
        widgetEntertainmentNews.load("entertainment");

        widgetHealthNews = view.findViewById(R.id.widgetHealthNews);
        widgetHealthNews.setText("Health");
        widgetHealthNews.setTypeandTopic("category","health");
        widgetHealthNews.load("health");

        widgetBusinessNews = view.findViewById(R.id.widgetBusinessNews);
        widgetBusinessNews.setText("Business");
        widgetBusinessNews.setTypeandTopic("category","business");
        widgetBusinessNews.load("business");
*/
        return view;
    }
}