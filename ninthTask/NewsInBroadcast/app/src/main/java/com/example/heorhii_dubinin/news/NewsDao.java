package com.example.heorhii_dubinin.news;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface NewsDao {

    @Insert
    void insert(News news);

    @Update
    void update(News news);

    @Delete
    void delete(News news);

    @Query("DELETE FROM news_table")
    void deleteExtraNews();

    @Query("SELECT * FROM news_table ORDER BY id DESC LIMIT 50")
    void getAllNews();
}
