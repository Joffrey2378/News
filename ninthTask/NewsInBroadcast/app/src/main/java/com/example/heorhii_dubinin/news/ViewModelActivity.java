package com.example.heorhii_dubinin.news;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

public class ViewModelActivity extends AppCompatActivity {

    private static final String TAG = "News";
    public static final String EXTRA_IMAGE = "imageUrl";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_SOURCE = "name";
    public static final String EXTRA_DESCRIPTION = "description";
    public static final String EXTRA_DATE = "date";

    private NewsViewModel newsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final NewsAdapter adapter = new NewsAdapter();
        recyclerView.setAdapter(adapter);

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.getAllNews().observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(@Nullable List<News> news) {
                adapter.setBreakingNews(news);
            }
        });
    }
}
