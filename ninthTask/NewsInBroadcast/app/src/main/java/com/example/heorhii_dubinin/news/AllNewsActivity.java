package com.example.heorhii_dubinin.news;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.heorhii_dubinin.articleEntities.R;

import java.util.List;

public class AllNewsActivity extends AppCompatActivity {

    private static final String TAG = "AllNewsActivity";
    //    public static final String EXTRA_ID = "id";
    public static final String EXTRA_IMAGE = "imageUrl";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_SOURCE = "name";
    public static final String EXTRA_DESCRIPTION = "description";
    public static final String EXTRA_DATE = "date";

    private NewsViewModel newsViewModel;

    Button cancelJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final NewsAdapter adapter = new NewsAdapter();
        recyclerView.setAdapter(adapter);

        fetchNews(adapter);

        prepareDataToIntent(adapter);

        scheduleJob();

        cancelButtonFunctions();
    }

    private void cancelButtonFunctions() {
        cancelJob = findViewById(R.id.cancel);
        cancelJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelJob(v);
            }
        });
    }

    private void fetchNews(final NewsAdapter adapter) {
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.getAllNews().observe(this, new Observer<List<ArticleEntity>>() {
            @Override
            public void onChanged(@Nullable List<ArticleEntity> articleEntities) {
                adapter.setBreakingNews(articleEntities);
            }
        });
    }

    private void prepareDataToIntent(NewsAdapter adapter) {
        adapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ArticleEntity articleEntity) {
                Intent dataForItemActivity = new Intent(AllNewsActivity.this, ItemActivity.class);

//                dataForItemActivity.putExtra(EXTRA_IMAGE, articleEntity.getId());
                dataForItemActivity.putExtra(EXTRA_IMAGE, articleEntity.getImage());
                dataForItemActivity.putExtra(EXTRA_TITLE, articleEntity.getTitle());
                dataForItemActivity.putExtra(EXTRA_SOURCE, articleEntity.getSourceName());
                dataForItemActivity.putExtra(EXTRA_DESCRIPTION, articleEntity.getDescription());
                dataForItemActivity.putExtra(EXTRA_DATE, articleEntity.getPublishedAt());

                startActivity(dataForItemActivity);
            }
        });
    }

    public void scheduleJob() {
        ComponentName componentName = new ComponentName(this, MyJobService.class);
        JobInfo info = new JobInfo.Builder(321, componentName)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();
        JobScheduler newsScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = newsScheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled");
            Toast.makeText(this, "Job scheduled", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "Job scheduling failed");
            Toast.makeText(this, "Job scheduling failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelJob(View view) {
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Log.d(TAG, "Job cancelled");
    }

}
