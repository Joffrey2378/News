package com.example.heorhii_dubinin.news;

class News {

    private String mTitle;
    private String mImage;

    News(String image, String title) {
        this.mImage = image;
        this.mTitle = title;
    }

    String getmTitle() {
        return mTitle;
    }

    String getmImage() {
        return mImage;
    }
}
