package com.example.heorhii_dubinin.news.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.heorhii_dubinin.news.networking.ArticleStructure;
import com.example.heorhii_dubinin.news.networking.HttpRequestAsyncTask;
import com.example.heorhii_dubinin.news.persistence.ArticleEntity;
import com.example.heorhii_dubinin.news.persistence.NewsRepository;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    private static final String HTTPS_REQUEST_URL = "https://" +
            "newsapi.org/v2/top-headlines?country=ua&apiKey=eefa8f5b92b24ff7993231986bfa9a96";
    private NewsRepository repository;
    private LiveData<List<ArticleEntity>> allNews;
    private final NewsAdapter adapter = new NewsAdapter();
    private ArticleStructure articleStructure;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        repository = new NewsRepository(application);
        allNews = repository.getAllNews();
        httpRequest(adapter);
    }

    private void insert(ArticleEntity articleEntity) {
        repository.insert(articleEntity);
    }

    private void deleteExtraNews() {
        repository.deleteExtraNews();
    }

    LiveData<List<ArticleEntity>> getAllNews() {
        return allNews;
    }

    private void httpRequest(NewsAdapter adapter) {
        HttpRequestAsyncTask httpRequestAsyncTask = new HttpRequestAsyncTask(new HttpRequestAsyncTask
                .IResultListener() {
            @Override
            public void onResult(String result) {
                List<ArticleEntity> breakingNews = ArticleStructure.newsExtractFromJson(result);
                for (ArticleEntity article : breakingNews) {
                    insert(article);
                }
                deleteExtraNews();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
            }
        });
        httpRequestAsyncTask.execute(HTTPS_REQUEST_URL);
    }
}