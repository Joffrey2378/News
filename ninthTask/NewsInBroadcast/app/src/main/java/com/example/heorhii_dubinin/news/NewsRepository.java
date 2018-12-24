package com.example.heorhii_dubinin.news;

import android.app.Application;

import java.util.ArrayList;

public class NewsRepository {

    private NewsDao newsDao;
    private /*LiveData<List<Note>>*/ ArrayList<News> allNews;

    NewsRepository(Application application) {
        NewsDatabase database = NewsDatabase.getInstance(application);
        newsDao = database.newsDao();
        newsDao.getAllNews(); //allNews = newsDao.getAllNews();
    }
}
