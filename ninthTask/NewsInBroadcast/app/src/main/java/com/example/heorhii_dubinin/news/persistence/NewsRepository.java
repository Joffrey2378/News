package com.example.heorhii_dubinin.news.persistence;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NewsRepository {

    private NewsDao newsDao;
    private LiveData<List<ArticleEntity>> allNews;

    public NewsRepository(Application application) {
        NewsDatabase database = NewsDatabase.getInstance(application);
        newsDao = database.newsDao();
        allNews = newsDao.getAllNews();
    }

    public void insert(ArticleEntity articleEntity) {
        new InsertNewsAsyncTask(newsDao).execute(articleEntity);
    }

    public void deleteExtraNews() {
        new DeleteExtraNewsAsyncTask(newsDao).execute();
    }

    public LiveData<List<ArticleEntity>> getAllNews() {
        return allNews;
    }

    private static class InsertNewsAsyncTask extends AsyncTask<ArticleEntity, Void, Void> {
        private NewsDao newsDao;

        private InsertNewsAsyncTask(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(ArticleEntity... articleEntities) {
            newsDao.insert(articleEntities[0]);
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
