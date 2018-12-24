package com.example.heorhii_dubinin.news;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

public abstract class NewsDatabase extends RoomDatabase {

    public static NewsDatabase instance;

    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
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
//            newsDao.insert(new News());
            return null;
        }
    }
}
