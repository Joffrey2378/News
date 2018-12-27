package com.example.heorhii_dubinin.news;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NewsRepository {

    private NewsDao newsDao;
    private LiveData<List<NewsPiece>> allNews;

    NewsRepository(Application application) {
        NewsDatabase database = NewsDatabase.getInstance(application);
        newsDao = database.newsDao();
        allNews = newsDao.getAllNews();
    }

    public void insert(NewsPiece newsPiece) {
        new InsertNewsAsyncTask(newsDao).execute(newsPiece);
    }

//    public void update(NewsPiece news) {}
//    public void delete(NewsPiece news) {}

    public void deleteExtraNews() {
        new DeleteExtraNewsAsyncTask(newsDao).execute();
    }

    public LiveData<List<NewsPiece>> getAllNews() {
        return allNews;
    }

    private static class InsertNewsAsyncTask extends AsyncTask<NewsPiece, Void, Void> {
        private NewsDao newsDao;

        private InsertNewsAsyncTask(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(NewsPiece... newsPieces) {
            newsDao.insert(newsPieces[0]);
            return null;
        }
    }

    private static class DeleteExtraNewsAsyncTask extends AsyncTask<Void, Void, Void> {
        private NewsDao newsDao;

        private DeleteExtraNewsAsyncTask(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            newsDao.deleteExtraNews();
            return null;
        }
    }
}
