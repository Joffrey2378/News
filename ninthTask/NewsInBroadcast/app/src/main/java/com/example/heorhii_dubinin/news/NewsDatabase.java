package com.example.heorhii_dubinin.news;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {News.class}, version = 1)
public abstract class NewsDatabase extends RoomDatabase {

    public static NewsDatabase instance;

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    static synchronized NewsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), NewsDatabase.class, "news_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    public abstract NewsDao newsDao();

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NewsDao newsDao;

        private PopulateDbAsyncTask(NewsDatabase db) {
            newsDao = db.newsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            newsDao.insert(new News(
                    null,
                    "This is a dummy item",
                    "Developer",
                    "Item that creates automatically at first launch",
                    "12/26/2018"));
            return null;
        }
    }
}
