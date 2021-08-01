package com.example.roundedbutton.Utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.format.DateUtils;
import android.util.Log;

import com.example.roundedbutton.Activity.UserChoiceClass;
import com.example.roundedbutton.R;
import com.google.gson.Gson;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class utils {

    public static List<UserChoiceClass> search(String searchQuery, List<UserChoiceClass> list) {

        List<UserChoiceClass> searchResults = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if ((list.get(i).getTitle().trim().toLowerCase().contains(searchQuery.trim().toLowerCase()))) {
                searchResults.add(list.get(i));
            }
        }
        return searchResults;
    }

    public static String formatDate(String dateStringUTC) {
        // Parse the dateString into a Date object
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss'Z'");
        Date dateObject = null;
        try {
            dateObject = simpleDateFormat.parse(dateStringUTC);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Initialize a SimpleDateFormat instance and configure it to provide a more readable
        // representation according to the given format, but still in UTC
        SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy  h:mm a", Locale.ENGLISH);
        String formattedDateUTC = df.format(dateObject);
        // Convert UTC into Local time
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = df.parse(formattedDateUTC);
            df.setTimeZone(TimeZone.getDefault());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df.format(date);
    }

    /**
     * Get the formatted web publication date string in milliseconds
     *
     * @param formattedDate the formatted web publication date string
     * @return the formatted web publication date in milliseconds
     */
    private static long getDateInMillis(String formattedDate) {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("MMM d, yyyy  h:mm a");
        long dateInMillis;
        Date dateObject;
        try {
            dateObject = simpleDateFormat.parse(formattedDate);
            dateInMillis = dateObject.getTime();
            return dateInMillis;
        } catch (ParseException e) {
            Log.e("Problem parsing date", e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get the time difference between the current date and web publication date
     *
     * @param formattedDate the formatted web publication date string
     * @return time difference (i.e "9 hours ago")
     */
    public static CharSequence getTimeDifference(String formattedDate) {
        long currentTime = System.currentTimeMillis();
        long publicationTime = getDateInMillis(formattedDate);
        return DateUtils.getRelativeTimeSpanString(publicationTime, currentTime,
                DateUtils.SECOND_IN_MILLIS);
    }

    public static String setFirstCapital(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static List<String> getTopicColour(String topic, Context context) {
        List<String> list = new ArrayList<>();
        if (("indore").equals(topic)) {
            list.add(context.getResources().getString(0 + R.color.red100));
            list.add(context.getResources().getString(0 + R.color.red));
        } else if (("in").equals(topic)) {
            list.add(context.getResources().getString(0 + R.color.green100));
            list.add(context.getResources().getString(0 + R.color.green500));
        } else if (("general").equals(topic) || ("startup").equals(topic)) {
            list.add(context.getResources().getString(0 + R.color.blue100));
            list.add(context.getResources().getString(0 + R.color.blue500));
        } else if (("technology").equals(topic)) {
            list.add(context.getResources().getString(0 + R.color.yellow100));
            list.add(context.getResources().getString(0 + R.color.yellow500));
        } else if (("science").equals(topic) || ("travel").equals(topic)) {
            list.add(context.getResources().getString(0 + R.color.purple100));
            list.add(context.getResources().getString(0 + R.color.purple500));
        } else if (("sports").equals(topic)) {
            list.add(context.getResources().getString(0 + R.color.brown100));
            list.add(context.getResources().getString(0 + R.color.brown500));
        } else if (("entertainment").equals(topic) || ("food").equals(topic)) {
            list.add(context.getResources().getString(0 + R.color.orange100));
            list.add(context.getResources().getString(0 + R.color.orange500));
        } else if (("health").equals(topic)) {
            list.add(context.getResources().getString(0 + R.color.pink100));
            list.add(context.getResources().getString(0 + R.color.pink500));
        } else if (("business").equals(topic)) {
            list.add(context.getResources().getString(0 + R.color.teal100));
            list.add(context.getResources().getString(0 + R.color.teal500));
        }
        return list;
    }
}
