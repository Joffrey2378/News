package com.example.heorhii_dubinin.news;

class News {

    private String mImage;
    private String mTitle;
    private String mSourceName;
    private String mDescription;
    private String mPublishedAt;

    News(String mImage, String mTitle, String mSourceName, String mDescription, String mPublishedAt) {
        this.mImage = mImage;
        this.mTitle = mTitle;
        this.mSourceName = mSourceName;
        this.mDescription = mDescription;
        this.mPublishedAt = mPublishedAt;
    }

    @Override
    public String toString() {
        return "News{" +
                "mImage='" + mImage + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mSourceName='" + mSourceName + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mPublishedAt='" + mPublishedAt + '\'' +
                '}';
    }

    String getmTitle() {
        return mTitle;
    }

    String getmImage() {
        return mImage;
    }

    String getmSourceName() {
        return mSourceName;
    }

    String getmDescription() {
        return mDescription;
    }

    String getmPublishedAt() {
        return mPublishedAt;
    }
}
