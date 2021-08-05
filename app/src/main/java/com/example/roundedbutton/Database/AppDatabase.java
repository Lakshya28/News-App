package com.example.roundedbutton.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roundedbutton.Dao.SavedArticleDao;
import com.example.roundedbutton.Entities.SavedArticle;

@Database(entities = {SavedArticle.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract SavedArticleDao savedArticleDao();

    private static volatile AppDatabase appDatabaseInstance;

    public synchronized static AppDatabase getAppDatabase(final Context context) {
        if (appDatabaseInstance == null) {
            synchronized (AppDatabase.class) {
                if (appDatabaseInstance == null) {
                    appDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "appDatabase")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return appDatabaseInstance;
    }

}
