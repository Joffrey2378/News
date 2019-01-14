package com.example.heorhii_dubinin.news.networking;

import android.util.Log;

import com.example.heorhii_dubinin.news.persistence.ArticleEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ArticleStructure {
    private static final String TAG = "ArticleStructure";

    public static ArrayList<ArticleEntity> newsExtractFromJson(String json) {

        ArrayList<ArticleEntity> parsedNews = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(json);
            JSONArray newsArray = baseJsonResponse.getJSONArray("articles");

            for (int i = 0; i < newsArray.length(); i++) {
                JSONObject currentNews = newsArray.getJSONObject(i);
                String title = currentNews.getString("title");
                String urlToImage = currentNews.getString("urlToImage");
                String sourceName = currentNews.getJSONObject("source").getString("name");
                String description = currentNews.getString("description");
                String publishedAt = currentNews.getString("publishedAt");

                ArticleEntity news = new ArticleEntity(urlToImage,
                        title,
                        sourceName,
                        description,
                        publishedAt);
                parsedNews.add(news);

                Log.d(TAG, "article: " + i + " " + news);
            }
            Log.d(TAG, "newsExtractFromJson: " + parsedNews.size());
        } catch (JSONException e) {
            Log.e(TAG, "Problem parsing the news JSON results", e);
        }
        return parsedNews;
    }
}