package com.example.heorhii_dubinin.news;

public class News {

    private String mTitle;
    private String mPublishedAt;
    private String mImage;

    public News(String title, String publishedAt, String image) {
        this.mTitle = title;
        this.mPublishedAt = publishedAt;
        this.mImage = image;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmPublishedAt() {
        return mPublishedAt;
    }

    public void setmPublishedAt(String mPublishedAt) {
        this.mPublishedAt = mPublishedAt;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }
}
