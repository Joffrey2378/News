package com.example.heorhii_dubinin.news;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "news_table")
class News {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String image;
    private String title;
    private String sourceName;
    private String description;
    private String publishedAt;

    News(String image, String title, String sourceName, String description, String publishedAt) {
        this.image = image;
        this.title = title;
        this.sourceName = sourceName;
        this.description = description;
        this.publishedAt = publishedAt;
    }

    @Override
    public String toString() {
        return "News{" +
                "image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", description='" + description + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public String getSourceName() {
//        return sourceName;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public String getPublishedAt() {
//        return publishedAt;
//    }
}
