package com.yogadarma.core.data.source.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yogadarma.core.data.source.local.room.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("UPDATE movie SET releaseDate=:releaseDate, genres=:genres WHERE id=:id")
    suspend fun insertMovieDetail(id: String, releaseDate: String?, genres: String?)

    @Query("SELECT * FROM movie")
    fun getMovies(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movie WHERE id=:id")
    suspend fun getMovieDetail(id: String): MovieEntity

    @Query("DELETE FROM movie")
    suspend fun deleteAll()
}