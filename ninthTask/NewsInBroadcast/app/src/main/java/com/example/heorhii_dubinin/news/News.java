package com.example.heorhii_dubinin.news;

class News {

    private String mImage;
    private String mTitle;
    private String mSourceName;
    private String mDescription;
    private String mPublishedAt;

//    News(String image, String title) {
//        this.mImage = image;
//        this.mTitle = title;
//    }

    public News(String mImage, String mTitle, String mSourceName, String mDescription, String mPublishedAt) {
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

    public String getmSourceName() {
        return mSourceName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmPublishedAt() {
        return mPublishedAt;
    }
}
