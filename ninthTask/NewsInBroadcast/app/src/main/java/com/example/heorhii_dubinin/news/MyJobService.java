package com.example.heorhii_dubinin.news;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.List;

public class MyJobService extends JobService {

    public static final String TAG = "JobService";
    public static final String HTTPS_NEWSAPI = "https://newsapi.org/v2/top-headlines?country=ua&apiKey=eefa8f5b92b24ff7993231986bfa9a96";
    private boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters params) {
        doBackgroundWork(params);
        return true;
    }

    private void doBackgroundWork(JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                showNotification(getApplicationContext(), "" + TAG, "Fresh news!");

                MyAsync myAsync = new MyAsync(new MyAsync.Listener() {
                    @Override
                    public void onLoaded(String json) {
                        List<News> brackingNews = QueryUtils.extractNews(json);
                    }

                    @Override
                    public void onError(String message) {

                    }
                });
                myAsync.execute(HTTPS_NEWSAPI);
                if (jobCancelled) {
                    return;
                }
//                for (int i = 0; i < 7; i++) {
//                    showNotification(getApplicationContext(), "" + TAG, "You better keep coding " + i);
//                    Log.d(TAG, "news: " + i);
//                    if (jobCancelled) {
//                        return;
//                    }
//
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
                Log.d(TAG, "News Updated");
            }
        }).start();
        jobFinished(params, false);
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        jobCancelled = true;
        return true;
    }

    private void showNotification(Context context, CharSequence title, CharSequence message) {
        final int ID = 322;
        final String CHANNEL_ID = "228";

        Intent intent = new Intent(context, NewsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 333, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setAutoCancel(true)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(android.R.drawable.ic_dialog_info);
        notificationManager.notify(ID, builder.build());
    }
}
