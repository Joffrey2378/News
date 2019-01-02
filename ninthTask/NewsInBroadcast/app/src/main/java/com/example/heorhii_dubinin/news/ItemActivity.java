package com.example.heorhii_dubinin.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heorhii_dubinin.articleEntities.R;
import com.squareup.picasso.Picasso;

import static com.example.heorhii_dubinin.news.AllNewsActivity.EXTRA_DATE;
import static com.example.heorhii_dubinin.news.AllNewsActivity.EXTRA_DESCRIPTION;
import static com.example.heorhii_dubinin.news.AllNewsActivity.EXTRA_IMAGE;
import static com.example.heorhii_dubinin.news.AllNewsActivity.EXTRA_SOURCE;
import static com.example.heorhii_dubinin.news.AllNewsActivity.EXTRA_TITLE;

public class ItemActivity extends AppCompatActivity {

    private static final String TAG = "ItemActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Log.d(TAG, "onCreate: started");

        getPrepareViews();
    }

    private void getPrepareViews() {
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_IMAGE);
        String title = intent.getStringExtra(EXTRA_TITLE);
        String source = intent.getStringExtra(EXTRA_SOURCE);
        String description = intent.getStringExtra(EXTRA_DESCRIPTION);
        String date = intent.getStringExtra(EXTRA_DATE);

        ImageView imageView = findViewById(R.id.url_to_image);
        TextView titleView = findViewById(R.id.title);
        TextView sourceView = findViewById(R.id.name);
        TextView descriptionView = findViewById(R.id.description);
        TextView dateView = findViewById(R.id.published_at);

        Picasso.with(this).load(imageUrl).placeholder(R.drawable.news_icon).fit().centerInside().into(imageView);
        titleView.setText(title);
        sourceView.setText(source);
        descriptionView.setText(description);
        dateView.setText(date);
    }
}