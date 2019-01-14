package com.example.heorhii_dubinin.news.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert
    void insert(ArticleEntity articleEntity);

//    @Update
//    void update(ArticleEntity news);

//    @Delete
//    void delete(ArticleEntity news);

    @Query("DELETE FROM news_table WHERE id NOT IN (SELECT id FROM news_table ORDER BY id DESC LIMIT 49);")
    void deleteExtraNews();

    @Query("SELECT * FROM news_table ORDER BY id DESC")
    LiveData<List<ArticleEntity>> getAllNews();
}
    //DELETE FROM news_table WHERE id IN (SELECT id FROM news_table ORDER BY id LIMIT 3);
    //DELETE FROM news_table WHERE id IN (SELECT id FROM news_table ORDER BY id LIMIT ((SELECT Count(*) FROM news_table) - 50));
    //DELETE FROM news_table WHERE id NOT IN (SELECT id FROM news_table ORDER BY id DESC LIMIT 10);
    //delete from news_table where id < last_insert_rowid() - 50; - WINNER!!!