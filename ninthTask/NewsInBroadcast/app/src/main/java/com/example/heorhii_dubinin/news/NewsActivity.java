package com.example.heorhii_dubinin.news;

//
//import android.app.job.JobInfo;
//import android.app.job.JobScheduler;
//import android.content.ComponentName;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class NewsActivity extends AppCompatActivity implements NewsAdapter.OnItemClickListener {
//
//    private static final String TAG = "News";
//    public static final String EXTRA_IMAGE = "imageUrl";
//    public static final String EXTRA_TITLE = "title";
//    public static final String EXTRA_SOURCE = "name";
//    public static final String EXTRA_DESCRIPTION = "description";
//    public static final String EXTRA_DATE = "date";
//
//    Button cancel;
//
//    private RecyclerView mRecyclerView;
//    public static final String HTTPS_REQUEST_URL = "https://" +
//            "newsapi.org/v2/top-headlines?country=ua&apiKey=eefa8f5b92b24ff7993231986bfa9a96";
//    private RecyclerView.LayoutManager mLayoutManager;
//    private ArrayList<News> mBrakingNews;
//    private NewsAdapter mAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mRecyclerView = findViewById(R.id.recycler);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mBrakingNews = new ArrayList<>();
//        mLayoutManager = new LinearLayoutManager(this);
//
//        mLayoutManager = (new LinearLayoutManager(this));
//        mAdapter = new NewsAdapter(mBrakingNews, this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();
//        mAdapter.setOnItemClickListener(NewsActivity.this);
//
//        MyAsync myAsync = new MyAsync(new MyAsync.IResultListener() {
//            @Override
//            public void onResult(String result) {
//                List<News> breakingNews = QueryUtils.extractNews(result);
//                mBrakingNews.addAll(breakingNews);
//                mAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onError(String error) {
//            }
//        });
//        myAsync.execute(HTTPS_REQUEST_URL);
//        scheduleJob();
//
//        cancel = findViewById(R.id.cancel);
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cancelJob(v);
//            }
//        });
//    }
//
//    public void scheduleJob() {
//        ComponentName componentName = new ComponentName(this, MyJobService.class);
//        JobInfo info = new JobInfo.Builder(321, componentName)
//                .setRequiresCharging(false)
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
//                .setPersisted(true)
//                .setPeriodic(15 * 60 * 1000)
//                .build();
//        JobScheduler newsScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//        int resultCode = newsScheduler.schedule(info);
//        if (resultCode == JobScheduler.RESULT_SUCCESS) {
//            Log.d(TAG, "Job scheduled");
//        } else {
//            Log.d(TAG, "Job scheduling failed");
//        }
//    }
//
//    public void cancelJob(View view) {
//        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//        scheduler.cancel(123);
//        Log.d(TAG, "Job cancelled");
//    }
//
//    @Override
//    public void onItemClick(int position) {
//        Intent intentItemActivity = new Intent(this, ItemActivity.class);
//        News clckedItem = mBrakingNews.get(position);
//
//        intentItemActivity.putExtra(EXTRA_IMAGE, clckedItem.getImage());
//        intentItemActivity.putExtra(EXTRA_TITLE, clckedItem.getTitle());
//        intentItemActivity.putExtra(EXTRA_SOURCE, clckedItem.getSourceName());
//        intentItemActivity.putExtra(EXTRA_DESCRIPTION, clckedItem.getDescription());
//        intentItemActivity.putExtra(EXTRA_DATE, clckedItem.getPublishedAt());
//
//        startActivity(intentItemActivity);
//    }
//}