package com.example.heorhii_dubinin.news;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class AllNewsActivity extends AppCompatActivity {

    //    public static final String EXTRA_ID = "id";
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
        newsViewModel.getAllNews().observe(this, new Observer<List<NewsPiece>>() {
            @Override
            public void onChanged(@Nullable List<NewsPiece> newsPieces) {
                adapter.setBreakingNews(newsPieces);
            }
        });

        adapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(NewsPiece newsPiece) {
                Intent dataForItemActivity = new Intent(AllNewsActivity.this, ItemActivity.class);

//                dataForItemActivity.putExtra(EXTRA_IMAGE, newsPiece.getId());
                dataForItemActivity.putExtra(EXTRA_IMAGE, newsPiece.getImage());
                dataForItemActivity.putExtra(EXTRA_TITLE, newsPiece.getTitle());
                dataForItemActivity.putExtra(EXTRA_SOURCE, newsPiece.getSourceName());
                dataForItemActivity.putExtra(EXTRA_DESCRIPTION, newsPiece.getDescription());
                dataForItemActivity.putExtra(EXTRA_DATE, newsPiece.getPublishedAt());

                startActivity(dataForItemActivity);
            }
        });
    }
}
