package com.cyberaccounting.movies.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Safa Amin on 14/06/2019.
 */
@Dao
public interface MovieDao {

    @Query("SELECT * FROM movies")
    LiveData<List<MovieEntity>> loadAllMovies();

    @Insert
    void inserteMovie(MovieEntity movieEntity);

    @Delete
    void deleteMovie(MovieEntity movieEntity);
}
