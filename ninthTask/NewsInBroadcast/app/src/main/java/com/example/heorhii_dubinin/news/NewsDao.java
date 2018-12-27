package com.example.heorhii_dubinin.news;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert
    void insert(News news);

//    @Update
//    void update(News news);

//    @Delete
//    void delete(News news);

    @Query("DELETE FROM news_table WHERE id NOT IN (SELECT id FROM news_table ORDER BY id DESC LIMIT 49);")
    void deleteExtraNews();

    @Query("SELECT * FROM news_table ORDER BY id DESC")
    LiveData<List<News>> getAllNews();
}
    //DELETE FROM news_table WHERE id IN (SELECT id FROM news_table ORDER BY id LIMIT 3);
    //DELETE FROM news_table WHERE id IN (SELECT id FROM news_table ORDER BY id LIMIT ((SELECT Count(*) FROM news_table) - 50));
    //DELETE FROM news_table WHERE id NOT IN (SELECT id FROM news_table ORDER BY id DESC LIMIT 10);
    //delete from news_table where id < last_insert_rowid() - 50; - WINNER!!!