package com.example.heorhii_dubinin.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<News> brakingNews = QueryUtils.extractNews();

        ListView newsListView = findViewById(R.id.list);

        NewsAdapter adapter = new NewsAdapter(this, brakingNews);

        newsListView.setAdapter(adapter);
    }
}