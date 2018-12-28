package com.example.heorhii_dubinin.news;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

class NewsRepository {

    private NewsDao newsDao;
    private LiveData<List<ArticleEntity>> allNews;

    NewsRepository(Application application) {
        NewsDatabase database = NewsDatabase.getInstance(application);
        newsDao = database.newsDao();
        allNews = newsDao.getAllNews();
    }

    void insert(ArticleEntity articleEntity) {
        new InsertNewsAsyncTask(newsDao).execute(articleEntity);
    }

    void deleteExtraNews() {
        new DeleteExtraNewsAsyncTask(newsDao).execute();
    }

    LiveData<List<ArticleEntity>> getAllNews() {
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
