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
    public static final int PERIODICITY_OF_CHECKING_NEWS = 900000;
    public static final int JOB_ID = 123;
    public static final String HTTPS_REQUEST_URL = "https://" +
            "newsapi.org/v2/top-headlines?country=ua&apiKey=eefa8f5b92b24ff7993231986bfa9a96";

    private NewsViewModel newsViewModel;

    private Button cancelJob;

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


        HttpRequestAsyncTask httpRequestAsyncTask = new HttpRequestAsyncTask(new HttpRequestAsyncTask.IResultListener() {
            @Override
            public void onResult(String result) {
                List<ArticleEntity> breakingNews = ArticleStructure.extractNews(result);
                breakingNews.addAll(breakingNews);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
            }
        });
        httpRequestAsyncTask.execute(HTTPS_REQUEST_URL);

        fetchNewsPeriodically();

        cancelButtonFunctions();
    }

    private void cancelButtonFunctions() {
        cancelJob = findViewById(R.id.cancel);
        cancelJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelPeriodicFetching(v);
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

    public void fetchNewsPeriodically() {
        ComponentName componentName = new ComponentName(this, NewsFetchingJobScheduler.class);
        JobInfo info = new JobInfo.Builder(JOB_ID, componentName)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(PERIODICITY_OF_CHECKING_NEWS)
                .build();
        JobScheduler newsScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = newsScheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Subscription created");
            Toast.makeText(this, "Subscribed for news", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "Subscription failed");
            Toast.makeText(this, "Subscription failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelPeriodicFetching(View view) {
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(JOB_ID);
        Toast.makeText(this, "Articles will not update anymore", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Cancel subscription");
    }

}
