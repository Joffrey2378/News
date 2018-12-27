package com.example.heorhii_dubinin.news;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    private NewsRepository repository;
    private LiveData<List<News>> allNews;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        repository = new NewsRepository(application);
        allNews = repository.getAllNews();
    }

    public void insert(News news) {
        repository.insert(news);
    }

    public void deleteExtraNews() {
        repository.deleteExtraNews();
    }

    public LiveData<List<News>> getAllNews() {
        return allNews;
    }
}