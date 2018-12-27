package com.example.heorhii_dubinin.news;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert
    void insert(NewsPiece newsPiece);

//    @Update
//    void update(NewsPiece news);

//    @Delete
//    void delete(NewsPiece news);

    @Query("DELETE FROM NewsPiece WHERE id NOT IN (SELECT id FROM NewsPiece ORDER BY id DESC LIMIT 49);")
    void deleteExtraNews();

    @Query("SELECT * FROM NewsPiece ORDER BY id DESC")
    LiveData<List<NewsPiece>> getAllNews();
}
    //DELETE FROM news_table WHERE id IN (SELECT id FROM news_table ORDER BY id LIMIT 3);
    //DELETE FROM news_table WHERE id IN (SELECT id FROM news_table ORDER BY id LIMIT ((SELECT Count(*) FROM news_table) - 50));
    //DELETE FROM news_table WHERE id NOT IN (SELECT id FROM news_table ORDER BY id DESC LIMIT 10);
    //delete from news_table where id < last_insert_rowid() - 50; - WINNER!!!