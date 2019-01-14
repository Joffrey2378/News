package com.example.heorhii_dubinin.news.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.heorhii_dubinin.news.persistence.ArticleEntity;
import com.example.heorhii_dubinin.news.persistence.NewsRepository;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    private NewsRepository repository;
    private LiveData<List<ArticleEntity>> allNews;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        repository = new NewsRepository(application);
        allNews = repository.getAllNews();
    }

    public void insert(ArticleEntity articleEntity) {
        repository.insert(articleEntity);
    }

    public void deleteExtraNews() {
        repository.deleteExtraNews();
    }

    LiveData<List<ArticleEntity>> getAllNews() {
        return allNews;
    }
}